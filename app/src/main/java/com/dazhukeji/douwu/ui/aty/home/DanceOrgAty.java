package com.dazhukeji.douwu.ui.aty.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dazhukeji.douwu.MyLogger;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.adapter.DanceOrgAdapter;
import com.dazhukeji.douwu.adapter.TitlesAdapter;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.bean.home.organization.OrganizationBean;
import com.dazhukeji.douwu.bean.home.organization.OrganizationListBean;
import com.dazhukeji.douwu.bean.publicBean.DanceTypeBean;
import com.dazhukeji.douwu.contract.DanceTypeContract;
import com.dazhukeji.douwu.contract.home.organization.OrganizationContract;
import com.dazhukeji.douwu.manager.RecyclerViewManager;
import com.dazhukeji.douwu.presenter.DanceTypePresenter;
import com.dazhukeji.douwu.presenter.home.organization.OrganizationPresenter;
import com.dazhukeji.douwu.view.MyEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/15 13:28
 * 功能描述：舞蹈机构
 */
public class DanceOrgAty extends BaseAty<OrganizationPresenter> implements OrganizationContract.View, DanceTypeContract.View {
    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.layout_bar)
    RelativeLayout layoutBar;
    @BindView(R.id.titles_recyclerView)
    RecyclerView titlesRecyclerView;
    @BindView(R.id.search_content_tv)
    MyEditText searchContentTv;
    @BindView(R.id.search_tv)
    TextView searchTv;
    @BindView(R.id.dance_recyclerView)
    RecyclerView danceRecyclerView;
    @BindView(R.id.info_frameLayout)
    FrameLayout infoFrameLayout;

    private List<DanceTypeBean.DataBean> titleList = new ArrayList<>();
    private RecyclerViewManager mRecyclerViewManager;
    private List<OrganizationBean> mList;
    private DanceOrgAdapter mDanceOrgAdapter;
    private TitlesAdapter mTitlesAdapter;
    private String mDance_type_id;
    private String mDistrict_id;
    private Bundle mExtras;

    private DanceTypePresenter mDanceTypePresenter;
    private String mDance_type_name;

    private int p = 1;

    public static void getInstance(Context context, String title, String dance_type_id, String district_id) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("dance_type_id", dance_type_id);
        bundle.putString("district_id", district_id);
        Intent intent = new Intent();
        intent.setClass(context, DanceOrgAty.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dance_organization;
    }

    @Override
    public void initView() {
        mExtras = getIntent().getExtras();
        if (mExtras != null) {
            String title = mExtras.getString("title");
            mDance_type_id = mExtras.getString("dance_type_id");
            mDance_type_name = mExtras.getString("dance_type_name");
            mDistrict_id = mExtras.getString("district_id");
            txtTitle.setText(title);
        }
        mRecyclerViewManager = new RecyclerViewManager(danceRecyclerView);
        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.VERTICAL);
    }

    @Override
    public void initData() {
        mList = new ArrayList<>();
        ((OrganizationPresenter) mPresenter).postOrganizationList(mDistrict_id, searchContentTv.getContent(), p, mDance_type_name, mDance_type_id);
        mDanceTypePresenter = new DanceTypePresenter();
        mDanceTypePresenter.attachView(this, mContext);
        mDanceTypePresenter.postDanceTypeSelect();
    }


    @Override
    public void refresh(OrganizationListBean organizationListBean) {
        List<OrganizationBean> organization = organizationListBean.getData().getOrganization();
        MyLogger.printStr("p=" + p);
        if (p == 1) {
            mList.clear();
        }
        mList.addAll(organization);
        mDanceOrgAdapter = new DanceOrgAdapter(mContext, R.layout.danceorg_item, mList);
        danceRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mDanceOrgAdapter.getItemCount()) {
                    p++;
                    ((OrganizationPresenter) mPresenter).postOrganizationList(mDistrict_id, searchContentTv.getContent(), p, mDance_type_name, mDance_type_id);
                }
            }
        });
        danceRecyclerView.setAdapter(mDanceOrgAdapter);
        mDanceOrgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("organization_id", String.valueOf(mList.get(position).getOrganization_id()));
                startActivity(DanceOrgDetailsAty.class, bundle);
            }
        });

    }

    @Override
    public void danceTypeSuccess(DanceTypeBean danceTypeBean) {
        mRecyclerViewManager = new RecyclerViewManager(titlesRecyclerView);
        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.HORIZONTAL);
        if (null != titleList && titleList.size()>0){
            titleList.clear();
        }
        titleList.addAll(danceTypeBean.getData());
        mTitlesAdapter = new TitlesAdapter(R.layout.home_title_item, titleList);
        titlesRecyclerView.setAdapter(mTitlesAdapter);
        mTitlesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                try {
                    DanceTypeBean.DataBean dataBean = titleList.get(position);
                    mDance_type_id = String.valueOf(dataBean.getDance_type_id());
                    mDance_type_name = dataBean.getDance_type_name();
                    ((OrganizationPresenter) mPresenter).postOrganizationList(mDistrict_id, searchContentTv.getContent(), p, mDance_type_name, mDance_type_id);
                } catch (Exception e) {
                    Toast.makeText(mContext, getString(R.string.data_error), Toast.LENGTH_SHORT).show();
                }
                mTitlesAdapter.setSelectPosition(position);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDanceTypePresenter != null) {
            mDanceTypePresenter.detachView();
        }
    }


    @OnClick(R.id.search_tv)
    public void searchView(View view) {
        ((OrganizationPresenter) mPresenter).postOrganizationList(mDistrict_id, searchContentTv.getContent(), p, mDance_type_name, mDance_type_id);
    }
}
