package com.zhangyunfei.mylibrary.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


;import com.zhangyunfei.mylibrary.R;
import com.zhangyunfei.mylibrary.base.BaseApplication;
import com.zhangyunfei.mylibrary.common.ActivityStack;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/18 15:15
 * 功能描述：Toast统一管理类
 */
public class ToastUtils {
    private static Toast toast;
    private static Toast toast2;

    private static Toast initToast(CharSequence message, int duration, int gravity) {
        if (toast != null) {
            toast = null;
        }
        toast = Toast.makeText(ActivityStack.getInstance().topActivity(), message, duration);
        toast.setText(message);
        toast.setGravity(gravity, 0, 0);
        toast.setDuration(duration);
        return toast;
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(CharSequence message, int gravity) {
        initToast(message, Toast.LENGTH_SHORT, gravity).show();
    }


    /**
     * 短时间显示Toast
     *
     * @param strResId
     */
    public static void showShort(int strResId, int gravity) {
        initToast(BaseApplication.getAppContext().getResources().getText(strResId), Toast.LENGTH_SHORT, gravity).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message, int gravity) {
        initToast(message, Toast.LENGTH_LONG, gravity).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param strResId
     */
    public static void showLong(int strResId, int gravity) {
        initToast(BaseApplication.getAppContext().getResources().getText(strResId), Toast.LENGTH_LONG, gravity).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(CharSequence message, int duration, int gravity) {
        initToast(message, duration, gravity).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param strResId
     * @param duration
     */
    public static void show(Context context, int strResId, int duration, int gravity) {
        initToast(context.getResources().getText(strResId), duration, gravity).show();
    }

    /**
     * 显示有image的toast
     *
     * @param tvStr
     * @param imageResource
     * @return
     */
    public static Toast showToastWithImg(final String tvStr, final int imageResource) {
        if (toast2 == null) {
            toast2 = new Toast(BaseApplication.getAppContext());
        }
        View view = LayoutInflater.from(BaseApplication.getAppContext()).inflate(R.layout.toast_custom, null);
        TextView tv = (TextView) view.findViewById(R.id.toast_custom_tv);
        tv.setText(TextUtils.isEmpty(tvStr) ? "" : tvStr);
        ImageView iv = (ImageView) view.findViewById(R.id.toast_custom_iv);
        if (imageResource > 0) {
            iv.setVisibility(View.VISIBLE);
            iv.setImageResource(imageResource);
        } else {
            iv.setVisibility(View.GONE);
        }
        toast2.setView(view);
        toast2.setGravity(Gravity.CENTER, 0, 0);
        toast2.show();
        return toast2;

    }

    public static void showToast(String msg) {
        initToast(msg,Toast.LENGTH_SHORT,Gravity.CENTER).show();
    }
}
