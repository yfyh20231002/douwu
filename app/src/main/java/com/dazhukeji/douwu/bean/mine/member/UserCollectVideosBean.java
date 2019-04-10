package com.dazhukeji.douwu.bean.mine.member;

import com.dazhukeji.douwu.bean.publicBean.BaseBean;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/17 14:55
 * 功能描述：
 */
public class UserCollectVideosBean extends BaseBean {


    /**
     * data : {"user":{"user_name":"","user_sex":1,"user_signature":""},"videos":[{"collect_id":4,"file_id":1,"collect_creattime":1548483640,"file_cover":"001/12.png","file_name":"街舞","file_type":1,"file_category":3,"file_state":1,"file_false_delete":1,"dance_type_name":"街舞","file_collection":999,"file_portrait":"20181122/dfe52400de36aaf502a037f08f2f408f.jpg"}]}
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
         * user : {"user_name":"","user_sex":1,"user_signature":""}
         * videos : [{"collect_id":4,"file_id":1,"collect_creattime":1548483640,"file_cover":"001/12.png","file_name":"街舞","file_type":1,"file_category":3,"file_state":1,"file_false_delete":1,"dance_type_name":"街舞","file_collection":999,"file_portrait":"20181122/dfe52400de36aaf502a037f08f2f408f.jpg"}]
         */

        private UserBean user;
        private List<VideosBean> videos;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public List<VideosBean> getVideos() {
            return videos;
        }

        public void setVideos(List<VideosBean> videos) {
            this.videos = videos;
        }

        public static class UserBean {
            /**
             * user_name :
             * user_sex : 1
             * user_signature :
             */

            private String user_name;
            private int user_sex;
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

            public String getUser_signature() {
                return user_signature;
            }

            public void setUser_signature(String user_signature) {
                this.user_signature = user_signature;
            }
        }

        public static class VideosBean {
            /**
             * collect_id : 4
             * file_id : 1
             * collect_creattime : 1548483640
             * file_cover : 001/12.png
             * file_name : 街舞
             * file_type : 1
             * file_category : 3
             * file_state : 1
             * file_false_delete : 1
             * dance_type_name : 街舞
             * file_collection : 999
             * file_portrait : 20181122/dfe52400de36aaf502a037f08f2f408f.jpg
             */

            private int collect_id;
            private int file_id;
            private int collect_creattime;
            private String file_cover;
            private String file_name;
            private int file_type;
            private int file_category;
            private int file_state;
            private int file_false_delete;
            private String dance_type_name;
            private int file_collection;
            private String file_portrait;

            public int getCollect_id() {
                return collect_id;
            }

            public void setCollect_id(int collect_id) {
                this.collect_id = collect_id;
            }

            public int getFile_id() {
                return file_id;
            }

            public void setFile_id(int file_id) {
                this.file_id = file_id;
            }

            public int getCollect_creattime() {
                return collect_creattime;
            }

            public void setCollect_creattime(int collect_creattime) {
                this.collect_creattime = collect_creattime;
            }

            public String getFile_cover() {
                return file_cover;
            }

            public void setFile_cover(String file_cover) {
                this.file_cover = file_cover;
            }

            public String getFile_name() {
                return file_name;
            }

            public void setFile_name(String file_name) {
                this.file_name = file_name;
            }

            public int getFile_type() {
                return file_type;
            }

            public void setFile_type(int file_type) {
                this.file_type = file_type;
            }

            public int getFile_category() {
                return file_category;
            }

            public void setFile_category(int file_category) {
                this.file_category = file_category;
            }

            public int getFile_state() {
                return file_state;
            }

            public void setFile_state(int file_state) {
                this.file_state = file_state;
            }

            public int getFile_false_delete() {
                return file_false_delete;
            }

            public void setFile_false_delete(int file_false_delete) {
                this.file_false_delete = file_false_delete;
            }

            public String getDance_type_name() {
                return dance_type_name;
            }

            public void setDance_type_name(String dance_type_name) {
                this.dance_type_name = dance_type_name;
            }

            public int getFile_collection() {
                return file_collection;
            }

            public void setFile_collection(int file_collection) {
                this.file_collection = file_collection;
            }

            public String getFile_portrait() {
                return file_portrait;
            }

            public void setFile_portrait(String file_portrait) {
                this.file_portrait = file_portrait;
            }
        }
    }
}
