package mm.com.nnhlmit.optimizedlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MIT69 on 12/28/2016.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Person> list;

    public MyAdapter(Context context, ArrayList<Person> list) {
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
        return position;
    }

    public class ViewHolder {
        TextView tvName, tvPhno;

        public ViewHolder(View v) {
            tvName = (TextView) v.findViewById(R.id.tvName);
            tvPhno = (TextView) v.findViewById(R.id.tvPhno);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder vHolder = null;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item, parent, false);
            vHolder = new ViewHolder(v);
            v.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) v.getTag();
        }
        vHolder.tvName.setText(list.get(position).getName());
        vHolder.tvPhno.setText(list.get(position).getPhno());
        return v;
    }
}
