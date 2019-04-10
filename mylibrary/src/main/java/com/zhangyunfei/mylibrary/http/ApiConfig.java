package com.zhangyunfei.mylibrary.http;

import com.zhangyunfei.mylibrary.common.ActivityStack;
import com.zhangyunfei.mylibrary.utils.SharedPreferencesUtils;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/3 11:46
 * 功能描述：
 */
public class ApiConfig {
    public static final String BASE_URL = "http://yuewu.dazhu-ltd.cn/index.php/api/";
    public static final String BASE_IMG_URL = "http://yuewu.dazhu-ltd.cn/public/uploads/";
    public static final String TOKEN = "token";

    public static String getToken() {
        String token = SharedPreferencesUtils.getInstance(ActivityStack.getInstance().topActivity()).getString(TOKEN, "");
        return token;
    }
}
