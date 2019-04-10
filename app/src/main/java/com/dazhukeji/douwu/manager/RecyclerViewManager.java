package com.dazhukeji.douwu.manager;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * 创建者：zhangyunfei
 * 时间：2018/11/14 0014
 * 联系方式：32457127@qq.com
 */
public class RecyclerViewManager {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;

    public RecyclerViewManager(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }
    public void setLinearLayoutManager(int orizentation) {
        mLinearLayoutManager=new LinearLayoutManager(mRecyclerView.getContext(),orizentation,false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
    }
    public void setGridLayoutManager(int spanCount) {
        mGridLayoutManager = new GridLayoutManager(mRecyclerView.getContext(),spanCount);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
    }
}
