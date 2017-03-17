package com.android.leleyouba.ybshop.find.bean;

import com.android.leleyouba.ybshop.common.TypeFactory;
import com.android.leleyouba.ybshop.common.Visitable;

/**
 * Created by xalo on 2017/3/7.
 */

public class FindRightImgItemBean implements Visitable {
    private String titleStr;
    private String contentStr;
    private String authorStr;
    private String dateStr;
    private String browseStr;
    private String leftCirImg;
    private String imgStr;
    boolean isMedia;

    public FindRightImgItemBean() {
    }

    public FindRightImgItemBean(String titleStr, String contentStr, String authorStr, String dateStr, String browseStr, String leftCirImg, String imgStr, boolean isMedia) {
        this.titleStr = titleStr;
        this.contentStr = contentStr;
        this.authorStr = authorStr;
        this.dateStr = dateStr;
        this.browseStr = browseStr;
        this.leftCirImg = leftCirImg;
        this.imgStr = imgStr;
        this.isMedia = isMedia;
    }

    public String getTitleStr() {
        return titleStr;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    public String getContentStr() {
        return contentStr;
    }

    public void setContentStr(String contentStr) {
        this.contentStr = contentStr;
    }

    public String getAuthorStr() {
        return authorStr;
    }

    public void setAuthorStr(String authorStr) {
        this.authorStr = authorStr;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getBrowseStr() {
        return browseStr;
    }

    public void setBrowseStr(String browseStr) {
        this.browseStr = browseStr;
    }

    public String getLeftCirImg() {
        return leftCirImg;
    }

    public void setLeftCirImg(String leftCirImg) {
        this.leftCirImg = leftCirImg;
    }

    public String getImg() {
        return imgStr;
    }

    public void setImg(String img) {
        imgStr = img;
    }

    public boolean isMedia() {
        return isMedia;
    }

    public void setMedia(boolean media) {
        isMedia = media;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }

    @Override
    public int getSpanSize() {
        return 0;
    }
}
