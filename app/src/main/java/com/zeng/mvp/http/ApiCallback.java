package com.zeng.mvp.http;

/**
 * Created by Zeng on 2019/4/8.
 */
public interface ApiCallback<T> {

    void onSuccess(T model);

    void onFailure(String msg);

    void onFailure(int code, String msg);
}
