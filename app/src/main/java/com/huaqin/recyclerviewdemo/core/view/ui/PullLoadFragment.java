package com.huaqin.recyclerviewdemo.core.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huaqin.recyclerviewdemo.R;
import com.huaqin.recyclerviewdemo.core.view.adapter.StaggeredAdapter;
import com.huaqin.recyclerviewdemo.util.Constants;
import com.huaqin.recyclerviewdemo.widget.VpRecyclerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ubuntu on 17-4-13.
 */

public class PullLoadFragment extends Fragment {

    private static PullLoadFragment fragment = null;
    Unbinder unbinder;

    @BindView(R.id.recyclerview)
    VpRecyclerView mRecyclerview;
    private StaggeredAdapter mAdapter;
    private Context mContext;

    private List<String> mList = new ArrayList<>();

    public static PullLoadFragment newInstance() {
        if (fragment == null) {
            fragment = new PullLoadFragment();
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
        View view = inflater.inflate(R.layout.fragment_pullload, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mList.clear();
        mList.addAll(Arrays.asList(Constants.mUrls));
        mAdapter = new StaggeredAdapter(mContext, mList);
        mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerview.refreshComplete();
                        mList.clear();
                        mList.addAll(Arrays.asList(Constants.mUrls));
                        mAdapter.notifyDataSetChanged();
                    }
                }, 2 * 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerview.loadMoreComplete();
                        mList.addAll(Arrays.asList(Constants.mUrls));
                        mAdapter.notifyDataSetChanged();
                    }
                }, 2 * 1000);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
