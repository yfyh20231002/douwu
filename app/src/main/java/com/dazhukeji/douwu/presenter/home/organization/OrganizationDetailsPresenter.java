package com.dazhukeji.douwu.presenter.home.organization;

import android.annotation.SuppressLint;

import com.dazhukeji.douwu.MyLogger;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.bean.home.organization.OrganizationFindBean;
import com.dazhukeji.douwu.contract.home.organization.OrganizationDetailsContract;
import com.zhangyunfei.mylibrary.base.BasePresenter;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/7 11:16
 * 功能描述：
 */
public class OrganizationDetailsPresenter extends BasePresenter<OrganizationDetailsContract.View> implements OrganizationDetailsContract.Presenter {
    @SuppressLint("CheckResult")
    @Override
    public void postOrganizationFind(String organization_id) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("organization_id", organization_id);
        map.put("user_token", ApiConfig.getToken());
        Observable<ResponseBody> observable = apiService.postOrganizationFind(map);
        mView.showProgress();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        Map<String, String> data = Config.getMap(body);
                        mView.refresh(data);
                        mView.hideProgress();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        Writer result = new StringWriter();
                        PrintWriter printWriter = new PrintWriter(result);
                        throwable.printStackTrace(printWriter);
                        MyLogger.printStr(result.toString());
                        mView.showError(throwable.getMessage());
                        mView.hideProgress();
                    }
                });
    }
}
