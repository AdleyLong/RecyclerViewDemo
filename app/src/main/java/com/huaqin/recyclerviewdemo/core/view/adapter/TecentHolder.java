package com.huaqin.recyclerviewdemo.core.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huaqin.recyclerviewdemo.R;
import com.huaqin.recyclerviewdemo.widget.NoScrollGridView;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ubuntu on 17-4-14.
 */

public class TecentHolder {
    public static final int ITEM_BANNER = 0;
    public static final int ITEM_THIRD = 1;
    public static final int ITEM_CATEGORY = 2;
    public static final int ITEM_LIST = 3;
    public static final int ITEM_AD = 4;

    private LayoutInflater mLayoutInflater;

    public TecentHolder(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public RecyclerView.ViewHolder getViewHolder(ViewGroup parent, int type) {
        switch (type) {
            case ITEM_BANNER:
                return new BannerViewHolder
                        (mLayoutInflater.inflate(R.layout.fragment_home_banner, parent, false));
            case ITEM_THIRD:
                return new ThirdViewHolder
                        (mLayoutInflater.inflate(R.layout.fragment_home_third, parent, false));
            case ITEM_CATEGORY:
                return new CategoryViewHolder
                        (mLayoutInflater.inflate(R.layout.fragment_home_category, parent, false));
            case ITEM_LIST:
                return new ListViewHolder
                        (mLayoutInflater.inflate(R.layout.fragment_home_list, parent, false));
            case ITEM_AD:
                return new AdViewHolder
                        (mLayoutInflater.inflate(R.layout.fragment_home_ad, parent, false));
        }
        return new BannerViewHolder
                (mLayoutInflater.inflate(R.layout.fragment_home_banner, parent, false));
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner)
        public Banner bannerGuideContent;
        @BindView(R.id.ll_banner)
        public LinearLayout llBanner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ThirdViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.third_grid_view)
        public NoScrollGridView thirdGridView;

        public ThirdViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.categoryTv)
        public TextView categoryTv;
        @BindView(R.id.category_iv)
        public ImageView categoryIv;
        @BindView(R.id.category_title_tv)
        public TextView categoryTitleTv;
        @BindView(R.id.category_sub_tv)
        public TextView categorySubTv;
        @BindView(R.id.rl_more)
        public RelativeLayout rlMore;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public class AdViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ad_iv)
        public ImageView adIv;

        public AdViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public class ListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_grid_view)
        public NoScrollGridView listGridView;
        @BindView(R.id.rl_refresh)
        public RelativeLayout rlRefresh;

        public ListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
