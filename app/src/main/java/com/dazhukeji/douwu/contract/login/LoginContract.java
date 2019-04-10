package com.dazhukeji.douwu.contract.login;

import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.base.IView;

import java.util.Map;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/6 14:53
 * 功能描述：
 */
public class LoginContract {
    public interface View extends IView{
        void refresh(Map<String, String> result);

        void onComplete(String msg);

    }

    public interface  Presenter extends IPresenter<View>{
        void postLogin(String phone,String password);

        void postRegister(String phone,String password,String code);

        void postSendMessage(String phone,String type);

        void postForget(String phone,String password,String code);
    }
}
