package com.dazhukeji.douwu.bean.home;

import com.dazhukeji.douwu.bean.publicBean.VideoBean;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/6 9:44
 * 功能描述：
 */
public class HomeIndexPagingBean {

    /**
     * code : 1
     * msg :
     * data : {"video":[{"file_id":1,"file_cover":"文件封面1","file_category":1,"user_name":"修改第二人啊！","user_id":2,"user_portrait":"20181116/68f792adfa334dd51dc5b202c68ea1f5.gif","file_name":"个人视频1","file_collection":9999,"teacher_name":null,"user_teacher_id":null,"teacher_portrait":null,"organization_name":null,"organization_portrait":null,"organization_id":null},{"file_id":2,"file_cover":"文件封面2","file_category":2,"user_name":null,"user_id":null,"user_portrait":null,"file_name":"教师视频2","file_collection":999,"teacher_name":"教师姓名","user_teacher_id":1,"teacher_portrait":"20181127/94bef36260d943e593d68415205a9ef8.jpg","organization_name":null,"organization_portrait":null,"organization_id":null},{"file_id":3,"file_cover":"文件封面3","file_category":3,"user_name":null,"user_id":null,"user_portrait":null,"file_name":"机构视频3","file_collection":99,"teacher_name":null,"user_teacher_id":null,"teacher_portrait":null,"organization_name":"机构名称","organization_portrait":"20181127/f7c04e0638c6a5674a2d95d2cdaf4393.jpg","organization_id":1}]}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<VideoBean> video;

        public List<VideoBean> getVideo() {
            return video;
        }

        public void setVideo(List<VideoBean> video) {
            this.video = video;
        }
    }
}
