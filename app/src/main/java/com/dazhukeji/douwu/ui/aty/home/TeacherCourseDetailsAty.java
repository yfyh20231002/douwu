package com.dazhukeji.douwu.ui.aty.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.base.BaseAty;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.DateUtils;
import com.zhangyunfei.mylibrary.utils.GlideApp;
import com.zhangyunfei.mylibrary.utils.JSONUtils;
import com.zhangyunfei.mylibrary.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 创建者：zhangyunfei
 * 时间：2018/11/19 0019
 * 联系方式：32457127@qq.com
 */
public class TeacherCourseDetailsAty extends BaseAty {
    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.txt_title)
    TextView txtTitle;

    @BindView(R.id.videoplayer)
    JzvdStd videoplayer;

    @BindView(R.id.phoneTv)
    TextView phoneTv;


    @BindView(R.id.priceTv)
    TextView priceTv;
    @BindView(R.id.coverImg)
    ImageView coverImg;
    @BindView(R.id.head_img)
    ImageView headImg;
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.nameTv)
    TextView nameTv;
    @BindView(R.id.difficultyTv)
    TextView difficultyTv;
    @BindView(R.id.numTv)
    TextView numTv;

    @BindView(R.id.collectTv)
    TextView collectTv;

    @BindView(R.id.cancelCollectTv)
    TextView cancelCollectTv;

    @BindView(R.id.courseDetailsTv)
    TextView courseDetailsTv;


    @BindView(R.id.letterImg)
    ImageView letterImg;


    @BindView(R.id.price_linearLayout)
    LinearLayout priceLinearLayout;


    private String mCurriculum_id;
    private String mUser_teacher_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_course_detail;
    }

    @Override
    public void initView() {
        txtTitle.setText("课程详情");
        mCurriculum_id = getIntent().getStringExtra("curriculum_id");
        mUser_teacher_id = getIntent().getStringExtra("user_teacher_id");

//        videoplayer.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"
//                , "", Jzvd.SCREEN_WINDOW_NORMAL);
//        GlideApp.with(this).load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png").into(videoplayer.thumbImageView);
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public void initData() {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("user_teacher_id", mUser_teacher_id);
        requestMap.put("curriculum_id", mCurriculum_id);
        requestMap.put("user_token", ApiConfig.getToken());
        requestMap.put("current_date", DateUtils.stampToDate(System.currentTimeMillis(),"yyyy-MM-dd"));
        Observable<ResponseBody> observable = apiService.postTeacherCurriculumDetails(requestMap);
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
                         "collect_state": 2,
                         "curriculum": {
                         "curriculum_id": 16,
                         "curriculum_admin": "云",
                         "curriculum_name": "街舞",
                         "curriculum_introduce_picture": "20181127/162f7150a9a56b364d67162d50a952f2.jpg",
                         "curriculum_details": "这是课程详情",
                         "curriculum_video": "课程展示视频",
                         "curriculum_actual_price": 100,
                         "curriculum_difficulty": "一般难度",
                         "curriculum_collect": 99,
                         "now_people_number": 10
                         }
                         }
                         }
                         */
                        Map<String, String> map = Config.getMap(responseBody);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        Map<String, String> curriculum = JSONUtils.parseKeyAndValueToMap(data.get("curriculum"));
                        GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + curriculum.get("curriculum_introduce_picture")).into(coverImg);
                        GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + curriculum.get("curriculum_photo")).circleCrop().into(headImg);
                        titleTv.setText(curriculum.get("curriculum_name"));

//                        if (Double.parseDouble(curriculum.get("curriculum_effective")) == 1) {
//                            nameTv.setText(curriculum.get("curriculum_admin") + "\u3000长期有效");
//                        } else {
//                            long curriculum_start_time = Long.parseLong(curriculum.get("curriculum_start_time"));
//                            long curriculum_over_time = Long.parseLong(curriculum.get("curriculum_over_time"));
//                            nameTv.setText(curriculum.get("curriculum_admin") + "\u3000" + DateUtils.stampToDate(curriculum_start_time, "HH:mm") + "\u0020-\u0020" + DateUtils.stampToDate(curriculum_over_time, "HH:mm"));
//                        }
                        if (curriculum.containsKey("curriculum_video") && curriculum.containsKey("curriculum_photo")){
                            videoplayer.setUp(ApiConfig.BASE_IMG_URL+curriculum.get("curriculum_video")
                                    , "", Jzvd.SCREEN_WINDOW_NORMAL);
                            GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL+curriculum.get("curriculum_photo")).into(videoplayer.thumbImageView);

                        }
                        difficultyTv.setText(curriculum.get("curriculum_difficulty"));
                        numTv.setText(curriculum.get("now_people_number") + "人购买");
                        courseDetailsTv.setText(curriculum.get("curriculum_details"));
//                        phoneTv.setText(curriculum.get("organization_service"));

                        priceTv.setText(curriculum.get("curriculum_actual_price"));

                        if (Double.parseDouble(data.get("collect_state")) == 1) {
                            cancelCollectTv.setVisibility(View.VISIBLE);
                        } else {
                            collectTv.setVisibility(View.VISIBLE);
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

    @OnClick({R.id.collectTv, R.id.cancelCollectTv, R.id.letterImg, R.id.price_linearLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.collectTv:
                cancelState(1);
                break;
            case R.id.cancelCollectTv:
                cancelState(2);
                break;
            case R.id.letterImg:
                break;
            case R.id.price_linearLayout:
//                startActivity(PayDialogAty.class);
                break;
        }
    }

    private void cancelState(int type){
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("curriculum_id", mCurriculum_id);
        requestMap.put("user_token", ApiConfig.getToken());
        requestMap.put("type", String.valueOf(type));
        Observable<ResponseBody> observable = apiService.postOrganizationCurriculumCollect(requestMap);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Map<String, String> map = Config.getMap(responseBody);
                        ToastUtils.showToast(map.get("msg"));
                        if (map.get("code").equals("1")){
                            initData();
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
