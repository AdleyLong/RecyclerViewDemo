package com.huaqin.recyclerviewdemo.core.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huaqin.recyclerviewdemo.util.Constants;
import com.huaqin.recyclerviewdemo.util.ImageLoader;
import com.huaqin.recyclerviewdemo.widget.NoScrollGridView;
import com.huaqin.recyclerviewdemo.widget.PicassoImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ubuntu on 17-4-14.
 */

public class TecentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private TecentHolder mHomeHolder;
    private Context mContext;
    private Activity mActivity;
    public Banner mBanner;

    public TecentAdapter(Activity activity, Context context) {
        mContext = context;
        mHomeHolder = new TecentHolder(context);
        mActivity = activity;
    }

    @Override
    public int getItemViewType(int position) {

        int type = 0;
        switch (position) {
            case 0:
                type = TecentHolder.ITEM_BANNER;
                break;
            case 1:
                type = TecentHolder.ITEM_THIRD;
                break;
            case 2:
                type = TecentHolder.ITEM_CATEGORY;
                break;
            case 3:
            case 5:
                type = TecentHolder.ITEM_LIST;
                break;
            case 4:
                type = TecentHolder.ITEM_AD;
                break;
        }
        return type;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mHomeHolder.getViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof TecentHolder.BannerViewHolder) {
            List<String> urls = Arrays.asList(Constants.mBannerUrls);
            List<String> titles = Arrays.asList(Constants.mBannerNames);

            mBanner = ((TecentHolder.BannerViewHolder) holder).bannerGuideContent;

            ((TecentHolder.BannerViewHolder) holder).bannerGuideContent
                    .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                    .setBannerTitles(titles)
                    .setImageLoader(new PicassoImageLoader())
                    .setImages(urls)
                    .setDelayTime(2000)
                    .setIndicatorGravity(BannerConfig.RIGHT)
                    .setOnBannerClickListener(new OnBannerClickListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            Toast.makeText(mContext, "position=" + (position - 1), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .start();

        } else if (holder instanceof TecentHolder.ThirdViewHolder) {

            NoScrollGridView gridView = ((TecentHolder.ThirdViewHolder) holder).thirdGridView;
            ThirdGridAdapter adapter = new ThirdGridAdapter(mContext);
            gridView.setAdapter(adapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(mContext, "I'm third app", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (holder instanceof TecentHolder.AdViewHolder) {

            ImageLoader.getInstance().showImage(mContext, "http://file.juzimi.com/weibopic/jezxmd2.jpg", ((TecentHolder.AdViewHolder) holder).adIv);
            ((TecentHolder.AdViewHolder) holder).adIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "I'm ad", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (holder instanceof TecentHolder.CategoryViewHolder) {

            TextView cateText = ((TecentHolder.CategoryViewHolder) holder).categoryTv;
            TextView titleText = ((TecentHolder.CategoryViewHolder) holder).categoryTitleTv;
            TextView subText = ((TecentHolder.CategoryViewHolder) holder).categorySubTv;
            ImageView cateIv = ((TecentHolder.CategoryViewHolder) holder).categoryIv;
            RelativeLayout rlMore = ((TecentHolder.CategoryViewHolder) holder).rlMore;

            cateText.setText("热门综艺");
            titleText.setText("人名的名义");
            subText.setText("育良书记被揭老底，当场发飙怒指关二代！");
            ImageLoader.getInstance().showImage(mContext, "http://www.people.com.cn/mediafile/pic/20170405/23/8596374254589647023.jpg", cateIv);

            cateIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "I'm big pic", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (holder instanceof TecentHolder.ListViewHolder) {

            NoScrollGridView listGridView = ((TecentHolder.ListViewHolder) holder).listGridView;
            final ListGridAdapter adapter = new ListGridAdapter(mContext, mActivity);
            listGridView.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public void setBannerPlay(boolean b) {
        if (mBanner != null) {
            if (b) {
                mBanner.startAutoPlay();
            } else {
                mBanner.stopAutoPlay();
            }
        }
    }
}