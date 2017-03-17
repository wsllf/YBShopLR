package com.android.leleyouba.ybshop.mine.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xalo on 2017/2/25.
 */

public class StorageUserCommunication {

    private String SHARED_NAME = "account";
    private Context context;
    static SharedPreferences preferences;

    public StorageUserCommunication(Context context) {
        this.context = context;
        //获取sharedpreference对象
         preferences =  context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);
    }

    public  void setUserCommunication(String accountName, String accountPwd){

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("accountName",accountName);
        editor.putString("accountPwd",accountPwd);
        editor.commit();
    }

    public String getUserCommunicationAccountName(){
        return preferences.getString("accountName","");
    }
    public String getUserCommunicationAccountPwd(){
        return preferences.getString("accountPwd","");
    }

}
