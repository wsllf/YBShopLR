package com.android.leleyouba.ybshop.classify.bean;

import com.android.leleyouba.ybshop.common.TypeFactory;
import com.android.leleyouba.ybshop.common.Visitable;

/**
 * 广告类
 */

public class AdvBean implements Visitable{

    private String advStr;

    public AdvBean(String advStr) {
        this.advStr = advStr;
    }

    public String getAdvStr() {
        return advStr;
    }

    public void setAdvStr(String advStr) {
        this.advStr = advStr;
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
