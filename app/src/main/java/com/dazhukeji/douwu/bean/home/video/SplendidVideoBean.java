package com.dazhukeji.douwu.bean.home.video;

import com.dazhukeji.douwu.bean.publicBean.BaseBean;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/18 11:12
 * 功能描述：
 */
public class SplendidVideoBean extends BaseBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * file_id : 22
         * file_cover : 20190125/4f8465fef2408460febf47b4bad39ba8.png
         * file_category : 1
         * user_name :
         * user_id : 79
         * user_portrait :
         * file_name : 这种
         * file_collection : 0
         * teacher_name : null
         * user_teacher_id : null
         * teacher_portrait : null
         * organization_name : null
         * organization_portrait : null
         * organization_id : null
         */

        private int file_id;
        private String file_cover;
        private int file_category;
        private String user_name;
        private int user_id;
        private String user_portrait;
        private String file_name;
        private int file_collection;
        private String teacher_name;
        private String user_teacher_id;
        private String teacher_portrait;
        private String organization_name;
        private String organization_portrait;
        private String organization_id;

        public int getFile_id() {
            return file_id;
        }

        public void setFile_id(int file_id) {
            this.file_id = file_id;
        }

        public String getFile_cover() {
            return file_cover;
        }

        public void setFile_cover(String file_cover) {
            this.file_cover = file_cover;
        }

        public int getFile_category() {
            return file_category;
        }

        public void setFile_category(int file_category) {
            this.file_category = file_category;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_portrait() {
            return user_portrait;
        }

        public void setUser_portrait(String user_portrait) {
            this.user_portrait = user_portrait;
        }

        public String getFile_name() {
            return file_name;
        }

        public void setFile_name(String file_name) {
            this.file_name = file_name;
        }

        public int getFile_collection() {
            return file_collection;
        }

        public void setFile_collection(int file_collection) {
            this.file_collection = file_collection;
        }

        public String getTeacher_name() {
            return teacher_name;
        }

        public void setTeacher_name(String teacher_name) {
            this.teacher_name = teacher_name;
        }

        public String getUser_teacher_id() {
            return user_teacher_id;
        }

        public void setUser_teacher_id(String user_teacher_id) {
            this.user_teacher_id = user_teacher_id;
        }

        public String getTeacher_portrait() {
            return teacher_portrait;
        }

        public void setTeacher_portrait(String teacher_portrait) {
            this.teacher_portrait = teacher_portrait;
        }

        public String getOrganization_name() {
            return organization_name;
        }

        public void setOrganization_name(String organization_name) {
            this.organization_name = organization_name;
        }

        public String getOrganization_portrait() {
            return organization_portrait;
        }

        public void setOrganization_portrait(String organization_portrait) {
            this.organization_portrait = organization_portrait;
        }

        public String getOrganization_id() {
            return organization_id;
        }

        public void setOrganization_id(String organization_id) {
            this.organization_id = organization_id;
        }
    }
}
