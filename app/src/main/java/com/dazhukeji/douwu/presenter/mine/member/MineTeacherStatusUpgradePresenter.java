package com.dazhukeji.douwu.presenter.mine.member;

import com.dazhukeji.douwu.contract.mine.member.MineTeacherStatusUpgradeContract;
import com.zhangyunfei.mylibrary.base.BasePresenter;

import java.io.File;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/19 11:10
 * 功能描述：
 */
public class MineTeacherStatusUpgradePresenter extends BasePresenter<MineTeacherStatusUpgradeContract.View> implements MineTeacherStatusUpgradeContract.Presenter{
    @Override
    public void postTeacherStatusUpgrade(String user_token, String enter_type, String district_id, File teacher_portrait, String teacher_name, String teacher_intro, String teacher_master) {
//        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
//        Map<String, RequestBody> bodyMap = UploadHelper.getInstance()
//                .addParameter("user_token", user_token)
//                .addParameter("enter_type", enter_type)
//                .addParameter("district_id", district_id)
//                .addParameter("teacher_portrait", teacher_portrait)
//                .addParameter("teacher_name", teacher_name)
//                .addParameter("teacher_intro", teacher_intro)
//                .addParameter("teacher_master", teacher_master)
//                .builder();
//        Observable<BaseBean> observable = apiService.postUserStatusUpgrade(bodyMap);
//        mView.showProgress();
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<BaseBean>() {
//                    @Override
//                    public void accept(BaseBean baseBean) throws Exception {
//                        if (baseBean.getCode() == 1){
//                            mView.changeStateSuccess(baseBean);
//                        }else {
//                            mView.showError(baseBean.getMsg());
//                        }
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
