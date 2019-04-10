package com.dazhukeji.douwu.wxapi;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.base.BaseAty;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhangyunfei.mylibrary.utils.ToastUtils;

public class WXPayEntryActivity extends BaseAty implements IWXAPIEventHandler{

	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

	private IWXAPI api;

	@Override
	public int getLayoutId() {
		return R.layout.activity_base;
	}

	@Override
	public void initView() {
		api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
		api.handleIntent(getIntent(), this);
	}

	@Override
	public void initData() {

	}


	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@SuppressLint("LongLogTag")
	@Override
	public void onResp(BaseResp resp) {
		Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);

		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			int errCord = resp.errCode;
			if (errCord == 0) {
				ToastUtils.showToast("支付成功！");
			} else {
				ToastUtils.showToast("支付失败");
			}
			//这里接收到了返回的状态码可以进行相应的操作，如果不想在这个页面操作可以把状态码存在本地然后finish掉这个页面，这样就回到了你调起支付的那个页面
			//获取到你刚刚存到本地的状态码进行相应的操作就可以了
			finish();
		}
	}
}