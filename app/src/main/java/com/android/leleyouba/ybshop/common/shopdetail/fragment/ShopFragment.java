package com.android.leleyouba.ybshop.common.shopdetail.fragment;


import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.common.shopdetail.activity.ShopDetailActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * 商品界面
 */
public class ShopFragment extends Fragment implements View.OnClickListener {

//    RecyclerView shop_recycle_veiw;


    ViewPager vp_shop_top_imgs;

    TextView tv_shop_current_page, tv_shop_total_page;
    TextView tv_shop_simple_info, tv_red_shop_simmple_info, tv_price_shop_simple_info;
    Button btn_lose_price_shop_simple_info;
    TextView tv_select_shop_info;
    ImageButton btn_select_more;
    TextView tv_address_send;
    TextView tv_address_supplier_method, tv_address_send_time;
    ImageButton btn_address_more;
    TextView tv_freight;
    LinearLayout linear_store_after_sale, linear_evalutation_more;
    TextView tv_shop_evalutation_count, tv_shop_well_evalutation_percent;
    ImageView img_shop_more_evalutation;
    LinearLayout linear_img_evalutation;//晒图相册
    TextView tv_img_evalutation_count;
    LinearLayout linear_shop_store_info;
    ImageView img_shop_store;
    TextView tv_shop_store_name, tv_all_level, tv_shop_store_simple_info, tv_shop_wares_level,
            tv_shop_attention_count, tv_servers_level, tv_shop_all_wares_count, tv_shop_logistics_level,
            tv_store_dynamic_state_count;

    LinearLayout linear_attach_to_store, linear_shop_select, linear_send_address, linear_shop_simple_info;


    RecyclerView recycle_shop_three_short_evalutation;

    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);


//        setRecycleView(view);

        initView(view);

        addSomeData();

        return view;

    }


    private void initView(View view) {

        vp_shop_top_imgs = (ViewPager) view.findViewById(R.id.vp_shop_top_imgs);

        List<String> list = new ArrayList<>();

        list.add("http://pic11.nipic.com/20101214/213291_155243023914_2.jpg");
        list.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
        list.add("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg");
        list.add("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg");


        MyAdapter my = new MyAdapter(list);
        vp_shop_top_imgs.setAdapter(my);
        tv_shop_current_page = (TextView) view.findViewById(R.id.tv_shop_current_page);
        tv_shop_total_page = (TextView) view.findViewById(R.id.tv_shop_total_page);
        tv_shop_simple_info = (TextView) view.findViewById(R.id.tv_shop_simple_info);
        tv_red_shop_simmple_info = (TextView) view.findViewById(R.id.tv_red_shop_simmple_info);
        tv_price_shop_simple_info = (TextView) view.findViewById(R.id.tv_price_shop_simple_info);
        tv_select_shop_info = (TextView) view.findViewById(R.id.tv_select_shop_info);
        tv_address_send = (TextView) view.findViewById(R.id.tv_address_send);
        tv_address_supplier_method = (TextView) view.findViewById(R.id.tv_address_supplier_method);
        tv_address_send_time = (TextView) view.findViewById(R.id.tv_address_send_time);
        tv_freight = (TextView) view.findViewById(R.id.tv_freight);
        tv_shop_evalutation_count = (TextView) view.findViewById(R.id.tv_shop_evalutation_count);
        tv_shop_well_evalutation_percent = (TextView) view.findViewById(R.id.tv_shop_well_evalutation_percent);
        tv_img_evalutation_count = (TextView) view.findViewById(R.id.tv_img_evalutation_count);
        tv_shop_store_name = (TextView) view.findViewById(R.id.tv_shop_store_name);
        tv_all_level = (TextView) view.findViewById(R.id.tv_all_level);
        tv_shop_store_simple_info = (TextView) view.findViewById(R.id.tv_shop_store_simple_info);
        tv_shop_wares_level = (TextView) view.findViewById(R.id.tv_shop_wares_level);
        tv_shop_attention_count = (TextView) view.findViewById(R.id.tv_shop_attention_count);
        tv_servers_level = (TextView) view.findViewById(R.id.tv_servers_level);
        tv_shop_all_wares_count = (TextView) view.findViewById(R.id.tv_shop_all_wares_count);
        tv_shop_logistics_level = (TextView) view.findViewById(R.id.tv_shop_logistics_level);
        tv_store_dynamic_state_count = (TextView) view.findViewById(R.id.tv_store_dynamic_state_count);

        btn_lose_price_shop_simple_info = (Button) view.findViewById(R.id.btn_lose_price_shop_simple_info);

        btn_select_more = (ImageButton) view.findViewById(R.id.btn_select_more);
        btn_address_more = (ImageButton) view.findViewById(R.id.btn_address_more);

        linear_store_after_sale = (LinearLayout) view.findViewById(R.id.linear_store_after_sale);
        linear_evalutation_more = (LinearLayout) view.findViewById(R.id.linear_evalutation_more);
        linear_img_evalutation = (LinearLayout) view.findViewById(R.id.linear_img_evalutation);
        linear_shop_store_info = (LinearLayout) view.findViewById(R.id.linear_shop_store_info);
        linear_attach_to_store = (LinearLayout) view.findViewById(R.id.linear_attach_to_store);
        linear_shop_select = (LinearLayout) view.findViewById(R.id.linear_shop_select);
        linear_send_address = (LinearLayout) view.findViewById(R.id.linear_send_address);
        linear_shop_simple_info = (LinearLayout) view.findViewById(R.id.linear_shop_simple_info);


        img_shop_more_evalutation = (ImageView) view.findViewById(R.id.img_shop_more_evalutation);
        img_shop_store = (ImageView) view.findViewById(R.id.img_shop_store);

        tv_red_shop_simmple_info.setOnClickListener(this);
        linear_store_after_sale.setOnClickListener(this);
        linear_evalutation_more.setOnClickListener(this);
        linear_img_evalutation.setOnClickListener(this);
        linear_shop_store_info.setOnClickListener(this);
        linear_attach_to_store.setOnClickListener(this);
        linear_shop_select.setOnClickListener(this);
        linear_send_address.setOnClickListener(this);

        linear_shop_simple_info.setOnClickListener(this);
        btn_lose_price_shop_simple_info.setOnClickListener(this);

        tv_shop_total_page.setText(list.size() + "");
        tv_shop_current_page.setText("1");
        vp_shop_top_imgs.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentPage = position + 1;
                tv_shop_current_page.setText(currentPage + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        recycle_shop_three_short_evalutation = (RecyclerView) view.findViewById(R.id
                .recycle_shop_three_short_evalutation);


        aboutThreeEvalutation();

    }


    /**
     * 填充的假数据
     */
    private void addSomeData() {

        tv_shop_simple_info.setText("联想（lenovo）小心310经典版15.6英寸笔记本电脑（i5-8200U 4G 1T+128G SSD 2G独显 office2016FHD");

        tv_red_shop_simmple_info.setText("【时尚外观 高清大屏】双硬盘版本 为快不破！ 银色外观时尚吸睛！ 15.6英寸高清屏！");

        tv_select_shop_info.setText("新310经典版【i5-7200U 双硬盘】1件 增值保障");
        tv_address_send.setText("陕西西安市户县五竹镇西安石油大学（新校区）");
        tv_address_supplier_method.setText("现货");
        tv_address_send_time.setText("23:00前下单，预计明天（03月08日）送达");
        tv_freight.setText("在线支付运费6元");

        Glide.with(getContext()).load("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=640531349," +
                "162567&fm=58&s=3102547E6BEA07280C9E6C830300E0EA").asBitmap().into(img_shop_store);
        tv_shop_store_name.setText("西安蓝鸥");
        tv_shop_store_simple_info.setText("腾讯网十年最具创新力IT教育品牌!国内最大的App开发培训基地,专注HTML5培训、Android开发培训、Unity " +
                "3d培训、Web安全攻防培训、UI培训、VR/AR技术培训,选择蓝鸥");
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        ShopDetailActivity activity = (ShopDetailActivity) getActivity();

        switch (v.getId()) {

            case R.id.tv_red_shop_simmple_info:
                //商品简介中部分粉红色字体的点击

                break;
            case R.id.linear_store_after_sale:
                //售后 付款的横向布局点击，点击后展示该商品支持的售后和退换政策

                break;
            case R.id.linear_evalutation_more:
                //更多评论的点击，跳转到评论页

                activity.setCurrentTab(2);

                break;
            case R.id.linear_img_evalutation:
                //晒图评论的点击  跳转到晒图评论
                activity.setCurrentTab(2);


                break;
            case R.id.linear_shop_store_info:
                //评论下面的商铺的点击，跳转到商铺界面

                break;
            case R.id.linear_attach_to_store:
                //评论下面的商铺的联系商家的点击

                break;
            case R.id.linear_shop_select:

                //商品的选择的点击

                break;
            case R.id.linear_send_address:
                //发货地址的选择


                break;

            case R.id.linear_shop_simple_info:

                //商品简介的点击，跳转到详情界面


                activity.setCurrentTab(1);
                break;
            case R.id.btn_lose_price_shop_simple_info:

                Toast.makeText(getContext(), "降价了", Toast.LENGTH_SHORT).show();
                break;

        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {


        super.setUserVisibleHint(isVisibleToUser);
    }


    private void aboutThreeEvalutation() {

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycle_shop_three_short_evalutation.setLayoutManager(manager);


        List<List<String>> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            List<String> mlist = new ArrayList<>();
            if (i == 1){
                list.add(mlist);
                continue;
            }

            mlist.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
            mlist.add("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg");
            mlist.add("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg");
            mlist.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
            mlist.add("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg");
            mlist.add("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg");
            mlist.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
            mlist.add("http://pic.58pic.com/58pic/11/79/85/13t58PICsap.jpg");
            mlist.add("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg");
            list.add(mlist);
        }


        List<Map<String, String>> textList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {

            Map<String, String> textMap = new HashMap<>();
            textMap.put("aksssad", "是的发送到快来发货速度快解放后收到了看风景圣诞快乐发送登录的沙发斯蒂芬是的发送到是打发士大夫撒打发第三方过得好公司快递费激光焊接上岛咖啡光电开关");
            textList.add(textMap);
        }


        SimpleRecycleViewAdapter adapter = new SimpleRecycleViewAdapter(list, textList);
        recycle_shop_three_short_evalutation.setAdapter(adapter);

    }

    /**
     * 一级RecycleView的adapter
     */
    static class SimpleRecycleViewAdapter extends RecyclerView.Adapter<SimpleRecycleViewAdapter.MainHolder> {


        List<List<String>> list;
        List<Map<String, String>> textList;

        public SimpleRecycleViewAdapter(List<List<String>> list, List<Map<String, String>> textList) {
            this.list = list;
            this.textList = textList;
        }


        @Override
        public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.evalutation_simple_layout, parent,
                    false);
            MainHolder holder = new MainHolder(view,parent.getContext());
            return holder;
        }

        @Override
        public void onBindViewHolder(MainHolder holder, int position) {

            Map<String, String> textMap = textList.get(position);

            for (Map.Entry<String, String> vo : textMap.entrySet()) {
                String key = vo.getKey();
                String value = vo.getValue();
                holder.nickName.setText(key);
                holder.evalutation.setText(value);
            }
            holder.ratingbar.setRating((float) position);

            List<String> imgList = list.get(position);

            if (imgList.size() > 0) {
                holder.recycleView.setVisibility(View.VISIBLE);

                LinearLayoutManager manager = new LinearLayoutManager(holder.context,LinearLayoutManager.HORIZONTAL,false);

                ImageAdapter adapter = new ImageAdapter(imgList);
                holder.recycleView.addItemDecoration(new RecyclerView.ItemDecoration() {

                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                        super.getItemOffsets(outRect, view, parent, state);
                        outRect.set(5,5,5,5);
                    }
                });
                holder.recycleView.setLayoutManager(manager);
                holder.recycleView.setAdapter(adapter);
            }else {

                holder.recycleView.setVisibility(View.GONE);
//              ViewGroup.LayoutParams l = holder.recycleView.getLayoutParams();
//                l.height = 0;
//                holder.recycleView.setLayoutParams(l);
            }


        }

        /**
         * Returns the total number of items in the data set held by the adapter.
         *
         * @return The total number of items in this adapter.
         */
        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MainHolder extends RecyclerView.ViewHolder {

            RatingBar ratingbar;
            RecyclerView recycleView;
            TextView evalutation, nickName;
            Context context;
            public MainHolder(View itemView,Context context) {
                super(itemView);
                this.context = context;
                ratingbar = (RatingBar) itemView.findViewById(R.id.rate_simple_evalutation);
                recycleView = (RecyclerView) itemView.findViewById(R.id.recycle_simple_evalutation_img);
                evalutation = (TextView) itemView.findViewById(R.id.tv_simple_evalutation_content);
                nickName = (TextView) itemView.findViewById(R.id.tv_simple_evalutation_user_nickname);
            }
        }
    }


    /**
     * 二级RecycleView的adapter
     */
    static class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {


        List<String> list;

        public ImageAdapter(List<String> list) {
            this.list = list;
        }


        @Override
        public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_evalutation,parent,false);

            ImageHolder hodler = new ImageHolder(view, parent.getContext());

            return hodler;
        }


        @Override
        public void onBindViewHolder(ImageHolder holder, int position) {

            String str = list.get(position);
            Glide.with(holder.context).load(str).asBitmap().into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ImageHolder extends RecyclerView.ViewHolder {

            Context context;

            ImageView imageView;

            public ImageHolder(View itemView, Context context) {
                super(itemView);
                this.context = context;
                imageView = (ImageView) itemView.findViewById(R.id.img_evalutation_content);
            }
        }
    }


    static class MyAdapter extends PagerAdapter {

        List<String> mlist;

        public MyAdapter(List<String> mlist) {
            this.mlist = mlist;
        }


        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return mlist.size();
        }

        /**
         * Determines whether a page View is associated with a specific key object
         * as returned by {@link #instantiateItem(ViewGroup, int)}. This method is
         * required for a PagerAdapter to function properly.
         *
         * @param view   Page View to check for association with <code>object</code>
         * @param object Object to check for association with <code>view</code>
         * @return true if <code>view</code> is associated with the key object <code>object</code>
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View v = LayoutInflater.from(container.getContext()).inflate(R.layout.image_test, null);

            ImageView im = (ImageView) v.findViewById(R.id.img);
            Glide.with(container.getContext()).load(mlist.get(position)).asBitmap().into(im);

            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }

    /**
     * 设置recycleView
     *
     * @param view
     */
//    private void setRecycleView(View view) {
//        shop_recycle_veiw = (RecyclerView) view.findViewById(R.id.shop_recycle_veiw);
//        //设置管理器
//        VirtualLayoutManager layoutManager = new VirtualLayoutManager(getContext());
//        shop_recycle_veiw.setLayoutManager(layoutManager);
//        //设置复用池的大小
////        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
////        shop_recycle_veiw.setRecycledViewPool(viewPool);
////        viewPool.setMaxRecycledViews(0,10);
//
//
//        List<LayoutHelper> helpers = new LinkedList<>();
//
//        LinearLayoutHelper vpHelper = new LinearLayoutHelper(5,1);
//        helpers.add(vpHelper);
//        LinearLayoutHelper infoHelper = new LinearLayoutHelper(5,1);
//        helpers.add(infoHelper);
//
//    }
//
//
}
