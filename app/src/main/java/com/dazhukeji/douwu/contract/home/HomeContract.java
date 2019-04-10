package com.dazhukeji.douwu.contract.home;

import com.dazhukeji.douwu.bean.home.HomeIndexBean;
import com.dazhukeji.douwu.bean.home.HomeIndexPagingBean;
import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.base.IView;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/4 10:19
 * 功能描述：
 */
public class HomeContract {
    public interface View extends IView{

        void refresh(HomeIndexBean homeIndexBean);


        void loadMore(HomeIndexPagingBean homeIndexPagingBean);
    }

    public  interface Presenter extends IPresenter<View> {

        void postHome(int dance_type_id, int district_id, int order,String seek);

        void postIndexPaging(int dance_type_id, int district_id, int order,int paging);

//        void postIndexSeek(String seek,int district_id);

    }
}
