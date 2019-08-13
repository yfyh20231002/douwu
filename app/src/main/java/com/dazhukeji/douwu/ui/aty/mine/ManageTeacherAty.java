package com.dazhukeji.douwu.ui.aty.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.ui.aty.home.DanceTeacherDetailsAty;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
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
 * 创建时间：2018/11/22 14:56
 * 功能描述：老师管理
 */
public class ManageTeacherAty extends BaseAty {

    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.editTv)
    TextView editTv;
    @BindView(R.id.teacher_recyclerView)
    RecyclerView teacherRecyclerView;

    @BindView(R.id.addTeacherTv)
    TextView addTeacherTv;

    private TeacherAdapter mTeacherAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_manage_teacher;
    }

    @Override
    public void initView() {
        txtTitle.setText("老师管理");
        editTv.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        teacherRecyclerView.setLayoutManager(layoutManager);
        addTeacherTv.setVisibility(View.GONE);
        mTeacherAdapter = new TeacherAdapter(R.layout.teacher_item, null);
    }

    @Override
    public void initData() {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("user_token", ApiConfig.getToken());
        Observable<ResponseBody> observable = apiService.postOrganizationTeacherManage(map);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        /**
                         * {
                         "code": 2,
                         "msg": "",
                         "data": {
                         "organization_recommend_teacher": 0,
                         "teacher": [
                         {
                         "organization_teacher_id": 3,
                         "teacher_name": "明溪",
                         "teacher_master": "街舞",
                         "teacher_intro": "认真负责负责",
                         "teacher_show": 2
                         }
                         ]
                         }
                         }
                         */
                        Map<String, String> stringMap = Config.getMap(responseBody);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(stringMap.get("data"));

                        if (data.containsKey("teacher")) {
                            ArrayList<Map<String, String>> arrayList = JSONUtils.parseKeyAndValueToMapList(data.get("teacher"));
                            if (arrayList != null && arrayList.size() > 0) {
                                ArrayList<Map<String, String>> mapArrayList = new ArrayList<>();
                                if (arrayList.size() > 3) {
                                    mapArrayList.add(arrayList.get(0));
                                    mapArrayList.add(arrayList.get(1));
                                    mapArrayList.add(arrayList.get(2));
                                } else {
                                    mapArrayList.addAll(arrayList);
                                }

                                int organization_recommend_teacher = Integer.parseInt(data.get("organization_recommend_teacher"));
                                if (organization_recommend_teacher > 3) {
                                    mTeacherAdapter.setDataAndState(mapArrayList,true);
                                } else {
                                    mTeacherAdapter.setDataAndState(mapArrayList,false);
                                }
                                teacherRecyclerView.setAdapter(mTeacherAdapter);
                                mTeacherAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                    @Override
                                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                        if (view.getId() == R.id.deleteImg) {
                                            delete(arrayList.get(position).get("organization_teacher_id"));
                                        }

                                        if (view.getId() == R.id.showImg) {
                                            show(arrayList.get(position).get("organization_teacher_id"));
                                        }
                                        if (view.getId() == R.id.cancelShowImg) {
                                            cancelShow(arrayList.get(position).get("organization_teacher_id"));
                                        }
                                    }
                                });
                                mTeacherAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("organization_teacher_id", arrayList.get(position).get("organization_teacher_id"));
                                        startActivity(DanceTeacherDetailsAty.class, bundle);
                                    }
                                });
                            }
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

    private void delete(String organization_teacher_id) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("user_token", ApiConfig.getToken());
        requestMap.put("organization_teacher_id", organization_teacher_id);
        Observable<ResponseBody> observable = apiService.postOrganizationTeacherDelete(requestMap);
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
                            initData();
                        }

                        if (addTeacherTv.getVisibility() == View.VISIBLE) {
                            addTeacherTv.setVisibility(View.GONE);
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

    private void show(String organization_teacher_id) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("user_token", ApiConfig.getToken());
        requestMap.put("organization_teacher_id", organization_teacher_id);
        Observable<ResponseBody> observable = apiService.postOrganizationTeacherRecommend(requestMap);
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
                            initData();
                        }
                        if (addTeacherTv.getVisibility() == View.VISIBLE) {
                            addTeacherTv.setVisibility(View.GONE);
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

    private void cancelShow(String organization_teacher_id) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("user_token", ApiConfig.getToken());
        requestMap.put("organization_teacher_id", organization_teacher_id);
        Observable<ResponseBody> observable = apiService.postOrganizationTeacherCancelRecommend(requestMap);
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
                            initData();
                        }
                        if (addTeacherTv.getVisibility() == View.VISIBLE) {
                            addTeacherTv.setVisibility(View.GONE);
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

    @OnClick({R.id.editTv, R.id.addTeacherTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.editTv:
                if (null != mTeacherAdapter){
                    mTeacherAdapter.showEdit(true);
                    addTeacherTv.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.addTeacherTv:
                startActivity(AddTeacherAty.class);
                break;
        }
    }

    public static class TeacherAdapter extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

        private boolean isShow;
        private boolean isGone;

        public TeacherAdapter(int layoutResId, @Nullable List<Map<String, String>> data) {
            super(layoutResId, data);

        }

        public void setDataAndState(List<Map<String, String>> data,boolean state){
            this.isGone = state;
            notifyDataSetChanged();
        }

        @Override
        protected void convert(BaseViewHolder helper, Map<String, String> item) {
            RelativeLayout relativeLayout = helper.getView(R.id.editRelativeLayout);
            if (isShow) {
                relativeLayout.setVisibility(View.VISIBLE);
            } else {
                relativeLayout.setVisibility(View.GONE);
            }
            ImageView headImg = helper.getView(R.id.head_img);
            LinearLayout barLayout = helper.getView(R.id.barLayout);
            headImg.setVisibility(View.GONE);
            barLayout.setVisibility(View.GONE);
            helper.addOnClickListener(R.id.deleteImg)
                    .addOnClickListener(R.id.showImg)
                    .addOnClickListener(R.id.cancelShowImg);

            helper.setText(R.id.name_tv, item.get("teacher_name"));
            helper.setText(R.id.classify_tv, item.get("teacher_master"));
            helper.setText(R.id.brief_tv, "简介：" + item.get("teacher_intro"));
            int teacher_show = Integer.parseInt(item.get("teacher_show"));
            ImageView alreadyImg = helper.getView(R.id.alreadyImg);
            ImageView showImg = helper.getView(R.id.showImg);
            ImageView cancelShowImg = helper.getView(R.id.cancelShowImg);
            if (teacher_show == 1) {
                alreadyImg.setVisibility(View.VISIBLE);
                showImg.setVisibility(View.GONE);
                cancelShowImg.setVisibility(View.VISIBLE);
            } else {
                alreadyImg.setVisibility(View.GONE);
                if (!isGone) {
                    showImg.setVisibility(View.VISIBLE);
                } else {
                    showImg.setVisibility(View.GONE);
                }

                cancelShowImg.setVisibility(View.GONE);
            }


        }

        public void showEdit(boolean state) {
            this.isShow = state;
            notifyDataSetChanged();
        }
    }
}
