package com.android.leleyouba.ybshop.classify.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xalo on 2017/3/1.
 */

public class BigClassifyBean {

    private List<AdvBean> advBeen;//广告
    private List<TitleBean> titleStr;//分类的标题
    private ArrayList<SmallClassifyBean> smallClassifies;//小分类集合

    public BigClassifyBean(List<AdvBean> advBeen, List<TitleBean> titleStr, ArrayList<SmallClassifyBean> smallClassifies) {
        this.advBeen = advBeen;
        this.titleStr = titleStr;
        this.smallClassifies = smallClassifies;
    }

    public List<AdvBean> getAdvBeen() {
        return advBeen;
    }

    public void setAdvBeen(List<AdvBean> advBeen) {
        this.advBeen = advBeen;
    }

    public List<TitleBean> getTitleStr() {
        return titleStr;
    }

    public void setTitleStr(List<TitleBean> titleStr) {
        this.titleStr = titleStr;
    }

    public ArrayList<SmallClassifyBean> getSmallClassifies() {
        return smallClassifies;
    }

    public void setSmallClassifies(ArrayList<SmallClassifyBean> smallClassifies) {
        this.smallClassifies = smallClassifies;
    }
}
