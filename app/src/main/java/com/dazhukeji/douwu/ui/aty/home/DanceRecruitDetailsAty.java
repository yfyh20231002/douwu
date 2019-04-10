package com.dazhukeji.douwu.ui.aty.home;

import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.base.BaseAty;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.GlideApp;
import com.zhangyunfei.mylibrary.utils.JSONUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/16 9:48
 * 功能描述：招聘详情
 */
public class DanceRecruitDetailsAty extends BaseAty {
    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.logo_img)
    ImageView logoImg;
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.manageNameTv)
    TextView manageNameTv;
    @BindView(R.id.addressTv)
    TextView addressTv;
    @BindView(R.id.phoneTv)
    TextView phoneTv;
    @BindView(R.id.timeTv)
    TextView timeTv;
    @BindView(R.id.typeTv)
    TextView typeTv;
    @BindView(R.id.ageTv)
    TextView ageTv;
    @BindView(R.id.demandTv)
    TextView demandTv;
    @BindView(R.id.logoImg1)
    ImageView logoImg1;
    @BindView(R.id.logoImg2)
    ImageView logoImg2;
    private String mInvitation_id;
    private String mOrganization_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recruit_details;
    }

    @Override
    public void initView() {
        mInvitation_id = getIntent().getStringExtra("invitation_id");
        mOrganization_id = getIntent().getStringExtra("organization_id");
        txtTitle.setText("招聘详情");
    }

    @Override
    public void initData() {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("invitation_id", mInvitation_id);
        map.put("organization_id", mOrganization_id);
        Observable<ResponseBody> observable = apiService.postOrganizationInvitation(map);
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
                         "organization_name": {
                         "organization_name": "舞动人生"
                         },
                         "invitation": {
                         "invitation_id": 1,
                         "invitation_title": "邀请函标题",
                         "invitation_contact": "刘经理",
                         "invitation_phone": "15999999999",
                         "invitation_interview_site": "汀棠路",
                         "invitation_interview_time": "上午10.00~下午2.00",
                         "invitation_dance_type": "街舞",
                         "invitation_age_demand": "24",
                         "invitation_explain": "职位简介： 岗位职责: （精通各类舞蹈） 要求: 舞蹈教师（专业院校本科及以上学历） 1、热爱教育事业,具有一定的舞蹈教学工作经验。 2、扎实基础,有良好的教学理论基础和个人亲和力及课堂教学能力。 3、对学生具有真诚、友善、宽容……",
                         "invitation_organization_picture": "20190000/1a.jpg",
                         "invitation_organization_picture2": "20190000/10a.jpg",
                         "invitation_creattime": 56465464
                         }
                         }
                         }
                         */
                        Map<String, String> map = Config.getMap(responseBody);
                        Map<String, String> data = JSONUtils.parseKeyAndValueToMap(map.get("data"));
                        Map<String, String> stringMap = JSONUtils.parseKeyAndValueToMap(data.get("invitation"));
                        titleTv.setText(stringMap.get("invitation_title"));
                        manageNameTv.setText(stringMap.get("invitation_contact"));
                        addressTv.setText(stringMap.get("invitation_interview_site"));
                        phoneTv.setText(stringMap.get("invitation_phone"));
                        timeTv.setText(stringMap.get("invitation_interview_time"));
                        typeTv.setText(stringMap.get("invitation_dance_type"));
                        ageTv.setText(stringMap.get("invitation_age_demand"));
                        demandTv.setText(stringMap.get("invitation_explain"));
                        String picture = stringMap.get("invitation_organization_picture");
                        if (!TextUtils.isEmpty(picture)) {
                            GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + picture).into(logoImg1);
                        }
                        String picture2 = stringMap.get("invitation_organization_picture2");
                        if (!TextUtils.isEmpty(picture2)) {
                            GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + picture2).into(logoImg2);
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

}
