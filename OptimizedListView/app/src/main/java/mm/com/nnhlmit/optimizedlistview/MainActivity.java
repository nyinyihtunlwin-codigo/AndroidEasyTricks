package mm.com.nnhlmit.optimizedlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Person> list;
    private String[] name = {"Mg Mg", "Nyi Nyi", "Ko Ko", "Ma Ma", "Kyaw Kyaw", "Tun Tun", "U Ba", "U Mya", "Hla Hla"};
    private String[] phno = {"09796841952", "09796841952", "09796841952", "09796841952", "09796841952", "09796841952", "09796841952", "09796841952", "09796841952"};
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lvId);
        list = new ArrayList<Person>();
        for (int i = 0; i < name.length; i++) {
            Person p = new Person();
            p.setName(name[i]);
            p.setPhno(phno[i]);
            list.add(p);
        }
        adapter = new MyAdapter(getApplicationContext(), list);
        listView.setAdapter(adapter);
    }
}
