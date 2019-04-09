package com.zeng.mvp.http;


import io.reactivex.functions.Function;

/**
 * Created by Zeng on 2019/4/8.
 * User to handle http response data with result code and return only data to subscriber
 */

public class HttpResultFunc<T> implements Function<HttpResult<T>, T> {

    @Override
    public T apply(HttpResult<T> httpResult) {

        if (httpResult.getCode() != 0) {
            throw new ApiException(httpResult.getCode(), httpResult.getMsg());
        }
        return httpResult.getData();
    }
}