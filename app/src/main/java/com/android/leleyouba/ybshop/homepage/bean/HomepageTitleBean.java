package com.android.leleyouba.ybshop.homepage.bean;

/**
 * Created by xalo on 2017/3/4.
 */

public class HomepageTitleBean {
    private String contentStr;//标题内容
    private int backgroundId;//标题背景

    public HomepageTitleBean(String contentStr, int backgroundId) {
        this.contentStr = contentStr;
        this.backgroundId = backgroundId;
    }

    public String getContentStr() {
        return contentStr;
    }

    public void setContentStr(String contentStr) {
        this.contentStr = contentStr;
    }

    public int getBackgroundId() {
        return backgroundId;
    }

    public void setBackgroundId(int backgroundId) {
        this.backgroundId = backgroundId;
    }
}
