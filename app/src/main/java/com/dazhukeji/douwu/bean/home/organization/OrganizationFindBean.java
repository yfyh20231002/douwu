package com.dazhukeji.douwu.bean.home.organization;

import com.dazhukeji.douwu.bean.publicBean.BaseBean;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 时间：2018/12/10
 * 联系方式：32457127@qq.com
 * 功能描述：
 */
public class OrganizationFindBean extends BaseBean {


    /**
     * data : {"attention_state":2,"basic":{"organization_id":8,"organization_name":"云飞","organization_portrait":"","organization_site":"机构地址","organization_facility":"wifi!=end=!微信支付!=end=!支付宝支付!=end=!停车场","organization_business_hours":"10：00~17：00","organization_level":5,"organization_district_id":1,"organization_synopsis":"拥有千名教师的专业舞蹈培训机构","organization_service":"18888888888","organization_type":"街舞,天鹅舞,孔雀舞，机械舞等"},"video":{"promotional_cover":"20181230/7f377ecfd448147931def1b30d7e587c.png","promotional_video":"20181230/c2a13367ef26ca425ae4503f3056b4d6.mp4"},"invitation":{"invitation_id":3,"invitation_dance_type":"街舞","invitation_title":"云飞聘请函"},"curriculum":[{"curriculum_id":41,"curriculum_admin":"张老师","curriculum_difficulty":"一般难度","curriculum_actual_price":100,"curriculum_start_time":654654,"curriculum_over_time":98798789,"curriculum_name":"街舞"}],"organization_teacher":[{"organization_teacher_id":5,"teacher_name":"定稿测试机构教师","teacher_master":"定稿机构教师街舞","teacher_intro":"定稿机构教师简介简介简介简介简介简介简介简介"}],"videos":[{"file_id":2,"file_cover":"20190308/0edc89c0c2196a98a4086f5e6a2d93f8.jpg","organization_portrait":"001/12.png","file_collection":1,"dance_type_name":"街舞","file_name":"浩天"},{"file_id":null,"file_cover":null,"organization_portrait":"20181122/dfe52400de36aaf502a037f08f2f408f.jpg","file_collection":null,"dance_type_name":null,"file_name":null}]}
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
         * attention_state : 2
         * basic : {"organization_id":8,"organization_name":"云飞","organization_portrait":"","organization_site":"机构地址","organization_facility":"wifi!=end=!微信支付!=end=!支付宝支付!=end=!停车场","organization_business_hours":"10：00~17：00","organization_level":5,"organization_district_id":1,"organization_synopsis":"拥有千名教师的专业舞蹈培训机构","organization_service":"18888888888","organization_type":"街舞,天鹅舞,孔雀舞，机械舞等"}
         * video : {"promotional_cover":"20181230/7f377ecfd448147931def1b30d7e587c.png","promotional_video":"20181230/c2a13367ef26ca425ae4503f3056b4d6.mp4"}
         * invitation : {"invitation_id":3,"invitation_dance_type":"街舞","invitation_title":"云飞聘请函"}
         * curriculum : [{"curriculum_id":41,"curriculum_admin":"张老师","curriculum_difficulty":"一般难度","curriculum_actual_price":100,"curriculum_start_time":654654,"curriculum_over_time":98798789,"curriculum_name":"街舞"}]
         * organization_teacher : [{"organization_teacher_id":5,"teacher_name":"定稿测试机构教师","teacher_master":"定稿机构教师街舞","teacher_intro":"定稿机构教师简介简介简介简介简介简介简介简介"}]
         * videos : [{"file_id":2,"file_cover":"20190308/0edc89c0c2196a98a4086f5e6a2d93f8.jpg","organization_portrait":"001/12.png","file_collection":1,"dance_type_name":"街舞","file_name":"浩天"},{"file_id":null,"file_cover":null,"organization_portrait":"20181122/dfe52400de36aaf502a037f08f2f408f.jpg","file_collection":null,"dance_type_name":null,"file_name":null}]
         */

        private int attention_state;
        private BasicBean basic;
        private VideoBean video;
        private InvitationBean invitation;
        private List<CurriculumBean> curriculum;
        private List<OrganizationTeacherBean> organization_teacher;
        private List<VideosBean> videos;

        public int getAttention_state() {
            return attention_state;
        }

        public void setAttention_state(int attention_state) {
            this.attention_state = attention_state;
        }

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public VideoBean getVideo() {
            return video;
        }

        public void setVideo(VideoBean video) {
            this.video = video;
        }

        public InvitationBean getInvitation() {
            return invitation;
        }

        public void setInvitation(InvitationBean invitation) {
            this.invitation = invitation;
        }

        public List<CurriculumBean> getCurriculum() {
            return curriculum;
        }

        public void setCurriculum(List<CurriculumBean> curriculum) {
            this.curriculum = curriculum;
        }

        public List<OrganizationTeacherBean> getOrganization_teacher() {
            return organization_teacher;
        }

        public void setOrganization_teacher(List<OrganizationTeacherBean> organization_teacher) {
            this.organization_teacher = organization_teacher;
        }

        public List<VideosBean> getVideos() {
            return videos;
        }

        public void setVideos(List<VideosBean> videos) {
            this.videos = videos;
        }

        public static class BasicBean {
            /**
             * organization_id : 8
             * organization_name : 云飞
             * organization_portrait :
             * organization_site : 机构地址
             * organization_facility : wifi!=end=!微信支付!=end=!支付宝支付!=end=!停车场
             * organization_business_hours : 10：00~17：00
             * organization_level : 5
             * organization_district_id : 1
             * organization_synopsis : 拥有千名教师的专业舞蹈培训机构
             * organization_service : 18888888888
             * organization_type : 街舞,天鹅舞,孔雀舞，机械舞等
             */

            private int organization_id;
            private String organization_name;
            private String organization_portrait;
            private String organization_site;
            private String organization_facility;
            private String organization_business_hours;
            private int organization_level;
            private int organization_district_id;
            private String organization_synopsis;
            private String organization_service;
            private String organization_type;

            public int getOrganization_id() {
                return organization_id;
            }

            public void setOrganization_id(int organization_id) {
                this.organization_id = organization_id;
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

            public String getOrganization_site() {
                return organization_site;
            }

            public void setOrganization_site(String organization_site) {
                this.organization_site = organization_site;
            }

            public String getOrganization_facility() {
                return organization_facility;
            }

            public void setOrganization_facility(String organization_facility) {
                this.organization_facility = organization_facility;
            }

            public String getOrganization_business_hours() {
                return organization_business_hours;
            }

            public void setOrganization_business_hours(String organization_business_hours) {
                this.organization_business_hours = organization_business_hours;
            }

            public int getOrganization_level() {
                return organization_level;
            }

            public void setOrganization_level(int organization_level) {
                this.organization_level = organization_level;
            }

            public int getOrganization_district_id() {
                return organization_district_id;
            }

            public void setOrganization_district_id(int organization_district_id) {
                this.organization_district_id = organization_district_id;
            }

            public String getOrganization_synopsis() {
                return organization_synopsis;
            }

            public void setOrganization_synopsis(String organization_synopsis) {
                this.organization_synopsis = organization_synopsis;
            }

            public String getOrganization_service() {
                return organization_service;
            }

            public void setOrganization_service(String organization_service) {
                this.organization_service = organization_service;
            }

            public String getOrganization_type() {
                return organization_type;
            }

            public void setOrganization_type(String organization_type) {
                this.organization_type = organization_type;
            }
        }

        public static class VideoBean {
            /**
             * promotional_cover : 20181230/7f377ecfd448147931def1b30d7e587c.png
             * promotional_video : 20181230/c2a13367ef26ca425ae4503f3056b4d6.mp4
             */

            private String promotional_cover;
            private String promotional_video;

            public String getPromotional_cover() {
                return promotional_cover;
            }

            public void setPromotional_cover(String promotional_cover) {
                this.promotional_cover = promotional_cover;
            }

            public String getPromotional_video() {
                return promotional_video;
            }

            public void setPromotional_video(String promotional_video) {
                this.promotional_video = promotional_video;
            }
        }

        public static class InvitationBean {
            /**
             * invitation_id : 3
             * invitation_dance_type : 街舞
             * invitation_title : 云飞聘请函
             */

            private int invitation_id;
            private String invitation_dance_type;
            private String invitation_title;

            public int getInvitation_id() {
                return invitation_id;
            }

            public void setInvitation_id(int invitation_id) {
                this.invitation_id = invitation_id;
            }

            public String getInvitation_dance_type() {
                return invitation_dance_type;
            }

            public void setInvitation_dance_type(String invitation_dance_type) {
                this.invitation_dance_type = invitation_dance_type;
            }

            public String getInvitation_title() {
                return invitation_title;
            }

            public void setInvitation_title(String invitation_title) {
                this.invitation_title = invitation_title;
            }
        }

        public static class CurriculumBean {
            /**
             * curriculum_id : 41
             * curriculum_admin : 张老师
             * curriculum_difficulty : 一般难度
             * curriculum_actual_price : 100
             * curriculum_start_time : 654654
             * curriculum_over_time : 98798789
             * curriculum_name : 街舞
             */

            private int curriculum_id;
            private String curriculum_admin;
            private String curriculum_difficulty;
            private int curriculum_actual_price;
            private long curriculum_start_time;
            private long curriculum_over_time;
            private String curriculum_name;

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

            public String getCurriculum_difficulty() {
                return curriculum_difficulty;
            }

            public void setCurriculum_difficulty(String curriculum_difficulty) {
                this.curriculum_difficulty = curriculum_difficulty;
            }

            public int getCurriculum_actual_price() {
                return curriculum_actual_price;
            }

            public void setCurriculum_actual_price(int curriculum_actual_price) {
                this.curriculum_actual_price = curriculum_actual_price;
            }

            public long getCurriculum_start_time() {
                return curriculum_start_time;
            }

            public void setCurriculum_start_time(long curriculum_start_time) {
                this.curriculum_start_time = curriculum_start_time;
            }

            public long getCurriculum_over_time() {
                return curriculum_over_time;
            }

            public void setCurriculum_over_time(long curriculum_over_time) {
                this.curriculum_over_time = curriculum_over_time;
            }

            public String getCurriculum_name() {
                return curriculum_name;
            }

            public void setCurriculum_name(String curriculum_name) {
                this.curriculum_name = curriculum_name;
            }
        }

        public static class OrganizationTeacherBean {
            /**
             * organization_teacher_id : 5
             * teacher_name : 定稿测试机构教师
             * teacher_master : 定稿机构教师街舞
             * teacher_intro : 定稿机构教师简介简介简介简介简介简介简介简介
             */

            private int organization_teacher_id;
            private String teacher_name;
            private String teacher_master;
            private String teacher_intro;

            public int getOrganization_teacher_id() {
                return organization_teacher_id;
            }

            public void setOrganization_teacher_id(int organization_teacher_id) {
                this.organization_teacher_id = organization_teacher_id;
            }

            public String getTeacher_name() {
                return teacher_name;
            }

            public void setTeacher_name(String teacher_name) {
                this.teacher_name = teacher_name;
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

        public static class VideosBean {
            /**
             * file_id : 2
             * file_cover : 20190308/0edc89c0c2196a98a4086f5e6a2d93f8.jpg
             * organization_portrait : 001/12.png
             * file_collection : 1
             * dance_type_name : 街舞
             * file_name : 浩天
             */

            private int file_id;
            private String file_cover;
            private String organization_portrait;
            private int file_collection;
            private String dance_type_name;
            private String file_name;

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

            public String getOrganization_portrait() {
                return organization_portrait;
            }

            public void setOrganization_portrait(String organization_portrait) {
                this.organization_portrait = organization_portrait;
            }

            public int getFile_collection() {
                return file_collection;
            }

            public void setFile_collection(int file_collection) {
                this.file_collection = file_collection;
            }

            public String getDance_type_name() {
                return dance_type_name;
            }

            public void setDance_type_name(String dance_type_name) {
                this.dance_type_name = dance_type_name;
            }

            public String getFile_name() {
                return file_name;
            }

            public void setFile_name(String file_name) {
                this.file_name = file_name;
            }
        }
    }
}
