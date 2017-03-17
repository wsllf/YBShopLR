package com.android.leleyouba.ybshop.common.shopdetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.android.leleyouba.ybshop.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by xalo on 2017/3/8.
 */

public class EvalutationImageAdapter extends RecyclerView.Adapter<EvalutationImageAdapter.ViewHolder> {



    List<String> imgList;

    public EvalutationImageAdapter(List<String> imgList) {
        this.imgList = imgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.evalutation_img_layout,parent,false);
        ViewHolder holder = new ViewHolder(view,parent.getContext());

        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(holder.mContext).load(imgList.get(position)).asBitmap().into(holder.img_evalutation);

    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_evalutation;
        Context mContext;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            this.mContext = context;
            img_evalutation = (ImageView) itemView.findViewById(R.id.img_evalutation);
        }
    }
}
