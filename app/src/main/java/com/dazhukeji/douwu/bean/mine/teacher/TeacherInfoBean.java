package com.dazhukeji.douwu.bean.mine.teacher;

import com.dazhukeji.douwu.bean.publicBean.BaseBean;

/**
 * 创建者：zhangyunfei
 * 时间：2018/12/25
 * 联系方式：32457127@qq.com
 * 功能描述：
 */
public class TeacherInfoBean extends BaseBean {


    /**
     * data : {"user_teacher_id":1,"teacher_portrait":"20190000/z.jpg","teacher_phone":"15999988888","teacher_name":"教师姓名","schooltime":"15:00~21:00","teacher_site":"地址","teacher_attention":65455,"teacher_level":5,"teacher_master":"舞蹈类型","teacher_intro":"文档教师","teacher_video":"展示视频地址","teacher_video_cover":"展示封面"}
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
         * user_teacher_id : 1
         * teacher_portrait : 20190000/z.jpg
         * teacher_phone : 15999988888
         * teacher_name : 教师姓名
         * schooltime : 15:00~21:00
         * teacher_site : 地址
         * teacher_attention : 65455
         * teacher_level : 5
         * teacher_master : 舞蹈类型
         * teacher_intro : 文档教师
         * teacher_video : 展示视频地址
         * teacher_video_cover : 展示封面
         */

        private int user_teacher_id;
        private String teacher_portrait;
        private String teacher_phone;
        private String teacher_name;
        private String schooltime;
        private String teacher_site;
        private int teacher_attention;
        private int teacher_level;
        private String teacher_master;
        private String teacher_intro;
        private String teacher_video;
        private String teacher_video_cover;

        public int getUser_teacher_id() {
            return user_teacher_id;
        }

        public void setUser_teacher_id(int user_teacher_id) {
            this.user_teacher_id = user_teacher_id;
        }

        public String getTeacher_portrait() {
            return teacher_portrait;
        }

        public void setTeacher_portrait(String teacher_portrait) {
            this.teacher_portrait = teacher_portrait;
        }

        public String getTeacher_phone() {
            return teacher_phone;
        }

        public void setTeacher_phone(String teacher_phone) {
            this.teacher_phone = teacher_phone;
        }

        public String getTeacher_name() {
            return teacher_name;
        }

        public void setTeacher_name(String teacher_name) {
            this.teacher_name = teacher_name;
        }

        public String getSchooltime() {
            return schooltime;
        }

        public void setSchooltime(String schooltime) {
            this.schooltime = schooltime;
        }

        public String getTeacher_site() {
            return teacher_site;
        }

        public void setTeacher_site(String teacher_site) {
            this.teacher_site = teacher_site;
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

        public String getTeacher_video() {
            return teacher_video;
        }

        public void setTeacher_video(String teacher_video) {
            this.teacher_video = teacher_video;
        }

        public String getTeacher_video_cover() {
            return teacher_video_cover;
        }

        public void setTeacher_video_cover(String teacher_video_cover) {
            this.teacher_video_cover = teacher_video_cover;
        }
    }
}
