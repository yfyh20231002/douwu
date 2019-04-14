package com.dazhukeji.douwu.ui.fgt.home;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dazhukeji.douwu.MyLogger;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.adapter.HomeClassifyAdapter;
import com.dazhukeji.douwu.adapter.TitlesAdapter;
import com.dazhukeji.douwu.adapter.VideoAdpater;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.api.Constant;
import com.dazhukeji.douwu.api.OnItemClickListener;
import com.dazhukeji.douwu.base.BaseFgt;
import com.dazhukeji.douwu.bean.home.HomeIndexBean;
import com.dazhukeji.douwu.bean.publicBean.DanceTypeBean;
import com.dazhukeji.douwu.bean.publicBean.DistrictBean;
import com.dazhukeji.douwu.bean.publicBean.VideoBean;
import com.dazhukeji.douwu.loader.BannerLoader;
import com.dazhukeji.douwu.manager.RecyclerViewManager;
import com.dazhukeji.douwu.presenter.home.HomePresenter;
import com.dazhukeji.douwu.ui.aty.home.CalendarAty;
import com.dazhukeji.douwu.ui.aty.home.DanceOrgAty;
import com.dazhukeji.douwu.ui.aty.home.NoticeDetailsAty;
import com.dazhukeji.douwu.ui.aty.home.RecruitHallAty;
import com.dazhukeji.douwu.ui.aty.home.TeacherAty;
import com.dazhukeji.douwu.ui.aty.home.VideoAty;
import com.dazhukeji.douwu.ui.aty.home.VideoDetailsAty;
import com.dazhukeji.douwu.ui.aty.mine.MemberChatAty;
import com.dazhukeji.douwu.ui.aty.mine.PublishVideoAty;
import com.dazhukeji.douwu.view.MyEditText;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.DisplayHelper;
import com.zhangyunfei.mylibrary.utils.GlideApp;
import com.zhangyunfei.mylibrary.utils.JSONUtils;
import com.zhangyunfei.mylibrary.utils.SoftKeyboardUtil;
import com.zhangyunfei.mylibrary.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 首页
 *
 * @author Administrator
 */
public class HomeFragment extends BaseFgt<HomePresenter> {
    @BindView(R.id.titles_recyclerView)
    RecyclerView titlesRecyclerView;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.classify_recyclerView)
    RecyclerView classifyRecyclerView;
    @BindView(R.id.video_recyclerView)
    RecyclerView video_recyclerView;
    @BindView(R.id.select_tv)
    TextView selectTv;
    @BindView(R.id.pop_relativeLayout)
    RelativeLayout popRelativeLayout;
    @BindView(R.id.location_tv)
    TextView locationTv;
    @BindView(R.id.notice_img)
    ImageView noticeImg;
    @BindView(R.id.video_img)
    ImageView videoImg;
    @BindView(R.id.pic_img)
    ImageView picImg;
    @BindView(R.id.lewu_img)
    ImageView lewuImg;
    @BindView(R.id.music_img)
    ImageView musicImg;
    @BindView(R.id.searchEdit)
    MyEditText searchEdit;

    private RecyclerViewManager mRecyclerViewManager;

    private List<DanceTypeBean.DataBean> titleList = new ArrayList<>();
    private List<String> imagesList = new ArrayList<>();
    private List<VideoBean> mVideoBeanList = new ArrayList<>();
    private List<DistrictBean> mDistrictBeanList=new ArrayList<>();


    private HomeClassifyAdapter mClassifyAdapter;
    private PopupWindow mPopupWindow;
    private VideoAdpater mVideoAdpater;
    private TitlesAdapter mTitlesAdapter;

    private List<View> editList = new ArrayList<>();

    private String dance_type_id = "1";
    private String district_id = "";
    private int order = 1;
    private int p = 1;

    //    private DanceTypePresenter mDanceTypePresenter;
    private String mDance_type_name;
    private Map<String, String> mCurrentRegion;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initialized(View view) {
        mRecyclerViewManager = new RecyclerViewManager(titlesRecyclerView);
        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.HORIZONTAL);

        mRecyclerViewManager = new RecyclerViewManager(classifyRecyclerView);
        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.HORIZONTAL);
        mClassifyAdapter = new HomeClassifyAdapter();
        classifyRecyclerView.setAdapter(mClassifyAdapter);
        mClassifyAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClickListener(int position) {
                Bundle bundle = new Bundle();
                switch (position) {
                    case 0:
                        DanceOrgAty.getInstance(mContext, Constant.CLASSIFY_TITLES[position], String.valueOf(dance_type_id), String.valueOf(district_id));
                        break;
                    case 1:
                        bundle.putString("district_id", String.valueOf(district_id));
                        startActivity(TeacherAty.class, bundle);
                        break;
                    case 2:
                        startActivity(CalendarAty.class);
                        break;
                    case 3:
                        bundle.putString("district_id", String.valueOf(district_id));
                        bundle.putString("dance_type_id", String.valueOf(dance_type_id));
                        bundle.putString("dance_type_name", mDance_type_name);
                        startActivity(VideoAty.class, bundle);
                        break;
                    case 4:
                        bundle.putString("district_id", String.valueOf(district_id));
                        startActivity(RecruitHallAty.class, bundle);
                        break;
                }
            }
        });


        mRecyclerViewManager = new RecyclerViewManager(video_recyclerView);
        mRecyclerViewManager.setGridLayoutManager(2);
        video_recyclerView.setNestedScrollingEnabled(false);
        video_recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildAdapterPosition(view) % 2 == 0) {
                    outRect.right = 10;
                }
            }
        });
        video_recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mVideoAdpater.getItemCount()) {
                    p++;
//                    ((HomePresenter) mPresenter).postIndexPaging(dance_type_id, district_id, order, p);
                    requestData();
                }
            }
        });


        editList.add(searchEdit);
        searchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    SoftKeyboardUtil.hideSoftKeyboard(mContext, editList);
                    requestData();
                    return true;
                }
                return false;
            }
        });
//        mDanceTypePresenter = new DanceTypePresenter();
//        mDanceTypePresenter.attachView(this,mContext);
//        mDanceTypePresenter.postDanceTypeSelect();
        chooseCity();
    }

    private void chooseCity() {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Observable<ResponseBody> observable = apiService.postArea();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Map<String, String> map = Config.getMap(responseBody);
                        ArrayList<Map<String, String>> arrayList = JSONUtils.parseKeyAndValueToMapList(map.get("data"));
                        if (arrayList != null && arrayList.size() > 0) {
                            for (int i = 0; i < arrayList.size(); i++) {
                                DistrictBean districtBean = new DistrictBean();
                                districtBean.setDistrict_id(Integer.parseInt(arrayList.get(i).get("district_id")));
                                districtBean.setDistrict_name(arrayList.get(i).get("district_name"));
                                mDistrictBeanList.add(districtBean);
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

    @Override
    protected void requestData() {
//        ((HomePresenter) mPresenter).postHome(dance_type_id, district_id, order,searchEdit.getContent());
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("dance_type_id", dance_type_id);
        requestMap.put("district_id", district_id);
        requestMap.put("order", String.valueOf(order));
        requestMap.put("seek", searchEdit.getContent());
        requestMap.put("paging", String.valueOf(p));
        Observable<ResponseBody> homeBeanObservable = apiService.postHome(requestMap);
        homeBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Map<String, String> map = Config.getMap(responseBody);
                        String code = map.get("code");
                        if (code.equals("1")) {
                            refresh(JSONUtils.parseKeyAndValueToMap(map.get("data")));
                        } else {
                            ToastUtils.showToast(map.get("msg"));
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
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

    @OnClick({R.id.location_tv, R.id.infoImg, R.id.pop_relativeLayout, R.id.notice_img, R.id.video_img, R.id.pic_img, R.id.lewu_img, R.id.music_img})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.location_tv:
                if (mDistrictBeanList.size()>0){
                    setCity();
                }
                break;
            case R.id.infoImg:
                startActivity(MemberChatAty.class);
                break;
            case R.id.pop_relativeLayout:
                setPop();
                break;
            case R.id.notice_img:
                startActivity(NoticeDetailsAty.class);
                break;
            case R.id.video_img:
                setPic();
                bundle.putString("type", "video");
                bundle.putString("from", "home");
                startActivity(PublishVideoAty.class, bundle);
                break;
            case R.id.pic_img:
                setPic();
                bundle.putString("type", "image");
                bundle.putString("from", "home");
                startActivity(PublishVideoAty.class, bundle);
                break;
            case R.id.lewu_img:
                setPic();
                break;
            case R.id.music_img:
                setPic();
                bundle.putString("type", "music");
                bundle.putString("from", "home");
                startActivity(PublishVideoAty.class, bundle);
                break;
        }
    }

    private void setPic() {
        if (videoImg.getVisibility() == View.VISIBLE) {
            videoImg.setVisibility(View.GONE);
            picImg.setVisibility(View.GONE);
            musicImg.setVisibility(View.GONE);
        } else {
            videoImg.setVisibility(View.VISIBLE);
            picImg.setVisibility(View.GONE);
            musicImg.setVisibility(View.VISIBLE);
        }
    }

    private void setCity() {
        List<City> cityList = new ArrayList<>();
        for (int i = 0; i < mDistrictBeanList.size(); i++) {
            DistrictBean districtBean = mDistrictBeanList.get(i);
            City city=new City(districtBean.getDistrict_name(),"","",String.valueOf(districtBean.getDistrict_id()));
            cityList.add(city);
        }
        CityPicker.getInstance()
                .setFragmentManager(getFragmentManager())    //此方法必须调用
                .enableAnimation(true)    //启用动画效果
                .setAnimationStyle(R.style.DefaultCityPickerAnimation)    //自定义动画
                .setAllCities(cityList)
                .setLocatedCity(new LocatedCity(mCurrentRegion.get("district_name"), "", String.valueOf(mCurrentRegion.get("district_id"))))  //APP自身已定位的城市，默认为null（定位失败）new LocatedCity("杭州", "浙江", "101210101")
                .setHotCities(null)    //指定热门城市
                .setOnPickListener(new OnPickListener() {
                    @Override
                    public void onPick(int position, City data) {
                        district_id = data.getCode();
                        requestData();
                    }

                    @Override
                    public void onLocate() {
                        //开始定位，这里模拟一下定位
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //定位完成之后更新数据
                                CityPicker.getInstance()
                                        .locateComplete(new LocatedCity("深圳", "广东", "101280601"), LocateState.SUCCESS);
                            }
                        }, 2000);
                    }
                })
                .show();
    }

    private void setPop() {
        if (null == mPopupWindow) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_pop, null);
            TextView timeTv = view.findViewById(R.id.time_tv);
            TextView collecTv = view.findViewById(R.id.collect_tv);
            TextView videoTv = view.findViewById(R.id.video_tv);
            videoTv.setVisibility(View.VISIBLE);
            timeTv.setText("时间升序");
            collecTv.setText("时间降序");
            videoTv.setText("收藏度");
            Drawable drawableLeft = getResources().getDrawable(
                    R.drawable.icon_time);

            collecTv.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                    null, null, null);
            collecTv.setCompoundDrawablePadding(2);
            mPopupWindow = new PopupWindow(view, DisplayHelper.dp2px(mContext, 100),
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopupWindow.setBackgroundDrawable(null);
            mPopupWindow.setOutsideTouchable(false);
            mPopupWindow.showAsDropDown(popRelativeLayout);
            timeTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Drawable drawableLeft = getResources().getDrawable(
                            R.drawable.icon_time);

                    selectTv.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                            null, null, null);
                    selectTv.setCompoundDrawablePadding(2);
                    selectTv.setText("时间升序");
                    order = 1;
                    requestData();
                    mPopupWindow.dismiss();
                }
            });
            collecTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Drawable drawableLeft = getResources().getDrawable(
                            R.drawable.icon_time);

                    selectTv.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                            null, null, null);
                    selectTv.setCompoundDrawablePadding(2);
                    selectTv.setText("时间降序");
                    order = 2;
                    requestData();
                    mPopupWindow.dismiss();
                }
            });

            videoTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Drawable drawableLeft = getResources().getDrawable(
                            R.drawable.icon_star);

                    selectTv.setCompoundDrawablesWithIntrinsicBounds(drawableLeft,
                            null, null, null);
                    selectTv.setCompoundDrawablePadding(2);
                    selectTv.setText("收藏度");
                    order = 3;
                    requestData();
                    mPopupWindow.dismiss();
                }
            });
        } else {
            if (mPopupWindow.isShowing()) {
                mPopupWindow.dismiss();
            } else {
                mPopupWindow.showAsDropDown(popRelativeLayout);
            }
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            hidePop();
        }
    }

    public void hidePop() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    public void refresh(Map<String, String> data) {
        if (p == 1) {
            mVideoBeanList.clear();
        }
        imagesList.clear();
//        mDistrictBeanList.clear();
        mCurrentRegion = JSONUtils.parseKeyAndValueToMap(data.get("current_region"));
        district_id = mCurrentRegion.get("district_id");
        district_id = "1";
        locationTv.setText(mCurrentRegion.get("district_name"));
//        List<DistrictBean> district = data.getDistrict();
//        mDistrictBeanList.addAll(district);

        List<DanceTypeBean.DataBean> dance_type = JSONUtils.parseKeyAndValueToMapList(DanceTypeBean.DataBean.class, data.get("dance_type"));
        titleList.clear();
        titleList.addAll(dance_type);
        if (titleList.size() > 0) {
            if (mTitlesAdapter == null) {
                mTitlesAdapter = new TitlesAdapter(R.layout.home_title_item, titleList);
                titlesRecyclerView.setAdapter(mTitlesAdapter);
                dance_type_id = String.valueOf(titleList.get(0).getDance_type_id());
                mTitlesAdapter.setOnItemClickListener((adapter, view, position) -> {
                    mTitlesAdapter.setSelectPosition(position);
                    dance_type_id = String.valueOf(titleList.get(position).getDance_type_id());
                    mDance_type_name = titleList.get(position).getDance_type_name();
                    requestData();
                });
            } else {
                mTitlesAdapter.replaceData(titleList);
            }
        }


        List<HomeIndexBean.DataBean.BannerBean> mBannerBeanList = JSONUtils.parseKeyAndValueToMapList(HomeIndexBean.DataBean.BannerBean.class, data.get("banner"));
        if (mBannerBeanList.size() > 0) {
            for (int i = 0; i < mBannerBeanList.size(); i++) {
                imagesList.add(ApiConfig.BASE_IMG_URL + mBannerBeanList.get(i).getBanner_content());
            }
        }
        if (imagesList.size() > 0) {
            setBanner();
        }
        String information_content = JSONUtils.parseKeyAndValueToMap(data.get("information")).get("hezuo_pic");
        GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + information_content).into(noticeImg);
        List<VideoBean> videoBeans = JSONUtils.parseKeyAndValueToMapList(VideoBean.class, data.get("video"));
        mVideoBeanList.clear();
        if (videoBeans != null) {
            mVideoBeanList.addAll(videoBeans);
        }
        MyLogger.printJsonOfError(videoBeans);
        if (mVideoBeanList.size() > 0) {
            mVideoAdpater = new VideoAdpater(R.layout.video_item, mVideoBeanList, mContext);
            video_recyclerView.setAdapter(mVideoAdpater);
            mVideoAdpater.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("file_id", String.valueOf(mVideoBeanList.get(position).getFile_id()));
                    bundle.putString("fileType", "1");
                    startActivity(VideoDetailsAty.class, bundle);
                }
            });
        }

    }


    private void setBanner() {
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new BannerLoader());
        //设置图片集合
        mBanner.setImages(imagesList);
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

//    public void loadMore(HomeIndexPagingBean homeIndexPagingBean) {
//        mVideoAdpater.addData(homeIndexPagingBean.getData().getVideo());
//    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        if (mDanceTypePresenter != null){
//            mDanceTypePresenter.detachView();
//        }
    }
}
