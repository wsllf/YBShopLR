package com.android.leleyouba.ybshop.find.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.common.BaseViewHolder;
import com.android.leleyouba.ybshop.common.MultiTypeAdapter;
import com.android.leleyouba.ybshop.find.bean.FindBean;
import com.android.leleyouba.ybshop.find.bean.FindClassifyBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 *
 */
public class FindClassifyViewHodler extends BaseViewHolder<FindBean> {

    static ArrayList<FindClassifyBean> findClassifyBeans = new ArrayList<>();

    public FindClassifyViewHodler(View itemView) {
        super(itemView);

    }

    @Override
    public void setUpView(FindBean model, int position, MultiTypeAdapter adapter, Context context) {

        Log.d("model", model + "");

        RecyclerView recycle_find_classify = (RecyclerView) itemView.findViewById(R.id.recycle_find_classify);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycle_find_classify.setLayoutManager(manager);
//        if (!findClassifyBeans.contains(model)){
//            findClassifyBeans.add(model);
//        }
        FindClassifyAdapter findClassifyAdapter = new FindClassifyAdapter(context, model.getFindClassifyBeanDatas());
        recycle_find_classify.setAdapter(findClassifyAdapter);
    }


    class FindClassifyAdapter extends RecyclerView.Adapter<FindClassifyAdapter.ViewHolder> {

        Context context;
        ArrayList<FindClassifyBean> list;

        public FindClassifyAdapter(Context context, ArrayList<FindClassifyBean> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public FindClassifyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.find_classify_item, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(FindClassifyAdapter.ViewHolder holder, int position) {
            FindClassifyBean bean = list.get(position);
            Log.d("FindClassifyBean", list.size() + "=========" + position + "");
            Glide.with(context).load(bean.getImgStr()).placeholder(R.mipmap.ic_launcher).into(holder.iv_find_classify_item);
            holder.tv_find_classify_item.setText(bean.getTitleStr());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv_find_classify_item;
            TextView tv_find_classify_item;

            public ViewHolder(View itemView) {
                super(itemView);
                iv_find_classify_item = (ImageView) itemView.findViewById(R.id.iv_find_classify_item);
                tv_find_classify_item = (TextView) itemView.findViewById(R.id.tv_find_classify_item);

            }
        }
    }
}
