package com.zhangyunfei.mylibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangyunfei.mylibrary.utils.TUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/19 10:48
 * 功能描述：
 */
public abstract class BaseFragment<T extends IPresenter> extends Fragment implements IView{

    protected Context mContext;
    protected boolean isViewVisible;//fragment是否可见

    protected T mPresenter;

    private Unbinder unbinder;

    /**
     * 用于设置页面布局
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化数据
     */
    protected abstract void initialized(View view);

    protected abstract void requestData();



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(getLayoutResId(), container, false);
        unbinder=ButterKnife.bind(this,layout);
        mPresenter = TUtil.getT(this, 0);
        if(mPresenter != null) {
            //绑定Presenter
            mPresenter.attachView(this, mContext);
        }
        initialized(layout);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requestData();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            isViewVisible = true;
            requestData();
        }
    }

    public Context getContext(){
        if (mContext==null){
            mContext=BaseApplication.getAppContext();
        }
       return mContext;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        unbinder.unbind();
    }

    @Override
    public void showProgress() {
//        ((BaseActivity)getActivity()).showProgress();
    }

    @Override
    public void hideProgress() {
//        ((BaseActivity)getActivity()).hideProgress();
    }

    @Override
    public void showError(String msg) {
        ((BaseActivity)getActivity()).showError(msg);
    }

}
