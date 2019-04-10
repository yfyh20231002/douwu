package com.dazhukeji.douwu.ui.aty.mine;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.base.BaseAty;
import com.zhangyunfei.mylibrary.utils.GlideApp;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/26 16:01
 * 功能描述：
 */
public class PurchasedCoursesDetailsAty extends BaseAty{
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.info_frameLayout)
    FrameLayout infoFrameLayout;

    @BindView(R.id.videoplayer)
    JzvdStd videoplayer;

    @Override
    public int getLayoutId() {
        return R.layout.activity_purchase_course_detail;
    }

    @Override
    public void initView() {
        txtTitle.setText("课程详情");
        infoFrameLayout.setVisibility(View.VISIBLE);

        videoplayer.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"
                , "", Jzvd.SCREEN_WINDOW_NORMAL);
        GlideApp.with(this).load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png").into(videoplayer.thumbImageView);
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.letterTv, R.id.trySeeTv, R.id.pay_linearLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.letterTv:
                break;
            case R.id.trySeeTv:
                break;
            case R.id.pay_linearLayout:
//                startActivity(PayDialogAty.class);
                break;
        }
    }
}
