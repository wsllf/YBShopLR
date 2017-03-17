package com.android.leleyouba.ybshop.classify.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.classify.activity.DetailClassifyActivity;
import com.android.leleyouba.ybshop.classify.bean.SmallClassifyBean;
import com.android.leleyouba.ybshop.common.BaseViewHolder;
import com.android.leleyouba.ybshop.common.MultiTypeAdapter;
import com.bumptech.glide.Glide;

/**
 *
 */

public class SmallClassifyBeanViewHolder extends BaseViewHolder<SmallClassifyBean>{
    public SmallClassifyBeanViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(final SmallClassifyBean model, int position, MultiTypeAdapter adapter, final Context context) {
        RelativeLayout rl_classify_small = (RelativeLayout) itemView.findViewById(R.id.rl_classify_small);
        ImageView iv_classify_small = (ImageView) getView(R.id.iv_classify_small);
        TextView tv_classify_small = (TextView) getView(R.id.tv_classify_small);
        tv_classify_small.setText(model.getCategoryName());
        Glide.with(context).load(model.getCategoryImg()).into(iv_classify_small);

        rl_classify_small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,model.getCategoryName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailClassifyActivity.class);
                context.startActivity(intent);
                ((Activity)context).overridePendingTransition(R.anim.translation_animation_enter_in,R.anim.translation_animation_enter_out);
            }
        });
    }
}
