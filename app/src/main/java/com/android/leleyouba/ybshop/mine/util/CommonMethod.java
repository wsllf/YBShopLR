package com.android.leleyouba.ybshop.mine.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.URLCollection;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 */

public class CommonMethod {


    public static String URL = "";
    public static int GET_ACCOUNT = 0;
    public static int SEND_ACCOUNT = 1;
    public static int ADDRESS_REQUEST = 9;
    public static int ADDRESS_RESULT = 10;

    /**
     * 输入框一键清除内容的方法
     *
     * @param editText
     * @param imageView
     */
    public static void clearContentByHit(final EditText editText, final ImageView imageView) {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                //获取输入框中的内容
                String content = editText.getText().toString().trim();
                //判断输入框中是否有值
                if (content != null && !content.equals("")) {
                    //有值 图片显示
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    //无值  图片隐藏
                    imageView.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 设置输入框密码是否可见
     */
    static boolean isShowPwd = false;

    public static void setPwdVisible(EditText editText, ImageView imageView) {

        if (!isShowPwd) {
            //设置密码可见
            editText.setInputType(0x90);
            imageView.setBackgroundResource(R.mipmap.open_eye);
            isShowPwd = true;

        } else {
            //设置密码不可见
            editText.setInputType(0x81);
            imageView.setBackgroundResource(R.mipmap.close_eye);
            isShowPwd = false;
        }
    }

    public static String string;
    public static final int SUCCESS = 1;
    public static final int FALL = 2;
    public static final int LOGINSUCCESS = 3;
    public static final int REGISTERSUCCESS = 4;
    public static final int COOKIE = 5;
    public static final int COMMITFINDPWD = 6;
    public static final int COMMITVERIFYANSWER = 7;
    public static final int COMMITNEWPWD = 8;


    /**
     * 点击刷新验证码的请求方法
     */
    public static void changeImage(OkHttpClient okHttpClient, final Handler handler, final int SUCCESS, final Context context,String url) {

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("info_callFailure", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] byte_image = response.body().bytes();


                //通过handler更新UI
                Message message = handler.obtainMessage();
                message.obj = byte_image;

                message.what = SUCCESS;
                Log.i("info_handler", "handler");
                handler.sendMessage(message);

                //session
                Headers headers = response.headers();
                Log.d("info_headers", "header " + headers);
                List<String> cookies = headers.values("Set-Cookie");

                //判断服务器是否关闭
                if (cookies.size() <= 0) {

                    Message message1 = new Message();
                    message1.what = COOKIE;
                    message1.obj = "网络不给力，请查看网络设置";
                    handler.sendMessage(message1);

                    return;
                }
                String session = cookies.get(0);


                Log.d("info_cookies", "onResponse-size: " + cookies);

                string = session.substring(0, session.indexOf(";"));

//               String[] sessionid =  string.split("=");

                UserStatusUtil.getInstanse().setCookie(string);
                Log.i("info_s", "session is  :" + string);
            }

        });
    }


    public static String changeImage1(OkHttpClient okHttpClient, final Handler handler, final int SUCCESS, final Context context,String url) {

        Request request = new Request.Builder()
                .addHeader("cookie",UserStatusUtil.getInstanse().getCookie())
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            String s = new String();

            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("info_callFailure", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] byte_image = response.body().bytes();


                //通过handler更新UI
                Message message = handler.obtainMessage();
                message.obj = byte_image;

                message.what = SUCCESS;
                Log.i("info_handler", "handler");
                handler.sendMessage(message);

                //session
                Headers headers = response.headers();
                Log.d("info_headers", "header " + headers);
                List<String> cookies = headers.values("Set-Cookie");

                //判断服务器是否关闭
                if (cookies.size() <= 0) {

                    Message message1 = new Message();
                    message1.what = COOKIE;
                    message1.obj = "网络不给力，请查看网络设置";
                    handler.sendMessage(message1);

                    return;
                }
                String session = cookies.get(0);


                Log.d("info_cookies", "onResponse-size: " + cookies);

                string = session.substring(0, session.indexOf(";"));

//               String[] sessionid =  string.split("=");

//                UserStatusUtil.getInstanse().setCookie(string);
                Log.i("info_s", "session is  :" + string);
            }

        });
        return string;
    }

}
