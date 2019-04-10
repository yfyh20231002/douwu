package com.dazhukeji.douwu.presenter.home.organization;

import com.dazhukeji.douwu.MyLogger;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.bean.home.organization.OrganizationListBean;
import com.dazhukeji.douwu.contract.home.organization.OrganizationContract;
import com.dazhukeji.douwu.contract.home.organization.OrganizationContract.Presenter;
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
 * 创建时间：2018/12/7 11:16
 * 功能描述：
 */
public class OrganizationPresenter extends BasePresenter<OrganizationContract.View> implements Presenter {

    @Override
    public void postOrganizationList(String district_id, String seek, int paging, String dance_type_name, String dance_type_id) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("district_id", district_id);
        map.put("seek", seek);
        map.put("paging", String.valueOf(paging));
        if (dance_type_name == null) {
            dance_type_name = "";
        }
        map.put("dance_type_name", dance_type_name);
        map.put("dance_type_id", dance_type_id);
        MyLogger.printMap(map);
        Observable<OrganizationListBean> observable = apiService.postOrganizationList(map);
        mView.showProgress();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OrganizationListBean>() {
                    @Override
                    public void accept(OrganizationListBean baseBean) throws Exception {
                        if (baseBean.getCode() == 1) {
                            mView.refresh(baseBean);
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
