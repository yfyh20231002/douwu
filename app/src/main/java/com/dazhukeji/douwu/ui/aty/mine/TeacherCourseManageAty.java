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
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.DateUtils;
import com.zhangyunfei.mylibrary.utils.JSONUtils;

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
        swithLine(0);
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
                swithLine(0);
                break;
            case R.id.layout2:
                swithLine(1);
                break;
        }
    }

    private void swithLine(int i){
        line1.setVisibility(View.INVISIBLE);
        line2.setVisibility(View.INVISIBLE);
        if (i==0){
            sell();
            line1.setVisibility(View.VISIBLE);
        }else if (i ==1){
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
                            mCourseAdapter = new CourseAdapter(R.layout.item_course_manage, arrayList);
                            courseRecyclerView.setAdapter(mCourseAdapter);
                            mCourseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("user_teacher_id", arrayList.get(position).get("user_teacher_id"));
                                    bundle.putString("curriculum_id", arrayList.get(position).get("curriculum_id"));
                                    startActivity(TeacherCourseDetailsAty.class, bundle);
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
                            mCourseAdapter = new CourseAdapter(R.layout.item_course_manage, arrayList);
                            courseRecyclerView.setAdapter(mCourseAdapter);
                            mCourseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("user_teacher_id", arrayList.get(position).get("user_teacher_id"));
                                    bundle.putString("curriculum_id", arrayList.get(position).get("curriculum_id"));
                                    startActivity(TeacherCourseDetailsAty.class, bundle);
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


    public class CourseAdapter extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {


        public CourseAdapter(int layoutResId, @Nullable List<Map<String, String>> data) {
            super(layoutResId, data);
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
                    long curriculum_start_time = Long.parseLong(item.get("curriculum_start_time"));
                    long curriculum_over_time = Long.parseLong(item.get("curriculum_over_time"));
                    helper.setText(R.id.adminTv, item.get("curriculum_admin") + "\u3000" + DateUtils.stampToDate(curriculum_start_time, "HH:mm") + "\u0020-\u0020" + DateUtils.stampToDate(curriculum_over_time, "HH:mm"));
                }
            }
            helper.setText(R.id.difficultyTv, item.get("curriculum_difficulty"));
            helper.setText(R.id.priceTv, item.get("curriculum_actual_price"));
        }

    }
}
