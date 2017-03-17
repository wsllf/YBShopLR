package com.android.leleyouba.ybshop.shoppingtrolley.bean;

/**
 * Created by xalo on 2017/3/13.
 */


/**
 *
 * {
 "CartMsgs":[
 {
 "brief_infor":"343243243",
 "count":4,
 "fileFileName":"http://192.168.10.124:8080/YoubaShopping/img/551489222328417.png",
 "goodsId":"222f4104-112b-4a62-8485-d3684932777e",
 "goodsName":"AA",
 "goodsType":"家用电器, 电视, 电视品牌",
 "id":"438e403c-822e-41db-b6e1-c302ffab3fb1",
 "price":3243243,
 "seller_id":"670f4190-b76e-4f4e-9103-6018761096ab",
 "storeName":"ABCD",
 "u_id":"b3dfd390-7c60-4c37-bed9-a74454ed297e"
 },
 {
 "brief_infor":"的撒放到舒服的沙发",
 "count":5,
 "fileFileName":"&http://192.168.10.128:8080/YoubaShopping/img/加入购物车1489398020888.png",
 "goodsId":"6a1ada59-fffb-49d4-9057-945f2a30f0e8",
 "goodsName":"连衣裙",
 "goodsType":"潮流女装, 春季新品, 长裙",
 "id":"ebd13159-15c4-495b-b297-4297fa2943a4",
 "price":350,
 "seller_id":"cd23ec7b-de58-4e73-81ff-74b7fc158e2a",
 "storeName":"WW",
 "u_id":"b3dfd390-7c60-4c37-bed9-a74454ed297e"
 }
 ]
 }
 *
 */

public class GoodsInfo {

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


    public GoodsInfo() {
    }

    public GoodsInfo(String brief_infor, String count, String fileFileName, String goodsId, String goodsName, String
            goodsType, String id, String price, String seller_id, String storeName, String u_id) {
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
}
