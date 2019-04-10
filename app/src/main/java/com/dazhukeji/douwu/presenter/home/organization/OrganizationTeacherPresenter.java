package com.dazhukeji.douwu.presenter.home.organization;

import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.contract.home.organization.OrganizationTeacherContract;
import com.zhangyunfei.mylibrary.base.BasePresenter;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/11 14:23
 * 功能描述：
 */
public class OrganizationTeacherPresenter extends BasePresenter<OrganizationTeacherContract.View> implements OrganizationTeacherContract.Presenter {
    @Override
    public void postOrganizationTeacher(String organization_id) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("organization_id", organization_id);
//        Observable<OrganizationTeacherBean> observable = apiService.postOrganizationTeacher(map);
//        mView.showProgress();
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<OrganizationTeacherBean>() {
//                    @Override
//                    public void accept(OrganizationTeacherBean baseBean) throws Exception {
//                        if (baseBean.getCode() == 1){
//                            mView.refresh(baseBean);
//                        }else {
//                            mView.showError(baseBean.getMsg());
//                        }
//
//                        mView.hideProgress();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        mView.showError(throwable.getMessage());
//                        mView.hideProgress();
//                    }
//                });
    }
}
