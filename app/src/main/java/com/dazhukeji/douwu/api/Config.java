package com.dazhukeji.douwu.api;

import android.text.TextUtils;

import com.zhangyunfei.mylibrary.common.ActivityStack;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.utils.JSONUtils;
import com.zhangyunfei.mylibrary.utils.SharedPreferencesUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;


/**
 * 创建者：zhangyunfei
 * 时间：2018/10/14 0014
 * 联系方式：32457127@qq.com
 */
public class Config {
    public static final int IMAGE_PICKER = 100;
    public static final int IMAGE_PICKER2 = 101;
    public static final int IMAGE_PICKER3 = 102;
    public static final int VIDEO_PICKER = 103;
    public static final int MUSIC_PICKER = 104;

    public static  boolean isLogin(){
        boolean isLogin= SharedPreferencesUtils.getInstance(ActivityStack.getInstance().topActivity()).getBoolean(Constant.ISLOGIN,false);
        return isLogin;
    }

    public static void setToken(String token){
        if (TextUtils.isEmpty(token)){
            SharedPreferencesUtils.getInstance(ActivityStack.getInstance().topActivity()).putBoolean(Constant.ISLOGIN,false);
        }else {
            SharedPreferencesUtils.getInstance(ActivityStack.getInstance().topActivity()).putBoolean(Constant.ISLOGIN,true);
        }
        SharedPreferencesUtils.getInstance(ActivityStack.getInstance().topActivity()).putString(ApiConfig.TOKEN,token);
    }

    public static String getJson(ResponseBody responseBody){
        try {
            return responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Map<String, String> getMap(ResponseBody responseBody){
        String jsonStr = getJson(responseBody);
        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(jsonStr);
        return map;
    }
}
