package com.dazhukeji.douwu.presenter.login;

import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.bean.publicBean.BaseBean;
import com.dazhukeji.douwu.contract.login.LoginContract;
import com.zhangyunfei.mylibrary.base.BasePresenter;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/6 15:04
 * 功能描述：
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    /**
     * 乐舞登陆
     *
     * @param phone
     * @param password
     */
    @Override
    public void postLogin(String phone, String password) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("phone", phone);
        requestMap.put("password", password);
        Observable<ResponseBody> responseObservable = apiService.postLogin(requestMap);
        mView.showProgress();
        responseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Map<String, String> map = Config.getMap(responseBody);
                        if (map.get("code").equals("1")){
                            mView.refresh(map);
                        }
                        mView.showError(map.get("msg"));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideProgress();
                    }
                });
    }


    /**
     * 注册账号
     *
     * @param phone
     * @param password
     * @param code
     */
    @Override
    public void postRegister(String phone, String password, String code) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("password", password);
        map.put("code", code);
        Observable<BaseBean> responseObservable = apiService.postRegister(map);
        mView.showProgress();
        responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        int code = baseBean.getCode();
                        String msg = baseBean.getMsg();
                        if (1 == code) {
                            mView.onComplete(msg);
                        } else {
                            mView.showError(msg);
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

    /**
     * 发送验证码
     *
     * @param phone
     * @param type
     */
    @Override
    public void postSendMessage(String phone, String type) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("type", type);
        Observable<BaseBean> responseObservable = apiService.postSendMessage(map);
        mView.showProgress();
        responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        int code = baseBean.getCode();
                        String msg = baseBean.getMsg();
                        if (1 == code) {
                            mView.onComplete(msg);
                        } else {
                            mView.showError(msg);
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

    /**
     * 确认修改密码
     *
     * @param phone
     * @param password
     * @param code
     */
    @Override
    public void postForget(String phone, String password, String code) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("password", password);
        map.put("code", code);
        Observable<BaseBean> responseObservable = apiService.postForget(map);
        mView.showProgress();
        responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        int code = baseBean.getCode();
                        String msg = baseBean.getMsg();
                        if (1 == code) {
                            mView.onComplete(msg);
                        } else {
                            mView.showError(msg);
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
