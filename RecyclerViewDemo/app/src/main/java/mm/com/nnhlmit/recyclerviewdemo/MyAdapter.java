package mm.com.nnhlmit.recyclerviewdemo;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<Person> list = Collections.emptyList();

    public MyAdapter(Context context, List<Person> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Person person = list.get(position);
        holder.iconV.setImageResource(person.getPic());
        holder.name.setText(person.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iconV;
        private TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            iconV = (ImageView) itemView.findViewById(R.id.imgV);
            name = (TextView) itemView.findViewById(R.id.tvName);
            name.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            delItem(getPosition());
        }
    }

    public void delItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }
}
