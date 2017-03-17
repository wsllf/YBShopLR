package com.android.leleyouba.ybshop.mine.util;

/**
 * Created by xalo on 2017/2/25.
 */

public class UserStatusUtil  {

    private boolean login;

    private String cookie;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    private static UserStatusUtil mStatusUtil;

    private UserStatusUtil(){
        login = false;
    }
    public static UserStatusUtil getInstanse(){
        synchronized (UserStatusUtil.class){
            if (mStatusUtil == null){
                mStatusUtil = new UserStatusUtil();
            }
            return mStatusUtil;
        }
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {

        this.login = login;
    }



}
