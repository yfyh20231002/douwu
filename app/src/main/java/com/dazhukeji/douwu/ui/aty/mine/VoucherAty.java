package com.dazhukeji.douwu.ui.aty.mine;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.base.BaseAty;

import butterknife.BindView;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/26 16:08
 * 功能描述：优惠券
 */
public class VoucherAty extends BaseAty {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.voucher_recyclerView)
    RecyclerView voucherRecyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_voucher;
    }

    @Override
    public void initView() {
        txtTitle.setText("优惠券");
    }

    @Override
    public void initData() {

    }

}
