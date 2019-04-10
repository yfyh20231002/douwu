package com.zhangyunfei.mylibrary.utils;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/18 16:02
 * 功能描述：动态权限
 */


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限组列表：
 *Android6.0只用申请权限组中一个权限及获得全部权限
 *Android8.0需要全部申请权限组权限，但是只会申请第一个权限时提示，后面不会提示
 *
 * group:android.permission-group.CONTACTS  读写联系人
 permission:android.permission.WRITE_CONTACTS
 permission:android.permission.GET_ACCOUNTS
 permission:android.permission.READ_CONTACTS

 group:android.permission-group.PHONE  读电话状态、打电话、读写电话记录
 permission:android.permission.READ_CALL_LOG
 permission:android.permission.READ_PHONE_STATE
 permission:android.permission.CALL_PHONE
 permission:android.permission.WRITE_CALL_LOG
 permission:android.permission.USE_SIP
 permission:android.permission.PROCESS_OUTGOING_CALLS
 permission:com.android.voicemail.permission.ADD_VOICEMAIL

 group:android.permission-group.CALENDAR  读写日历
 permission:android.permission.READ_CALENDAR
 permission:android.permission.WRITE_CALENDAR

 group:android.permission-group.CAMERA 相机
 permission:android.permission.CAMERA

 group:android.permission-group.SENSORS  传感器
 permission:android.permission.BODY_SENSORS

 group:android.permission-group.LOCATION  读位置信息
 permission:android.permission.ACCESS_FINE_LOCATION
 permission:android.permission.ACCESS_COARSE_LOCATION

 group:android.permission-group.STORAGE  读写存储卡
 permission:android.permission.READ_EXTERNAL_STORAGE
 permission:android.permission.WRITE_EXTERNAL_STORAGE

 group:android.permission-group.MICROPHONE  使用麦克风
 permission:android.permission.RECORD_AUDIO

 group:android.permission-group.SMS  读写短信、收发短信
 permission:android.permission.READ_SMS
 permission:android.permission.RECEIVE_WAP_PUSH
 permission:android.permission.RECEIVE_MMS
 permission:android.permission.RECEIVE_SMS
 permission:android.permission.SEND_SMS
 permission:android.permission.READ_CELL_BROADCASTS
 */
public class PermissionHelper {

    private static int mRequestCode = -1;

    private static OnPermissionListener mOnPermissionListener;

    public interface OnPermissionListener {

        void onPermissionGranted();

        void onPermissionDenied();
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean hasSelfPermissions(Context context, String... permissions) {
            for (String permission : permissions) {
                if (!hasSelfPermission(context, permission)) {
                    return false;
                }
            }
            return true;
    }

    private static boolean hasSelfPermission(Context context, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && "Xiaomi".equalsIgnoreCase(Build.MANUFACTURER)) {
            return hasSelfPermissionForXiaomi(context, permission);
        }
        try {
            return PermissionChecker.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
        } catch (RuntimeException t) {
            return false;
        }
    }

    private static boolean hasSelfPermissionForXiaomi(Context context, String permission) {
        String permissionToOp = AppOpsManagerCompat.permissionToOp(permission);
        if (permissionToOp == null) {
            // in case of normal permissions(e.g. INTERNET)
            return true;
        }
        int noteOp = AppOpsManagerCompat.noteOp(context, permissionToOp, android.os.Process.myUid(), context.getPackageName());
        return noteOp == AppOpsManagerCompat.MODE_ALLOWED && PermissionChecker.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void requestPermissions(Context context, int requestCode
            , String[] permissions, OnPermissionListener listener) {
            if (context instanceof Activity) {
                mOnPermissionListener = listener;
                List<String> deniedPermissions = getDeniedPermissions(context, permissions);
                if (deniedPermissions.size() > 0) {
                    mRequestCode = requestCode;

                    ((Activity) context).requestPermissions(deniedPermissions
                            .toArray(new String[deniedPermissions.size()]), requestCode);

                } else {
                    if (mOnPermissionListener != null)
                        mOnPermissionListener.onPermissionGranted();
                }
            } else {
                throw new RuntimeException("Context must be an Activity");
            }
    }

    /**
     * 获取请求权限中需要授权的权限
     */
    private static List<String> getDeniedPermissions(Context context, String... permissions) {
        List<String> deniedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                deniedPermissions.add(permission);
            }
        }
        return deniedPermissions;
    }

    /**
     * 请求权限结果，对应Activity中onRequestPermissionsResult()方法。
     */
    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (mRequestCode != -1 && requestCode == mRequestCode) {
            if (mOnPermissionListener != null) {
                if (verifyPermissions(grantResults)) {
                    mOnPermissionListener.onPermissionGranted();
                } else {
                    mOnPermissionListener.onPermissionDenied();
                }
            }
        }
    }

    /**
     * 验证所有权限是否都已经授权
     */
    private static boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    /**
     * 显示提示对话框
     */
    public static void showTipsDialog(final Context context) {
        new AlertDialog.Builder(context)
                .setTitle("提示信息")
                .setMessage("当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings(context);
                    }
                }).show();
    }
    /**
     * 启动当前应用设置页面
     */
    private static void startAppSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }
}
