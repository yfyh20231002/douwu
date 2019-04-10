package com.dazhukeji.douwu.contract.mine.member;

import com.dazhukeji.douwu.bean.publicBean.BaseBean;
import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.base.IView;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/19 11:06
 * 功能描述：
 */
public class MineOrgStatusUpgradeContract {
    public interface View extends IView {
        void changeStateSuccess(BaseBean baseBean);
    }

    public interface Presenter extends IPresenter<View> {
        void postTeacherStatusUpgrade(String user_token, String enter_type, String district_id, String teacher_portrait, String teacher_name, String teacher_intro, String teacher_master, String teacher_site, String teacher_phone, String schooltime, String teacher_video_cover,String teacher_video);
        void postOrgStatusUpgrade(String user_token, String enter_type, String district_id, String organization_portrait, String organization_cover, String organization_name, String organization_site, String organization_facility, String organization_business_hours, String organization_synopsis, String organization_type,String organization_service,String promotional_video,String promotional_cover);
    }
}
