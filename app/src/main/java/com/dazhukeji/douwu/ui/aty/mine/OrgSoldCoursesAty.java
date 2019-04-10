package com.dazhukeji.douwu.ui.aty.mine;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.manager.RecyclerViewManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建者：zhangyunfei
 * 创建时间：2019/1/16 15:37
 * 功能描述：机构已售课程
 */
public class OrgSoldCoursesAty extends BaseAty {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.line4)
    View line4;

    private RecyclerViewManager mRecyclerViewManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sold_courses;
    }

    @Override
    public void initView() {
        txtTitle.setText("已售课程");
        mRecyclerViewManager = new RecyclerViewManager(mRecyclerView);
        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.VERTICAL);
        switchLine(0);
        List<Object> list =new ArrayList<>();
        list.add(new Object());
        list.add(new Object());
        mRecyclerView.setAdapter(new OrgSoldCourseAdapter(R.layout.item_sold_courses,list));
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.layout1, R.id.layout2, R.id.layout3, R.id.layout4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout1:
                switchLine(0);
                break;
            case R.id.layout2:
                switchLine(1);
                break;
            case R.id.layout3:
                switchLine(2);
                break;
            case R.id.layout4:
                switchLine(3);
                break;
        }
    }

    private void switchLine(int position){
        line1.setVisibility(View.INVISIBLE);
        line2.setVisibility(View.INVISIBLE);
        line3.setVisibility(View.INVISIBLE);
        line4.setVisibility(View.INVISIBLE);
        if (position== 0){
            line1.setVisibility(View.VISIBLE);
        }else if (position== 1){
            line2.setVisibility(View.VISIBLE);
        }else if (position== 2){
            line3.setVisibility(View.VISIBLE);
        }else if (position== 3){
            line4.setVisibility(View.VISIBLE);
        }
    }

    public class  OrgSoldCourseAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {
        public OrgSoldCourseAdapter(int layoutResId, @Nullable List<Object> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {

        }
    }
}
