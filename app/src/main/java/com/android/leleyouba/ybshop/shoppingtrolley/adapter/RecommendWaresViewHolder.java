package com.android.leleyouba.ybshop.shoppingtrolley.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.common.BaseViewHolder;
import com.android.leleyouba.ybshop.common.MultiTypeAdapter;
import com.android.leleyouba.ybshop.shoppingtrolley.bean.RecommendWaresModel;

/**
 * Created by xalo on 2017/2/28.
 */

public class RecommendWaresViewHolder extends BaseViewHolder<RecommendWaresModel> {


    ImageView iv_commodity_referral;
    TextView tv_commodity_referral;
    TextView tv_commodity_price;
    ImageView iv_add_shop;
    public RecommendWaresViewHolder(View itemView) {
        super(itemView);
        iv_commodity_referral = (ImageView) itemView.findViewById(R.id.iv_commodity_referral);
        tv_commodity_referral = (TextView) itemView.findViewById(R.id.tv_commodity_referral);
        tv_commodity_price = (TextView) itemView.findViewById(R.id.tv_commodity_price);
        iv_add_shop = (ImageView) itemView.findViewById(R.id.iv_add_shop);

    }

    @Override
    public void setUpView(final RecommendWaresModel model, int position, MultiTypeAdapter adapter,Context context) {



    }
}
