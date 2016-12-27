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

public class Update extends AppCompatActivity {
    private EditText etUName, etUM, etUNrc, etUP;
    private Button btnUpdate, btnDelete;
    private String name, Uname, mail, Umail, nrc, Unrc, phno, Uphno;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        etUName = (EditText) findViewById(R.id.etUName);
        etUM = (EditText) findViewById(R.id.etUM);
        etUNrc = (EditText) findViewById(R.id.etUNrc);
        etUP = (EditText) findViewById(R.id.etUP);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        Intent i = getIntent();
        name = i.getExtras().getString("ifName");
        mail = i.getExtras().getString("ifMail");
        nrc = i.getExtras().getString("ifNrc");
        phno = i.getExtras().getString("ifPhno");
        id = i.getExtras().getInt("ifId");
        etUName.setText(name);
        etUM.setText(mail);
        etUNrc.setText(nrc);
        etUP.setText(phno);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyDAsyn().execute();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uname = etUName.getText().toString();
                Umail = etUM.getText().toString();
                Unrc = etUNrc.getText().toString();
                Uphno = etUP.getText().toString();
                new MyJAsyn().execute();
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

    private class MyDAsyn extends AsyncTask<String, Void, String> {

        JSONObject jObj = new JSONObject();

        @Override
        protected String doInBackground(String... params) {
            String result = null;
            try {
                jObj.put("id", id);
                jObj.put("name", Uname);
                jObj.put("email", Umail);
                jObj.put("nrc", Unrc);
                jObj.put("phno", Uphno);
                result = sendJsonUrl(getString(R.string.del_url), jObj.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            startActivity(new Intent(Update.this, MainActivity.class));
        }
    }

    private class MyJAsyn extends AsyncTask<String, Void, String> {

        JSONObject jsonobj = new JSONObject();

        protected String doInBackground(String... strings) {
            String result = null;
            try {
                jsonobj.put("id", id);
                jsonobj.put("name", Uname);
                jsonobj.put("email", Umail);
                jsonobj.put("nrc", Unrc);
                jsonobj.put("phno", Uphno);
                result = sendJsonUrl(getString(R.string.update_url), jsonobj.toString());
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
            etUName.setText("");
            etUM.setText("");
            etUNrc.setText("");
            etUP.setText("");
            startActivity(new Intent(Update.this, MainActivity.class));
        }
    }
}
