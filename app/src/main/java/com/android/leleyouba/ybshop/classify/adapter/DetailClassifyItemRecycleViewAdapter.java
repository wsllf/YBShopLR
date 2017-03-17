package com.android.leleyouba.ybshop.classify.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.classify.bean.BaseShopBean;
import com.android.leleyouba.ybshop.common.shopdetail.activity.ShopDetailActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by xalo on 2017/3/9.
 */

public class DetailClassifyItemRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {


    public boolean TYPE_ITEM_ONE = false;
    public boolean TYPE_ITEM_TWO = true;
    private ArrayList<BaseShopBean> datas;
    Context context;
    boolean isOne;

    public DetailClassifyItemRecycleViewAdapter(ArrayList<BaseShopBean> datas, Context context, boolean isOne) {
        this.datas = datas;
        this.context = context;
        this.isOne = isOne;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (TYPE_ITEM_ONE = isOne) {
            View view = LayoutInflater.from(context).inflate(R.layout.detail_classify_item_one, parent, false);
            DetailClassifyItemOneViewHolder holder = new DetailClassifyItemOneViewHolder(view);
            return holder;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.detail_classify_item_two, parent, false);
            DetailClassifyItemTwoViewHolder holder = new DetailClassifyItemTwoViewHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseShopBean baseShopBean = datas.get(position);
        if (holder instanceof DetailClassifyItemOneViewHolder) {
            ((DetailClassifyItemOneViewHolder) holder).tv_detail_classi_title.setText(baseShopBean.getGoodsName());
            ((DetailClassifyItemOneViewHolder) holder).tv_detail_classi_evaluate.setText(baseShopBean.getEvaluate());
            ((DetailClassifyItemOneViewHolder) holder).tv_detail_classify_price.setText("￥ " + baseShopBean.getInPrice());
            Glide.with(context).load(baseShopBean.getPictures().get(0)).into(((DetailClassifyItemOneViewHolder) holder).iv_detail_classify_item_img);

            ((DetailClassifyItemOneViewHolder) holder).ll_detail_classify_item.setOnClickListener(this);

        } else {
            ((DetailClassifyItemTwoViewHolder) holder).tv_detail_classi_title.setText(baseShopBean.getGoodsName());
            ((DetailClassifyItemTwoViewHolder) holder).tv_detail_classi_evaluate.setText(baseShopBean.getEvaluate());
            ((DetailClassifyItemTwoViewHolder) holder).tv_detail_classify_price.setText("￥ " + baseShopBean.getInPrice());
            Glide.with(context).load(baseShopBean.getPictures().get(0)).into(((DetailClassifyItemTwoViewHolder) holder).iv_detail_classify_item_img);
            ((DetailClassifyItemTwoViewHolder) holder).ll_detail_classify_item.setOnClickListener(this);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_detail_classify_item:
                Intent intent = new Intent(context, ShopDetailActivity.class);
                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(R.anim.translation_animation_enter_in, R.anim.translation_animation_enter_out);
                break;
        }
    }

    public static class DetailClassifyItemOneViewHolder extends RecyclerView.ViewHolder {
        TextView tv_detail_classi_title, tv_detail_classify_coupon, tv_detail_classify_fullCut, tv_detail_classify_price, tv_detail_classi_evaluate, tv_detail_classi_adv;
        ImageView iv_detail_classify_item_img;
        LinearLayout ll_detail_classify_item;

        public DetailClassifyItemOneViewHolder(View itemView) {
            super(itemView);
            tv_detail_classi_title = (TextView) itemView.findViewById(R.id.tv_detail_classi_title);
            tv_detail_classify_coupon = (TextView) itemView.findViewById(R.id.tv_detail_classify_coupon);
            tv_detail_classify_fullCut = (TextView) itemView.findViewById(R.id.tv_detail_classify_fullCut);
            tv_detail_classify_price = (TextView) itemView.findViewById(R.id.tv_detail_classify_price);
            tv_detail_classi_evaluate = (TextView) itemView.findViewById(R.id.tv_detail_classi_evaluate);
            tv_detail_classi_adv = (TextView) itemView.findViewById(R.id.tv_detail_classi_adv);
            iv_detail_classify_item_img = (ImageView) itemView.findViewById(R.id.iv_detail_classify_item_img);
            ll_detail_classify_item = (LinearLayout) itemView.findViewById(R.id.ll_detail_classify_item);
        }


    }

    public static class DetailClassifyItemTwoViewHolder extends RecyclerView.ViewHolder {
        TextView tv_detail_classi_title, tv_detail_classify_coupon, tv_detail_classify_fullCut, tv_detail_classify_price, tv_detail_classi_evaluate, tv_detail_classi_adv;
        ImageView iv_detail_classify_item_img;
        LinearLayout ll_detail_classify_item;

        public DetailClassifyItemTwoViewHolder(View itemView) {
            super(itemView);
            tv_detail_classi_title = (TextView) itemView.findViewById(R.id.tv_detail_classi_title);
            tv_detail_classify_coupon = (TextView) itemView.findViewById(R.id.tv_detail_classify_coupon);
            tv_detail_classify_fullCut = (TextView) itemView.findViewById(R.id.tv_detail_classify_fullCut);
            tv_detail_classify_price = (TextView) itemView.findViewById(R.id.tv_detail_classify_price);
            tv_detail_classi_evaluate = (TextView) itemView.findViewById(R.id.tv_detail_classi_evaluate);
            tv_detail_classi_adv = (TextView) itemView.findViewById(R.id.tv_detail_classi_adv);
            iv_detail_classify_item_img = (ImageView) itemView.findViewById(R.id.iv_detail_classify_item_img);
            ll_detail_classify_item = (LinearLayout) itemView.findViewById(R.id.ll_detail_classify_item);
        }
    }
}
