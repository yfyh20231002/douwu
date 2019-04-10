package com.zhangyunfei.mylibrary.base;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/13 11:50
 * 功能描述：
 */
public interface IView {
    void showProgress();

    void hideProgress();

    void showError(String msg);
}
