package com.dazhukeji.douwu.presenter.follow;

import android.annotation.SuppressLint;

import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.bean.follow.UserAttentionOrganizationBean;
import com.dazhukeji.douwu.bean.follow.UserAttentionTeacherBean;
import com.dazhukeji.douwu.bean.publicBean.BaseBean;
import com.dazhukeji.douwu.contract.follow.FollowContract;
import com.zhangyunfei.mylibrary.base.BasePresenter;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/17 16:05
 * 功能描述：
 */
public class FollowPresenter extends BasePresenter<FollowContract.View> implements FollowContract.Presenter{
    @Override
    public void postUserAttentionTeacher(String user_token) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String,String> map=new HashMap<>();
        map.put("user_token",user_token);
        Observable<UserAttentionTeacherBean> homeBeanObservable = apiService.postUserAttentionTeacher(map);
        mView.showProgress();
        homeBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserAttentionTeacherBean>() {
                    @Override
                    public void accept(UserAttentionTeacherBean bean) throws Exception {
                        if (bean.getCode()==1){
                            mView.refreshTeacher(bean);
                        }else {
                            mView.showError(bean.getMsg());
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
    public void postCancelAttentionTeacher(String user_token, String user_teacher_id) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String,String> map=new HashMap<>();
        map.put("user_teacher_id",user_teacher_id);
        map.put("user_token",user_token);
        Observable<BaseBean> homeBeanObservable = apiService.postCancelAttentionTeacher(map);
        mView.showProgress();
        homeBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        if (baseBean.getCode()==1){
                            mView.cancelTeacher();
                        }
                        mView.showError(baseBean.getMsg());
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
    public void postUserAttentionOrganization(String user_token) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String,String> map=new HashMap<>();
        map.put("user_token",user_token);
        Observable<UserAttentionOrganizationBean> homeBeanObservable = apiService.postUserAttentionOrganization(map);
        mView.showProgress();
        homeBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserAttentionOrganizationBean>() {
                    @Override
                    public void accept(UserAttentionOrganizationBean bean) throws Exception {
                        if (bean.getCode()==1){
                            mView.refreshOrganization(bean);
                        }else {
                            mView.showError(bean.getMsg());
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

    @SuppressLint("CheckResult")
    @Override
    public void postCancelAttentionOrganization(String user_token, String organization_id) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String,String> map=new HashMap<>();
        map.put("user_token",user_token);
        map.put("organization_id",organization_id);
        Observable<ResponseBody> homeBeanObservable = apiService.postOrganizationCancelAttention(map);
        mView.showProgress();
        homeBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        Map<String, String> map = Config.getMap(responseBody);
                        if (map.get("code").equals("1")){
                            mView.cancelOrganization();
                        }
                        mView.showError(map.get("msg"));
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
