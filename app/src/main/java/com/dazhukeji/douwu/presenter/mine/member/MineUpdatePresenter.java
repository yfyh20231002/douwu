package com.dazhukeji.douwu.presenter.mine.member;

import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.bean.mine.AffirmUpdateBean;
import com.dazhukeji.douwu.bean.publicBean.BaseBean;
import com.dazhukeji.douwu.contract.mine.member.MineUpdateContract;
import com.dazhukeji.douwu.contract.mine.member.MineUpdateContract.Presenter;
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
        //        Map<String, RequestBody> builder = UploadHelper.getInstance().addParameter("user_token", user_token)
        //                .addParameter("user_name", user_name)
        //                .addParameter("user_portrait", user_portrait)
        //                .addParameter("user_signature", user_signature)
        //                .addParameter("user_sex", user_sex).builder();
        Map<String, String> parmasMap = new HashMap<>();
        parmasMap.put("user_token", user_token);
        parmasMap.put("user_name", user_name);
        parmasMap.put("user_portrait", user_portrait);
        parmasMap.put("user_signature", user_signature);
        parmasMap.put("user_sex", user_sex);
        Observable<AffirmUpdateBean> homeBeanObservable = apiService.postAffirmUpdate(parmasMap);
        mView.showProgress();
        homeBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AffirmUpdateBean>() {
                    @Override
                    public void accept(AffirmUpdateBean affirmUpdateBean) throws Exception {
                        if (affirmUpdateBean.getCode() == 1) {
                            mView.onSuccess();
                        }
                        mView.showError(affirmUpdateBean.getMsg());
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
