package com.android.leleyouba.ybshop;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;

import com.android.leleyouba.ybshop.classify.ClassifyFragment;
import com.android.leleyouba.ybshop.find.FindFragment;
import com.android.leleyouba.ybshop.homepage.HomepageFragment;
import com.android.leleyouba.ybshop.mine.MineFragment;
import com.android.leleyouba.ybshop.shoppingtrolley.ShoppingtrolleyFragment;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity {

    private int item[] = {R.drawable.main_tab_menu_homepage, R.drawable.main_tab_menu_classify, R.drawable.main_tab_menu_find, R.drawable.main_tab_menu_shoppingtrolley, R.drawable.main_tab_menu_mine};
    private int content[] = {R.id.ll_main_homepage, R.id.ll_main_classify, R.id.ll_main_find, R.id.ll_main_shoppingtrolley, R.id.ll_main_mine};
    private Fragment fragment[] = {new HomepageFragment(), new ClassifyFragment(), new FindFragment(), new ShoppingtrolleyFragment(), new MineFragment()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //极光推送
        JPushInterface.init(getApplicationContext());
        //初始化布局
        initView();
    }

    private void initView() {
        TabHost tabhost = (TabHost) findViewById(android.R.id.tabhost);
        //启用setUp方法
        tabhost.setup();

        for (int i = 0; i < item.length; i++) {
            //实例化TabSpec对象（内部类对象）
            TabHost.TabSpec cell = tabhost.newTabSpec(String.valueOf(i));
            //设置内容
            View view = getLayoutInflater().inflate(R.layout.main_tab_menu_item, null);
            view.setBackgroundResource(item[i]);
            cell.setIndicator(view);
            //按钮被选择后tabContent中显示哪一个布局
            cell.setContent(content[i]);
            //按钮被选择
            tabhost.addTab(cell);
        }

        //获取fragmentManager对象
        FragmentManager manager = getSupportFragmentManager();
        //获取transaction对象（通过管理者来操作业务员）
        FragmentTransaction transaction = manager.beginTransaction();
        //业务员进行操作
        for (int i = 0; i < fragment.length; i++) {
            transaction.replace(content[i], fragment[i]);
        }
        //提交业务
        transaction.commit();


    }
}
