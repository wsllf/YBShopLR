package com.android.leleyouba.ybshop.mine.util;

import com.android.leleyouba.ybshop.mine.bean.AddressBean;

import java.util.ArrayList;

/**
 * Created by xalo on 2017/3/9.
 */

public class UserInfoSave {

    private ArrayList<AddressBean> address;
    private String answer;
    private String birth;
    private String id;
    private String nickname;
    private String phoneNum;
    private String photo;
    private String question;
    private String sex;
    private String username;


    private static UserInfoSave userInfoSave;

    private UserInfoSave(){}

    public static UserInfoSave getInstace(){

        synchronized (UserInfoSave.class){
            if (userInfoSave == null){
                userInfoSave = new UserInfoSave();
            }
        }
        return userInfoSave;
    }


    public AddressBean getDefaultAddress(){
        if (address != null && address.size()>0){
            if (address.size() == 0){
                return address.get(0);
            }
            for (int i = 0; i < address.size(); i++) {
                AddressBean addressBean = address.get(i);
                if (addressBean.getDefault_address().equals("1")){
                    return addressBean;
                }
            }
        }
        return null;
    }

    public ArrayList<AddressBean> getAddress() {
        return address;
    }

    public void setAddress(ArrayList<AddressBean> address) {
        this.address = address;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
