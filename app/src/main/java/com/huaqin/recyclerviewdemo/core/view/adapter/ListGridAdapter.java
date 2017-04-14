package com.huaqin.recyclerviewdemo.core.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huaqin.recyclerviewdemo.R;
import com.huaqin.recyclerviewdemo.util.Constants;
import com.huaqin.recyclerviewdemo.util.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ubuntu on 17-4-14.
 */

public class ListGridAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context mContext;
    private Activity mActivity;

    public ListGridAdapter(Context context, Activity activity) {
        mContext = context;
        mActivity = activity;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 6;
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_grid_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageLoader.getInstance().showImage(mContext, Constants.mBannerUrls[position], holder.ivContent);

        holder.tvTitle.setText(Constants.mBannerNames[position]);
        holder.tvSubtitle.setText(Constants.mSubTitles[position]);

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_content)
        ImageView ivContent;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.rl_title)
        RelativeLayout rlTitle;
        @BindView(R.id.tv_subtitle)
        TextView tvSubtitle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}