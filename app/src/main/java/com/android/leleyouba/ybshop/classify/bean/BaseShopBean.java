package com.android.leleyouba.ybshop.classify.bean;


import java.util.List;

/**
 * Created by xalo on 2017/3/9.
 */

public class BaseShopBean {
    private String id;
    private String seller_id;//商家id
    private String goodsName;//货名
    private List<String> pictures;//图片集合
    private String source;//货源
    private int goodsStock;//库存
    private String saleMount;//数量
    private int inPrice;//价格
    private String detail_infor;//详细信息
    private String brief_info;//简介
    private String sell_status;//卖出状态
    private String goodsType;//商品类型
    private String storeName;//店铺名
    private String isActive;//是否参与分成
    private int vipPirce;//会员价
    private String evaluate;//评价

    private BaseShopBean(Builder builder) {
        this.id = builder.id;
        this.seller_id = builder.seller_id;
        this.goodsName = builder.goodsName;
        this.pictures = builder.pictures;
        this.source = builder.source;
        this.goodsStock = builder.goodsStock;
        this.saleMount = builder.saleMount;
        this.inPrice = builder.inPrice;
        this.detail_infor = builder.detail_infor;
        this.brief_info = builder.brief_info;
        this.sell_status = builder.sell_status;
        this.goodsType = builder.goodsType;
        this.storeName = builder.storeName;
        this.isActive = builder.isActive;
        this.vipPirce = builder.vipPirce;
        this.evaluate = builder.evaluate;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(int goodsStock) {
        this.goodsStock = goodsStock;
    }

    public String getSaleMount() {
        return saleMount;
    }

    public void setSaleMount(String saleMount) {
        this.saleMount = saleMount;
    }

    public int getInPrice() {
        return inPrice;
    }

    public void setInPrice(int inPrice) {
        this.inPrice = inPrice;
    }

    public String getDetail_infor() {
        return detail_infor;
    }

    public void setDetail_infor(String detail_infor) {
        this.detail_infor = detail_infor;
    }

    public String getBrief_info() {
        return brief_info;
    }

    public void setBrief_info(String brief_info) {
        this.brief_info = brief_info;
    }

    public String getSell_status() {
        return sell_status;
    }

    public void setSell_status(String sell_status) {
        this.sell_status = sell_status;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public int getVipPirce() {
        return vipPirce;
    }

    public void setVipPirce(int vipPirce) {
        this.vipPirce = vipPirce;
    }

    public static class Builder {
        private String id;
        private String seller_id;//商家id
        private String goodsName;//货名
        private List<String> pictures;//图片集合
        private String source;//货源
        private int goodsStock;//库存
        private String saleMount;//数量
        private int inPrice;//价格
        private String detail_infor;//详细信息
        private String brief_info;//简介
        private String sell_status;//卖出状态
        private String goodsType;//商品类型
        private String storeName;//店铺名
        private String isActive;//是否参与分成
        private int vipPirce;//会员价
        private String evaluate;//评价

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder seller_id(String seller_id) {
            this.seller_id = seller_id;
            return this;
        }

        public Builder goodsName(String goodsName) {
            this.goodsName = goodsName;
            return this;
        }

        public Builder pictures(List<String> pictures) {
            this.pictures = pictures;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        public Builder goodsStock(int goodsStock) {
            this.goodsStock = goodsStock;
            return this;
        }

        public Builder saleMount(String saleMount) {
            this.saleMount = saleMount;
            return this;
        }

        public Builder inPrice(int inPrice) {
            this.inPrice = inPrice;
            return this;
        }

        public Builder detail_infor(String detail_infor) {
            this.detail_infor = detail_infor;
            return this;
        }

        public Builder brief_info(String brief_info) {
            this.brief_info = brief_info;
            return this;
        }

        public Builder sell_status(String sell_status) {
            this.sell_status = sell_status;
            return this;
        }

        public Builder goodsType(String goodsType) {
            this.goodsType = goodsType;
            return this;
        }

        public Builder storeName(String storeName) {
            this.storeName = storeName;
            return this;
        }

        public Builder isActive(String isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder evaluate(String evaluate) {
            this.evaluate = evaluate;
            return this;
        }

        public Builder vipPirce(int vipPirce) {
            this.vipPirce = vipPirce;
            return this;
        }

        public BaseShopBean build() {
            return new BaseShopBean(this);
        }
    }
}
