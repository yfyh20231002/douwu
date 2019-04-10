package com.dazhukeji.douwu.ui.aty.home;

import android.widget.TextView;
import android.widget.Toast;

import com.dazhukeji.douwu.MyLogger;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.base.BaseAty;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.JSONUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 创建者：zhangyunfei
 * 时间：2018/11/20 0020
 * 联系方式：32457127@qq.com
 */
public class DanceTeacherDetailsAty extends BaseAty {

    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.timeTv)
    TextView timeTv;
    @BindView(R.id.phoneTv)
    TextView phoneTv;
    @BindView(R.id.typeTv)
    TextView typeTv;
    @BindView(R.id.introduceTv)
    TextView introduceTv;
    private String mOrganization_id;
    private String mOrganization_teacher_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_dance_teacher_details;
    }

    @Override
    public void initView() {
        txtTitle.setText("老师详情");
        mOrganization_id = getIntent().getStringExtra("organization_id");
        mOrganization_teacher_id = getIntent().getStringExtra("organization_teacher_id");
    }

    @Override
    public void initData() {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        if (mOrganization_id == null){
            requestMap.put("user_token", ApiConfig.getToken());
            requestMap.put("organization_teacher_id", mOrganization_teacher_id);
            Observable<ResponseBody> observable = apiService.postOrganizationTeacherFind(requestMap);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<ResponseBody>() {
                        @Override
                        public void onNext(ResponseBody responseBody) {
                            /**
                             * {
                             "code": 1,
                             "msg": "",
                             "data": {
                             "teacher": {
                             "organization_teacher_id": 2,
                             "teacher_photo": "20181127/162f7150a9a56b364d67162d50a952f2.jpg",
                             "teacher_phone": "",
                             "teacher_name": "明溪",
                             "teacher_master": "街舞",
                             "teacher_intro": "认真负责负责",
                             "teacher_office_time": "0",
                             "teacher_show": 2
                             }
                             }
                             }
                             */
                            Map<String, String> map = Config.getMap(responseBody);
                            Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                            Map<String, String> teacher = JSONUtils.parseKeyAndValueToMap(data.get("teacher"));
                            titleTv.setText(teacher.get("teacher_name"));
                            timeTv.setText(teacher.get("teacher_office_time"));
                            phoneTv.setText(teacher.get("teacher_phone"));
                            typeTv.setText(teacher.get("teacher_master"));
                            introduceTv.setText(teacher.get("teacher_intro"));

                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }else {
            requestMap.put("organization_id", mOrganization_id);
            requestMap.put("organization_teacher_id", mOrganization_teacher_id);
            Observable<ResponseBody> observable = apiService.postOrganizationTeacherDetails(requestMap);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<ResponseBody>() {
                        @Override
                        public void onNext(ResponseBody responseBody) {
                            /**
                             * {
                             "code": 1,
                             "msg": "",
                             "data": {
                             "organization_teacher_id": 1,
                             "teacher_phone": "",
                             "teacher_name": "明溪",
                             "teacher_master": "街舞",
                             "teacher_intro": "认真负责负责",
                             "teacher_office_time": "0"
                             }
                             }
                             */
                            try {
                                Map<String, String> map = Config.getMap(responseBody);
                                Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                                titleTv.setText(data.get("teacher_name"));
                                timeTv.setText(data.get("teacher_office_time"));
                                phoneTv.setText(data.get("teacher_phone"));
                                typeTv.setText(data.get("teacher_master"));
                                introduceTv.setText(data.get("teacher_intro"));
                            } catch (Exception e) {
                                Toast.makeText(mContext, "数据异常", Toast.LENGTH_SHORT).show();
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
}
