package com.dazhukeji.douwu.ui.aty.home;

import android.widget.ImageView;
import android.widget.TextView;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.loader.BannerLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.GlideApp;
import com.zhangyunfei.mylibrary.utils.JSONUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/19 14:48
 * 功能描述：
 */
public class NoticeDetailsAty extends BaseAty {
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.logoPic)
    ImageView logoPic;
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.timeTv)
    TextView timeTv;
    @BindView(R.id.phoneTv)
    TextView phoneTv;
    @BindView(R.id.addressTv)
    TextView addressTv;
    @BindView(R.id.typeTv)
    TextView typeTv;
    @BindView(R.id.introduceTv)
    TextView introduceTv;
    @BindView(R.id.hezuoPic)
    ImageView hezuoPic;
    private List<String> images;

    @Override
    public int getLayoutId() {
        return R.layout.activity_notice_details;
    }

    @Override
    public void initView() {
        images = new ArrayList<>();
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new BannerLoader());

        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);

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
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Observable<ResponseBody> observable = apiService.postPlatform();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Map<String, String> map = Config.getMap(responseBody);
                        if (Integer.parseInt(map.get("code")) == 1) {
                            Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                            ArrayList<Map<String, String>> banner = JSONUtils.parseKeyAndValueToMapList(data.get("banner"));
                            if (null != banner && banner.size() > 0) {
                                for (Map<String, String> stringMap : banner) {
                                    images.add(ApiConfig.BASE_IMG_URL + stringMap.get("banner_content"));
                                }
                                //设置图片集合
                                mBanner.setImages(images);
                                //banner设置方法全部调用完毕时最后调用
                                mBanner.start();
                            }
                            Map<String, String> information = JSONUtils.parseKeyAndValueToMap(data.get("information"));
                            if (null != information) {
                                //GlideApp.with(NoticeDetailsAty.this).load(ApiConfig.BASE_IMG_URL + information.get("pic")).into(logoPic);
                                titleTv.setText(information.get("name"));
                                timeTv.setText(information.get("time"));
                                phoneTv.setText(information.get("phone"));
                                addressTv.setText(information.get("site"));
                                typeTv.setText(information.get("dance_type"));
                                introduceTv.setText(information.get("introduce"));
                                //GlideApp.with(NoticeDetailsAty.this).load(ApiConfig.BASE_IMG_URL + information.get("hezuo_pic")).into(hezuoPic);
                                GlideApp.with(NoticeDetailsAty.this).load(ApiConfig.BASE_IMG_URL + information.get("pic"))
                                        .centerCrop()
                                        .into(hezuoPic);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
