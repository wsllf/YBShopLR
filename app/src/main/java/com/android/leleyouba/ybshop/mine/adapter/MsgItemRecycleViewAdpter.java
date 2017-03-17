package com.android.leleyouba.ybshop.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.mine.bean.MsgItemBean;

import java.util.ArrayList;

/**
 * 消息中心对应的适配器
 */

public class MsgItemRecycleViewAdpter extends RecyclerView.Adapter<MsgItemRecycleViewAdpter.MsgViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    ArrayList<MsgItemBean> data;
    Context context;
    RecyclerView recyclerView;

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {

            mOnItemClickListener.onItemClick(v);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemClickListener != null) {

            mOnItemClickListener.onItemLongClick(v);
        }
        return false;
    }

    /**
     * 添加item的方法
     *
     * @param bean
     * @param position
     */
    public void addItem(MsgItemBean bean, int position) {
        data.add(position, bean);
        notifyItemInserted(position);
        recyclerView.scrollToPosition(position);
    }

    /**
     * 删除item的方法
     */
    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    //自定义监听事件
    public static interface OnRecycleViewItemClickListener {
        void onItemClick(View view);

        void onItemLongClick(View view);
    }

    private OnRecycleViewItemClickListener mOnItemClickListener = null;

    public void setmOnItemClickListener(OnRecycleViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public MsgItemRecycleViewAdpter(ArrayList<MsgItemBean> data, Context context, RecyclerView recyclerView) {
        this.data = data;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    /**
     * 设置布局
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MsgItemRecycleViewAdpter.MsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_msg_mine, parent, false);
        //给布局设置点击和长按监听
        item.setOnClickListener(this);
        item.setOnLongClickListener(this);

        MsgViewHolder holder = new MsgViewHolder(item);
        return holder;
    }

    /**
     * 填充数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MsgItemRecycleViewAdpter.MsgViewHolder holder, final int position) {

        if (position ==0){

        }
        MsgItemBean bean = data.get(position);
        holder.iv_msg_item.setImageResource(R.mipmap.y_);
        holder.tv_msg_item_title.setText(bean.getTitle());
        holder.tv_msg_item_content.setText(bean.getContent());
        holder.tv_msg_item_date.setText(bean.getDate());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {

        return super.getItemId(position);
    }

    class MsgViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_msg_item;
        TextView tv_msg_item_title, tv_msg_item_content, tv_msg_item_date;

        public MsgViewHolder(View itemView) {
            super(itemView);
            iv_msg_item = (ImageView) itemView.findViewById(R.id.iv_msg_item);
            tv_msg_item_title = (TextView) itemView.findViewById(R.id.tv_msg_item_title);
            tv_msg_item_content = (TextView) itemView.findViewById(R.id.tv_msg_item_content);
            tv_msg_item_date = (TextView) itemView.findViewById(R.id.tv_msg_item_date);
        }
    }

}
