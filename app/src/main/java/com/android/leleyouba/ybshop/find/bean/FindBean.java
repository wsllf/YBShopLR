package com.android.leleyouba.ybshop.find.bean;

import com.android.leleyouba.ybshop.common.TypeFactory;
import com.android.leleyouba.ybshop.common.Visitable;

import java.util.ArrayList;

/**
 * Created by xalo on 2017/3/7.
 */

public class FindBean implements Visitable {
    ArrayList<FindClassifyBean> findClassifyBeanDatas;

    public FindBean() {
    }

    public FindBean(ArrayList<FindClassifyBean> findClassifyBeanDatas) {
        this.findClassifyBeanDatas = findClassifyBeanDatas;
    }

    public ArrayList<FindClassifyBean> getFindClassifyBeanDatas() {
        return findClassifyBeanDatas;
    }

    public void setFindClassifyBeanDatas(ArrayList<FindClassifyBean> findClassifyBeanDatas) {
        this.findClassifyBeanDatas = findClassifyBeanDatas;
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
