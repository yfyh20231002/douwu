package com.dazhukeji.douwu.ui.aty.home;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.adapter.TitlesAdapter;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.bean.home.video.SplendidVideoBean;
import com.dazhukeji.douwu.bean.publicBean.DanceTypeBean;
import com.dazhukeji.douwu.contract.DanceTypeContract;
import com.dazhukeji.douwu.contract.home.video.SplendidVideoContract;
import com.dazhukeji.douwu.manager.RecyclerViewManager;
import com.dazhukeji.douwu.presenter.DanceTypePresenter;
import com.dazhukeji.douwu.presenter.home.video.SplendidVideoPresenter;
import com.dazhukeji.douwu.view.MyEditText;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.utils.DisplayHelper;
import com.zhangyunfei.mylibrary.utils.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/19 16:17
 * 功能描述：精彩视频
 */
public class VideoAty extends BaseAty<SplendidVideoPresenter> implements SplendidVideoContract.View, DanceTypeContract.View {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.titles_recyclerView)
    RecyclerView titlesRecyclerView;
    @BindView(R.id.search_content_tv)
    MyEditText searchContentTv;
    @BindView(R.id.search_tv)
    TextView searchTv;
    @BindView(R.id.select_tv)
    TextView selectTv;
    @BindView(R.id.select2_tv)
    TextView selectTv2;
    @BindView(R.id.time_relativeLayout)
    RelativeLayout timeRelativeLayout;
    @BindView(R.id.video_relativeLayout)
    RelativeLayout videoRelativeLayout;
    @BindView(R.id.video_recyclerView)
    RecyclerView videoRecyclerView;
    private PopupWindow mTimePopupWindow;
    private PopupWindow mVideoPopupWindow;
    private RecyclerViewManager mRecyclerViewManager;
    private VideoAdapter mVideoAdpater;
    private TitlesAdapter mTitlesAdapter;
    private String mDance_type_id;
    private String mDistrict_id;
    private String mFile_category = "3";
    private String mOrder = "1";
    private DanceTypePresenter mDanceTypePresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_video;
    }

    @Override
    public void initView() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mDance_type_id = extras.getString("dance_type_id");
            mDistrict_id = extras.getString("district_id");
        }
        txtTitle.setText("精彩视频");
        searchContentTv.setHint("请输入您要搜索的名称");
        Drawable drawableLeft = getResources().getDrawable(
                R.drawable.icon_time);
        selectTv.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                null, null, null);
        selectTv.setCompoundDrawablePadding(2);
        selectTv.setText("时间升序");
        Drawable drawableLeft3 = getResources().getDrawable(
                R.drawable.icon_star);
        selectTv2.setCompoundDrawablesWithIntrinsicBounds(drawableLeft3,
                null, null, null);
        selectTv2.setCompoundDrawablePadding(2);
        selectTv2.setText("全部视频");

        mRecyclerViewManager = new RecyclerViewManager(videoRecyclerView);
        mRecyclerViewManager.setGridLayoutManager(2);
        videoRecyclerView.setNestedScrollingEnabled(false);
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
        requestData();
        mDanceTypePresenter = new DanceTypePresenter();
        mDanceTypePresenter.attachView(this, mContext);
        mDanceTypePresenter.postDanceTypeSelect();
    }

    private void requestData() {
        String searchContentTvContent = searchContentTv.getContent();
        ((SplendidVideoPresenter) mPresenter).postSplendVideo(mDistrict_id, mDance_type_id, mFile_category, searchContentTvContent, mOrder);
    }

    @OnClick({R.id.search_tv, R.id.time_relativeLayout, R.id.video_relativeLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_tv:
                requestData();
                break;
            case R.id.time_relativeLayout:
                setTimePop();
                break;
            case R.id.video_relativeLayout:
                setVideoPop();
                break;
        }
    }

    private void setTimePop() {
        if (null == mTimePopupWindow) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_pop, null);
            TextView timeTv = view.findViewById(R.id.time_tv);
            TextView collecTv = view.findViewById(R.id.collect_tv);
            TextView videoTv = view.findViewById(R.id.video_tv);
            videoTv.setVisibility(View.VISIBLE);
            timeTv.setText("时间升序");
            collecTv.setText("时间降序");
            videoTv.setText("收藏度");
            Drawable drawableLeft = getResources().getDrawable(
                    R.drawable.icon_time);

            collecTv.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                    null, null, null);
            collecTv.setCompoundDrawablePadding(2);
            mTimePopupWindow = new PopupWindow(view, DisplayHelper.dp2px(mContext, 100),
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            mTimePopupWindow.setBackgroundDrawable(null);
            mTimePopupWindow.setOutsideTouchable(false);
            mTimePopupWindow.showAsDropDown(timeRelativeLayout);
            timeTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Drawable drawableLeft = getResources().getDrawable(
                            R.drawable.icon_time);

                    selectTv.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                            null, null, null);
                    selectTv.setCompoundDrawablePadding(2);
                    selectTv.setText("时间升序");
                    mOrder = "1";
                    requestData();
                    mTimePopupWindow.dismiss();
                }
            });
            collecTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Drawable drawableLeft = getResources().getDrawable(
                            R.drawable.icon_time);

                    selectTv.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                            null, null, null);
                    selectTv.setCompoundDrawablePadding(2);
                    selectTv.setText("时间降序");
                    mOrder = "2";
                    requestData();
                    mTimePopupWindow.dismiss();
                }
            });

            videoTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Drawable drawableLeft = getResources().getDrawable(
                            R.drawable.icon_star);

                    selectTv.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                            null, null, null);
                    selectTv.setCompoundDrawablePadding(2);
                    selectTv.setText("收藏度");
                    mOrder = "3";
                    requestData();
                    mTimePopupWindow.dismiss();
                }
            });
        } else {
            if (mTimePopupWindow.isShowing()) {
                mTimePopupWindow.dismiss();
            } else {
                mTimePopupWindow.showAsDropDown(timeRelativeLayout);
            }
        }
    }

    private void setVideoPop() {
        if (null == mVideoPopupWindow) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_pop, null);
            TextView timeTv = view.findViewById(R.id.time_tv);
            TextView collecTv = view.findViewById(R.id.collect_tv);
            TextView videoTv = view.findViewById(R.id.video_tv);
            TextView lastTv = view.findViewById(R.id.lastTv);
            videoTv.setVisibility(View.VISIBLE);
            lastTv.setVisibility(View.VISIBLE);
            timeTv.setText("全部视频");
            Drawable drawableLeft = getResources().getDrawable(
                    R.drawable.icon_star);
            timeTv.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                    null, null, null);
            timeTv.setCompoundDrawablePadding(2);

            collecTv.setText("个人视频");
            Drawable drawableLeft2 = getResources().getDrawable(
                    R.drawable.icon_video_teacher);
            collecTv.setCompoundDrawablesWithIntrinsicBounds(drawableLeft2,
                    null, null, null);
            collecTv.setCompoundDrawablePadding(2);

            videoTv.setText("教师视频");
            Drawable drawableLeft3 = getResources().getDrawable(
                    R.drawable.icon_video_teacher);
            videoTv.setCompoundDrawablesWithIntrinsicBounds(drawableLeft3,
                    null, null, null);
            videoTv.setCompoundDrawablePadding(2);

            lastTv.setText("机构视频");
            Drawable drawableLeft4 = getResources().getDrawable(
                    R.drawable.icon_video_jigou);
            lastTv.setCompoundDrawablesWithIntrinsicBounds(drawableLeft4,
                    null, null, null);
            lastTv.setCompoundDrawablePadding(2);

            mVideoPopupWindow = new PopupWindow(view, DisplayHelper.dp2px(mContext, 100),
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            mVideoPopupWindow.setBackgroundDrawable(null);
            mVideoPopupWindow.setOutsideTouchable(false);
            mVideoPopupWindow.showAsDropDown(videoRelativeLayout);
            timeTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Drawable drawableLeft = getResources().getDrawable(
                            R.drawable.icon_star);

                    selectTv2.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                            null, null, null);
                    selectTv2.setCompoundDrawablePadding(2);
                    selectTv2.setText("全部视频");
                    mFile_category = "3";
                    requestData();
                    mVideoPopupWindow.dismiss();
                }
            });
            collecTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Drawable drawableLeft = getResources().getDrawable(
                            R.drawable.icon_video_teacher);

                    selectTv2.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                            null, null, null);
                    selectTv2.setCompoundDrawablePadding(2);
                    selectTv2.setText("个人视频");
                    mFile_category = "4";
                    requestData();
                    mVideoPopupWindow.dismiss();
                }
            });
            videoTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Drawable drawableLeft = getResources().getDrawable(
                            R.drawable.icon_video_teacher);

                    selectTv2.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                            null, null, null);
                    selectTv2.setCompoundDrawablePadding(2);
                    selectTv2.setText("教师视频");
                    mFile_category = "1";
                    requestData();
                    mVideoPopupWindow.dismiss();
                }
            });

            lastTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Drawable drawableLeft = getResources().getDrawable(
                            R.drawable.icon_video_jigou);

                    selectTv2.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                            null, null, null);
                    selectTv2.setCompoundDrawablePadding(2);
                    selectTv2.setText("机构视频");
                    mFile_category = "2";
                    requestData();
                    mVideoPopupWindow.dismiss();
                }
            });
        } else {
            if (mVideoPopupWindow.isShowing()) {
                mVideoPopupWindow.dismiss();
            } else {
                mVideoPopupWindow.showAsDropDown(videoRelativeLayout);
            }
        }
    }

    @Override
    public void refreshSplendVideo(SplendidVideoBean splendidVideoBean) {
        List<SplendidVideoBean.DataBean> data = splendidVideoBean.getData();
        if (data != null && data.size() > 0) {
            mVideoAdpater = new VideoAdapter(R.layout.video_item, data, mContext);
            videoRecyclerView.setAdapter(mVideoAdpater);
            mVideoAdpater.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("file_id", String.valueOf(data.get(position).getFile_id()));
                    bundle.putString("fileType", "1");
                    startActivity(VideoDetailsAty.class, bundle);
                }
            });
        }


    }

    @Override
    public void danceTypeSuccess(DanceTypeBean danceTypeBean) {
        List<DanceTypeBean.DataBean> dance_type = danceTypeBean.getData();
        if (dance_type.size() > 0) {
            mRecyclerViewManager = new RecyclerViewManager(titlesRecyclerView);
            mRecyclerViewManager.setLinearLayoutManager(RecyclerView.HORIZONTAL);
            mTitlesAdapter = new TitlesAdapter(R.layout.home_title_item, dance_type);
            titlesRecyclerView.setAdapter(mTitlesAdapter);
            mTitlesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    mDance_type_id = String.valueOf(dance_type.get(position).getDance_type_id());
                    mTitlesAdapter.setSelectPosition(position);
                    requestData();
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDanceTypePresenter != null) {
            mDanceTypePresenter.detachView();
        }

    }

    public class VideoAdapter extends BaseQuickAdapter<SplendidVideoBean.DataBean, BaseViewHolder> {
        private Context mContext;

        public VideoAdapter(int layoutResId, @Nullable List<SplendidVideoBean.DataBean> data, Context context) {
            super(layoutResId, data);
            mContext = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, SplendidVideoBean.DataBean item) {
            //1:教师2:机构3:精彩视频(全部)4:个人
            helper.setText(R.id.name_tv, item.getFile_name());
            helper.setText(R.id.num_tv, String.valueOf(item.getFile_collection()));
            int file_category = item.getFile_category();
            if (file_category == 1) {
                String file_cover = item.getFile_cover();
                if (!TextUtils.isEmpty(file_cover)) {
                    ImageView coverImg = helper.getView(R.id.coverImg);
                    GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + file_cover).into(coverImg);
                }
                String teacher_portrait = item.getTeacher_portrait();
                if (!TextUtils.isEmpty(teacher_portrait)) {
                    ImageView headImg = helper.getView(R.id.head_img);
                    GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + teacher_portrait).circleCrop().into(headImg);
                }
                helper.setText(R.id.contentTv, item.getTeacher_name());
            } else if (file_category == 2) {
                String file_cover = item.getFile_cover();
                if (!TextUtils.isEmpty(file_cover)) {
                    ImageView coverImg = helper.getView(R.id.coverImg);
                    GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + file_cover).into(coverImg);
                }
                String organization_portrait = item.getOrganization_portrait();
                if (!TextUtils.isEmpty(organization_portrait)) {
                    ImageView headImg = helper.getView(R.id.head_img);
                    GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + organization_portrait).circleCrop().into(headImg);
                }
                helper.setText(R.id.contentTv, item.getOrganization_name());
            } else if (file_category == 4) {
                String file_cover = item.getFile_cover();
                if (!TextUtils.isEmpty(file_cover)) {
                    ImageView coverImg = helper.getView(R.id.coverImg);
                    GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + file_cover).diskCacheStrategy(DiskCacheStrategy.NONE).into(coverImg);
                }
                String user_portrait = item.getUser_portrait();
                if (!TextUtils.isEmpty(user_portrait)) {
                    ImageView headImg = helper.getView(R.id.head_img);
                    GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + user_portrait).circleCrop().into(headImg);
                }

                helper.setText(R.id.contentTv, item.getUser_name());
            }

        }
    }

}
