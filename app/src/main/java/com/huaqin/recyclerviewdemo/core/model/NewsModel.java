package com.huaqin.recyclerviewdemo.core.model;

import android.app.Activity;
import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;
import com.huaqin.recyclerviewdemo.core.bean.NewsItem;
import com.huaqin.recyclerviewdemo.retrofit.ApiManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ubuntu on 17-4-13.
 */

public class NewsModel {
    private static final String TAG = "NewsModel";

    public void getData(Activity activity, final BaseModelCallBack callBack) {
        ApiManager.getInstance().apiNews()
                .getNewsList("T1348648517839", 0, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Map<String,List<NewsItem>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(e.toString());
                    }

                    @Override
                    public void onNext(Map<String,List<NewsItem>> map) {
                        List<NewsItem> list = map.get("T1348648517839");
                        callBack.onResponse(list);
                    }
                });
    }
}
