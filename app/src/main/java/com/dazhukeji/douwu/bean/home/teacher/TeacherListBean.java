package com.dazhukeji.douwu.bean.home.teacher;

import com.dazhukeji.douwu.bean.publicBean.BaseBean;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/20 10:06
 * 功能描述：
 */
public class TeacherListBean extends BaseBean {


    /**
     * data : {"dance_type":[{"dance_type_id":1,"dance_type_name":"街舞"},{"dance_type_id":2,"dance_type_name":"现代舞"},{"dance_type_id":3,"dance_type_name":"摩登舞"},{"dance_type_id":4,"dance_type_name":"拉丁舞"},{"dance_type_id":5,"dance_type_name":"民族舞"},{"dance_type_id":6,"dance_type_name":"儿童舞"},{"dance_type_id":7,"dance_type_name":"踢踏舞"},{"dance_type_id":8,"dance_type_name":"爵士舞"},{"dance_type_id":9,"dance_type_name":"芭蕾舞"},{"dance_type_id":10,"dance_type_name":"机械舞"}],"teacher":[{"user_teacher_id":1,"user_id":2,"teacher_portrait":"20190000/z.jpg","teacher_name":"教师姓名","teacher_attention":65455,"teacher_level":5,"teacher_master":"舞蹈类型","teacher_intro":"文档教师"},{"user_teacher_id":2,"user_id":41,"teacher_portrait":"20190000/z.jpg","teacher_name":"壕","teacher_attention":645654,"teacher_level":5,"teacher_master":"街舞","teacher_intro":"对每一位学生认真负责"},{"user_teacher_id":4,"user_id":7,"teacher_portrait":"头像地址","teacher_name":"教师姓名","teacher_attention":0,"teacher_level":0,"teacher_master":"舞蹈类型","teacher_intro":"文档教师"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<DanceTypeBean> dance_type;
        private List<TeacherBean> teacher;

        public List<DanceTypeBean> getDance_type() {
            return dance_type;
        }

        public void setDance_type(List<DanceTypeBean> dance_type) {
            this.dance_type = dance_type;
        }

        public List<TeacherBean> getTeacher() {
            return teacher;
        }

        public void setTeacher(List<TeacherBean> teacher) {
            this.teacher = teacher;
        }

        public static class DanceTypeBean {
            /**
             * dance_type_id : 1
             * dance_type_name : 街舞
             */

            private int dance_type_id;
            private String dance_type_name;

            public int getDance_type_id() {
                return dance_type_id;
            }

            public void setDance_type_id(int dance_type_id) {
                this.dance_type_id = dance_type_id;
            }

            public String getDance_type_name() {
                return dance_type_name;
            }

            public void setDance_type_name(String dance_type_name) {
                this.dance_type_name = dance_type_name;
            }
        }

        public static class TeacherBean {
            /**
             * user_teacher_id : 1
             * user_id : 2
             * teacher_portrait : 20190000/z.jpg
             * teacher_name : 教师姓名
             * teacher_attention : 65455
             * teacher_level : 5
             * teacher_master : 舞蹈类型
             * teacher_intro : 文档教师
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
}
