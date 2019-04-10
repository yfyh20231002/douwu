package com.dazhukeji.douwu.contract.mine.org;

import com.dazhukeji.douwu.bean.mine.org.OrgInvitationFindBean;
import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.base.IView;

/**
 * 创建者：zhangyunfei
 * 时间：2018/12/24
 * 联系方式：32457127@qq.com
 * 功能描述：
 */
public class OrgRecruitContract {
    public interface View extends IView{
        void refreshOrgInvitationFind(int code ,OrgInvitationFindBean orgInvitationFindBean);
        void changeSuccess();
        void toLogin();
    }

    public interface Presenter extends IPresenter<View>{
        void postOrganizationInvitationFind(String user_token);
        void postOrganizationInvitationEdit(String user_token,String invitation_id,String invitation_contact,String invitation_phone,String invitation_interview_site,String invitation_interview_time,String invitation_dance_type,String invitation_age_demand,String invitation_explain,String invitation_organization_picture,String invitation_organization_picture2);
        void postOrganizationInvitationAdd(String user_token,String invitation_id,String invitation_contact,String invitation_phone,String invitation_interview_site,String invitation_interview_time,String invitation_dance_type,String invitation_age_demand,String invitation_explain,String invitation_organization_picture,String invitation_organization_picture2);
    }
}
