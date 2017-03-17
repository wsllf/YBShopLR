package com.android.leleyouba.ybshop.shoppingtrolley.bean;

import com.android.leleyouba.ybshop.common.TypeFactory;
import com.android.leleyouba.ybshop.common.Visitable;

/**
 * Created by xalo on 2017/2/28.
 *
 * 购物车的伪订单的Model
 */

public class ShoppingTrolleyWaresModel implements Visitable {

    private String ShopStore;

    public String getShopStore() {
        return ShopStore;
    }

    public void setShopStore(String shopStore) {
        ShopStore = shopStore;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }

    @Override
    public int getSpanSize() {
        return 4;
    }
}
