package com.dazhukeji.douwu.presenter.mine.member;

import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.bean.publicBean.BaseBean;
import com.dazhukeji.douwu.contract.mine.member.MineOrgStatusUpgradeContract;
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
 * 创建时间：2018/12/19 11:11
 * 功能描述：
 */
public class MineOrgStatusUpgradePresenter extends BasePresenter<MineOrgStatusUpgradeContract.View> implements MineOrgStatusUpgradeContract.Presenter{
    @Override
    public void postTeacherStatusUpgrade(String user_token, String enter_type, String district_id, String teacher_portrait, String teacher_name, String teacher_intro, String teacher_master, String teacher_site, String teacher_phone, String schooltime, String teacher_video_cover, String teacher_video) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
//        Map<String, RequestBody> bodyMap = UploadHelper.getInstance()
//                .addParameter("user_token", user_token)
//                .addParameter("enter_type", enter_type)
//                .addParameter("district_id", district_id)
//                .addParameter("teacher_portrait", teacher_portrait)
//                .addParameter("teacher_name", teacher_name)
//                .addParameter("teacher_intro", teacher_intro)
//                .addParameter("teacher_master", teacher_master)
//                .addParameter("teacher_site", teacher_site)
//                .addParameter("teacher_phone", teacher_phone)
//                .addParameter("schooltime", schooltime)
//                .addParameter("teacher_video_cover", teacher_video_cover)
//                .addParameter("teacher_video", teacher_video)
//                .builder();
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("user_token", user_token);
        requestMap.put("enter_type", enter_type);
        requestMap.put("district_id", district_id);
        requestMap.put("teacher_portrait", teacher_portrait);
        requestMap.put("teacher_name", teacher_name);
        requestMap.put("teacher_intro", teacher_intro);
        requestMap.put("teacher_master", teacher_master);
        requestMap.put("teacher_site", teacher_site);
        requestMap.put("teacher_phone", teacher_phone);
        requestMap.put("schooltime", schooltime);
        requestMap.put("teacher_video_cover", teacher_video_cover);
        requestMap.put("teacher_video", teacher_video);
        Observable<BaseBean> observable = apiService.postUserStatusUpgrade(requestMap);
        mView.showProgress();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        if (baseBean.getCode() == 1){
                            mView.changeStateSuccess(baseBean);
                        }else {
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

    @Override
    public void postOrgStatusUpgrade(String user_token, String enter_type, String district_id, String organization_portrait, String organization_cover, String organization_name, String organization_site, String organization_facility, String organization_business_hours, String organization_synopsis, String organization_type,String organization_service,String promotional_video,String promotional_cover) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
//        Map<String, RequestBody> bodyMap = UploadHelper.getInstance()
//                .addParameter("user_token", user_token)
//                .addParameter("enter_type", enter_type)
//                .addParameter("district_id", district_id)
//                .addParameter("organization_portrait", organization_portrait)
//                .addParameter("organization_cover", organization_cover)
//                .addParameter("organization_name", organization_name)
//                .addParameter("organization_site", organization_site)
//                .addParameter("organization_facility", organization_facility)
//                .addParameter("organization_business_hours", organization_business_hours)
//                .addParameter("organization_synopsis", organization_synopsis)
//                .addParameter("organization_type", organization_type)
//                .addParameter("organization_service", organization_service)
//                .addParameter("promotional_video", promotional_video)
//                .addParameter("promotional_cover", promotional_cover)
//                .builder();
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("user_token", user_token);
        requestMap.put("enter_type", enter_type);
        requestMap.put("district_id", district_id);
        requestMap.put("organization_portrait", organization_portrait);
        requestMap.put("organization_cover", organization_cover);
        requestMap.put("organization_name", organization_name);
        requestMap.put("organization_site", organization_site);
        requestMap.put("organization_facility", organization_facility);
        requestMap.put("organization_business_hours", organization_business_hours);
        requestMap.put("organization_synopsis", organization_synopsis);
        requestMap.put("organization_type", organization_type);
        requestMap.put("organization_service", organization_service);
        requestMap.put("promotional_video", promotional_video);
        requestMap.put("promotional_cover", promotional_cover);
        Observable<BaseBean> observable = apiService.postUserStatusUpgrade(requestMap);
        mView.showProgress();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        if (baseBean.getCode() == 1){
                            mView.changeStateSuccess(baseBean);
                        }else {
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
