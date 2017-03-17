package com.android.leleyouba.ybshop.shoppingtrolley.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by xalo on 2017/3/2.
 */

public class ShopCarModel {

    private String brief_infor;
    private String count;
    private String fileFileName;
    private String goodsId;
    private String goodsName;
    private String goodsType;
    private String id;
    private String price;
    private String seller_id;
    private String storeName;
    private String u_id;
    private boolean checked;

    public ShopCarModel() {
    }

    public ShopCarModel(String brief_infor, String count, String fileFileName, String goodsId, String goodsName,
                        String goodsType, String id, String price, String seller_id, String storeName, String u_id,
                        boolean checked) {
        this.brief_infor = brief_infor;
        this.count = count;
        this.fileFileName = fileFileName;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsType = goodsType;
        this.id = id;
        this.price = price;
        this.seller_id = seller_id;
        this.storeName = storeName;
        this.u_id = u_id;
        this.checked = checked;
    }

    public String getBrief_infor() {
        return brief_infor;
    }

    public void setBrief_infor(String brief_infor) {
        this.brief_infor = brief_infor;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
