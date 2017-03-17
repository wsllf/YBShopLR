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
 *   规格参数   和  出版信息  公用
 *
 */
public class StandardArgumentsFragment extends Fragment {


    public StandardArgumentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standard_arguments, container, false);
    }

}
