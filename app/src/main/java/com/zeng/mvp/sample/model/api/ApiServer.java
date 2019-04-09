package com.zeng.mvp.sample.model.api;

import com.zeng.mvp.sample.model.bean.TestBean;
import com.zeng.mvp.http.HttpResult;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Zeng on 2019/4/8.
 */

public interface ApiServer {

    @GET("xxxxxx")
    Observable<HttpResult<TestBean>> requestData();

}
