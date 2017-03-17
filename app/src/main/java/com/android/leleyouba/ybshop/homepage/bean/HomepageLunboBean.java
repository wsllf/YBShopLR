package com.android.leleyouba.ybshop.homepage.bean;

import java.util.List;

/**
 * Created by xalo on 2017/3/14.
 */

public class HomepageLunboBean {

    /**
     * lunboList : [{"id":"0031031d-6281-4bf1-b4ad-089b12e4b066","imgurl":"http://192.168.10.149:8080/YoubaShopping/img/timg (6)1489463160797.jpeg","title":"哈哈"},{"id":"b7282f9d-de22-41cb-81ce-4fe67e1086bf","imgurl":"http://192.168.10.149:8080/YoubaShopping/img/timg (4)1489475584324.jpeg","title":"蒋耀峰"},{"id":"dc0b3551-1c84-45c5-84ff-00e5d0865e45","imgurl":"http://192.168.10.149:8080/YoubaShopping/img/timg (5)1489475596486.jpeg","title":"吴强"},{"id":"df707970-594a-49dd-bab4-34ec45479c79","imgurl":"http://192.168.10.149:8080/YoubaShopping/img/timg (10)1489475607763.jpeg","title":"毛维克"},{"id":"e5905c24-187e-4849-ab9c-edaf606707fd","imgurl":"http://192.168.10.149:8080/YoubaShopping/img/timg11489463335713.jpeg","title":"吴强小朋友"}]
     * status : 0
     * info : 轮播图成功调出
     */

    private int status;
    private String info;
    private List<LunboListBean> lunboList;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<LunboListBean> getLunboList() {
        return lunboList;
    }

    public void setLunboList(List<LunboListBean> lunboList) {
        this.lunboList = lunboList;
    }

    public static class LunboListBean {
        /**
         * id : 0031031d-6281-4bf1-b4ad-089b12e4b066
         * imgurl : http://192.168.10.149:8080/YoubaShopping/img/timg (6)1489463160797.jpeg
         * title : 哈哈
         */

        private String id;
        private String imgurl;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
