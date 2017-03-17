package com.android.leleyouba.ybshop.shoppingtrolley.adapter;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.mine.util.UserStatusUtil;
import com.android.leleyouba.ybshop.shoppingtrolley.bean.ShopCarModel;
import com.android.leleyouba.ybshop.shoppingtrolley.bean.ShopCarSection;
import com.android.leleyouba.ybshop.shoppingtrolley.customview.ShopCountView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.model.GlideUrl;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by xalo on 2017/3/2.
 */

public class SectionShopCarAdapter extends BaseSectionQuickAdapter<ShopCarSection,BaseViewHolder> {

   public static boolean allSelected = false;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SectionShopCarAdapter(int layoutResId, int sectionHeadResId, List<ShopCarSection> data) {
        super(layoutResId, sectionHeadResId, data);

    }

    @Override
    protected void convertHead(BaseViewHolder helper, ShopCarSection item) {

        helper.setText(R.id.tv_header_store_name,item.header);
        helper.setVisible(R.id.tv_isPrivilege,item.isprivilege());
        helper.setText(R.id.tv_isPrivilege,"去凑单");
        helper.addOnClickListener(R.id.tv_isPrivilege);
        helper.addOnClickListener(R.id.tv_header_store_name);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShopCarSection item) {
        final ShopCarModel shopCarModel = (ShopCarModel) item.t;



        helper.setText(R.id.tv_shop_trolley_wares_info,shopCarModel.getGoodsName());
       final ImageView imageView = helper.getView(R.id.iv_shop_trolley_wares);
        Glide.with(mContext).load(shopCarModel.getFileFileName()).into(imageView);
        helper.setText(R.id.tv_shop_trolley_wares_price,"¥"+shopCarModel.getPrice());
        helper.addOnClickListener(R.id.iv_shop_trolley_wares);
        helper.addOnClickListener(R.id.tv_shop_trolley_wares_info);
        helper.addOnClickListener(R.id.tv_shop_trolley_wares_price);
        helper.addOnClickListener(R.id.btn_shop_num);
        final ShopCountView shopCountView = helper.getView(R.id.btn_shop_num);
        shopCountView.setCountChangeListenter(new ShopCountView.CountChangeListenter() {
            @Override
            public void deleteTvClickListenter(View view) {
                Toast.makeText(mContext, "onItemChildClick" +"减去", Toast.LENGTH_LONG).show();
                if (shopCountView.getCount() == 1){
                    return;
                }else {
                    shopCountView.setCount(shopCountView.getCount() - 1);
                    shopCarModel.setCount(shopCountView.getCount()+"");
                }
            }

            @Override
            public void addTvClickListenter(View view) {
                Toast.makeText(mContext, "onItemChildClick" +"加上", Toast.LENGTH_LONG).show();
                shopCountView.setCount(shopCountView.getCount()+1);
                shopCarModel.setCount(shopCountView.getCount()+"");
            }

            @Override
            public void countEdtClickListenter(View view) {
                Toast.makeText(mContext, "onItemChildClick" +"编辑", Toast.LENGTH_LONG).show();


            }
        });
        helper.addOnClickListener(R.id.iv_shop_trolley_wares);
        helper.addOnLongClickListener(R.id.real_shop_content);
        helper.addOnClickListener(R.id.real_shop_content);
        final CheckBox checkBox = helper.getView(R.id.shop_trolley_wares_checkbox);
        checkBox.setChecked(shopCarModel.isChecked());



        checkBox.setChecked(selectShops.contains(item.t));

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    selectShops.add(item.t);
                }else {
                    if (selectShops.contains(item.t)){
                        selectShops.remove(item.t);
                    }
                }
                Log.d(TAG, "onCheckedChanged: "+selectShops.size());
            }
        });



    }

    public static List<ShopCarModel> selectShops = new ArrayList<>();



    public void selectAll(boolean isChecked){

        if (!UserStatusUtil.getInstanse().isLogin()|| mData.size() == 0){
            return;
        }

        if (isChecked){
            selectShops.clear();
            for (ShopCarSection section: mData) {
                if (!section.isHeader){
                    selectShops.add(section.t);
                }
            }

            notifyDataSetChanged();
        }else {
            selectShops.clear();

            notifyDataSetChanged();
        }
        Log.d(TAG, "onCheckedChanged: "+selectShops.size());
    }


    public void removeSelectShop(){
        selectShops.clear();
    }

}
