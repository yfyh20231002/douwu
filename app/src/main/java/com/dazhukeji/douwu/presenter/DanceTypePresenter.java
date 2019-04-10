package com.dazhukeji.douwu.presenter;

import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.bean.publicBean.DanceTypeBean;
import com.dazhukeji.douwu.contract.DanceTypeContract;
import com.zhangyunfei.mylibrary.base.BasePresenter;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * 创建者：zhangyunfei
 * 时间：2019/1/20
 * 联系方式：32457127@qq.com
 * 功能描述：
 */
public class DanceTypePresenter extends BasePresenter<DanceTypeContract.View> implements DanceTypeContract.Presenter{
    @Override
    public void postDanceTypeSelect() {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Observable<DanceTypeBean> danceTypeBeanObservable = apiService.postDanceTypeSelect();
        mView.showProgress();
        danceTypeBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<DanceTypeBean>() {
                    @Override
                    public void onNext(DanceTypeBean response) {
                        if (response.getCode() == 1){
                            mView.danceTypeSuccess(response);
                        }else {
                            mView.showError(response.getMsg());
                        }
                        mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
