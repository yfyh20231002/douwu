package com.dazhukeji.douwu.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/22 11:29
 * 功能描述：
 */
public class MyEditText extends AppCompatEditText implements TextWatcher {
    private String content = getText().toString();

    public MyEditText(Context context) {
        this(context, null);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (content.length() > 0) {
            setSelection(content.length());
        }
        addTextChangedListener(this);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        content = s.toString();
    }

    public String getContent() {
        return content;
    }
}
