package com.android.leleyouba.ybshop;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by xalo on 2017/2/23.
 */

public class MainFragmentPageAdapter extends FragmentPagerAdapter {

    List<Fragment> mFragmentList ;


    public MainFragmentPageAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.mFragmentList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }


}
