package com.android.leleyouba.ybshop.common.shopdetail.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.leleyouba.ybshop.R;

import java.util.List;
import java.util.Map;

/**
 * Created by xalo on 2017/3/8.
 */

public class EvalutationAdapter extends RecyclerView.Adapter<EvalutationAdapter.ViewHolder> {


        List<Map<String,String>> mMapList;
        List<List<String>> imgLsit;

    public EvalutationAdapter(List<Map<String, String>> mapList, List<List<String>> imgLsit) {
        this.mMapList = mapList;
        this.imgLsit = imgLsit;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.evalutation_layout,parent,false);

        ViewHolder holder = new ViewHolder(view,parent.getContext());

        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Map<String,String> map = mMapList.get(position);

        String name = map.get("nickname");
        String eva_date = map.get("eva_date");
        String content = map.get("content");
        String type = map.get("type");
        String rate = map.get("rate");
        String buy_date = map.get("buy_date");
        String encouge_count = map.get("encouge_count");
        String reply_count = map.get("reply_count");

        holder.tv_evalutation_user_nickname.setText(name);
        holder.tv_evalutation_date.setText(eva_date);
        holder.tv_evalutation_content.setText(content);
        holder.tv_evalutation_classfy.setText(type);
        holder.rate_evalutation.setRating((float) Integer.parseInt(rate));
        holder.tv_evalutation_buy_date.setText(buy_date);
        holder.tv_evalutation_reply_count.setText(reply_count);
        holder.tv_evalutation_encourage_count.setText(encouge_count);


        if (imgLsit.get(position).size() > 0){
            holder.recycle_evalutation_img.setVisibility(View.VISIBLE);
            EvalutationImageAdapter imageAdapter = new EvalutationImageAdapter(imgLsit.get(position));
            LinearLayoutManager layoutManager = new LinearLayoutManager(holder.mContext,LinearLayoutManager.HORIZONTAL,false);
//            holder.recycle_evalutation_img.addItemDecoration(new RecyclerView.ItemDecoration() {
//
//                @Override
//                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                    outRect.set(5,5,5,5);
//                }
//            });

            holder.recycle_evalutation_img.setLayoutManager(layoutManager);
            holder.recycle_evalutation_img.setAdapter(imageAdapter);

        }else {
            holder.recycle_evalutation_img.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mMapList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        ImageView iv_evalutation_user_img,
                iv_evalutation_user_level,
                img_evalutation_encourage;
        TextView tv_evalutation_user_nickname,
                tv_evalutation_date,
                tv_evalutation_content,
                tv_evalutation_classfy,
                tv_evalutation_buy_date,
                tv_evalutation_reply_count,
                tv_evalutation_encourage_count;
        RatingBar rate_evalutation;
        RecyclerView recycle_evalutation_img;


        Context mContext;
        public ViewHolder(View itemView, Context context) {
            super(itemView);
            this.mContext = context;
            iv_evalutation_user_img = (ImageView) itemView.findViewById(R.id.iv_evalutation_user_img);
            iv_evalutation_user_level = (ImageView) itemView.findViewById(R.id.iv_evalutation_user_level);
            img_evalutation_encourage = (ImageView) itemView.findViewById(R.id.img_evalutation_encourage);

            tv_evalutation_user_nickname = (TextView) itemView.findViewById(R.id.tv_evalutation_user_nickname);
            tv_evalutation_date = (TextView) itemView.findViewById(R.id.tv_evalutation_date);
            tv_evalutation_content = (TextView) itemView.findViewById(R.id.tv_evalutation_content);
            tv_evalutation_classfy = (TextView) itemView.findViewById(R.id.tv_evalutation_classfy);
            tv_evalutation_buy_date = (TextView) itemView.findViewById(R.id.tv_evalutation_buy_date);
            tv_evalutation_reply_count = (TextView) itemView.findViewById(R.id.tv_evalutation_reply_count);
            tv_evalutation_encourage_count = (TextView) itemView.findViewById(R.id.tv_evalutation_encourage_count);

            rate_evalutation = (RatingBar) itemView.findViewById(R.id.rate_evalutation);
            recycle_evalutation_img = (RecyclerView) itemView.findViewById(R.id.recycle_evalutation_img);
        }
    }
}
