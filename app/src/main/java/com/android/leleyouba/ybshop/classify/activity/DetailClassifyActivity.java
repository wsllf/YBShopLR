package com.android.leleyouba.ybshop.classify.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.classify.adapter.DetailClassifyItemRecycleViewAdapter;
import com.android.leleyouba.ybshop.classify.bean.BaseShopBean;

import java.util.ArrayList;

public class DetailClassifyActivity extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout activity_detail_classify;
    RecyclerView recycle_detail_classify;
    Button btn_detail_classify_change;
    DetailClassifyItemRecycleViewAdapter adapter;
    boolean isShowH = true;
    int TYPE_ITEM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_classify);
        initView();
        //加载recycleView
        setRecycleView(isShowH, TYPE_ITEM);

    }

    /**
     * 初始化控件
     */
    private void initView() {
        activity_detail_classify = (DrawerLayout) findViewById(R.id.activity_detail_classify);
        recycle_detail_classify = (RecyclerView) findViewById(R.id.recycle_detail_classify);
        btn_detail_classify_change = (Button) findViewById(R.id.btn_detail_classify_change);
        ImageView iv_detail_classify_back = (ImageView) findViewById(R.id.iv_detail_classify_back);
        TextView tv_detail_classify_comprehensive = (TextView) findViewById(R.id.tv_detail_classify_comprehensive);
        TextView tv_detail_classify_SalesVolume = (TextView) findViewById(R.id.tv_detail_classify_SalesVolume);
        TextView tv_detail_classify_price = (TextView) findViewById(R.id.tv_detail_classify_price);
        TextView tv_detail_classify_screen = (TextView) findViewById(R.id.tv_detail_classify_screen);
        TextView tv_detail_classify_complete = (TextView) findViewById(R.id.tv_detail_classify_complete);
        btn_detail_classify_change.setOnClickListener(this);
        iv_detail_classify_back.setOnClickListener(this);
        tv_detail_classify_comprehensive.setOnClickListener(this);
        tv_detail_classify_SalesVolume.setOnClickListener(this);
        tv_detail_classify_price.setOnClickListener(this);
        tv_detail_classify_screen.setOnClickListener(this);
        tv_detail_classify_complete.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_detail_classify_change:
                if (!isShowH) {
                    TYPE_ITEM = 1;
                    isShowH = true;
                    btn_detail_classify_change.setText("单列");

                } else {
                    TYPE_ITEM = 2;
                    isShowH = false;
                    btn_detail_classify_change.setText("双列");
                }
                setRecycleView(isShowH, TYPE_ITEM);
                adapter.notifyDataSetChanged();
                break;
            case R.id.iv_detail_classify_back:
                finish();
                overridePendingTransition(R.anim.translation_animation_back_in, R.anim.translation_animation_back_out);
                break;
            case R.id.tv_detail_classify_comprehensive:

                break;
            case R.id.tv_detail_classify_SalesVolume:

                break;
            case R.id.tv_detail_classify_price:

                break;
            case R.id.tv_detail_classify_screen:
                activity_detail_classify.openDrawer(Gravity.RIGHT);
                break;
            case R.id.tv_detail_classify_complete:
                activity_detail_classify.closeDrawer(Gravity.RIGHT);
                break;
        }
    }

    /**
     * 设置recycleView
     *
     * @param isShowH
     * @param TYPE_ITEM
     */
    private void setRecycleView(boolean isShowH, int TYPE_ITEM) {


        GridLayoutManager manager = new GridLayoutManager(this, TYPE_ITEM);

        recycle_detail_classify.setLayoutManager(manager);
        ArrayList<String> pics = new ArrayList<>();
        pics.add("http://p8.qhmsg.com/t01fbaf2e16676e4bf9.jpg");
        pics.add("http://ww1.sinaimg.cn/large/bd698b0fjw1dzq75vk5eyj.jpg");
        pics.add("http://ww1.sinaimg.cn/large/bd698b0fjw1dzq75vk5eyj.jpg");
        pics.add("http://ww1.sinaimg.cn/large/bd698b0fjw1dzq75vk5eyj.jpg");
        ArrayList<BaseShopBean> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            BaseShopBean.Builder builder = new BaseShopBean.Builder();
            BaseShopBean bean = builder
                    .goodsName("七匹狼长袖纯棉休闲男装")
                    .pictures(pics)
                    .inPrice(99)
                    .evaluate("14852条 评价 98%好评")
                    .build();
            datas.add(bean);
        }
        adapter = new DetailClassifyItemRecycleViewAdapter(datas, this, isShowH);
        recycle_detail_classify.setAdapter(adapter);

    }
}
