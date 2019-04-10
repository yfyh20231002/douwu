package com.dazhukeji.douwu.ui.aty.home;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.DateUtils;
import com.zhangyunfei.mylibrary.utils.DisplayHelper;
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
 * 时间：2018/11/20 0020
 * 联系方式：32457127@qq.com
 */
public class TeacherCourseAty extends BaseAty {
    @BindView(R.id.txt_title)
    TextView txtTitle;

    @BindView(R.id.course_child_recyclerView)
    RecyclerView courseChildRecyclerView;

    private RecyclerViewManager mRecyclerViewManager;

    private ChildCourseAdapter mChildCourseAdapter;
    private String mUser_teacher_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_teacher_course;
    }

    @Override
    public void initView() {
        txtTitle.setText("教师课程");
        mUser_teacher_id = getIntent().getStringExtra("user_teacher_id");

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

    }

    @Override
    public void initData() {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("user_teacher_id", mUser_teacher_id);
        requestMap.put("user_token", ApiConfig.getToken());
        requestMap.put("current_date", DateUtils.stampToDate(System.currentTimeMillis(), "yyyy-MM-dd"));
        Observable<ResponseBody> observable = apiService.postCurriculumMore(requestMap);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        /**
                         * {
                         "code": 1,
                         "msg": "",
                         "data": [
                         {
                         "curriculum_id": 2,
                         "curriculum_admin": "明溪",
                         "curriculum_name": "孔雀舞",
                         "curriculum_actual_price": 100,
                         "curriculum_difficulty": "中等难度",
                         "curriculum_start_time": 6554566,
                         "curriculum_over_time": 98765466
                         }
                         ]
                         }
                         */
                        Map<String, String> map = Config.getMap(responseBody);
                        ArrayList<Map<String, String>> data = JSONUtils.parseKeyAndValueToMapList(map.get("data"));
                        if (data != null && data.size() > 0) {
                            mChildCourseAdapter = new ChildCourseAdapter(R.layout.child_course_item, data);
                            courseChildRecyclerView.setAdapter(mChildCourseAdapter);
                            mChildCourseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("user_teacher_id", mUser_teacher_id);
                                    bundle.putString("curriculum_id", data.get(position).get("curriculum_id"));
                                    startActivity(TeacherCourseDetailsAty.class, bundle);
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
            /**
             * {
             "curriculum_id": 2,
             "curriculum_admin": "明溪",
             "curriculum_name": "孔雀舞",
             "curriculum_actual_price": 100,
             "curriculum_difficulty": "中等难度",
             "curriculum_start_time": 6554566,
             "curriculum_over_time": 98765466
             }
             */
            ImageView headImg = helper.getView(R.id.head_img);
            headImg.setVisibility(View.GONE);
            helper.setText(R.id.courseNameTv, item.get("curriculum_name"));
            //            if (!TextUtils.isEmpty(item.get("curriculum_effective"))) {
            //                if (Double.parseDouble(item.get("curriculum_effective")) == 1) {
            //                    helper.setText(R.id.adminTv, item.get("curriculum_admin") + "\u3000长期有效");
            //                } else {
            //                    long curriculum_start_time = Long.parseLong(item.get("curriculum_start_time"));
            //                    long curriculum_over_time = Long.parseLong(item.get("curriculum_over_time"));
            //                    helper.setText(R.id.adminTv, item.get("curriculum_admin") + "\u3000" + DateUtils.stampToDate(curriculum_start_time, "HH:mm") + "\u0020-\u0020" + DateUtils.stampToDate(curriculum_over_time, "HH:mm"));
            //                }
            //            }
            long curriculum_start_time = Long.parseLong(item.get("curriculum_start_time"));
            long curriculum_over_time = Long.parseLong(item.get("curriculum_over_time"));
            helper.setText(R.id.adminTv, item.get("curriculum_admin") + "\u3000" + DateUtils.stampToDate(curriculum_start_time, "HH:mm") + "\u0020-\u0020" + DateUtils.stampToDate(curriculum_over_time, "HH:mm"));
            helper.setText(R.id.difficultyTv, item.get("curriculum_difficulty"));
            helper.setText(R.id.priceTv, item.get("curriculum_actual_price"));
        }
    }
}
