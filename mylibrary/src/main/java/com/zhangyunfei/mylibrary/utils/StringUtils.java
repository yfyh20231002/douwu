package com.zhangyunfei.mylibrary.utils;

import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 创建者：zhangyunfei
 * 时间：2018/10/14 0014
 * 联系方式：32457127@qq.com
 */
public class StringUtils {
    /**
            * 判断手机号是否符合规范
     * @param phoneNo 输入的手机号
     * @return
             */
    public static boolean isPhoneNumber(String phoneNo) {
        if (TextUtils.isEmpty(phoneNo)) {
            return false;
        }
        if (phoneNo.length() == 11) {
            for (int i = 0; i < 11; i++) {
                if (!PhoneNumberUtils.isISODigit(phoneNo.charAt(i))) {
                    return false;
                }
            }
            Pattern p = Pattern.compile("^((13[^4,\\D])" + "|(134[^9,\\D])" +
                    "|(14[5,7])" +
                    "|(15[^4,\\D])" +
                    "|(17[3,6-8])" +
                    "|(18[0-9]))\\d{8}$");
            Matcher m = p.matcher(phoneNo);
            return m.matches();
        }
        return false;
    }

    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    public static boolean isEmpty(List  list) {

        if (list == null || list.size() == 0){
            return true;
        }
        return false;
    }
}
