package com.android.leleyouba.ybshop.homepage.bean;

/**
 * Created by xalo on 2017/3/3.
 */

public class HomepageViewFlipperBean {
    private String recommendStr;
    private String contentStr;

    public HomepageViewFlipperBean(String recommendStr, String contentStr) {
        this.recommendStr = recommendStr;
        this.contentStr = contentStr;
    }

    public String getRecommendStr() {
        return recommendStr;
    }

    public void setRecommendStr(String recommendStr) {
        this.recommendStr = recommendStr;
    }

    public String getContentStr() {
        return contentStr;
    }

    public void setContentStr(String contentStr) {
        this.contentStr = contentStr;
    }
}
