package com.android.leleyouba.ybshop.mine.bean;

import android.widget.ImageView;

/**
 * Created by xalo on 2017/2/27.
 */

public class MsgItemBean {

    private String imgStr;
    private String title;
    private String content;
    private String date;

    public MsgItemBean() {
    }

    public MsgItemBean(String imgStr, String title, String content, String date) {
        this.imgStr = imgStr;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String getImgStr() {
        return imgStr;
    }

    public void setImgStr(String imgStr) {
        this.imgStr = imgStr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
