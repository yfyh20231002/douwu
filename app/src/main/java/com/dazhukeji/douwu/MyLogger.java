package com.dazhukeji.douwu;


import com.orhanobut.logger.Logger;

import java.lang.reflect.Type;
import java.util.Map;

import cn.jmessage.support.google.gson.Gson;
import cn.jmessage.support.google.gson.reflect.TypeToken;

/**
 * 朱海龙
 */
public final class MyLogger {

    public static final Gson GSON = new Gson();

    public static final void printJsonOfError(Object o) {
        Logger.json(GSON.toJson(o));
    }

    public static final void printMap(Map<String, String> map) {
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Logger.e(GSON.toJson(map, type));
    }

    public static final void printStr(String str) {
        Logger.e(str);
    }

}
