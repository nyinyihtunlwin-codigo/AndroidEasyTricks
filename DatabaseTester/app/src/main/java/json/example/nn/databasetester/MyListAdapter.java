package json.example.nn.databasetester;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by DELL on 12/20/2016.
 */

public class MyListAdapter extends BaseAdapter {
    private ArrayList<JSONObject> list;
    private Context context;

    public MyListAdapter(Context context, ArrayList<JSONObject> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }
        TextView tvItem = (TextView) convertView.findViewById(R.id.tvItem);
        try {
            tvItem.setText(list.get(position).getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return convertView;
    }
}
