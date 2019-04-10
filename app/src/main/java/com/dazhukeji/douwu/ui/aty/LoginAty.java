package com.dazhukeji.douwu.ui.aty;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.contract.login.LoginContract;
import com.dazhukeji.douwu.presenter.login.LoginPresenter;
import com.dazhukeji.douwu.view.MyEditText;
import com.zhangyunfei.mylibrary.utils.JSONUtils;
import com.zhangyunfei.mylibrary.utils.ToastUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/27 14:08
 * 功能描述：
 */
public class LoginAty extends BaseAty<LoginPresenter> implements LoginContract.View{
    @BindView(R.id.phoneEdit)
    MyEditText phoneEdit;
    @BindView(R.id.codeEdit)
    MyEditText codeEdit;
    @BindView(R.id.codeLinearLayout)
    LinearLayout codeLinearLayout;
    @BindView(R.id.passwordTv)
    TextView passwordTv;
    @BindView(R.id.passwordEdit)
    MyEditText passwordEdit;
    @BindView(R.id.forgetTv)
    TextView forgetTv;
    @BindView(R.id.confirmTv)
    TextView confirmTv;
    @BindView(R.id.tipTv)
    TextView tipTv;
    private String tips = "您还没有乐舞账号？立即注册";
    private String mPhone;
    private String mCode;
    private String mPassword;
    private String mType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        setTipTv();
    }

    @Override
    public void initData() {
    }


    @OnClick({R.id.sendCodeTv,R.id.forgetTv, R.id.confirmTv, R.id.tipTv})
    public void onViewClicked(View view) {
        mPhone = phoneEdit.getContent();
        mPassword = passwordEdit.getContent();
        mCode = codeEdit.getContent();
        String confirmContent = confirmTv.getText().toString();
        switch (view.getId()) {
            case R.id.sendCodeTv:
                if (TextUtils.isEmpty(mPhone)){
                    ToastUtils.showToast("手机号不能为空");
                    return;
                }
                if (confirmContent.equals("立即注册")){
                   mType="1";
                }else {
                    mType="2";
                }
                Bundle bundle=new Bundle();
                bundle.putString("phone",mPhone);
                bundle.putString("type",mType);
                startActivity(LoginDialogAty.class,bundle);
                break;
            case R.id.forgetTv:
                phoneEdit.setText("");
                passwordEdit.setText("");
                tips = "已经有乐舞账号？立即登录";
                setTipTv();
                codeLinearLayout.setVisibility(View.VISIBLE);
                codeEdit.setText("");
                forgetTv.setVisibility(View.GONE);
                confirmTv.setText("重置密码");
                passwordTv.setText("新密码");
                passwordEdit.setHint("请输入新密码");
                break;
            case R.id.confirmTv:
                if (TextUtils.isEmpty(mPhone)){
                    ToastUtils.showToast("手机号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(mPassword)){
                    ToastUtils.showToast("密码不能为空");
                    return;
                }

                if (confirmContent.equals("立即注册")){
                    if (TextUtils.isEmpty(mCode)){
                        ToastUtils.showToast("验证码不能为空");
                        return;
                    }
                    ((LoginPresenter)mPresenter).postRegister(mPhone,mPassword,mCode);
                }else if (confirmContent.equals("立即登录")){
                    ((LoginPresenter)mPresenter).postLogin(mPhone,mPassword);
                }else {
                    if (TextUtils.isEmpty(mCode)){
                        ToastUtils.showToast("验证码不能为空");
                        return;
                    }
                    ((LoginPresenter)mPresenter).postForget(mPhone,mPassword,mCode);
                }
                break;
            case R.id.tipTv:
                phoneEdit.setText("");
                passwordEdit.setText("");
                if (tips.contains("立即注册")) {
                    tips = "已经有乐舞账号？立即登录";
                    setTipTv();
                    codeLinearLayout.setVisibility(View.VISIBLE);
                    codeEdit.setText("");
                    confirmTv.setText("立即注册");
                    passwordTv.setText("密码");
                    passwordEdit.setHint("请输入密码");
                    forgetTv.setVisibility(View.GONE);
                    return;
                }

                if (tips.contains("立即登录")) {
                    tips = "您还没有乐舞账号？立即注册";
                    setTipTv();
                    passwordTv.setText("密码");
                    passwordEdit.setHint("请输入密码");
                    forgetTv.setVisibility(View.VISIBLE);
                    codeLinearLayout.setVisibility(View.GONE);
                    confirmTv.setText("立即登录");
                    return;
                }
                break;

        }
    }

    private void setTipTv(){
        SpannableString spannableString = new SpannableString(tips);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#37C8F8")), tips.length() - 4, tips.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tipTv.setText(spannableString);
    }

    @Override
    public void refresh(Map<String, String> result) {
        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(result.get("data"));
        String user_token = data.get("user_token");
        Config.setToken(user_token);
        Log.e("token", user_token);
        JMessageClient.login(data.get("jmphone"), data.get("jmpassword"), new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage) {
//                if (responseCode == 0) {
//                    SharedPreferencesUtils.getInstance(mContext).setCachedPsw(password);
//                    UserInfo myInfo = JMessageClient.getMyInfo();
//                    File avatarFile = myInfo.getAvatarFile();
//                    //登陆成功,如果用户有头像就把头像存起来,没有就设置null
//                    if (avatarFile != null) {
//                        SharePreferenceManager.setCachedAvatarPath(avatarFile.getAbsolutePath());
//                    } else {
//                        SharePreferenceManager.setCachedAvatarPath(null);
//                    }
//                    String username = myInfo.getUserName();
//                    String appKey = myInfo.getAppKey();
//                }
            }
        });
        startActivity(MainActivity.class);
        finish();
    }

    @Override
    public void onComplete(String msg) {
        ToastUtils.showToast(msg);
        passwordEdit.setText("");
        tips = "您还没有乐舞账号？立即注册";
        setTipTv();
        passwordTv.setText("密码");
        passwordEdit.setHint("请输入密码");
        forgetTv.setVisibility(View.VISIBLE);
        codeLinearLayout.setVisibility(View.GONE);
        confirmTv.setText("立即登录");
    }
}
