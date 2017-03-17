package com.android.leleyouba.ybshop.shoppingtrolley.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.leleyouba.ybshop.R;

/**
 * Created by xalo on 2017/3/13.
 */

public class OrderDetailItemAdapter extends RecyclerView.Adapter<OrderDetailItemAdapter.ViewHolder> {

    Context context;

    public OrderDetailItemAdapter(Context context) {
        this.context = context;
    }

    @Override
    public OrderDetailItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_detail_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(OrderDetailItemAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
