package com.android.leleyouba.ybshop.mine.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.leleyouba.ybshop.BaseActivity;
import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.mine.util.UserInfoSave;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountManagementActivity extends BaseActivity implements View.OnClickListener {


    private static final String TAG = "AccountManagementActivi";
    Toolbar mToolbar;
    RelativeLayout rela_user_info,
            rela_plus_club,
            rela_plus,
            rela_credit,
            rela_location_manager,
            rela_inovice,
            rela_realname,
            rela_security_account;//账户
    CircleImageView user_img;//用户图像
    TextView tv_user_nickname,//用户昵称
            tv_user_name;//用户名
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);

        initView();



    }


    /**
     * 初始化控件
     */
    private void initView() {

        //Toolbar相关
        mToolbar = (Toolbar) findViewById(R.id.account_toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_arrows);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        //其他控件
        user_img = (CircleImageView) findViewById(R.id.user_img);
        tv_user_nickname = (TextView) findViewById(R.id.tv_user_nickname);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);

        rela_user_info = (RelativeLayout) findViewById(R.id.rela_user_info);
        rela_plus_club = (RelativeLayout) findViewById(R.id.rela_plus_club);
        rela_plus = (RelativeLayout) findViewById(R.id.rela_plus);
        rela_credit = (RelativeLayout) findViewById(R.id.rela_credit);
        rela_location_manager = (RelativeLayout) findViewById(R.id.rela_location_manager);
        rela_inovice = (RelativeLayout) findViewById(R.id.rela_inovice);
        rela_realname = (RelativeLayout) findViewById(R.id.rela_realname);
        rela_security_account = (RelativeLayout) findViewById(R.id.rela_security_account);
        //设置点击
        rela_user_info.setOnClickListener(this);
        rela_plus_club.setOnClickListener(this);
        rela_plus.setOnClickListener(this);
        rela_credit.setOnClickListener(this);
        rela_location_manager.setOnClickListener(this);
        rela_inovice.setOnClickListener(this);
        rela_realname.setOnClickListener(this);
        rela_security_account.setOnClickListener(this);

        setData();

    }

    private void setData() {

        Log.d(TAG, "setData: "+UserInfoSave.getInstace().getQuestion()+UserInfoSave.getInstace());
        Log.d(TAG, "setData: "+UserInfoSave.getInstace().getNickname());
        
        tv_user_nickname.setText(UserInfoSave.getInstace().getNickname());
        tv_user_name.setText(UserInfoSave.getInstace().getUsername());
        Glide.with(this).load(UserInfoSave.getInstace().getPhoto()).asBitmap().into(user_img);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                if (toast == null){
                    toast = Toast.makeText(this, "返回按钮", Toast.LENGTH_SHORT);
                }
                toast.show();
                finish();
                break;
        }
        return true;
    }



    @Override
    public void onClick(View v) {
        if (toast == null){
            toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        }
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.rela_user_info:
                toast.setText("用户信息");

                startActivity(new Intent(AccountManagementActivity.this,PersonInfoActivity.class));

                break;
            case R.id.rela_plus_club:
                toast.setText("会员俱乐部");
                break;
            case R.id.rela_plus:
                toast.setText("会员PLUS");
                break;
            case R.id.rela_credit:
                toast.setText("小白信用分");
                break;
            case R.id.rela_location_manager:
                toast.setText("地址管理");

                intent.setClass(this,AddresManagerActivity.class);
                startActivity(intent);

                break;
            case R.id.rela_inovice:
                toast.setText("增票资质");
                break;
            case R.id.rela_realname:
                toast.setText("实名认证");
                break;
            case R.id.rela_security_account:
                toast.setText("账户安全");
                intent.setClass(AccountManagementActivity.this,ChangePwdActivity.class);
                startActivity(intent);
                break;
        }
        toast.show();
    }
}
