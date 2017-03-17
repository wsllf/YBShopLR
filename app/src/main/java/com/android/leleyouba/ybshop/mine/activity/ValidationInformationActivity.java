package com.android.leleyouba.ybshop.mine.activity;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.URLCollection;
import com.android.leleyouba.ybshop.mine.util.CommonMethod;
import com.android.leleyouba.ybshop.mine.util.UserStatusUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ValidationInformationActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_verify_answer;
    TextView tv_verify_account, tv_verify_question;
    ImageView iv_verify_account_clear, iv_verify_answer_clear;
    Button btn_verify_next;
    OkHttpClient okHttpClient = new OkHttpClient();
    String accountStr, answerStr;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation_infomation);

        initView();
        //调用监听输入框的方法
        monitorEdiTextContent();

        //设置导航栏
        setToolBar();
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
     * 监听输入框的方法
     */
    private void monitorEdiTextContent() {
        CommonMethod.clearContentByHit(et_verify_answer, iv_verify_answer_clear);
    }

    private void initView() {
        tv_verify_account = (TextView) findViewById(R.id.tv_verify_account);
        et_verify_answer = (EditText) findViewById(R.id.et_verify_answer);
        tv_verify_question = (TextView) findViewById(R.id.tv_verify_question);
        iv_verify_answer_clear = (ImageView) findViewById(R.id.iv_verify_answer_clear);
        btn_verify_next = (Button) findViewById(R.id.btn_verify_next);

        btn_verify_next.setOnClickListener(this);
        iv_verify_answer_clear.setOnClickListener(this);

        Intent intent = getIntent();
        String accountStr = intent.getStringExtra("accountStr");
        String personInformation = intent.getStringExtra("personInformation");
        try {
            JSONObject object = new JSONObject(personInformation);
            if (object.has("question")) {
                JSONArray array = object.getJSONArray("question");
                JSONObject object1 = (JSONObject) array.get(0);
                if (object1.has("question")) {
                    String question = object1.getString("question");
                    tv_verify_question.setText(question);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        tv_verify_account.setText(accountStr);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_verify_next:
                if (isNull()) {
                    commitAnswer();
                } else {
                    Toast.makeText(ValidationInformationActivity.this, str, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_verify_answer_clear:
                et_verify_answer.setText("");
                break;
        }
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case CommonMethod.COMMITVERIFYANSWER:
                    if (msg.arg1 == 0) {
                        Toast.makeText(ValidationInformationActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ValidationInformationActivity.this, NewPWDActivity.class);
                        intent.putExtra("accountStr", accountStr);
                        startActivity(intent);
                        overridePendingTransition(R.anim.translation_animation_enter_in, R.anim.translation_animation_enter_out);
                    } else {
                        Toast.makeText(ValidationInformationActivity.this, "回答的答案不正确", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
            return false;
        }
    });

    private void commitAnswer() {
        Log.i("info_Login", "知道了session：" + UserStatusUtil.getInstanse().getCookie());
        FormBody body = new FormBody.Builder()
                .add("user.username", accountStr)
                .add("question", tv_verify_question.getText().toString().trim())
                .add("answer", answerStr)
                .build();

        Request request = new Request.Builder()
                .addHeader("cookie", UserStatusUtil.getInstanse().getCookie())
                .url(URLCollection.ANSWERVERIFYQUESTIONURL)
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
                            msg.what = CommonMethod.COMMITVERIFYANSWER;
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

    /**
     * 判断输入框是否为空
     *
     * @return
     */
    private boolean isNull() {
        accountStr = tv_verify_account.getText().toString().trim();
        answerStr = et_verify_answer.getText().toString().trim();

        if (TextUtils.isEmpty(accountStr)) {
            str = "账号不能为空";
            return false;
        }
        if (TextUtils.isEmpty(answerStr)) {
            str = "答案不能为空";
            return false;
        }
        return true;
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
}
