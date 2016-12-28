package mm.com.nnhlmit.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rView;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rView = (RecyclerView) findViewById(R.id.myRView);
        adapter = new MyAdapter(getApplicationContext(), getPerson());
        rView.setAdapter(adapter);
        rView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    public List<Person> getPerson() {
        int[] icons = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine, R.drawable.ten, R.drawable.eleven, R.drawable.twelve};
        String[] name = {"Nyi Nyi", "Mg Mg", "Aung Aung", "U Ba", "U Mya", "Ko Ko", "Mya Mya", "Ma Ma", "Hla Hla", "U Hla", "U Mya", "Kyaw Kyaw"};
        List<Person> list = new ArrayList<Person>();
        for (int i = 0; i < icons.length; i++) {
            Person p = new Person();
            p.setName(name[i]);
            p.setPic(icons[i]);
            list.add(p);
        }
        return list;
    }
}
