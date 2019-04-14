package com.dazhukeji.douwu.ui.aty.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.adapter.TitlesAdapter;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.bean.home.organization.OrganizationTeacherBean;
import com.dazhukeji.douwu.bean.home.teacher.TeacherListBean;
import com.dazhukeji.douwu.bean.publicBean.DanceTypeBean;
import com.dazhukeji.douwu.contract.DanceTypeContract;
import com.dazhukeji.douwu.contract.home.organization.OrganizationTeacherContract;
import com.dazhukeji.douwu.contract.home.teacher.TeacherListContract;
import com.dazhukeji.douwu.manager.RecyclerViewManager;
import com.dazhukeji.douwu.presenter.DanceTypePresenter;
import com.dazhukeji.douwu.presenter.home.organization.OrganizationTeacherPresenter;
import com.dazhukeji.douwu.presenter.home.teacher.TeacherListPresenter;
import com.dazhukeji.douwu.view.MyEditText;
import com.dazhukeji.douwu.view.RatingBar;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/15 14:29
 * 功能描述：老师列表
 */
public class TeacherAty extends BaseAty implements OrganizationTeacherContract.View, TeacherListContract.View, DanceTypeContract.View {
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
    @BindView(R.id.teacher_recyclerView)
    RecyclerView teacherRecyclerView;
    private DanceTypePresenter mDanceTypePresenter;
    private RecyclerViewManager mRecyclerViewManager;
    private TeacherAdapter mTeacherAdapter;
    private TeacherListAdapter mTeacherListAdapter;
    private TitlesAdapter mTitlesAdapter;
    private OrganizationTeacherPresenter mOrganizationTeacherPresenter;
    private TeacherListPresenter mTeacherListPresenter;
    private int mOrganization_id;
    private Bundle mExtras;
    private String mDistrict_id;
    private String mDance_type_name;
    private String mDance_type_id;


    private int p = 1;

    private List<TeacherListBean.DataBean.TeacherBean> mTeacherBeanList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_teacher;
    }

    @Override
    public void initView() {
        mExtras = getIntent().getExtras();
        if (mExtras != null) {
            mOrganization_id = mExtras.getInt("organization_id");
            mDistrict_id = mExtras.getString("district_id");
        }
        txtTitle.setText("老师列表");

        mRecyclerViewManager = new RecyclerViewManager(titlesRecyclerView);
        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.HORIZONTAL);


        mRecyclerViewManager = new RecyclerViewManager(teacherRecyclerView);
        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.VERTICAL);

        mDanceTypePresenter = new DanceTypePresenter();
        mDanceTypePresenter.attachView(this, mContext);
        mDanceTypePresenter.postDanceTypeSelect();
    }

    @Override
    public void initData() {
    }

    private void requestData() {
        if (mDistrict_id == null) {
            mOrganizationTeacherPresenter = new OrganizationTeacherPresenter();
            mOrganizationTeacherPresenter.attachView(this, this);
            mOrganizationTeacherPresenter.postOrganizationTeacher(String.valueOf(mOrganization_id));
        } else {
            mTeacherListPresenter = new TeacherListPresenter();
            mTeacherListPresenter.attachView(this, this);
            mTeacherListPresenter.postTeacherList(mDistrict_id, searchContentTv.getContent(), p, mDance_type_name, mDance_type_id);
        }
    }

    @Override
    public void refresh(OrganizationTeacherBean bean) {
        List<OrganizationTeacherBean.DataBean> data = bean.getData();
        if (data.size() > 0) {
            mTeacherAdapter = new TeacherAdapter(R.layout.teacher_item, data, mContext);
            teacherRecyclerView.setAdapter(mTeacherAdapter);
            mTeacherAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("user_teacher_id", String.valueOf(data.get(position).getOrganization_teacher_id()));
                    startActivity(TeacherDetailsAty.class, bundle);
                }
            });
        }
    }

    @Override
    public void refreshTeacherList(TeacherListBean teacherListBean) {
        TeacherListBean.DataBean data = teacherListBean.getData();

        List<TeacherListBean.DataBean.TeacherBean> teacher = data.getTeacher();
        if (p == 1) {
            mTeacherBeanList.clear();
        }
        if (teacher != null) {
            mTeacherBeanList.addAll(teacher);
        }
        if (mTeacherBeanList.size() >= 0) {
            mTeacherListAdapter = new TeacherListAdapter(R.layout.teacher_item, mTeacherBeanList, mContext);
            teacherRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mTeacherAdapter.getItemCount()) {
                        p++;
                        requestData();
                    }
                }
            });
            teacherRecyclerView.setAdapter(mTeacherListAdapter);
            mTeacherListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("user_teacher_id", String.valueOf(teacher.get(position).getUser_teacher_id()));
                    startActivity(TeacherDetailsAty.class, bundle);
                }
            });
        }
    }

    @Override
    public void danceTypeSuccess(DanceTypeBean danceTypeBean) {
        List<DanceTypeBean.DataBean> dance_type = danceTypeBean.getData();
        if (dance_type.size() > 0) {
            mTitlesAdapter = new TitlesAdapter(R.layout.home_title_item, dance_type);
            titlesRecyclerView.setAdapter(mTitlesAdapter);
            mDance_type_name = dance_type.get(0).getDance_type_name();
            mDance_type_id = String.valueOf(dance_type.get(0).getDance_type_id());
            requestData();
            mTitlesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    mTitlesAdapter.setSelectPosition(position);
                    mDance_type_name = dance_type.get(position).getDance_type_name();
                    mDance_type_id = String.valueOf(dance_type.get(position).getDance_type_id());
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


    @OnClick({ R.id.search_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_tv:
                requestData();
                break;
        }
    }


    public static class TeacherAdapter extends BaseQuickAdapter<OrganizationTeacherBean.DataBean, BaseViewHolder> {
        private Context mContext;

        public TeacherAdapter(int layoutResId, @Nullable List<OrganizationTeacherBean.DataBean> data, Context context) {
            super(layoutResId, data);
            mContext = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, OrganizationTeacherBean.DataBean item) {
            ImageView headImg = helper.getView(R.id.head_img);
            RatingBar ratingBar = helper.getView(R.id.ratingBar);
            GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + item.getTeacher_photo()).circleCrop().into(headImg);
            helper.setText(R.id.name_tv, item.getTeacher_name());
            helper.setText(R.id.classify_tv, item.getTeacher_master());
            int teacher_level = item.getTeacher_level();
            ratingBar.setStar(teacher_level);
            helper.setText(R.id.score_tv, String.valueOf(teacher_level));
            helper.setText(R.id.brief_tv, "简介：" + item.getTeacher_intro());
        }
    }


    public static class TeacherListAdapter extends BaseQuickAdapter<TeacherListBean.DataBean.TeacherBean, BaseViewHolder> {
        private Context mContext;

        public TeacherListAdapter(int layoutResId, @Nullable List<TeacherListBean.DataBean.TeacherBean> data, Context context) {
            super(layoutResId, data);
            mContext = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, TeacherListBean.DataBean.TeacherBean item) {
            ImageView headImg = helper.getView(R.id.head_img);
            RatingBar ratingBar = helper.getView(R.id.ratingBar);
            GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + item.getTeacher_portrait()).circleCrop().into(headImg);
            helper.setText(R.id.name_tv, item.getTeacher_name());
            helper.setText(R.id.classify_tv, item.getTeacher_master());
            int teacher_level = item.getTeacher_level();
            ratingBar.setStar(teacher_level);
            helper.setText(R.id.score_tv, String.valueOf(teacher_level));
            helper.setText(R.id.brief_tv, "简介：" + item.getTeacher_intro());
        }
    }

}
