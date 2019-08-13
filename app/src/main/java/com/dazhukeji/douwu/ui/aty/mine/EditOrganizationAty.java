package com.dazhukeji.douwu.ui.aty.mine;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.contract.upload.UpLoadContract;
import com.dazhukeji.douwu.presenter.UpLoadPresenter;
import com.dazhukeji.douwu.utils.MyUtils;
import com.dazhukeji.douwu.view.MyEditText;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.GlideApp;
import com.zhangyunfei.mylibrary.utils.JSONUtils;
import com.zhangyunfei.mylibrary.utils.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 创建者：zhangyunfei
 * 创建时间：2019/2/20 14:17
 * 功能描述：
 */
public class EditOrganizationAty extends BaseAty  implements  UpLoadContract.View {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.headImg)
    ImageView headImg;
    @BindView(R.id.addressEdit)
    MyEditText addressEdit;
    @BindView(R.id.phoneEdit)
    MyEditText phoneEdit;
    @BindView(R.id.nameEdit)
    MyEditText nameEdit;
    @BindView(R.id.wifiBox)
    CheckBox wifiBox;
    @BindView(R.id.parkingSpaceBox)
    CheckBox parkingSpaceBox;
    @BindView(R.id.alipayBox)
    CheckBox alipayBox;
    @BindView(R.id.wechatBox)
    CheckBox wechatBox;
    @BindView(R.id.timeEdit)
    MyEditText timeEdit;
    @BindView(R.id.videoImg)
    ImageView videoImg;
    @BindView(R.id.coverImg)
    ImageView coverImg;
    @BindView(R.id.bgImg)
    ImageView bgImg;
    @BindView(R.id.storeBriefEdit)
    MyEditText storeBriefEdit;
    @BindView(R.id.danceTypeEdit)
    MyEditText danceTypeEdit;
    private String mOrganization_id;


    private UpLoadPresenter mUpLoadPresenter;
    private String mHeadPath;
    private String mCoverPath;
    private String mBgPath;
    private String mVideoPath;

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_organization;
    }

    @Override
    public void initView() {
        mOrganization_id = getIntent().getStringExtra("organization_id");
        txtTitle.setText("编辑机构");
        mUpLoadPresenter = new UpLoadPresenter();
        mUpLoadPresenter.attachView(this, mContext);
    }

    @Override
    public void initData() {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("user_token", ApiConfig.getToken());
        requestMap.put("organization_id", mOrganization_id);
        Observable<ResponseBody> observable = apiService.postOrganizationInformationEdit(requestMap);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        /**
                         * {
                         "code": 1,
                         "msg": "",
                         "data": {
                         "organization_id": 1,
                         "organization_name": "智慧舞蹈机构联盟",
                         "organization_portrait": "20181116/timg (7).jpg",
                         "organization_cover": "20181116/timg (8).jpg",
                         "organization_site": "机构地址",
                         "organization_facility": "机构设施",
                         "organization_business_hours": "机构营业时间",
                         "organization_synopsis": "机构简介",
                         "organization_service": "15777777777",
                         "promotional_video": "",
                         "promotional_cover": "",
                         "organization_type": "街舞 !=end=!机器舞 !=end=!少儿舞",
                         "organization_creattime": 2147483647
                         }
                         }
                         */
                        Map<String, String> map = Config.getMap(responseBody);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL+data.get("organization_portrait")).into(headImg);
                        GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL+data.get("organization_cover")).into(bgImg);
                        addressEdit.setText(data.get("organization_site"));
                        phoneEdit.setText(data.get("organization_service"));
                        nameEdit.setText(data.get("organization_name"));
                        timeEdit.setText(data.get("organization_business_hours"));
                        storeBriefEdit.setText(data.get("organization_synopsis"));
                        danceTypeEdit.setText(data.get("organization_type"));
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @OnClick({R.id.headImg, R.id.videoImg, R.id.coverImg, R.id.bgImg, R.id.confirmTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.headImg:
                pickerPhoto(Config.IMAGE_PICKER);
                break;
            case R.id.videoImg:
                /**
                 * intent.setType(“image/*”);//选择图片
                 * intent.setType(“audio/*”); //选择音频
                 * intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
                 * intent.setType(“video/*;image/*”);//同时选择视频和图片
                 */
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("video/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, "选择文件"), Config.VIDEO_PICKER);
                break;
            case R.id.coverImg:
                pickerPhoto(Config.IMAGE_PICKER2);
                break;
            case R.id.bgImg:
                pickerPhoto(Config.IMAGE_PICKER3);
                break;
            case R.id.confirmTv:
                confirm();
                break;
        }
    }

    private void pickerPhoto(int requestCode) {
        setImagePicker();
        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, requestCode);
    }


    private void confirm() {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("user_token", ApiConfig.getToken());
        requestMap.put("organization_portrait", mHeadPath);
        requestMap.put("organization_cover", mBgPath);
        requestMap.put("organization_name", nameEdit.getContent());
        requestMap.put("organization_site", addressEdit.getContent());
        requestMap.put("organization_service", phoneEdit.getContent());
        requestMap.put("organization_facility", "");
        requestMap.put("organization_business_hours", timeEdit.getContent());
        requestMap.put("organization_synopsis", storeBriefEdit.getContent());
        requestMap.put("organization_type", danceTypeEdit.getContent());
        requestMap.put("promotional_video", mVideoPath);
        requestMap.put("promotional_cover", mCoverPath);
        Observable<ResponseBody> observable = apiService.postOrganizationAffirmEdit(requestMap);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        /**
                         * {
                         "code": 1,
                         "msg": "取消成功!",
                         "data": ""
                         }
                         */
                        Map<String, String> map = Config.getMap(responseBody);
                        ToastUtils.showToast(map.get("msg"));
                        if (map.get("code").equals("1")) {
                            finish();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == Config.IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images.size() > 0) {
                    String path = images.get(0).path;
                    File file = new File(path);
                    GlideApp.with(mContext).load(file).circleCrop().into(headImg);
//                    mUpLoadPresenter.postPic("headImg", file);
                    mUpLoadPresenter.postFile("headImg",file,"3");
                }
            } else if (data != null && requestCode == Config.IMAGE_PICKER2) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images.size() > 0) {
                    String path = images.get(0).path;
                    File file = new File(path);
                    GlideApp.with(mContext).load(file).into(coverImg);
//                    mUpLoadPresenter.postPic("coverImg", file);
                    mUpLoadPresenter.postFile("coverImg",file,"3");
                }
            } else if (data != null && requestCode == Config.IMAGE_PICKER3) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images.size() > 0) {
                    String path = images.get(0).path;
                    File file = new File(path);
                    GlideApp.with(mContext).load(file).circleCrop().into(bgImg);
//                    mUpLoadPresenter.postPic("bgImg", file);
                    mUpLoadPresenter.postFile("bgImg",file,"3");
                }
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }

        if (resultCode == RESULT_OK && data != null && requestCode == Config.VIDEO_PICKER) {
            File file = new File(MyUtils.getRealPath(mContext,data));
            mUpLoadPresenter.postFile("video",file,"1");
        }
    }

    @Override
    public void uploadSuccess(String channel, String path) {
        if (channel.equals("headImg")) {
            mHeadPath = path;
        } else if (channel.equals("coverImg")) {
            mCoverPath = path;
        } else if (channel.equals("bgImg")) {
            mBgPath = path;
        } else if (channel.equals("video")) {
            mVideoPath = path;
        }
    }
}
