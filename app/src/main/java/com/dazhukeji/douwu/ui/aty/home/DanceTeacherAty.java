package com.dazhukeji.douwu.ui.aty.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.MyLogger;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.manager.RecyclerViewManager;
import com.dazhukeji.douwu.view.RatingBar;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.JSONUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/15 14:29
 * 功能描述：老师列表
 */
public class DanceTeacherAty extends BaseAty{
    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.teacher_recyclerView)
    RecyclerView teacherRecyclerView;
    private RecyclerViewManager mRecyclerViewManager;
    private TeacherAdapter mTeacherAdapter;
    private String mOrganization_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_dance_teacher;
    }

    @Override
    public void initView() {
        mOrganization_id = getIntent().getStringExtra("organization_id");
        txtTitle.setText("老师列表");


        mRecyclerViewManager = new RecyclerViewManager(teacherRecyclerView);
        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.VERTICAL);


    }

    @Override
    public void initData() {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("organization_id", mOrganization_id);
        Observable<ResponseBody> observable = apiService.postOrganizationTeacher(requestMap);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        /**
                         * {
                         "code": 1,
                         "msg": "",
                         "data": "curriculum": [
                         {
                         "curriculum_id": 15,
                         "curriculum_admin": "鸿蒙",
                         "curriculum_name": "街舞",
                         "curriculum_actual_price": 100,
                         "curriculum_difficulty": "一般难度",
                         "curriculum_effective": 1,
                         "curriculum_start_time": 6554566,
                         "curriculum_over_time": 98765466
                         }
                         ]
                         }
                         */
                        Map<String, String> map = Config.getMap(responseBody);

                        MyLogger.printMap(map);
                        ArrayList<Map<String, String>> arrayList = JSONUtils.parseKeyAndValueToMapList(map.get("data"));
                        if (arrayList != null && arrayList.size()>0) {
                            mTeacherAdapter = new TeacherAdapter(R.layout.teacher_item, arrayList);
                            teacherRecyclerView.setAdapter(mTeacherAdapter);
                            mTeacherAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("organization_id", mOrganization_id);
                                    bundle.putString("organization_teacher_id", arrayList.get(position).get("organization_teacher_id"));
                                    startActivity(DanceTeacherDetailsAty.class, bundle);
                                }
                            });
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

    public static class TeacherAdapter extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

        public TeacherAdapter(int layoutResId, @Nullable List<Map<String, String>> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Map<String, String> item) {
            ImageView headImg = helper.getView(R.id.head_img);
            headImg.setVisibility(View.GONE);
            RatingBar ratingBar = helper.getView(R.id.ratingBar);
            ratingBar.setVisibility(View.GONE);
            TextView score_tv = helper.getView(R.id.score_tv);
            score_tv.setVisibility(View.GONE);
            helper.setText(R.id.name_tv, item.get("teacher_name"));
            helper.setText(R.id.classify_tv, item.get("teacher_master"));
            helper.setText(R.id.brief_tv, "简介：" + item.get("teacher_intro"));
        }
    }
}
