package com.android.leleyouba.ybshop.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.MenuItem;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.URLCollection;
import com.android.leleyouba.ybshop.mine.adapter.AddressAdapter;
import com.android.leleyouba.ybshop.mine.bean.AddressBean;
import com.android.leleyouba.ybshop.mine.bean.AllAddress;
import com.android.leleyouba.ybshop.mine.util.CommonMethod;
import com.android.leleyouba.ybshop.mine.util.UserStatusUtil;
import com.google.gson.Gson;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.support.v7.widget.RecyclerView.*;

public class AddresManagerActivity extends AppCompatActivity {


    private final String TAGF = getClass().getSimpleName();
    public static final String INTENT_EXTRA = "AddresManagerActivity";
    private static final String TAG = "AddresManagerActivity";
    //进入添加地址页面的请求码
    private static final int NEW_ADDRESS_REQUEST_CODE = 105;

    Toolbar address_toolbar;
    TextView tv_toolbar_center_text;
    RecyclerView address_recycle;
    Button btn_address_add_new;
    AddressAdapter addressAdapter;


    List<AddressBean> addressBeanList = new ArrayList<>();
    OkHttpClient okHttpclient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addres_manager);

//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        Integer a = bundle.getInt(INTENT_EXTRA,102);

        setToolbar();
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAddressFromServer();
    }

    private void initData() {

        getAddressFromServer();

        address_recycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ItemDecoration itemDecoration = new ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
                super.getItemOffsets(outRect, view, parent, state);
                view.setBackgroundColor(Color.parseColor("#CDC0B0"));
                outRect.set(0, 0, 0, 20);
            }
        };
        address_recycle.addItemDecoration(itemDecoration);

    }


    private void setToolbar() {
        address_toolbar = (Toolbar) findViewById(R.id.address_toolbar);
        setSupportActionBar(address_toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_arrows);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        tv_toolbar_center_text = (TextView) findViewById(R.id.tv_toolbar_center_text);
        address_recycle = (RecyclerView) findViewById(R.id.address_recycle);
        btn_address_add_new = (Button) findViewById(R.id.btn_address_add_new);
        btn_address_add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddresManagerActivity.this, EditAndNewAddressActivity.class);
                startActivityForResult(intent, NEW_ADDRESS_REQUEST_CODE);
            }
        });
    }

    private void getAddressFromServer() {

        okHttpclient = new OkHttpClient();
        Request request = new Request.Builder().url(URLCollection.CHECK_ADDRESS).addHeader("cookie", UserStatusUtil
                .getInstanse().getCookie()).build();
        Call call = okHttpclient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + "个人地址请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: " + "个人地址请求成功");
                String address = response.body().string();
                Gson gson = new Gson();
                AllAddress allAddress = gson.fromJson(address, AllAddress.class);
                Log.d(TAG, "onResponse: " + address);
//                 Log.d(TAG, "onResponse: "+allAddress.getAddressMsgs().size());
                Message message = handler.obtainMessage();
                message.what = GET_ADDRESS_SUCCESS;
                message.obj = allAddress;
                handler.sendMessage(message);
            }
        });
    }

    private final int GET_ADDRESS_SUCCESS = 1000;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what) {
                case GET_ADDRESS_SUCCESS:

                    AllAddress all = (AllAddress) msg.obj;
                    addressBeanList = all.getAddressMsgs();
                    if (addressBeanList == null) {
                        break;
                    }
//                    Log.d(TAG, "handleMessage: "+addressBeanList.get(0).getConsigneeAddress());
                    addressAdapter = new AddressAdapter(addressBeanList, AddresManagerActivity.this);
                    address_recycle.setAdapter(addressAdapter);

                    addressAdapter.setAddressItemOnClickListener(new AddressAdapter.LinearAddressItemOnClickListener() {
                        @Override
                        public void onClick(AddressBean bean, int position) {
                            Log.d("getConsigneeAddress", bean.getConsigneeAddress());
                            Toast.makeText(AddresManagerActivity.this, bean.getConsigneeAddress() + position + "",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.putExtra("Address", bean.getConsigneeAddress());
                            intent.putExtra("id", bean.getId());
                            setResult(CommonMethod.ADDRESS_RESULT, intent);
                            finish();
                            overridePendingTransition(R.anim.translation_animation_back_in, R.anim
                                    .translation_animation_back_out);
                        }
                    });

                    break;
                default:
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_ADDRESS_REQUEST_CODE && resultCode == RESULT_OK){
            getAddressFromServer();
        }
    }
}
