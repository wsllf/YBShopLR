package com.android.leleyouba.ybshop.classify.bean;

import com.android.leleyouba.ybshop.common.TypeFactory;
import com.android.leleyouba.ybshop.common.Visitable;

/**
 * 商品种类
 */

public class SmallClassifyBean implements Visitable{

    private String categoryImg;//
    private String categoryName;

    public SmallClassifyBean(String categoryImg, String categoryName) {
        this.categoryImg = categoryImg;
        this.categoryName = categoryName;
    }

    public String getCategoryImg() {
        return categoryImg;
    }

    public void setCategoryImg(String categoryImg) {
        this.categoryImg = categoryImg;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }

    @Override
    public int getSpanSize() {
        return 1;
    }
}
