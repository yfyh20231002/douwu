package com.zhangyunfei.mylibrary.utils;

import android.net.Uri;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/17 10:40
 * 功能描述：
 */
public class UploadHelper {
    private volatile static UploadHelper mInstance;
    public static Map<String, RequestBody> params;
    private Uri photoUri;

    private UploadHelper() {}

    //单例模式
    public static UploadHelper getInstance() {
        if (mInstance == null) {
            synchronized (UploadHelper.class) {
                if (mInstance == null)
                    mInstance = new UploadHelper();
                params = new HashMap<>();
            }
        }
        return mInstance;
    }

    //根据传进来的Object对象来判断是String还是File类型的参数
    public UploadHelper addParameter(String key, Object o) {
        RequestBody body = null;
        if (o instanceof String) {
            body = RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"), (String) o);
        } else if (o instanceof File) {
            body = RequestBody.create(MediaType.parse("multipart/form-data;charset=UTF-8"), (File) o);
        }
        params.put(key, body);
        return this;
    }

    //建造者模式
    public Map<String, RequestBody> builder() {
        return params;
    }

    //清除参数
    public void clear(){
        params.clear();
    }

    //最终上传的Bitmap保存为File对象
//    public void saveBitmapFile(List<Bitmap> mList, File[] files) {
//        String root = Environment.getExternalStorageDirectory().toString();
//        File myDir = new File(root + "/suggestionUpload");
//        if (myDir.exists()) {
//            myDir.delete();
//        }
//        myDir.mkdirs();
//        for (int i = 0; i < mList.size(); i++) {
//            files[i] = new File(myDir, "ims" + i + ".JPEG");
//            try {
//                if (files[i].exists()) {
//                    files[i].delete();
//                }
//                files[i].createNewFile();
//                FileOutputStream out = new FileOutputStream(files[i]);
//                mList.get(i).compress(Bitmap.CompressFormat.JPEG, 100, out);
//                out.flush();
//                out.close();
//                Log.e("最终上传图片的路径------>" , files[i].getAbsolutePath());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    //启用裁剪
//    public void startPhotoZoom(Activity mContext, List<String> drr) {
//        try {
//            // 获取系统时间 然后将裁剪后的图片保存至指定的文件夹
//            @SuppressLint("SimpleDateFormat")
//            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
//            String address = sDateFormat.format(new Date());
//            if (!FileUtils.isFileExist("")) {
//                FileUtils.createSDDir("");
//            }
//            drr.add(FileUtils.SDPATH + address + ".JPEG");
//            @SuppressLint("SdCardPath")
//            Uri imageUri = Uri.parse("file:///sdcard/formats/" + address + ".JPEG");
//            final Intent intent = new Intent("com.android.camera.action.CROP");
//            // 照片URL地址
//            intent.setDataAndType(photoUri, "image/*");
//            intent.putExtra("crop", "true");
//            intent.putExtra("aspectX", 1);
//            intent.putExtra("aspectY", 1);
//            intent.putExtra("outputX", 480);
//            intent.putExtra("outputY", 480);
//            // 输出路径
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//            // 输出格式
//            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
//            // 不启用人脸识别
//            intent.putExtra("noFaceDetection", false);
//            intent.putExtra("return-data", false);
//            mContext.startActivityForResult(intent, Constant.REQUEST_CUTTING_CODE);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //拍照
//    public void takePhoto(Activity mContext) {
//        try {
//            Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            String sdcardState = Environment.getExternalStorageState();
//            String sdcardPathDir = Environment.getExternalStorageDirectory().getPath() + "/myImage/";
//            File file = null;
//            if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
//                // 有sd卡，是否有myImage文件夹
//                File fileDir = new File(sdcardPathDir);
//                if (!fileDir.exists()) {
//                    fileDir.mkdirs();
//                }
//                // 是否有headImg文件
//                file = new File(sdcardPathDir + System.currentTimeMillis() + ".JPEG");
//            }
//            if (file != null) {
//                String path = file.getPath();
//                photoUri = Uri.fromFile(file);
//                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
//                mContext.startActivityForResult(openCameraIntent, Constant.REQUEST_CAMERA_CODE);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    //从相册选择
//    public void albmSelect(Activity mContext, int maxCanSelectNum, List<String> drr) {
//        int selectedMode = MultiImageSelectorActivity.MODE_MULTI;
//        int maxNum = maxCanSelectNum - drr.size();
//        Intent intent = new Intent(mContext, MultiImageSelectorActivity.class);
//        // 是否显示拍摄图片
//        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, false);
//        // 最大可选择图片数量
//        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxNum);
//        // 选择模式
//        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, selectedMode);
//        mContext.startActivityForResult(intent, Constant.REQUEST_ALBUM_CODE);
//
//    }
}
