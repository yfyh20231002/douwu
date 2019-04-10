package com.dazhukeji.douwu.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.bean.publicBean.DanceTypeBean;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 时间：2018/11/14 0014
 * 联系方式：32457127@qq.com
 */
public class TitlesAdapter extends BaseQuickAdapter<DanceTypeBean.DataBean,BaseViewHolder> {

    private int selectPosition=0;
    public TitlesAdapter(int layoutResId, @Nullable List<DanceTypeBean.DataBean> data) {
        super(layoutResId, data);
    }


    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, DanceTypeBean.DataBean item) {
        helper.setText(R.id.home_title_tv,item.getDance_type_name());
        if (helper.getLayoutPosition()==selectPosition){
            helper.setTextColor(R.id.home_title_tv,mContext.getResources().getColor((R.color.title_select_color)));
        }else {
            helper.setTextColor(R.id.home_title_tv,mContext.getResources().getColor((R.color.white)));
        }
    }
}
