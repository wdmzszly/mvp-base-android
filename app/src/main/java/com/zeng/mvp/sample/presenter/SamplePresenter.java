package com.zeng.mvp.sample.presenter;

import com.zeng.mvp.sample.model.bean.TestBean;
import com.zeng.mvp.http.ApiCallback;
import com.zeng.mvp.mvp.RxPresenter;
import com.zeng.mvp.sample.contract.SampleContract;

public class SamplePresenter extends RxPresenter<SampleContract.SampleView> implements SampleContract.SamplePresenter{
    @Override
    public void requestData() {
        request(apiServer.requestData(), new ApiCallback<TestBean>() {
            @Override
            public void onSuccess(TestBean model) {
                mView.onRequestDataSuccess(model);
            }

            @Override
            public void onFailure(String msg) {
                mView.showError(msg);
            }

            @Override
            public void onFailure(int code, String msg) {
                //使用错误码来转化错误信息

                mView.showError(msg);
            }

        });

    }
}
