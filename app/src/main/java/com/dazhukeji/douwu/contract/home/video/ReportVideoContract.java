package com.dazhukeji.douwu.contract.home.video;

import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.base.IView;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/18 13:56
 * 功能描述：
 */
public class ReportVideoContract {
    public interface View extends IView {
    }
    public interface Presenter extends IPresenter<View> {
        void postReportVideo(String user_token,String file_id,String report_cause);
    }
}
