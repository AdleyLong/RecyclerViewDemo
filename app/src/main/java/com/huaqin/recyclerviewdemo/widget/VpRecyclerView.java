package com.huaqin.recyclerviewdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by ubuntu on 17-4-13.
 */

public class VpRecyclerView extends XRecyclerView {
    public static final int NONE = 0;
    //状态刷新
    public static final int REFRESH = 1;
    //状态加载更多
    public static final int LOADMORE = 2;
    private int curState = NONE;

    public VpRecyclerView(Context context) {
        super(context);
        setRefreshLoadingMoreStyle();
        setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    public VpRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setRefreshLoadingMoreStyle();
        setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    public VpRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setRefreshLoadingMoreStyle();
        setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    /**
     * 统一设置下拉刷新及上拉加载更多样式
     */
    private void setRefreshLoadingMoreStyle() {
        setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
    }

    /**
     * 设置没有更多可以加载时不显示上拉加载
     */
    public void setLoadingMoreEnabled(boolean b) {
        super.setLoadingMoreEnabled(b);
    }

    /**
     * 刷新完成
     */
    public void refreshComplete() {
        super.refreshComplete();
    }

    /**
     * 加载更多完成
     */
    public void loadMoreComplete() {
        super.loadMoreComplete();
    }

    /**
     * 设置下拉刷新及上拉加载监听
     *
     * @param listener
     */
    public void setLoadingListener(final LoadingListener listener) {
        super.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                curState = REFRESH;
                listener.onRefresh();
            }

            @Override
            public void onLoadMore() {
                curState = LOADMORE;
                listener.onLoadMore();
            }
        });
    }

    /**
     * 设置当前页及总页数,在刷新完成及加载完成调用
     */
    public boolean setCurrentAndTotal(int cur, int total) {
        if (cur >= 0 && total >= 0) {
            if (cur == total) {
                setLoadingMoreEnabled(false);
                return true;
            } else if (cur < total) {
                setLoadingMoreEnabled(true);
            }
        }
        return false;
    }

    /**
     * loading接口
     */
    public interface LoadingListener {

        void onRefresh();

        void onLoadMore();
    }

    /**
     * @param type 刷新完成类型
     */
    public void setComplete(int type) {
        switch (type) {
            case REFRESH:
                super.refreshComplete();
                break;
            case LOADMORE:
                super.loadMoreComplete();
                break;
            default:
                super.refreshComplete();
                break;
        }
        curState = NONE;
    }

    public void setComplete() {
        setComplete(curState);
    }
}