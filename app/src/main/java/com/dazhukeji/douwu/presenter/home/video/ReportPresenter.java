package com.dazhukeji.douwu.presenter.home.video;

import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.contract.home.video.ReportVideoContract;
import com.zhangyunfei.mylibrary.base.BasePresenter;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/18 13:57
 * 功能描述：
 */
public class ReportPresenter extends BasePresenter<ReportVideoContract.View> implements ReportVideoContract.Presenter{
    @Override
    public void postReportVideo(String user_token, String file_id, String report_cause) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("file_id", file_id);
        map.put("user_token", user_token);
        map.put("report_cause", report_cause);
//        Observable<BaseBean> homeBeanObservable = apiService.postIndexVideoCollect(map);
//        mView.showProgress();
//        homeBeanObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<BaseBean>() {
//                    @Override
//                    public void accept(BaseBean baseBean) throws Exception {
//                        mView.showError(baseBean.getMsg());
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
