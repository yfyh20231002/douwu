package com.dazhukeji.douwu.ui.aty.home;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
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
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.GlideApp;
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
public class TeacherVideoAty extends BaseAty{
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.video_recyclerView)
    RecyclerView videoRecyclerView;
    private RecyclerViewManager mRecyclerViewManager;
    private VideoAdpater mVideoAdpater;
    private String mUser_teacher_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_teacher_video;
    }

    @Override
    public void initView() {
        txtTitle.setText("老师视频");
        mUser_teacher_id = getIntent().getStringExtra("user_teacher_id");
        mRecyclerViewManager = new RecyclerViewManager(videoRecyclerView);
        mRecyclerViewManager.setGridLayoutManager(2);
        videoRecyclerView.setNestedScrollingEnabled(false);
        videoRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildAdapterPosition(view)%2 == 0){
                    outRect.right = 10;
                }
            }
        });
    }

    @Override
    public void initData() {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("user_teacher_id", mUser_teacher_id);
        requestMap.put("paging", "1");
        Observable<ResponseBody> observable = apiService.postTeacherVideosList(requestMap);
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
                         "file": [
                         {
                         "file_id": 1,
                         "dance_type_name": "街舞",
                         "file_cover": "20190000/a.jpg",
                         "file_name": "个人视频1",
                         "file_collection": 9999,
                         "teacher_portrait": "20190000/z.jpg",
                         "file_type": 1,
                         "user_teacher_id": 1
                         }
                         ]
                         }
                         }
                         */
                        Map<String, String> map = Config.getMap(responseBody);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        if (data != null ) {
                            ArrayList<Map<String, String>> arrayList = JSONUtils.parseKeyAndValueToMapList(data.get("file"));
                            if (arrayList != null && arrayList.size()>0) {
                                mVideoAdpater = new VideoAdpater(R.layout.video_item, arrayList, mContext);
                                videoRecyclerView.setAdapter(mVideoAdpater);
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

    public class VideoAdpater extends BaseQuickAdapter<Map<String, String>,BaseViewHolder> {
        private Context mContext;
        public VideoAdpater(int layoutResId, @Nullable List<Map<String, String>> data, Context context) {
            super(layoutResId, data);
            mContext = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, Map<String, String> item) {
            /**
             *  {
             "file_id": 1,
             "dance_type_name": "街舞",
             "file_cover": "20190000/a.jpg",
             "file_name": "个人视频1",
             "file_collection": 9999,
             "teacher_portrait": "20190000/z.jpg",
             "file_type": 1,
             "user_teacher_id": 1
             }
             */
            helper.setText(R.id.name_tv,item.get("dance_type_name"));
            helper.setText(R.id.num_tv,String.valueOf(item.get("file_collection")));
            String file_cover = item.get("file_cover");
            if (!TextUtils.isEmpty(file_cover)){
                ImageView coverImg = helper.getView(R.id.coverImg);
                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL+ file_cover).diskCacheStrategy(DiskCacheStrategy.NONE).into(coverImg);
            }
            String user_portrait = item.get("teacher_portrait");
            if (!TextUtils.isEmpty(user_portrait)){
                ImageView headImg = helper.getView(R.id.head_img);
                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL+ user_portrait).circleCrop().into(headImg);
            }

            helper.setText(R.id.contentTv,item.get("file_name"));

        }
    }
}
