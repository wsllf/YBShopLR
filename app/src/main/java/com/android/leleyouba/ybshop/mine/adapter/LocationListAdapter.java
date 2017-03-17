package com.android.leleyouba.ybshop.mine.adapter;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.leleyouba.ybshop.R;

import java.util.List;

/**
 * Created by xalo on 2017/3/11.
 */

public class LocationListAdapter extends BaseAdapter {


//    List<ProvinceModel> provinceModels;
//    List<CityModel> cityModels;
//    List<DistrictModel> districtModels;

//    public LocationListAdapter(List<ProvinceModel> provinceModels) {
//        this.provinceModels = provinceModels;
//    }



//    public LocationListAdapter(List<ProvinceModel> provinceModels, List<CityModel> cityModels, List<DistrictModel>
//            districtModels) {
//        this.provinceModels = provinceModels;
//        this.cityModels = cityModels;
//        this.districtModels = districtModels;
//    }

    @Override
    public int getCount() {
//        if (districtModels.size() > 0){
//            return districtModels.size();
//        }else if (cityModels.size() > 0){
//            return cityModels.size();
//        }else {
//            return provinceModels.size();
//        }
        return 0;
    }


    @Override
    public Object getItem(int position) {
//        if (districtModels.size() > 0){
//            return districtModels.get(position);
//        }else if (cityModels.size() > 0){
//            return cityModels.get(position);
//        }else {
//            return provinceModels.get(position);
//        }

        return  null;

    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_listview,null);
            viewHolder = new ViewHolder();
            viewHolder.mRadioButton = (TextView) convertView.findViewById(R.id.tv_content_location);
            Resources resource = (Resources) parent.getContext().getResources();
            ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.evalutation_top_label_color);
            viewHolder.mRadioButton.setTextColor(csl);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        if (districtModels.size() > 0){
//            viewHolder.mRadioButton.setText(((DistrictModel)(districtModels.get(position))).getNAME());
//        }else if (cityModels.size() > 0){
//            viewHolder.mRadioButton.setText(((CityModel)(cityModels.get(position))).getNAME());
//        }else {
//            viewHolder.mRadioButton.setText(((ProvinceModel)(provinceModels.get(position))).getNAME());
//        }

        return convertView;
    }


    class ViewHolder{

        TextView mRadioButton;
        public ViewHolder() {

        }
    }


}
