package com.dazhukeji.douwu.contract.mine.member;

import com.dazhukeji.douwu.bean.publicBean.BaseBean;
import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.base.IView;

import java.io.File;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/19 11:03
 * 功能描述：
 */
public class MineTeacherStatusUpgradeContract {
    public interface View extends IView{
        void changeStateSuccess(BaseBean baseBean);
    }

    public interface Presenter extends IPresenter<View>{
        void postTeacherStatusUpgrade(String user_token, String enter_type, String district_id, File teacher_portrait, String teacher_name, String teacher_intro, String teacher_master);
    }
}
