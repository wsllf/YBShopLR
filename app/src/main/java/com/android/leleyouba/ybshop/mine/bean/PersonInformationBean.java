package com.android.leleyouba.ybshop.mine.bean;

import java.util.ArrayList;

/**
 * Created by xalo on 2017/3/8.
 */

public class PersonInformationBean {


    /**
     * address : null
     * answer : 66
     * birth : 2017-03-08
     * id : db254bb3-f80f-4848-8620-bbc3fd0eb58e
     * nickname : fds
     * phoneNum : 78945612310
     * photo : http://192.168.10.124:8080/YoubaShopping/img/DIRECTORY_PICTURESf4561488978224100.jpg
     * question : 66
     * sex : 男
     * username : kkk
     */

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
