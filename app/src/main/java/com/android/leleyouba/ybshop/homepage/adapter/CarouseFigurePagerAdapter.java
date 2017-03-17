package com.android.leleyouba.ybshop.homepage.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.ArrayList;

/**
 * Created by xalo on 2017/3/2.
 */

public class CarouseFigurePagerAdapter extends PagerAdapter {

    Context context;
    ArrayList<View> list;

    public CarouseFigurePagerAdapter(Context context, ArrayList<View> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        if (list.size() <= 1) {
            return list.size();
        }
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        int newPosition = position % list.size();
        View view = list.get(newPosition);

        ViewParent viewParent = view.getParent();
        if (viewParent != null) {
            ViewGroup parent = (ViewGroup) viewParent;
            parent.removeView(view);
        }
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
