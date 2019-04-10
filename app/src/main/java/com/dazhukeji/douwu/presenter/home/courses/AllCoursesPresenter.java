package com.dazhukeji.douwu.presenter.home.courses;

import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.bean.home.courses.AllCoursesBean;
import com.dazhukeji.douwu.contract.home.courses.AllCoursesContract;
import com.zhangyunfei.mylibrary.base.BasePresenter;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/20 10:48
 * 功能描述：
 */
public class AllCoursesPresenter extends BasePresenter<AllCoursesContract.View> implements AllCoursesContract.Presenter{
    @Override
    public void postAllCourses(String seek, String paging, String time,String dance_type_id) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String,String> map=new HashMap<>();
        map.put("seek",seek);
        map.put("paging",paging);
        map.put("time",time);
        map.put("dance_type_id",dance_type_id);
        Observable<AllCoursesBean> homeBeanObservable = apiService.postAllCourses(map);
        mView.showProgress();
        homeBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AllCoursesBean>() {
                    @Override
                    public void accept(AllCoursesBean allCoursesBean) throws Exception {
                        if (allCoursesBean.getCode()==1){
                            mView.refreshAllCourses(allCoursesBean);
                        }else {
                            mView.showError(allCoursesBean.getMsg());
                        }
                        mView.hideProgress();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable.getMessage());
                        mView.hideProgress();
                    }
                });
    }
}
