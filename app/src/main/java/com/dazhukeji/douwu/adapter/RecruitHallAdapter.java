package com.dazhukeji.douwu.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.R;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/15 16:05
 * 功能描述：
 */
public class RecruitHallAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {


    public RecruitHallAdapter(int layoutResId, @Nullable List<Object> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        helper.addOnClickListener(R.id.see_tv);

    }

}
