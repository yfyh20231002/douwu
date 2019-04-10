package com.dazhukeji.douwu.ui.aty.mine;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.bean.publicBean.BaseBean;
import com.dazhukeji.douwu.contract.mine.member.MineOrgStatusUpgradeContract;
import com.dazhukeji.douwu.contract.upload.UpLoadContract;
import com.dazhukeji.douwu.presenter.UpLoadPresenter;
import com.dazhukeji.douwu.presenter.mine.member.MineOrgStatusUpgradePresenter;
import com.dazhukeji.douwu.view.MyEditText;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.GlideApp;
import com.zhangyunfei.mylibrary.utils.JSONUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
 * 创建时间：2018/11/26 13:57
 * 功能描述：成为机构
 */
public class BeComeOrgAty extends BaseAty<MineOrgStatusUpgradePresenter> implements MineOrgStatusUpgradeContract.View, UpLoadContract.View {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.headImg)
    ImageView headImg;
    @BindView(R.id.coverImg)
    ImageView coverImg;
    @BindView(R.id.bgImg)
    ImageView bgImg;

    @BindView(R.id.addressEdit)
    MyEditText addressEdit;
    @BindView(R.id.timeEdit)
    MyEditText timeEdit;
    @BindView(R.id.storeBriefEdit)
    MyEditText storeBriefEdit;
    @BindView(R.id.danceTypeEdit)
    MyEditText danceTypeEdit;
    @BindView(R.id.cityTv)
    TextView cityTv;
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

    private OptionsPickerView addressOptions;
    private String mDistrict_id;
    private String mHeadPath;
    private String mVideoPath;
    private String mCoverPath;
    private String mBgPath;

    /**
     * 机构设施
     */
    private String mOrganization_facility;

    private UpLoadPresenter mUpLoadPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_become_org;
    }

    @Override
    public void initView() {
        txtTitle.setText("成为机构");
        mUpLoadPresenter = new UpLoadPresenter();
        mUpLoadPresenter.attachView(this, mContext);
    }

    @Override
    public void initData() {
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
                            initAddressOptions(arrayList);
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
    public void changeStateSuccess(BaseBean baseBean) {
        finish();
    }


    @OnClick({R.id.headImg, R.id.chooseLayout, R.id.videoImg, R.id.coverImg, R.id.bgImg, R.id.confirmTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.headImg:
                pickerPhoto(Config.IMAGE_PICKER);
                break;
            case R.id.chooseLayout:
                addressOptions.show();
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
                ((MineOrgStatusUpgradePresenter) mPresenter).postOrgStatusUpgrade(ApiConfig.getToken(), "2", mDistrict_id, mHeadPath, mBgPath, nameEdit.getContent(), addressEdit.getContent(), mOrganization_facility, timeEdit.getContent(), storeBriefEdit.getContent(), danceTypeEdit.getContent(), phoneEdit.getContent(), mVideoPath, mCoverPath);
                break;
        }
    }

    private void pickerPhoto(int requestCode) {
//        setImagePicker();
//        Intent intent = new Intent(this, ImageGridActivity.class);
//        startActivityForResult(intent, requestCode);
    }

    private void initAddressOptions(ArrayList<Map<String, String>> arrayList) {
        List<String> cities = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            cities.add(arrayList.get(i).get("district_name"));
        }
        OptionsPickerBuilder optionsPickerBuilder = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mDistrict_id = arrayList.get(options1).get("district_id");
                cityTv.setText(arrayList.get(options1).get("district_name"));
            }
        });
        addressOptions = optionsPickerBuilder
                .setLayoutRes(R.layout.pop_choose_city, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        TextView sureTv = v.findViewById(R.id.sureTv);
                        sureTv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addressOptions.returnData();
                                addressOptions.dismiss();
                            }
                        });
                    }
                })
                .setOutSideCancelable(false)
                .build();
        addressOptions.setPicker(cities);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
//            if (data != null && requestCode == Config.IMAGE_PICKER) {
//                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
//                if (images.size() > 0) {
//                    String path = images.get(0).path;
//                    File file = new File(path);
//                    GlideApp.with(mContext).load(file).circleCrop().into(headImg);
//                    mUpLoadPresenter.postPic("headImg", file);
//                }
//            } else if (data != null && requestCode == Config.IMAGE_PICKER2) {
//                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
//                if (images.size() > 0) {
//                    String path = images.get(0).path;
//                    File file = new File(path);
//                    GlideApp.with(mContext).load(file).circleCrop().into(coverImg);
//                    mUpLoadPresenter.postPic("coverImg", file);
//                }
//            } else if (data != null && requestCode == Config.IMAGE_PICKER3) {
//                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
//                if (images.size() > 0) {
//                    String path = images.get(0).path;
//                    File file = new File(path);
//                    GlideApp.with(mContext).load(file).circleCrop().into(bgImg);
//                    mUpLoadPresenter.postPic("bgImg", file);
//                }
//            } else {
//                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
//            }
//        }

        if (resultCode == RESULT_OK && data != null && requestCode == Config.VIDEO_PICKER) {
            Uri uri = data.getData();
            String path = uri.getPath();
            File file = new File(path);
            mUpLoadPresenter.postVideo("video", file);
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
