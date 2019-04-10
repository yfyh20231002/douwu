package com.zhangyunfei.mylibrary.utils;

import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/8/8 11:35
 * 功能描述：Android 应用获取通知栏权限是否开启，并跳转设置界面
 */
public class NotificationsUtils {
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

    /**
     * android 8.0 之前获取通知栏开关状态
     * @param context
     * @return
     */
    public static boolean isNotificationEnabled(Context context) {

        AppOpsManager mAppOps =
                (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);

        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;
        Class appOpsClass = null;

        /* Context.APP_OPS_MANAGER */
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());

            Method checkOpNoThrowMethod =
                    appOpsClass.getMethod(CHECK_OP_NO_THROW,
                            Integer.TYPE, Integer.TYPE, String.class);

            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
            int value = (Integer) opPostNotificationValue.get(Integer.class);

            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) ==
                    AppOpsManager.MODE_ALLOWED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * android 8.0 获取通知栏开关状态
     * @param context
     * @param pkg
     * @param uid
     * @return
     */
    public static boolean isEnableV26(Context context, String pkg, int uid) {
        try {
            NotificationManager notificationManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            Method sServiceField = notificationManager.getClass().getDeclaredMethod("getService");
            sServiceField.setAccessible(true);
            Object sService = sServiceField.invoke(notificationManager);

            Method method = sService.getClass().getDeclaredMethod("areNotificationsEnabledForPackage"
                    , String.class, Integer.TYPE);
            method.setAccessible(true);
            return (boolean) method.invoke(sService, pkg, uid);
        } catch (Exception e) {
            return true;
        }
    }

//android 8.0 显示通知的方式
    //        final String CHANNEL_ID = "channel_id_1";
    //        final String CHANNEL_NAME = "channel_name_1";
    //
    //        NotificationManager mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    //        Notification.Builder builder = null;
    //
    //
    //        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
    //            builder = new Notification.Builder(this);
    //        }else{
    //            /**
    //             * Oreo不用Priority了，用importance
    //             * IMPORTANCE_NONE 关闭通知
    //             * IMPORTANCE_MIN 开启通知，不会弹出，但没有提示音，状态栏中无显示
    //             * IMPORTANCE_LOW 开启通知，不会弹出，不发出提示音，状态栏中显示
    //             * IMPORTANCE_DEFAULT 开启通知，不会弹出，发出提示音，状态栏中显示
    //             * IMPORTANCE_HIGH 开启通知，会弹出，发出提示音，状态栏中显示
    //             */
    //            NotificationChannel notificationChannel = new
    //                    NotificationChannel(CHANNEL_ID ,CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH); //如果这里用IMPORTANCE_NOENE就需要在系统的设置里面开启渠道， //通知才能正常弹出
    //            mManager.createNotificationChannel(notificationChannel);
    //            builder = new Notification.Builder(this,CHANNEL_ID);
    //            builder.setSmallIcon(R.drawable.jpush_notification_icon);
    //            builder.setContentTitle("无界优品");
    //            builder.setContentText("");
    //        }
    //        Notification notification = builder.build();
    //        mManager.notify(555, notification);

}
