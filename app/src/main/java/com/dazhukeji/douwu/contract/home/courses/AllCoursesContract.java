package com.dazhukeji.douwu.contract.home.courses;

import com.dazhukeji.douwu.bean.home.courses.AllCoursesBean;
import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.base.IView;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/20 10:46
 * 功能描述：
 */
public class AllCoursesContract {
    public interface View extends IView{
        void refreshAllCourses(AllCoursesBean allCoursesBean);
    }

    public interface Presenter extends IPresenter<View>{
        void postAllCourses(String seek,String paging,String time,String dance_type_id);
    }
}
