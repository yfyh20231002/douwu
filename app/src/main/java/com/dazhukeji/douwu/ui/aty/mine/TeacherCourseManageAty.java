package com.dazhukeji.douwu.ui.aty.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.manager.RecyclerViewManager;
import com.dazhukeji.douwu.ui.aty.home.TeacherCourseDetailsAty;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.DateUtils;
import com.zhangyunfei.mylibrary.utils.JSONUtils;
import com.zhangyunfei.mylibrary.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 * 创建时间：2018/12/21 10:15
 * 功能描述：
 */
public class TeacherCourseManageAty extends BaseAty {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.editTv)
    TextView editTv;
    @BindView(R.id.course_recyclerView)
    RecyclerView courseRecyclerView;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.line2)
    View line2;

    private CourseAdapter mCourseAdapter;
    private RecyclerViewManager mRecyclerViewManager;
    private int pos= 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_teacher_course_manage;
    }

    @Override
    public void initView() {
        txtTitle.setText("课程管理");
        editTv.setVisibility(View.VISIBLE);
        mRecyclerViewManager = new RecyclerViewManager(courseRecyclerView);
        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.VERTICAL);

    }

    @Override
    public void initData() {
        swithLine();
    }

    @OnClick({R.id.editTv,R.id.layout1, R.id.layout2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.editTv:
                if ("编辑".equals(editTv.getText().toString())) {
                    editTv.setText("添加");
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("from","teacher");
                    startActivity(AddCourseAty.class,bundle);
                }
                break;
            case R.id.layout1:
                pos = 0;
                swithLine();
                break;
            case R.id.layout2:
                pos = 1;
                swithLine();
                break;
        }
    }

    private void swithLine(){
        line1.setVisibility(View.INVISIBLE);
        line2.setVisibility(View.INVISIBLE);
        if (pos==0){
            sell();
            line1.setVisibility(View.VISIBLE);
        }else if (pos ==1){
            stopSell();
            line2.setVisibility(View.VISIBLE);
        }
    }

    private void sell(){
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("user_token", ApiConfig.getToken());
        Observable<ResponseBody> observable = apiService.postTeacherCurriculumSellList(map);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Map<String, String> map = Config.getMap(responseBody);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        ArrayList<Map<String, String>> arrayList = JSONUtils.parseKeyAndValueToMapList(data.get("curriculum"));
                        if (arrayList!= null && arrayList.size()>0){
                            courseRecyclerView.setVisibility(View.VISIBLE);
                            mCourseAdapter = new CourseAdapter(R.layout.item_course_manage, arrayList,0);
                            courseRecyclerView.setAdapter(mCourseAdapter);
//                            mCourseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                                    Bundle bundle = new Bundle();
//                                    bundle.putString("user_teacher_id", arrayList.get(position).get("user_teacher_id"));
//                                    bundle.putString("curriculum_id", arrayList.get(position).get("curriculum_id"));
//                                    startActivity(TeacherCourseDetailsAty.class, bundle);
//                                }
//                            });
                            mCourseAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                                    if (view.getId() == R.id.contentLayout ){
//                                        Bundle bundle = new Bundle();
//                                        bundle.putString("user_teacher_id", arrayList.get(position).get("user_teacher_id"));
//                                        bundle.putString("curriculum_id", arrayList.get(position).get("curriculum_id"));
//                                        startActivity(TeacherCourseDetailsAty.class, bundle);
//                                    }
                                    if (view.getId() == R.id.stateTv ){
                                        caozuo("2",arrayList.get(position).get("curriculum_id"));
                                    }
                                }
                            });
                        }else {
                            courseRecyclerView.setVisibility(View.GONE);
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


    private void stopSell(){
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("user_token", ApiConfig.getToken());
        Observable<ResponseBody> observable = apiService.postTeacherCurriculumStopSellingList(map);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Map<String, String> map = Config.getMap(responseBody);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        ArrayList<Map<String, String>> arrayList = JSONUtils.parseKeyAndValueToMapList(data.get("curriculum"));
                        if (arrayList!= null && arrayList.size()>0){
                            courseRecyclerView.setVisibility(View.VISIBLE);
                            mCourseAdapter = new CourseAdapter(R.layout.item_course_manage, arrayList,1);
                            courseRecyclerView.setAdapter(mCourseAdapter);
//                            mCourseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                                    Bundle bundle = new Bundle();
//                                    bundle.putString("user_teacher_id", arrayList.get(position).get("user_teacher_id"));
//                                    bundle.putString("curriculum_id", arrayList.get(position).get("curriculum_id"));
//                                    startActivity(TeacherCourseDetailsAty.class, bundle);
//                                }
//                            });
                            mCourseAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                    if (view.getId() == R.id.contentLayout ){
                                        Bundle bundle = new Bundle();
                                        bundle.putString("user_teacher_id", arrayList.get(position).get("user_teacher_id"));
                                        bundle.putString("curriculum_id", arrayList.get(position).get("curriculum_id"));
                                        startActivity(TeacherCourseDetailsAty.class, bundle);
                                    }
                                    if (view.getId() == R.id.stateTv ){
                                        caozuo("1",arrayList.get(position).get("curriculum_id"));
                                    }
                                }
                            });
                        }else {
                            courseRecyclerView.setVisibility(View.GONE);
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

    /**
     *
     * @param type  1：上架2：下架
     * @param curriculum_id  课程id
     */
    private void caozuo(String type,String curriculum_id){
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("user_token", ApiConfig.getToken());
        map.put("curriculum_id", curriculum_id);
        map.put("identity_type","1");
        map.put("operation_type", type);
        Observable<ResponseBody> observable = apiService.postTeacherCurriculumStateUpdate(map);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        /**
                         * {
                         "code": 0,
                         "msg": "信息查询失败",
                         "data": ""
                         }
                         */
                        Map<String, String> stringMap = Config.getMap(responseBody);
                        ToastUtils.showToast(stringMap.get("msg"));
                        if (stringMap.get("code").equals("1")){
                            swithLine();
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


    public class CourseAdapter extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

        private int state;
        public CourseAdapter(int layoutResId, @Nullable List<Map<String, String>> data,int state) {
            super(layoutResId, data);
            this.state = state;
        }

        @Override
        protected void convert(BaseViewHolder helper, Map<String, String> item) {
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

            SwipeMenuLayout layout = helper.getView(R.id.rootLayout);
            layout.setIos(true).setLeftSwipe(true).setSwipeEnable(true);
            if (state == 0){
                helper.setText(R.id.tipTv,"出售中");
                helper.setText(R.id.stateTv, "下架");
            }else {
                helper.setText(R.id.tipTv,"已下架");
                helper.setText(R.id.stateTv, "上架");
            }
            helper.addOnClickListener(R.id.contentLayout);
            helper.addOnClickListener(R.id.stateTv);
        }

    }
}
