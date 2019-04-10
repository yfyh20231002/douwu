package com.dazhukeji.douwu.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.R;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/15 16:05
 * 功能描述：
 */
public class TeacherAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {

    private boolean isShow=false;

    public TeacherAdapter(int layoutResId, @Nullable List<Object> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        RelativeLayout relativeLayout = helper.getView(R.id.editRelativeLayout);
        if (isShow){
            relativeLayout.setVisibility(View.VISIBLE);
        }else {
            relativeLayout.setVisibility(View.GONE);
        }
    }

    public void showEdit(boolean state){
        this.isShow = state;
        notifyDataSetChanged();
    }
}
