package com.dazhukeji.douwu.presenter;

import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.bean.publicBean.Response;
import com.dazhukeji.douwu.contract.upload.UpLoadContract;
import com.zhangyunfei.mylibrary.base.BasePresenter;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/29 16:15
 * 功能描述：
 */
public class UpLoadPresenter extends BasePresenter<UpLoadContract.View> implements UpLoadContract.Presenter{

//    @Override
//    public void postPic(String channel, File image) {
//        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
//        //1.创建MultipartBody.Builder对象
//        MultipartBody.Builder builder = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM);//表单类型
//
//        //2.获取图片，创建请求体
//        RequestBody body=RequestBody.create(MediaType.parse("multipart/form-data"),image);//表单类型
//
//        //3.调用MultipartBody.Builder的addFormDataPart()方法添加表单数据
////        builder.addFormDataPart(key, value);//传入服务器需要的key，和相应value值
//        builder.addFormDataPart("image",image.getName(),body); //添加图片数据，body创建的请求体
//
//        //4.创建List<MultipartBody.Part> 集合，
//        //  调用MultipartBody.Builder的build()方法会返回一个新创建的MultipartBody
//        //  再调用MultipartBody的parts()方法返回MultipartBody.Part集合
//        List<MultipartBody.Part> parts=builder.build().parts();
//        Observable<Response> observable = apiService.postUploadImage(parts);
//        mView.showProgress();
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableObserver<Response>() {
//                    @Override
//                    public void onNext(Response response) {
//                        if (response.getCode() == 1){
//                            mView.uploadSuccess(channel,response.getData().toString());
//                        }
//                        mView.showError(response.getMsg());
//                        mView.hideProgress();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mView.showError(e.getMessage());
//                        mView.hideProgress();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }

    @Override
    public void postVideo(String channel, File video) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        //1.创建MultipartBody.Builder对象
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型

        //2.获取图片，创建请求体
        RequestBody body=RequestBody.create(MediaType.parse("multipart/form-data"),video);//表单类型

        //3.调用MultipartBody.Builder的addFormDataPart()方法添加表单数据
        //        builder.addFormDataPart(key, value);//传入服务器需要的key，和相应value值
        builder.addFormDataPart("video",video.getName(),body); //添加图片数据，body创建的请求体

        //4.创建List<MultipartBody.Part> 集合，
        //  调用MultipartBody.Builder的build()方法会返回一个新创建的MultipartBody
        //  再调用MultipartBody的parts()方法返回MultipartBody.Part集合
        List<MultipartBody.Part> parts=builder.build().parts();
        Observable<Response> observable = apiService.postUploadVideo(parts);
        mView.showProgress();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Response>() {
                    @Override
                    public void onNext(Response response) {
                        if (response.getCode() == 1){
                            mView.uploadSuccess(channel,response.getData().toString());
                        }
                        mView.showError(response.getMsg());
                        mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void postMusic(String channel, File music) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        //1.创建MultipartBody.Builder对象
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型

        //2.获取图片，创建请求体
        RequestBody body=RequestBody.create(MediaType.parse("multipart/form-data"),music);//表单类型

        //3.调用MultipartBody.Builder的addFormDataPart()方法添加表单数据
        //        builder.addFormDataPart(key, value);//传入服务器需要的key，和相应value值
        builder.addFormDataPart("music",music.getName(),body); //添加图片数据，body创建的请求体

        //4.创建List<MultipartBody.Part> 集合，
        //  调用MultipartBody.Builder的build()方法会返回一个新创建的MultipartBody
        //  再调用MultipartBody的parts()方法返回MultipartBody.Part集合
        List<MultipartBody.Part> parts=builder.build().parts();
        Observable<Response> observable = apiService.postUploadMusic(parts);
        mView.showProgress();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Response>() {
                    @Override
                    public void onNext(Response response) {
                        if (response.getCode() == 1){
                            mView.uploadSuccess(channel,response.getData().toString());
                        }
                        mView.showError(response.getMsg());
                        mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public  void postFile(String channel, File file,String type) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        //1.创建MultipartBody.Builder对象
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型

        //2.获取图片，创建请求体
        RequestBody body=RequestBody.create(MediaType.parse("multipart/form-data"),file);//表单类型
        //3.调用MultipartBody.Builder的addFormDataPart()方法添加表单数据
        //        builder.addFormDataPart(key, value);//传入服务器需要的key，和相应value值
        builder.addFormDataPart("file",file.getName(),body); //添加图片数据，body创建的请求体


        builder.addFormDataPart("file_type",type);

        //4.创建List<MultipartBody.Part> 集合，
        //  调用MultipartBody.Builder的build()方法会返回一个新创建的MultipartBody
        //  再调用MultipartBody的parts()方法返回MultipartBody.Part集合
        List<MultipartBody.Part> parts=builder.build().parts();
        Observable<Response> observable = apiService.postUploadFile(parts);
        mView.showProgress();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Response>() {
                    @Override
                    public void onNext(Response response) {
                        if (response.getCode() == 1){
                            mView.uploadSuccess(channel,response.getData().toString());
                        }
                        mView.showError(response.getMsg());
                        mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
