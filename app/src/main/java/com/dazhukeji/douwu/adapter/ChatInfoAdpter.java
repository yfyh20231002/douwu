package com.dazhukeji.douwu.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.R;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/28 11:24
 * 功能描述：
 */
public class ChatInfoAdpter extends BaseQuickAdapter<Object,BaseViewHolder> {

    public ChatInfoAdpter(int layoutResId, @Nullable List<Object> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        LinearLayout leftLinearLayout=helper.getView(R.id.leftLinearLayout);
        LinearLayout rightLinearLayout=helper.getView(R.id.rightLinearLayout);
        if (helper.getLayoutPosition()%2==0){
            leftLinearLayout.setVisibility(View.VISIBLE);
            rightLinearLayout.setVisibility(View.GONE);
        }else {
            leftLinearLayout.setVisibility(View.GONE);
            rightLinearLayout.setVisibility(View.VISIBLE);
        }

    }
}
