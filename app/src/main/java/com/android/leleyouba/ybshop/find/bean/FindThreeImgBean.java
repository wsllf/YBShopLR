package com.android.leleyouba.ybshop.find.bean;

import com.android.leleyouba.ybshop.common.TypeFactory;
import com.android.leleyouba.ybshop.common.Visitable;

/**
 * Created by xalo on 2017/3/7.
 */

public class FindThreeImgBean implements Visitable {

    private String imgStr1;
    private String imgStr2;
    private String imgStr3;
    private String themeStr1;
    private String themeStr2;
    private String themeStr3;
    private String contentStr1;
    private String contentStr2;
    private String contentStr3;

    public FindThreeImgBean(String imgStr1, String imgStr2, String imgStr3, String themeStr1, String themeStr2, String themeStr3, String contentStr1, String contentStr2, String contentStr3) {
        this.imgStr1 = imgStr1;
        this.imgStr2 = imgStr2;
        this.imgStr3 = imgStr3;
        this.themeStr1 = themeStr1;
        this.themeStr2 = themeStr2;
        this.themeStr3 = themeStr3;
        this.contentStr1 = contentStr1;
        this.contentStr2 = contentStr2;
        this.contentStr3 = contentStr3;
    }

    public String getImgStr1() {
        return imgStr1;
    }

    public void setImgStr1(String imgStr1) {
        this.imgStr1 = imgStr1;
    }

    public String getImgStr2() {
        return imgStr2;
    }

    public void setImgStr2(String imgStr2) {
        this.imgStr2 = imgStr2;
    }

    public String getImgStr3() {
        return imgStr3;
    }

    public void setImgStr3(String imgStr3) {
        this.imgStr3 = imgStr3;
    }

    public String getThemeStr1() {
        return themeStr1;
    }

    public void setThemeStr1(String themeStr1) {
        this.themeStr1 = themeStr1;
    }

    public String getThemeStr2() {
        return themeStr2;
    }

    public void setThemeStr2(String themeStr2) {
        this.themeStr2 = themeStr2;
    }

    public String getThemeStr3() {
        return themeStr3;
    }

    public void setThemeStr3(String themeStr3) {
        this.themeStr3 = themeStr3;
    }

    public String getContentStr1() {
        return contentStr1;
    }

    public void setContentStr1(String contentStr1) {
        this.contentStr1 = contentStr1;
    }

    public String getContentStr2() {
        return contentStr2;
    }

    public void setContentStr2(String contentStr2) {
        this.contentStr2 = contentStr2;
    }

    public String getContentStr3() {
        return contentStr3;
    }

    public void setContentStr3(String contentStr3) {
        this.contentStr3 = contentStr3;
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
