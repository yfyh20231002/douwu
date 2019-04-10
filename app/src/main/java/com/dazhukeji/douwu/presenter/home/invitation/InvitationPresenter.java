package com.dazhukeji.douwu.presenter.home.invitation;

import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.bean.home.invitation.InvitationListBean;
import com.dazhukeji.douwu.contract.home.invitation.InvitationContract;
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
 * 时间：2018/12/17
 * 联系方式：32457127@qq.com
 * 功能描述：
 */
public class InvitationPresenter extends BasePresenter<InvitationContract.View> implements InvitationContract.Presenter {
    @Override
    public void postInvitationList(String district_id) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String,String> map=new HashMap<>();
        map.put("district_id",district_id);
        Observable<InvitationListBean> observable = apiService.postInvitationList(map);
        mView.showProgress();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<InvitationListBean>() {
                    @Override
                    public void accept(InvitationListBean invitationListBean) throws Exception {
                        if (invitationListBean.getCode()==1){
                            mView.refreshInvitation(invitationListBean);
                        }else {
                            mView.showError(invitationListBean.getMsg());
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
