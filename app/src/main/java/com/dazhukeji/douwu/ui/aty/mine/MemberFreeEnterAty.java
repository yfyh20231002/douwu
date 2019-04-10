package com.dazhukeji.douwu.ui.aty.mine;

import android.content.DialogInterface;
import android.view.View;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.view.TipDialog;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/26 11:26
 * 功能描述：免费入驻
 */
public class MemberFreeEnterAty extends BaseAty {
    @Override
    public int getLayoutId() {
        return R.layout.activity_member_free_enter;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.orgLinearLayout, R.id.teacherLinearLayout, R.id.rootLayout})
    public void onViewClicked(View view) {
//        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.orgLinearLayout:
//                bundle.putString("enter_type","2");
//                startActivity(BeComeOrgAty.class,bundle);
                checkout("2");
                break;
            case R.id.teacherLinearLayout:
//                bundle.putString("enter_type","1");
//                startActivity(BeComeTeacherAty.class,bundle);
                checkout("1");
                break;
            case R.id.rootLayout:
                finish();
                break;
        }
    }

    /**
     *
     * @param verify_type 判断类型1:教师2:机构
     */
    private void checkout(String verify_type) {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("verify_type", verify_type);
        requestMap.put("user_token", ApiConfig.getToken());
        Observable<ResponseBody> observable = apiService.postVerify(requestMap);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {

                        /**
                         * code：1正常3审核中4驳回

                         如果code为1正常时直接进入申请页面

                         如果为code为3则显示审核中

                         如果为code为4则代表申请被驳回需要重新填写信息重新申请
                         */
                        Map<String, String> map = Config.getMap(responseBody);
                        ToastUtils.showToast(map.get("msg"));
                        if (verify_type.equals("1")){
                            if (map.get("code").equals("1")){
                                startActivity(BeComeTeacherAty.class,null);
                                finish();
                            }else if (map.get("code").equals("3")){
                                new TipDialog.Builder(mContext)
                                        .setTitle("审核中...")
                                        .setMessage("您所提交的入驻正在审核中请稍后")
                                        .setCancelable(false)
                                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                finish();
                                            }
                                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        finish();
                                    }
                                }).create().show();

                            }else if (map.get("code").equals("4")){
                                new TipDialog.Builder(mContext)
                                        .setTitle("审核被驳回...")
                                        .setMessage("您所提交的入驻审核被驳回需要重新填写信息")
                                        .setCancelable(false)
                                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                startActivity(BeComeTeacherAty.class,null);
                                                dialog.dismiss();
                                                finish();
                                            }
                                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        finish();
                                    }
                                }).create().show();
                            }
                        }else if (verify_type.equals("2")){
                            if (map.get("code").equals("1")){
                                startActivity(BeComeOrgAty.class,null);
                                finish();
                            }else if (map.get("code").equals("3")){
                                new TipDialog.Builder(mContext)
                                        .setTitle("审核中...")
                                        .setMessage("您所提交的入驻正在审核中请稍后")
                                        .setCancelable(false)
                                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                finish();
                                            }
                                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        finish();
                                    }
                                }).create().show();

                            }else if (map.get("code").equals("4")){
                                new TipDialog.Builder(mContext)
                                        .setTitle("审核被驳回...")
                                        .setMessage("您所提交的入驻审核被驳回需要重新填写信息")
                                        .setCancelable(false)
                                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                startActivity(BeComeOrgAty.class,null);
                                                dialog.dismiss();
                                                finish();
                                            }
                                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        finish();
                                    }
                                }).create().show();
                            }
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
