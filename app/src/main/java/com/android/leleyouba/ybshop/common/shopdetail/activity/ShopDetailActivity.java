package com.android.leleyouba.ybshop.common.shopdetail.activity;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.common.shopdetail.adapter.DetailFragmentStateAdapter;
import com.android.leleyouba.ybshop.common.shopdetail.fragment.DetailFragment;
import com.android.leleyouba.ybshop.common.shopdetail.fragment.EvaluationFragment;
import com.android.leleyouba.ybshop.common.shopdetail.fragment.ShopFragment;

import java.util.ArrayList;
import java.util.List;

public class ShopDetailActivity extends AppCompatActivity {

    Toolbar detail_toolbar;

    TabLayout tablayout;
    ViewPager mViewPager;
    DetailFragmentStateAdapter adapter;
    List<Fragment> fragmentList;

    /**
     * 设置显示那一页
     *
     * @param index
     */
    public void setCurrentTab(int index) {
        if (index >= 0) {
            if (tablayout != null) {
                if ((tablayout.getTabCount() - 1) >= index) {
                    mViewPager.setCurrentItem(index);
                }
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);

        initView();
        initFragment();
        setUpWithViewAndadApter();
    }


    /**
     *
     */
    private void initView() {

        detail_toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(detail_toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_arrows);
            actionBar.setDisplayShowTitleEnabled(false);
        }

//        初始化view
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.vp_detail_one);
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
     * 集合添加Fragment
     */
    private void initFragment() {
        fragmentList = new ArrayList<Fragment>();
        ShopFragment shopFragment = new ShopFragment();
        DetailFragment detailFragment = new DetailFragment();
        EvaluationFragment evaluationFragment = new EvaluationFragment();
        fragmentList.add(shopFragment);
        fragmentList.add(detailFragment);
        fragmentList.add(evaluationFragment);
    }

    ;

    /**
     * 关联各个视图
     */
    private void setUpWithViewAndadApter() {
        adapter = new DetailFragmentStateAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(mViewPager);
        String[] titles = new String[]{"商品", "详情", "评价"};

        for (int i = 0; i < tablayout.getTabCount(); i++) {
            tablayout.getTabAt(i).setText(titles[i]);
        }
//        int height =  detail_toolbar.getLayoutParams().height;
//        ViewGroup.LayoutParams layoutParams = tablayout.getLayoutParams();
//        layoutParams.height = height ;
//        tablayout.setLayoutParams(layoutParams);
    }
}
