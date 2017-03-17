package com.android.leleyouba.ybshop.mine.bean;

import java.io.Serializable;

/**
 * Created by xalo on 2017/3/8.
 */

public class AddressBean  implements Serializable{

    /**
     * consigneeAddress : 西安
     * consigneeNum : 16854682563
     * consigneePC : 785126
     * consigneename : 冉涛
     * default_address : 1
     * id : 12314c98-e13f-4c5b-93e5-0fb577a53c7c
     * u_id : 000e4890-5437-43e6-b955-785f48681d59
     * username : rrrrr
     */

    private String consigneeAddress;
    private String consigneeNum;
    private String consigneePC;
    private String consigneename;
    private String default_address;
    private String id;
    private String u_id;
    private String username;

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneeNum() {
        return consigneeNum;
    }

    public void setConsigneeNum(String consigneeNum) {
        this.consigneeNum = consigneeNum;
    }

    public String getConsigneePC() {
        return consigneePC;
    }

    public void setConsigneePC(String consigneePC) {
        this.consigneePC = consigneePC;
    }

    public String getConsigneename() {
        return consigneename;
    }

    public void setConsigneename(String consigneename) {
        this.consigneename = consigneename;
    }

    public String getDefault_address() {
        return default_address;
    }

    public void setDefault_address(String default_address) {
        this.default_address = default_address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
