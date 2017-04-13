package com.huaqin.recyclerviewdemo.core.view.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.huaqin.recyclerviewdemo.BaseToolbarActivity;
import com.huaqin.recyclerviewdemo.R;
import com.huaqin.recyclerviewdemo.widget.ProgressWebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends BaseToolbarActivity {

    @BindView(R.id.activity_web_view)
    RelativeLayout activityWebView;
    private ProgressWebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        mWebView = new ProgressWebView(this);
        activityWebView.addView(mWebView);

        Intent intent = getIntent();
        String actionUrl = intent.getStringExtra("url");
        mWebView.loadUrl(actionUrl);

        initToolBar();
    }

    private void initToolBar() {
        setTitle("");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }
}
