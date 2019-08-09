package com.dazhukeji.douwu.ui.fgt.mine;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.base.BaseFgt;
import com.dazhukeji.douwu.ui.aty.LoginAty;
import com.dazhukeji.douwu.ui.aty.home.DanceOrgDetailsAty;
import com.dazhukeji.douwu.ui.aty.home.TeacherDetailsAty;
import com.dazhukeji.douwu.ui.aty.mine.EditPersonalInfoAty;
import com.dazhukeji.douwu.ui.aty.mine.ManageTeacherAty;
import com.dazhukeji.douwu.ui.aty.mine.MemberChatAty;
import com.dazhukeji.douwu.ui.aty.mine.MemberCollectAty;
import com.dazhukeji.douwu.ui.aty.mine.MemberFollowAty;
import com.dazhukeji.douwu.ui.aty.mine.MemberFreeEnterAty;
import com.dazhukeji.douwu.ui.aty.mine.MemberSoldCoursesAty;
import com.dazhukeji.douwu.ui.aty.mine.MemberVideoAty;
import com.dazhukeji.douwu.ui.aty.mine.OrgChatAty;
import com.dazhukeji.douwu.ui.aty.mine.OrgCourseManageAty;
import com.dazhukeji.douwu.ui.aty.mine.OrgFansAty;
import com.dazhukeji.douwu.ui.aty.mine.OrgSoldCoursesAty;
import com.dazhukeji.douwu.ui.aty.mine.OrgWalletAty;
import com.dazhukeji.douwu.ui.aty.mine.OrganizationRecruitAty;
import com.dazhukeji.douwu.ui.aty.mine.OrganizationWorksAty;
import com.dazhukeji.douwu.ui.aty.mine.TeacherChatAty;
import com.dazhukeji.douwu.ui.aty.mine.TeacherCourseManageAty;
import com.dazhukeji.douwu.ui.aty.mine.TeacherFansAty;
import com.dazhukeji.douwu.ui.aty.mine.TeacherSoldCoursesAty;
import com.dazhukeji.douwu.ui.aty.mine.TeacherWalletAty;
import com.dazhukeji.douwu.ui.aty.mine.TeacherWorksAty;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.GlideApp;
import com.zhangyunfei.mylibrary.utils.JSONUtils;
import com.zhangyunfei.mylibrary.utils.ToastUtils;

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
 * 创建时间：2018/11/14 11:48
 * 功能描述：
 */
public class MyFragment extends BaseFgt{
    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.head_img)
    ImageView headImg;
    @BindView(R.id.logo_img)
    ImageView logoImg;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.contentTv)
    TextView contentTv;
    @BindView(R.id.memberFreeEnterLinearLayout)
    LinearLayout memberFreeEnterLinearLayout;
    @BindView(R.id.organizationLinearLayout)
    LinearLayout organizationLinearLayout;
    @BindView(R.id.teacherLinearLayout)
    LinearLayout teacherLinearLayout;
    private String mUser_teacher_id;
    private String mOrganization_id;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initialized(View view) {
        backImg.setVisibility(View.GONE);
        txtTitle.setText("个人中心");
    }

    @Override
    protected void requestData() {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("user_token", ApiConfig.getToken());
        Observable<ResponseBody> observable = apiService.postUserCentre(map);
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
                         "user": {
                         "user_id": 4,
                         "user_name": "剑心",
                         "user_sex": 2,
                         "user_role": 2,
                         "user_portrait": "001/12.png",
                         "user_signature": "舞动人生，做最好的自己！",
                         "user_false_delete": 1,
                         "user_status": 1
                         },
                         "user_teacher_id": {
                         "user_teacher_id": 8
                         },
                         "discounts": 0
                         }
                         }
                         */
                        Map<String, String> stringMap = Config.getMap(responseBody);
                        if (Integer.parseInt(stringMap.get("code"))==1){
                            Map<String, String> data = JSONUtils.parseKeyAndValueToMap(stringMap.get("data"));
                            Map<String, String> user = JSONUtils.parseKeyAndValueToMap(data.get("user"));
                            int user_role = Integer.parseInt(user.get("user_role"));
                            if (user_role == 1){
                                organizationLinearLayout.setVisibility(View.GONE);
                                teacherLinearLayout.setVisibility(View.GONE);
                                memberFreeEnterLinearLayout.setVisibility(View.VISIBLE);
                            }else if (user_role == 2){
                                organizationLinearLayout.setVisibility(View.GONE);
                                teacherLinearLayout.setVisibility(View.VISIBLE);
                                memberFreeEnterLinearLayout.setVisibility(View.INVISIBLE);
                            }else if (user_role == 3){
                                organizationLinearLayout.setVisibility(View.VISIBLE);
                                teacherLinearLayout.setVisibility(View.GONE);
                                memberFreeEnterLinearLayout.setVisibility(View.INVISIBLE);
                            }
                            String user_portrait = user.get("user_portrait");
                            if (!TextUtils.isEmpty(user_portrait)){
                                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL+user_portrait).circleCrop().into(headImg);
                            }
                            nameTv.setText(user.get("user_name"));
                            contentTv.setText(user.get("user_signature"));
                            int user_sex = Integer.parseInt(user.get("user_sex"));
                            if (user_sex == 1){
                                Drawable drawable = ContextCompat.getDrawable(mContext,R.drawable.icon_mine_boy);
                                nameTv.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
                                nameTv.setCompoundDrawablePadding(11);
                            }else {
                                Drawable drawable = ContextCompat.getDrawable(mContext,R.drawable.icon_mine_girl);
                                nameTv.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
                                nameTv.setCompoundDrawablePadding(11);
                            }

                            if (data.containsKey("user_teacher_id")){
                                Map<String, String> teacherId = JSONUtils.parseKeyAndValueToMap(data.get("user_teacher_id"));
                                mUser_teacher_id = teacherId.get("user_teacher_id");
                            }

                            if (data.containsKey("organization_id")){
                                Map<String, String> organizationId = JSONUtils.parseKeyAndValueToMap(data.get("organization_id"));
                                mOrganization_id = organizationId.get("organization_id");
                            }
                        }else  if(Integer.parseInt(stringMap.get("code"))==-1){
                            startActivity(LoginAty.class);
                        }else {
                            ToastUtils.showToast(stringMap.get("msg"));
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


    @OnClick({R.id.edit_img, R.id.memberCollectLinearLayout, R.id.memberCourseLinearLayout, R.id.memberChatLinearLayout, R.id.memberFreeEnterLinearLayout,  R.id.memberWorksLinearLayout, R.id.memberFollowLinearLayout, R.id.organizationRecruitLinearLayout, R.id.organizationManageTeacherLinearLayout,  R.id.organizationWorksLinearLayout, R.id.organizationFansLinearLayout, R.id.organizationInfoLinearLayout, R.id.organizationManageCourseLinearLayout,R.id.organizationChatLinearLayout,R.id.organizationWalletLinearLayout,R.id.organizationSaleCourseLinearLayout, R.id.teacherWorksLinearLayout, R.id.teacherManageCourseLinearLayout,  R.id.teacherFansLinearLayout, R.id.teacherChatLinearLayout,R.id.teacherWalletLinearLayout,R.id.teacherSaleCourseLinearLayout,R.id.teacherInfoLinearLayout,R.id.loginOutTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_img:
                startActivity(EditPersonalInfoAty.class);
                break;
            case R.id.memberCollectLinearLayout:
                startActivity(MemberCollectAty.class);
                break;
            case R.id.memberCourseLinearLayout:
                startActivity(MemberSoldCoursesAty.class);
                break;
            case R.id.memberChatLinearLayout:
                startActivity(MemberChatAty.class);
                break;
            case R.id.memberFreeEnterLinearLayout:
                startActivity(MemberFreeEnterAty.class);
                break;
            case R.id.memberWorksLinearLayout:
                startActivity(MemberVideoAty.class);
                break;
            case R.id.memberFollowLinearLayout:
                startActivity(MemberFollowAty.class);
                break;
            case R.id.organizationRecruitLinearLayout:
                startActivity(OrganizationRecruitAty.class);
                break;
            case R.id.organizationManageTeacherLinearLayout:
                startActivity(ManageTeacherAty.class);
                break;
            case R.id.organizationWorksLinearLayout:
                startActivity(OrganizationWorksAty.class);
                break;
            case R.id.organizationFansLinearLayout:
                startActivity(OrgFansAty.class);
                break;
            case R.id.organizationInfoLinearLayout:
                if (mOrganization_id != null){
                    Bundle bundle =new Bundle();
                    bundle.putBoolean("isShow",true);
                    bundle.putString("organization_id", mOrganization_id);
                    startActivity(DanceOrgDetailsAty.class,bundle);
                }
                break;
            case R.id.organizationManageCourseLinearLayout:
                startActivity(OrgCourseManageAty.class);
                break;
            case R.id.organizationChatLinearLayout:
                startActivity(OrgChatAty.class);
                break;
            case R.id.organizationWalletLinearLayout:
                startActivity(OrgWalletAty.class);
                break;
            case R.id.organizationSaleCourseLinearLayout:
                startActivity(OrgSoldCoursesAty.class);
                break;
            case R.id.teacherWorksLinearLayout:
                startActivity(TeacherWorksAty.class);
                break;
            case R.id.teacherManageCourseLinearLayout:
                startActivity(TeacherCourseManageAty.class);
                break;
            case R.id.teacherFansLinearLayout:
                startActivity(TeacherFansAty.class);
                break;
            case R.id.teacherChatLinearLayout:
                startActivity(TeacherChatAty.class);
                break;
            case R.id.teacherWalletLinearLayout:
                startActivity(TeacherWalletAty.class);
                break;
            case R.id.teacherSaleCourseLinearLayout:
                startActivity(TeacherSoldCoursesAty.class);
                break;
            case R.id.teacherInfoLinearLayout:
                if (mUser_teacher_id != null){
                    Bundle bundle =new Bundle();
                    bundle.putBoolean("isShow",true);
                    bundle.putString("user_teacher_id", mUser_teacher_id);
                    startActivity(TeacherDetailsAty.class,bundle);
                }
                break;
            case R.id.loginOutTv:
                ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
                Map<String, String> map = new HashMap<>();
                map.put("user_token", ApiConfig.getToken());
                Observable<ResponseBody> observable = apiService.postLoginOut(map);
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableObserver<ResponseBody>() {
                            @Override
                            public void onNext(ResponseBody responseBody) {
                                Map<String, String> stringMap = Config.getMap(responseBody);
                                Config.setToken("");
                                if (Integer.parseInt(stringMap.get("code"))==-1){
                                    startActivity(LoginAty.class);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
        }
    }

}
