package com.android.leleyouba.ybshop.mine.activity;

import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.URLCollection;
import com.android.leleyouba.ybshop.mine.util.UserStatusUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    Switch switch_loadImg, switch_update;
    RelativeLayout rl_setting_clear, rl_setting_recommend, rl_setting_about;
    Button btn_setting_exitLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        setToolBar();
        initView();
    }

    /**
     * 设置导航栏
     */
    private void setToolBar() {
        Toolbar toolBar_login = (Toolbar) findViewById(R.id.toolBar_setting);
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

    private void initView() {
        switch_loadImg = (Switch) findViewById(R.id.switch_loadImg);
        switch_update = (Switch) findViewById(R.id.switch_update);
        rl_setting_clear = (RelativeLayout) findViewById(R.id.rl_setting_clear);
        rl_setting_recommend = (RelativeLayout) findViewById(R.id.rl_setting_recommend);
        rl_setting_about = (RelativeLayout) findViewById(R.id.rl_setting_about);
        btn_setting_exitLogin = (Button) findViewById(R.id.btn_setting_exitLogin);

        switch_loadImg.setOnClickListener(this);
        switch_update.setOnClickListener(this);
        rl_setting_clear.setOnClickListener(this);
        rl_setting_recommend.setOnClickListener(this);
        rl_setting_about.setOnClickListener(this);
        btn_setting_exitLogin.setOnClickListener(this);

        if (!UserStatusUtil.getInstanse().isLogin()){
            btn_setting_exitLogin.setVisibility(View.GONE);
        }else {
            btn_setting_exitLogin.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch_loadImg:
                break;
            case R.id.switch_update:
                break;
            case R.id.rl_setting_clear:
                //添加弹出框
                addDialog();
                break;
            case R.id.rl_setting_recommend:
                break;
            case R.id.rl_setting_about:
                break;
            case R.id.btn_setting_exitLogin:
                //退出登录的方法
                setExitLogin();
                finish();
                overridePendingTransition(R.anim.translation_animation_back_in, R.anim.translation_animation_back_out);
                break;
            default:
                break;
        }
    }

    /**
     * 退出登录的方法
     *
     * @return
     */
    private void setExitLogin() {

        UserStatusUtil.getInstanse().setLogin(false);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URLCollection.EXITLOGIN)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SettingActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
                        Log.d("setExitLogin --- Cookie", UserStatusUtil.getInstanse().getCookie());

                        UserStatusUtil.getInstanse().setCookie("");
                    }
                });
            }
        });
    }

    private void addDialog() {
        //得到构造器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置内容
        builder.setMessage("确定清除本地缓存？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();//关闭对话框
                Toast.makeText(SettingActivity.this, "已清除", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


}
