package com.dazhukeji.douwu.ui.aty.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.adapter.RecruitHallAdapter;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.bean.home.invitation.InvitationListBean;
import com.dazhukeji.douwu.contract.home.invitation.InvitationContract;
import com.dazhukeji.douwu.manager.RecyclerViewManager;
import com.dazhukeji.douwu.presenter.home.invitation.InvitationPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/16 8:39
 * 功能描述：招聘大厅
 */
public class RecruitHallAty extends BaseAty<InvitationPresenter> implements InvitationContract.View {
    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.recruit_recyclerView)
    RecyclerView recruitRecyclerView;

    private RecyclerViewManager mRecyclerViewManager;
    private RecruitHallAdapter mRecruitHallAdapter;
    private int mOrganization_id;
    private Bundle mExtras;
    private String mDistrict_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recruit_hall;
    }

    @Override
    public void initView() {
        mExtras = getIntent().getExtras();
        if (mExtras != null) {
            mOrganization_id = mExtras.getInt("organization_id");
            mDistrict_id = mExtras.getString("district_id");
        }

        txtTitle.setText("招聘大厅");
        mRecyclerViewManager = new RecyclerViewManager(recruitRecyclerView);
        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.VERTICAL);

    }

    @Override
    public void initData() {
        ((InvitationPresenter) mPresenter).postInvitationList(mDistrict_id);
    }

    @Override
    public void refreshInvitation(InvitationListBean invitationListBean) {
        List<InvitationListBean.DataBean> data = invitationListBean.getData();
        if (data.size() > 0) {
            mRecruitHallAdapter = new RecruitHallAdapter(R.layout.recruit_item, data);
            recruitRecyclerView.setAdapter(mRecruitHallAdapter);
            mRecruitHallAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("invitation_id", String.valueOf(data.get(position).getInvitation_id()));
                    startActivity(RecruitDetailsAty.class,bundle);
                }
            });
        }

    }

    public static class RecruitHallAdapter extends BaseQuickAdapter<InvitationListBean.DataBean,BaseViewHolder> {


        public RecruitHallAdapter(int layoutResId, @Nullable List<InvitationListBean.DataBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, InvitationListBean.DataBean item) {
            helper.addOnClickListener(R.id.see_tv);
            helper.setText(R.id.recruitTitleTv,item.getOrganization_name());
            helper.setText(R.id.recruitTypeTv,item.getInvitation_explain());
        }

    }
}
