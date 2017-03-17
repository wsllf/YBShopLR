package com.android.leleyouba.ybshop.shoppingtrolley;


import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.leleyouba.ybshop.MainActivity;
import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.URLCollection;
import com.android.leleyouba.ybshop.common.shopdetail.activity.ShopDetailActivity;
import com.android.leleyouba.ybshop.mine.activity.CommunicateActivity;
import com.android.leleyouba.ybshop.mine.activity.LoginActivity;
import com.android.leleyouba.ybshop.mine.adapter.RecommendAdapter;
import com.android.leleyouba.ybshop.mine.util.UserStatusUtil;
import com.android.leleyouba.ybshop.shoppingtrolley.activity.OrderActivity;
import com.android.leleyouba.ybshop.shoppingtrolley.adapter.SectionShopCarAdapter;
import com.android.leleyouba.ybshop.shoppingtrolley.bean.AllGoods;
import com.android.leleyouba.ybshop.shoppingtrolley.bean.GoodsInfo;
import com.android.leleyouba.ybshop.shoppingtrolley.bean.ShopCarModel;
import com.android.leleyouba.ybshop.shoppingtrolley.bean.ShopCarSection;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingtrolleyFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ShoppingtrolleyFragment";

    LinearLayout ll_shoppingtrolley_isEmpty;
    TextView tvTotalSelect,//选中商品的个数
            tvTotalPrice;//选中商品的价格
    CheckBox bottomCheckboxOk,//当toolbar的按钮处于完成状态底部的全选按钮
            bottomCheckboxEdit;//当toolbar的按钮处于编辑状态底部的全选按钮
    Toolbar shopToolbar;//toolbar
    ImageView ivCommunicationShop;//toolbar最右边的交流按钮，点击跳转到消息中心
    Button btnEdit,//toolbar上的切换编辑状态的按钮
            btnEditShare,//处于编辑状态的最下边的分享按钮
            btnEditMovetoAttention,//处于编辑状态的最下边的移入关注按钮
            btnEditDelete;//处于编辑状态的最下边的删除按钮
    RecyclerView recommendRecycle, shopRecycle;//显示订单和为你推荐的recycleView
    RelativeLayout relaOk;//未编辑状态的最下边的结算视图
    LinearLayout linearPay,//未编辑状态的最下边的去结算视图
            linearEdit;//编辑状态的最下边的操作视图

    ScrollView nest_shop_scroll;
    LinearLayout tvStickyHeaderView;


    AllGoods cartMsgs;
    OkHttpClient okhttpClient;

    public ShoppingtrolleyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shoppingtrolley, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cartMsgs = new AllGoods();
        okhttpClient = new OkHttpClient();
        //判断是否登录
        judgeIsLogin(view);

        initView(view);


    }


    private void startInitData(final View view) {
        Request request = new Request.Builder().addHeader("cookie", UserStatusUtil.getInstanse().getCookie()).url
                (URLCollection.SHOPPING_SELECTCART).build();
        Call call = okhttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        AllGoods cartMsgs = gson.fromJson(str, AllGoods.class);
//                        Log.d(TAG, "run: " +str+cartMsgs.getCartMsgs().size()+cartMsgs);

                        //分类

                        if (cartMsgs == null || cartMsgs.getCartMsgs() == null){
                            return;
                        }

                        Map<String, ArrayList<GoodsInfo>> map = new HashMap<String, ArrayList<GoodsInfo>>();

                        for (GoodsInfo goodsInfo : cartMsgs.getCartMsgs()) {
                            String str = goodsInfo.getStoreName();
                            if (map.get(str) != null) {
                                ArrayList<GoodsInfo> goodsList = map.get(str);
                                goodsList.add(goodsInfo);
                                map.put(str, goodsList);
                            } else {
                                ArrayList<GoodsInfo> goodList = new ArrayList<GoodsInfo>();
                                goodList.add(goodsInfo);
                                map.put(str, goodList);
                            }
                        }
                        aboutRecycleView(view, map);

                    }
                });

            }
        });


    }


    private void judgeIsLogin(View view) {
        LinearLayout ll_shoppingtrolley_isLogin = (LinearLayout) view.findViewById(R.id.ll_shoppingtrolley_isLogin);
        Button btn_shoppingtrolley_login = (Button) view.findViewById(R.id.btn_shoppingtrolley_login);
        Button btn_shoppingtrolley_recommend = (Button) view.findViewById(R.id.btn_shoppingtrolley_recommend);
        btn_shoppingtrolley_login.setOnClickListener(this);
        btn_shoppingtrolley_recommend.setOnClickListener(this);
        if (UserStatusUtil.getInstanse().isLogin()) {
            ll_shoppingtrolley_isLogin.setVisibility(View.GONE);
            //如果已经登录，则显示添加的物品
            startInitData(view);
        } else {
            ll_shoppingtrolley_isLogin.setVisibility(View.VISIBLE);
        }

    }


    private void initView(View view) {

        shopToolbar = (Toolbar) view.findViewById(R.id.shop_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(shopToolbar);
        ActionBar actionbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayShowTitleEnabled(false);
        }
        ivCommunicationShop = (ImageView) view.findViewById(R.id.iv_communication_shop);
        btnEdit = (Button) view.findViewById(R.id.btn_edit);
        bottomCheckboxOk = (CheckBox) view.findViewById(R.id.bottom_checkbox_ok);
        tvTotalPrice = (TextView) view.findViewById(R.id.tv_total_price);
        tvTotalSelect = (TextView) view.findViewById(R.id.tv_total_select);
        linearPay = (LinearLayout) view.findViewById(R.id.linear_pay);
        relaOk = (RelativeLayout) view.findViewById(R.id.rela_ok);
        shopRecycle = (RecyclerView) view.findViewById(R.id.shop_recycle);
        linearEdit = (LinearLayout) view.findViewById(R.id.linear_edit);
        bottomCheckboxEdit = (CheckBox) view.findViewById(R.id.bottom_checkbox_edit);
        btnEditShare = (Button) view.findViewById(R.id.btn_edit_share);
        btnEditMovetoAttention = (Button) view.findViewById(R.id.btn_edit_moveto_attention);
        btnEditDelete = (Button) view.findViewById(R.id.btn_edit_delete);


        recommendRecycle = (RecyclerView) view.findViewById(R.id.recommend_recycle);
        nest_shop_scroll = (ScrollView) view.findViewById(R.id.nest_shop_scroll);
        nest_shop_scroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        btnEdit.setOnClickListener(this);
        ivCommunicationShop.setOnClickListener(this);
        linearPay.setOnClickListener(this);
        btnEditShare.setOnClickListener(this);
        btnEditMovetoAttention.setOnClickListener(this);
        btnEditDelete.setOnClickListener(this);


        bottomCheckboxOk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!UserStatusUtil.getInstanse().isLogin()){
                    return;
                }

                setAllSelected(isChecked);
            }
        });

        bottomCheckboxEdit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setEditAllSelected(isChecked);
            }
        });


//        aboutRecycleView(view);
    }

    private void setAllSelected(boolean isChecked) {
        adapter.selectAll(isChecked);
    }

    private void setEditAllSelected(boolean isChecked) {
        adapter.selectAll(isChecked);
    }

    private List<ShopCarSection> list;

    SectionShopCarAdapter adapter;

    private void aboutRecycleView(View view, Map<String, ArrayList<GoodsInfo>> map) {

        list = new ArrayList<>();

        Set<String> stores = map.keySet();
        for (String storeName : stores) {


            list.add(new ShopCarSection(true, storeName, true));
            ArrayList<GoodsInfo> goodsinfos = map.get(storeName);
            for (GoodsInfo goodsInfo : goodsinfos) {

                list.add(new ShopCarSection(new ShopCarModel(goodsInfo.getBrief_infor(), goodsInfo.getCount(),
                        goodsInfo.getFileFileName(), goodsInfo.getGoodsId(), goodsInfo.getGoodsName(), goodsInfo
                        .getGoodsType(), goodsInfo.getId(), goodsInfo.getPrice(), goodsInfo.getSeller_id(), goodsInfo
                        .getStoreName(), goodsInfo.getU_id(), false)));
            }

        }


//        list.add(new ShopCarSection(true, "任涛", true));
//        list.add(new ShopCarSection(new ShopCarModel("http://pic11.nipic.com/20101214/213291_155243023914_2.jpg",
// "任涛1", new Random().nextFloat() * 100, false)));
//        list.add(new ShopCarSection(new ShopCarModel("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg",
// "任涛2", new Random().nextFloat() * 100, false)));
//        list.add(new ShopCarSection(new ShopCarModel("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg", "任涛3",
// new Random().nextFloat() * 100, false)));
//        list.add(new ShopCarSection(new ShopCarModel("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg",
// "任涛4", new Random().nextFloat() * 100, false)));
//        list.add(new ShopCarSection(true, "李博", true));
//        list.add(new ShopCarSection(new ShopCarModel("http://pic4.nipic.com/20091121/3764872_215617048242_2.jpg",
// "李博1", new Random().nextFloat() * 100, false)));
//        list.add(new ShopCarSection(new ShopCarModel("http://img01.taopic.com/141128/240418-14112P9345826.jpg",
// "李博2", new Random().nextFloat() * 100, false)));
//        list.add(new ShopCarSection(new ShopCarModel("http://img1.3lian.com/2015/a1/113/d/10.jpg", "李博3", new
// Random().nextFloat() * 100, false)));
//        list.add(new ShopCarSection(true, "范立鹏", true));
//        list.add(new ShopCarSection(new ShopCarModel("http://img.taopic
// .com/uploads/allimg/121203/267873-121203225I999.jpg", "范立鹏1", new Random().nextFloat() * 100, false)));
//        list.add(new ShopCarSection(new ShopCarModel("http://pic15.nipic.com/20110617/2707401_222720447000_2.jpg",
// "范立鹏2", new Random().nextFloat() * 100, false)));
//        list.add(new ShopCarSection(true, "薛佳伟", true));
//        list.add(new ShopCarSection(new ShopCarModel("http://pic.58pic.com/58pic/13/86/90/43A58PICuUn_1024.jpg",
// "薛佳伟1", new Random().nextFloat() * 100, false)));
//        list.add(new ShopCarSection(new ShopCarModel("http://pic.58pic.com/58pic/13/87/72/73t58PICjpT_1024.jpg",
// "薛佳伟2", new Random().nextFloat() * 100, false)));
//        list.add(new ShopCarSection(true, "爱是可敬的", true));
//        list.add(new ShopCarSection(new ShopCarModel("http://pic.58pic.com/58pic/13/71/30/15r58PIChmX_1024.jpg",
// "爱是可敬的1", new Random().nextFloat() * 100, false)));
//        list.add(new ShopCarSection(new ShopCarModel("http://img.taopic
// .com/uploads/allimg/121126/240453-1211261I64263.jpg", "爱是可敬的2", new Random().nextFloat() * 100, false)));

        //判断购物车是否为空
        judgeIsEmpty(view);
        adapter = new SectionShopCarAdapter(R.layout.shopping_trolley_wares, R.layout.shop_trolley_header_view, list);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        shopRecycle.setLayoutManager(manager);
        shopRecycle.setAdapter(adapter);
        ImageView im = new ImageView(getActivity());
        im.setImageResource(R.mipmap.apple);
        adapter.setEmptyView(im);
        ImageView im1 = new ImageView(getActivity());
        im1.setImageResource(R.mipmap.apple);
        adapter.addFooterView(im1);
        shopRecycle.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                ShopCarSection section = list.get(position);
                if (section.isHeader) {
                    Toast.makeText(getActivity(), position + "店铺名" + section.header, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), position + "店铺名" + section.t.getStoreName(), Toast.LENGTH_SHORT)
                            .show();
                }


            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//            Toast.makeText(getContext(), "onItemChildClick" + position, Toast.LENGTH_LONG).show();
                ShopCarSection section = list.get(position);
                switch (view.getId()) {

                    case R.id.tv_header_store_name:

                        Toast.makeText(getActivity(), position + "店铺名" + section.header, Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.tv_isPrivilege:

                        Toast.makeText(getActivity(), position + "去凑单" + section.header, Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.tv_shop_trolley_wares_price:
                        Toast.makeText(getActivity(), "onItemChildClick" + position + "商品价格", Toast.LENGTH_LONG).show();

                        break;
                    case R.id.tv_shop_trolley_wares_info:
                        Toast.makeText(getActivity(), "onItemChildClick" + position + "商品信息", Toast.LENGTH_LONG).show();

                        break;
                    case R.id.iv_shop_trolley_wares:
                        Toast.makeText(getActivity(), "onItemChildClick" + position + "商品图片", Toast.LENGTH_LONG).show();

                        break;
                    case R.id.real_shop_content:

                        Intent intent = new Intent(getActivity(), ShopDetailActivity.class);
                        startActivity(intent);
                        break;

//                case R.id.btn_shop_num:
//                    Toast.makeText(getContext(), "onItemChildClick" + position+"商品数量", Toast.LENGTH_LONG).show();
//
//                    break;

                }
            }

            @Override
            public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemLongClick(adapter, view, position);

                Toast.makeText(getActivity(), "删除" + position, Toast.LENGTH_SHORT).show();

            }
        });


        recommend();
    }

    /**
     * 判断购物车是否为空
     *
     * @param view
     */
    private void judgeIsEmpty(View view) {
        ll_shoppingtrolley_isEmpty = (LinearLayout) view.findViewById(R.id.ll_shoppingtrolley_isEmpty);
        if (list.size() == 0) {
            ll_shoppingtrolley_isEmpty.setVisibility(View.VISIBLE);
            shopRecycle.setVisibility(View.GONE);

        } else {
            ll_shoppingtrolley_isEmpty.setVisibility(View.GONE);
            shopRecycle.setVisibility(View.VISIBLE);

        }

    }

//    private void aboutScrollREcycleView(RecyclerView recyclerView, int dx, int dy) {
//
//        // 找到RecyclerView的item中，和RecyclerView的getTop 向下相距5个像素的那个item
//        // (尝试2、3个像素位置都找不到，所以干脆用了5个像素)，
//        // 我们根据这个item，来更新吸顶布局的内容，
//        // 因为我们的StickyLayout展示的信息肯定是最上面的那个item的信息.
//        View stickyInfoView = recyclerView.findChildViewUnder(tvStickyHeaderView.getMeasuredWidth() / 2, 5);
//        if (stickyInfoView != null && stickyInfoView.getContentDescription() != null) {
//            ((TextView)tvStickyHeaderView.findViewById(R.id.tv_header_store_name)).setText(String.valueOf
// (stickyInfoView.getContentDescription()));
//        }
//
//        // 找到固定在屏幕上方那个FakeStickyLayout下面一个像素位置的RecyclerView的item，
//        // 我们根据这个item来更新假的StickyLayout要translate多少距离.
//        // 并且只处理HAS_STICKY_VIEW和NONE_STICKY_VIEW这两种tag，
//        // 因为第一个item的StickyLayout虽然展示，但是一定不会引起FakeStickyLayout的滚动.
//        View transInfoView = recyclerView.findChildViewUnder(
//                tvStickyHeaderView.getMeasuredWidth() / 2, tvStickyHeaderView.getMeasuredHeight() + 1);
//
//        if (transInfoView != null && transInfoView.getTag() != null) {
//            int transViewStatus = (int) transInfoView.getTag();
//            int dealtY = transInfoView.getTop() - tvStickyHeaderView.getMeasuredHeight();
//
//            // 如果当前item需要展示StickyLayout，
//            // 那么根据这个item的getTop和FakeStickyLayout的高度相差的距离来滚动FakeStickyLayout.
//            // 这里有一处需要注意，如果这个item的getTop已经小于0，也就是滚动出了屏幕，
//            // 那么我们就要把假的StickyLayout恢复原位，来覆盖住这个item对应的吸顶信息.
//            if (transViewStatus == ShoppingTrolleyWaresViewHolder.HAS_STICKY_VIEW) {
//                if (transInfoView.getTop() > 0) {
//                    tvStickyHeaderView.setTranslationY(dealtY);
//                } else {
//                    tvStickyHeaderView.setTranslationY(0);
//                }
//            } else if (transViewStatus == ShoppingTrolleyWaresViewHolder.NONE_STICKY_VIEW) {
//                // 如果当前item不需要展示StickyLayout，那么就不会引起FakeStickyLayout的滚动.
//                tvStickyHeaderView.setTranslationY(0);
//            }
//        }
//    }


//    private void checkBoxAbout() {
//        bottomCheckboxOk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (allCheckedChangedListenter != null){
//                    allCheckedChangedListenter.allCheckedChanged(isChecked);
//                }
//
//            }
//        });
//
//    }


//    public interface AllCheckedChangedListenter{
//
//       void allCheckedChanged(boolean isChecked);
//    }
//
//    private AllCheckedChangedListenter allCheckedChangedListenter;
//
//    public AllCheckedChangedListenter getAllCheckedChangedListenter() {
//        return allCheckedChangedListenter;
//    }
//
//    public void setAllCheckedChangedListenter(AllCheckedChangedListenter allCheckedChangedListenter) {
//        this.allCheckedChangedListenter = allCheckedChangedListenter;
//    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit:
                if (btnEdit.getText().toString().equals("编辑")) {
                    btnEdit.setText("完成");
                    relaOk.setVisibility(View.GONE);
                    linearEdit.setVisibility(View.VISIBLE);
                } else {
                    btnEdit.setText("编辑");
                    relaOk.setVisibility(View.VISIBLE);
                    linearEdit.setVisibility(View.GONE);
                }

                break;
            case R.id.iv_communication_shop:
                Intent intent = new Intent(getActivity(), CommunicateActivity.class);
                startActivity(intent);
                break;

            case R.id.linear_pay:
                float total = 0;
                for (ShopCarModel model : SectionShopCarAdapter.selectShops) {
                    total += Float.parseFloat(model.getPrice());
                }
                if (UserStatusUtil.getInstanse().isLogin()) {
                    Toast.makeText(getActivity(), "去结算:选中了" + SectionShopCarAdapter.selectShops.size() + "件商品" +
                            total + "元", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(getActivity(), OrderActivity.class);
                    startActivity(intent1);
                    getActivity().overridePendingTransition(R.anim.translation_animation_enter_in, R.anim
                            .translation_animation_enter_out);
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().overridePendingTransition(R.anim.translation_animation_enter_in, R.anim
                            .translation_animation_enter_out);
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_edit_share:
                Toast.makeText(getActivity(), "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_edit_moveto_attention:
                Toast.makeText(getActivity(), "移入关注", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_edit_delete:
                Toast.makeText(getActivity(), "删除", Toast.LENGTH_SHORT).show();


                list.clear();
                adapter.notifyDataSetChanged();

                adapter.removeSelectShop();


                linearEdit.setVisibility(View.GONE);

                break;
            case R.id.btn_shoppingtrolley_login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().overridePendingTransition(R.anim.translation_animation_enter_in, R.anim
                        .translation_animation_enter_out);
                break;
            case R.id.btn_shoppingtrolley_recommend:
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().overridePendingTransition(R.anim.translation_animation_enter_in, R.anim
                        .translation_animation_enter_out);
                break;
            default:
                break;
        }
    }

    private void recommend() {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            list.add("sss" + i);
        }
//        mine_fragment_recycle

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recommendRecycle.setLayoutManager(manager);
        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);

            }
        };
        recommendRecycle.addItemDecoration(itemDecoration);

        RecommendAdapter adapter = new RecommendAdapter(getActivity(), list);
        recommendRecycle.setAdapter(adapter);


    }

}
