package com.android.leleyouba.ybshop.shoppingtrolley.bean;

import java.util.List;

/**
 * Created by xalo on 2017/3/13.
 */

public class AllGoods {
    private List<GoodsInfo> CartMsgs;

    public AllGoods() {
    }

    public AllGoods(List<GoodsInfo> cartMsgs) {
        CartMsgs = cartMsgs;
    }

    public List<GoodsInfo> getCartMsgs() {
        return CartMsgs;
    }

    public void setCartMsgs(List<GoodsInfo> cartMsgs) {
        CartMsgs = cartMsgs;
    }
}
