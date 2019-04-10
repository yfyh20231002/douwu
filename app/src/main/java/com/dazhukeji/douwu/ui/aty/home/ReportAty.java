package com.dazhukeji.douwu.ui.aty.home;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.view.MyEditText;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 创建者：zhangyunfei
 * 时间：2018/12/2 0002
 * 联系方式：32457127@qq.com
 * 功能描述：举报弹窗
 */
public class ReportAty extends BaseAty {
    @BindView(R.id.reasonEdit)
    MyEditText reasonEdit;
    private String mFile_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_report;
    }

    @Override
    public void initView() {
        mFile_id = getIntent().getStringExtra("file_id");
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.commitTv)
    public void onViewClicked() {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("file_id", mFile_id);
        requestMap.put("user_token", ApiConfig.getToken());
        requestMap.put("report_cause", reasonEdit.getContent());
        Observable<ResponseBody> observable = apiService.postReportVideo(requestMap);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Map<String, String> map = Config.getMap(responseBody);
                        ToastUtils.showToast(map.get("msg"));
                        if (map.get("code").equals("1")){
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
