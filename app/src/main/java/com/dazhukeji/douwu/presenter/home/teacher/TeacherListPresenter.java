package com.dazhukeji.douwu.presenter.home.teacher;

import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.bean.home.teacher.TeacherListBean;
import com.dazhukeji.douwu.contract.home.teacher.TeacherListContract;
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
 * 创建时间：2018/12/12 14:03
 * 功能描述：
 */
public class TeacherListPresenter extends BasePresenter<TeacherListContract.View> implements TeacherListContract.Presenter {
    @Override
    public void postTeacherList(String district_id, String teacher_seek,int paging,String dance_type_name,String dance_type_id) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String,String> map=new HashMap<>();
        map.put("district_id",district_id);
        map.put("teacher_seek",teacher_seek);
        map.put("paging", String.valueOf(paging));
        map.put("dance_type_name", dance_type_name);
        map.put("dance_type_id", dance_type_id);
        Observable<TeacherListBean> homeBeanObservable = apiService.postTeacherList(map);
        mView.showProgress();
        homeBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TeacherListBean>() {
                    @Override
                    public void accept(TeacherListBean teacherListBean) throws Exception {
                        if (teacherListBean.getCode()==1){
                            mView.refreshTeacherList(teacherListBean);
                        }else {
                            mView.showError(teacherListBean.getMsg());
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
