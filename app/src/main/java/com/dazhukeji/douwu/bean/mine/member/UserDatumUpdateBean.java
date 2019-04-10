package com.dazhukeji.douwu.bean.mine.member;

import com.dazhukeji.douwu.bean.publicBean.BaseBean;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/17 9:49
 * 功能描述：
 */
public class UserDatumUpdateBean extends BaseBean {

    /**
     * data : {"user_id":2,"user_name":"修改第二人啊！","user_sex":2,"user_portrait":"20181116/68f792adfa334dd51dc5b202c68ea1f5.gif","user_signature":"修改第二人签名！","user_token":"456"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_id : 2
         * user_name : 修改第二人啊！
         * user_sex : 2
         * user_portrait : 20181116/68f792adfa334dd51dc5b202c68ea1f5.gif
         * user_signature : 修改第二人签名！
         * user_token : 456
         */

        private int user_id;
        private String user_name;
        private int user_sex;
        private String user_portrait;
        private String user_signature;
        private String user_token;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getUser_sex() {
            return user_sex;
        }

        public void setUser_sex(int user_sex) {
            this.user_sex = user_sex;
        }

        public String getUser_portrait() {
            return user_portrait;
        }

        public void setUser_portrait(String user_portrait) {
            this.user_portrait = user_portrait;
        }

        public String getUser_signature() {
            return user_signature;
        }

        public void setUser_signature(String user_signature) {
            this.user_signature = user_signature;
        }

        public String getUser_token() {
            return user_token;
        }

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }
    }
}
