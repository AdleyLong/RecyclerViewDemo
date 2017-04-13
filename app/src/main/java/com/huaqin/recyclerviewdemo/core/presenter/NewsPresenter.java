package com.huaqin.recyclerviewdemo.core.presenter;

import android.app.Activity;

import com.huaqin.recyclerviewdemo.core.bean.NewsItem;
import com.huaqin.recyclerviewdemo.core.contract.NewsContract;
import com.huaqin.recyclerviewdemo.core.model.BaseModelCallBack;
import com.huaqin.recyclerviewdemo.core.model.NewsModel;

import java.util.List;

/**
 * Created by ubuntu on 17-4-13.
 */

public class NewsPresenter implements NewsContract.Presenter {

    private NewsModel mModel;
    private NewsContract.View mView;

    public NewsPresenter(NewsContract.View view) {
        mModel = new NewsModel();
        mView = view;
    }

    @Override
    public void getData(Activity activity) {
        mModel.getData(activity, new BaseModelCallBack<List<NewsItem>>() {

            @Override
            public void onResponse(List<NewsItem> response) {
                mView.onSuccess(response);
            }

            @Override
            public void onFailure(String error) {
                mView.onFailure(error);
            }
        });
    }
}
