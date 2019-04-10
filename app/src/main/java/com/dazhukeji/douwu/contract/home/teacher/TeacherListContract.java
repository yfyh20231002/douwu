package com.dazhukeji.douwu.contract.home.teacher;

import com.dazhukeji.douwu.bean.home.teacher.TeacherListBean;
import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.base.IView;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/12 14:03
 * 功能描述：
 */
public class TeacherListContract {
    public interface View extends IView {

        void refreshTeacherList(TeacherListBean teacherListBean);

    }

    public  interface Presenter extends IPresenter<View> {

        void postTeacherList(String district_id, String teacher_seek,int paging,String dance_type_name,String dance_type_id);

    }
}
