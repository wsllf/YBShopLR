package com.android.leleyouba.ybshop.classify.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.classify.bean.AdvBean;
import com.android.leleyouba.ybshop.common.BaseViewHolder;
import com.android.leleyouba.ybshop.common.MultiTypeAdapter;
import com.bumptech.glide.Glide;

/**
 * Created by xalo on 2017/3/1.
 */

public class AdvBeanViewHolder extends BaseViewHolder<AdvBean>{

    public AdvBeanViewHolder(View itemView) {
        super(itemView);

    }

    @Override
    public void setUpView(AdvBean model, int position, MultiTypeAdapter adapter,Context context) {
        ImageView iv_classify_adv = (ImageView) getView(R.id.iv_classify_adv);

        Glide.with(context).load(model.getAdvStr()).into(iv_classify_adv);

    }
}
