package com.android.leleyouba.ybshop.homepage.bean;

/**
 * Created by xalo on 2017/3/2.
 */

public class HomePageSecKillBean {
    private String topImg;
    private String nowPrice;
    private String oldPrice;

    public HomePageSecKillBean(String topImg, String nowPrice, String oldPrice) {
        this.topImg = topImg;
        this.nowPrice = nowPrice;
        this.oldPrice = oldPrice;
    }

    public String getTopImg() {
        return topImg;
    }

    public void setTopImg(String topImg) {
        this.topImg = topImg;
    }

    public String getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }
}
