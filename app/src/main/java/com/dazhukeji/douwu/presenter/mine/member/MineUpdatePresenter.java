package com.dazhukeji.douwu.presenter.mine.member;

import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.bean.publicBean.BaseBean;
import com.dazhukeji.douwu.contract.mine.member.MineUpdateContract;
import com.dazhukeji.douwu.contract.mine.member.MineUpdateContract.Presenter;
import com.zhangyunfei.mylibrary.base.BasePresenter;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.UploadHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/17 9:45
 * 功能描述：
 */
public class MineUpdatePresenter extends BasePresenter<MineUpdateContract.View> implements Presenter {
    @Override
    public void postUserDatumUpdate(String user_token) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
                map.put("user_token", user_token);
        Observable<BaseBean> homeBeanObservable = apiService.postUserDatumUpdate(map);
        mView.showProgress();
        homeBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        //                        mView.showError(baseBean.getMsg());
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
    public void postAffirmUpdate(String user_token,String user_name, String user_portrait, String user_signature, String user_sex) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, RequestBody> builder = UploadHelper.getInstance().addParameter("user_token", user_token)
                .addParameter("user_name", user_name)
                .addParameter("user_portrait", user_portrait)
                .addParameter("user_signature", user_signature)
                .addParameter("user_sex", user_sex).builder();
        Observable<BaseBean> homeBeanObservable = apiService.postAffirmUpdate(builder);
        mView.showProgress();
        homeBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        if (baseBean.getCode() == 1) {
                            mView.onSuccess();
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
}
