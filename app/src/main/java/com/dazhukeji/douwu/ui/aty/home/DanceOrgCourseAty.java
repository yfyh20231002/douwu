package com.dazhukeji.douwu.ui.aty.home;

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
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.DateUtils;
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
 * 创建时间：2018/11/21 8:44
 * 功能描述：机构课程
 */
public class DanceOrgCourseAty extends BaseAty {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.course_recyclerView)
    RecyclerView courseRecyclerView;

    private RecyclerViewManager mRecyclerViewManager;
    private ChildCourseAdapter mCourseAdapter;
    private String mOrganization_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_danceorg_course;
    }

    @Override
    public void initView() {
        mOrganization_id = getIntent().getStringExtra("organization_id");
        txtTitle.setText("机构课程");
        mRecyclerViewManager = new RecyclerViewManager(courseRecyclerView);
        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.VERTICAL);

    }

    @Override
    public void initData() {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("organization_id", mOrganization_id);
        Observable<ResponseBody> observable = apiService.postOrganizationCurriculum(requestMap);
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
                         "curriculum": [
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
                         }
                         */
                        Map<String, String> map = Config.getMap(responseBody);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        ArrayList<Map<String, String>> arrayList = JSONUtils.parseKeyAndValueToMapList(data.get("curriculum"));
                        if (arrayList != null && arrayList.size()>0) {
                            mCourseAdapter = new ChildCourseAdapter(R.layout.child_course_item, arrayList);
                            courseRecyclerView.setAdapter(mCourseAdapter);
                            mCourseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("organization_id", mOrganization_id);
                                    bundle.putString("curriculum_id", arrayList.get(position).get("curriculum_id"));
                                    startActivity(DanceCourseDetailsAty.class, bundle);
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

    public static class ChildCourseAdapter extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {
        public ChildCourseAdapter(int layoutResId, @Nullable List<Map<String, String>> data) {
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
                    long curriculum_start_time = Long.parseLong(item.get("curriculum_start_time"))*1000;
                    long curriculum_over_time = Long.parseLong(item.get("curriculum_over_time"))*1000;
                    helper.setText(R.id.adminTv, item.get("curriculum_admin") + "\u3000" + DateUtils.stampToDate(curriculum_start_time, "yyyy年MM月dd日") + "\u0020-\u0020" + DateUtils.stampToDate(curriculum_over_time, "yyyy年MM月dd日"));
                }
            }
            helper.setText(R.id.difficultyTv, item.get("curriculum_difficulty"));
            helper.setText(R.id.priceTv, item.get("curriculum_actual_price"));
        }
    }
}
