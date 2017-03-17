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
import com.android.leleyouba.ybshop.find.bean.FindRightImgItemBean;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 *
 */
public class FindRightImgItemViewHolder extends BaseViewHolder<FindRightImgItemBean> {

    public FindRightImgItemViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(FindRightImgItemBean model, final int position, MultiTypeAdapter adapter, final Context context) {
        LinearLayout ll_right_img_item = (LinearLayout) itemView.findViewById(R.id.ll_right_img_item);
        TextView tv_right_img_item_title = (TextView) itemView.findViewById(R.id.tv_right_img_item_title);
        TextView tv_right_img_item_content = (TextView) itemView.findViewById(R.id.tv_right_img_item_content);
        TextView tv_right_img_item_author = (TextView) itemView.findViewById(R.id.tv_right_img_item_author);
        TextView tv_right_img_item_date = (TextView) itemView.findViewById(R.id.tv_right_img_item_date);
        TextView tv_right_img_item_browse = (TextView) itemView.findViewById(R.id.tv_right_img_item_browse);
        ImageView iv_right_img_item_right = (ImageView) itemView.findViewById(R.id.iv_right_img_item_right);
        ImageView iv_right_img_item_isMedia = (ImageView) itemView.findViewById(R.id.iv_right_img_item_isMedia);
        CircleImageView civ_right_img_item_left = (CircleImageView) itemView.findViewById(R.id.civ_right_img_item_left);
        tv_right_img_item_title.setText(model.getTitleStr());
        tv_right_img_item_content.setText(model.getContentStr());
        tv_right_img_item_author.setText(model.getAuthorStr());
        tv_right_img_item_date.setText(model.getDateStr());
        tv_right_img_item_browse.setText(model.getBrowseStr());
        Glide.with(context).load(model.getImg()).into(iv_right_img_item_right);
        Glide.with(context).load(model.getLeftCirImg()).into(civ_right_img_item_left);

        if (!model.isMedia()){
            iv_right_img_item_isMedia.setVisibility(View.VISIBLE);
        }


        ll_right_img_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了：" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
