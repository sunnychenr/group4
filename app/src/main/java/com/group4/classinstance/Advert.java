package com.group4.classinstance;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bwfadmin on 2016/10/16.
 */

public class Advert implements Serializable {


    /**
     * error : 0
     * message : ok
     * data : [{"title":"装修省40%的秘笈，看这里！","imagesrc":"http://appmanager.17house.com/upload/20161006/57f5e069ab71d_t.jpg","imagesrc2":"http://appmanager.17house.com/upload/20161006/57f5e069cc0c4_t.jpg","tid":"","type":4,"banner_url":"http://m.17house.com/2615k0s1e2008.html"},{"title":"一起装修网强势入驻天猫","imagesrc":"http://appmanager.17house.com/upload/20160829/57c4099d411f8_t.jpg","imagesrc2":"http://appmanager.17house.com/upload/20160829/57c4099d4da3a_t.jpg","tid":"","type":4,"banner_url":"http://www.17house.com/news/251250.html"},{"title":"全包装修80平米仅需5.4万","imagesrc":"http://appmanager.17house.com/upload/20160712/5784c5d8ec177_t.png","imagesrc2":"http://appmanager.17house.com/upload/20160712/5784c5d91e7a3_t.png","tid":"","type":4,"banner_url":"http://beijing.17zhuangxiu.com/zhengzhuang/"},{"title":"7年服务700万业主，口碑太好了！","imagesrc":"http://appmanager.17house.com/upload/20160709/57810cf956f1d_t.png","imagesrc2":"http://appmanager.17house.com/upload/20160709/57810cf9811e5_t.png","tid":"","type":4,"banner_url":"http://m.17house.com/banner/index.html"}]
     */

    private int error;
    private String message;
    /**
     * title : 装修省40%的秘笈，看这里！
     * imagesrc : http://appmanager.17house.com/upload/20161006/57f5e069ab71d_t.jpg
     * imagesrc2 : http://appmanager.17house.com/upload/20161006/57f5e069cc0c4_t.jpg
     * tid :
     * type : 4
     * banner_url : http://m.17house.com/2615k0s1e2008.html
     */

    private List<DataBean> data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String title;
        private String imagesrc;
        private String imagesrc2;
        private String tid;
        private int type;
        private String banner_url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImagesrc() {
            return imagesrc;
        }

        public void setImagesrc(String imagesrc) {
            this.imagesrc = imagesrc;
        }

        public String getImagesrc2() {
            return imagesrc2;
        }

        public void setImagesrc2(String imagesrc2) {
            this.imagesrc2 = imagesrc2;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getBanner_url() {
            return banner_url;
        }

        public void setBanner_url(String banner_url) {
            this.banner_url = banner_url;
        }
    }
}
