package com.zeng.mvp.sample.view;

import android.os.Bundle;
import android.widget.Toast;

import com.zeng.mvp.R;
import com.zeng.mvp.sample.model.bean.TestBean;
import com.zeng.mvp.mvp.MvpActivity;
import com.zeng.mvp.sample.contract.SampleContract;
import com.zeng.mvp.sample.presenter.SamplePresenter;

public class SampleActivity extends MvpActivity<SamplePresenter> implements SampleContract.SampleView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
    }

    @Override
    public void onInitViewAndData() {
        mPresenter.requestData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sample;
    }

    @Override
    public SamplePresenter createPresenter() {
        return new SamplePresenter();
    }

    @Override
    public void onRequestDataSuccess(TestBean data) {
        // TODO: 2019/4/9 展示数据
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
