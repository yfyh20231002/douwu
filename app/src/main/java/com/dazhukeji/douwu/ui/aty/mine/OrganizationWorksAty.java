package com.dazhukeji.douwu.ui.aty.mine;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 创建者：zhangyunfei
 * 创建时间：2019/1/16 16:07
 * 功能描述：老师作品
 */
public class OrganizationWorksAty extends BaseAty {
    @BindView(R.id.txt_title)
    TextView txtTitle;

    @BindView(R.id.rightImg)
    ImageView rightImg;

    @BindView(R.id.head_img)
    ImageView headImg;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.contentTv)
    TextView contentTv;

    @BindView(R.id.video_recyclerView)
    RecyclerView video_recyclerView;

    private RecyclerViewManager mRecyclerViewManager;
    private VideoAdpater mVideoAdpater;

    @Override
    public int getLayoutId() {
        return R.layout.activity_teacher_works;
    }

    @Override
    public void initView() {
        txtTitle.setText("机构作品");
        rightImg.setVisibility(View.VISIBLE);
        rightImg.setImageResource(R.drawable.icon_upload_video);
        mRecyclerViewManager = new RecyclerViewManager(video_recyclerView);
        mRecyclerViewManager.setGridLayoutManager(2);
        video_recyclerView.setNestedScrollingEnabled(false);
        video_recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildAdapterPosition(view) % 2 == 0) {
                    outRect.right = 10;
                }
            }
        });

    }

    @Override
    public void initData() {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("user_token", ApiConfig.getToken());
        Observable<ResponseBody> observable = apiService.postOrganizationFile(map);
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
                         "organization": {
                         "organization_id": 8,
                         "organization_name": "云飞",
                         "organization_portrait": "",
                         "organization_synopsis": "拥有千名教师的专业舞蹈培训机构"
                         },
                         "file": [
                         {
                         "file_id": 35,
                         "dance_type_name": "街舞",
                         "file_cover": "001/12.png",
                         "file_name": "文件名称",
                         "file_type": 3,
                         "file_creattime": 1550554001,
                         "file_collection": 0,
                         "organization_portrait": "",
                         "organization_name": "云飞"
                         }
                         ]
                         }
                         }
                         */
                        Map<String, String> map = Config.getMap(responseBody);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        Map<String, String> organization = JSONUtils.parseKeyAndValueToMap(data.get("organization"));
                        String user_portrait = organization.get("organization_portrait");
                        if (!TextUtils.isEmpty(user_portrait)) {
                            GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + user_portrait).circleCrop().into(headImg);
                        }
                        nameTv.setText(organization.get("organization_name"));
                        contentTv.setText(organization.get("organization_synopsis"));
//                        int user_sex = Integer.parseInt(organization.get("teacher_sex"));
//                        if (user_sex == 1) {
//                            Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.icon_mine_boy);
//                            nameTv.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
//                            nameTv.setCompoundDrawablePadding(11);
//                        } else {
//                            Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.icon_mine_girl);
//                            nameTv.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
//                            nameTv.setCompoundDrawablePadding(11);
//                        }

                        ArrayList<Map<String, String>> file = JSONUtils.parseKeyAndValueToMapList(data.get("file"));
                        if (file != null && file.size() > 0) {
                            mVideoAdpater = new VideoAdpater(R.layout.video_item, file, mContext);
                            video_recyclerView.setAdapter(mVideoAdpater);
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
                                                        deleteOrgWork(file.get(position).get("file_id"));
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
                                    bundle.putString("file_id", String.valueOf(file.get(position).get("file_id")));
                                    bundle.putString("fileType", "1");
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

    private void deleteOrgWork(String file_id) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("file_id", file_id);
        requestMap.put("user_token", ApiConfig.getToken());
        Observable<ResponseBody> observable = apiService.postOrganizationDeleteWork(requestMap);
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


    @OnClick(R.id.rightImg)
    public void onViewClicked(View view) {
        showPop(view);
    }

    private void showPop(View v) {
        PopupWindow mPopupWindow = new PopupWindow();
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = getLayoutInflater().inflate(R.layout.pop_teacher_works, null);
        LinearLayout picLayout = view.findViewById(R.id.picLayout);
        LinearLayout videoLayout = view.findViewById(R.id.videoLayout);
        LinearLayout musicLayout = view.findViewById(R.id.musicLayout);
        picLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("type", "image");
                bundle.putString("from","org");
                startActivity(PublishVideoAty.class, bundle);
            }
        });
        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("type", "video");
                bundle.putString("from","org");
                startActivity(PublishVideoAty.class, bundle);
            }
        });
        musicLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("type", "music");
                bundle.putString("from","org");
                startActivity(PublishVideoAty.class, bundle);
            }
        });
        mPopupWindow.setContentView(view);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAtLocation(v, Gravity.BOTTOM, 0, v.getHeight());
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);
            }
        });
    }

    public class VideoAdpater extends BaseQuickAdapter<Map<String, String>, BaseViewHolder> {
        private Context mContext;

        public VideoAdpater(int layoutResId, @Nullable List<Map<String, String>> data, Context context) {
            super(layoutResId, data);
            mContext = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, Map<String, String> item) {
            ImageView deleteImg = helper.getView(R.id.deleteImg);
            deleteImg.setVisibility(View.VISIBLE);
            helper.addOnClickListener(R.id.deleteImg);
            helper.setText(R.id.name_tv, item.get("file_name"));
            helper.setText(R.id.num_tv, String.valueOf(item.get("file_collection")));
            String file_cover = item.get("file_cover");
            if (!TextUtils.isEmpty(file_cover)) {
                ImageView coverImg = helper.getView(R.id.coverImg);
                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + file_cover).diskCacheStrategy(DiskCacheStrategy.NONE).into(coverImg);
            }
            String user_portrait = item.get("teacher_portrait");
            if (!TextUtils.isEmpty(user_portrait)) {
                ImageView headImg = helper.getView(R.id.head_img);
                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + user_portrait).circleCrop().into(headImg);
            }

            helper.setText(R.id.contentTv, item.get("dance_type_name"));
        }
    }
}
