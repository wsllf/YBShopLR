package com.android.leleyouba.ybshop.mine;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.URLCollection;
import com.android.leleyouba.ybshop.mine.activity.AccountManagementActivity;
import com.android.leleyouba.ybshop.mine.activity.CommunicateActivity;
import com.android.leleyouba.ybshop.mine.activity.LoginActivity;
import com.android.leleyouba.ybshop.mine.activity.SettingActivity;
import com.android.leleyouba.ybshop.mine.adapter.RecommendAdapter;
import com.android.leleyouba.ybshop.mine.bean.PersonInformationBean;
import com.android.leleyouba.ybshop.mine.util.UserInfoSave;
import com.android.leleyouba.ybshop.mine.util.UserStatusUtil;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment implements View.OnClickListener {


    private static final String TAG = "MineFragment";
    OkHttpClient okHttpClient = new OkHttpClient();

    TextView detailTv;//账户管理的TV,未登录时不显示

    RelativeLayout rela_detail;//上面的用户布局容器
    CircleImageView mine_fragment_user_img,//大的用户图片
            toolbar_user_img;//小的用户图片
    TextView mine_fragment_user_nickname,//用户昵称，默认显示，登陆后默认为用户名
            mine_fragment_user_plus;//用户会员等级，默认x不显示
    ImageView mine_toolbar_communate,//交流按钮
            mine_toolbar_setting;//设置按钮
    RecyclerView mine_fragment_recycle;


    TextView tvPear,//有吧豆
            tvSaverTicket,//优惠券
            tvWhiteStrip,//白条
            tvVault,//金库
            tvCommodityAttention,//商品关注
            tvStoreAttention,//店铺关注
            tvBrowsingHistory;//浏览记录

    LinearLayout linearWaitPay,//等待付款
            linearWaitReceive,//等待收货
            linearWaitEvaluate,//等待评价
            linearAfterSale,//售后
            linearOrderForm,//订单
            linearPears,//豆
            linearSaverTicket,//优惠券
            linearWhiteStrip,//白条
            linearVault,//金库
            linearWallet,//钱包
            linearCommodityAttention,//商品关注
            linearStoreAttention,//店铺关注
            linearBrowserHistory,//浏览记录
            linearCustomerService,//客户服务
            linearActivity,//我的活动
            linearSupermarket,//超市
            linearRankList,//排行榜
            linearGetBean;//获取有吧豆
    ScrollView mine_scrollview;

    private static final int PERSON_INFO = 100;

    CollapsingToolbarLayout mCollapsingToolbarLayout;
    Toolbar mToolbar;
    private SwipeRefreshLayout refreshLayout;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);

        recommend();
    }

    @Override
    public void onResume() {
        super.onResume();
        mine_fragment_user_nickname.setFocusableInTouchMode(true);
        mine_fragment_user_nickname.requestFocus();
        mine_fragment_recycle.clearFocus();

        //请求个人信息
        if (UserStatusUtil.getInstanse().isLogin() && TextUtils.isEmpty(UserInfoSave.getInstace().getUsername())){
            checkPersonInfo();
        }

    }

    /**
     * 初始化控件
     *
     * @param view
     */

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.mine_fragment_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
        }
        mine_scrollview = (ScrollView) view.findViewById(R.id.mine_scrollview);

        detailTv = (TextView) view.findViewById(R.id.tv_detail);

        rela_detail = (RelativeLayout) view.findViewById(R.id.rela_detail);
        mine_fragment_user_img = (CircleImageView) view.findViewById(R.id.mine_fragment_user_img);

        mine_fragment_user_nickname = (TextView) view.findViewById(R.id.mine_fragment_user_nickname);
        mine_fragment_user_plus = (TextView) view.findViewById(R.id.mine_fragment_user_plus);
        mine_toolbar_communate = (ImageView) view.findViewById(R.id.mine_toolbar_communate);
        mine_toolbar_setting = (ImageView) view.findViewById(R.id.mine_toolbar_setting);
        mine_fragment_recycle = (RecyclerView) view.findViewById(R.id.mine_fragment_recycle);

        rela_detail.setOnClickListener(this);
        mine_fragment_user_plus.setOnClickListener(this);
        mine_toolbar_communate.setOnClickListener(this);
        mine_toolbar_setting.setOnClickListener(this);


        tvPear = (TextView) view.findViewById(R.id.tv_pears);//有吧豆
        tvSaverTicket = (TextView) view.findViewById(R.id.tv_saver_ticket);//优惠券
        tvWhiteStrip = (TextView) view.findViewById(R.id.tv_white_strip);//白条
        tvVault = (TextView) view.findViewById(R.id.tv_vault);//金库
        tvCommodityAttention = (TextView) view.findViewById(R.id.tv_commodity_attention);//商品关注
        tvStoreAttention = (TextView) view.findViewById(R.id.tv_store_attention);//店铺关注
        tvBrowsingHistory = (TextView) view.findViewById(R.id.tv_browsing_history);//浏览记录

        linearWaitPay = (LinearLayout) view.findViewById(R.id.linear_wait_pay);
        linearWaitReceive = (LinearLayout) view.findViewById(R.id.linear_wait_receive);
        linearWaitEvaluate = (LinearLayout) view.findViewById(R.id.linear_wait_evaluate);
        linearAfterSale = (LinearLayout) view.findViewById(R.id.linear_after_sale);
        linearOrderForm = (LinearLayout) view.findViewById(R.id.linear_order_form);
        linearPears = (LinearLayout) view.findViewById(R.id.linear_pears);
        linearSaverTicket = (LinearLayout) view.findViewById(R.id.linear_saver_ticket);
        linearWhiteStrip = (LinearLayout) view.findViewById(R.id.linear_white_strip);
        linearVault = (LinearLayout) view.findViewById(R.id.linear_vault);
        linearWallet = (LinearLayout) view.findViewById(R.id.linear_wallet);
        linearCommodityAttention = (LinearLayout) view.findViewById(R.id.linear_commodity_attention);
        linearStoreAttention = (LinearLayout) view.findViewById(R.id.linear_store_attention);
        linearBrowserHistory = (LinearLayout) view.findViewById(R.id.linear_browser_history);
        linearCustomerService = (LinearLayout) view.findViewById(R.id.linear_customer_service);
        linearActivity = (LinearLayout) view.findViewById(R.id.linear_activity);
        linearSupermarket = (LinearLayout) view.findViewById(R.id.linear_supermarket);
        linearRankList = (LinearLayout) view.findViewById(R.id.linear_rank_list);
        linearGetBean = (LinearLayout) view.findViewById(R.id.linear_get_bean);

        linearWaitPay.setOnClickListener(this);
        linearWaitReceive.setOnClickListener(this);
        linearWaitEvaluate.setOnClickListener(this);
        linearAfterSale.setOnClickListener(this);
        linearOrderForm.setOnClickListener(this);
        linearPears.setOnClickListener(this);
        linearSaverTicket.setOnClickListener(this);
        linearWhiteStrip.setOnClickListener(this);
        linearVault.setOnClickListener(this);
        linearWallet.setOnClickListener(this);
        linearCommodityAttention.setOnClickListener(this);
        linearStoreAttention.setOnClickListener(this);
        linearBrowserHistory.setOnClickListener(this);
        linearCustomerService.setOnClickListener(this);
        linearActivity.setOnClickListener(this);
        linearSupermarket.setOnClickListener(this);
        linearRankList.setOnClickListener(this);
        linearGetBean.setOnClickListener(this);


        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
//        refreshLayout.setProgressBackgroundColorSchemeResource(R.color.colorAccent);
        refreshLayout.setColorSchemeResources(R.color.red);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新中请求个人信息

                if (UserStatusUtil.getInstanse().isLogin()){
                    checkPersonInfo();
                }

            }
        });


    }


    /**
     * 为你推荐相关
     */

    private void recommend() {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            list.add("sss" + i);
        }
//        mine_fragment_recycle

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        mine_fragment_recycle.setLayoutManager(manager);
        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);

            }
        };
        mine_fragment_recycle.addItemDecoration(itemDecoration);

        RecommendAdapter adapter = new RecommendAdapter(getActivity(), list);
        mine_fragment_recycle.setAdapter(adapter);

    }


    /**
     * 控件的点击
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rela_detail:
                if (UserStatusUtil.getInstanse().isLogin()) {
                    startActivity(new Intent(getActivity(), AccountManagementActivity.class));
                    getActivity().overridePendingTransition(R.anim.translation_animation_enter_in, R.anim.translation_animation_enter_out);

                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().overridePendingTransition(R.anim.translation_animation_enter_in, R.anim.translation_animation_enter_out);

                }
                break;
            case R.id.mine_fragment_user_plus:
                Toast.makeText(getActivity(), "用户会员", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_toolbar_communate:
                if (UserStatusUtil.getInstanse().isLogin()) {
                    startActivity(new Intent(getActivity(), CommunicateActivity.class));
                    getActivity().overridePendingTransition(R.anim.translation_animation_enter_in, R.anim.translation_animation_enter_out);

                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().overridePendingTransition(R.anim.translation_animation_enter_in, R.anim.translation_animation_enter_out);

                }

                break;
            case R.id.mine_toolbar_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                getActivity().overridePendingTransition(R.anim.translation_animation_enter_in, R.anim.translation_animation_enter_out);

                break;


            case R.id.linear_wait_pay:
                Toast.makeText(getActivity(), "待支付", Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_wait_receive:
                break;
            case R.id.linear_wait_evaluate:
                break;
            case R.id.linear_after_sale:
                break;
            case R.id.linear_order_form:
                break;
            case R.id.linear_pears:
                break;
            case R.id.linear_saver_ticket:
                break;
            case R.id.linear_white_strip:
                break;
            case R.id.linear_vault:
                break;
            case R.id.linear_wallet:
                break;
            case R.id.linear_commodity_attention:
                break;
            case R.id.linear_store_attention:
                break;
            case R.id.linear_browser_history:
                break;
            case R.id.linear_customer_service:
                break;
            case R.id.linear_activity:
                break;
            case R.id.linear_supermarket:
                break;
            case R.id.linear_rank_list:
                break;
            case R.id.linear_get_bean:
                break;


            default:
                break;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        mine_fragment_user_nickname.setFocusableInTouchMode(true);
        mine_fragment_user_nickname.requestFocus();
    }


    /**
     * 登录完成，查询个人信息
     */

    public void checkPersonInfo(){

        Request request = new Request.Builder()
                .addHeader("cookie",UserStatusUtil.getInstanse().getCookie())
                .url(URLCollection.CHECK_USER_MESSAGE)
                .build();

      okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: "+"查询个人信息失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: "+"查询个人信息成功");
                String msg = response.body().string();
                Log.d(TAG, "onResponse: "+msg);

                Message message = handler.obtainMessage(PERSON_INFO);
                message.obj = msg;
                handler.sendMessage(message);



            }
        });

    }


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case PERSON_INFO:
                    String personiinfo = (String) msg.obj;
                    anilyseData(personiinfo);
                    break;
                default:
                    break;
            }



        }
    };

    private void anilyseData(String personiinfo) {

        try {
            PersonInformationBean bean = new PersonInformationBean();

            JSONObject jsonObject = new JSONObject(personiinfo);
            JSONArray jsonArray = jsonObject.getJSONArray("PersonalMsgs");
            JSONObject json = jsonArray.getJSONObject(0);
            if (json.getString("address") != null){
                bean.setAddress(null);
            }
            if (json.getString("answer") != null){
                bean.setAnswer(json.getString("answer"));
            }
            if (json.getString("birth") != null){
                bean.setBirth(json.getString("birth"));
            }
            if (json.getString("id") != null){
                bean.setId(json.getString("id"));
            }
            if (json.getString("nickname") != null){
                bean.setNickname(json.getString("nickname"));
            }
            if (json.getString("phoneNum") != null){
                bean.setPhoneNum(json.getString("phoneNum"));
            }
            if (json.getString("photo") != null){
                bean.setPhoto(json.getString("photo"));
            }
            if (json.getString("question") != null){
                bean.setQuestion(json.getString("question"));
            }
            if (json.getString("sex") != null){
                bean.setSex(json.getString("sex"));
            }
            if (json.getString("username") != null){
                bean.setUsername(json.getString("username"));
            }
            if (bean != null){
                detailTv.setVisibility(View.VISIBLE);
            }
            if (bean.getNickname() != null){
                mine_fragment_user_nickname.setText(bean.getNickname());
            }else if (bean.getUsername() != null){
                mine_fragment_user_nickname.setText(bean.getUsername());
            }

            if (bean.getPhoto() != null){
                Glide.with(getContext()).load(bean.getPhoto()).asBitmap().placeholder(R.mipmap.apple).error(R.mipmap.apple).into(mine_fragment_user_img);
            }

         UserInfoSave userInfoSave =  UserInfoSave.getInstace();
            userInfoSave.setQuestion(bean.getQuestion());
            userInfoSave.setPhoto(bean.getPhoto());
            userInfoSave.setPhoneNum(bean.getPhoneNum());
            userInfoSave.setAddress(bean.getAddress());
            userInfoSave.setAnswer(bean.getAnswer());
            userInfoSave.setBirth(bean.getBirth());
            userInfoSave.setId(bean.getId());
            userInfoSave.setNickname(bean.getNickname());
            userInfoSave.setUsername(bean.getUsername());
            userInfoSave.setSex(bean.getSex());

            Log.d(TAG, "onResponse: "+bean.getAnswer()+bean.getUsername()+bean.getPhoto());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
