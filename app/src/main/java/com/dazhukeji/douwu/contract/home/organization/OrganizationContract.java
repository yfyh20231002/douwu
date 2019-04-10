package com.dazhukeji.douwu.contract.home.organization;

import com.dazhukeji.douwu.bean.home.organization.OrganizationListBean;
import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.base.IView;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/7 11:15
 * 功能描述：舞蹈机构
 */
public class OrganizationContract {
    public  interface View extends IView{
        void refresh(OrganizationListBean organizationListBean);
    }

    public interface Presenter extends IPresenter<View>{
        void postOrganizationList(String district_id,String seek,int paging, String dance_type_name, String dance_type_id);
//        void postOrganizationFind(String organization_id);
//        void postOrganizationInvitation(String organization_id);
//        void postOrganizationTeacher(String organization_id);
//        void postOrganizationVideos(String organization_id);
//        void postOrganizationCurriculum(String curriculum_id);
    }
}
