package mm.com.nnhlmit.gallerydemo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by MIT69 on 12/27/2016.
 */

public class MyGalleryAdapter extends BaseAdapter {
    private ArrayList<Drawable> imgList;
    private Context context;
    private ImageHolder holder;
    private ImageView imageView;

    public MyGalleryAdapter(Context context, ArrayList<Drawable> imgList) {
        this.context = context;
        this.imgList = imgList;
    }

    @Override
    public int getCount() {
        return imgList.size();
    }

    @Override
    public Object getItem(int position) {
        return imgList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ImageHolder();
            imageView = new ImageView(context);
            imageView.setPadding(3, 3, 3, 3);
            convertView = imageView;
            holder.imageView = imageView;
            convertView.setTag(holder);
        } else {
            holder = (ImageHolder) convertView.getTag();
        }
        holder.imageView.setImageDrawable(imgList.get(position));
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.imageView.setLayoutParams(new Gallery.LayoutParams(70, 80));
        return imageView;
    }

    public class ImageHolder {
        ImageView imageView;
    }
}
