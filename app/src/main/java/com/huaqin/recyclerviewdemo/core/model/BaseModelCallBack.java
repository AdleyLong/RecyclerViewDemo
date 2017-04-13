package com.huaqin.recyclerviewdemo.core.model;

/**
 * Created by ubuntu on 17-4-13.
 */

public interface BaseModelCallBack<T> {
    void onResponse(T response);

    void onFailure(String error);
}
