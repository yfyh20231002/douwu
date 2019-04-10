package com.zhangyunfei.mylibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/3 15:30
 * 功能描述：SharedPreferences工具类
 * 联系方式：
 */
public class SharedPreferencesUtils {
    private volatile static SharedPreferencesUtils mPreferencesUtils=null;

    private SharedPreferences mSharedPreferences;
    private SharedPreferencesUtils(Context context){
        mSharedPreferences=context.getSharedPreferences("SharedPreferencesUtils",Context.MODE_PRIVATE);
    }

    public static SharedPreferencesUtils getInstance(Context context){
        if (mPreferencesUtils==null){
                synchronized (SharedPreferencesUtils.class){
                    if (mPreferencesUtils==null) {
                        mPreferencesUtils = new SharedPreferencesUtils(context);
                    }
            }
        }
        return mPreferencesUtils;
    }

    public boolean putString(String key,String value){
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putString(key,value);
        return edit.commit();
    }

    public  String getString(String key,String defaultValue){
        return mSharedPreferences.getString(key,defaultValue);
    }


    public boolean putBoolean(String key,boolean value){
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putBoolean(key,value);
        return edit.commit();
    }

    public  boolean getBoolean(String key,boolean defaultValue){
        return mSharedPreferences.getBoolean(key,defaultValue);
    }

    public boolean putInt(String key,int value){
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putInt(key,value);
        return edit.commit();
    }

    public  int getInt(String key,int defaultValue){
        return mSharedPreferences.getInt(key,defaultValue);
    }
}
