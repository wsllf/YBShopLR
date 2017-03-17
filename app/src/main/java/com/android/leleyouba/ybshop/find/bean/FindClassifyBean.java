package com.android.leleyouba.ybshop.find.bean;

import com.android.leleyouba.ybshop.common.TypeFactory;
import com.android.leleyouba.ybshop.common.Visitable;

/**
 * Created by xalo on 2017/3/6.
 */

public class FindClassifyBean implements Visitable {
    private String imgStr;
    private String titleStr;//分类标题

    public FindClassifyBean(String imgStr, String titleStr) {
        this.imgStr = imgStr;
        this.titleStr = titleStr;
    }

    public String getImgStr() {
        return imgStr;
    }

    public void setImgStr(String imgStr) {
        this.imgStr = imgStr;
    }

    public String getTitleStr() {
        return titleStr;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
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
