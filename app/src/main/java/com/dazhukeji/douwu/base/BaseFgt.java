package com.dazhukeji.douwu.base;

import android.content.Intent;
import android.os.Bundle;

import com.zhangyunfei.mylibrary.base.BaseFragment;
import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.utils.ToastUtils;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/13 11:00
 * 功能描述：
 */
public abstract class BaseFgt<T extends IPresenter> extends BaseFragment {


    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text,int gravity) {
        ToastUtils.showShort(text,gravity);
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId,int gravity) {
        ToastUtils.showShort(resId,gravity);
    }


    public void showToastWithImg(String text,int res) {
        ToastUtils.showToastWithImg(text,res);
    }

}
