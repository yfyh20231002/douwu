package com.dazhukeji.douwu.ui.aty.home;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.base.BaseAty;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/22 9:26
 * 功能描述：
 */
public class PayDialogAty extends BaseAty {
    @BindView(R.id.priceTv)
    TextView priceTv;
    @BindView(R.id.head_img)
    ImageView headImg;
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.contentTv)
    TextView contentTv;
    @BindView(R.id.difficultyTv)
    TextView difficultyTv;
    @BindView(R.id.voucherTv)
    TextView voucherTv;
    @BindView(R.id.payTv)
    TextView payTv;
    @BindView(R.id.rootLayout)
    FrameLayout rootLayout;
    @BindView(R.id.wechat_img)
    ImageView wechatImg;
    @BindView(R.id.alipay_img)
    ImageView alipayImg;

    /**
     * 0 微信
     * 1 支付宝
     */
    private int type;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    public void initView() {
        setSelect();
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.voucherTv, R.id.wechat_img, R.id.alipay_img, R.id.payTv, R.id.rootLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.voucherTv:
                break;
            case R.id.wechat_img:
                type = 0;
                setSelect();
                break;
            case R.id.alipay_img:
                type = 1;
                setSelect();
                break;
            case R.id.payTv:
                break;
            case R.id.rootLayout:
                finish();
                break;
        }
    }

    private void setSelect() {
        wechatImg.setImageResource(R.drawable.icon_pay_unselect);
        alipayImg.setImageResource(R.drawable.icon_pay_unselect);
        if (0 == type) {
            wechatImg.setImageResource(R.drawable.icon_pay_select);
        } else {
            alipayImg.setImageResource(R.drawable.icon_pay_select);
        }
    }
}
