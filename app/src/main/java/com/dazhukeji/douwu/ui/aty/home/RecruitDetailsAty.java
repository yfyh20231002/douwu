package com.dazhukeji.douwu.ui.aty.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.bean.home.invitation.InvitationFindBean;
import com.dazhukeji.douwu.contract.home.invitation.InvitationFindContract;
import com.dazhukeji.douwu.presenter.home.invitation.InvitationFindPresenter;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.utils.GlideApp;

import butterknife.BindView;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/16 9:48
 * 功能描述：招聘详情
 */
public class RecruitDetailsAty extends BaseAty<InvitationFindPresenter> implements InvitationFindContract.View {
    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.logo_img)
    ImageView logoImg;
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.manageNameTv)
    TextView manageNameTv;
    @BindView(R.id.addressTv)
    TextView addressTv;
    @BindView(R.id.phoneTv)
    TextView phoneTv;
    @BindView(R.id.timeTv)
    TextView timeTv;
    @BindView(R.id.typeTv)
    TextView typeTv;
    @BindView(R.id.ageTv)
    TextView ageTv;
    @BindView(R.id.demandTv)
    TextView demandTv;
    @BindView(R.id.logoImg1)
    ImageView logoImg1;
    @BindView(R.id.logoImg2)
    ImageView logoImg2;
//    @BindView(R.id.pic_recyclerView)
//    RecyclerView picRecyclerView;

    //    private RecyclerViewManager mRecyclerViewManager;
//    private PicAdapter mPicAdapter;
    private Bundle mExtras;
    private String mInvitation_id;
    private String mOrganization_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recruit_details;
    }

    @Override
    public void initView() {
        mExtras = getIntent().getExtras();
        if (mExtras != null) {
            mInvitation_id = mExtras.getString("invitation_id");
            mOrganization_id = mExtras.getString("organization_id");
        }
        txtTitle.setText("招聘详情");
//        mRecyclerViewManager = new RecyclerViewManager(picRecyclerView);
//        mRecyclerViewManager.setGridLayoutManager(2);
//        mPicAdapter = new PicAdapter(R.layout.pic_item, mList);
//        picRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                super.getItemOffsets(outRect, view, parent, state);
//                LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
//                if (parent.getChildAdapterPosition(view)!=(layoutManager.getItemCount()-1)){
//                    outRect.right= DisplayHelper.dp2px(mContext,13);
//                }
//            }
//        });
//        picRecyclerView.setAdapter(mPicAdapter);

    }

    @Override
    public void initData() {
        ((InvitationFindPresenter) mPresenter).postInvitationFind(mInvitation_id);
    }

    @Override
    public void refreshInvitationFind(InvitationFindBean invitationFindBean) {
        InvitationFindBean.DataBean data = invitationFindBean.getData();
        titleTv.setText(data.getOrganization_name());
        manageNameTv.setText(data.getInvitation_contact());
        addressTv.setText(data.getInvitation_interview_site());
        timeTv.setText(data.getInvitation_interview_time());
        typeTv.setText(data.getInvitation_dance_type());
        ageTv.setText(data.getInvitation_age_demand());
        demandTv.setText(data.getInvitation_explain());
        phoneTv.setText(data.getInvitation_phone());
        String picture = data.getInvitation_organization_picture();
        if (!TextUtils.isEmpty(picture)){
            GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL+picture).into(logoImg1);
        }
        String picture2 = data.getInvitation_organization_picture2();
        if (!TextUtils.isEmpty(picture2)){
            GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL+picture2).into(logoImg2);
        }
    }

//    public static class  PicAdapter extends BaseQuickAdapter<Object,BaseViewHolder>{
//
//        public PicAdapter(int layoutResId, @Nullable List<Object> data) {
//            super(layoutResId, data);
//        }
//
//        @Override
//        protected void convert(BaseViewHolder helper, Object item) {
//
//        }
//    }
}
