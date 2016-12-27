package json.example.nn.databasetester;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Info extends AppCompatActivity implements View.OnClickListener {
    private TextView tvName, tvMail, tvNrc, tvPhno;
    private Button btnUpdate;
    private int id = 0;
    private String strName, strMail, strPhno, strNrc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        tvName = (TextView) findViewById(R.id.tvName);
        tvMail = (TextView) findViewById(R.id.tvMail);
        tvNrc = (TextView) findViewById(R.id.tvNrc);
        tvPhno = (TextView) findViewById(R.id.tvPhno);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);
        Intent i = getIntent();
        id = i.getExtras().getInt("fmaId");
        strName = i.getExtras().getString("fmaName");
        strMail = i.getExtras().getString("fmaMail");
        strNrc = i.getExtras().getString("fmaNrc");
        strPhno = i.getExtras().getString("fmaPhno");
        tvName.setText(strName);
        tvMail.setText(strMail);
        tvNrc.setText(strNrc);
        tvPhno.setText(strPhno);
    }

    @Override
    public void onClick(View v) {
        Intent in = new Intent(Info.this, Update.class);
        in.putExtra("ifId", id);
        in.putExtra("ifName", strName);
        in.putExtra("ifMail", strMail);
        in.putExtra("ifNrc", strNrc);
        in.putExtra("ifPhno", strPhno);
        startActivity(in);
    }
}
