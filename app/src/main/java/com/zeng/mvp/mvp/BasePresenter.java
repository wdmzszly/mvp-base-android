package com.zeng.mvp.mvp;


/**
 * Created by Zeng on 2019/4/8.
 */
public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
