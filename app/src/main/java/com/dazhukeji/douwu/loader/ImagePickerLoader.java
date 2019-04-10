package com.dazhukeji.douwu.loader;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.imagepicker.loader.ImageLoader;
import com.zhangyunfei.mylibrary.utils.GlideApp;

import java.io.File;


/**
 * 创建者：zhangyunfei
 * 时间：2018/10/16 0016
 * 联系方式：32457127@qq.com
 */
public class ImagePickerLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        GlideApp.with(activity)                             //配置上下文
                .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
//                .error(R.mipmap.default_image)           //设置错误图片
//                .placeholder(R.mipmap.default_image)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {

    }

    @Override
    public void clearMemoryCache() {
//这里是清除缓存的方法,根据需要自己实现
    }
}
