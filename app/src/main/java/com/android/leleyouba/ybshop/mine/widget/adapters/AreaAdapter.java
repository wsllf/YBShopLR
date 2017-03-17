package com.android.leleyouba.ybshop.mine.widget.adapters;

import android.content.Context;


import com.android.leleyouba.ybshop.mine.widget.model.DistrictModel;

import java.util.List;

/**
 * Created by xuan on 16/1/7.
 */
public class AreaAdapter extends AbstractWheelTextAdapter {
    private List<DistrictModel> mList;
    private Context mContext;
    public AreaAdapter(Context context, List<DistrictModel> list) {
        super(context);
        mList=list;
        mContext=context;
    }

    @Override
    protected CharSequence getItemText(int index) {
        DistrictModel districtModel=mList.get(index);
        return districtModel.NAME;
    }

    @Override
    public int getItemsCount() {
        return mList.size();
    }
}
