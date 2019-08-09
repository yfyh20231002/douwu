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
    public static final String JIGUANGUSERNAME = "极光_username";
    public static final String JIGUANGAPPKEY= "极光_appkey";
    public static final String JIGUANGUSERHEAD = "极光_userhead";
    public static final String JIGUANGPASSWORD = "极光_password";

    public static String getToken() {
        String token = SharedPreferencesUtils.getInstance(ActivityStack.getInstance().topActivity()).getString(TOKEN, "");
        return token;
    }
}
