package com.huaqin.recyclerviewdemo.core.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.huaqin.recyclerviewdemo.BaseActivity;
import com.huaqin.recyclerviewdemo.MainActivity;
import com.huaqin.recyclerviewdemo.R;
import com.huaqin.recyclerviewdemo.core.presenter.NewsPresenter;

public class WelcomeActivity extends BaseActivity {

    private static final String TAG = "WelcomeActivity";
    private NewsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                WelcomeActivity.this.startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                WelcomeActivity.this.finish();
            }
        }, 3 * 1000);
    }

}
