package com.dazhukeji.douwu.ui.aty.mine;

import android.widget.TextView;

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
 * 创建时间：2019/2/20 11:51
 * 功能描述：填写信息
 */
public class AddTeacherAty extends BaseAty {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.nameEdit)
    MyEditText nameEdit;
    @BindView(R.id.timeEdit)
    MyEditText timeEdit;
    @BindView(R.id.phoneEdit)
    MyEditText phoneEdit;
    @BindView(R.id.teacherBriefEdit)
    MyEditText teacherBriefEdit;
    @BindView(R.id.danceTypeEdit)
    MyEditText danceTypeEdit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_teacher;
    }

    @Override
    public void initView() {
        txtTitle.setText("填写信息");
    }

    @Override
    public void initData() {

    }


    @OnClick(R.id.confirmTv)
    public void onViewClicked() {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("user_token", ApiConfig.getToken());
        requestMap.put("teacher_name", nameEdit.getContent());
        requestMap.put("teacher_phone", phoneEdit.getContent());
        requestMap.put("teacher_master", danceTypeEdit.getContent());
        requestMap.put("teacher_intro", teacherBriefEdit.getContent());
        requestMap.put("teacher_office_time", timeEdit.getContent());
        Observable<ResponseBody> observable = apiService.postOrganizationTeacherAdd(requestMap);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        /**
                         * {
                         "code": 1,
                         "msg": "取消成功!",
                         "data": ""
                         }
                         */
                        Map<String, String> map = Config.getMap(responseBody);
                        ToastUtils.showToast(map.get("msg"));
                        if (map.get("code").equals("1")) {
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
