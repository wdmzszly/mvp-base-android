package com.zeng.mvp.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Zeng on 2019/4/8.
 */
public abstract class BaseFragment extends Fragment {

    protected Context mContext;

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getContext();
        onInitViewAndData(view);
    }

    public abstract void onInitViewAndData(View view);

    public abstract int getLayoutId();
}
