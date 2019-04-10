package com.dazhukeji.douwu.ui.aty.home;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.MyLogger;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.bean.home.organization.OrganizationFindBean;
import com.dazhukeji.douwu.contract.home.organization.OrganizationDetailsContract;
import com.dazhukeji.douwu.manager.RecyclerViewManager;
import com.dazhukeji.douwu.presenter.home.organization.OrganizationDetailsPresenter;
import com.dazhukeji.douwu.ui.aty.mine.EditOrganizationAty;
import com.dazhukeji.douwu.view.RatingBar;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.DateUtils;
import com.zhangyunfei.mylibrary.utils.DisplayHelper;
import com.zhangyunfei.mylibrary.utils.GlideApp;
import com.zhangyunfei.mylibrary.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
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
 * 创建时间：2018/11/20 10:28
 * 功能描述：机构详情
 */
public class DanceOrgDetailsAty extends BaseAty<OrganizationDetailsPresenter> implements OrganizationDetailsContract.View {
    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.editTv)
    TextView editTv;
    @BindView(R.id.info_frameLayout)
    FrameLayout infoFrameLayout;
    @BindView(R.id.videoplayer)
    JzvdStd videoplayer;
    @BindView(R.id.course_child_recyclerView)
    RecyclerView courseChildRecyclerView;
    @BindView(R.id.teacher_recyclerView)
    RecyclerView teacherRecyclerView;
    @BindView(R.id.video_recyclerView)
    RecyclerView videoRecyclerView;
    @BindView(R.id.logoImg)
    ImageView logoImg;
    @BindView(R.id.nameTv)
    TextView nameTv;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.score_tv)
    TextView scoreTv;
    @BindView(R.id.timeTv)
    TextView timeTv;
    @BindView(R.id.phoneTv)
    TextView phoneTv;
    @BindView(R.id.addressTv)
    TextView addressTv;
    @BindView(R.id.typeTv)
    TextView typeTv;
    @BindView(R.id.followTv)
    TextView followTv;
    @BindView(R.id.briefTv)
    TextView briefTv;
    @BindView(R.id.recruitTitleTv)
    TextView recruitTitleTv;
    @BindView(R.id.recruitTypeTv)
    TextView recruitTypeTv;
    @BindView(R.id.see_tv)
    TextView seeTv;
    @BindView(R.id.courseTv)
    RelativeLayout courseTv;
    @BindView(R.id.danceTeacherTv)
    TextView danceTeacherTv;
    @BindView(R.id.mechanismVideoTv)
    TextView mechanismVideoTv;
    @BindView(R.id.wifi)
    TextView wifiView;
    @BindView(R.id.parking)
    TextView parkingView;
    @BindView(R.id.wechatPay)
    TextView wechatPayView;
    @BindView(R.id.aliPay)
    TextView aliPayView;


    private RecyclerViewManager mRecyclerViewManager;
    private List<OrganizationFindBean.DataBean.VideosBean> mList = new ArrayList<>();
    private CourseAdapter mChildCourseAdapter;
    private VideoAdpater mVideoAdpater;
    private String mOrganization_id;

    private int mInvitation_id;
    private boolean mIsShow;
    private int attention_state = -1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_danceorg_details;
    }

    @Override
    public void initView() {
        mOrganization_id = getIntent().getStringExtra("organization_id");
        mIsShow = getIntent().getBooleanExtra("isShow", false);
        txtTitle.setText("机构详情");
        if (mIsShow) {
            editTv.setVisibility(View.VISIBLE);
            editTv.setText("编辑");
            editTv.setTextSize(15);
        }
        //        videoplayer.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"
        //                , "", Jzvd.SCREEN_WINDOW_NORMAL);
        //        GlideApp.with(this).load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png").into(videoplayer.thumbImageView);


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

        mRecyclerViewManager = new RecyclerViewManager(teacherRecyclerView);
        teacherRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.VERTICAL);


        mRecyclerViewManager = new RecyclerViewManager(videoRecyclerView);
        videoRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerViewManager.setGridLayoutManager(2);
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


    @Override
    public void initData() {
        ((OrganizationDetailsPresenter) mPresenter).postOrganizationFind(mOrganization_id);
    }

    @OnClick({R.id.editTv, R.id.followTv, R.id.see_tv, R.id.courseTv, R.id.danceTeacherTv, R.id.mechanismVideoTv})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("organization_id", String.valueOf(mOrganization_id));
        switch (view.getId()) {
            case R.id.editTv:
                startActivity(EditOrganizationAty.class, bundle);
                break;
            case R.id.followTv:
                follow();
                break;
            case R.id.see_tv:
                bundle.putString("invitation_id", String.valueOf(mInvitation_id));
                startActivity(DanceRecruitDetailsAty.class, bundle);
                break;
            case R.id.courseTv:
                startActivity(DanceOrgCourseAty.class, bundle);
                break;
            case R.id.danceTeacherTv:
                startActivity(DanceTeacherAty.class, bundle);
                break;
            case R.id.mechanismVideoTv:
                startActivity(DanceOrganizationVideoAty.class, bundle);
                break;
        }
    }

    private void follow() {
        if (attention_state == -1) {
            Toast.makeText(mContext, "状态异常", Toast.LENGTH_SHORT).show();
            return;
        }
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("organization_id", String.valueOf(mOrganization_id));
        requestMap.put("user_token", ApiConfig.getToken());
        Observable<ResponseBody> observable = attention_state == 2 ? apiService.postOrganizationAttention(requestMap) : apiService.postOrganizationCancelAttention(requestMap);
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

    @Override
    public void refresh(OrganizationFindBean organizationFindBean) {
        OrganizationFindBean.DataBean.BasicBean basic = organizationFindBean.getData().getBasic();

        if (!mIsShow) {
            attention_state = organizationFindBean.getData().getAttention_state();
            exchangeFollowState();
        }

        GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + basic.getOrganization_portrait()).into(logoImg);
        nameTv.setText(basic.getOrganization_name());
        int organization_level = basic.getOrganization_level();
        ratingBar.setStar(organization_level);
        scoreTv.setText(String.valueOf(organization_level));
        timeTv.setText("营业时间：" + basic.getOrganization_business_hours());
        addressTv.setText("门店地址：" + basic.getOrganization_site());
        typeTv.setText("舞种类型：" + basic.getOrganization_type());

        //机构设施
        setGroupDevice(basic);

        briefTv.setText(basic.getOrganization_synopsis());

        OrganizationFindBean.DataBean.VideoBean video = organizationFindBean.getData().getVideo();
        videoplayer.setUp(ApiConfig.BASE_IMG_URL + video.getPromotional_video(), "", Jzvd.SCREEN_WINDOW_NORMAL);
        GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + video.getPromotional_cover()).into(videoplayer.thumbImageView);

        OrganizationFindBean.DataBean.InvitationBean invitation = organizationFindBean.getData().getInvitation();
        mInvitation_id = invitation.getInvitation_id();
        recruitTitleTv.setText(invitation.getInvitation_title());
        recruitTypeTv.setText(invitation.getInvitation_dance_type());

        List<OrganizationFindBean.DataBean.CurriculumBean> curriculum = organizationFindBean.getData().getCurriculum();

        mChildCourseAdapter = new CourseAdapter(R.layout.child_course_item, curriculum);
        courseChildRecyclerView.setAdapter(mChildCourseAdapter);


        List<OrganizationFindBean.DataBean.OrganizationTeacherBean> organization_teacher = organizationFindBean.getData().getOrganization_teacher();
        teacherRecyclerView.setAdapter(new TeacherAdapter(R.layout.teacher_item, organization_teacher, mContext));

        mList.clear();
        List<OrganizationFindBean.DataBean.VideosBean> videos = organizationFindBean.getData().getVideos();
        mList.addAll(videos);
        mVideoAdpater = new VideoAdpater(R.layout.video_item, mList, mContext);
        videoRecyclerView.setAdapter(mVideoAdpater);
    }

    private void setGroupDevice(OrganizationFindBean.DataBean.BasicBean basic) {
        List<String> strings = Arrays.asList(basic.getOrganization_facility().split("!=end=!"));

        Drawable rightDrawable = ContextCompat.getDrawable(this, R.drawable.icon_select_choice);

        Drawable errorDrawable = ContextCompat.getDrawable(this, R.drawable.icon_unselect_choice);

        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());

        errorDrawable.setBounds(0, 0, errorDrawable.getMinimumWidth(), errorDrawable.getMinimumHeight());

        wifiView.setCompoundDrawables(strings.contains("wifi") ? rightDrawable : errorDrawable, null, null, null);
        parkingView.setCompoundDrawables(strings.contains("停车场") ? rightDrawable : errorDrawable, null, null, null);
        wechatPayView.setCompoundDrawables(strings.contains("微信支付") ? rightDrawable : errorDrawable, null, null, null);
        aliPayView.setCompoundDrawables(strings.contains("支付宝支付") ? rightDrawable : errorDrawable, null, null, null);

    }

    private void exchangeFollowState() {
        followTv.setVisibility(View.VISIBLE);
        if (attention_state == 2) {
            followTv.setText("关注机构");
        } else if (attention_state == 1) {
            followTv.setText("取消关注");
        }
    }


    public static class CourseAdapter extends BaseQuickAdapter<OrganizationFindBean.DataBean.CurriculumBean, BaseViewHolder> {
        public CourseAdapter(int layoutResId, @Nullable List<OrganizationFindBean.DataBean.CurriculumBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, OrganizationFindBean.DataBean.CurriculumBean item) {
            ImageView headImg = helper.getView(R.id.head_img);
            headImg.setVisibility(View.GONE);
            helper.setText(R.id.courseNameTv, item.getCurriculum_name());
            long curriculum_start_time = item.getCurriculum_start_time();
            long curriculum_over_time = item.getCurriculum_over_time();
            helper.setText(R.id.adminTv, item.getCurriculum_admin() + "\u3000" + DateUtils.stampToDate(curriculum_start_time, "HH:mm") + "\u0020-\u0020" + DateUtils.stampToDate(curriculum_over_time, "HH:mm"));
            helper.setText(R.id.difficultyTv, item.getCurriculum_difficulty());
            helper.setText(R.id.priceTv, String.valueOf(item.getCurriculum_actual_price()));
        }
    }


    public static class TeacherAdapter extends BaseQuickAdapter<OrganizationFindBean.DataBean.OrganizationTeacherBean, BaseViewHolder> {
        private Context mContext;

        public TeacherAdapter(int layoutResId, @Nullable List<OrganizationFindBean.DataBean.OrganizationTeacherBean> data, Context context) {
            super(layoutResId, data);
            mContext = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, OrganizationFindBean.DataBean.OrganizationTeacherBean item) {
            ImageView headImg = helper.getView(R.id.head_img);
            RatingBar ratingBar = helper.getView(R.id.ratingBar);
            TextView scoreTv = helper.getView(R.id.score_tv);
            headImg.setVisibility(View.GONE);
            ratingBar.setVisibility(View.GONE);
            scoreTv.setVisibility(View.GONE);
            //            GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL+item.getTeacher_photo()).circleCrop().into(headImg);
            helper.setText(R.id.name_tv, item.getTeacher_name());
            helper.setText(R.id.classify_tv, item.getTeacher_master());
            //            int teacher_level = item.getTeacher_level();
            //            ratingBar.setStar(teacher_level);
            //            helper.setText(R.id.score_tv,String.valueOf(teacher_level));
            helper.setText(R.id.brief_tv, "简介：" + item.getTeacher_intro());
        }
    }


    public class VideoAdpater extends BaseQuickAdapter<OrganizationFindBean.DataBean.VideosBean, BaseViewHolder> {
        private Context mContext;

        public VideoAdpater(int layoutResId, @Nullable List<OrganizationFindBean.DataBean.VideosBean> data, Context context) {
            super(layoutResId, data);
            mContext = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, OrganizationFindBean.DataBean.VideosBean item) {
            helper.setText(R.id.name_tv, item.getFile_name());
            helper.setText(R.id.num_tv, String.valueOf(item.getFile_collection()));
            String file_cover = item.getFile_cover();
            if (!TextUtils.isEmpty(file_cover)) {
                ImageView coverImg = helper.getView(R.id.coverImg);
                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + file_cover).diskCacheStrategy(DiskCacheStrategy.NONE).into(coverImg);
            }
            String user_portrait = item.getOrganization_portrait();
            if (!TextUtils.isEmpty(user_portrait)) {
                ImageView headImg = helper.getView(R.id.head_img);
                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + user_portrait).circleCrop().into(headImg);
            }

            helper.setText(R.id.contentTv, item.getDance_type_name());
        }
    }
}
