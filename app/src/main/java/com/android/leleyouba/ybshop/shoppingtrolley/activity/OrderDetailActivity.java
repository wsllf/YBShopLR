package com.android.leleyouba.ybshop.shoppingtrolley.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.shoppingtrolley.adapter.OrderDetailItemAdapter;

public class OrderDetailActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        //设置导航栏
        setToolbar();
        initView();
        //设置订单
        setRecycleView();
    }


    private void setToolbar() {
        Toolbar toolbar_payment_show = (Toolbar) findViewById(R.id.toolbar_order_detail);
        setSupportActionBar(toolbar_payment_show);
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
        TextView tv_order_detail_numberCon = (TextView) findViewById(R.id.tv_order_detail_numberCon);
        TextView tv_order_detail_isPay = (TextView) findViewById(R.id.tv_order_detail_isPay);
        TextView tv_order_detail_account = (TextView) findViewById(R.id.tv_order_detail_account);
        TextView tv_order_detail_phoneNumber = (TextView) findViewById(R.id.tv_order_detail_phoneNumber);
        TextView tv_order_detail_address = (TextView) findViewById(R.id.tv_order_detail_address);
        TextView tv_order_detail_paymentMethod = (TextView) findViewById(R.id.tv_order_detail_paymentMethod);
        TextView tv_order_detail_distribution = (TextView) findViewById(R.id.tv_order_detail_distribution);
        TextView tv_order_detail_totalPrice = (TextView) findViewById(R.id.tv_order_detail_totalPrice);
        TextView tv_order_detail_freight = (TextView) findViewById(R.id.tv_order_detail_freight);
        TextView tv_order_detail_distributionPrice = (TextView) findViewById(R.id.tv_order_detail_distributionPrice);
        TextView tv_order_detail_paymentRequired = (TextView) findViewById(R.id.tv_order_detail_paymentRequired);
        TextView tv_order_detail_placeTime = (TextView) findViewById(R.id.tv_order_detail_placeTime);
        TextView tv_order_detail_surplusTime = (TextView) findViewById(R.id.tv_order_detail_surplusTime);
        Button btn_order_detail_cancel = (Button) findViewById(R.id.btn_order_detail_cancel);
        Button btn_order_detail_pay = (Button) findViewById(R.id.btn_order_detail_pay);
        btn_order_detail_cancel.setOnClickListener(this);
        btn_order_detail_pay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_order_detail_cancel:
                Toast.makeText(OrderDetailActivity.this, "取消订单", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_order_detail_pay:
                Toast.makeText(OrderDetailActivity.this, "去支付", Toast.LENGTH_SHORT).show();
                finish();
                overridePendingTransition(R.anim.translation_animation_back_in, R.anim.translation_animation_back_out);
                break;
        }
    }

    private void setRecycleView() {
        RecyclerView recycle_order_detail = (RecyclerView) findViewById(R.id.recycle_order_detail);
        recycle_order_detail.setLayoutManager(new LinearLayoutManager(this));
        OrderDetailItemAdapter adapter = new OrderDetailItemAdapter(this);
        recycle_order_detail.setAdapter(adapter);
    }
}
