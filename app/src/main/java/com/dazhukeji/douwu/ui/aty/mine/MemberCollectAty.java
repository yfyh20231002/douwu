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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.bean.mine.member.UserCollectCurriculumBean;
import com.dazhukeji.douwu.bean.mine.member.UserCollectVideosBean;
import com.dazhukeji.douwu.contract.mine.member.MineCollectContract;
import com.dazhukeji.douwu.manager.RecyclerViewManager;
import com.dazhukeji.douwu.presenter.mine.member.MineCollectPresenter;
import com.dazhukeji.douwu.ui.aty.home.VideoDetailsAty;
import com.dazhukeji.douwu.view.TipDialog;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.DateUtils;
import com.zhangyunfei.mylibrary.utils.GlideApp;
import com.zhangyunfei.mylibrary.utils.ToastUtils;

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
 * 创建时间：2018/11/22 12:02
 * 功能描述：我的收藏
 */
public class MemberCollectAty extends BaseAty<MineCollectPresenter> implements MineCollectContract.View {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.editTv)
    TextView editTv;
    @BindView(R.id.head_img)
    ImageView headImg;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.contentTv)
    TextView contentTv;
    @BindView(R.id.collect_recyclerView)
    RecyclerView collectRecyclerView;
    @BindView(R.id.video_recyclerView)
    RecyclerView videoRecyclerView;
    @BindView(R.id.collectCourseTv)
    TextView collectCourseTv;
    @BindView(R.id.collectVideoTv)
    TextView collectVideoTv;

    private RecyclerViewManager mRecyclerViewManager;
    private int position = 0;
    private ChildCourseAdapter mCourseAdapter;
    private VideoAdpater mVideoAdpater;

    private int p = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_member_collect;
    }

    @Override
    public void initView() {
        txtTitle.setText("我的收藏");
        videoRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
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
        if (position == 0) {
            setCollectCourseClickable();
        } else if (position == 1) {
            setCollectVideoClickable();
        }

    }


    @OnClick({R.id.editTv, R.id.collectCourseTv, R.id.collectVideoTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.editTv:

                break;
            case R.id.collectCourseTv:
                setCollectCourseClickable();
                break;
            case R.id.collectVideoTv:
                setCollectVideoClickable();
                break;
        }

    }

    private void setCollectCourseClickable() {
        position = 0;
        editTv.setVisibility(View.VISIBLE);
        editTv.setText("编辑");
        editTv.setTextSize(15);
        collectCourseTv.setEnabled(false);
        collectVideoTv.setEnabled(true);
        setSelect();
    }

    private void setCollectVideoClickable() {
        position = 1;
        editTv.setVisibility(View.GONE);
        collectCourseTv.setEnabled(true);
        collectVideoTv.setEnabled(false);
        setSelect();
    }

    private void setSelect() {
        collectCourseTv.setBackground(null);
        collectVideoTv.setBackground(null);
        if (0 == position) {
            collectRecyclerView.setVisibility(View.VISIBLE);
            videoRecyclerView.setVisibility(View.GONE);
            ((MineCollectPresenter) mPresenter).postUserCollectCurriculum(ApiConfig.getToken(), p);
            mRecyclerViewManager = new RecyclerViewManager(collectRecyclerView);
            mRecyclerViewManager.setLinearLayoutManager(RecyclerView.VERTICAL);
            collectCourseTv.setBackgroundResource(R.drawable.icon_title_bg);
        } else if (1 == position) {
            collectRecyclerView.setVisibility(View.GONE);
            videoRecyclerView.setVisibility(View.VISIBLE);
            ((MineCollectPresenter) mPresenter).postUserCollectVideos(ApiConfig.getToken());
            mRecyclerViewManager = new RecyclerViewManager(videoRecyclerView);
            mRecyclerViewManager.setGridLayoutManager(2);
            collectVideoTv.setBackgroundResource(R.drawable.icon_title_bg);
        }

    }

    @Override
    public void refreshCurriculum(UserCollectCurriculumBean bean) {
        UserCollectCurriculumBean.DataBean data = bean.getData();
        UserCollectCurriculumBean.DataBean.UserBean user = data.getUser();
        String user_portrait = user.getUser_portrait();
        if (!TextUtils.isEmpty(user_portrait)) {
            GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + user_portrait).circleCrop().into(headImg);
        }
        nameTv.setText(user.getUser_name());
        contentTv.setText(user.getUser_signature());
        int user_sex = user.getUser_sex();
        if (user_sex == 1) {
            Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.icon_mine_boy);
            nameTv.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
            nameTv.setCompoundDrawablePadding(11);
        } else {
            Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.icon_mine_girl);
            nameTv.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
            nameTv.setCompoundDrawablePadding(11);
        }

        List<UserCollectCurriculumBean.DataBean.CurriculumBean> curriculum = data.getCurriculum();
        if (curriculum != null && curriculum.size() > 0) {
            collectRecyclerView.setVisibility(View.VISIBLE);
            mCourseAdapter = new ChildCourseAdapter(R.layout.course_item, curriculum, mContext);
            collectRecyclerView.setAdapter(mCourseAdapter);
        } else {
            collectRecyclerView.setVisibility(View.GONE);
        }

    }

    @Override
    public void refreshVideos(UserCollectVideosBean bean) {
        List<UserCollectVideosBean.DataBean.VideosBean> data = bean.getData().getVideos();
        if (data != null && data.size() > 0) {
            videoRecyclerView.setVisibility(View.VISIBLE);
            mVideoAdpater = new VideoAdpater(R.layout.video_item, data, mContext);
            videoRecyclerView.setAdapter(mVideoAdpater);
            mVideoAdpater.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    if (view.getId() == R.id.deleteImg) {
                        new TipDialog.Builder(mContext)
                                .setTitle("是否删除已收藏的视频")
                                .setMessage("被删除的视频将无法恢复")
                                .setCancelable(false)
                                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        collectState(String.valueOf(data.get(position).getFile_id()), 2);
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
                    bundle.putString("file_id", String.valueOf(data.get(position).getFile_id()));
                    bundle.putString("fileType", "1");
                    startActivity(VideoDetailsAty.class, bundle);
                }
            });
        } else {
            videoRecyclerView.setVisibility(View.GONE);
        }

    }

    private void collectState(String file_id, int type) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("file_id", file_id);
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
                            setSelect();
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

    public static class ChildCourseAdapter extends BaseQuickAdapter<UserCollectCurriculumBean.DataBean.CurriculumBean, BaseViewHolder> {
        private Context mContext;
        private boolean isShoDelete;

        public ChildCourseAdapter(int layoutResId, @Nullable List<UserCollectCurriculumBean.DataBean.CurriculumBean> data, Context context) {
            super(layoutResId, data);
            mContext = context;
        }


        public void setShoDelete(boolean shoDelete) {
            isShoDelete = shoDelete;
            notifyDataSetChanged();
        }

        @Override
        protected void convert(BaseViewHolder helper, UserCollectCurriculumBean.DataBean.CurriculumBean item) {
            ImageView headImg = helper.getView(R.id.head_img);
            GlideApp.with(mContext).load(item.getCurriculum_photo()).circleCrop().into(headImg);
            helper.setText(R.id.courseNameTv, item.getCurriculum_name());
            if (!TextUtils.isEmpty(item.getCurriculum_effective())) {
                if (Double.parseDouble(item.getCurriculum_effective()) == 1) {
                    helper.setText(R.id.adminTv, item.getCurriculum_admin() + "\u3000长期有效");
                } else {
                    long curriculum_start_time = Long.parseLong(item.getCurriculum_start_time());
                    long curriculum_over_time = Long.parseLong(item.getCurriculum_over_time());
                    helper.setText(R.id.adminTv, item.getCurriculum_admin() + "\u3000" + DateUtils.stampToDate(curriculum_start_time, "HH:mm") + "\u0020-\u0020" + DateUtils.stampToDate(curriculum_over_time, "HH:mm"));
                }
            }
            helper.setText(R.id.difficultyTv, item.getCurriculum_difficulty());
            helper.setText(R.id.priceTv, item.getCurriculum_actual_price());

            TextView lookTv = helper.getView(R.id.lookTv);
            TextView xiajiaTv = helper.getView(R.id.xiajiaTv);
            LinearLayout deleteLayout = helper.getView(R.id.deleteLayout);
            LinearLayout xiajiadeleteLayout = helper.getView(R.id.xiajiadeleteLayout);
            if (!TextUtils.isEmpty(item.getCurriculum_state())) {
                if (isShoDelete) {
                    if (Integer.parseInt(item.getCurriculum_state()) == 1) {
                        lookTv.setVisibility(View.GONE);
                        xiajiaTv.setVisibility(View.GONE);
                        deleteLayout.setVisibility(View.VISIBLE);
                        xiajiadeleteLayout.setVisibility(View.GONE);
                    } else {
                        lookTv.setVisibility(View.GONE);
                        xiajiaTv.setVisibility(View.GONE);
                        deleteLayout.setVisibility(View.GONE);
                        xiajiadeleteLayout.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (Integer.parseInt(item.getCurriculum_state()) == 1) {
                        lookTv.setVisibility(View.VISIBLE);
                        xiajiaTv.setVisibility(View.GONE);
                        deleteLayout.setVisibility(View.GONE);
                        xiajiadeleteLayout.setVisibility(View.GONE);
                    } else {
                        lookTv.setVisibility(View.GONE);
                        xiajiaTv.setVisibility(View.VISIBLE);
                        deleteLayout.setVisibility(View.GONE);
                        xiajiadeleteLayout.setVisibility(View.GONE);
                    }
                }
            }
        }
    }


    public static class VideoAdpater extends BaseQuickAdapter<UserCollectVideosBean.DataBean.VideosBean, BaseViewHolder> {
        private Context mContext;

        public VideoAdpater(int layoutResId, @Nullable List<UserCollectVideosBean.DataBean.VideosBean> data, Context context) {
            super(layoutResId, data);
            mContext = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, UserCollectVideosBean.DataBean.VideosBean item) {
            ImageView deleteImg = helper.getView(R.id.deleteImg);
            deleteImg.setVisibility(View.VISIBLE);
            helper.addOnClickListener(R.id.deleteImg);
            helper.setText(R.id.name_tv, item.getFile_name());
            helper.setText(R.id.num_tv, String.valueOf(item.getFile_collection()));
            String file_cover = item.getFile_cover();
            if (!TextUtils.isEmpty(file_cover)) {
                ImageView coverImg = helper.getView(R.id.coverImg);
                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + file_cover).diskCacheStrategy(DiskCacheStrategy.NONE).into(coverImg);
            }
            String user_portrait = item.getFile_portrait();
            if (!TextUtils.isEmpty(user_portrait)) {
                ImageView headImg = helper.getView(R.id.head_img);
                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + user_portrait).circleCrop().into(headImg);
            }
            helper.setText(R.id.contentTv, item.getDance_type_name());
        }
    }
}
