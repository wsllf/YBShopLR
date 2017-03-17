package com.android.leleyouba.ybshop.mine.bean;

import java.util.List;

/**
 * Created by xalo on 2017/3/9.
 */

public class AllAddress {
        List<AddressBean> AddressMsgs;

    public AllAddress() {
    }

    public AllAddress(List<AddressBean> addressMsgs) {
        AddressMsgs = addressMsgs;
    }

    public List<AddressBean> getAddressMsgs() {
        return AddressMsgs;
    }

    public void setAddressMsgs(List<AddressBean> addressMsgs) {
        AddressMsgs = addressMsgs;
    }
}
