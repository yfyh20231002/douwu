package com.dazhukeji.douwu.ui.aty.mine;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.adapter.ChatInfoAdpter;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.manager.RecyclerViewManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/28 10:43
 * 功能描述：
 */
public class MemberChatDetailsAty extends BaseAty {

    @BindView(R.id.txt_title)
    TextView txtTitle;
//    @BindView(R.id.headsRecyclerView)
//    RecyclerView headsRecyclerView;
    @BindView(R.id.chatInfoRecyclerView)
    RecyclerView chatInfoRecyclerView;

    private RecyclerViewManager mRecyclerViewManager;
    private List<Object> mList;
    @Override
    public int getLayoutId() {
        return R.layout.activity_member_chat_details;
    }

    @Override
    public void initView() {
        txtTitle.setText("How are you doing");
        mList=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mList.add(new Object());
        }
//        mRecyclerViewManager = new RecyclerViewManager(headsRecyclerView);
//        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.HORIZONTAL);
//        headsRecyclerView.setAdapter(new ChatHeadsAdapter(R.layout.heads_item,mList));
//        headsRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                super.getItemOffsets(outRect, view, parent, state);
//                if (parent.getChildAdapterPosition(view)!=state.getItemCount()-1){
//                    outRect.right= DisplayHelper.dp2px(mContext,17);
//                }
//            }
//        });
        mRecyclerViewManager = new RecyclerViewManager(chatInfoRecyclerView);
        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.VERTICAL);
        chatInfoRecyclerView.setAdapter(new ChatInfoAdpter(R.layout.chat_info_item,mList));
    }

    @Override
    public void initData() {

    }

}
