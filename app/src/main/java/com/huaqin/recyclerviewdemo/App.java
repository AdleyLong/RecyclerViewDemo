package com.huaqin.recyclerviewdemo;

import android.app.Application;

/**
 * Created by ubuntu on 17-4-13.
 */

public class App extends Application {
    public static App singleton = null;

    private final String TAG = "App";

    public static App getInstance() {
        return singleton;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;

    }

}