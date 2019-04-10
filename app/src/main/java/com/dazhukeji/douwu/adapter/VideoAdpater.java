package com.dazhukeji.douwu.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.bean.publicBean.VideoBean;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.utils.DenisityUtils;
import com.zhangyunfei.mylibrary.utils.GlideApp;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/15 9:59
 * 功能描述：
 */
public class VideoAdpater extends BaseQuickAdapter<VideoBean, BaseViewHolder> {
    private Context mContext;


    public VideoAdpater(int layoutResId, @Nullable List<VideoBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;


    }

    @Override
    protected void convert(BaseViewHolder helper, VideoBean item) {
        helper.setText(R.id.name_tv, item.getFile_name());
        helper.setText(R.id.num_tv, String.valueOf(item.getFile_collection()));
        int file_category = item.getFile_category();
        if (file_category == 1) {
            String file_cover = item.getFile_cover();
            if (!TextUtils.isEmpty(file_cover)) {
                ImageView coverImg = helper.getView(R.id.coverImg);
                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + file_cover).diskCacheStrategy(DiskCacheStrategy.NONE).into(coverImg);
            }
            String user_portrait = item.getUser_portrait();
            if (!TextUtils.isEmpty(user_portrait)) {
                ImageView headImg = helper.getView(R.id.head_img);
                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + user_portrait).circleCrop().into(headImg);
            }

            helper.setText(R.id.contentTv, item.getUser_name());
        } else if (file_category == 2) {
            String file_cover = item.getFile_cover();
            if (!TextUtils.isEmpty(file_cover)) {
                ImageView coverImg = helper.getView(R.id.coverImg);
                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + file_cover).into(coverImg);
            }
            String teacher_portrait = item.getTeacher_portrait();
            if (!TextUtils.isEmpty(teacher_portrait)) {
                ImageView headImg = helper.getView(R.id.head_img);
                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + teacher_portrait).circleCrop().into(headImg);
            }
            helper.setText(R.id.contentTv, item.getTeacher_name());
        } else {
            String file_cover = item.getFile_cover();
            if (!TextUtils.isEmpty(file_cover)) {
                ImageView coverImg = helper.getView(R.id.coverImg);
                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + file_cover).into(coverImg);
            }
            String organization_portrait = item.getOrganization_portrait();
            if (!TextUtils.isEmpty(organization_portrait)) {
                ImageView headImg = helper.getView(R.id.head_img);
                GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + organization_portrait).circleCrop().into(headImg);
            }
            helper.setText(R.id.contentTv, item.getOrganization_name());
        }

    }
}
