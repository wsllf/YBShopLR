package com.android.leleyouba.ybshop.common.shopdetail.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.leleyouba.ybshop.R;

/**
 * A simple {@link Fragment} subclass.
 *
 *
 * 商品介绍  和 内容详情  公用
 *
 */
public class WaresIntroduceFragment extends Fragment {


    public WaresIntroduceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wares_introduce, container, false);
    }

}
