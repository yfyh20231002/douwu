package com.dazhukeji.douwu.bean.home.invitation;

import com.dazhukeji.douwu.bean.publicBean.BaseBean;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 时间：2018/12/17
 * 联系方式：32457127@qq.com
 * 功能描述：
 */
public class InvitationListBean extends BaseBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * invitation_id : 1
         * organization_id : 1
         * organization_name : 机构名称
         * invitation_explain : 说明
         */

        private int invitation_id;
        private int organization_id;
        private String organization_name;
        private String invitation_explain;

        public int getInvitation_id() {
            return invitation_id;
        }

        public void setInvitation_id(int invitation_id) {
            this.invitation_id = invitation_id;
        }

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

        public String getInvitation_explain() {
            return invitation_explain;
        }

        public void setInvitation_explain(String invitation_explain) {
            this.invitation_explain = invitation_explain;
        }
    }
}
