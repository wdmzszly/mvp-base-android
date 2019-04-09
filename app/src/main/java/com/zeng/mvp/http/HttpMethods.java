package com.zeng.mvp.http;

import com.zeng.mvp.constant.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Zeng on 2019/4/8.
 */
public class HttpMethods {

    private Retrofit retrofit;

    private HttpMethods() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //设置超时
        builder.connectTimeout(5, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Api.BASE_URL)
                .build();
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final Retrofit INSTANCE = new HttpMethods().getServer();
    }

    //获取单例
    public static Retrofit getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private Retrofit getServer() {
        return retrofit;
    }
}
