package com.dazhukeji.douwu.contract.mine.member;

import com.dazhukeji.douwu.bean.mine.member.UserDatumUpdateBean;
import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.base.IView;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/17 9:40
 * 功能描述：
 */
public class MineUpdateContract {
    public interface  View extends IView {
        void refresh(UserDatumUpdateBean bean);
        void onSuccess();
    }

    public interface  Presenter extends IPresenter<View> {
        void postUserDatumUpdate(String user_token);
        void postAffirmUpdate(String user_token,String user_name, String user_portrait, String user_signature, String user_sex);
    }
}
