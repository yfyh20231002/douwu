package com.dazhukeji.douwu.bean.home;

import com.dazhukeji.douwu.bean.publicBean.BaseBean;
import com.dazhukeji.douwu.bean.publicBean.DistrictBean;
import com.dazhukeji.douwu.bean.publicBean.VideoBean;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/6 9:05
 * 功能描述：
 */
public class HomeIndexBean extends BaseBean{

    /**
     * data : {"current_region":{"district_id":1,"district_name":"天津"},"dance_type":[{"dance_type_id":1,"dance_type_name":"街舞"},{"dance_type_id":2,"dance_type_name":"现代舞"},{"dance_type_id":3,"dance_type_name":"摩登舞"},{"dance_type_id":4,"dance_type_name":"拉丁舞"},{"dance_type_id":5,"dance_type_name":"民族舞"},{"dance_type_id":6,"dance_type_name":"儿童舞"},{"dance_type_id":7,"dance_type_name":"踢踏舞"},{"dance_type_id":8,"dance_type_name":"爵士舞"},{"dance_type_id":9,"dance_type_name":"芭蕾舞"},{"dance_type_id":10,"dance_type_name":"机械舞"}],"banner":[{"banner_content":"20190106/81dfdcefb2877ef36df97ccb3e76bd22.gif"},{"banner_content":"20190106/fce23d036dd31765a685740260be3b15.gif"},{"banner_content":"20190106/6a70b0ea3bdeb57f482680bd38ad7ffb.gif"}],"district":[{"district_id":1,"district_name":"天津"},{"district_id":2,"district_name":"北京"},{"district_id":3,"district_name":"深圳"},{"district_id":4,"district_name":"南京"},{"district_id":5,"district_name":"上海"},{"district_id":6,"district_name":"海南"}],"information":{"information_content":"20190116/99bab3f2aefe17d95d8c3ac776b01c2b.png"},"video":[{"file_id":1,"file_cover":"20190000/a.jpg","file_category":1,"user_name":"壕","user_id":41,"user_portrait":"001/12.png","file_name":"个人视频1","file_collection":9999,"teacher_name":"教师姓名","user_teacher_id":1,"teacher_portrait":"20190000/z.jpg","organization_name":"机构名称","organization_portrait":"20181122/dfe52400de36aaf502a037f08f2f408f.jpg","organization_id":4},{"file_id":1,"file_cover":"20190000/a.jpg","file_category":1,"user_name":"定稿测试","user_id":2,"user_portrait":"20190000/z.jpg","file_name":"个人视频1","file_collection":9999,"teacher_name":"教师姓名","user_teacher_id":1,"teacher_portrait":"20190000/z.jpg","organization_name":"机构名称","organization_portrait":"20181122/dfe52400de36aaf502a037f08f2f408f.jpg","organization_id":4},{"file_id":2,"file_cover":"20190000/b.jpg","file_category":2,"user_name":"壕","user_id":41,"user_portrait":"001/12.png","file_name":"教师视频2","file_collection":999,"teacher_name":"教师姓名","user_teacher_id":1,"teacher_portrait":"20190000/z.jpg","organization_name":null,"organization_portrait":null,"organization_id":null},{"file_id":2,"file_cover":"20190000/b.jpg","file_category":2,"user_name":"壕","user_id":41,"user_portrait":"001/12.png","file_name":"教师视频2","file_collection":999,"teacher_name":"这","user_teacher_id":5,"teacher_portrait":"20190103/65b0dde318b5b7e1f73126d01321ed54.png","organization_name":null,"organization_portrait":null,"organization_id":null},{"file_id":3,"file_cover":"20190000/c.jpg","file_category":3,"user_name":"壕","user_id":41,"user_portrait":"001/12.png","file_name":"智莲天鹅舞蹈","file_collection":99,"teacher_name":null,"user_teacher_id":null,"teacher_portrait":null,"organization_name":"机构名称","organization_portrait":"20181122/dfe52400de36aaf502a037f08f2f408f.jpg","organization_id":1},{"file_id":6,"file_cover":"20190000/f.jpg","file_category":2,"user_name":null,"user_id":null,"user_portrait":null,"file_name":"文件名称","file_collection":0,"teacher_name":null,"user_teacher_id":null,"teacher_portrait":null,"organization_name":null,"organization_portrait":null,"organization_id":null},{"file_id":7,"file_cover":"20190000/g.jpg","file_category":2,"user_name":null,"user_id":null,"user_portrait":null,"file_name":"文件名称","file_collection":0,"teacher_name":"教师姓名","user_teacher_id":1,"teacher_portrait":"20190000/z.jpg","organization_name":null,"organization_portrait":null,"organization_id":null},{"file_id":10,"file_cover":"20190000/b.jpg","file_category":1,"user_name":null,"user_id":null,"user_portrait":null,"file_name":"首页文件","file_collection":0,"teacher_name":null,"user_teacher_id":null,"teacher_portrait":null,"organization_name":null,"organization_portrait":null,"organization_id":null},{"file_id":11,"file_cover":"20190000/i.jpg","file_category":1,"user_name":"定稿测试","user_id":2,"user_portrait":"20190000/z.jpg","file_name":"首页文件","file_collection":0,"teacher_name":null,"user_teacher_id":null,"teacher_portrait":null,"organization_name":null,"organization_portrait":null,"organization_id":null},{"file_id":12,"file_cover":"20190000/c.jpg","file_category":1,"user_name":"定稿测试","user_id":2,"user_portrait":"20190000/z.jpg","file_name":"首页图片","file_collection":0,"teacher_name":null,"user_teacher_id":null,"teacher_portrait":null,"organization_name":null,"organization_portrait":null,"organization_id":null},{"file_id":13,"file_cover":"20190000/e.jpg","file_category":1,"user_name":"定稿测试","user_id":2,"user_portrait":"20190000/z.jpg","file_name":"首页视频地址","file_collection":0,"teacher_name":null,"user_teacher_id":null,"teacher_portrait":null,"organization_name":null,"organization_portrait":null,"organization_id":null},{"file_id":14,"file_cover":"20190000/g.jpg","file_category":1,"user_name":"定稿测试","user_id":2,"user_portrait":"20190000/z.jpg","file_name":"首页音乐","file_collection":0,"teacher_name":null,"user_teacher_id":null,"teacher_portrait":null,"organization_name":null,"organization_portrait":null,"organization_id":null},{"file_id":18,"file_cover":"文件封面测试地址","file_category":2,"user_name":null,"user_id":null,"user_portrait":null,"file_name":"文件名称","file_collection":0,"teacher_name":"教师姓名","user_teacher_id":1,"teacher_portrait":"20190000/z.jpg","organization_name":null,"organization_portrait":null,"organization_id":null}]}
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
         * current_region : {"district_id":1,"district_name":"天津"}
         * dance_type : [{"dance_type_id":1,"dance_type_name":"街舞"},{"dance_type_id":2,"dance_type_name":"现代舞"},{"dance_type_id":3,"dance_type_name":"摩登舞"},{"dance_type_id":4,"dance_type_name":"拉丁舞"},{"dance_type_id":5,"dance_type_name":"民族舞"},{"dance_type_id":6,"dance_type_name":"儿童舞"},{"dance_type_id":7,"dance_type_name":"踢踏舞"},{"dance_type_id":8,"dance_type_name":"爵士舞"},{"dance_type_id":9,"dance_type_name":"芭蕾舞"},{"dance_type_id":10,"dance_type_name":"机械舞"}]
         * banner : [{"banner_content":"20190106/81dfdcefb2877ef36df97ccb3e76bd22.gif"},{"banner_content":"20190106/fce23d036dd31765a685740260be3b15.gif"},{"banner_content":"20190106/6a70b0ea3bdeb57f482680bd38ad7ffb.gif"}]
         * district : [{"district_id":1,"district_name":"天津"},{"district_id":2,"district_name":"北京"},{"district_id":3,"district_name":"深圳"},{"district_id":4,"district_name":"南京"},{"district_id":5,"district_name":"上海"},{"district_id":6,"district_name":"海南"}]
         * information : {"information_content":"20190116/99bab3f2aefe17d95d8c3ac776b01c2b.png"}
         * video : [{"file_id":1,"file_cover":"20190000/a.jpg","file_category":1,"user_name":"壕","user_id":41,"user_portrait":"001/12.png","file_name":"个人视频1","file_collection":9999,"teacher_name":"教师姓名","user_teacher_id":1,"teacher_portrait":"20190000/z.jpg","organization_name":"机构名称","organization_portrait":"20181122/dfe52400de36aaf502a037f08f2f408f.jpg","organization_id":4},{"file_id":1,"file_cover":"20190000/a.jpg","file_category":1,"user_name":"定稿测试","user_id":2,"user_portrait":"20190000/z.jpg","file_name":"个人视频1","file_collection":9999,"teacher_name":"教师姓名","user_teacher_id":1,"teacher_portrait":"20190000/z.jpg","organization_name":"机构名称","organization_portrait":"20181122/dfe52400de36aaf502a037f08f2f408f.jpg","organization_id":4},{"file_id":2,"file_cover":"20190000/b.jpg","file_category":2,"user_name":"壕","user_id":41,"user_portrait":"001/12.png","file_name":"教师视频2","file_collection":999,"teacher_name":"教师姓名","user_teacher_id":1,"teacher_portrait":"20190000/z.jpg","organization_name":null,"organization_portrait":null,"organization_id":null},{"file_id":2,"file_cover":"20190000/b.jpg","file_category":2,"user_name":"壕","user_id":41,"user_portrait":"001/12.png","file_name":"教师视频2","file_collection":999,"teacher_name":"这","user_teacher_id":5,"teacher_portrait":"20190103/65b0dde318b5b7e1f73126d01321ed54.png","organization_name":null,"organization_portrait":null,"organization_id":null},{"file_id":3,"file_cover":"20190000/c.jpg","file_category":3,"user_name":"壕","user_id":41,"user_portrait":"001/12.png","file_name":"智莲天鹅舞蹈","file_collection":99,"teacher_name":null,"user_teacher_id":null,"teacher_portrait":null,"organization_name":"机构名称","organization_portrait":"20181122/dfe52400de36aaf502a037f08f2f408f.jpg","organization_id":1},{"file_id":6,"file_cover":"20190000/f.jpg","file_category":2,"user_name":null,"user_id":null,"user_portrait":null,"file_name":"文件名称","file_collection":0,"teacher_name":null,"user_teacher_id":null,"teacher_portrait":null,"organization_name":null,"organization_portrait":null,"organization_id":null},{"file_id":7,"file_cover":"20190000/g.jpg","file_category":2,"user_name":null,"user_id":null,"user_portrait":null,"file_name":"文件名称","file_collection":0,"teacher_name":"教师姓名","user_teacher_id":1,"teacher_portrait":"20190000/z.jpg","organization_name":null,"organization_portrait":null,"organization_id":null},{"file_id":10,"file_cover":"20190000/b.jpg","file_category":1,"user_name":null,"user_id":null,"user_portrait":null,"file_name":"首页文件","file_collection":0,"teacher_name":null,"user_teacher_id":null,"teacher_portrait":null,"organization_name":null,"organization_portrait":null,"organization_id":null},{"file_id":11,"file_cover":"20190000/i.jpg","file_category":1,"user_name":"定稿测试","user_id":2,"user_portrait":"20190000/z.jpg","file_name":"首页文件","file_collection":0,"teacher_name":null,"user_teacher_id":null,"teacher_portrait":null,"organization_name":null,"organization_portrait":null,"organization_id":null},{"file_id":12,"file_cover":"20190000/c.jpg","file_category":1,"user_name":"定稿测试","user_id":2,"user_portrait":"20190000/z.jpg","file_name":"首页图片","file_collection":0,"teacher_name":null,"user_teacher_id":null,"teacher_portrait":null,"organization_name":null,"organization_portrait":null,"organization_id":null},{"file_id":13,"file_cover":"20190000/e.jpg","file_category":1,"user_name":"定稿测试","user_id":2,"user_portrait":"20190000/z.jpg","file_name":"首页视频地址","file_collection":0,"teacher_name":null,"user_teacher_id":null,"teacher_portrait":null,"organization_name":null,"organization_portrait":null,"organization_id":null},{"file_id":14,"file_cover":"20190000/g.jpg","file_category":1,"user_name":"定稿测试","user_id":2,"user_portrait":"20190000/z.jpg","file_name":"首页音乐","file_collection":0,"teacher_name":null,"user_teacher_id":null,"teacher_portrait":null,"organization_name":null,"organization_portrait":null,"organization_id":null},{"file_id":18,"file_cover":"文件封面测试地址","file_category":2,"user_name":null,"user_id":null,"user_portrait":null,"file_name":"文件名称","file_collection":0,"teacher_name":"教师姓名","user_teacher_id":1,"teacher_portrait":"20190000/z.jpg","organization_name":null,"organization_portrait":null,"organization_id":null}]
         */

        private CurrentRegionBean current_region;
        private InformationBean information;
        private List<DanceTypeBean> dance_type;
        private List<BannerBean> banner;
        private List<DistrictBean> district;
        private List<VideoBean> video;

        public CurrentRegionBean getCurrent_region() {
            return current_region;
        }

        public void setCurrent_region(CurrentRegionBean current_region) {
            this.current_region = current_region;
        }

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

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<DistrictBean> getDistrict() {
            return district;
        }

        public void setDistrict(List<DistrictBean> district) {
            this.district = district;
        }

        public List<VideoBean> getVideo() {
            return video;
        }

        public void setVideo(List<VideoBean> video) {
            this.video = video;
        }

        public static class CurrentRegionBean {
            /**
             * district_id : 1
             * district_name : 天津
             */

            private int district_id;
            private String district_name;

            public int getDistrict_id() {
                return district_id;
            }

            public void setDistrict_id(int district_id) {
                this.district_id = district_id;
            }

            public String getDistrict_name() {
                return district_name;
            }

            public void setDistrict_name(String district_name) {
                this.district_name = district_name;
            }
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

        public static class BannerBean {
            /**
             * banner_content : 20190106/81dfdcefb2877ef36df97ccb3e76bd22.gif
             */

            private String banner_content;

            public String getBanner_content() {
                return banner_content;
            }

            public void setBanner_content(String banner_content) {
                this.banner_content = banner_content;
            }
        }
    }
}
