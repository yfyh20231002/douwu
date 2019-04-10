package com.dazhukeji.douwu.bean.home.organization;

import com.dazhukeji.douwu.bean.publicBean.BaseBean;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/12 10:17
 * 功能描述：
 */
public class OrganizationTeacherBean extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * organization_teacher_id : 1
         * teacher_photo : 20181127/162f7150a9a56b364d67162d50a952f2.jpg
         * teacher_name : 明溪
         * teacher_level : 5
         * teacher_master : 街舞
         * teacher_intro : 认真负责负责
         */

        private int organization_teacher_id;
        private String teacher_photo;
        private String teacher_name;
        private int teacher_level;
        private String teacher_master;
        private String teacher_intro;

        public int getOrganization_teacher_id() {
            return organization_teacher_id;
        }

        public void setOrganization_teacher_id(int organization_teacher_id) {
            this.organization_teacher_id = organization_teacher_id;
        }

        public String getTeacher_photo() {
            return teacher_photo;
        }

        public void setTeacher_photo(String teacher_photo) {
            this.teacher_photo = teacher_photo;
        }

        public String getTeacher_name() {
            return teacher_name;
        }

        public void setTeacher_name(String teacher_name) {
            this.teacher_name = teacher_name;
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
