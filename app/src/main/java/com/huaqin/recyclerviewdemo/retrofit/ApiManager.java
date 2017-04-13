package com.huaqin.recyclerviewdemo.retrofit;

/**
 * Created by ubuntu on 17-4-13.
 */

public class ApiManager {

    private static ApiManager util = null;
    private INewsApi mApiNews;

    /**
     * 单例
     *
     * @return
     */
    public static ApiManager getInstance() {
        if (util == null) {
            util = new ApiManager();
        }
        return util;
    }


    /**
     * news接口
     *
     * @return
     */
    public INewsApi apiNews() {
        if (mApiNews == null) {
            mApiNews = RetrofitClient.getInstance().create("http://c.m.163.com", INewsApi.class);
        }
        return mApiNews;
    }

}
