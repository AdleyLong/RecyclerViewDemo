package com.huaqin.recyclerviewdemo.core.view.ui;

import android.support.v4.app.Fragment;

/**
 * Created by ubuntu on 17-4-13.
 */

public class FragmentFactory {
    private static FragmentFactory instance;

    public static synchronized FragmentFactory getInstance() {
        if (instance == null) {
            instance = new FragmentFactory();
        }

        return instance;
    }

    public Fragment createFragment(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = TencentFragment.newInstance();
                break;
            case 1:
                fragment = NewsFragment.newInstance();
                break;
            case 2:
                fragment = NewsHorFragment.newInstance();
                break;
            case 3:
                fragment = GridFragment.newInstance();
                break;
            case 4:
                fragment = StaggeredDemoFragment.newInstance();
                break;
            case 5:
                fragment = PullLoadFragment.newInstance();
                break;
        }
        return fragment;
    }

}
