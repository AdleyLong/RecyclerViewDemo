package com.huaqin.recyclerviewdemo.core.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.huaqin.recyclerviewdemo.core.view.ui.FragmentFactory;

import java.util.ArrayList;

/**
 * Created by ubuntu on 17-4-13.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String tabTitles[] = new String[]{"腾讯视频","垂直","水平", "网格", "瀑布流", "带刷新的" };

    public MainFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.getInstance().createFragment(position);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }


}
