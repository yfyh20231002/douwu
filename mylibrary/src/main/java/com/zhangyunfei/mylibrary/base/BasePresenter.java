package com.zhangyunfei.mylibrary.base;

import android.content.Context;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/13 11:51
 * 功能描述：
 */
public abstract class BasePresenter<T> implements IPresenter<T> {
    public Context mActivity;
    public T mView;

    @Override
    public void attachView(T view, Context context) {
        this.mView = view;
        this.mActivity = context;
        this.onStart();
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    public void onStart() {

    }

}
