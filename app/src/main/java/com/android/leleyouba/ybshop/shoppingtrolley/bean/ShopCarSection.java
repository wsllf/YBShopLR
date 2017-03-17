package com.android.leleyouba.ybshop.shoppingtrolley.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by xalo on 2017/3/2.
 */

public class ShopCarSection extends SectionEntity<ShopCarModel>{

    private boolean isprivilege;

    public ShopCarSection(boolean isHeader, String header, boolean isprivilege) {
        super(isHeader, header);
        this.isprivilege = isprivilege;
    }

    public ShopCarSection(ShopCarModel shopCarModel) {
        super(shopCarModel);
    }


    public boolean isprivilege() {
        return isprivilege;
    }

    public void setIsprivilege(boolean isprivilege) {
        this.isprivilege = isprivilege;
    }
}
