package com.android.leleyouba.ybshop.mine.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.mine.fragment.MsgFragment;
import com.android.leleyouba.ybshop.mine.fragment.SubscribeFragment;

import java.util.ArrayList;

public class CommunicateActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_communate_backToMine, iv_communate_setting, iv_communate_calendar;
    RadioGroup rg_communate_bar;
    RadioButton rb_communate_msg, rb_communate_subscribe;

    private int mIndex;
    ArrayList<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicate);

        initView();
        initFragment();
    }

    private void initFragment() {
        MsgFragment msgFragment = new MsgFragment();
        SubscribeFragment subscribeFragment = new SubscribeFragment();
        //添加到数组中
        fragments = new ArrayList<>();
        fragments.add(msgFragment);
        fragments.add(subscribeFragment);
        //开启事务
        FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
        //添加消息界面
        fTransaction.add(R.id.fl_communate_content,msgFragment).commit();
        //设置默认为第0个
        setIndexSelected(0);
    }

    private void setIndexSelected(int index) {
        if (mIndex == index){
            return;
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fTransaction = manager.beginTransaction();
        //隐藏
        fTransaction.hide(fragments.get(mIndex));
        //判断是否添加
        if (!fragments.get(index).isAdded()){
            fTransaction.add(R.id.fl_communate_content,fragments.get(index)).show(fragments.get(index));
        }else {
            fTransaction.show(fragments.get(index));
        }
        fTransaction.commit();
        //再次赋值
        mIndex = index;

    }

    private void initView() {
        iv_communate_backToMine = (ImageView) findViewById(R.id.iv_communate_backToMine);
        iv_communate_setting = (ImageView) findViewById(R.id.iv_communate_setting);
        iv_communate_calendar = (ImageView) findViewById(R.id.iv_communate_calendar);
        rg_communate_bar = (RadioGroup) findViewById(R.id.rg_communate_bar);
        rb_communate_msg = (RadioButton) findViewById(R.id.rb_communate_msg);
        rb_communate_subscribe = (RadioButton) findViewById(R.id.rb_communate_subscribe);

        iv_communate_backToMine.setOnClickListener(this);
        iv_communate_setting.setOnClickListener(this);
        iv_communate_calendar.setOnClickListener(this);
        rb_communate_msg.setOnClickListener(this);
        rb_communate_subscribe.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_communate_backToMine:
                finish();
                overridePendingTransition(R.anim.translation_animation_back_in,R.anim.translation_animation_back_out);
                break;
            case R.id.iv_communate_setting:
                break;
            case R.id.iv_communate_calendar:
                break;
            case R.id.rb_communate_msg:
                setIndexSelected(0);
                break;
            case R.id.rb_communate_subscribe:
                setIndexSelected(1);
                break;
            default:
                break;
        }
    }
}
