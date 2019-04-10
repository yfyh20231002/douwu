package com.dazhukeji.douwu.contract.mine.teacher;

import com.dazhukeji.douwu.bean.mine.member.UserCollectVideosBean;
import com.dazhukeji.douwu.bean.mine.teacher.TeacherCurriculumListBean;
import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.base.IView;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/21 10:46
 * 功能描述：
 */
public class MineTeacherCourseContract {
    public interface View extends IView{
        void refreshTeacherCurriculumList(TeacherCurriculumListBean teacherCurriculumListBean);
        void deleteSuccess();
    }

    public interface Presenter extends IPresenter<View>{
        void postTeacherCurriculumList(String user_token);
        void postCurriculumDelete(String user_token,String curriculum_id);
    }
}
