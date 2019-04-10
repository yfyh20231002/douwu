package com.zhangyunfei.mylibrary.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhangyunfei.mylibrary.R;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/11 9:53
 * 功能描述：
 */
public class ProgressDialog extends Dialog {

    private Context mContext;
    private ProgressBar mProgressBar;
    private TextView mTextView;

    public ProgressDialog(@NonNull Context context) {
        this(context, R.style.DialogStyle);
    }

    public ProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext=context;
        init();
    }

    private void init() {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_progress,null);
        mProgressBar = view.findViewById(R.id.progressBar);
        mTextView = view.findViewById(R.id.tipTv);
        setContentView(view);
    }

    public void setText(CharSequence tip){
        if (mTextView != null){
            mTextView.setText(tip);
        }
    }
}
