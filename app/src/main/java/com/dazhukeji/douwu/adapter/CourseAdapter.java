package com.dazhukeji.douwu.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.manager.RecyclerViewManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/15 16:05
 * 功能描述：
 */
public class CourseAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {


    public CourseAdapter(int layoutResId, @Nullable List<Object> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        RecyclerView helperView = helper.getView(R.id.course_child_recyclerView);
        RecyclerViewManager recyclerViewManager=new RecyclerViewManager(helperView);
        recyclerViewManager.setLinearLayoutManager(RecyclerView.VERTICAL);
        List<Object> objectList=new ArrayList<>();
        objectList.add(new Object());
        objectList.add(new Object());
        helperView.setAdapter(new ChildCourseAdapter(R.layout.child_course_item,objectList));

    }

    public static class ChildCourseAdapter extends BaseQuickAdapter<Object,BaseViewHolder>{
        public ChildCourseAdapter(int layoutResId, @Nullable List<Object> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
//            TextView original_price_tv = helper.getView(R.id.original_price_tv);
//            SpannableString spannableString = new SpannableString("240");
//            StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
//            spannableString.setSpan(strikethroughSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//            original_price_tv.setText(spannableString);
        }
    }
}
