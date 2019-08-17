package com.dazhukeji.douwu.ui.aty.mine;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.adapter.MemberChatAdpater;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.manager.RecyclerViewManager;
import com.dazhukeji.douwu.view.LineItemDecoration;

import java.util.List;

import butterknife.BindView;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.Conversation;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/28 9:31
 * 功能描述：私信列表
 */
public class MemberChatAty extends BaseAty {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.chat_recyclerView)
    RecyclerView chatRecyclerView;

    private RecyclerViewManager mRecyclerViewManager;
    private List<Conversation> mList;
    private MemberChatAdpater mMemberChatAdpater;

    @Override
    public int getLayoutId() {
        return R.layout.activity_member_chat;
    }

    @Override
    public void initView() {
        txtTitle.setText("我的聊天");
        mRecyclerViewManager = new RecyclerViewManager(chatRecyclerView);
        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.VERTICAL);
        mList = JMessageClient.getConversationList();
        if (null != mList && mList.size()>0){
            mMemberChatAdpater = new MemberChatAdpater(R.layout.member_chat_item, mList);
            chatRecyclerView.setAdapter(mMemberChatAdpater);
            LineItemDecoration decor = new LineItemDecoration(mContext, DividerItemDecoration.VERTICAL);
            decor.setDrawable(ContextCompat.getDrawable(mContext,R.drawable.shape_line));
            chatRecyclerView.addItemDecoration(decor);
            mMemberChatAdpater.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Conversation conversation = mList.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putString("targetId",conversation.getTargetId());
                    startActivity(MemberChatDetailsAty.class,bundle);
                }
            });
        }else {
            Toast.makeText(this, "暂无会话列表", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void initData() {

    }

}
