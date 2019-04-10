package com.dazhukeji.douwu.bean.home.organization;

import com.dazhukeji.douwu.bean.publicBean.BaseBean;
import com.dazhukeji.douwu.bean.publicBean.DistrictBean;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/7 15:42
 * 功能描述：
 */
public class OrganizationListBean extends BaseBean {

    /**
     * data : {"district":[{"district_id":1,"district_name":"天津"},{"district_id":2,"district_name":"北京"},{"district_id":3,"district_name":"深圳"},{"district_id":4,"district_name":"南京"},{"district_id":5,"district_name":"上海"},{"district_id":6,"district_name":"海南"}],"organization":[{"organization_id":1,"organization_name":"机构名称","organization_cover":"20181122/dfe52400de36aaf502a037f08f2f408f.jpg","organization_site":"机构地址","organization_facility":"机构设施","organization_business_hours":"机构营业时间","organization_level":5,"organization_district_id":1,"organization_type":"舞蹈种类"},{"organization_id":6,"organization_name":"机构名称","organization_cover":"aedrfg3sare5g41a6e5rg4ae6rg5","organization_site":"机构地址","organization_facility":"机构设施","organization_business_hours":"机构营业时间","organization_level":3,"organization_district_id":0,"organization_type":"舞蹈种类"},{"organization_id":7,"organization_name":"公安局","organization_cover":"20181230/457662d033eb57a530149af989ded2d9.png","organization_site":"啦啦门店名称测试","organization_facility":"wifi!=end=!微信支付!=end=!支付宝支付!=end=!停车场","organization_business_hours":"你们在","organization_level":3,"organization_district_id":0,"organization_type":"舞蹈课ing老师"},{"organization_id":4,"organization_name":"机构名称","organization_cover":"20181122/dfe52400de36aaf502a037f08f2f408f.jpg","organization_site":"机构地址","organization_facility":"机构设施","organization_business_hours":"14:00~20:00","organization_level":0,"organization_district_id":2,"organization_type":"舞蹈种类"},{"organization_id":5,"organization_name":"pst舞蹈联盟","organization_cover":"20181122/dfe52400de36aaf502a037f08f2f408f.jpg","organization_site":"机构地址","organization_facility":"机构设施","organization_business_hours":"14:00~20:00","organization_level":0,"organization_district_id":2,"organization_type":""}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<DistrictBean> district;
        private List<OrganizationBean> organization;

        public List<DistrictBean> getDistrict() {
            return district;
        }

        public void setDistrict(List<DistrictBean> district) {
            this.district = district;
        }

        public List<OrganizationBean> getOrganization() {
            return organization;
        }

        public void setOrganization(List<OrganizationBean> organization) {
            this.organization = organization;
        }
    }
}
