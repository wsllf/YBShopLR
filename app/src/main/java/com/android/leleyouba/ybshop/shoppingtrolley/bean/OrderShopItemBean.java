package com.android.leleyouba.ybshop.shoppingtrolley.bean;

/**
 * Created by xalo on 2017/3/9.
 */

public class OrderShopItemBean {
    private String logoStr;
    private String shopName;
    private String shopImg;
    private String shopTitle;
    private String shopContent;
    private float shopPrice;
    private int shopNumber;
    private String shopDistribution;
    private String shopPayment;
    private String shopMsgToSeller;
    private int shopTotalNumber;
    private float shopTotalPrice;

    public OrderShopItemBean() {
    }

    public OrderShopItemBean(String logoStr, String shopName, String shopImg, String shopTitle, String shopContent, float shopPrice, int shopNumber, String shopDistribution, String shopPayment, String shopMsgToSeller, int shopTotalNumber, float shopTotalPrice) {
        this.logoStr = logoStr;
        this.shopName = shopName;
        this.shopImg = shopImg;
        this.shopTitle = shopTitle;
        this.shopContent = shopContent;
        this.shopPrice = shopPrice;
        this.shopNumber = shopNumber;
        this.shopDistribution = shopDistribution;
        this.shopPayment = shopPayment;
        this.shopMsgToSeller = shopMsgToSeller;
        this.shopTotalNumber = shopTotalNumber;
        this.shopTotalPrice = shopTotalPrice;
    }

    public String getLogoStr() {
        return logoStr;
    }

    public void setLogoStr(String logoStr) {
        this.logoStr = logoStr;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public String getShopTitle() {
        return shopTitle;
    }

    public void setShopTitle(String shopTitle) {
        this.shopTitle = shopTitle;
    }

    public String getShopContent() {
        return shopContent;
    }

    public void setShopContent(String shopContent) {
        this.shopContent = shopContent;
    }

    public float getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(float shopPrice) {
        this.shopPrice = shopPrice;
    }

    public int getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(int shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getShopDistribution() {
        return shopDistribution;
    }

    public void setShopDistribution(String shopDistribution) {
        this.shopDistribution = shopDistribution;
    }

    public String getShopPayment() {
        return shopPayment;
    }

    public void setShopPayment(String shopPayment) {
        this.shopPayment = shopPayment;
    }

    public String getShopMsgToSeller() {
        return shopMsgToSeller;
    }

    public void setShopMsgToSeller(String shopMsgToSeller) {
        this.shopMsgToSeller = shopMsgToSeller;
    }

    public int getShopTotalNumber() {
        return shopTotalNumber;
    }

    public void setShopTotalNumber(int shopTotalNumber) {
        this.shopTotalNumber = shopTotalNumber;
    }

    public float getShopTotalPrice() {
        return shopTotalPrice;
    }

    public void setShopTotalPrice(float shopTotalPrice) {
        this.shopTotalPrice = shopTotalPrice;
    }
}
