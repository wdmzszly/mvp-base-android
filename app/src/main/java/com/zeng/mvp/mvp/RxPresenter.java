package com.zeng.mvp.mvp;

import com.zeng.mvp.sample.model.api.ApiServer;
import com.zeng.mvp.http.ApiCallback;
import com.zeng.mvp.http.ApiException;
import com.zeng.mvp.http.HttpMethods;
import com.zeng.mvp.http.HttpResult;
import com.zeng.mvp.http.HttpResultFunc;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Zeng on 2019/4/8.
 * A Presenter Base on RxJava
 */
public class RxPresenter<T extends BaseView> implements BasePresenter<T> {
    protected ApiServer apiServer = HttpMethods.getInstance().create(ApiServer.class);

    protected T mView;
    protected CompositeDisposable mCompositeDisposable;

    private void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    //    RxJava绑定
    private void addSubscription(Disposable d) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(d);
    }

    public <F> void request(Observable<HttpResult<F>> observable, final ApiCallback<F> apiCallback) {
        observable
                .map(new HttpResultFunc<F>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<F>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscription(d);
                    }

                    @Override
                    public void onNext(F o) {
                        apiCallback.onSuccess(o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        String msg;
                        if (e instanceof SocketTimeoutException) {
                            msg = "网络中断，请检查您的网络状态";
                            apiCallback.onFailure(msg);
                        } else if (e instanceof ConnectException) {
                            msg = "网络中断，请检查您的网络状态";
                            apiCallback.onFailure(msg);
                        } else if (e instanceof ApiException) {
                            apiCallback.onFailure(((ApiException) e).getCode(), e.getMessage());
                        } else {
                            apiCallback.onFailure(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
