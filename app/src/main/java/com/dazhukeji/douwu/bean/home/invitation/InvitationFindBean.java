package com.dazhukeji.douwu.bean.home.invitation;

import com.dazhukeji.douwu.bean.publicBean.BaseBean;

/**
 * 创建者：zhangyunfei
 * 时间：2018/12/17
 * 联系方式：32457127@qq.com
 * 功能描述：
 */
public class InvitationFindBean extends BaseBean {

    /**
     * data : {"organization_name":"乐舞机构","invitation_contact":"陈经理","invitation_interview_site":"汀棠路","invitation_interview_time":"早10.00","invitation_dance_type":"拉丁舞","invitation_age_demand":"25","invitation_explain":"认真负责","invitation_organization_picture":"20181127/162f7150a9a56b364d67162d50a952f2.jpg","invitation_organization_picture2":"20181127/162f7150a9a56b364d67162d50a952f2.jpg","invitation_phone":"15999999999"}
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
         * organization_name : 乐舞机构
         * invitation_contact : 陈经理
         * invitation_interview_site : 汀棠路
         * invitation_interview_time : 早10.00
         * invitation_dance_type : 拉丁舞
         * invitation_age_demand : 25
         * invitation_explain : 认真负责
         * invitation_organization_picture : 20181127/162f7150a9a56b364d67162d50a952f2.jpg
         * invitation_organization_picture2 : 20181127/162f7150a9a56b364d67162d50a952f2.jpg
         * invitation_phone : 15999999999
         */

        private String organization_name;
        private String invitation_contact;
        private String invitation_interview_site;
        private String invitation_interview_time;
        private String invitation_dance_type;
        private String invitation_age_demand;
        private String invitation_explain;
        private String invitation_organization_picture;
        private String invitation_organization_picture2;
        private String invitation_phone;

        public String getOrganization_name() {
            return organization_name;
        }

        public void setOrganization_name(String organization_name) {
            this.organization_name = organization_name;
        }

        public String getInvitation_contact() {
            return invitation_contact;
        }

        public void setInvitation_contact(String invitation_contact) {
            this.invitation_contact = invitation_contact;
        }

        public String getInvitation_interview_site() {
            return invitation_interview_site;
        }

        public void setInvitation_interview_site(String invitation_interview_site) {
            this.invitation_interview_site = invitation_interview_site;
        }

        public String getInvitation_interview_time() {
            return invitation_interview_time;
        }

        public void setInvitation_interview_time(String invitation_interview_time) {
            this.invitation_interview_time = invitation_interview_time;
        }

        public String getInvitation_dance_type() {
            return invitation_dance_type;
        }

        public void setInvitation_dance_type(String invitation_dance_type) {
            this.invitation_dance_type = invitation_dance_type;
        }

        public String getInvitation_age_demand() {
            return invitation_age_demand;
        }

        public void setInvitation_age_demand(String invitation_age_demand) {
            this.invitation_age_demand = invitation_age_demand;
        }

        public String getInvitation_explain() {
            return invitation_explain;
        }

        public void setInvitation_explain(String invitation_explain) {
            this.invitation_explain = invitation_explain;
        }

        public String getInvitation_organization_picture() {
            return invitation_organization_picture;
        }

        public void setInvitation_organization_picture(String invitation_organization_picture) {
            this.invitation_organization_picture = invitation_organization_picture;
        }

        public String getInvitation_organization_picture2() {
            return invitation_organization_picture2;
        }

        public void setInvitation_organization_picture2(String invitation_organization_picture2) {
            this.invitation_organization_picture2 = invitation_organization_picture2;
        }

        public String getInvitation_phone() {
            return invitation_phone;
        }

        public void setInvitation_phone(String invitation_phone) {
            this.invitation_phone = invitation_phone;
        }
    }
}
