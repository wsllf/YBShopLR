package com.android.leleyouba.ybshop.mine.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.URLCollection;
import com.android.leleyouba.ybshop.mine.util.CommonMethod;
import com.android.leleyouba.ybshop.mine.util.UserStatusUtil;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_register_account, et_register_pwd, et_register_confirm, et_register_phoneNum, et_register_verification;//输入框
    Button btn_register_register;//注册按钮
    ImageView iv_register_account_clear, iv_register_pwd_clear, iv_register_confirm_clear, iv_register_phoneNum_clear, iv_register_verification_clear;//清除按钮
    ImageView iv_register_pwd_eye, iv_register_confirm_eye;//眼睛
    ImageView iv_register_verification;//验证码
    private String accountStr, pwdStr, confirmStr, phoneNumStr, verificationStr;
    String str = "";

    String s;
    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //设置导航栏
        setToolBar();
        initView();
        //调用监听输入框的方法
        monitorEdiTextContent();
    }

    /**
     * 设置导航栏
     */
    private void setToolBar() {
        Toolbar toolBar_login = (Toolbar) findViewById(R.id.toolBar_register);
        setSupportActionBar(toolBar_login);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_arrows);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.translation_animation_back_in, R.anim.translation_animation_back_out);
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * 监听输入框的方法
     */
    private void monitorEdiTextContent() {
        CommonMethod.clearContentByHit(et_register_account, iv_register_account_clear);
        CommonMethod.clearContentByHit(et_register_pwd, iv_register_pwd_clear);
        CommonMethod.clearContentByHit(et_register_confirm, iv_register_confirm_clear);
        CommonMethod.clearContentByHit(et_register_phoneNum, iv_register_phoneNum_clear);
        CommonMethod.clearContentByHit(et_register_verification, iv_register_verification_clear);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        et_register_account = (EditText) findViewById(R.id.et_register_account);
        et_register_pwd = (EditText) findViewById(R.id.et_register_pwd);
        et_register_confirm = (EditText) findViewById(R.id.et_register_confirm);
        et_register_phoneNum = (EditText) findViewById(R.id.et_register_phoneNum);
        et_register_verification = (EditText) findViewById(R.id.et_register_verification);
        btn_register_register = (Button) findViewById(R.id.btn_register_register);
        iv_register_account_clear = (ImageView) findViewById(R.id.iv_register_account_clear);
        iv_register_pwd_clear = (ImageView) findViewById(R.id.iv_register_pwd_clear);
        iv_register_confirm_clear = (ImageView) findViewById(R.id.iv_register_confirm_clear);
        iv_register_phoneNum_clear = (ImageView) findViewById(R.id.iv_register_phoneNum_clear);
        iv_register_verification_clear = (ImageView) findViewById(R.id.iv_register_verification_clear);
        iv_register_pwd_eye = (ImageView) findViewById(R.id.iv_register_pwd_eye);
        iv_register_confirm_eye = (ImageView) findViewById(R.id.iv_register_confirm_eye);
        iv_register_verification = (ImageView) findViewById(R.id.iv_register_verification);

        btn_register_register.setOnClickListener(this);
        iv_register_account_clear.setOnClickListener(this);
        iv_register_pwd_clear.setOnClickListener(this);
        iv_register_confirm_clear.setOnClickListener(this);
        iv_register_phoneNum_clear.setOnClickListener(this);
        iv_register_verification_clear.setOnClickListener(this);
        iv_register_pwd_eye.setOnClickListener(this);
        iv_register_confirm_eye.setOnClickListener(this);
        iv_register_verification.setOnClickListener(this);

        CommonMethod.changeImage(okHttpClient, handler, CommonMethod.SUCCESS, this, URLCollection.REGISTIMGURL);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register_register:
                if (!isNull()) {
                    Toast.makeText(RegisterActivity.this, str, Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //发送注册请求
                    registerServer();
                }
                break;
            case R.id.iv_register_account_clear:
                et_register_account.setText("");
                break;
            case R.id.iv_register_pwd_clear:
                et_register_pwd.setText("");
                break;
            case R.id.iv_register_confirm_clear:
                et_register_confirm.setText("");
                break;
            case R.id.iv_register_phoneNum_clear:
                et_register_phoneNum.setText("");
                break;
            case R.id.iv_register_verification_clear:
                et_register_verification.setText("");
                break;
            case R.id.iv_register_pwd_eye:
                CommonMethod.setPwdVisible(et_register_pwd, iv_register_pwd_eye);
                break;
            case R.id.iv_register_confirm_eye:
                CommonMethod.setPwdVisible(et_register_confirm, iv_register_confirm_eye);
                break;
            case R.id.iv_register_verification:

                CommonMethod.changeImage(okHttpClient, handler, CommonMethod.SUCCESS, this, URLCollection.REGISTIMGURL);
                break;
            default:
                break;
        }
    }

    private boolean isNull() {
        accountStr = et_register_account.getText().toString().trim();
        pwdStr = et_register_pwd.getText().toString().trim();
        confirmStr = et_register_confirm.getText().toString().trim();
        phoneNumStr = et_register_phoneNum.getText().toString().trim();
        verificationStr = et_register_verification.getText().toString().trim();

        if (accountStr == null || accountStr.equals("")) {
            str = "账号不能为空";
            return false;
        }
        if (pwdStr == null || pwdStr.equals("")) {
            str = "密码不能为空";
            return false;
        }
        if (pwdStr.length() < 6) {
            str = "密码长度不能小于6";
            return false;
        }
        if (confirmStr == null || confirmStr.equals("")) {
            str = "请再次确认密码";
            return false;
        }
        if (phoneNumStr == null || phoneNumStr.equals("")) {
            str = "电话号码不能为空";
            return false;
        }
        if (!TextUtils.equals(pwdStr, confirmStr)) {
            str = "两次输入密码不一致";
            return false;
        }
        if (TextUtils.isEmpty(verificationStr)) {
            str = "验证码不能为空";
            return false;
        }
        return true;
    }


    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                //加载网络成功进行UI的更新,处理得到的图片资源
                case CommonMethod.SUCCESS:
                    //通过message，拿到字节数组
                    byte[] Picture = (byte[]) msg.obj;
                    //使用BitmapFactory工厂，把字节数组转化为bitmap
                    Bitmap bitmap = BitmapFactory.decodeByteArray(Picture, 0, Picture.length);
                    //通过imageview，设置图片
                    iv_register_verification.setImageBitmap(bitmap);

                    break;
                //当加载网络失败执行的逻辑代码
                case CommonMethod.FALL:
                    Toast.makeText(RegisterActivity.this, "网络出现了问题", Toast.LENGTH_SHORT).show();
                    break;
                case CommonMethod.COOKIE:
                    if (msg.what ==CommonMethod.COOKIE){
                        Toast.makeText(RegisterActivity.this,msg.obj.toString(),Toast.LENGTH_SHORT).show();
                    }
                    break;
                //当注册成功是显示的
                case CommonMethod.REGISTERSUCCESS:
                    if (msg.obj.equals("注册成功")) {
                        Toast.makeText(RegisterActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                        //设置要返回的内容
                        setResult(CommonMethod.SEND_ACCOUNT, new Intent().putExtra("data", accountStr));
                        finish();
                        overridePendingTransition(R.anim.translation_animation_back_in, R.anim.translation_animation_back_out);
                    } else {
                        Toast.makeText(RegisterActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    }

                    break;
            }
        }
    };

    /**
     * 注册的方法
     */
    private void registerServer() {

        Log.i("info_Login", "知道了session：" + s);
        FormBody formBody = new FormBody.Builder()
                .add("user.username", accountStr)
                .add("user.password", pwdStr)
                .add("confirmPassword", confirmStr)
                .add("user.phoneNum", phoneNumStr)
                .add("verify", verificationStr)
                .build();

        s = CommonMethod.string;

        Request request = new Request.Builder()
                .addHeader("cookie", s)
                .url(URLCollection.REGISTURL)
                .post(formBody)
                .build();


        Call call2 = okHttpClient.newCall(request);
        call2.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("info_call2fail", e.toString());


            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
//                    Log.i("info_call2success", response.body().string());
                    String string = response.body().string();
                    try {
                        JSONObject object = new JSONObject(string);
                        if (object.has("info")) {
                            String  info = object.getString("info");
                            Message msg = new Message();
                            msg.what = CommonMethod.REGISTERSUCCESS;
                            msg.obj = info;
                            handler.sendMessage(msg);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Headers headers = response.headers();
                Log.i("info_respons.headers", headers + "");

            }
        });
    }
}
