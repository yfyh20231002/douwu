package com.zhangyunfei.mylibrary.base;

import android.content.Context;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/13 11:49
 * 功能描述：
 */
public interface IPresenter<T> {
    void attachView(T view, Context context);

    void detachView();
}
