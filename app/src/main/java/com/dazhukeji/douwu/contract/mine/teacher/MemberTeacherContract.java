package com.dazhukeji.douwu.contract.mine.teacher;

import com.dazhukeji.douwu.bean.mine.teacher.TeacherInfoBean;
import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.base.IView;

/**
 * 创建者：zhangyunfei
 * 时间：2018/12/25
 * 联系方式：32457127@qq.com
 * 功能描述：
 */
public class MemberTeacherContract {
    public interface View extends IView{
        void refreshTeacherInfo(TeacherInfoBean teacherInfoBean);
        void commitSuccess();
    }
    public interface Presenter extends IPresenter<View>{
        void  postTeacherInformation(String user_token);
        void  postUserTeacherAffirmEdit(String user_token, String teacher_portrait, String teacher_name, String teacher_intro, String teacher_master, String teacher_site,String teacher_phone,String schooltime,String teacher_video_cover,String teacher_video);
    }
}
