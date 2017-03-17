package com.android.leleyouba.ybshop.homepage.bean;

import android.content.Context;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xalo on 2017/3/3.
 */

public class HomepageClassifyBean {
    private List<String> carouselImgs;//轮播图集合
    private ArrayList<HomepageBaseClassifyBean> topBaseClassifyBeans;//上部
    private ArrayList<HomepageBaseClassifyBean> bottomBaseClassifyBeans;//上部
    private HomepageTitleBean classifyTitles;//分类标题

    public HomepageClassifyBean(List<String> carouselImgs, ArrayList<HomepageBaseClassifyBean> topBaseClassifyBeans, ArrayList<HomepageBaseClassifyBean> bottomBaseClassifyBeans, HomepageTitleBean classifyTitles) {
        this.carouselImgs = carouselImgs;
        this.topBaseClassifyBeans = topBaseClassifyBeans;
        this.bottomBaseClassifyBeans = bottomBaseClassifyBeans;
        this.classifyTitles = classifyTitles;
    }

    public List<String> getCarouselImgs() {
        return carouselImgs;
    }

    public void setCarouselImgs(List<String> carouselImgs) {
        this.carouselImgs = carouselImgs;
    }

    public ArrayList<HomepageBaseClassifyBean> getTopBaseClassifyBeans() {
        return topBaseClassifyBeans;
    }

    public void setTopBaseClassifyBeans(ArrayList<HomepageBaseClassifyBean> topBaseClassifyBeans) {
        this.topBaseClassifyBeans = topBaseClassifyBeans;
    }

    public ArrayList<HomepageBaseClassifyBean> getBottomBaseClassifyBeans() {
        return bottomBaseClassifyBeans;
    }

    public void setBottomBaseClassifyBeans(ArrayList<HomepageBaseClassifyBean> bottomBaseClassifyBeans) {
        this.bottomBaseClassifyBeans = bottomBaseClassifyBeans;
    }

    public HomepageTitleBean getClassifyTitles() {
        return classifyTitles;
    }

    public void setClassifyTitleStr(HomepageTitleBean classifyTitleStr) {
        this.classifyTitles = classifyTitleStr;
    }
}
