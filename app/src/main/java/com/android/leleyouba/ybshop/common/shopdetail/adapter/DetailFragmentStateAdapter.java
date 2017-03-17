package com.android.leleyouba.ybshop.common.shopdetail.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.leleyouba.ybshop.common.shopdetail.fragment.DetailFragment;

import java.util.Collections;
import java.util.List;

/**
 * Created by xalo on 2017/3/3.
 */

public class DetailFragmentStateAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragmentList;

    public DetailFragmentStateAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.fragmentList = list;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
    Fragment fragment = fragmentList.get(position);

        if (fragment instanceof DetailFragment) {
            Bundle bundle = new Bundle();
            bundle.putInt(DetailFragment.DETAIL_TAB_COUNT, 3);
            fragment.setArguments(bundle);
        }
        return fragmentList.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return fragmentList.size();
    }


}
