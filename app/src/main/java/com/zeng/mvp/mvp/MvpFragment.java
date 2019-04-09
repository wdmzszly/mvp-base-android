package com.zeng.mvp.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Zeng on 2019/4/8.
 */

public abstract class MvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {
    protected T mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        if (mPresenter != null)
            mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    public abstract T createPresenter();
}
