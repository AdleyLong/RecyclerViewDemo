package com.huaqin.recyclerviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.huaqin.recyclerviewdemo.core.view.adapter.MainFragmentPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    private MainFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    /**
     * 初始化对象
     */
    private void initView() {
        mAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), this);
        mViewpager.setAdapter(mAdapter);
        mViewpager.setOffscreenPageLimit(5);
        mTabLayout.setupWithViewPager(mViewpager);
        //        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

    }

}
