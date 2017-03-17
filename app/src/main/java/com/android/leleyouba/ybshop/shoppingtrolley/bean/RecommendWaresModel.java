package com.android.leleyouba.ybshop.shoppingtrolley.bean;

import com.android.leleyouba.ybshop.common.TypeFactory;
import com.android.leleyouba.ybshop.common.Visitable;

/**
 * Created by xalo on 2017/2/28.
 * //为你推荐的商品model
 */

public class RecommendWaresModel implements Visitable {


    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }

    @Override
    public int getSpanSize() {
        return 2;
    }
}
