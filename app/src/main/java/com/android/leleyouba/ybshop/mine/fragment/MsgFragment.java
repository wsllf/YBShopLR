package com.android.leleyouba.ybshop.mine.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.mine.adapter.MsgItemRecycleViewAdpter;
import com.android.leleyouba.ybshop.mine.bean.MsgItemBean;
import com.android.leleyouba.ybshop.mine.util.SpacesItemDecoration;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MsgFragment extends Fragment {
    RecyclerView rl_msg_show;
    MsgItemRecycleViewAdpter adapter;

    public MsgFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_msg, container, false);

        setRecycle(view);
        return view;
    }

    /**
     * 设置Recycle
     *
     * @param view
     */
    private void setRecycle(View view) {
        rl_msg_show = (RecyclerView) view.findViewById(R.id.rl_msg_show);
        //设置布局管理器
        rl_msg_show.setLayoutManager(new LinearLayoutManager(getContext()));
        int spacingInPixels = 10;
        rl_msg_show.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        //准备数据
        ArrayList<MsgItemBean> list = new ArrayList<MsgItemBean>();
        list.add(new MsgItemBean("", "有吧客服", "点击查看您与客服的沟通记录", "1"));
        list.add(new MsgItemBean("", "优惠促销", "点击查看您与客服的沟通记录", "2"));
        list.add(new MsgItemBean("", "我的资产", "点击查看您与客服的沟通记录", "3"));
        list.add(new MsgItemBean("", "我的资产", "点击查看您与客服的沟通记录", "4"));
        list.add(new MsgItemBean("", "我的资产", "点击查看您与客服的沟通记录", "5"));
        list.add(new MsgItemBean("", "我的资产", "点击查看您与客服的沟通记录", "6"));
        list.add(new MsgItemBean("", "我的资产", "点击查看您与客服的沟通记录", "7"));
        list.add(new MsgItemBean("", "我的资产", "点击查看您与客服的沟通记录", "8"));
        list.add(new MsgItemBean("", "我的资产", "点击查看您与客服的沟通记录", "9"));
        list.add(new MsgItemBean("", "我的资产", "点击查看您与客服的沟通记录", "10"));
        list.add(new MsgItemBean("", "我的资产", "点击查看您与客服的沟通记录", "11"));
        list.add(new MsgItemBean("", "我的资产", "点击查看您与客服的沟通记录", "12"));
        list.add(new MsgItemBean("", "我的资产", "点击查看您与客服的沟通记录", "13"));
        list.add(new MsgItemBean("", "我的资产", "点击查看您与客服的沟通记录", "14"));
        list.add(new MsgItemBean("", "我的资产", "点击查看您与客服的沟通记录", "15"));
        list.add(new MsgItemBean("", "我的资产", "点击查看您与客服的沟通记录", "16"));
        list.add(new MsgItemBean("", "我的资产", "点击查看您与客服的沟通记录", "17"));
        list.add(new MsgItemBean("", "我的资产", "点击查看您与客服的沟通记录", "18"));
        list.add(new MsgItemBean("", "我的资产", "点击查看您与客服的沟通记录", "19"));
        adapter = new MsgItemRecycleViewAdpter(list, getContext(), rl_msg_show);
        rl_msg_show.setAdapter(adapter);

        //item的点击相关的方法
        setAdapterHitMethod();


    }

    private void setAdapterHitMethod() {

        adapter.setmOnItemClickListener(new MsgItemRecycleViewAdpter.OnRecycleViewItemClickListener() {
            @Override
            public void onItemClick(View view) {
                Toast.makeText(getContext(), "点击了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view) {
                Toast.makeText(getContext(), "长安了", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("确定删除此类消息吗？").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        adapter.removeItem();
                        Toast.makeText(getContext(), "已经删除", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).create();

                builder.show();

            }
        });
    }


}
