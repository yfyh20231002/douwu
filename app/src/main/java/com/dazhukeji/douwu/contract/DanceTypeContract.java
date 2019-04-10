package com.dazhukeji.douwu.contract;

import com.dazhukeji.douwu.bean.publicBean.DanceTypeBean;
import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.base.IView;

/**
 * 创建者：zhangyunfei
 * 时间：2019/1/20
 * 联系方式：32457127@qq.com
 * 功能描述：
 */
public class DanceTypeContract {
    public interface View extends IView {
        void danceTypeSuccess(DanceTypeBean danceTypeBean);
    }

    public interface Presenter extends IPresenter<View> {
        void postDanceTypeSelect();
    }
}
