package com.dazhukeji.douwu.contract.home.video;

import com.dazhukeji.douwu.bean.home.video.SplendidVideoBean;
import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.base.IView;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/18 11:13
 * 功能描述：
 */
public class SplendidVideoContract {
    public interface View extends IView{
        void refreshSplendVideo(SplendidVideoBean splendidVideoBean);
    }
    public interface Presenter extends IPresenter<View>{
        void postSplendVideo(String district_id,String dance_type_id,String file_category,String seek,String order);
    }
}
