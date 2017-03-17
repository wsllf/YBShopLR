package com.android.leleyouba.ybshop.classify.bean;

import com.android.leleyouba.ybshop.common.TypeFactory;
import com.android.leleyouba.ybshop.common.Visitable;

/**
 * 商品分类的标题类
 */

public class TitleBean implements Visitable{
    private String title;//分类标题
    private boolean hasCharts;//是否有排行榜

    public TitleBean(String title, boolean hasCharts) {
        this.title = title;
        this.hasCharts = hasCharts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHasCharts() {
        return hasCharts;
    }

    public void setHasCharts(boolean hasCharts) {
        this.hasCharts = hasCharts;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }

    @Override
    public int getSpanSize() {
        return 3;
    }
}
