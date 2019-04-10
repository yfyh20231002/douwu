package com.dazhukeji.douwu.contract.home.organization;

import com.dazhukeji.douwu.bean.home.organization.OrganizationListBean;
import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.base.IView;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/7 11:15
 * 功能描述：舞蹈机构
 */
public class OrganizationCurriculumContract {
    public  interface View extends IView{
        void refresh(OrganizationListBean organizationListBean);
    }

    public interface Presenter extends IPresenter<View>{
        void postOrganizationCurriculum(String curriculum_id);
    }
}
