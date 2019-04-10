package com.dazhukeji.douwu.contract.home.invitation;

import com.dazhukeji.douwu.bean.home.invitation.InvitationFindBean;
import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.base.IView;

/**
 * 创建者：zhangyunfei
 * 时间：2018/12/17
 * 联系方式：32457127@qq.com
 * 功能描述：
 */
public class InvitationFindContract {
    public interface View extends IView{
        void refreshInvitationFind(InvitationFindBean invitationFindBean);
    }

    public interface Presenter extends IPresenter<View>{
        void postInvitationFind(String invitation_id);
    }
}
