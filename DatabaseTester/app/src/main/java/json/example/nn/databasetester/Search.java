package json.example.nn.databasetester;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Search extends AppCompatActivity {

    private EditText etSearch;
    private Button btnS;
    private TextView tvNameS, tvMailS, tvNrcS, tvPhnoS;
    private CardView cardView;
    private String searchStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        etSearch = (EditText) findViewById(R.id.etSearch);
        tvNameS = (TextView) findViewById(R.id.tvNameS);
        tvMailS = (TextView) findViewById(R.id.tvMailS);
        tvNrcS = (TextView) findViewById(R.id.tvNrcS);
        tvPhnoS = (TextView) findViewById(R.id.tvPhnoS);
        btnS = (Button) findViewById(R.id.btnS);
        cardView = (CardView) findViewById(R.id.card_search);
        cardView.setVisibility(View.INVISIBLE);
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etSearch.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();
                } else {
                    searchStr = etSearch.getText().toString();
                    new HerAsyn().execute();
                    cardView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public static String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500000;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            //Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public static String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

    public class HerAsyn extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String res = null;
            try {
                res = downloadUrl(getString(R.string.search_url) + searchStr);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                tvNameS.setText(jsonObject.getString("name"));
                tvMailS.setText(jsonObject.getString("email"));
                tvNrcS.setText(jsonObject.getString("nrc"));
                tvPhnoS.setText(jsonObject.getString("phno"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
