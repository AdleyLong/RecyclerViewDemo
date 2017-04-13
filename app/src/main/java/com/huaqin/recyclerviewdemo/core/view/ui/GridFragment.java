package com.huaqin.recyclerviewdemo.core.view.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huaqin.recyclerviewdemo.BaseFragment;
import com.huaqin.recyclerviewdemo.R;
import com.huaqin.recyclerviewdemo.core.bean.NewsItem;
import com.huaqin.recyclerviewdemo.core.contract.NewsContract;
import com.huaqin.recyclerviewdemo.core.presenter.NewsPresenter;
import com.huaqin.recyclerviewdemo.core.view.adapter.NewsAdapter;
import com.huaqin.recyclerviewdemo.core.view.adapter.NewsGridAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ubuntu on 17-4-13.
 */

public class GridFragment extends BaseFragment implements NewsContract.View {
    private static GridFragment fragment = null;
    Unbinder unbinder;
    @BindView(R.id.movie_recyclerview)
    RecyclerView mRecyclerview;
    private NewsGridAdapter mAdapter;
    private Context mContext;
    private final List<NewsItem> mList = new ArrayList<>();
    private NewsPresenter mPresenter;

    public static GridFragment newInstance() {
        if (fragment == null) {
            fragment = new GridFragment();
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
        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new NewsPresenter(this);
        mPresenter.getData(getActivity());
    }


    private void initView() {
        mAdapter = new NewsGridAdapter(mContext, mList);
        //设置grid是一行2个
        mRecyclerview.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new NewsGridAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String url = mList.get(position).getUrl();
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", url);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSuccess(List<NewsItem> list) {
        mList.clear();
        mList.addAll(list);
        initView();
    }

    @Override
    public void onFailure(String error) {
        showToast(error);
    }
}
