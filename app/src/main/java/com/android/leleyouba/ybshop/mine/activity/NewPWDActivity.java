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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewPWDActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_newPwd_account, et_newPwd_pwd, et_newPwd_confirm;//输入框
    ImageView iv_newPwd_account_clear, iv_newPwd_pwd_clear, iv_newPwd_confirm_clear;//清除按钮
    ImageView iv_newPwd_pwd_eye, iv_newPwd_confirm_eye;//密码可见度
    Button btn_newPwd_complete;//完成按钮
    OkHttpClient okHttpClient = new OkHttpClient();
    String accountStr, pwdStr, confirmStr;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pwd);
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
        Toolbar toolBar_newPwd = (Toolbar) findViewById(R.id.toolBar_newPwd);
        setSupportActionBar(toolBar_newPwd);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_arrows);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * 监听输入框中的内容变化
     */
    private void monitorEdiTextContent() {
        CommonMethod.clearContentByHit(et_newPwd_account, iv_newPwd_account_clear);
        CommonMethod.clearContentByHit(et_newPwd_pwd, iv_newPwd_pwd_clear);
        CommonMethod.clearContentByHit(et_newPwd_confirm, iv_newPwd_confirm_clear);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        et_newPwd_account = (EditText) findViewById(R.id.et_newPwd_account);
        et_newPwd_pwd = (EditText) findViewById(R.id.et_newPwd_pwd);
        et_newPwd_confirm = (EditText) findViewById(R.id.et_newPwd_confirm);
        iv_newPwd_account_clear = (ImageView) findViewById(R.id.iv_newPwd_account_clear);
        iv_newPwd_pwd_clear = (ImageView) findViewById(R.id.iv_newPwd_pwd_clear);
        iv_newPwd_confirm_clear = (ImageView) findViewById(R.id.iv_newPwd_confirm_clear);
        iv_newPwd_pwd_eye = (ImageView) findViewById(R.id.iv_newPwd_pwd_eye);
        iv_newPwd_confirm_eye = (ImageView) findViewById(R.id.iv_newPwd_confirm_eye);
        btn_newPwd_complete = (Button) findViewById(R.id.btn_newPwd_complete);

        iv_newPwd_account_clear.setOnClickListener(this);
        iv_newPwd_pwd_clear.setOnClickListener(this);
        iv_newPwd_confirm_clear.setOnClickListener(this);
        iv_newPwd_pwd_eye.setOnClickListener(this);
        iv_newPwd_confirm_eye.setOnClickListener(this);
        btn_newPwd_complete.setOnClickListener(this);

        Intent intent = getIntent();
        String str = intent.getStringExtra("accountStr");
        et_newPwd_account.setText(str);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_newPwd_complete:
                if (isNull()) {
                    commitNewPwdServer();
                } else {
                    Toast.makeText(NewPWDActivity.this, str, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_newPwd_account_clear:
                et_newPwd_account.setText("");
                break;
            case R.id.iv_newPwd_pwd_clear:
                et_newPwd_pwd.setText("");
                break;
            case R.id.iv_newPwd_confirm_clear:
                et_newPwd_confirm.setText("");
                break;
            case R.id.iv_newPwd_pwd_eye:
                CommonMethod.setPwdVisible(et_newPwd_pwd, iv_newPwd_pwd_eye);
                break;
            case R.id.iv_newPwd_confirm_eye:
                CommonMethod.setPwdVisible(et_newPwd_confirm, iv_newPwd_confirm_eye);
                break;
        }

    }

    /**
     * 判断输入框是否为空
     *
     * @return
     */
    private boolean isNull() {
        accountStr = et_newPwd_account.getText().toString().trim();
        pwdStr = et_newPwd_pwd.getText().toString().trim();
        confirmStr = et_newPwd_confirm.getText().toString().trim();

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
        if (TextUtils.isEmpty(confirmStr)) {
            str = "确认密码不能为空";
            return false;
        }
        if (!TextUtils.equals(pwdStr, confirmStr)) {
            str = "两次输入密码不一致";
            return false;
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.translation_animation_back_in, R.anim.translation_animation_back_out);
        }

        return super.onOptionsItemSelected(item);
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case CommonMethod.COMMITNEWPWD:
                    if (msg.arg1 == 0) {
                        startActivity(new Intent(NewPWDActivity.this, LoginActivity.class));
                        overridePendingTransition(R.anim.translation_animation_enter_in, R.anim.translation_animation_enter_out);
                        Toast.makeText(NewPWDActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(NewPWDActivity.this, "请重新输入新密码", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
            return false;
        }
    });

    /**
     * 提交
     */
    private void commitNewPwdServer() {

        Log.i("info_Login", "知道了session：" + UserStatusUtil.getInstanse().getCookie());
        FormBody body = new FormBody.Builder()
                .add("user.username", accountStr)
                .add("user.password", pwdStr)
                .add("confirmPassword", confirmStr)
                .build();

        Request request = new Request.Builder()
                .addHeader("cookie", UserStatusUtil.getInstanse().getCookie())
                .url(URLCollection.COMMITNEWPWDURL)
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

                    String string = response.body().string();
                    Log.i("info_call2success", string);
                    try {
                        JSONObject object = new JSONObject(string);
                        if (object.has("status")) {
                            int status = object.getInt("status");
                            Message msg = new Message();
                            msg.what = CommonMethod.COMMITNEWPWD;
                            msg.arg1 = status;
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
