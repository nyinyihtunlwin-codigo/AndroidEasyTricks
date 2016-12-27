package mm.com.nnhlmit.assignmentsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Student_List extends AppCompatActivity {

    private ListView lv;
    private MyHelperAdapter adapter;
    private ArrayList<Student> dataList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student__list);
        lv = (ListView) findViewById(R.id.lvId);
        dataList = new ArrayList<Student>();
        adapter = new MyHelperAdapter(this);
        adapter.dbOpen();
        dataList = adapter.dataUpload();
        adapter.dbClose();
        myAdapter = new MyAdapter(getApplicationContext(), dataList);
        lv.setAdapter(myAdapter);
    }
}
