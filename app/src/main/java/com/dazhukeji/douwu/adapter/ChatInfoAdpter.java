package com.dazhukeji.douwu.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.R;
import com.zhangyunfei.mylibrary.utils.GlideApp;
import com.zhangyunfei.mylibrary.utils.JSONUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/28 11:24
 * 功能描述：
 */
public class ChatInfoAdpter extends BaseQuickAdapter<Message,BaseViewHolder> {

    public ChatInfoAdpter(int layoutResId, @Nullable List<Message> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Message item) {
        LinearLayout leftLinearLayout=helper.getView(R.id.leftLinearLayout);
        LinearLayout rightLinearLayout=helper.getView(R.id.rightLinearLayout);
        Map<String, String> map = JSONUtils.parseKeyAndValueToMap(item.getContent().toJson());
        if (helper.getLayoutPosition()%2==0){
            leftLinearLayout.setVisibility(View.VISIBLE);
            rightLinearLayout.setVisibility(View.GONE);
            helper.setText(R.id.leftText,map.get("text"));
            ImageView leftHead = helper.getView(R.id.leftHead);
            JMessageClient.getUserInfo(item.getTargetID(), item.getTargetAppKey(), new GetUserInfoCallback() {
                @Override
                public void gotResult(int i, String s, UserInfo userInfo) {
                    GlideApp.with(leftHead.getContext()).load(userInfo.getAvatarFile()).circleCrop().into(leftHead);
                    helper.setText(R.id.nameTv,userInfo.getUserName());
                }
            });
        }else {
            leftLinearLayout.setVisibility(View.GONE);
            rightLinearLayout.setVisibility(View.VISIBLE);
            helper.setText(R.id.rightText,map.get("text"));
            ImageView rightHead = helper.getView(R.id.rightHead);
            UserInfo myInfo = JMessageClient.getMyInfo();
            File avatarFile = myInfo.getAvatarFile();
            GlideApp.with(rightHead.getContext()).load(avatarFile).circleCrop().into(rightHead);
        }

    }
}
