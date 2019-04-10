package com.dazhukeji.douwu.ui.aty.mine;

import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.manager.RecyclerViewManager;
import com.dazhukeji.douwu.view.MyEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建者：zhangyunfei
 * 时间：2019/1/7
 * 联系方式：32457127@qq.com
 * 功能描述：老师我的钱包
 */
public class TeacherWalletAty extends BaseAty {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.moneyTv)
    TextView moneyTv;
    @BindView(R.id.walletRecyclerView)
    RecyclerView walletRecyclerView;


    RecyclerViewManager mRecyclerViewManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_wallet;
    }

    @Override
    public void initView() {
        txtTitle.setText("我的钱包");
        mRecyclerViewManager = new RecyclerViewManager(walletRecyclerView);
        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.VERTICAL);
    }

    @Override
    public void initData() {
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        list.add(new Object());
        walletRecyclerView.setAdapter(new WalletAdapter(R.layout.item_wallet, list));
    }


    @OnClick({R.id.zhifubaoLayout, R.id.weixinLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhifubaoLayout:
                showPop(view);
                break;
            case R.id.weixinLayout:
                showPop(view);
                break;
        }
    }

    public static class WalletAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {
        public WalletAdapter(int layoutResId, @Nullable List<Object> data) {
            super(layoutResId, data);
        }


        @Override
        protected void convert(BaseViewHolder helper, Object item) {

        }
    }

    private void showPop(View v) {
        PopupWindow mPopupWindow = new PopupWindow();
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.setWidth(FrameLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(FrameLayout.LayoutParams.WRAP_CONTENT);
        View view = getLayoutInflater().inflate(R.layout.withdrawal_layout, null);
        MyEditText withdrawalEdit = view.findViewById(R.id.withdrawalEdit);
        TextView totalPriceTv = view.findViewById(R.id.totalPriceTv);
        TextView sureTv = view.findViewById(R.id.sureTv);
        withdrawalEdit.setText("¥ 131313");
        withdrawalEdit.setSelection(withdrawalEdit.getContent().length());
        totalPriceTv.setText("可用余额 411.94 元");
        switch (view.getId()) {
            case R.id.zhifubaoLayout:
                break;
            case R.id.weixinLayout:
                break;
        }
        sureTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mPopupWindow.setContentView(view);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);
            }
        });
    }
}
