package com.android.leleyouba.ybshop.find.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.common.BaseViewHolder;
import com.android.leleyouba.ybshop.common.MultiTypeAdapter;
import com.android.leleyouba.ybshop.find.bean.FindThreeImgBean;
import com.bumptech.glide.Glide;

/**
 *
 */
public class FindThreeImgViewHolder extends BaseViewHolder<FindThreeImgBean> implements View.OnClickListener {
    Context context;

    public FindThreeImgViewHolder(View itemView) {
        super(itemView);

    }

    @Override
    public void setUpView(FindThreeImgBean model, int position, MultiTypeAdapter adapter, Context context) {
        this.context = context;
        ImageView iv_find_threeImg_img_one = (ImageView) itemView.findViewById(R.id.iv_find_threeImg_img_one);
        ImageView iv_find_threeImg_img_two = (ImageView) itemView.findViewById(R.id.iv_find_threeImg_img_two);
        ImageView iv_find_threeImg_img_three = (ImageView) itemView.findViewById(R.id.iv_find_threeImg_img_three);
        TextView tv_find_threeImg_theme_one = (TextView) itemView.findViewById(R.id.tv_find_threeImg_theme_one);
        TextView tv_find_threeImg_theme_two = (TextView) itemView.findViewById(R.id.tv_find_threeImg_theme_two);
        TextView tv_find_threeImg_theme_three = (TextView) itemView.findViewById(R.id.tv_find_threeImg_theme_three);
        TextView tv_find_threeImg_content_one = (TextView) itemView.findViewById(R.id.tv_find_threeImg_content_one);
        TextView tv_find_threeImg_content_two = (TextView) itemView.findViewById(R.id.tv_find_threeImg_content_two);
        TextView tv_find_threeImg_content_three = (TextView) itemView.findViewById(R.id.tv_find_threeImg_content_three);
        Glide.with(context).load(model.getImgStr1()).into(iv_find_threeImg_img_one);
        Glide.with(context).load(model.getImgStr2()).into(iv_find_threeImg_img_two);
        Glide.with(context).load(model.getImgStr3()).into(iv_find_threeImg_img_three);
        tv_find_threeImg_theme_one.setText("一" + model.getThemeStr1() + "一");
        tv_find_threeImg_theme_two.setText("一" + model.getThemeStr2() + "一");
        tv_find_threeImg_theme_three.setText("一" + model.getThemeStr3() + "一");
        tv_find_threeImg_content_one.setText(model.getContentStr1());
        tv_find_threeImg_content_two.setText(model.getContentStr2());
        tv_find_threeImg_content_three.setText(model.getContentStr3());

        LinearLayout ll_find_threeImg_img_one = (LinearLayout) itemView.findViewById(R.id.ll_find_threeImg_img_one);
        LinearLayout ll_find_threeImg_img_two = (LinearLayout) itemView.findViewById(R.id.ll_find_threeImg_img_two);
        LinearLayout ll_find_threeImg_img_three = (LinearLayout) itemView.findViewById(R.id.ll_find_threeImg_img_three);
        ll_find_threeImg_img_one.setOnClickListener(this);
        ll_find_threeImg_img_two.setOnClickListener(this);
        ll_find_threeImg_img_three.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_find_threeImg_img_one:
                Toast.makeText(context,"跳转1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_find_threeImg_img_two:
                Toast.makeText(context,"跳转2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_find_threeImg_img_three:
                Toast.makeText(context,"跳转3",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
