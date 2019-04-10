package com.dazhukeji.douwu.bean.login;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/6 16:59
 * 功能描述：
 */
public class LoginBean implements Parcelable{


    /**
     * user_token : a41aa6d381d7a350f2560d1dad91f075
     * jmphone : 18338699733
     * jmpassword : yuewu2019
     */

    private String user_token;
    private String jmphone;
    private String jmpassword;

    protected LoginBean(Parcel in) {
        user_token = in.readString();
        jmphone = in.readString();
        jmpassword = in.readString();
    }

    public static final Creator<LoginBean> CREATOR = new Creator<LoginBean>() {
        @Override
        public LoginBean createFromParcel(Parcel in) {
            return new LoginBean(in);
        }

        @Override
        public LoginBean[] newArray(int size) {
            return new LoginBean[size];
        }
    };

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public String getJmphone() {
        return jmphone;
    }

    public void setJmphone(String jmphone) {
        this.jmphone = jmphone;
    }

    public String getJmpassword() {
        return jmpassword;
    }

    public void setJmpassword(String jmpassword) {
        this.jmpassword = jmpassword;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user_token);
        dest.writeString(jmphone);
        dest.writeString(jmpassword);
    }
}
