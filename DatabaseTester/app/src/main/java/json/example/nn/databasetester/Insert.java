package json.example.nn.databasetester;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class Insert extends AppCompatActivity {
    private EditText etName, etMail, etNrc, etPhno;
    private Button btnInsert;
    private String name, email, nrc, phno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        etName = (EditText) findViewById(R.id.etName);
        etMail = (EditText) findViewById(R.id.etMail);
        etNrc = (EditText) findViewById(R.id.etNrc);
        etPhno = (EditText) findViewById(R.id.etPhno);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString();
                email = etMail.getText().toString();
                nrc = etNrc.getText().toString();
                phno = etPhno.getText().toString();
                new ReadJSONFeedTask().execute();
            }
        });
    }

    public static String sendJsonUrl(String myUrl, String Json) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 50000;

        try {
            URL url = new URL(myUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            writer.write(Json);
            writer.close();
            conn.connect();
            int response = conn.getResponseCode();
            is = conn.getInputStream();
            String contentAsString = readIt(is, len);
            return contentAsString;
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

    private class ReadJSONFeedTask extends AsyncTask<String, Void, String> {

        JSONObject jsonobj = new JSONObject();

        protected String doInBackground(String... strings) {
            String result = null;
            try {
                jsonobj.put("name", name);
                jsonobj.put("email", email);
                jsonobj.put("nrc", nrc);
                jsonobj.put("phno", phno);
                result = sendJsonUrl("http://192.168.43.113:8080/demoservice/api/demo/insert", jsonobj.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            etName.setText("");
            etMail.setText("");
            etNrc.setText("");
            etPhno.setText("");
            startActivity(new Intent(Insert.this, MainActivity.class));
        }
    }
}
