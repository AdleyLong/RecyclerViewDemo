package com.huaqin.recyclerviewdemo.core.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huaqin.recyclerviewdemo.R;
import com.huaqin.recyclerviewdemo.core.view.adapter.StaggeredAdapter;
import com.huaqin.recyclerviewdemo.util.Constants;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ubuntu on 17-4-13.
 */

public class StaggeredDemoFragment extends Fragment {

    private static StaggeredDemoFragment fragment = null;
    @BindView(R.id.staggered_recyclerview)
    RecyclerView mRecyclerview;
    Unbinder unbinder;
    private Context mContext;
    private StaggeredAdapter mAdapter;


    public static StaggeredDemoFragment newInstance() {
        if (fragment == null) {
            fragment = new StaggeredDemoFragment();
        }
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_staggered, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    private void initView() {
        mAdapter = new StaggeredAdapter(mContext, Arrays.asList(Constants.mUrls));
        mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        mRecyclerview.setAdapter(mAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
