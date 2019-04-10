package com.dazhukeji.douwu.contract.mine.member;

import com.dazhukeji.douwu.bean.mine.member.UserCollectCurriculumBean;
import com.dazhukeji.douwu.bean.mine.member.UserCollectVideosBean;
import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.base.IView;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/17 14:47
 * 功能描述：
 */
public class MineCollectContract {
    public interface  View extends IView {
        void refreshCurriculum(UserCollectCurriculumBean bean);
        void refreshVideos(UserCollectVideosBean bean);
    }

    public interface  Presenter extends IPresenter<View> {
        void postUserCollectVideos(String user_token);
        void postUserCollectCurriculum(String user_token,int paging);
    }
}
