package com.dazhukeji.douwu.presenter.home;

import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.bean.home.HomeIndexPagingBean;
import com.dazhukeji.douwu.contract.home.HomeContract;
import com.dazhukeji.douwu.contract.home.HomeContract.Presenter;
import com.zhangyunfei.mylibrary.base.BasePresenter;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/3 9:38
 * 功能描述：
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements Presenter {
    /**
     * 乐舞首页
     * @param dance_type_id
     * @param district_id
     * @param order
     */
    @Override
    public void postHome(int dance_type_id, int district_id, int order,String seek) {
//        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
//        Map<String,String> map=new HashMap<>();
//        map.put("dance_type_id", String.valueOf(dance_type_id));
//        map.put("district_id", String.valueOf(district_id));
//        map.put("order", String.valueOf(order));
//        map.put("seek",seek);
//        map.put("paging","1");
//        Observable<ResponseBody> homeBeanObservable = apiService.postHome(map);
//        mView.showProgress();
//        homeBeanObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<HomeIndexBean>() {
//                    @Override
//                    public void accept(HomeIndexBean homeIndexBean) throws Exception {
//                        if (homeIndexBean.getCode()==1){
//                            mView.refresh(homeIndexBean);
//                        }else {
//                            mView.showError(homeIndexBean.getMsg());
//                        }
//                        mView.hideProgress();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        mView.showError(throwable.getMessage());
//                        mView.hideProgress();
//                    }
//                });

    }

    /**
     * 乐舞首页-下方视频分页请求地址
     * @param dance_type_id
     * @param district_id
     * @param order
     * @param paging
     */
    @Override
    public void postIndexPaging(int dance_type_id, int district_id, int order, int paging) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String,Integer> map=new HashMap<>();
        map.put("dance_type_id",dance_type_id);
        map.put("district_id",district_id);
        map.put("order",order);
        map.put("paging",paging);
        Observable<HomeIndexPagingBean> homeBeanObservable = apiService.postIndexPaging(map);
        mView.showProgress();
        homeBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomeIndexPagingBean>() {
                    @Override
                    public void accept(HomeIndexPagingBean homeIndexPagingBean) throws Exception {
                        if (homeIndexPagingBean.getCode()==1){
                            mView.loadMore(homeIndexPagingBean);
                        }else {
                            mView.showError(homeIndexPagingBean.getMsg());
                        }
                        mView.hideProgress();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable.getMessage());
                        mView.hideProgress();
                    }
                });

    }
}
