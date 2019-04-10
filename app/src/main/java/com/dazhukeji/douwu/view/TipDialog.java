package com.dazhukeji.douwu.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dazhukeji.douwu.R;

/**
 * 创建者：zhangyunfei
 * 时间：2019/1/6
 * 联系方式：32457127@qq.com
 * 功能描述：
 */
public class TipDialog extends Dialog {
    protected TipDialog(@NonNull Context context) {
        this(context, R.style.PayDialog);
    }

    protected TipDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    private void initView() {

    }


    public interface  OnClickListener{
        void onClick();
    }

    public static class Builder {
        private Context context;
        /**
         * 标题
         */
        private String title;
        /**
         * 提示信息
         */
        private String message;
        /**
         * 设置点击弹窗外侧是否关闭弹窗
         */
        private Boolean cancelable = true;
        /**
         * 左边按钮内容
         */
        private String positiveButtonText;
        /**
         * 右边按钮内容
         */
        private String negativeButtonText;

        /**
         * 左边按钮的点击事件
         */
        private DialogInterface.OnClickListener positiveButtonClickListener;
        /**
         * 右边按钮的点击事件
         */
        private DialogInterface.OnClickListener negativeButtonClickListener;

        /**
         * 上下文
         *
         * @param context
         */
        public Builder(Context context) {
            this.context = context;
        }

        /**
         * 提示内容
         *
         * @param message
         * @return
         */
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * 提示内容
         *
         * @param message
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        /**
         * 设置标题
         *
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * 设置标题
         *
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }
        /**
         * 设置点击外侧是否关闭
         *
         * @param cancelable false为不关闭，默认为True
         * @return
         */
        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public TipDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final TipDialog dialog = new TipDialog(context);
            View layout = inflater.inflate(R.layout.tip_dialog_layout, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
           TextView titleTv = layout.findViewById(R.id.titleTv);
            TextView contentTv = layout.findViewById(R.id.contentTv);
            titleTv.setText(title);
            contentTv.setText(message);
            if (positiveButtonText != null) {
                ((TextView)layout.findViewById(R.id.confirmTv)).setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    (layout.findViewById(R.id.confirmTv)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            }
            if (negativeButtonText != null) {
                ((TextView) layout.findViewById(R.id.cancelTv)).setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    (layout.findViewById(R.id.cancelTv)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            }
            dialog.setContentView(layout);
            dialog.setCancelable(cancelable); // 设置点击弹窗外侧是否关闭弹窗，默认为true关闭
            return dialog;
        }
    }
}
