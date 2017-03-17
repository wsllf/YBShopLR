package com.android.leleyouba.ybshop.common.shopdetail.fragment;


import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.android.leleyouba.ybshop.R;
import com.bumptech.glide.provider.ChildLoadProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private final static  int TWOTAB = 2;
    private final static  int THREETAB = 3;

    public static final String DETAIL_TAB_COUNT  = "detail_tab_count";

    private int FragmentCount = THREETAB;
    private List<String> list = new ArrayList<>();

    private Fragment fragments[] = {new WaresIntroduceFragment(),new StandardArgumentsFragment(),new PackAfterSaleFragment()};
    private int contents[] = {R.id.detail_shop_introduce,R.id.detail_shop_standard_arguments,R.id.detail_shop_pack_after_sale};


    public DetailFragment() {
        // Required empty public constructor

    }


    /**
     * 数组添加元素，决定顶部显示tab的个数
     */

    private void setTabCount(){

        if (FragmentCount == TWOTAB){
            if (list != null){
                list.clear();
            }
            assert list != null;
            list.add("内容详情");
            list.add("出版信息");
        }else if (FragmentCount == THREETAB){
            list.add("商品简介");
            list.add("规格参数");
            list.add("包装售后");
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);


        //接收activity传递的数据，判断应该显示几个子tab（2或3）
        Bundle bundle =  getArguments();
        if (bundle != null){
            FragmentCount = bundle.getInt(DETAIL_TAB_COUNT);
            setTabCount();
        }
        //设置tabhost
        setTabHost(view);
        return view;
    }


    /**
     * tabHost 相关
     */

    void setTabHost(View view){

        TabHost tabHost;
        tabHost = (TabHost) view.findViewById(R.id.tabhost);
        tabHost.setup();



        for (int i = 0; i <list.size() ; i++) {


            TabHost.TabSpec spec = tabHost.newTabSpec("tab"+i);

            spec.setContent(contents[i]);
            spec.setIndicator(list.get(i));

            View view1 = LayoutInflater.from(getContext()).inflate(R.layout.deatail_top_label,tabHost.getTabWidget(),false);
            TextView textView = (TextView) view1.findViewById(R.id.detail_label);
            textView.setText(list.get(i));
            Resources resource=(Resources)getContext().getResources();
            ColorStateList csl=(ColorStateList)resource.getColorStateList(R.color.evalutation_top_label_color);
            textView.setTextColor(csl);
            spec.setIndicator(view1);
            tabHost.addTab(spec);
        }

        //获取fragmentManager对象
        FragmentManager manager = getChildFragmentManager();
//        获取transaction对象（通过管理者来操作业务员）
        FragmentTransaction transaction = manager.beginTransaction();
//        业务员进行操作
        for (int i = 0;i < list.size();i++){
            Fragment fragment = fragments[i];
            if (fragment instanceof WaresIntroduceFragment){



            }else if (fragment instanceof StandardArgumentsFragment){



            }else if (fragment instanceof PackAfterSaleFragment){



            }
            transaction.replace(contents[i],fragment);
        }
//        提交业务
        transaction.commit();


    }


}
