package com.android.leleyouba.ybshop.classify.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.classify.bean.TitleBean;
import com.android.leleyouba.ybshop.common.BaseViewHolder;
import com.android.leleyouba.ybshop.common.MultiTypeAdapter;

/**
 * Created by xalo on 2017/3/1.
 */

public class TitleBeanViewHolder extends BaseViewHolder<TitleBean> {
    public TitleBeanViewHolder(View itemView) {

        super(itemView);
    }

    @Override
    public void setUpView(TitleBean model, int position, MultiTypeAdapter adapter,Context context) {
        TextView tv_classify_title = (TextView) getView(R.id.tv_classify_title);

        tv_classify_title.setText(model.getTitle());
    }
}
