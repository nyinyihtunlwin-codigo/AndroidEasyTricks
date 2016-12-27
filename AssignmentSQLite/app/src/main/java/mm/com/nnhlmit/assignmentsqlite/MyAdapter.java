package mm.com.nnhlmit.assignmentsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MIT69 on 12/23/2016.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Student> stdList;
    private LayoutInflater inflater;

    public MyAdapter(Context context, ArrayList<Student> stdList) {
        this.context = context;
        this.stdList = stdList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return stdList.size();
    }

    @Override
    public Object getItem(int position) {
        return stdList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }
        TextView tvItem = (TextView) convertView.findViewById(R.id.tvItem);
        tvItem.setText(stdList.get(position).getfName() + " " + stdList.get(position).getlName());
        return convertView;
    }
}
