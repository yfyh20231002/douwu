package com.dazhukeji.douwu.bean.home.courses;

import com.dazhukeji.douwu.bean.publicBean.BaseBean;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/11 16:00
 * 功能描述：
 */
public class AllCoursesBean extends BaseBean {


    /**
     * data : {"information":{"information_content":"20190116/99bab3f2aefe17d95d8c3ac776b01c2b.png"},"dance_type":[{"dance_type_id":1,"dance_type_name":"街舞"},{"dance_type_id":2,"dance_type_name":"现代舞"},{"dance_type_id":3,"dance_type_name":"摩登舞"},{"dance_type_id":4,"dance_type_name":"拉丁舞"},{"dance_type_id":5,"dance_type_name":"民族舞"},{"dance_type_id":6,"dance_type_name":"儿童舞"},{"dance_type_id":7,"dance_type_name":"踢踏舞"},{"dance_type_id":8,"dance_type_name":"爵士舞"},{"dance_type_id":9,"dance_type_name":"芭蕾舞"},{"dance_type_id":10,"dance_type_name":"机械舞"}],"curriculum":[{"curriculum_id":2,"curriculum_admin":"明溪","curriculum_name":"孔雀舞","curriculum_photo":"20190000/z.jpg","curriculum_actual_price":100,"curriculum_difficulty":"中等难度","curriculum_start_time":6554566,"curriculum_over_time":98765466,"curriculum_identity_type":1},{"curriculum_id":15,"curriculum_admin":"鸿蒙","curriculum_name":"街舞","curriculum_photo":"20181127/162f7150a9a56b364d67162d50a952f2.jpg","curriculum_actual_price":100,"curriculum_difficulty":"一般难度","curriculum_start_time":6554566,"curriculum_over_time":98765466,"curriculum_identity_type":2}]}
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
         * information : {"information_content":"20190116/99bab3f2aefe17d95d8c3ac776b01c2b.png"}
         * dance_type : [{"dance_type_id":1,"dance_type_name":"街舞"},{"dance_type_id":2,"dance_type_name":"现代舞"},{"dance_type_id":3,"dance_type_name":"摩登舞"},{"dance_type_id":4,"dance_type_name":"拉丁舞"},{"dance_type_id":5,"dance_type_name":"民族舞"},{"dance_type_id":6,"dance_type_name":"儿童舞"},{"dance_type_id":7,"dance_type_name":"踢踏舞"},{"dance_type_id":8,"dance_type_name":"爵士舞"},{"dance_type_id":9,"dance_type_name":"芭蕾舞"},{"dance_type_id":10,"dance_type_name":"机械舞"}]
         * curriculum : [{"curriculum_id":2,"curriculum_admin":"明溪","curriculum_name":"孔雀舞","curriculum_photo":"20190000/z.jpg","curriculum_actual_price":100,"curriculum_difficulty":"中等难度","curriculum_start_time":6554566,"curriculum_over_time":98765466,"curriculum_identity_type":1},{"curriculum_id":15,"curriculum_admin":"鸿蒙","curriculum_name":"街舞","curriculum_photo":"20181127/162f7150a9a56b364d67162d50a952f2.jpg","curriculum_actual_price":100,"curriculum_difficulty":"一般难度","curriculum_start_time":6554566,"curriculum_over_time":98765466,"curriculum_identity_type":2}]
         */

        private InformationBean information;
        private List<DanceTypeBean> dance_type;
        private List<CurriculumBean> curriculum;

        public InformationBean getInformation() {
            return information;
        }

        public void setInformation(InformationBean information) {
            this.information = information;
        }

        public List<DanceTypeBean> getDance_type() {
            return dance_type;
        }

        public void setDance_type(List<DanceTypeBean> dance_type) {
            this.dance_type = dance_type;
        }

        public List<CurriculumBean> getCurriculum() {
            return curriculum;
        }

        public void setCurriculum(List<CurriculumBean> curriculum) {
            this.curriculum = curriculum;
        }

        public static class InformationBean {
            /**
             * information_content : 20190116/99bab3f2aefe17d95d8c3ac776b01c2b.png
             */

            private String information_content;

            public String getInformation_content() {
                return information_content;
            }

            public void setInformation_content(String information_content) {
                this.information_content = information_content;
            }
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

        public static class CurriculumBean {
            /**
             * curriculum_id : 2
             * curriculum_admin : 明溪
             * curriculum_name : 孔雀舞
             * curriculum_photo : 20190000/z.jpg
             * curriculum_actual_price : 100
             * curriculum_difficulty : 中等难度
             * curriculum_start_time : 6554566
             * curriculum_over_time : 98765466
             * curriculum_identity_type : 1
             */

            private int curriculum_id;
            private String curriculum_admin;
            private String curriculum_name;
            private String curriculum_photo;
            private int curriculum_actual_price;
            private String curriculum_difficulty;
            private int curriculum_start_time;
            private int curriculum_over_time;
            private int curriculum_identity_type;

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

            public int getCurriculum_actual_price() {
                return curriculum_actual_price;
            }

            public void setCurriculum_actual_price(int curriculum_actual_price) {
                this.curriculum_actual_price = curriculum_actual_price;
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

            public int getCurriculum_identity_type() {
                return curriculum_identity_type;
            }

            public void setCurriculum_identity_type(int curriculum_identity_type) {
                this.curriculum_identity_type = curriculum_identity_type;
            }
        }
    }
}
