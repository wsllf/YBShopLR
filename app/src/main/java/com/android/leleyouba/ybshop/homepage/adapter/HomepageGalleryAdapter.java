package com.android.leleyouba.ybshop.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.homepage.bean.HomePageSecKillBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by xalo on 2017/3/2.
 */

public class HomepageGalleryAdapter extends RecyclerView.Adapter<HomepageGalleryAdapter.ViewHolder> {

    private Context context;
    private List<HomePageSecKillBean> mDatas;

    public HomepageGalleryAdapter(Context context, List<HomePageSecKillBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }


    @Override
    public HomepageGalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.homepage_gallery, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HomepageGalleryAdapter.ViewHolder holder, int position) {
        HomePageSecKillBean secKillBean = mDatas.get(position);
        Glide.with(context).load(secKillBean.getTopImg()).into(holder.iv_homepage_gallery);
        holder.tv_homepage_gallery_now.setText(secKillBean.getNowPrice());
        holder.tv_homepage_gallery_old.setText(secKillBean.getOldPrice());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_homepage_gallery;
        TextView tv_homepage_gallery_now, tv_homepage_gallery_old;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_homepage_gallery = (ImageView) itemView.findViewById(R.id.iv_homepage_gallery);
            tv_homepage_gallery_now = (TextView) itemView.findViewById(R.id.tv_homepage_gallery_now);
            tv_homepage_gallery_old = (TextView) itemView.findViewById(R.id.tv_homepage_gallery_old);
        }
    }
}
