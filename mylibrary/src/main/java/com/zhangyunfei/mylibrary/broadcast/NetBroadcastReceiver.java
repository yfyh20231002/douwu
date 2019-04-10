package com.zhangyunfei.mylibrary.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.zhangyunfei.mylibrary.base.BaseActivity;
import com.zhangyunfei.mylibrary.utils.NetWorkUtils;


/**
 * 创建者：zhangyunfei
 * 创建时间：2018/6/29 17:13
 * 功能描述：自定义检查手机网络状态是否切换的广播接受器
 * 联系方式：
 */
public class NetBroadcastReceiver extends BroadcastReceiver {
    private NetEvent mEvent= BaseActivity.mEvent;
    @Override
    public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                int netWorkState = NetWorkUtils.getNetWorkState(context);
                mEvent.onNetChange(netWorkState);
            }
    }
}
