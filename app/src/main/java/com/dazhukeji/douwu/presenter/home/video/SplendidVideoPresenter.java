package com.dazhukeji.douwu.presenter.home.video;

import android.annotation.SuppressLint;

import com.dazhukeji.douwu.MyLogger;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.bean.home.video.SplendidVideoBean;
import com.dazhukeji.douwu.contract.home.video.SplendidVideoContract;
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
 * 创建时间：2018/12/18 11:15
 * 功能描述：
 */
public class SplendidVideoPresenter extends BasePresenter<SplendidVideoContract.View> implements SplendidVideoContract.Presenter {
    @SuppressLint("CheckResult")
    @Override
    public void postSplendVideo(String district_id, String dance_type_id, String file_category, String seek, String order) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("dance_type_id", dance_type_id);
        map.put("district_id", district_id);
        map.put("file_category", file_category);
        map.put("seek", seek);
        map.put("order", order);
        map.put("paging", "1");
        Observable<SplendidVideoBean> homeBeanObservable = apiService.postSplendidVideo(map);
        mView.showProgress();
        homeBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(splendidVideoBean -> {
                    if (splendidVideoBean.getCode() == 1) {
                        mView.refreshSplendVideo(splendidVideoBean);
                    } else {
                        mView.showError(splendidVideoBean.getMsg());
                    }
                    mView.hideProgress();
                }, throwable -> {
                    mView.showError(throwable.getMessage());
                    mView.hideProgress();
                });
    }
}
