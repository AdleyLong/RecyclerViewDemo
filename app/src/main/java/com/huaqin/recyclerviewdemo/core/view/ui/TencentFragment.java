package com.huaqin.recyclerviewdemo.core.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huaqin.recyclerviewdemo.R;
import com.huaqin.recyclerviewdemo.core.view.adapter.TecentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ubuntu on 17-4-13.
 */

public class TencentFragment extends Fragment {

    private static TencentFragment fragment = null;
    Unbinder unbinder;

    @BindView(R.id.tencent_recyclerview)
    RecyclerView mRecyclerview;
    private Context mContext;

    public static TencentFragment newInstance() {
        if (fragment == null) {
            fragment = new TencentFragment();
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
        View view = inflater.inflate(R.layout.fragment_tencent, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerview.setAdapter(new TecentAdapter(getActivity(), mContext));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
