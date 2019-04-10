package com.zhangyunfei.mylibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/9/29 15:14
 * 功能描述：
 */
public class BitmapUtils {

    private static BitmapUtils instance;

    private BitmapUtils() {
    }

    public static BitmapUtils gainInstance() {
        synchronized (BitmapUtils.class) {
            if (null == instance) {
                instance = new BitmapUtils();
            }
            return instance;
        }
    }

    public void savePic(final Context context, final String url, final String picName, final Listener listener){
//        getBitmap(context,url,picName,listener);
        new Thread(new Runnable() {
            @Override
            public void run() {
                getBitmap(context,url,picName,listener);
            }
        }).start();
    }

    private void getBitmap(Context context,String imageUrl,String picName,  Listener listener){
        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL(imageUrl);
            conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if(conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                saveBmp2Gallery(context,bitmap,picName,listener);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            conn.disconnect();
        }

    }

    public interface Listener {
        void saveSuccess();
    }

    public void saveBmp2Gallery(Context context, Bitmap bmp, String picName, Listener listener) {

        String fileName = null;
        //系统相册目录
//        String galleryPath = Environment.getExternalStorageDirectory()
//                + File.separator + Environment.DIRECTORY_DCIM
//                + File.separator + "Camera" + File.separator;
        String galleryPath = Environment.getExternalStorageDirectory().toString();


        // 声明文件对象
        File file = null;
        // 声明输出流
        FileOutputStream outStream = null;

        try {
            // 如果有目标文件，直接获得文件对象，否则创建一个以filename为名称的文件
            file = new File(galleryPath, picName + ".jpg");

            // 获得文件相对路径
            fileName = file.toString();
            // 获得输出流，如果文件中有内容，追加内容

            outStream = new FileOutputStream(fileName);
            if (null != outStream) {
                bmp.compress(Bitmap.CompressFormat.JPEG, 80, outStream);
            }
            outStream.flush();
            listener.saveSuccess();
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //通知相册更新
        MediaStore.Images.Media.insertImage(context.getContentResolver(), bmp, fileName, null);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
    }
}
