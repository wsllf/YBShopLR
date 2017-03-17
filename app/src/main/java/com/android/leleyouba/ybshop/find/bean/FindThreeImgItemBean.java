package com.android.leleyouba.ybshop.find.bean;

import com.android.leleyouba.ybshop.common.TypeFactory;
import com.android.leleyouba.ybshop.common.Visitable;

/**
 * Created by xalo on 2017/3/7.
 */

public class FindThreeImgItemBean implements Visitable {

    private String titleStr;
    private String contentStr;
    private String imgOne;
    private String imgTwo;
    private String imgThree;
    private String authorStr;
    private String leftCivImg;
    private String browseStr;

    public FindThreeImgItemBean() {
    }

    public FindThreeImgItemBean(String titleStr, String contentStr, String imgOne, String imgTwo, String imgThree, String authorStr, String leftCivImg, String browseStr) {
        this.titleStr = titleStr;
        this.contentStr = contentStr;
        this.imgOne = imgOne;
        this.imgTwo = imgTwo;
        this.imgThree = imgThree;
        this.authorStr = authorStr;
        this.leftCivImg = leftCivImg;
        this.browseStr = browseStr;
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

    public String getImgOne() {
        return imgOne;
    }

    public void setImgOne(String imgOne) {
        this.imgOne = imgOne;
    }

    public String getImgTwo() {
        return imgTwo;
    }

    public void setImgTwo(String imgTwo) {
        this.imgTwo = imgTwo;
    }

    public String getImgThree() {
        return imgThree;
    }

    public void setImgThree(String imgThree) {
        this.imgThree = imgThree;
    }

    public String getAuthorStr() {
        return authorStr;
    }

    public void setAuthorStr(String authorStr) {
        this.authorStr = authorStr;
    }

    public String getLeftCivImg() {
        return leftCivImg;
    }

    public void setLeftCivImg(String leftCivImg) {
        this.leftCivImg = leftCivImg;
    }

    public String getBrowseStr() {
        return browseStr;
    }

    public void setBrowseStr(String browseStr) {
        this.browseStr = browseStr;
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
