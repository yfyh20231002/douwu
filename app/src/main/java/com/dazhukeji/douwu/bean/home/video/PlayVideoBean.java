package com.dazhukeji.douwu.bean.home.video;

import com.dazhukeji.douwu.bean.publicBean.BaseBean;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/12/18 13:36
 * 功能描述：
 */
public class PlayVideoBean extends BaseBean {

    /**
     * data : {"video":{"file_id":2,"file_content":"视频路径","file_name":"教师视频2"},"comments":[],"collect_state":1}
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
         * video : {"file_id":2,"file_content":"视频路径","file_name":"教师视频2"}
         * comments : []
         * collect_state : 1
         */

        private VideoBean video;
        private int collect_state;
        private List<?> comments;

        public VideoBean getVideo() {
            return video;
        }

        public void setVideo(VideoBean video) {
            this.video = video;
        }

        public int getCollect_state() {
            return collect_state;
        }

        public void setCollect_state(int collect_state) {
            this.collect_state = collect_state;
        }

        public List<?> getComments() {
            return comments;
        }

        public void setComments(List<?> comments) {
            this.comments = comments;
        }

        public static class VideoBean {
            /**
             * file_id : 2
             * file_content : 视频路径
             * file_name : 教师视频2
             */

            private int file_id;
            private String file_content;
            private String file_name;

            public int getFile_id() {
                return file_id;
            }

            public void setFile_id(int file_id) {
                this.file_id = file_id;
            }

            public String getFile_content() {
                return file_content;
            }

            public void setFile_content(String file_content) {
                this.file_content = file_content;
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
