package com.dazhukeji.douwu.base;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dazhukeji.douwu.loader.ImagePickerLoader;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.zhangyunfei.mylibrary.base.BaseActivity;
import com.zhangyunfei.mylibrary.base.IPresenter;
import com.zhangyunfei.mylibrary.utils.ToastUtils;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/13 10:52
 * 功能描述：
 */
public abstract class BaseAty<T extends IPresenter> extends BaseActivity {

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text, int gravity) {
        ToastUtils.showShort(text, gravity);
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId, int gravity) {
        ToastUtils.showShort(resId, gravity);
    }

    public void showToastWithImg(String text, int res) {
        ToastUtils.showToastWithImg(text, res);
    }


    public void onBeBack(View view) {
        finish();
    }


    /**
     * 复制字符串内容
     *
     * @param contentStr
     */
    public void copyText(String contentStr) {
        if (!contentStr.isEmpty()) {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("simple text", contentStr);
            clipboardManager.setPrimaryClip(clipData);
            ToastUtils.showToast("复制成功");
        } else {
            ToastUtils.showToast("复制失败，内容为空");
        }
    }


    public void setImagePicker(){
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setMultiMode(false);
        imagePicker.setImageLoader(new ImagePickerLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(false);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

}
