package com.huaqin.recyclerviewdemo.core.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huaqin.recyclerviewdemo.R;
import com.huaqin.recyclerviewdemo.util.ImageLoader;

/**
 * Created by ubuntu on 17-4-14.
 */

public class ThirdGridAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context mContext;

    public ThirdGridAdapter(Context context) {
        mContext = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.third_grid_layout, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.third_iv);
            holder.textView = (TextView) convertView.findViewById(R.id.third_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText("应用0" + position);
//        ImageLoader.getInstance().showImage(mContext, "", holder.imageView);
        return convertView;
    }


    static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}