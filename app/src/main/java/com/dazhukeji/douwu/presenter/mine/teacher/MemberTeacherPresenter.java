package com.dazhukeji.douwu.presenter.mine.teacher;

import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.bean.mine.teacher.TeacherInfoBean;
import com.dazhukeji.douwu.bean.publicBean.BaseBean;
import com.dazhukeji.douwu.contract.mine.teacher.MemberTeacherContract;
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
 * 时间：2018/12/25
 * 联系方式：32457127@qq.com
 * 功能描述：
 */
public class MemberTeacherPresenter extends BasePresenter<MemberTeacherContract.View> implements MemberTeacherContract.Presenter {
    @Override
    public void postTeacherInformation(String user_token) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("user_token", user_token);
        Observable<TeacherInfoBean> observable = apiService.postTeacherInformation(map);
        mView.showProgress();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TeacherInfoBean>() {
                    @Override
                    public void accept(TeacherInfoBean teacherInfoBean) throws Exception {
                        if (teacherInfoBean.getCode() == 1) {
                            mView.refreshTeacherInfo(teacherInfoBean);
                        } else {
                            mView.showError(teacherInfoBean.getMsg());
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
    public void postUserTeacherAffirmEdit(String user_token, String teacher_portrait, String teacher_name, String teacher_intro, String teacher_master, String teacher_site, String teacher_phone, String schooltime, String teacher_video_cover, String teacher_video) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        //        Map<String, RequestBody> bodyMap = UploadHelper.getInstance()
        //                .addParameter("user_token", user_token)
        //                .addParameter("teacher_portrait", teacher_portrait)
        //                .addParameter("teacher_name", teacher_name)
        //                .addParameter("teacher_intro", teacher_intro)
        //                .addParameter("teacher_master", teacher_master)
        //                .addParameter("teacher_site", teacher_site)
        //                .addParameter("teacher_phone", teacher_phone)
        //                .addParameter("schooltime", schooltime)
        //                .addParameter("teacher_video_cover", teacher_video_cover)
        //                .addParameter("teacher_video", teacher_video)
        //                .builder();
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("user_token", user_token);
        requestMap.put("teacher_portrait", teacher_portrait);
        requestMap.put("teacher_name", teacher_name);
        requestMap.put("teacher_intro", teacher_intro);
        requestMap.put("teacher_master", teacher_master);
        requestMap.put("teacher_site", teacher_site);
        requestMap.put("teacher_phone", teacher_phone);
        requestMap.put("schooltime", schooltime);
        requestMap.put("teacher_video_cover", teacher_video_cover);
        requestMap.put("teacher_video", teacher_video);
        Observable<BaseBean> observable = apiService.postUserTeacherAffirmEdit(requestMap);
        mView.showProgress();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        if (baseBean.getCode() == 1) {
                            mView.commitSuccess();
                        } else {
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
