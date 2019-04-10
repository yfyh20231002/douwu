package com.dazhukeji.douwu.ui.aty.home;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.loader.BannerLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/19 14:48
 * 功能描述：
 */
public class NoticeDetailsAty extends BaseAty {
    @BindView(R.id.banner)
    Banner mBanner;
    private List<Integer> images;
    @Override
    public int getLayoutId() {
        return R.layout.activity_notice_details;
    }

    @Override
    public void initView() {
        images = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            //            images.add("http://e.hiphotos.baidu.com/image/pic/item/c83d70cf3bc79f3dd43c5964b7a1cd11738b2980.jpg");
            images.add(R.drawable.icon_douwu_bg);
        }
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new BannerLoader());
        //设置图片集合
        mBanner.setImages(images);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }
    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }

    @Override
    public void initData() {

    }
}
