package com.android.leleyouba.ybshop.shoppingtrolley.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.mine.activity.AddresManagerActivity;
import com.android.leleyouba.ybshop.shoppingtrolley.activity.OrderActivity;
import com.android.leleyouba.ybshop.shoppingtrolley.bean.OrderShopItemBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by xalo on 2017/3/9.
 */

public class OrderShopItemRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    LayoutInflater mLayoutInflater;
    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_FOOTER = 2;
    public int mHeaderCount = 1;
    public int mFooterCount = 1;

    ArrayList<OrderShopItemBean> list;
    public static Context context;
    public String addressStr;

    public String getAddressStr() {
        return addressStr;
    }

    public void setAddressStr(String addressStr) {
        this.addressStr = addressStr;
    }

    public OrderShopItemRecycleAdapter(ArrayList<OrderShopItemBean> list, Context context) {
        this.list = list;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE_HEADER) {
            View view1 = mLayoutInflater.inflate(R.layout.order_header, parent, false);
            HeaderViewHolder headerViewHolder = new HeaderViewHolder(view1);
            return headerViewHolder;
        } else if (viewType == ITEM_TYPE_FOOTER) {
            View view2 = mLayoutInflater.inflate(R.layout.order_footer, parent, false);
            FooterViewHolder headerViewHolder = new FooterViewHolder(view2);
            return headerViewHolder;
        } else {
            View view = mLayoutInflater.inflate(R.layout.order_shop_item, parent, false);
            ContentViewHolder contentViewHolder = new ContentViewHolder(view);
            return contentViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            if (!TextUtils.isEmpty(addressStr)){
                ((HeaderViewHolder) holder).tv_order_address.setText(addressStr);
            }else {

            }

        } else if (holder instanceof FooterViewHolder) {

        } else {
            Log.d("list.size", list.size() + "");
            OrderShopItemBean bean = list.get(position - mHeaderCount);
            Glide.with(context).load(bean.getLogoStr()).into(((ContentViewHolder) holder).iv_order_shop_logo);
            Glide.with(context).load(bean.getShopImg()).into(((ContentViewHolder) holder).iv_order_shop_img);
            ((ContentViewHolder) holder).tv_order_shop_name.setText(bean.getShopName());
            ((ContentViewHolder) holder).tv_order_shop_title.setText(bean.getShopTitle());
            ((ContentViewHolder) holder).tv_order_shop_content.setText(bean.getShopContent());
            ((ContentViewHolder) holder).tv_order_shop_price.setText("￥ " + bean.getShopPrice());
            ((ContentViewHolder) holder).tv_order_shop_number.setText("x " + bean.getShopNumber());
            ((ContentViewHolder) holder).tv_order_shop_distribution.setText(bean.getShopDistribution());
            ((ContentViewHolder) holder).tv_order_shop_payment.setText(bean.getShopPayment());
            ((ContentViewHolder) holder).et_order_msgToSeller.setHint(bean.getShopMsgToSeller());
            ((ContentViewHolder) holder).tv_order_total_number.setText("共" + bean.getShopTotalNumber() + "件商品");
            ((ContentViewHolder) holder).tv_order_total_price.setText("￥ " + bean.getShopTotalPrice());
        }


    }


    @Override
    public int getItemCount() {
        return mHeaderCount + list.size() + mFooterCount;
    }

    /**
     * 判断当前item是否是HeaderView
     *
     * @param position
     * @return
     */
    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && position < mHeaderCount;
    }

    /**
     * 判断当前item是否是FooterrView
     *
     * @param position
     * @return
     */
    public boolean isFooterView(int position) {
        return mFooterCount != 0 && position >= (mHeaderCount + list.size());
    }

    /**
     * 判断当前item类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (mHeaderCount != 0 && position < mHeaderCount) {
            //头部
            return ITEM_TYPE_HEADER;
        } else if (mFooterCount != 0 && position >= (mHeaderCount + list.size())) {
            //底部
            return ITEM_TYPE_FOOTER;
        } else {

            return ITEM_TYPE_CONTENT;
        }

    }

    /**
     * 内容 ViewHolder
     */
    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_order_shop_logo, iv_order_shop_img;
        TextView tv_order_shop_name, tv_order_shop_title, tv_order_shop_content, tv_order_shop_price, tv_order_shop_number;
        TextView tv_order_shop_distribution, tv_order_shop_payment, tv_order_total_number, tv_order_total_price;
        EditText et_order_msgToSeller;

        public ContentViewHolder(View itemView) {
            super(itemView);
            iv_order_shop_logo = (ImageView) itemView.findViewById(R.id.iv_order_shop_logo);
            iv_order_shop_img = (ImageView) itemView.findViewById(R.id.iv_order_shop_img);
            tv_order_shop_name = (TextView) itemView.findViewById(R.id.tv_order_shop_name);
            tv_order_shop_title = (TextView) itemView.findViewById(R.id.tv_order_shop_title);
            tv_order_shop_content = (TextView) itemView.findViewById(R.id.tv_order_shop_content);
            tv_order_shop_price = (TextView) itemView.findViewById(R.id.tv_order_shop_price);
            tv_order_shop_number = (TextView) itemView.findViewById(R.id.tv_order_shop_number);
            tv_order_shop_distribution = (TextView) itemView.findViewById(R.id.tv_order_shop_distribution);
            tv_order_shop_payment = (TextView) itemView.findViewById(R.id.tv_order_shop_payment);
            et_order_msgToSeller = (EditText) itemView.findViewById(R.id.et_order_msgToSeller);
            tv_order_total_number = (TextView) itemView.findViewById(R.id.tv_order_total_number);
            tv_order_total_price = (TextView) itemView.findViewById(R.id.tv_order_total_price);
        }
    }

    /**
     * 头部 HeaderViewHolder
     */
    public static class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_order_account, tv_order_phoneNumber, tv_order_address;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            tv_order_account = (TextView) itemView.findViewById(R.id.tv_order_account);
            tv_order_phoneNumber = (TextView) itemView.findViewById(R.id.tv_order_phoneNumber);
            tv_order_address = (TextView) itemView.findViewById(R.id.tv_order_address);
            RelativeLayout rl_order_person = (RelativeLayout) itemView.findViewById(R.id.rl_order_person);
            rl_order_person.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rl_order_person:
                    Toast.makeText(context, "跳转到添加地址界面", Toast.LENGTH_SHORT).show();
                    jumpToAddressManager.jumpToAddressClickListener();

                    break;
            }
        }

    }

    public interface JumpToAddressManager {
        abstract void jumpToAddressClickListener();
    }

    private static JumpToAddressManager jumpToAddressManager;

    public JumpToAddressManager getJumpToAddressManager() {
        return jumpToAddressManager;
    }

    public void setJumpToAddressManager(JumpToAddressManager jumpToAddressManager) {
        this.jumpToAddressManager = jumpToAddressManager;
    }

    /**
     * 底部 FooterViewHolder
     */
    public static class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }


}
