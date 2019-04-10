package com.dazhukeji.douwu.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.MyLogger;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.bean.home.organization.OrganizationBean;
import com.dazhukeji.douwu.view.RatingBar;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.utils.DenisityUtils;
import com.zhangyunfei.mylibrary.utils.GlideApp;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/15 14:17
 * 功能描述：
 */
public class DanceOrgAdapter extends BaseQuickAdapter<OrganizationBean, BaseViewHolder> {

    int itemW;
    int itemH;

    public DanceOrgAdapter(Context context, int layoutResId, @Nullable List<OrganizationBean> data) {
        super(layoutResId, data);

        BitmapFactory.Options options = new BitmapFactory.Options();
        BitmapFactory.decodeResource(context.getResources(),R.drawable.icon_dance_item_bg,options);

        //获取图片的宽高
        itemW = options.outHeight;
        itemH = options.outWidth;

        MyLogger.printStr(itemW + "," + itemH);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrganizationBean item) {
        /*@BindView(R.id.title_tv)
        TextView titleTv;
        @BindView(R.id.classify_tv)
        TextView classifyTv;
        @BindView(R.id.address_tv)
        TextView addressTv;
        @BindView(R.id.ratingBar)
        RatingBar ratingBar;
        @BindView(R.id.score_tv)
        TextView scoreTv;*/
        helper.setText(R.id.title_tv, item.getOrganization_name());
        helper.setText(R.id.classify_tv, item.getOrganization_type());
        helper.setText(R.id.address_tv, item.getOrganization_site());
        int organization_level = item.getOrganization_level();
        RatingBar ratingBar = helper.getView(R.id.ratingBar);
        ratingBar.setStar(organization_level);
        helper.setText(R.id.score_tv, String.valueOf(organization_level));
        GlideApp.with(mContext).load(ApiConfig.BASE_IMG_URL + item.getOrganization_cover())
                .into(new SimpleTarget<Drawable>(itemW, itemH){
            @Override
            public void onResourceReady (@NonNull Drawable resource, @Nullable Transition < ? super
            Drawable > transition){
                helper.getView(R.id.daceorg_item).setBackground(resource);
            }
        });

    }
}
