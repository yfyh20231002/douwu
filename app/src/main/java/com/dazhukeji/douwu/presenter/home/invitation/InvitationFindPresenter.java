package com.dazhukeji.douwu.presenter.home.invitation;

import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.bean.home.invitation.InvitationFindBean;
import com.dazhukeji.douwu.bean.home.invitation.InvitationListBean;
import com.dazhukeji.douwu.contract.home.invitation.InvitationFindContract;
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
public class InvitationFindPresenter extends BasePresenter<InvitationFindContract.View> implements InvitationFindContract.Presenter {
    @Override
    public void postInvitationFind(String invitation_id) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String,String> map=new HashMap<>();
        map.put("invitation_id",invitation_id);
        Observable<InvitationFindBean> observable = apiService.postInvitationFind(map);
        mView.showProgress();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<InvitationFindBean>() {
                    @Override
                    public void accept(InvitationFindBean invitationFindBean) throws Exception {
                        if (invitationFindBean.getCode()==1){
                            mView.refreshInvitationFind(invitationFindBean);
                        }else {
                            mView.showError(invitationFindBean.getMsg());
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
