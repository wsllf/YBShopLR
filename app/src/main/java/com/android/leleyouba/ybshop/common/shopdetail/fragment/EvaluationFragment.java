package com.android.leleyouba.ybshop.common.shopdetail.fragment;


import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.common.shopdetail.adapter.DetailFragmentStateAdapter;
import com.android.leleyouba.ybshop.shoppingtrolley.ShoppingtrolleyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * 评价界面
 */
public class EvaluationFragment extends Fragment {



    private String[] titles = new String[]{"全部评价","好评","中评","差评","晒图"};

    private int contents[] = {R.id.evaluation_all,R.id.evaluation_well,R.id.evaluation_middle,R.id.evaluation_bad,R.id.evaluation_img};

    private Fragment fragments[] = {new AllEvaFragment(),new WellEvaFragment(),new MiddleEvaFragment(),new BadEvaFragment(),new ImgEvaFragment()};



    public EvaluationFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_evaluation, container, false);


        initData();

        setTabHost(view);
        return view;
    }

    private void initData() {


    }

    void setTabHost(View view){

        final TabHost tabHost;
        tabHost = (TabHost) view.findViewById(R.id.tabhost);
        tabHost.setup();



        for (int i = 0; i <titles.length ; i++) {

            TabHost.TabSpec spec = tabHost.newTabSpec("tab"+i);
            spec.setContent(contents[i]);
            View view1 = LayoutInflater.from(getContext()).inflate(R.layout.evaluation_top_label,tabHost.getTabWidget(),false);
            TextView textView = (TextView) view1.findViewById(R.id.evalution_label);
            TextView textView1 = (TextView) view1.findViewById(R.id.evalution_count);
            textView.setText(titles[i]);
            //设置评论的条数，应该获取导数据后，动态设置
            textView1.setText(i+15+"");
            //设置选中颜色（tabhost的tab）
            Resources resource = (Resources) getContext().getResources();
            ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.evalutation_top_label_color);
            textView.setTextColor(csl);
            textView1.setTextColor(csl);
            spec.setIndicator(view1);
            tabHost.addTab(spec);
        }




        //获取fragmentManager对象
        FragmentManager manager = getChildFragmentManager();
//        获取transaction对象（通过管理者来操作业务员）
        FragmentTransaction transaction = manager.beginTransaction();
//        业务员进行操作
        for (int i = 0;i < titles.length;i++){
            Fragment fragment = fragments[i];
            if (fragment instanceof AllEvaFragment){

                Bundle bundle = new Bundle();
                bundle.putString(AllEvaFragment.ARGUMENT_KEY,"AllEvaFragment的URL");

                fragment.setArguments(bundle);

            }else if (fragment instanceof WellEvaFragment){
                Bundle bundle = new Bundle();
                bundle.putString(WellEvaFragment.ARGUMENT_KEY,"WellEvaFragment的URL");

                fragment.setArguments(bundle);


            }else if (fragment instanceof MiddleEvaFragment){
                Bundle bundle = new Bundle();
                bundle.putString(MiddleEvaFragment.ARGUMENT_KEY,"MiddleEvaFragment的URL");

                fragment.setArguments(bundle);


            }
            else if (fragment instanceof BadEvaFragment){
                Bundle bundle = new Bundle();
                bundle.putString(BadEvaFragment.ARGUMENT_KEY,"BadEvaFragment的URL");

                fragment.setArguments(bundle);


            }
            else if (fragment instanceof ImgEvaFragment){
                Bundle bundle = new Bundle();
                bundle.putString(ImgEvaFragment.ARGUMENT_KEY,"ImgEvaFragment的URL");

                fragment.setArguments(bundle);


            }
            transaction.replace(contents[i],fragment);
        }
//        提交业务
        transaction.commit();


    }

}
