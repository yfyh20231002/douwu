package com.dazhukeji.douwu.presenter.mine.member;

import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.bean.mine.member.UserCollectCurriculumBean;
import com.dazhukeji.douwu.bean.mine.member.UserCollectVideosBean;
import com.dazhukeji.douwu.contract.mine.member.MineCollectContract;
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
 * 创建时间：2018/12/17 14:48
 * 功能描述：
 */
public class MineCollectPresenter extends BasePresenter<MineCollectContract.View> implements MineCollectContract.Presenter {
    @Override
    public void postUserCollectVideos(String user_token) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("user_token", user_token);
        Observable<UserCollectVideosBean> homeBeanObservable = apiService.postUserCollectVideos(map);
        mView.showProgress();
        homeBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserCollectVideosBean>() {
                    @Override
                    public void accept(UserCollectVideosBean baseBean) throws Exception {
                        if (baseBean.getCode() == 1){
                            mView.refreshVideos(baseBean);
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

    @Override
    public void postUserCollectCurriculum(String user_token,int paging) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("user_token", user_token);
        map.put("paging", String.valueOf(paging));
        Observable<UserCollectCurriculumBean> homeBeanObservable = apiService.postUserCollectCurriculum(map);
        mView.showProgress();
        homeBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserCollectCurriculumBean>() {
                    @Override
                    public void accept(UserCollectCurriculumBean baseBean) throws Exception {
                        if (baseBean.getCode() == 1){
                            mView.refreshCurriculum(baseBean);
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
