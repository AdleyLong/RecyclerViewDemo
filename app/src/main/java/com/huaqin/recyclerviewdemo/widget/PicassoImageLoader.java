package com.huaqin.recyclerviewdemo.widget;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.huaqin.recyclerviewdemo.util.ImageLoader;

/**
 * Created by ubuntu on 17-4-14.
 */

public class PicassoImageLoader extends com.youth.banner.loader.ImageLoader {


    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //要先判断url是否为空，不然Picasso会报异常
        String url = (String) path;
        ImageLoader.getInstance().showImage(context, url, imageView);
    }
}