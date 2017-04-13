package com.huaqin.recyclerviewdemo.core.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.huaqin.recyclerviewdemo.R;
import com.huaqin.recyclerviewdemo.core.bean.NewsItem;
import com.huaqin.recyclerviewdemo.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ubuntu on 17-4-13.
 */

public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private List<String> mUrls;

    public StaggeredAdapter(Context context, List<String> urls) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        mUrls = urls;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.staggered_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageLoader.getInstance().showImage(mContext, mUrls.get(position), holder.ivImg);
    }

    @Override
    public int getItemCount() {
        return mUrls.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.cardview)
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}