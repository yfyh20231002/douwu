package com.dazhukeji.douwu.ui.aty.mine;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
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
import com.dazhukeji.douwu.bean.mine.teacher.TeacherInfoBean;
import com.dazhukeji.douwu.contract.mine.teacher.MemberTeacherContract;
import com.dazhukeji.douwu.contract.upload.UpLoadContract;
import com.dazhukeji.douwu.presenter.UpLoadPresenter;
import com.dazhukeji.douwu.presenter.mine.teacher.MemberTeacherPresenter;
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
 * 时间：2018/12/25
 * 联系方式：32457127@qq.com
 * 功能描述：
 */
public class MemberTeacherInfo extends BaseAty<MemberTeacherPresenter> implements MemberTeacherContract.View , UpLoadContract.View{
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.headImg)
    ImageView headImg;
    @BindView(R.id.nameEdit)
    MyEditText nameEdit;
    @BindView(R.id.addressEdit)
    MyEditText addressEdit;
    @BindView(R.id.phoneEdit)
    MyEditText phoneEdit;
    @BindView(R.id.timeEdit)
    MyEditText timeEdit;
    @BindView(R.id.teacherBriefEdit)
    MyEditText teacherBriefEdit;
    @BindView(R.id.danceTypeEdit)
    MyEditText danceTypeEdit;

    @BindView(R.id.cityTv)
    TextView cityTv;
    @BindView(R.id.coverImg)
    ImageView coverImg;
    @BindView(R.id.videoImg)
    ImageView videoImg;
    private String mEnter_type;

    private UpLoadPresenter mUpLoadPresenter;

    private OptionsPickerView addressOptions;
    private String mDistrict_id;
    private String headImgPath="";
    private String coverImgPath="";
    private String videoPath="";

    @Override
    public int getLayoutId() {
        return R.layout.activity_become_teacher;
    }

    @Override
    public void initView() {
        txtTitle.setText("编辑老师");
        mEnter_type = getIntent().getStringExtra("enter_type");
        mUpLoadPresenter = new UpLoadPresenter();
        mUpLoadPresenter.attachView(this, mContext);
    }

    @Override
    public void initData() {
        ((MemberTeacherPresenter) mPresenter).postTeacherInformation(ApiConfig.getToken());
    }

    @Override
    public void refreshTeacherInfo(TeacherInfoBean teacherInfoBean) {
        TeacherInfoBean.DataBean data = teacherInfoBean.getData();
        if (!TextUtils.isEmpty(data.getTeacher_portrait())){
            GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + data.getTeacher_portrait()).circleCrop().into(headImg);
        }
        nameEdit.setText(data.getTeacher_name());
        addressEdit.setText(data.getTeacher_site());
        phoneEdit.setText(data.getTeacher_phone());
        timeEdit.setText(data.getSchooltime());
        teacherBriefEdit.setText(data.getTeacher_intro());
        danceTypeEdit.setText(data.getTeacher_master());
        if (!TextUtils.isEmpty(data.getTeacher_video_cover())){
            GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + data.getTeacher_video_cover()).into(coverImg);
        }
    }

    @Override
    public void commitSuccess() {
        finish();
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
//            } if (data != null && requestCode == Config.IMAGE_PICKER2) {
//                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
//                if (images.size() > 0) {
//                    String path = images.get(0).path;
//                    File file = new File(path);
//                    GlideApp.with(mContext).load(file).into(coverImg);
//                    mUpLoadPresenter.postPic("coverImg", file);
//                }
//            }else {
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


    @OnClick({R.id.headImg,R.id.videoImg,R.id.coverImg, R.id.chooseLayout,R.id.confirmTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.headImg:
//                setImagePicker();
//                Intent intent = new Intent(this, ImageGridActivity.class);
//                startActivityForResult(intent, Config.IMAGE_PICKER);
                break;
            case R.id.videoImg:
                /**
                 * intent.setType(“image/*”);//选择图片
                 * intent.setType(“audio/*”); //选择音频
                 * intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
                 * intent.setType(“video/*;image/*”);//同时选择视频和图片
                 */
                Intent intent2 = new Intent(Intent.ACTION_GET_CONTENT);
                intent2.setType("video/*");
                intent2.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent2, "选择文件"), Config.VIDEO_PICKER);
                break;
            case R.id.coverImg:
//                setImagePicker();
//                Intent intent3 = new Intent(this, ImageGridActivity.class);
//                startActivityForResult(intent3, Config.IMAGE_PICKER2);
                break;
            case R.id.chooseLayout:
                chooseCity();
                break;
            case R.id.confirmTv:
                ((MemberTeacherPresenter) mPresenter).postUserTeacherAffirmEdit(ApiConfig.getToken(), headImgPath, nameEdit.getContent(), teacherBriefEdit.getContent(), danceTypeEdit.getContent(), addressEdit.getContent(), phoneEdit.getContent(), timeEdit.getContent(),coverImgPath,videoPath);
                break;
        }
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
        addressOptions.show();
    }

    @Override
    public void uploadSuccess(String channel, String path) {
        if (channel.equals("headImg")) {
            headImgPath = path;
        }
        if (channel.equals("coverImg")) {
            coverImgPath = path;
        }
        if (channel.equals("video")) {
            videoPath = path;
        }
    }
}
