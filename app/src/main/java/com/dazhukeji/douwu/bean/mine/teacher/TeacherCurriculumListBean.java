package com.dazhukeji.douwu.bean.mine.teacher;

import com.dazhukeji.douwu.bean.publicBean.BaseBean;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 时间：2018/12/23
 * 联系方式：32457127@qq.com
 * 功能描述：
 */
public class TeacherCurriculumListBean extends BaseBean {


    /**
     * data : {"teacher":{"user_teacher_id":"","teacher_name":"","teacher_portrait":"","user_signature":"","user_sex":1,"user_role":1},"curriculum":[{"curriculum_id":1,"curriculum_admin":"涅凡尘","curriculum_name":"街舞","curriculum_photo":"20181127/162f7150a9a56b364d67162d50a952f2.jpg","curriculum_difficulty":"中等难度","curriculum_start_time":154278974,"curriculum_over_time":1542889800}]}
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
         * teacher : {"user_teacher_id":"","teacher_name":"","teacher_portrait":"","user_signature":"","user_sex":1,"user_role":1}
         * curriculum : [{"curriculum_id":1,"curriculum_admin":"涅凡尘","curriculum_name":"街舞","curriculum_photo":"20181127/162f7150a9a56b364d67162d50a952f2.jpg","curriculum_difficulty":"中等难度","curriculum_start_time":154278974,"curriculum_over_time":1542889800}]
         */

        private TeacherBean teacher;
        private List<CurriculumBean> curriculum;

        public TeacherBean getTeacher() {
            return teacher;
        }

        public void setTeacher(TeacherBean teacher) {
            this.teacher = teacher;
        }

        public List<CurriculumBean> getCurriculum() {
            return curriculum;
        }

        public void setCurriculum(List<CurriculumBean> curriculum) {
            this.curriculum = curriculum;
        }

        public static class TeacherBean {
            /**
             * user_teacher_id :
             * teacher_name :
             * teacher_portrait :
             * user_signature :
             * user_sex : 1
             * user_role : 1
             */

            private String user_teacher_id;
            private String teacher_name;
            private String teacher_portrait;
            private String user_signature;
            private int user_sex;
            private int user_role;

            public String getUser_teacher_id() {
                return user_teacher_id;
            }

            public void setUser_teacher_id(String user_teacher_id) {
                this.user_teacher_id = user_teacher_id;
            }

            public String getTeacher_name() {
                return teacher_name;
            }

            public void setTeacher_name(String teacher_name) {
                this.teacher_name = teacher_name;
            }

            public String getTeacher_portrait() {
                return teacher_portrait;
            }

            public void setTeacher_portrait(String teacher_portrait) {
                this.teacher_portrait = teacher_portrait;
            }

            public String getUser_signature() {
                return user_signature;
            }

            public void setUser_signature(String user_signature) {
                this.user_signature = user_signature;
            }

            public int getUser_sex() {
                return user_sex;
            }

            public void setUser_sex(int user_sex) {
                this.user_sex = user_sex;
            }

            public int getUser_role() {
                return user_role;
            }

            public void setUser_role(int user_role) {
                this.user_role = user_role;
            }
        }

        public static class CurriculumBean {
            /**
             * curriculum_id : 1
             * curriculum_admin : 涅凡尘
             * curriculum_name : 街舞
             * curriculum_photo : 20181127/162f7150a9a56b364d67162d50a952f2.jpg
             * curriculum_difficulty : 中等难度
             * curriculum_start_time : 154278974
             * curriculum_over_time : 1542889800
             */

            private int curriculum_id;
            private String curriculum_admin;
            private String curriculum_name;
            private String curriculum_photo;
            private String curriculum_difficulty;
            private int curriculum_start_time;
            private int curriculum_over_time;

            public int getCurriculum_id() {
                return curriculum_id;
            }

            public void setCurriculum_id(int curriculum_id) {
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

            public String getCurriculum_difficulty() {
                return curriculum_difficulty;
            }

            public void setCurriculum_difficulty(String curriculum_difficulty) {
                this.curriculum_difficulty = curriculum_difficulty;
            }

            public int getCurriculum_start_time() {
                return curriculum_start_time;
            }

            public void setCurriculum_start_time(int curriculum_start_time) {
                this.curriculum_start_time = curriculum_start_time;
            }

            public int getCurriculum_over_time() {
                return curriculum_over_time;
            }

            public void setCurriculum_over_time(int curriculum_over_time) {
                this.curriculum_over_time = curriculum_over_time;
            }
        }
    }
}
