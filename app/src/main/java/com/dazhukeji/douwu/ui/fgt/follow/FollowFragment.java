package com.dazhukeji.douwu.ui.fgt.follow;

import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.MyLogger;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.base.BaseFgt;
import com.dazhukeji.douwu.bean.follow.UserAttentionOrganizationBean;
import com.dazhukeji.douwu.bean.follow.UserAttentionTeacherBean;
import com.dazhukeji.douwu.contract.follow.FollowContract;
import com.dazhukeji.douwu.manager.RecyclerViewManager;
import com.dazhukeji.douwu.presenter.follow.FollowPresenter;
import com.dazhukeji.douwu.ui.aty.home.DanceOrgDetailsAty;
import com.dazhukeji.douwu.ui.aty.home.TeacherDetailsAty;
import com.dazhukeji.douwu.view.RatingBar;
import com.dazhukeji.douwu.view.TipDialog;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.utils.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/14 11:47
 * 功能描述：关注
 */
public class FollowFragment extends BaseFgt<FollowPresenter> implements FollowContract.View {
    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.layout_bar)
    RelativeLayout layoutBar;
    @BindView(R.id.teacher_tv)
    TextView teacherTv;
    @BindView(R.id.mechanism_tv)
    TextView mechanismTv;
    @BindView(R.id.follow_teacher_recyclerView)
    RecyclerView follow_teacher_recyclerView;
    @BindView(R.id.follow_org_recyclerView)
    RecyclerView follow_org_recyclerView;
    private RecyclerViewManager mRecyclerViewManager;
    private int position = 0;
    private TeacherAdapter mTeacherAdapter;
    private DanceOrgAdapter mDanceOrgAdapter;
    private boolean isVisible;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_follow;
    }

    public void showBack(boolean isVisible) {
        this.isVisible = isVisible;
    }

    @Override
    protected void initialized(View view) {
        if (isVisible) {
            backImg.setVisibility(View.VISIBLE);
        } else {
            backImg.setVisibility(View.GONE);
        }
        txtTitle.setText("我的关注");
        setTeacherClickable();
    }

    private void setTeacherClickable() {
        position = 0;
        teacherTv.setEnabled(false);
        mechanismTv.setEnabled(true);
        setSelect();
    }

    private void setMechanismClickable() {
        position = 1;
        teacherTv.setEnabled(true);
        mechanismTv.setEnabled(false);
        setSelect();
    }

    @Override
    protected void requestData() {
        ((FollowPresenter) mPresenter).postUserAttentionTeacher(ApiConfig.getToken());
    }


    @OnClick({R.id.teacher_tv, R.id.mechanism_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.teacher_tv:
                setTeacherClickable();
                break;
            case R.id.mechanism_tv:
                setMechanismClickable();
                break;
        }
    }

    private void setSelect() {
        teacherTv.setBackground(null);
        mechanismTv.setBackground(null);
        if (0 == position) {
            follow_teacher_recyclerView.setVisibility(View.VISIBLE);
            follow_org_recyclerView.setVisibility(View.GONE);
            mRecyclerViewManager = new RecyclerViewManager(follow_teacher_recyclerView);
            mRecyclerViewManager.setLinearLayoutManager(RecyclerView.VERTICAL);
            teacherTv.setBackgroundResource(R.drawable.icon_title_bg);
            ((FollowPresenter) mPresenter).postUserAttentionTeacher(ApiConfig.getToken());
        } else if (1 == position) {
            follow_teacher_recyclerView.setVisibility(View.GONE);
            follow_org_recyclerView.setVisibility(View.VISIBLE);
            mRecyclerViewManager = new RecyclerViewManager(follow_org_recyclerView);
            mRecyclerViewManager.setLinearLayoutManager(RecyclerView.VERTICAL);
            mechanismTv.setBackgroundResource(R.drawable.icon_title_bg);
            ((FollowPresenter) mPresenter).postUserAttentionOrganization(ApiConfig.getToken());
        }

    }

    @Override
    public void refreshTeacher(UserAttentionTeacherBean bean) {
        List<UserAttentionTeacherBean.DataBean> data = bean.getData();
        if (data != null && data.size() > 0) {
            mTeacherAdapter = new TeacherAdapter(R.layout.teacher_item, data);
            follow_teacher_recyclerView.setAdapter(mTeacherAdapter);
            mTeacherAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("user_teacher_id", String.valueOf(data.get(position).getUser_teacher_id()));
                    startActivity(TeacherDetailsAty.class, bundle);
                }
            });
            mTeacherAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    if (view.getId() == R.id.cancelImg) {
                        new TipDialog.Builder(mContext)
                                .setTitle("是否删除已关注的老师")
                                .setMessage("被删除的老师将无法恢复")
                                .setCancelable(false)
                                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ((FollowPresenter) mPresenter).postCancelAttentionTeacher(ApiConfig.getToken(), String.valueOf(data.get(position).getUser_teacher_id()));
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
        } else {
            follow_teacher_recyclerView.setVisibility(View.GONE);
        }

    }

    @Override
    public void cancelTeacher() {
        setSelect();
    }

    @Override
    public void refreshOrganization(UserAttentionOrganizationBean bean) {
        List<UserAttentionOrganizationBean.DataBean> data = bean.getData();
        if (data != null && data.size() > 0) {
            mDanceOrgAdapter = new DanceOrgAdapter(R.layout.danceorg_item, data);
            follow_org_recyclerView.setAdapter(mDanceOrgAdapter);
            mDanceOrgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("organization_id", String.valueOf(data.get(position).getOrganization_id()));
                    startActivity(DanceOrgDetailsAty.class, bundle);
                }
            });
            mDanceOrgAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    if (view.getId() == R.id.cancelImg) {
                        new TipDialog.Builder(mContext)
                                .setTitle("是否删除已关注的机构")
                                .setMessage("被删除的机构将无法恢复")
                                .setCancelable(false)
                                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ((FollowPresenter) mPresenter).postCancelAttentionOrganization(ApiConfig.getToken(), String.valueOf(data.get(position).getOrganization_id()));
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
        } else {
            follow_org_recyclerView.setVisibility(View.GONE);
        }

    }

    @Override
    public void cancelOrganization() {
        setSelect();
    }


    public static class TeacherAdapter extends BaseQuickAdapter<UserAttentionTeacherBean.DataBean, BaseViewHolder> {

        public TeacherAdapter(int layoutResId, @Nullable List<UserAttentionTeacherBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, UserAttentionTeacherBean.DataBean item) {
            ImageView cancelImg = helper.getView(R.id.cancelImg);
            cancelImg.setVisibility(View.VISIBLE);
            helper.addOnClickListener(R.id.cancelImg);

            ImageView headImg = helper.getView(R.id.head_img);
            RatingBar ratingBar = helper.getView(R.id.ratingBar);
            String teacher_portrait = item.getTeacher_portrait();
            if (!TextUtils.isEmpty(teacher_portrait)) {
                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + teacher_portrait).circleCrop().into(headImg);
            }
            helper.setText(R.id.name_tv, item.getTeacher_name());
            helper.setText(R.id.classify_tv, item.getTeacher_master());
            int teacher_level = item.getTeacher_level();
            ratingBar.setStar(teacher_level);
            helper.setText(R.id.score_tv, String.valueOf(teacher_level));
            helper.setText(R.id.brief_tv, "简介：" + item.getTeacher_intro());
        }


    }


    public static class DanceOrgAdapter extends BaseQuickAdapter<UserAttentionOrganizationBean.DataBean, BaseViewHolder> {
        int itemW;
        int itemH;

        public DanceOrgAdapter(int layoutResId, @Nullable List<UserAttentionOrganizationBean.DataBean> data) {
            super(layoutResId, data);
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_dance_item_bg, options);
//
//            //获取图片的宽高
//            itemW = options.outHeight;
//            itemH = options.outWidth;
//
//            MyLogger.printStr(itemW + "," + itemH);
        }

        @Override
        protected void convert(BaseViewHolder helper, UserAttentionOrganizationBean.DataBean item) {
        /*@BindView(R.id.title_tv)
        TextView titleTv;
        @BindView(R.id.classify_tv)
        TextView classifyTv;
        @BindView(R.id.address_tv)
        TextView addressTv;
        @BindView(R.id.ratingBar)
        RatingBar ratingBar;
        @BindView(R.id.score_tv)
        TextView scoreTv;*/
            helper.setText(R.id.title_tv, item.getOrganization_name());
            helper.setText(R.id.classify_tv, item.getOrganization_type());
            helper.setText(R.id.address_tv, item.getOrganization_site());
            int organization_level = item.getOrganization_level();
            RatingBar ratingBar = helper.getView(R.id.ratingBar);
            ratingBar.setStar(organization_level);
            helper.setText(R.id.score_tv, String.valueOf(organization_level));
            ImageView cancelImg = helper.getView(R.id.cancelImg);
            cancelImg.setVisibility(View.VISIBLE);
            helper.addOnClickListener(R.id.cancelImg);
            GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + item.getOrganization_cover())
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super
                                Drawable> transition) {
                            helper.getView(R.id.daceorg_item).setBackground(resource);
                        }
                    });
        }
    }
}
