package com.dazhukeji.douwu.bean.publicBean;

/**
 * 创建者：zhangyunfei
 * 时间：2019/1/8
 * 联系方式：32457127@qq.com
 * 功能描述：
 */
public class MessageEvent {
    private String message;
    private String label;

    public MessageEvent(String message) {
        this.message = message;
    }

    public MessageEvent(String message, String label) {
        this.message = message;
        this.label = label;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
