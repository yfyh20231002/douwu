package com.dazhukeji.douwu.ui.aty.mine;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
 * 创建时间：2018/11/27 13:36
 * 功能描述：我的粉丝
 */
public class OrgFansAty extends BaseAty {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.fansRecyclerView)
    RecyclerView fansRecyclerView;
//    @BindView(R.id.dynamicRecyclerView)
//    RecyclerView dynamicRecyclerView;

    private RecyclerViewManager mRecyclerViewManager;
//    private List<OrgFansBean.VideosBean> mVideosBeanList = new ArrayList<>();

    private int p = 1;
//    private VideoAdpater mVideoAdpater;
    private FansAdapter mFansAdapter;

    private List<Map<String,String>> mMapList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_fans;
    }

    @Override
    public void initView() {
        txtTitle.setText("我的粉丝");

        mRecyclerViewManager = new RecyclerViewManager(fansRecyclerView);
        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.VERTICAL);
//        fansRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                super.getItemOffsets(outRect, view, parent, state);
//                LinearLayoutManager layoutManager= (LinearLayoutManager) parent.getLayoutManager();
//                if (parent.getChildAdapterPosition(view) != layoutManager.getItemCount()+1){
//                    outRect.right= DisplayHelper.dp2px(mContext,20);
//                }
//            }
//        });
        fansRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mFansAdapter.getItemCount()) {
                    p++;
                    initData();
                }
            }
        });

//        mRecyclerViewManager = new RecyclerViewManager(dynamicRecyclerView);
//        mRecyclerViewManager.setGridLayoutManager(2);
//        dynamicRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                super.getItemOffsets(outRect, view, parent, state);
//                if (parent.getChildAdapterPosition(view)/2==0){
//                    outRect.right= DisplayHelper.dp2px(mContext,8);
//                }
//            }
//        });

//        dynamicRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
//                int lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
//                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mVideoAdpater.getItemCount()) {
//                    p++;
//                    ((OrgFansPresenter)mPresenter).postOrgFans(ApiConfig.getToken(),String.valueOf(p),"");
//                }
//            }
//        });
    }

    @Override
    public void initData() {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("user_token", ApiConfig.getToken());
        map.put("type", "2");
        map.put("number", String.valueOf(p));
        map.put("request_type", "1");
        Observable<ResponseBody> observable = apiService.postCommonFans(map);
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
                         "fans": [
                         {
                         "user_name": "剑心",
                         "user_portrait": "001/12.png",
                         "user_signature": "舞动人生，做最好的自己！"
                         }
                         ]
                         }
                         }
                         */
                        Map<String, String> stringMap = Config.getMap(responseBody);
                        if (Integer.parseInt(stringMap.get("code"))==1){
                            Map<String, String> data = JSONUtils.parseKeyAndValueToMap(stringMap.get("data"));
                            ArrayList<Map<String, String>> arrayList = JSONUtils.parseKeyAndValueToMapList(data.get("fans"));
                            if (p==1){
                                mMapList.clear();
                            }
                            if (arrayList!= null && arrayList.size()>0){
                                mMapList.addAll(arrayList);
                            }

                            if (mMapList.size()>0){
                                mFansAdapter = new FansAdapter(R.layout.fans_item,mMapList,mContext);
                                fansRecyclerView.setAdapter(mFansAdapter);
                            }
                        }else {
                            ToastUtils.showToast(stringMap.get("msg"));
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



    public class FansAdapter extends BaseQuickAdapter<Map<String, String>,BaseViewHolder> {

        private Context mContext;

        public FansAdapter(int layoutResId, @Nullable List<Map<String, String>> data, Context context) {
            super(layoutResId, data);
            mContext = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, Map<String, String> item) {
            /**
             * {
             "user_name": "剑心",
             "user_portrait": "001/12.png",
             "user_signature": "舞动人生，做最好的自己！"
             }
             */
            ImageView imageView = helper.getView(R.id.head_img);
            GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL+item.get("user_portrait")).circleCrop().into(imageView);
            helper.setText(R.id.nameTv,item.get("user_name"));
            helper.setText(R.id.descTv,item.get("user_signature"));
        }
    }

//    public static class VideoAdpater extends BaseQuickAdapter<OrgFansBean.VideosBean,BaseViewHolder> {
//        public VideoAdpater(int layoutResId, @Nullable List<OrgFansBean.VideosBean> data) {
//            super(layoutResId, data);
//        }
//
//        @Override
//        protected void convert(BaseViewHolder helper, OrgFansBean.VideosBean item) {
//            helper.setText(R.id.name_tv,item.getFile_name());
//            helper.setText(R.id.num_tv,String.valueOf(item.getFile_collection()));
//            helper.setText(R.id.contentTv,item.getDance_type_name());
//            String file_cover = item.getFile_cover();
//            if (!TextUtils.isEmpty(file_cover)){
//                ImageView coverImg = helper.getView(R.id.coverImg);
//                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL+ file_cover).diskCacheStrategy(DiskCacheStrategy.NONE).into(coverImg);
//            }

//            String user_portrait = item.getTeacher_portrait();
//            if (!TextUtils.isEmpty(user_portrait)){
//                ImageView headImg = helper.getView(R.id.head_img);
//                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL+ user_portrait).circleCrop().into(headImg);
//            }
//        }
//    }
}
