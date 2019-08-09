package com.dazhukeji.douwu;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.zhangyunfei.mylibrary.base.BaseApplication;
import com.zhangyunfei.mylibrary.utils.LogUtils;

import cn.jpush.im.android.api.JMessageClient;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/13 10:55
 * 功能描述：
 */
public class DwApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag("hailong")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        LogUtils.isDebug = false;
        JMessageClient.setDebugMode(LogUtils.isDebug);
        JMessageClient.init(this);

    }
}
