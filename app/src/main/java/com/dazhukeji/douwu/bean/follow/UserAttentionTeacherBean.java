package com.dazhukeji.douwu.bean.follow;

import com.dazhukeji.douwu.bean.publicBean.BaseBean;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/17 16:13
 * 功能描述：
 */
public class UserAttentionTeacherBean extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_teacher_id : 1
         * user_id : 2
         * teacher_portrait : 教师头像
         * teacher_name : 老师姓名2
         * teacher_attention : 65455
         * teacher_level : 5
         * teacher_master : 街舞
         * teacher_intro : 非常优秀
         */

        private int user_teacher_id;
        private int user_id;
        private String teacher_portrait;
        private String teacher_name;
        private int teacher_attention;
        private int teacher_level;
        private String teacher_master;
        private String teacher_intro;

        public int getUser_teacher_id() {
            return user_teacher_id;
        }

        public void setUser_teacher_id(int user_teacher_id) {
            this.user_teacher_id = user_teacher_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getTeacher_portrait() {
            return teacher_portrait;
        }

        public void setTeacher_portrait(String teacher_portrait) {
            this.teacher_portrait = teacher_portrait;
        }

        public String getTeacher_name() {
            return teacher_name;
        }

        public void setTeacher_name(String teacher_name) {
            this.teacher_name = teacher_name;
        }

        public int getTeacher_attention() {
            return teacher_attention;
        }

        public void setTeacher_attention(int teacher_attention) {
            this.teacher_attention = teacher_attention;
        }

        public int getTeacher_level() {
            return teacher_level;
        }

        public void setTeacher_level(int teacher_level) {
            this.teacher_level = teacher_level;
        }

        public String getTeacher_master() {
            return teacher_master;
        }

        public void setTeacher_master(String teacher_master) {
            this.teacher_master = teacher_master;
        }

        public String getTeacher_intro() {
            return teacher_intro;
        }

        public void setTeacher_intro(String teacher_intro) {
            this.teacher_intro = teacher_intro;
        }
    }
}
