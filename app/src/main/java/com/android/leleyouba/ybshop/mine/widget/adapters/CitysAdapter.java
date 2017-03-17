package com.android.leleyouba.ybshop.mine.widget.adapters;

import android.content.Context;


import com.android.leleyouba.ybshop.mine.widget.model.CityModel;

import java.util.List;

/**
 * Created by xuan on 16/1/7.
 */
public class CitysAdapter extends AbstractWheelTextAdapter {
    public List<CityModel> mList;
    private Context mContext;
    public CitysAdapter(Context context, List<CityModel> list) {
        super(context);
        mList=list;
        mContext=context;
    }

    @Override
    protected CharSequence getItemText(int index) {
        CityModel cityModel=mList.get(index);
        return cityModel.NAME;
    }

    @Override
    public int getItemsCount() {
        return mList.size();
    }
}
