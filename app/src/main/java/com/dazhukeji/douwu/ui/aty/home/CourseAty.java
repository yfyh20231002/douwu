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
import com.dazhukeji.douwu.bean.home.courses.AllCoursesBean;
import com.dazhukeji.douwu.bean.publicBean.DanceTypeBean;
import com.dazhukeji.douwu.contract.DanceTypeContract;
import com.dazhukeji.douwu.contract.home.courses.AllCoursesContract;
import com.dazhukeji.douwu.manager.RecyclerViewManager;
import com.dazhukeji.douwu.presenter.DanceTypePresenter;
import com.dazhukeji.douwu.presenter.home.courses.AllCoursesPresenter;
import com.dazhukeji.douwu.view.MyEditText;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.utils.DateUtils;
import com.zhangyunfei.mylibrary.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/15 16:36
 * 功能描述：课程列表
 */
public class CourseAty extends BaseAty<AllCoursesPresenter> implements AllCoursesContract.View, DanceTypeContract.View {

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
    @BindView(R.id.course_recyclerView)
    RecyclerView courseRecyclerView;

    private RecyclerViewManager mRecyclerViewManager;
    private CourseAdapter mCourseAdapter;
    private TitlesAdapter mTitlesAdapter;
    private int paging = 1;
    private String mCalendarDate;

    private DanceTypePresenter mDanceTypePresenter;

    private List<AllCoursesBean.DataBean.CurriculumBean> mCurriculumBeanList = new ArrayList<>();

    private String mDance_type_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_course;
    }

    @Override
    public void initView() {
        mCalendarDate = getIntent().getStringExtra("calendarDate");
        txtTitle.setText("课程列表");
        searchContentTv.setHint("店名、舞种、拼音");

        mRecyclerViewManager = new RecyclerViewManager(titlesRecyclerView);
        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.HORIZONTAL);

        mRecyclerViewManager = new RecyclerViewManager(courseRecyclerView);
        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.VERTICAL);

        courseRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mCourseAdapter.getItemCount()) {
                    paging++;
                    requestData();
                }
            }
        });
    }


    @OnClick({ R.id.search_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_tv:
                requestData();
                break;
        }
    }

    @Override
    public void initData() {
        mDanceTypePresenter = new DanceTypePresenter();
        mDanceTypePresenter.attachView(this, mContext);
        mDanceTypePresenter.postDanceTypeSelect();

    }

    private void requestData(){
        ((AllCoursesPresenter) mPresenter).postAllCourses(searchContentTv.getContent(), String.valueOf(paging), mCalendarDate,mDance_type_id);
    }

    @Override
    public void refreshAllCourses(AllCoursesBean allCoursesBean) {
        List<AllCoursesBean.DataBean.CurriculumBean> curriculum = allCoursesBean.getData().getCurriculum();
        if (null != curriculum){
            if (paging == 1) {
                mCurriculumBeanList.clear();
            }
            mCurriculumBeanList.addAll(curriculum);
            mCourseAdapter = new CourseAdapter(R.layout.course_item, mCurriculumBeanList, mContext);
            courseRecyclerView.setAdapter(mCourseAdapter);
            mCourseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("curriculum_id", String.valueOf(curriculum.get(position).getCurriculum_id()));
                    startActivity(CourseDetailsAty.class, bundle);
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
            mDance_type_id = String.valueOf(dance_type.get(0).getDance_type_id());
            requestData();
            mTitlesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    mTitlesAdapter.setSelectPosition(position);
                    mDance_type_id = String.valueOf(dance_type.get(position).getDance_type_id());
                    requestData();
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDanceTypePresenter != null){
            mDanceTypePresenter.detachView();
        }
    }

    public class CourseAdapter extends BaseQuickAdapter<AllCoursesBean.DataBean.CurriculumBean, BaseViewHolder> {
        private Context mContext;

        public CourseAdapter(int layoutResId, @Nullable List<AllCoursesBean.DataBean.CurriculumBean> data, Context context) {
            super(layoutResId, data);
            mContext = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, AllCoursesBean.DataBean.CurriculumBean item) {
            ImageView headImg = helper.getView(R.id.head_img);
            GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + item.getCurriculum_photo()).circleCrop().into(headImg);
            helper.setText(R.id.courseNameTv, item.getCurriculum_name());
            long curriculum_start_time = item.getCurriculum_start_time()*1000;
            long curriculum_over_time = item.getCurriculum_over_time()*1000;
            helper.setText(R.id.adminTv, item.getCurriculum_admin() + "\u3000" + DateUtils.stampToDate(curriculum_start_time, "yyyy年MM月dd日") + "\u0020-\u0020" + DateUtils.stampToDate(curriculum_over_time, "yyyy年MM月dd日"));
            helper.setText(R.id.difficultyTv, item.getCurriculum_difficulty());
            helper.setText(R.id.priceTv, String.valueOf(item.getCurriculum_actual_price()));
        }
    }
}
