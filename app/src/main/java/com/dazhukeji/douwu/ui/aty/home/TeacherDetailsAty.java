package com.dazhukeji.douwu.ui.aty.home;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.manager.RecyclerViewManager;
import com.dazhukeji.douwu.ui.aty.mine.MemberTeacherInfo;
import com.dazhukeji.douwu.view.RatingBar;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.DateUtils;
import com.zhangyunfei.mylibrary.utils.DisplayHelper;
import com.zhangyunfei.mylibrary.utils.GlideApp;
import com.zhangyunfei.mylibrary.utils.JSONUtils;
import com.zhangyunfei.mylibrary.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 * 时间：2018/11/20 0020
 * 联系方式：32457127@qq.com
 */
public class TeacherDetailsAty extends BaseAty {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.editTv)
    TextView editTv;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.score_tv)
    TextView scoreTv;
    @BindView(R.id.videoplayer)
    JzvdStd videoplayer;
    @BindView(R.id.course_child_recyclerView)
    RecyclerView courseChildRecyclerView;
    @BindView(R.id.video_recyclerView)
    RecyclerView videoRecyclerView;
    @BindView(R.id.headImg)
    ImageView headImg;
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.timeTv)
    TextView timeTv;
    @BindView(R.id.phoneTv)
    TextView phoneTv;
    @BindView(R.id.addressTv)
    TextView addressTv;
    @BindView(R.id.typeTv)
    TextView typeTv;
    @BindView(R.id.followStateTv)
    TextView followStateTv;
    @BindView(R.id.introduceTv)
    TextView introduceTv;

    private RecyclerViewManager mRecyclerViewManager;

    private CourseAdapter mCourseAdapter;
    private VideoAdpater mVideoAdpater;
    private String mUser_teacher_id;
    private int mAttentionState;
    private boolean mIsShow;

    @Override
    public int getLayoutId() {
        return R.layout.activity_teacher_details;
    }

    @Override
    public void initView() {
        mUser_teacher_id = getIntent().getStringExtra("user_teacher_id");
        mIsShow = getIntent().getBooleanExtra("isShow", false);
        txtTitle.setText("老师详情");
        if (mIsShow){
            editTv.setVisibility(View.VISIBLE);
            editTv.setText("编辑");
            editTv.setTextSize(15);
        }

        courseChildRecyclerView.setFocusable(false);
        videoRecyclerView.setFocusable(false);


        mRecyclerViewManager = new RecyclerViewManager(courseChildRecyclerView);
        courseChildRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.VERTICAL);
        courseChildRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) parent.getLayoutManager();
                if (parent.getChildAdapterPosition(view) == linearLayoutManager.getItemCount() - 1) {
                    outRect.bottom = DisplayHelper.dp2px(mContext, 15);
                }
            }
        });

        mRecyclerViewManager = new RecyclerViewManager(videoRecyclerView);
        videoRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerViewManager.setGridLayoutManager(2);
        videoRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildAdapterPosition(view) % 2 == 0) {
                    outRect.right = 10;
                }
            }
        });


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
        requestMap.put("user_token", ApiConfig.getToken());
        Observable<ResponseBody> observable = apiService.postTeacherDetails(requestMap);
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
                         "attention_state": 2,
                         "information": [
                         {
                         "user_teacher_id": 1,
                         "teacher_portrait": "2019/z.jpg",
                         "teacher_phone": "15999988888",
                         "teacher_name": "教师姓名",
                         "schooltime": "15:00~21:00",
                         "teacher_site": "地址",
                         "teacher_level": 5,
                         "teacher_master": "舞蹈类型",
                         "teacher_intro": "文档教师"
                         }
                         ],
                         "curriculum": [
                         {
                         "curriculum_id": 2,
                         "curriculum_admin": "明溪",
                         "curriculum_name": "孔雀舞",
                         "curriculum_actual_price": 100,
                         "curriculum_difficulty": "中等难度",
                         "curriculum_start_time": 6554566,
                         "curriculum_over_time": 98765466,
                         "curriculum_effective": 1
                         }
                         ],
                         "show_video": {
                         "teacher_video_cover": "展示封面",
                         "teacher_video": "展示视频地址"
                         },
                         "teacher_video": []
                         }
                         }
                         */
                        Map<String, String> map = Config.getMap(responseBody);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        if (!mIsShow){
                            //attention_state等于1表示关注2:表示未关注
                            mAttentionState = Integer.parseInt(data.get("attention_state"));
                            if (mAttentionState == 1) {
                                followStateTv.setText("取消关注");
                            } else if (mAttentionState == 2) {
                                followStateTv.setText("关注老师");
                            }
                        }

                        Map<String, String> information= JSONUtils.parseKeyAndValueToMap(data.get("information"));
                        if (information != null) {
                            GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + information.get("teacher_portrait")).circleCrop().into(headImg);
                            titleTv.setText(information.get("teacher_name"));
                            ratingBar.setStar(Float.parseFloat(information.get("teacher_level")));
                            scoreTv.setText(information.get("teacher_level"));
                            timeTv.setText("上课时间：" + information.get("schooltime"));
                            phoneTv.setText("联系电话：" + information.get("teacher_phone"));
                            addressTv.setText("联系地址：" + information.get("teacher_site"));
                            typeTv.setText(information.get("teacher_master"));
                            introduceTv.setText(information.get("teacher_intro"));
                        }

                        ArrayList<Map<String, String>> curriculumList = JSONUtils.parseKeyAndValueToMapList(data.get("curriculum"));
                        if (curriculumList != null) {
                            mCourseAdapter = new CourseAdapter(R.layout.child_course_item, curriculumList);
                            courseChildRecyclerView.setAdapter(mCourseAdapter);
                        }
                        Map<String, String> showVideo = JSONUtils.parseKeyAndValueToMap(data.get("show_video"));
                        //                        videoplayer.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"
                        //                                , "", Jzvd.SCREEN_WINDOW_NORMAL);
                        //                        GlideApp.with(mContext).load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png").into(videoplayer.thumbImageView);
                        videoplayer.setUp(ApiConfig.BASE_IMG_URL + showVideo.get("teacher_video"), "", Jzvd.SCREEN_WINDOW_NORMAL);
                        GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + showVideo.get("teacher_video_cover")).into(videoplayer.thumbImageView);


                        ArrayList<Map<String, String>> teacherVideoList = JSONUtils.parseKeyAndValueToMapList(data.get("teacher_video"));
                        if (teacherVideoList != null) {
                            mVideoAdpater = new VideoAdpater(R.layout.video_item, teacherVideoList,mContext);
                            videoRecyclerView.setAdapter(mVideoAdpater);
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

    @OnClick({R.id.editTv,R.id.followStateTv, R.id.courseTv, R.id.mechanismVideoTv})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("user_teacher_id", String.valueOf(mUser_teacher_id));
        switch (view.getId()) {
            case R.id.editTv:
                startActivity(MemberTeacherInfo.class);
                break;
            case R.id.followStateTv:
                if (mAttentionState == 1){
                    cancelAttention();
                }else if (mAttentionState ==2){
                    attention();
                }
                break;
            case R.id.courseTv:
                startActivity(TeacherCourseAty.class,bundle);
                break;
            case R.id.mechanismVideoTv:
                startActivity(TeacherVideoAty.class,bundle);
                break;
        }
    }

    private void attention(){
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("user_teacher_id", mUser_teacher_id);
        requestMap.put("user_token", ApiConfig.getToken());
        Observable<ResponseBody> observable = apiService.postAttentionTeacher(requestMap);
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

    private void cancelAttention(){
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("user_teacher_id", mUser_teacher_id);
        requestMap.put("user_token", ApiConfig.getToken());
        Observable<ResponseBody> observable = apiService.postCancelAttentionTeacherOperation(requestMap);
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

    public static class CourseAdapter extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {
        public CourseAdapter(int layoutResId, @Nullable List<Map<String, String>> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Map<String, String> item) {
            /**
             * {
             "curriculum_id": 2,
             "curriculum_admin": "明溪",
             "curriculum_name": "孔雀舞",
             "curriculum_actual_price": 100,
             "curriculum_difficulty": "中等难度",
             "curriculum_start_time": 6554566,
             "curriculum_over_time": 98765466,
             "curriculum_effective": 1
             }
             */
            ImageView headImg = helper.getView(R.id.head_img);
            headImg.setVisibility(View.GONE);
            helper.setText(R.id.courseNameTv, item.get("curriculum_name"));
            if (!TextUtils.isEmpty(item.get("curriculum_effective"))) {
                if (Double.parseDouble(item.get("curriculum_effective")) == 1) {
                    helper.setText(R.id.adminTv, item.get("curriculum_admin") + "\u3000长期有效");
                } else {
                    long curriculum_start_time = Long.parseLong(item.get("curriculum_start_time"))*1000;
                    long curriculum_over_time = Long.parseLong(item.get("curriculum_over_time"))*1000;
                    helper.setText(R.id.adminTv, item.get("curriculum_admin") + "\u3000" + DateUtils.stampToDate(curriculum_start_time, "yyyy年MM月dd日") + "\u0020-\u0020" + DateUtils.stampToDate(curriculum_over_time, "yyyy年MM月dd日"));
                }
            }
            helper.setText(R.id.difficultyTv, item.get("curriculum_difficulty"));
            helper.setText(R.id.priceTv, item.get("curriculum_actual_price"));

        }
    }


    public class VideoAdpater extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {
        private Context mContext;

        public VideoAdpater(int layoutResId, @Nullable List<Map<String, String>> data, Context context) {
            super(layoutResId, data);
            mContext = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, Map<String, String> item) {
            /**
             * {
             "file_id": 1,
             "file_cover": "20190000/a.jpg",
             "file_name": "个人视频1",
             "file_collection": 9999,
             "teacher_portrait": "20190000/z.jpg",
             "dance_type_name": "街舞",
             "teacher_name": "教师姓名"
             }
             */
            helper.setText(R.id.name_tv, item.get("dance_type_name"));
            helper.setText(R.id.num_tv, String.valueOf(item.get("file_collection")));
            String file_cover = item.get("file_cover");
            if (!TextUtils.isEmpty(file_cover)) {
                ImageView coverImg = helper.getView(R.id.coverImg);
                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + file_cover).diskCacheStrategy(DiskCacheStrategy.NONE).into(coverImg);
            }
            String user_portrait = item.get("teacher_portrait");
            if (!TextUtils.isEmpty(user_portrait)) {
                ImageView headImg = helper.getView(R.id.head_img);
                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + user_portrait).circleCrop().into(headImg);
            }

            helper.setText(R.id.contentTv, item.get("teacher_name"));
        }
    }
}
