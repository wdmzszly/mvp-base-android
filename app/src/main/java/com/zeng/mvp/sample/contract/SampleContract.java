package com.zeng.mvp.sample.contract;

import com.zeng.mvp.sample.model.bean.TestBean;
import com.zeng.mvp.mvp.BasePresenter;
import com.zeng.mvp.mvp.BaseView;

public interface SampleContract {
    interface SampleView extends BaseView{
        void onRequestDataSuccess(TestBean data);
    }

    interface SamplePresenter extends BasePresenter<SampleView>{
        void requestData();
    }
}
