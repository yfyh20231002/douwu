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
 * 创建时间：2018/11/20 15:24
 * 功能描述：机构视频
 */
public class DanceOrganizationVideoAty extends BaseAty {
    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.video_recyclerView)
    RecyclerView videoRecyclerView;
    private RecyclerViewManager mRecyclerViewManager;
    private VideoAdpater mVideoAdpater;
    private String mOrganization_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mechanism_video;
    }

    @Override
    public void initView() {
        mOrganization_id = getIntent().getStringExtra("organization_id");
        txtTitle.setText("机构视频");
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
        requestMap.put("organization_id", mOrganization_id);
        Observable<ResponseBody> observable = apiService.postOrganizationVideos(requestMap);
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
                         "file_id": 1,
                         "dance_type_name": "街舞",
                         "file_cover": "001/12.png",
                         "file_name": "街舞",
                         "file_type": 1,
                         "file_creattime": 1548246620,
                         "file_collection": 999,
                         "organization_portrait": "20181122/dfe52400de36aaf502a037f08f2f408f.jpg",
                         "organization_name": "舞动人生"
                         }
                         ]
                         }
                         */
                        Map<String, String> map = Config.getMap(responseBody);
                        ArrayList<Map<String, String>> arrayList = JSONUtils.parseKeyAndValueToMapList(map.get("data"));
                        if (arrayList != null && arrayList.size()>0) {
                            mVideoAdpater = new VideoAdpater(R.layout.video_item, arrayList, mContext);
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


    public class VideoAdpater extends BaseQuickAdapter<Map<String, String>,BaseViewHolder>{
        private Context mContext;
        public VideoAdpater(int layoutResId, @Nullable List<Map<String, String>> data, Context context) {
            super(layoutResId, data);
            mContext = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, Map<String, String> item) {
            helper.setText(R.id.name_tv,item.get("dance_type_name"));
            helper.setText(R.id.num_tv,String.valueOf(item.get("file_collection")));
                String file_cover = item.get("file_cover");
                if (!TextUtils.isEmpty(file_cover)){
                    ImageView coverImg = helper.getView(R.id.coverImg);
                    GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL+ file_cover).diskCacheStrategy(DiskCacheStrategy.NONE).into(coverImg);
                }
                String user_portrait = item.get("organization_portrait");
                if (!TextUtils.isEmpty(user_portrait)){
                    ImageView headImg = helper.getView(R.id.head_img);
                    GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL+ user_portrait).circleCrop().into(headImg);
                }

                helper.setText(R.id.contentTv,item.get("organization_name"));

        }
    }

}
