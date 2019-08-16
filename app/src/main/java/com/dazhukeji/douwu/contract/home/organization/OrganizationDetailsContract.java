package com.dazhukeji.douwu.contract.home.organization;

import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.base.IView;

import java.util.Map;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/7 11:15
 * 功能描述：舞蹈机构
 */
public class OrganizationDetailsContract {
    public  interface View extends IView{
        void refresh(Map<String, String> data);
    }

    public interface Presenter extends IPresenter<View>{
        void postOrganizationFind(String organization_id);
    }
}
