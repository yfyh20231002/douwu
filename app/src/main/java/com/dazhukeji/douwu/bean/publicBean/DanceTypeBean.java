package com.dazhukeji.douwu.bean.publicBean;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/6 10:33
 * 功能描述：
 */
public class DanceTypeBean extends BaseBean{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
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
}
