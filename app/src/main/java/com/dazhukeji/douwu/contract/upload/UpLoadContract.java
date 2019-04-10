package com.dazhukeji.douwu.contract.upload;

import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.base.IView;

import java.io.File;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/29 16:15
 * 功能描述：
 */
public class UpLoadContract {
    public interface View extends IView{
        void uploadSuccess(String channel,String path);
    }

    public interface Presenter extends IPresenter<View>{
//        void postPic(String channel,File image);
        void postVideo(String channel,File video);
        void postMusic(String channel,File music);
    }
}
