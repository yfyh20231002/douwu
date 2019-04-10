package com.dazhukeji.douwu.ui.aty;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.contract.login.LoginContract;
import com.dazhukeji.douwu.presenter.login.LoginPresenter;
import com.dazhukeji.douwu.view.MyEditText;
import com.zhangyunfei.mylibrary.utils.ToastUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建者：zhangyunfei
 * 时间：2018/12/2 0002
 * 联系方式：32457127@qq.com
 */
public class LoginDialogAty extends BaseAty<LoginPresenter> implements LoginContract.View{
    @BindView(R.id.codeImg)
    ImageView codeImg;
    @BindView(R.id.codeEdit)
    MyEditText codeEdit;
    private String mPhone;
    private String mType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login_dialog;
    }

    @Override
    public void initView() {
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            mPhone = extras.getString("phone");
            mType = extras.getString("type");
        }
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.exitImg, R.id.commitTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.exitImg:
                finish();
                break;
            case R.id.commitTv:
                ((LoginPresenter)mPresenter).postSendMessage(mPhone,mType);
                break;
        }
    }

    @Override
    public void refresh(Map<String, String> result) {
    }

    @Override
    public void onComplete(String msg) {
        ToastUtils.showToast(msg);
        finish();
    }
}
