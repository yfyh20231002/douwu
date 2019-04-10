package com.dazhukeji.douwu.ui.aty.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.MyLogger;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.manager.RecyclerViewManager;
import com.dazhukeji.douwu.view.LineItemDecoration;
import com.dazhukeji.douwu.view.MyEditText;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.DateUtils;
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
 * 时间：2018/11/19 0019
 * 联系方式：32457127@qq.com
 */
public class VideoDetailsAty extends BaseAty {
    @BindView(R.id.rootLayout)
    LinearLayout rootLayout;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.videoplayer)
    JzvdStd videoplayer;
    @BindView(R.id.typeTv)
    TextView typeTv;
    @BindView(R.id.nameTv)
    TextView nameTv;
    @BindView(R.id.reportTv)
    TextView reportTv;
    @BindView(R.id.collectTv)
    TextView collectTv;
    @BindView(R.id.cancelCollectTv)
    TextView cancelCollectTv;
    @BindView(R.id.chat_recyclerView)
    RecyclerView chatRecyclerView;
    @BindView(R.id.contentEdit)
    MyEditText contentEdit;

    private RecyclerViewManager mRecyclerViewManager;
    private String mFile_id;
    private String mFileType;

    private ArrayList<Map<String, String>> mCommentsList = new ArrayList<>();

    private int p = 1;
    private VideoDetailsAdapter mVideoDetailsAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_video_details;
    }

    @Override
    public void initView() {
        mFile_id = getIntent().getStringExtra("file_id");
        mFileType = getIntent().getStringExtra("fileType");
        boolean isGoneReport = getIntent().getBooleanExtra("isGoneReport", false);
        if (isGoneReport) {
            reportTv.setVisibility(View.GONE);
        }
        txtTitle.setText("视频播放");
        //        videoplayer.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"
        //                , "", Jzvd.SCREEN_WINDOW_NORMAL);
        //        GlideApp.with(this).load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png").into(videoplayer.thumbImageView);


        chatRecyclerView.setFocusable(false);
        mRecyclerViewManager = new RecyclerViewManager(chatRecyclerView);
        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.VERTICAL);
        chatRecyclerView.setNestedScrollingEnabled(false);

        LineItemDecoration decor = new LineItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.shape_line));
        chatRecyclerView.addItemDecoration(decor);
        chatRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (mVideoDetailsAdapter != null && newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mVideoDetailsAdapter.getItemCount()) {
                    p++;
                    getMoreData();
                }
            }
        });

//        rootLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                Rect rect = new Rect();
//                rootLayout.getWindowVisibleDisplayFrame(rect);
//                int mainInvisibleHeight = rootLayout.getRootView().getHeight() - rect.bottom;
//                int height = rootLayout.getRootView().getHeight();
//                if (mainInvisibleHeight > height / 4) {
//                    int[] location = new int[2];
//                    contentEdit.getLocationInWindow(location);
//                    int scrollHeight = location[1] - rect.bottom;
//                    rootLayout.scrollBy(0, scrollHeight+20);
//                } else {
//                    rootLayout.scrollTo(0, 0);
//                }
//                rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this::onGlobalLayout);
//
//            }
//        });
    }

    private void getMoreData() {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("file_id", mFile_id);
        requestMap.put("paging", String.valueOf(p));
        Observable<ResponseBody> observable = apiService.postFilePlayComments(requestMap);
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
                         "comments": [
                         {
                         "comments_id": 2,
                         "comments_content": "评论",
                         "comments_creattime": 1543223669,
                         "user_portrait": "20190000/z.jpg",
                         "user_name": "定稿测试"
                         }
                         ]
                         }
                         }
                         */
                        Map<String, String> map = Config.getMap(responseBody);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        ArrayList<Map<String, String>> comments = JSONUtils.parseKeyAndValueToMapList(data.get("comments"));
                        if (comments != null) {
                            if (p == 1) {
                                mCommentsList.clear();
                            }
                            mCommentsList.addAll(comments);
                            mVideoDetailsAdapter = new VideoDetailsAdapter(R.layout.chat_item, mCommentsList, mContext);
                            chatRecyclerView.setAdapter(mVideoDetailsAdapter);
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

    @Override
    public void initData() {
        getMoreData();//增加首次进入获取评论信息
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("file_id", mFile_id);
        requestMap.put("user_token", ApiConfig.getToken());
        requestMap.put("file_type", mFileType);
        Observable<ResponseBody> observable = apiService.postFilePlay(requestMap);
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
                         "video": {
                         "file_cover": "20190000/a.jpg",
                         "file_content": "视频路径",
                         "file_name": "个人视频1",
                         "dance_type_name": "街舞"
                         },
                         "comments": [
                         {
                         "comments_id": 2,
                         "comments_content": "评论",
                         "comments_creattime": 1543223669,
                         "user_portrait": "20190000/z.jpg",
                         "user_name": "定稿测试"
                         }
                         ],
                         "collect": 2,
                         "my_file": 2
                         }
                         }
                         */
                        try {
                            Map<String, String> map = Config.getMap(responseBody);
                            Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                            MyLogger.printMap(data);
                            Map<String, String> video = JSONUtils.parseKeyAndValueToMap(data.get("video"));
                            videoplayer.setUp(ApiConfig.BASE_IMG_URL + video.get("file_content"), "", Jzvd.SCREEN_WINDOW_NORMAL);
                            GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + video.get("file_cover")).into(videoplayer.thumbImageView);
                            typeTv.setText("[" + video.get("dance_type_name") + "]");
                            nameTv.setText(video.get("file_name"));
                            ArrayList<Map<String, String>> comments = JSONUtils.parseKeyAndValueToMapList(data.get("comments"));
                            if (comments != null) {
                                if (p == 1) {
                                    mCommentsList.clear();
                                }
                                mCommentsList.addAll(comments);
                                chatRecyclerView.setAdapter(new VideoDetailsAdapter(R.layout.chat_item, mCommentsList, mContext));
                            }


                            if (video.get("my_file").equals("2")) {
                                if (video.get("collect").equals("1")) {
                                    collectTv.setVisibility(View.INVISIBLE);
                                    cancelCollectTv.setVisibility(View.VISIBLE);
                                } else if (video.get("collect").equals("2")) {
                                    collectTv.setVisibility(View.VISIBLE);
                                    cancelCollectTv.setVisibility(View.INVISIBLE);
                                }
                            } else {
                                collectTv.setVisibility(View.INVISIBLE);
                                cancelCollectTv.setVisibility(View.INVISIBLE);
                            }
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

    @OnClick({R.id.collectTv, R.id.cancelCollectTv, R.id.reportTv, R.id.sendImg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.collectTv:
                collectState(1);
                break;
            case R.id.cancelCollectTv:
                collectState(2);
                break;
            case R.id.reportTv:
                Bundle bundle = new Bundle();
                bundle.putString("file_id", mFile_id);
                startActivity(ReportAty.class, bundle);
                break;
            case R.id.sendImg:
                sendMessage();
                break;
        }
    }

    private void sendMessage() {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("file_id", mFile_id);
        requestMap.put("user_token", ApiConfig.getToken());
        requestMap.put("comments_content", contentEdit.getContent());
        Observable<ResponseBody> observable = apiService.postUser_videos_comments(requestMap);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Map<String, String> map = Config.getMap(responseBody);
                        ToastUtils.showToast(map.get("msg"));
                        if (map.get("code").equals("1")) {
                            initData();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void collectState(int type) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("file_id", mFile_id);
        requestMap.put("user_token", ApiConfig.getToken());
        requestMap.put("type", String.valueOf(type));
        Observable<ResponseBody> observable = apiService.postFileCollect(requestMap);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Map<String, String> map = Config.getMap(responseBody);
                        ToastUtils.showToast(map.get("msg"));
                        if (map.get("code").equals("1")) {
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


    public class VideoDetailsAdapter extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {

        private Context mContext;

        public VideoDetailsAdapter(int layoutResId, @Nullable List<Map<String, String>> data, Context context) {
            super(layoutResId, data);
            mContext = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, Map<String, String> item) {
            /**
             *  {
             "comments_id": 2,
             "comments_content": "评论",
             "comments_creattime": 1543223669,
             "user_portrait": "20190000/z.jpg",
             "user_name": "定稿测试"
             }
             */

            ImageView headImg = helper.getView(R.id.headImg);
            GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + item.get("user_portrait")).circleCrop().into(headImg);

            helper.setText(R.id.nameTv, item.get("user_name"));
            helper.setText(R.id.timeTv, DateUtils.stampToDate(Long.parseLong(item.get("comments_creattime")), "MM-dd HH:mm"));
            helper.setText(R.id.contentTv, item.get("comments_content"));
        }
    }

}
