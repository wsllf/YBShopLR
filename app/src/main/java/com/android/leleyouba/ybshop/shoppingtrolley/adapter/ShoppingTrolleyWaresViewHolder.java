package com.android.leleyouba.ybshop.shoppingtrolley.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.common.BaseViewHolder;
import com.android.leleyouba.ybshop.common.MultiTypeAdapter;
import com.android.leleyouba.ybshop.shoppingtrolley.bean.ShoppingTrolleyWaresModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xalo on 2017/2/28.
 */

public class ShoppingTrolleyWaresViewHolder extends BaseViewHolder<ShoppingTrolleyWaresModel> {

    // RecyclerView 的第一个item，肯定是展示StickyLayout的.
    public static final int FIRST_STICKY_VIEW = 1;
    // RecyclerView 除了第一个item以外，要展示StickyLayout的.
    public static final int HAS_STICKY_VIEW = 2;
    // RecyclerView 的不展示StickyLayout的item.
    public static final int NONE_STICKY_VIEW = 3;

   static List<ShoppingTrolleyWaresModel> checkedModels = new ArrayList<>();

   static List<ShoppingTrolleyWaresModel> allModels = new ArrayList<>();

    LinearLayout rv_header;
    CheckBox shop_trolley_wares_checkbox;
    RelativeLayout real_shop_content;
    ImageView iv_shop_trolley_wares;
    TextView tv_shop_trolley_wares_info, tv_shop_trolley_wares_price;


    public ShoppingTrolleyWaresViewHolder(View itemView) {
        super(itemView);
        rv_header = (LinearLayout) itemView.findViewById(R.id.rv_header);
        shop_trolley_wares_checkbox = (CheckBox) itemView.findViewById(R.id.shop_trolley_wares_checkbox);
    }


    @Override
    public void setUpView(final ShoppingTrolleyWaresModel model, final int position, MultiTypeAdapter adapter,
                          Context context) {


        allModels.add(model);

        shop_trolley_wares_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (listenter != null){
                    listenter.checkedChange(model,position,isChecked);
                }

                if (isChecked) {
                    //通知改变
                    shop_trolley_wares_checkbox.setTag( isChecked);
                } else {
                    checkedModels.remove(model);
                    shop_trolley_wares_checkbox.setTag( isChecked);
                }
            }
        });

        if (position == 0) {
            this.rv_header.setVisibility(View.VISIBLE);
            ((TextView) this.rv_header.findViewById(R.id.tv_header_store_name)).setText(model.getShopStore());

            // 第一个item的吸顶信息肯定是展示的，并且标记tag为FIRST_STICKY_VIEW
            this.itemView.setTag(FIRST_STICKY_VIEW);

        } else {
            // 之后的item都会和前一个item要展示的吸顶信息进行比较，不相同就展示，并且标记tag为HAS_STICKY_VIEW
            if (!TextUtils.equals(model.getShopStore(), ((ShoppingTrolleyWaresModel) adapter.getModels().get(position
                    - 1)).getShopStore())) {
                this.rv_header.setVisibility(View.VISIBLE);
                ((TextView) this.rv_header.findViewById(R.id.tv_header_store_name)).setText(model.getShopStore());
                this.itemView.setTag(HAS_STICKY_VIEW);

            } else {
                // 相同就不展示，并且标记tag为NONE_STICKY_VIEW
                this.rv_header.setVisibility(View.GONE);
                this.itemView.setTag(NONE_STICKY_VIEW);

            }
        }
// ContentDescription 用来记录并获取要吸顶展示的信息
        this.itemView.setContentDescription(model.getShopStore());
    }

    public static List<ShoppingTrolleyWaresModel> getCheckedModels(){

        return checkedModels;
    }

    public void checkedAllModels(){
        if (allModels.size() > checkedModels.size()){
            checkedModels = allModels;
            //把所有的check状态改为选中
        }else if (allModels.size() == checkedModels.size()){
            for (ShoppingTrolleyWaresModel models: checkedModels) {
                checkedModels.remove(models);

            }
            //把所有的check状态改为未选中
        }
    }


    public interface CheckedChangeListenter{
        void checkedChange(ShoppingTrolleyWaresModel model,int position,boolean isChecked);
    }

    private CheckedChangeListenter listenter;

    public CheckedChangeListenter getListenter() {
        return listenter;
    }

    public void setListenter(CheckedChangeListenter listenter) {
        this.listenter = listenter;
    }
}
