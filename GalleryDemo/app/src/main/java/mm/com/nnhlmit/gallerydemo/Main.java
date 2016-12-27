package mm.com.nnhlmit.gallerydemo;

import android.graphics.drawable.Drawable;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class Main extends AppCompatActivity implements View.OnClickListener {
    private EditText etInput;
    private Button btnBack, btnGo;
    private Gallery gallery;
    private ImageView imgSearch, imgCenter;
    private PowerManager.WakeLock wl;
    private LinearLayout lOut;
    private ArrayList<Drawable> imgArr;
    private MyGalleryAdapter adapter;
    private int posit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "hello");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        loadImage();
        imgSearch = (ImageView) findViewById(R.id.imgSearch);
        imgCenter = (ImageView) findViewById(R.id.imgCenter);
        etInput = (EditText) findViewById(R.id.etInput);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnGo = (Button) findViewById(R.id.btnForward);
        gallery = (Gallery) findViewById(R.id.gallery);
        lOut = (LinearLayout) findViewById(R.id.lOut);
        adapter = new MyGalleryAdapter(getApplicationContext(), imgArr);
        gallery.setAdapter(adapter);
        imgCenter.setVisibility(View.VISIBLE);
        lOut.setVisibility(View.VISIBLE);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posit = position;
                imgCenter.setImageDrawable(imgArr.get(position));
            }
        });
        btnGo.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    private void loadImage() {
        imgArr = new ArrayList<Drawable>();
        imgArr.add(getResources().getDrawable(R.drawable.one));
        imgArr.add(getResources().getDrawable(R.drawable.two));
        imgArr.add(getResources().getDrawable(R.drawable.three));
        imgArr.add(getResources().getDrawable(R.drawable.four));
        imgArr.add(getResources().getDrawable(R.drawable.five));
        imgArr.add(getResources().getDrawable(R.drawable.six));
        imgArr.add(getResources().getDrawable(R.drawable.seven));
        imgArr.add(getResources().getDrawable(R.drawable.eight));
        imgArr.add(getResources().getDrawable(R.drawable.nine));
        imgArr.add(getResources().getDrawable(R.drawable.ten));
        imgArr.add(getResources().getDrawable(R.drawable.eleven));
        imgArr.add(getResources().getDrawable(R.drawable.twelve));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnForward:
                if (posit > 0) {
                    ++posit;
                } else {
                    btnGo.setEnabled(false);
                }
                imgCenter.setImageDrawable(imgArr.get(posit));
                break;
            case R.id.btnBack:
                if (posit > 0) {
                    --posit;
                } else {
                    btnBack.setEnabled(false);
                }
                imgCenter.setImageDrawable(imgArr.get(posit));
                break;
        }
    }
}
