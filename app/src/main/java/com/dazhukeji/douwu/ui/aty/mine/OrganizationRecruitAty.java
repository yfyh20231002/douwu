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
import com.dazhukeji.douwu.bean.mine.org.OrgInvitationFindBean;
import com.dazhukeji.douwu.contract.mine.org.OrgRecruitContract;
import com.dazhukeji.douwu.contract.upload.UpLoadContract;
import com.dazhukeji.douwu.presenter.UpLoadPresenter;
import com.dazhukeji.douwu.presenter.mine.org.OrgRecruitPresenter;
import com.dazhukeji.douwu.ui.aty.LoginAty;
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
 * 创建时间：2018/11/22 14:14
 * 功能描述：我要招聘
 */
public class OrganizationRecruitAty extends BaseAty<OrgRecruitPresenter> implements OrgRecruitContract.View ,UpLoadContract.View{
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.administratorsNameEdit)
    MyEditText administratorsNameEdit;
    @BindView(R.id.addressEdit)
    MyEditText addressEdit;
    @BindView(R.id.phoneEdit)
    MyEditText phoneEdit;
    @BindView(R.id.timeEdit)
    MyEditText timeEdit;
    @BindView(R.id.ageEdit)
    MyEditText ageEdit;
    @BindView(R.id.applicationRequirementsEdit)
    MyEditText applicationRequirementsEdit;
    @BindView(R.id.typeEdit)
    MyEditText typeEdit;
    @BindView(R.id.picImg1)
    ImageView picImg1;
    @BindView(R.id.picImg2)
    ImageView picImg2;
    @BindView(R.id.confirmTv)
    TextView confirmTv;
    private int mInvitation_id;
    private String mPicture1;
    private String mPicture2;

    private UpLoadPresenter mUpLoadPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_organization_recruit;
    }

    @Override
    public void initView() {
        txtTitle.setText("我要招聘");
        mUpLoadPresenter = new UpLoadPresenter();
        mUpLoadPresenter.attachView(this,this);
    }

    @Override
    public void initData() {
        ((OrgRecruitPresenter)mPresenter).postOrganizationInvitationFind(ApiConfig.getToken());
    }

    @Override
    public void refreshOrgInvitationFind(int code,OrgInvitationFindBean orgInvitationFindBean) {
        if (code == 1){
            confirmTv.setText("确认修改");
        }else {
            confirmTv.setText("确定添加");
        }
        mInvitation_id = orgInvitationFindBean.getInvitation_id();
        administratorsNameEdit.setText(orgInvitationFindBean.getInvitation_contact());
        addressEdit.setText(orgInvitationFindBean.getInvitation_interview_site());
        phoneEdit.setText(orgInvitationFindBean.getInvitation_phone());
        timeEdit.setText(orgInvitationFindBean.getInvitation_interview_time());
        ageEdit.setText(orgInvitationFindBean.getInvitation_age_demand());
        applicationRequirementsEdit.setText(orgInvitationFindBean.getInvitation_explain());
        typeEdit.setText(orgInvitationFindBean.getInvitation_dance_type());
        String invitation_organization_picture = orgInvitationFindBean.getInvitation_organization_picture();
        if (!TextUtils.isEmpty(invitation_organization_picture)){
        GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + invitation_organization_picture).into(picImg1);
        }
        String invitation_organization_picture2 = orgInvitationFindBean.getInvitation_organization_picture2();
        if (!TextUtils.isEmpty(invitation_organization_picture2)){
            GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + invitation_organization_picture2).into(picImg1);
        }
    }

    @Override
    public void changeSuccess() {
        finish();
    }

    @Override
    public void toLogin() {
        startActivity(LoginAty.class);
    }

    @OnClick({R.id.picImg1, R.id.picImg2, R.id.confirmTv})
    public void onViewClicked(View view) {
        setImagePicker();
        Intent intent = new Intent(this, ImageGridActivity.class);
        switch (view.getId()) {
            case R.id.picImg1:
                startActivityForResult(intent, Config.IMAGE_PICKER);
                break;
            case R.id.picImg2:
                startActivityForResult(intent, Config.IMAGE_PICKER2);
                break;
            case R.id.confirmTv:
                if (TextUtils.isEmpty(mPicture1) || TextUtils.isEmpty(mPicture2)){
                    Toast.makeText(OrganizationRecruitAty.this, "请将信息补充完整", Toast.LENGTH_SHORT).show();
                }
                if (confirmTv.getText().toString().equals("确认修改")){
                    ((OrgRecruitPresenter)mPresenter).postOrganizationInvitationEdit(ApiConfig.getToken(),String.valueOf(mInvitation_id),administratorsNameEdit.getContent(),phoneEdit.getContent(),addressEdit.getContent(),timeEdit.getContent(),typeEdit.getContent(),ageEdit.getContent(),applicationRequirementsEdit.getContent(),mPicture1,mPicture2);
                }else {
                    ((OrgRecruitPresenter)mPresenter).postOrganizationInvitationAdd(ApiConfig.getToken(),String.valueOf(mInvitation_id),administratorsNameEdit.getContent(),phoneEdit.getContent(),addressEdit.getContent(),timeEdit.getContent(),typeEdit.getContent(),ageEdit.getContent(),applicationRequirementsEdit.getContent(),mPicture1,mPicture2);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == Config.IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images.size() > 0) {
                    String path = images.get(0).path;
                    File mFile = new File(path);
                        GlideApp.with(mContext).load(mFile).into(picImg1);
//                    mUpLoadPresenter.postPic("image1",mFile);
                    mUpLoadPresenter.postFile("image1",mFile,"3");
                }
            }else if (data != null && requestCode == Config.IMAGE_PICKER2) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images.size() > 0) {
                    String path = images.get(0).path;
                    File mFile = new File(path);
                    GlideApp.with(mContext).load(mFile).into(picImg2);
//                    mUpLoadPresenter.postPic("image2",mFile);
                    mUpLoadPresenter.postFile("image2",mFile,"3");
                }
            }else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void uploadSuccess(String channel, String path) {
        if ("image1".equals(channel)){
            mPicture1 = path;
        }else if ("image2".equals(channel)){
            mPicture2 = path;
        }
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
        ToastUtils.showToast(msg);
    }
}
