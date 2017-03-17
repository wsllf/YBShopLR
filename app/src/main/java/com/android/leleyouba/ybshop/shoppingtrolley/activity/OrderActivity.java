package com.android.leleyouba.ybshop.shoppingtrolley.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.mine.activity.AddresManagerActivity;
import com.android.leleyouba.ybshop.mine.util.CommonMethod;
import com.android.leleyouba.ybshop.shoppingtrolley.adapter.OrderShopItemRecycleAdapter;
import com.android.leleyouba.ybshop.shoppingtrolley.bean.OrderShopItemBean;
import com.android.leleyouba.ybshop.shoppingtrolley.customview.CustomDialog;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recycle_order_show;
    TextView tv_order_total_price;
    Button btn_order_commit;
    OrderShopItemRecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        //设置toolBar
        setToolbar();
        initView();
        //设置个人信息的展示
        setPersonInformationShow();
        //设置recycleView
        setRecycleView();

    }

    /**
     * 设置toolBar
     */
    private void setToolbar() {
        Toolbar toolbar_order_show = (Toolbar) findViewById(R.id.toolbar_order_show);
        setSupportActionBar(toolbar_order_show);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_arrows);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * 设置返回按钮
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            showAlertDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlertDialog() {
        final CustomDialog dialog = new CustomDialog(OrderActivity.this);

        dialog.setMessage("便宜不等人，请三思而行");
        dialog.setYesOnclickListener("去意已决", new CustomDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                dialog.dismiss();
                finish();
                overridePendingTransition(R.anim.translation_animation_back_in, R.anim.translation_animation_back_out);
            }
        });
        dialog.setNoOnclickListener("我再想想", new CustomDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void initView() {
        recycle_order_show = (RecyclerView) findViewById(R.id.recycle_order_show);
        tv_order_total_price = (TextView) findViewById(R.id.tv_order_total_price);
        btn_order_commit = (Button) findViewById(R.id.btn_order_commit);
        btn_order_commit.setOnClickListener(this);

    }

    /**
     * 设置个人信息的展示
     */
    private void setPersonInformationShow() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_order_commit:
                Toast.makeText(OrderActivity.this, "提交了", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(OrderActivity.this, PaymentActivity.class));
                overridePendingTransition(R.anim.translation_animation_enter_in, R.anim.translation_animation_enter_out);
                break;
        }
    }

    /**
     * 设置订单商品的展示
     */
    private void setRecycleView() {
        recycle_order_show.setLayoutManager(new LinearLayoutManager(this));
        recycle_order_show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        //准备数据
        ArrayList<OrderShopItemBean> shopItemBeans = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            OrderShopItemBean bean = new OrderShopItemBean();
            bean.setLogoStr("http://pic.qiantucdn.com/58pic/17/44/99/17c58PICQPd_1024.jpg");
            bean.setShopName("V2先生");
            bean.setShopTitle("夏季七分袖T恤韩版男士宽松大码半袖衣服日系男装短袖打底衫体恤");
            bean.setShopContent("颜色：黑色  尺码：3XL[170-185斤]");
            bean.setShopImg("http://imgsrc.baidu.com/forum/pic/item/70f2f736afc379315af2130ce9c4b74543a9117a.jpg");
            bean.setShopPrice(78.00f);
            bean.setShopNumber(1 + i);
            bean.setShopDistribution("快递 免邮");
            bean.setShopPayment("在线支付");
            bean.setShopTotalNumber(1 + i);
            bean.setShopTotalPrice(78.00f * (1 + i));
            bean.setShopMsgToSeller("选填：对本次交易的说明和建议(建议填写)" + i);
            shopItemBeans.add(bean);
        }
        adapter = new OrderShopItemRecycleAdapter(shopItemBeans, this);

        recycle_order_show.setAdapter(adapter);

        adapter.setJumpToAddressManager(new OrderShopItemRecycleAdapter.JumpToAddressManager() {
            @Override
            public void jumpToAddressClickListener() {
                Intent intent = new Intent(OrderActivity.this, AddresManagerActivity.class);
                startActivityForResult(intent, CommonMethod.ADDRESS_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //判断返回码和请求码是否正确
        if (requestCode == CommonMethod.ADDRESS_REQUEST && resultCode == CommonMethod.ADDRESS_RESULT) {
            String str = data.getStringExtra("Address");
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
            adapter.setAddressStr(str);
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 监听Back键按下事件,方法:
     * 注意:
     * super.onBackPressed()会自动调用finish()方法,关闭
     * 当前Activity.
     * 若要屏蔽Back键盘,注释该行代码即可
     */
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        showAlertDialog();
    }
}
