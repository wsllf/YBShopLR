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
import com.android.leleyouba.ybshop.find.bean.FindThreeImgItemBean;
import com.bumptech.glide.Glide;

/**
 * Created by xalo on 2017/3/7.
 */
public class FindThreeImgItemViewHolder extends BaseViewHolder<FindThreeImgItemBean> {

    public FindThreeImgItemViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(FindThreeImgItemBean model, final int position, MultiTypeAdapter adapter, final Context context) {
        LinearLayout ll_three_img_item = (LinearLayout) itemView.findViewById(R.id.ll_three_img_item);
        TextView tv_three_img_item_title = (TextView) itemView.findViewById(R.id.tv_three_img_item_title);
        TextView tv_three_img_item_content = (TextView) itemView.findViewById(R.id.tv_three_img_item_content);
        TextView tv_three_img_item_author = (TextView) itemView.findViewById(R.id.tv_three_img_item_author);
        TextView tv_three_img_item_browse = (TextView) itemView.findViewById(R.id.tv_three_img_item_browse);
        ImageView iv_three_img_item_one = (ImageView) itemView.findViewById(R.id.iv_three_img_item_one);
        ImageView iv_three_img_item_two = (ImageView) itemView.findViewById(R.id.iv_three_img_item_two);
        ImageView iv_three_img_item_three = (ImageView) itemView.findViewById(R.id.iv_three_img_item_three);
        ImageView civ_three_img_item_left = (ImageView) itemView.findViewById(R.id.civ_three_img_item_left);
        tv_three_img_item_title.setText(model.getTitleStr());
        tv_three_img_item_content.setText(model.getContentStr());
        tv_three_img_item_author.setText(model.getAuthorStr());
        tv_three_img_item_browse.setText(model.getBrowseStr());
        Glide.with(context).load(model.getLeftCivImg()).into(civ_three_img_item_left);
        Glide.with(context).load(model.getImgOne()).into(iv_three_img_item_one);
        Glide.with(context).load(model.getImgTwo()).into(iv_three_img_item_two);
        Glide.with(context).load(model.getImgThree()).into(iv_three_img_item_three);
        ll_three_img_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了：" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
