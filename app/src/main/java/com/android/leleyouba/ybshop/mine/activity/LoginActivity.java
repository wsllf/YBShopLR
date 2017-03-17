package com.android.leleyouba.ybshop.mine.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.leleyouba.ybshop.MainActivity;
import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.URLCollection;
import com.android.leleyouba.ybshop.mine.util.CommonMethod;

import com.android.leleyouba.ybshop.mine.util.StorageUserCommunication;
import com.android.leleyouba.ybshop.mine.util.UserStatusUtil;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.tls.OkHostnameVerifier;

/**
 * 登录界面
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "LoginActivity";

    EditText et_login_account, et_login_pwd, et_login_verification;
    Button btn_login_login, btn_login_register, btn_login_findPwd;
    RelativeLayout rl_login_sina, rl_login_qq;
    ImageView iv_login_verification, iv_login_account_clear, iv_login_pwd_clear, iv_login_pwd_eye, iv_login_verification_clear;
    String accountStr, pwdStr, verifyStr;
    String str = "";
    String s;
    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //设置导航栏
        setToolBar();
        //初始化控件
        initView();
        //调用监听输入框的方法
        monitorEdiTextContent();


    }

    /**
     * 设置导航栏
     */
    private void setToolBar() {
        Toolbar toolBar_login = (Toolbar) findViewById(R.id.toolBar_login);
        setSupportActionBar(toolBar_login);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_x);
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
     * 初始化控件
     */
    private void initView() {
        //初始化控件
        et_login_account = (EditText) findViewById(R.id.et_login_account);
        et_login_pwd = (EditText) findViewById(R.id.et_login_pwd);
        et_login_verification = (EditText) findViewById(R.id.et_login_verification);
        btn_login_login = (Button) findViewById(R.id.btn_login_login);
        btn_login_register = (Button) findViewById(R.id.btn_login_register);
        btn_login_findPwd = (Button) findViewById(R.id.btn_login_findPwd);
        rl_login_sina = (RelativeLayout) findViewById(R.id.rl_login_sina);
        rl_login_qq = (RelativeLayout) findViewById(R.id.rl_login_qq);
        iv_login_verification = (ImageView) findViewById(R.id.iv_login_verification);
        iv_login_account_clear = (ImageView) findViewById(R.id.iv_login_account_clear);
        iv_login_pwd_clear = (ImageView) findViewById(R.id.iv_login_pwd_clear);
        iv_login_verification_clear = (ImageView) findViewById(R.id.iv_login_verification_clear);
        iv_login_pwd_eye = (ImageView) findViewById(R.id.iv_login_pwd_eye);
        //添加对应的点击事件
        btn_login_login.setOnClickListener(this);
        btn_login_register.setOnClickListener(this);
        btn_login_findPwd.setOnClickListener(this);
        iv_login_account_clear.setOnClickListener(this);
        iv_login_pwd_clear.setOnClickListener(this);
        iv_login_verification_clear.setOnClickListener(this);
        iv_login_pwd_eye.setOnClickListener(this);
        rl_login_sina.setOnClickListener(this);
        rl_login_qq.setOnClickListener(this);
        iv_login_verification.setOnClickListener(this);
        et_login_pwd.setInputType(0x81);
    }

    /**
     * 点击事件的实现
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_login:

                if (isNull()) {
                    LoginServer();
                } else {
                    Toast.makeText(LoginActivity.this, str, Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_login_register:
                //跳转到注册界面 带返回值
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, CommonMethod.GET_ACCOUNT);
                //平移动画、
                overridePendingTransition(R.anim.translation_animation_enter_in, R.anim.translation_animation_enter_out);
                break;
            case R.id.btn_login_findPwd:
                String accountStr = et_login_account.getText().toString().trim();
                Intent findIntent = new Intent(LoginActivity.this, RetrievePasswordActivity.class);
                findIntent.putExtra("accountStr", accountStr);
                startActivity(findIntent);
                //平移动画
                overridePendingTransition(R.anim.translation_animation_enter_in, R.anim.translation_animation_enter_out);
                break;
            case R.id.iv_login_verification:
                CommonMethod.changeImage(okHttpClient, handler, CommonMethod.SUCCESS, this, URLCollection.LOGINMGURL);
//                Log.d("LoginActivity", "hit");
                break;

            case R.id.iv_login_account_clear:
                et_login_account.setText("");
                break;
            case R.id.iv_login_pwd_clear:
                et_login_pwd.setText("");
                break;
            case R.id.iv_login_verification_clear:
                et_login_verification.setText("");
                break;

            case R.id.iv_login_pwd_eye:
                CommonMethod.setPwdVisible(et_login_pwd, iv_login_pwd_eye);
                break;
            case R.id.rl_login_sina:
                break;
            case R.id.rl_login_qq:
                break;
            default:
                break;
        }
    }

    private void setSharedPreference() {
        StorageUserCommunication storage = new StorageUserCommunication(this);
        storage.setUserCommunication(accountStr, pwdStr);
    }

    /**
     * 判断输入框是否为空
     *
     * @return
     */
    private boolean isNull() {
        accountStr = et_login_account.getText().toString().trim();
        pwdStr = et_login_pwd.getText().toString().trim();
        verifyStr = et_login_verification.getText().toString().trim();

        if (TextUtils.isEmpty(accountStr)) {
            str = "账号不能为空";
            return false;
        }
        if (TextUtils.isEmpty(pwdStr)) {
            str = "密码不能为空";
            return false;
        }
        if (pwdStr.length() < 6) {
            str = "密码长度不能小于6";
            return false;
        }
        if (TextUtils.isEmpty(verifyStr)) {
            str = "验证码不能为空";
            return false;
        }

        return true;
    }

    /**
     * 监听输入框中的内容变化
     */
    private void monitorEdiTextContent() {

        CommonMethod.clearContentByHit(et_login_account, iv_login_account_clear);
        CommonMethod.clearContentByHit(et_login_pwd, iv_login_pwd_clear);
        CommonMethod.clearContentByHit(et_login_verification, iv_login_verification_clear);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Animation translation = AnimationUtils.loadAnimation(this, R.anim.translation_animation_share_icon);
        rl_login_sina.setAnimation(translation);
        rl_login_qq.setAnimation(translation);
    }

    @Override
    protected void onResume() {
        super.onResume();

        CommonMethod.changeImage(okHttpClient, handler, CommonMethod.SUCCESS, this, URLCollection.LOGINMGURL);
//        Animation translation = AnimationUtils.loadAnimation(this, R.anim.translation_animation_share_icon);
//        rl_login_sina.setAnimation(translation);
//        rl_login_qq.setAnimation(translation);
//
//        rl_login_sina.startLayoutAnimation();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //判断返回码和请求码是否正确
        if (requestCode == CommonMethod.GET_ACCOUNT && resultCode == CommonMethod.SEND_ACCOUNT) {
            String str = data.getStringExtra("data");
            et_login_account.setText(str);
        }
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
                    iv_login_verification.setImageBitmap(bitmap);

                    break;
                //当加载网络失败执行的逻辑代码
                case CommonMethod.FALL:
                    Toast.makeText(LoginActivity.this, "网络出现了问题", Toast.LENGTH_SHORT).show();
                    break;
                case CommonMethod.COOKIE:
                    if (msg.what == CommonMethod.COOKIE) {
                        Toast.makeText(LoginActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    }
                    break;

                //当登录成功是显示的
                case CommonMethod.LOGINSUCCESS:
                    if (msg.obj.equals("登录成功")) {
                        Toast.makeText(LoginActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                        //设置默认设置存储登录账号和密码
                        setSharedPreference();
                        //存储登录状态
                        UserStatusUtil.getInstanse().setLogin(true);

                        Log.d("LOGINSUCCESS --- Cookie", UserStatusUtil.getInstanse().getCookie());
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        overridePendingTransition(R.anim.translation_animation_back_in, R.anim.translation_animation_back_out);
                    } else {
                        Toast.makeText(LoginActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    }

                    break;
            }
        }
    };

    /**
     * 登录服务
     */
    private void LoginServer() {

        Log.i("info_Login", "知道了session：" + s);
        FormBody body = new FormBody.Builder()
                .add("user.username", accountStr)
                .add("user.password", pwdStr)
                .add("verify", verifyStr)
                .add("langCode", "zh-cn")
                .build();

        s = CommonMethod.string;

        Request request = new Request.Builder()
                .addHeader("cookie", s)
                .url(URLCollection.LOGINURL)
                .post(body)
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
                            String info = object.getString("info");
                            Message msg = new Message();
                            msg.what = CommonMethod.LOGINSUCCESS;
                            msg.obj = info;
                            handler.sendMessage(msg);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                Headers headers = response.headers();
                Log.i("info_respons.headers", headers + "");

                Log.d("info_headers", "header " + headers);
                List<String> cookies = headers.values("Set-Cookie");
                if (cookies.size() > 0) {
                    String session = cookies.get(0);
                    String string = session.substring(0, session.indexOf(";"));
//                    String[] sessionid =  string.split("=");
                    Log.i("login", "session is  :" + string);
                    UserStatusUtil.getInstanse().setCookie(string);

                }


            }
        });
    }


}
