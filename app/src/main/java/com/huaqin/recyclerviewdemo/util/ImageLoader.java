package com.huaqin.recyclerviewdemo.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.huaqin.recyclerviewdemo.R;
import com.squareup.picasso.Picasso;

/**
 * Created by ubuntu on 17-4-13.
 */

public class ImageLoader {

    private static ImageLoader imageLoader = null;

    public static ImageLoader getInstance() {
        if (imageLoader == null) {
            imageLoader = new ImageLoader();
        }
        return imageLoader;
    }

    //网络图片加载 placeholder
    public void showImage(Context context, String url, ImageView imageView) {
        //要先判断url是否为空，不然Picasso会报异常
        if (!TextUtils.isEmpty(url)) {
            Picasso
                    .with(context)
                    .load(url)
                    .placeholder(R.drawable.place_holder)
                    .error(R.drawable.place_holder)
                    .into(imageView);
        } else {
            imageView.setImageResource(R.drawable.place_holder);
        }

    }
}
