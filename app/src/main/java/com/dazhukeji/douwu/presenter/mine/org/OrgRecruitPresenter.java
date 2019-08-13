package com.dazhukeji.douwu.presenter.mine.org;

import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.bean.mine.org.OrgInvitationFindBean;
import com.dazhukeji.douwu.bean.publicBean.Response;
import com.dazhukeji.douwu.contract.mine.org.OrgRecruitContract;
import com.zhangyunfei.mylibrary.base.BasePresenter;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * 创建者：zhangyunfei
 * 时间：2018/12/24
 * 联系方式：32457127@qq.com
 * 功能描述：
 */
public class OrgRecruitPresenter extends BasePresenter<OrgRecruitContract.View> implements OrgRecruitContract.Presenter{
    @Override
    public void postOrganizationInvitationFind(String user_token) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("user_token", user_token);
        Observable<Response<OrgInvitationFindBean>> observable = apiService.postOrganizationInvitationFind(map);
        mView.showProgress();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Response<OrgInvitationFindBean>>() {
                    @Override
                    public void onNext(Response<OrgInvitationFindBean> orgInvitationFindBeanResponse) {
                        if (orgInvitationFindBeanResponse.getCode() == 1 || orgInvitationFindBeanResponse.getCode() == 2){
                            mView.refreshOrgInvitationFind(orgInvitationFindBeanResponse.getCode(),orgInvitationFindBeanResponse.getData());
                        }else if (orgInvitationFindBeanResponse.getCode() == -1){
                            mView.toLogin();
                        }else {
                            mView.showError(orgInvitationFindBeanResponse.getMsg());
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


    @Override
    public void postOrganizationInvitationEdit(String user_token, String invitation_id, String invitation_contact, String invitation_phone, String invitation_interview_site, String invitation_interview_time, String invitation_dance_type, String invitation_age_demand, String invitation_explain, String invitation_organization_picture, String invitation_organization_picture2) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("user_token", user_token);
        map.put("invitation_id", invitation_id);
        map.put("invitation_contact", invitation_contact);
        map.put("invitation_phone", invitation_phone);
        map.put("invitation_interview_site", invitation_interview_site);
        map.put("invitation_interview_time", invitation_interview_time);
        map.put("invitation_dance_type", invitation_dance_type);
        map.put("invitation_age_demand", invitation_age_demand);
        map.put("invitation_explain", invitation_explain);
        map.put("invitation_organization_picture", invitation_organization_picture);
        map.put("invitation_organization_picture2", invitation_organization_picture2);
        Observable<Response> observable = apiService.postOrganizationInvitationEdit(map);
        mView.showProgress();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Response>() {
                    @Override
                    public void onNext(Response response) {
                        if (response.getCode() == 1){
                            mView.changeSuccess();
                        }else {
                            mView.showError(response.getMsg());
                        }
                        mView.showError(response.getMsg());
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

    @Override
    public void postOrganizationInvitationAdd(String user_token, String invitation_id, String invitation_contact, String invitation_phone, String invitation_interview_site, String invitation_interview_time, String invitation_dance_type, String invitation_age_demand, String invitation_explain, String invitation_organization_picture, String invitation_organization_picture2) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("user_token", user_token);
        map.put("invitation_id", invitation_id);
        map.put("invitation_contact", invitation_contact);
        map.put("invitation_phone", invitation_phone);
        map.put("invitation_interview_site", invitation_interview_site);
        map.put("invitation_interview_time", invitation_interview_time);
        map.put("invitation_dance_type", invitation_dance_type);
        map.put("invitation_age_demand", invitation_age_demand);
        map.put("invitation_explain", invitation_explain);
        map.put("invitation_organization_picture", invitation_organization_picture);
        map.put("invitation_organization_picture2", invitation_organization_picture2);
        Observable<Response> observable = apiService.postOrganizationInvitationAdd(map);
        mView.showProgress();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Response>() {
                    @Override
                    public void onNext(Response response) {
                        if (response.getCode() == 1){
                            mView.changeSuccess();
                        }else {
                            mView.showError(response.getMsg());
                        }
                        mView.showError(response.getMsg());
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
