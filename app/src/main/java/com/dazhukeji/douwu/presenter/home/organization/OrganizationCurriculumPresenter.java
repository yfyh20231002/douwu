package com.dazhukeji.douwu.presenter.home.organization;

import com.dazhukeji.douwu.contract.home.organization.OrganizationCurriculumContract;
import com.zhangyunfei.mylibrary.base.BasePresenter;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/7 11:16
 * 功能描述：
 */
public class OrganizationCurriculumPresenter extends BasePresenter<OrganizationCurriculumContract.View> implements OrganizationCurriculumContract.Presenter {
    @Override
    public void postOrganizationCurriculum(String curriculum_id) {

    }

    //    @Override
//    public void postOrganizationList(String district_id, String dance_type_id, String organization_name) {
//        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
//        Map<String, String> map = new HashMap<>();
//        map.put("dance_type_id", dance_type_id);
//        map.put("district_id", district_id);
//        map.put("organization_name", organization_name);
//        Observable<OrganizationListBean> observable = apiService.postOrganizationList(map);
//        mView.showProgress();
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<OrganizationListBean>() {
//                    @Override
//                    public void accept(OrganizationListBean baseBean) throws Exception {
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
//    }

}
