package com.dazhukeji.douwu.ui.aty.mine;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.bean.mine.member.UserDatumUpdateBean;
import com.dazhukeji.douwu.contract.mine.member.MineUpdateContract;
import com.dazhukeji.douwu.contract.upload.UpLoadContract;
import com.dazhukeji.douwu.presenter.UpLoadPresenter;
import com.dazhukeji.douwu.presenter.mine.member.MineUpdatePresenter;
import com.dazhukeji.douwu.view.MyEditText;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.utils.GlideApp;
import com.zhangyunfei.mylibrary.utils.ToastUtils;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/22 11:06
 * 功能描述：修改个人信息
 */
public class EditPersonalInfoAty extends BaseAty<MineUpdatePresenter> implements MineUpdateContract.View,UpLoadContract.View{
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.head_img)
    ImageView headImg;
    @BindView(R.id.user_name_edit)
    MyEditText userNameEdit;
    @BindView(R.id.sign_edit)
    MyEditText signEdit;
    @BindView(R.id.boy_tv)
    TextView boyTv;
    @BindView(R.id.girl_tv)
    TextView girlTv;
    @BindView(R.id.confirm_revision_tv)
    TextView confirmRevisionTv;

    private String sex = "boy";
    private String mHeadPath;


    private UpLoadPresenter mUpLoadPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_info;
    }

    @Override
    public void initView() {
        txtTitle.setText("修改个人信息");
        mUpLoadPresenter = new UpLoadPresenter();
        mUpLoadPresenter.attachView(this,mContext);
    }

    @Override
    public void initData() {
        ((MineUpdatePresenter)mPresenter).postUserDatumUpdate(ApiConfig.getToken());
    }

    @OnClick({R.id.head_img, R.id.boy_tv, R.id.girl_tv, R.id.confirm_revision_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_img:
                setImagePicker();
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, Config.IMAGE_PICKER);
                break;
            case R.id.boy_tv:
                sex = "boy";
                setSex();
                break;
            case R.id.girl_tv:
                sex = "girl";
                setSex();
                break;
            case R.id.confirm_revision_tv:
                String userNameEditContent = userNameEdit.getContent();
                String signEditContent = signEdit.getContent();
                String s;
                if ("boy".equals(sex)){
                    s = "1";
                }else {
                    s = "2";
                }
                if (!TextUtils.isEmpty(userNameEditContent) && !TextUtils.isEmpty(signEditContent) && mHeadPath != null){
                    ((MineUpdatePresenter)mPresenter).postAffirmUpdate(ApiConfig.getToken(),userNameEditContent,mHeadPath,signEditContent,s);
                }else {
                    ToastUtils.showToast("请将信息填写完整");
                    return;
                }

                break;
        }
    }

    private void setSex() {
        boyTv.setBackgroundResource(R.drawable.icon_sex_unselect_bg);
        girlTv.setBackgroundResource(R.drawable.icon_sex_unselect_bg);
        if ("boy".equals(sex)) {
            boyTv.setBackgroundResource(R.drawable.icon_sex_select_bg);
        } else {
            girlTv.setBackgroundResource(R.drawable.icon_sex_select_bg);
        }
    }

    @Override
    public void refresh(UserDatumUpdateBean bean) {
        UserDatumUpdateBean.DataBean data = bean.getData();
        String user_portrait = data.getUser_portrait();
        if (!TextUtils.isEmpty(user_portrait)){
            GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL+user_portrait).circleCrop().into(headImg);
        }
        String user_name = data.getUser_name();
        if (!TextUtils.isEmpty(user_name)) {
            userNameEdit.setText(user_name);
        }
        String user_signature = data.getUser_signature();
        if (!TextUtils.isEmpty(user_signature)) {
            signEdit.setText(user_signature);
        }

        int user_sex = data.getUser_sex();
        if (user_sex == 1){
            sex = "boy";
            setSex();
        }else {
            sex = "girl";
            setSex();
        }
    }

    @Override
    public void onSuccess() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == Config.IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images.size()>0){
                    String path = images.get(0).path;
                    File file = new File(path);
                    GlideApp.with(mContext).load(file).circleCrop().into(headImg);
                    //mUpLoadPresenter.postPic("headImg",file);
                    mUpLoadPresenter.postFile("headImg",file,"3");
                }
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void uploadSuccess(String channel, String path) {
        if (channel.equals("headImg")){
            mHeadPath = path;
        }
    }
}
