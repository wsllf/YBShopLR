package com.android.leleyouba.ybshop.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.URLCollection;
import com.android.leleyouba.ybshop.mine.activity.AddresManagerActivity;
import com.android.leleyouba.ybshop.mine.activity.EditAndNewAddressActivity;
import com.android.leleyouba.ybshop.mine.bean.AddressBean;
import com.android.leleyouba.ybshop.mine.bean.PersonInformationBean;
import com.android.leleyouba.ybshop.mine.util.UserStatusUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xalo on 2017/3/9.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    private static final String TAG = "AddressAdapter";
    Context context;

    List<AddressBean> mAddressBeanList;

    OkHttpClient mOkHttpClient = new OkHttpClient();

    public AddressAdapter(List<AddressBean> addressBeanList, Context context){
        this.mAddressBeanList = addressBeanList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_recycle_item,parent,false);
        ViewHolder holder = new ViewHolder(view);


        return holder;
    }


    public interface LinearAddressItemOnClickListener{
        void onClick(AddressBean bean,int position);
    }

    public LinearAddressItemOnClickListener mAddressItemOnClickListener;

    public LinearAddressItemOnClickListener getAddressItemOnClickListener() {
        return mAddressItemOnClickListener;
    }

    public void setAddressItemOnClickListener(LinearAddressItemOnClickListener addressItemOnClickListener) {
        mAddressItemOnClickListener = addressItemOnClickListener;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final AddressBean addressBean = mAddressBeanList.get(position);
        holder.tv_address_consignee_name.setText(addressBean.getConsigneename());
        holder.tv_address_phonenum.setText(addressBean.getConsigneeNum());
        holder.tv_address_content.setText(addressBean.getConsigneeAddress());
        if (addressBean.getDefault_address().equals("1")){
            holder.checkbox_default_address.setChecked(true);
        }else {
            holder.checkbox_default_address.setChecked(false);
        }

        holder.checkbox_default_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort(holder,position);
            }
        });

        holder.tv_address_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success =  deleteAddressFromServer(position);

                    mAddressBeanList.remove(position);
                    notifyDataSetChanged();

            }
        });
        holder.img_address_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success =  deleteAddressFromServer(position);

                    mAddressBeanList.remove(position);
                    notifyDataSetChanged();

            }
        });
        holder.img_address_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editAddress(position);
            }
        });
        holder.tv_address_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editAddress(position);
            }
        });
        holder.linear_address_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAddressItemOnClickListener != null){
                    mAddressItemOnClickListener.onClick(addressBean,position);
                }
            }
        });
    }




    /**
     * 设置默认的地址在最上边
     * @param holder
     * @param position
     */
    private void sort(ViewHolder holder, int position) {

        AddressBean bean = mAddressBeanList.get(position);

        if (holder.checkbox_default_address.isChecked()){
            //改变服务器上的数据
          boolean success =  postDefaultAddressToServer(bean);
//            if (success){
                mAddressBeanList.remove(bean);
                for (int i = 0; i <mAddressBeanList.size() ; i++) {
                    mAddressBeanList.get(i).setDefault_address("0");
                }
                bean.setDefault_address("1");
                mAddressBeanList.add(0,bean);
                for (int i = 0; i < mAddressBeanList.size(); i++) {
                    Log.d(TAG, "sort: "+mAddressBeanList.get(i).getDefault_address());
                }
                notifyDataSetChanged();

        }

    }




    /**
     * 点击设置默认地址后上传
     * @return
     */
    private boolean postDefaultAddressToServer(AddressBean bean){
        final boolean a[] = {false};
        FormBody formBody  = new FormBody.Builder()
                .add("address.id",bean.getId())
                .build();

        Log.d(TAG, "postDefaultAddressToServer: "+UserStatusUtil.getInstanse().getCookie());
         Request request = new Request.Builder()
                .url(URLCollection.SET_DEFAULT_ADDRESS)
                .post(formBody)
                .addHeader("cookie", UserStatusUtil.getInstanse().getCookie())
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "postDefaultAddressToServer: "+"上传默认地址失败");
                e.printStackTrace();
                Message message = handler.obtainMessage();
                message.what = SET_DEFAULT_ADDRESS_FAILURE;
                message.obj = "上传默认地址失败";
                handler.sendMessage(message);
                a[0] = false;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.d(TAG, "onResponse:设置默认 "+str);
                Log.d(TAG, "onResponse: "+UserStatusUtil.getInstanse().getCookie());
                Message message = handler.obtainMessage();
                message.what = SET_DEFAULT_ADDRESS_SUCCESS;
                message.obj = str;
                handler.sendMessage(message);
                a[0] = true;
            }
        });
        return a[0];
    }

    private boolean deleteAddressFromServer(int position){
        final boolean[] a = {false};
        AddressBean bean = mAddressBeanList.get(position);

        FormBody formbody = new FormBody.Builder()
                .add("address.id",bean.getId())
                .build();
         Request request = new Request.Builder()
                .addHeader("cookie",UserStatusUtil.getInstanse().getCookie())
                .post(formbody)
                .url(URLCollection.DELETE_ADDRESS)
                .build();

        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "deleteAddressFromServer onFailure: " + "删除服务器的地址失败");
                e.printStackTrace();
                Message message = handler.obtainMessage();
                message.what = DELETE_ADDRESS_FAILURE;
                message.obj ="删除收货地址失败";
                handler.sendMessage(message);
                a[0] = false;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Message message = handler.obtainMessage();
                message.what = DELETE_ADDRESS_SUCCESS;
                message.obj =str;
                handler.sendMessage(message);
                a[0] = true;
            }
        });
        return a[0];
    }

    private void editAddress(int position){

        AddressBean addressBean = mAddressBeanList.get(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable("address",addressBean);

        Intent intent = new Intent(context, EditAndNewAddressActivity.class);
        intent.putExtra("address",bundle);
        ((AddresManagerActivity)context).startActivityForResult(intent,108);
    }

    @Override
    public int getItemCount() {
//        Log.d(TAG, "getItemCount: "+mAddressBeanList.size());
        return mAddressBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_address_consignee_name,tv_address_phonenum,tv_address_content,tv_address_edit,tv_address_delete;
        ImageView img_address_right,img_address_edit,img_address_delete;
        CheckBox checkbox_default_address;
        LinearLayout linear_address_item;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_address_consignee_name = (TextView) itemView.findViewById(R.id.tv_address_consignee_name);
            tv_address_phonenum = (TextView) itemView.findViewById(R.id.tv_address_phonenum);
            tv_address_content = (TextView) itemView.findViewById(R.id.tv_address_content);
            tv_address_edit = (TextView) itemView.findViewById(R.id.tv_address_edit);
            tv_address_delete = (TextView) itemView.findViewById(R.id.tv_address_delete);
            img_address_right = (ImageView) itemView.findViewById(R.id.img_address_right);
            img_address_edit = (ImageView) itemView.findViewById(R.id.img_address_edit);
            img_address_delete = (ImageView) itemView.findViewById(R.id.img_address_delete);
            checkbox_default_address = (CheckBox) itemView.findViewById(R.id.checkbox_default_address);
            linear_address_item = (LinearLayout) itemView.findViewById(R.id.linear_address_item);
        }
    }


    private final int SET_DEFAULT_ADDRESS_SUCCESS = 2000;
    private final int DELETE_ADDRESS_SUCCESS = 2001;
    private final int SET_DEFAULT_ADDRESS_FAILURE = 2002;
    private final int DELETE_ADDRESS_FAILURE = 2003;


    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what){

                case SET_DEFAULT_ADDRESS_SUCCESS:
                    Toast.makeText(context,(String)msg.obj,Toast.LENGTH_SHORT).show();
                    break;
                case DELETE_ADDRESS_SUCCESS:
                    Toast.makeText(context,(String)msg.obj,Toast.LENGTH_SHORT).show();
                    break;
                case SET_DEFAULT_ADDRESS_FAILURE:
                    Toast.makeText(context,(String)msg.obj,Toast.LENGTH_SHORT).show();
                    break;
                case DELETE_ADDRESS_FAILURE:
                    Toast.makeText(context,(String)msg.obj,Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
    });

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
