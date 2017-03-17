package com.android.leleyouba.ybshop.mine.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.common.shopdetail.activity.ShopDetailActivity;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by xalo on 2017/2/27.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder> implements View.OnClickListener {

    List<String> list;
    Context context;

    public RecommendAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mine_fragment_cardview, parent, false);
        RecommendViewHolder holder = new RecommendViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecommendViewHolder holder, int position) {
        holder.cd_recommend_item.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cd_recommend_item:
                Intent intent = new Intent(context, ShopDetailActivity.class);
                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(R.anim.translation_animation_enter_in, R.anim.translation_animation_enter_out);
                break;
        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {
        CardView cd_recommend_item;

        public RecommendViewHolder(View itemView) {
            super(itemView);
            cd_recommend_item = (CardView) itemView.findViewById(R.id.cd_recommend_item);
        }
    }

}
