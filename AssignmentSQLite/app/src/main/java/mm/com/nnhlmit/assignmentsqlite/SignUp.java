package mm.com.nnhlmit.assignmentsqlite;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by MIT69 on 12/23/2016.
 */

public class SignUp extends Activity {

    private EditText etFname, etLname, etPhno, etNrc, etAddress, etPass;
    private RadioGroup radioGroup;
    private Button btnRegister, btnReset;
    private Spinner citySpin;
    private String fName, lName, city, phno, nrc, address, pass;
    private int gender = 0;
    private MyHelperAdapter adapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_form);
        etFname = (EditText) findViewById(R.id.etFname);
        etLname = (EditText) findViewById(R.id.etLname);
        etPhno = (EditText) findViewById(R.id.etPhno);
        etPass = (EditText) findViewById(R.id.etPass);
        etNrc = (EditText) findViewById(R.id.etNrc);
        etAddress = (EditText) findViewById(R.id.etAddress);
        radioGroup = (RadioGroup) findViewById(R.id.rGroup);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        citySpin = (Spinner) findViewById(R.id.citySpin);
        btnReset = (Button) findViewById(R.id.btnReset);
        adapter = new MyHelperAdapter(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rMail:
                        gender = 1;
                        break;
                    case R.id.rFemale:
                        gender = 0;
                        break;
                }
            }
        });
        citySpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fName = etFname.getText().toString();
                lName = etLname.getText().toString();
                nrc = etNrc.getText().toString();
                address = etAddress.getText().toString();
                phno = etPhno.getText().toString();
                pass = etPass.getText().toString();
                //   Toast.makeText(getApplicationContext(), fName + "/" + lName + "/" + gender + "/" + nrc + "/" + city + "/" + pass + "/" + phno + "/" + address, Toast.LENGTH_LONG).show();
                adapter.dbOpen();
                long id = adapter.dataInsert(fName, lName, nrc, pass, phno, address, city, gender);
                Toast.makeText(getApplicationContext(), "Saved with id=" + id, Toast.LENGTH_SHORT).show();
                adapter.dbClose();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etFname.setText("");
                etLname.setText("");
                etAddress.setText("");
                etNrc.setText("");
                etPhno.setText("");
                etPass.setText("");
            }
        });
    }
}
