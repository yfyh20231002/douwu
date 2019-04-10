package com.dazhukeji.douwu.presenter.mine.teacher;

import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.bean.mine.teacher.TeacherCurriculumListBean;
import com.dazhukeji.douwu.bean.publicBean.BaseBean;
import com.dazhukeji.douwu.contract.mine.teacher.MineTeacherCourseContract;
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
 * 创建时间：2018/12/21 10:45
 * 功能描述：
 */
public class MineTeacherCoursePresenter extends BasePresenter<MineTeacherCourseContract.View> implements MineTeacherCourseContract.Presenter {
    @Override
    public void postTeacherCurriculumList(String user_token) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("user_token", user_token);
        Observable<TeacherCurriculumListBean> observable = apiService.postTeacherCurriculumList(map);
        mView.showProgress();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TeacherCurriculumListBean>() {
                    @Override
                    public void accept(TeacherCurriculumListBean teacherCurriculumListBean) throws Exception {
                        if (teacherCurriculumListBean.getCode() == 1){
                            mView.refreshTeacherCurriculumList(teacherCurriculumListBean);
                        }else {
                            mView.showError(teacherCurriculumListBean.getMsg());
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

    @Override
    public void postCurriculumDelete(String user_token, String curriculum_id) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("user_token", user_token);
        map.put("curriculum_id", curriculum_id);
        Observable<BaseBean> observable = apiService.postTeacherCurriculumDelete(map);
        mView.showProgress();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        if (baseBean.getCode() == 1){
                            mView.deleteSuccess();
                        }else {
                            mView.showError(baseBean.getMsg());
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
