package com.dazhukeji.douwu.bean.follow;

import com.dazhukeji.douwu.bean.publicBean.BaseBean;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/17 16:13
 * 功能描述：
 */
public class UserAttentionOrganizationBean extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * organization_id : 1
         * organization_name : 乐舞机构
         * organization_cover : 20181127/162f7150a9a56b364d67162d50a952f2.jpg
         * organization_site : 汀棠路
         * organization_facility : WiFi
         * organization_business_hours : AM 9.30~11.30   PM 1.30~4.30
         * organization_level : 5
         * organization_district_id : 1
         * organization_type : 舞蹈种类
         */

        private int organization_id;
        private String organization_name;
        private String organization_cover;
        private String organization_site;
        private String organization_facility;
        private String organization_business_hours;
        private int organization_level;
        private int organization_district_id;
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

        public String getOrganization_cover() {
            return organization_cover;
        }

        public void setOrganization_cover(String organization_cover) {
            this.organization_cover = organization_cover;
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

        public String getOrganization_type() {
            return organization_type;
        }

        public void setOrganization_type(String organization_type) {
            this.organization_type = organization_type;
        }
    }
}
