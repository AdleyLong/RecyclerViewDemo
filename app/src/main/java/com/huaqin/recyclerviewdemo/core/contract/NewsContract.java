package com.huaqin.recyclerviewdemo.core.contract;

import android.app.Activity;

import com.huaqin.recyclerviewdemo.core.bean.NewsItem;
import com.huaqin.recyclerviewdemo.core.presenter.BasePresenter;
import com.huaqin.recyclerviewdemo.core.view.BaseView;

import java.util.List;

/**
 * Created by ubuntu on 17-4-13.
 */

public class NewsContract {
    public interface View extends BaseView<Presenter> {
        void onSuccess(List<NewsItem> list);

        void onFailure(String error);

    }


    public interface Presenter extends BasePresenter {
        void getData(Activity activity);
    }
}
