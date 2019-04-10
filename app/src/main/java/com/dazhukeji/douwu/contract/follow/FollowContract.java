package com.dazhukeji.douwu.contract.follow;

import com.dazhukeji.douwu.bean.follow.UserAttentionOrganizationBean;
import com.dazhukeji.douwu.bean.follow.UserAttentionTeacherBean;
import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.base.IView;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/17 16:05
 * 功能描述：
 */
public class FollowContract {
    public interface View extends IView{
        void refreshTeacher(UserAttentionTeacherBean bean);
        void cancelTeacher();
        void refreshOrganization(UserAttentionOrganizationBean bean);
        void cancelOrganization();
    }

    public interface Presenter extends IPresenter<View>{
        void postUserAttentionTeacher(String user_token);
        void postCancelAttentionTeacher(String user_token,String user_teacher_id);
        void postUserAttentionOrganization(String user_token);
        void postCancelAttentionOrganization(String user_token,String organization_id);
    }
}
