package com.android.leleyouba.ybshop.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xalo on 2017/2/28.
 */

public class MultiTypeAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private TypeFactory typeFactory;
    private List<Visitable> models;
    private Context context;

    public MultiTypeAdapter(List<Visitable> models, Context context) {
        this.models = models;
        this.context = context;
        this.typeFactory = new TypeFactoryForList();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(viewType, parent, false);
        return typeFactory.createViewHolder(viewType, itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setUpView(models.get(position), position, this, context);
    }

    @Override
    public int getItemCount() {
        if (null == models) {
            return 0;
        }
        return models.size();
    }

    public List<Visitable> getModels() {
        return models;
    }

    @Override
    public int getItemViewType(int position) {
        return models.get(position).type(typeFactory);
    }

    /**
     * @param position
     * @return
     */
    public int getSpanSize(int position) {
        return models.get(position).getSpanSize();
    }
}
