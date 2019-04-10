package com.dazhukeji.douwu.bean.mine.member;

import com.dazhukeji.douwu.bean.publicBean.BaseBean;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/17 14:53
 * 功能描述：
 */
public class UserCollectCurriculumBean extends BaseBean {

    /**
     * data : {"user":{"user_name":"","user_sex":1,"user_portrait":"","user_signature":""},"curriculum":[{"collect_id":3,"curriculum_id":null,"curriculum_admin":null,"curriculum_name":null,"curriculum_photo":null,"curriculum_actual_price":null,"curriculum_difficulty":null,"curriculum_identity_type":null,"curriculum_effective":null,"curriculum_start_time":null,"curriculum_over_time":null,"curriculum_state":null,"curriculum_false_delete":null}]}
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
         * user : {"user_name":"","user_sex":1,"user_portrait":"","user_signature":""}
         * curriculum : [{"collect_id":3,"curriculum_id":null,"curriculum_admin":null,"curriculum_name":null,"curriculum_photo":null,"curriculum_actual_price":null,"curriculum_difficulty":null,"curriculum_identity_type":null,"curriculum_effective":null,"curriculum_start_time":null,"curriculum_over_time":null,"curriculum_state":null,"curriculum_false_delete":null}]
         */

        private UserBean user;
        private List<CurriculumBean> curriculum;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public List<CurriculumBean> getCurriculum() {
            return curriculum;
        }

        public void setCurriculum(List<CurriculumBean> curriculum) {
            this.curriculum = curriculum;
        }

        public static class UserBean {
            /**
             * user_name : 
             * user_sex : 1
             * user_portrait : 
             * user_signature : 
             */

            private String user_name;
            private int user_sex;
            private String user_portrait;
            private String user_signature;

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
        }

        public static class CurriculumBean {
            /**
             * collect_id : 3
             * curriculum_id : null
             * curriculum_admin : null
             * curriculum_name : null
             * curriculum_photo : null
             * curriculum_actual_price : null
             * curriculum_difficulty : null
             * curriculum_identity_type : null
             * curriculum_effective : null
             * curriculum_start_time : null
             * curriculum_over_time : null
             * curriculum_state : null
             * curriculum_false_delete : null
             */

            private int collect_id;
            private String curriculum_id;
            private String curriculum_admin;
            private String curriculum_name;
            private String curriculum_photo;
            private String curriculum_actual_price;
            private String curriculum_difficulty;
            private String curriculum_identity_type;
            private String curriculum_effective;
            private String curriculum_start_time;
            private String curriculum_over_time;
            private String curriculum_state;
            private String curriculum_false_delete;

            public int getCollect_id() {
                return collect_id;
            }

            public void setCollect_id(int collect_id) {
                this.collect_id = collect_id;
            }

            public String getCurriculum_id() {
                return curriculum_id;
            }

            public void setCurriculum_id(String curriculum_id) {
                this.curriculum_id = curriculum_id;
            }

            public String getCurriculum_admin() {
                return curriculum_admin;
            }

            public void setCurriculum_admin(String curriculum_admin) {
                this.curriculum_admin = curriculum_admin;
            }

            public String getCurriculum_name() {
                return curriculum_name;
            }

            public void setCurriculum_name(String curriculum_name) {
                this.curriculum_name = curriculum_name;
            }

            public String getCurriculum_photo() {
                return curriculum_photo;
            }

            public void setCurriculum_photo(String curriculum_photo) {
                this.curriculum_photo = curriculum_photo;
            }

            public String getCurriculum_actual_price() {
                return curriculum_actual_price;
            }

            public void setCurriculum_actual_price(String curriculum_actual_price) {
                this.curriculum_actual_price = curriculum_actual_price;
            }

            public String getCurriculum_difficulty() {
                return curriculum_difficulty;
            }

            public void setCurriculum_difficulty(String curriculum_difficulty) {
                this.curriculum_difficulty = curriculum_difficulty;
            }

            public String getCurriculum_identity_type() {
                return curriculum_identity_type;
            }

            public void setCurriculum_identity_type(String curriculum_identity_type) {
                this.curriculum_identity_type = curriculum_identity_type;
            }

            public String getCurriculum_effective() {
                return curriculum_effective;
            }

            public void setCurriculum_effective(String curriculum_effective) {
                this.curriculum_effective = curriculum_effective;
            }

            public String getCurriculum_start_time() {
                return curriculum_start_time;
            }

            public void setCurriculum_start_time(String curriculum_start_time) {
                this.curriculum_start_time = curriculum_start_time;
            }

            public String getCurriculum_over_time() {
                return curriculum_over_time;
            }

            public void setCurriculum_over_time(String curriculum_over_time) {
                this.curriculum_over_time = curriculum_over_time;
            }

            public String getCurriculum_state() {
                return curriculum_state;
            }

            public void setCurriculum_state(String curriculum_state) {
                this.curriculum_state = curriculum_state;
            }

            public String getCurriculum_false_delete() {
                return curriculum_false_delete;
            }

            public void setCurriculum_false_delete(String curriculum_false_delete) {
                this.curriculum_false_delete = curriculum_false_delete;
            }
        }
    }
}
