package com.huaqin.recyclerviewdemo.core.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huaqin.recyclerviewdemo.R;

/**
 * Created by ubuntu on 17-4-13.
 */

public class TencentFragment extends Fragment {

    private static TencentFragment fragment = null;

    public static TencentFragment newInstance() {
        if (fragment == null) {
            fragment = new TencentFragment();
        }
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tencent, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
