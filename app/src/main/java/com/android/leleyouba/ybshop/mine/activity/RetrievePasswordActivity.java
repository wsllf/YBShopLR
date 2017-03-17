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

public class RetrievePasswordActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_retrieve_account, et_retrieve_verification;
    ImageView iv_retrieve_account_clear, iv_retrieve_verification_clear;
    ImageView iv_retrieve_verification;
    String accountStr, verifyStr;
    String str;
    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_password);
        initView();
        //调用监听输入框的方法
        monitorEdiTextContent();

        //设置导航栏
        setToolBar();
    }

    private void initView() {
        et_retrieve_account = (EditText) findViewById(R.id.et_retrieve_account);
        et_retrieve_verification = (EditText) findViewById(R.id.et_retrieve_verification);
        iv_retrieve_account_clear = (ImageView) findViewById(R.id.iv_retrieve_account_clear);
        iv_retrieve_verification_clear = (ImageView) findViewById(R.id.iv_retrieve_verification_clear);
        iv_retrieve_verification = (ImageView) findViewById(R.id.iv_retrieve_verification);

        Button btn_retrieve_next = (Button) findViewById(R.id.btn_retrieve_next);
        btn_retrieve_next.setOnClickListener(this);
        iv_retrieve_account_clear.setOnClickListener(this);
        iv_retrieve_verification_clear.setOnClickListener(this);
        iv_retrieve_verification.setOnClickListener(this);
        //加载验证码
        CommonMethod.changeImage(okHttpClient, handler, CommonMethod.SUCCESS, this, URLCollection.VERIFYURL);
        //传递账号
        Intent intent = getIntent();
        String str = intent.getStringExtra("accountStr");
        et_retrieve_account.setText(str);
    }

    /**
     * 设置导航栏
     */
    private void setToolBar() {
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolBar_verify);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //给左上角图标的左边加上一个返回的图标
            actionBar.setDisplayHomeAsUpEnabled(true);
            //给左上角的图标设置内容
            actionBar.setHomeAsUpIndicator(R.mipmap.back_arrows);
            //设置系统标题不显示
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * 导航栏ToolBar上的item的点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //判断是否是左侧标题
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
        CommonMethod.clearContentByHit(et_retrieve_account, iv_retrieve_account_clear);
        CommonMethod.clearContentByHit(et_retrieve_verification, iv_retrieve_verification_clear);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_retrieve_next:
                accountStr = et_retrieve_account.getText().toString().trim();
                verifyStr = et_retrieve_verification.getText().toString().trim();
                if (!isNull()) {
                    Toast.makeText(RetrievePasswordActivity.this, str, Toast.LENGTH_SHORT).show();
                    return;
                }
                commitFindPwdServer();

                break;
            case R.id.iv_retrieve_account_clear:
                et_retrieve_account.setText("");
                break;
            case R.id.iv_retrieve_verification_clear:
                et_retrieve_verification.setText("");
                break;
            case R.id.iv_retrieve_verification:
                CommonMethod.changeImage(okHttpClient, handler, CommonMethod.SUCCESS, this, URLCollection.VERIFYURL);
                break;
        }
    }

    /**
     * 判断输入框是否为空
     *
     * @return
     */
    private boolean isNull() {

        if (TextUtils.isEmpty(accountStr)) {
            str = "账号不能为空";
            return false;
        }
        if (TextUtils.isEmpty(verifyStr)) {
            str = "验证码不能为空";
            return false;
        }
        return true;
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case CommonMethod.SUCCESS:
                    byte[] byte_img = (byte[]) msg.obj;
                    Bitmap bitmap = BitmapFactory.decodeByteArray(byte_img, 0, byte_img.length);
                    iv_retrieve_verification.setImageBitmap(bitmap);
                    break;
                case CommonMethod.COMMITFINDPWD:
                    Log.d("COMMITFINDPWD", msg.obj.toString());
                    String personInformation = (String) msg.obj;
                    if (!TextUtils.isEmpty(personInformation)) {
                        Intent intent = new Intent(RetrievePasswordActivity.this, ValidationInformationActivity.class);
                        intent.putExtra("accountStr", accountStr);
                        intent.putExtra("personInformation", personInformation);
                        startActivity(intent);
                        overridePendingTransition(R.anim.translation_animation_enter_in, R.anim.translation_animation_enter_out);
                    } else {
                        Toast.makeText(RetrievePasswordActivity.this, "验证码不正确", Toast.LENGTH_SHORT).show();
                    }

                    break;
            }
            return false;
        }
    });

    /**
     * 提交
     */
    private void commitFindPwdServer() {

        Log.i("info_Login", "知道了session：" + UserStatusUtil.getInstanse().getCookie());
        FormBody body = new FormBody.Builder()
                .add("user.username", accountStr)
                .add("verify", verifyStr)
                .build();

        Request request = new Request.Builder()
                .addHeader("cookie", UserStatusUtil.getInstanse().getCookie())
                .url(URLCollection.GETVERIFYQUESTIONURL)
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
                    Message msg = new Message();
                    msg.what = CommonMethod.COMMITFINDPWD;
                    msg.obj = string;
                    handler.sendMessage(msg);

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
