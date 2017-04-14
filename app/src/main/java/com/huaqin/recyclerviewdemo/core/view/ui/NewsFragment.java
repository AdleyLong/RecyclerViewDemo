package com.huaqin.recyclerviewdemo.core.view.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.huaqin.recyclerviewdemo.BaseFragment;
import com.huaqin.recyclerviewdemo.R;
import com.huaqin.recyclerviewdemo.core.bean.NewsItem;
import com.huaqin.recyclerviewdemo.core.contract.NewsContract;
import com.huaqin.recyclerviewdemo.core.presenter.NewsPresenter;
import com.huaqin.recyclerviewdemo.core.view.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ubuntu on 17-4-13.
 */

public class NewsFragment extends BaseFragment implements NewsContract.View {

    @BindView(R.id.news_recyclerview)
    RecyclerView mRecyclerview;

    private static NewsFragment fragment = null;
    Unbinder unbinder;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private Context mContext;
    private final List<NewsItem> mList = new ArrayList<>();
    private NewsPresenter mPresenter;
    private NewsAdapter mAdapter;

    public static NewsFragment newInstance() {
        if (fragment == null) {
            fragment = new NewsFragment();
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
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //先显示加载框
        mRecyclerview.setVisibility(View.GONE);
        progressbar.setVisibility(View.VISIBLE);
        mPresenter = new NewsPresenter(this);
        mPresenter.getData(getActivity());


    }

    private void initView() {
        mAdapter = new NewsAdapter(mContext, mList);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new NewsAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String url = mList.get(position).getUrl();
                if (TextUtils.isEmpty(url)) {
                    showToast("除了第一条都可以点击");
                } else {
                    Intent intent = new Intent(getActivity(), WebActivity.class);
                    intent.putExtra("url", url);
                    mContext.startActivity(intent);
                }
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
        mRecyclerview.setVisibility(View.VISIBLE);
        progressbar.setVisibility(View.GONE);
        initView();
    }

    @Override
    public void onFailure(String error) {
        progressbar.setVisibility(View.GONE);
        showToast(error);
    }
}
