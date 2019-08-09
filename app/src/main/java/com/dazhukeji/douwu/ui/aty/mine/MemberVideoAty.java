package com.dazhukeji.douwu.ui.aty.mine;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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
import com.dazhukeji.douwu.ui.aty.home.VideoDetailsAty;
import com.dazhukeji.douwu.view.TipDialog;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.GlideApp;
import com.zhangyunfei.mylibrary.utils.JSONUtils;
import com.zhangyunfei.mylibrary.utils.ToastUtils;

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
 * 时间：2018/11/25 0025
 * 联系方式：32457127@qq.com
 */
public class MemberVideoAty extends BaseAty {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.head_img)
    ImageView headImg;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.contentTv)
    TextView contentTv;
    @BindView(R.id.video_recyclerView)
    RecyclerView videoRecyclerView;
    private RecyclerViewManager mRecyclerViewManager;
    private VideoAdpater mVideoAdpater;

    @Override
    public int getLayoutId() {
        return R.layout.activity_member_video;
    }

    @Override
    public void initView() {
        txtTitle.setText("我的作品");
        mRecyclerViewManager = new RecyclerViewManager(videoRecyclerView);
        mRecyclerViewManager.setGridLayoutManager(2);
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
        requestMap.put("user_token", ApiConfig.getToken());
        Observable<ResponseBody> observable = apiService.postUserVideos(requestMap);
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
                         "user_data": {
                         "user_name": "",
                         "user_sex": 1,
                         "user_signature": "",
                         "user_role": 2,
                         "user_portrait": ""
                         },
                         "videos": [
                         {
                         "file_id": 3,
                         "dance_type_name": "街舞",
                         "file_cover": "001/12.png",
                         "file_name": "街舞",
                         "file_collection": 57,
                         "file_type": 1,
                         "user_name": "",
                         "user_portrait": ""
                         }
                         ]
                         }
                         }
                         */
                        Map<String, String> map = Config.getMap(responseBody);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        Map<String, String> userData = JSONUtils.parseKeyAndValueToMap(data.get("user_data"));
                        GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL+userData.get("user_portrait")).circleCrop().into(headImg);
                        nameTv.setText(userData.get("user_name"));
                        contentTv.setText(userData.get("user_signature"));
                        if (!TextUtils.isEmpty(userData.get("user_sex"))){
                            if (Integer.parseInt(userData.get("user_sex"))==1){
                                Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.icon_mine_boy);
                                nameTv.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                                nameTv.setCompoundDrawablePadding(11);
                            }else if (Integer.parseInt(userData.get("user_sex"))==2){
                                Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.icon_mine_girl);
                                nameTv.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                                nameTv.setCompoundDrawablePadding(11);
                            }
                        }
                        ArrayList<Map<String, String>> arrayList = JSONUtils.parseKeyAndValueToMapList(data.get("videos"));
                        if (arrayList != null && arrayList.size()>0){
                            mVideoAdpater = new VideoAdpater(R.layout.video_item, arrayList, mContext);
                            videoRecyclerView.setAdapter(mVideoAdpater);
                            mVideoAdpater.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                    if (view.getId() == R.id.deleteImg) {
                                        new TipDialog.Builder(mContext)
                                                .setTitle("是否删除我的作品")
                                                .setMessage("被删除的作品将无法恢复")
                                                .setCancelable(false)
                                                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        deleteMyWork(arrayList.get(position).get("file_id"));
                                                        dialog.dismiss();
                                                    }
                                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        }).create().show();
                                    }
                                }
                            });
                            mVideoAdpater.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("file_id", arrayList.get(position).get("file_id"));
                                    bundle.putString("fileType", "1");
                                    bundle.putBoolean("isGoneReport",true);
                                    startActivity(VideoDetailsAty.class, bundle);
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

    private void deleteMyWork(String file_id){
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("file_id", file_id);
        requestMap.put("user_token", ApiConfig.getToken());
        Observable<ResponseBody> observable = apiService.postUserVideosDelete(requestMap);
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


    public static class VideoAdpater extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {
        private Context mContext;

        public VideoAdpater(int layoutResId, @Nullable List<Map<String, String>> data, Context context) {
            super(layoutResId, data);
            mContext = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, Map<String, String> item) {
            /**
             * {
             "file_id": 3,
             "dance_type_name": "街舞",
             "file_cover": "001/12.png",
             "file_name": "街舞",
             "file_collection": 57,
             "file_type": 1,
             "user_name": "",
             "user_portrait": ""
             }
             */
            ImageView deleteImg = helper.getView(R.id.deleteImg);
            deleteImg.setVisibility(View.VISIBLE);
            helper.addOnClickListener(R.id.deleteImg);
            helper.setText(R.id.name_tv, item.get("file_name"));
            helper.setText(R.id.num_tv, item.get("file_collection"));
            String file_cover = item.get("file_cover");
            if (!TextUtils.isEmpty(file_cover)) {
                ImageView coverImg = helper.getView(R.id.coverImg);
                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + file_cover).diskCacheStrategy(DiskCacheStrategy.NONE).into(coverImg);
            }
            String user_portrait = item.get("user_portrait");
            if (!TextUtils.isEmpty(user_portrait)) {
                ImageView headImg = helper.getView(R.id.head_img);
                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + user_portrait).circleCrop().into(headImg);
            }
            helper.setText(R.id.contentTv, item.get("dance_type_name"));
        }
    }
}
