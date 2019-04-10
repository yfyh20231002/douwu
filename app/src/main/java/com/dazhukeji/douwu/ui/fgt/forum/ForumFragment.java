package com.dazhukeji.douwu.ui.fgt.forum;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.base.BaseFgt;

import butterknife.BindView;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/14 11:48
 * 功能描述：
 */
public class ForumFragment extends BaseFgt {
    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.txt_title)
    TextView txtTitle;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_forum;
    }

    @Override
    protected void initialized(View view) {
        backImg.setVisibility(View.GONE);
        txtTitle.setText("论坛");
    }

    @Override
    protected void requestData() {

    }

}
