package mm.com.nnhlmit.assignmentsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etUname, etUpass;
    private Button btnSignUp, btnLogin;
    private MyHelperAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUname = (EditText) findViewById(R.id.etUname);
        etUpass = (EditText) findViewById(R.id.etUpass);
        btnSignUp = (Button) findViewById(R.id.btnSignup);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        adapter = new MyHelperAdapter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignup:
                startActivity(new Intent(MainActivity.this, SignUp.class));
                break;
            case R.id.btnLogin:
                String name = etUname.getText().toString();
                String pass = etUpass.getText().toString();
                adapter.dbOpen();
                Student student = adapter.dataQuery(name, pass);
                if (student.getfName().equals("Success")) {
                    Toast.makeText(getApplicationContext(), student.getfName(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Student_List.class));
                } else {
                    Toast.makeText(getApplicationContext(), "No Account! Please Sign Up!", Toast.LENGTH_SHORT).show();
                    etUname.setText("");
                    etUpass.setText("");
                }
                adapter.dbClose();

        }
    }
}
