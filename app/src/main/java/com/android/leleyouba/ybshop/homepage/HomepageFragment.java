package com.android.leleyouba.ybshop.homepage;


import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


import com.android.leleyouba.ybshop.R;
import com.android.leleyouba.ybshop.URLCollection;
import com.android.leleyouba.ybshop.common.shopdetail.activity.ShopDetailActivity;
import com.android.leleyouba.ybshop.homepage.adapter.HomepageClassifyAdapter;
import com.android.leleyouba.ybshop.homepage.adapter.HomepageGalleryAdapter;
import com.android.leleyouba.ybshop.homepage.bean.HomePageSecKillBean;
import com.android.leleyouba.ybshop.homepage.bean.HomepageBaseClassifyBean;
import com.android.leleyouba.ybshop.homepage.bean.HomepageClassifyBean;
import com.android.leleyouba.ybshop.homepage.bean.HomepageLunboBean;
import com.android.leleyouba.ybshop.homepage.bean.HomepageTitleBean;
import com.android.leleyouba.ybshop.homepage.bean.HomepageViewFlipperBean;
import com.android.leleyouba.ybshop.homepage.util.AlphaTitleScrollView;
import com.android.leleyouba.ybshop.homepage.util.PicsView;
import com.android.leleyouba.ybshop.mine.activity.CommunicateActivity;
import com.android.leleyouba.ybshop.mine.adapter.RecommendAdapter;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomepageFragment extends Fragment implements View.OnClickListener {
    LinearLayout ll_homepage_title;
    SearchView search_homepage_show;//搜索框
    ImageView iv_homepage_communate;//消息中心
    PicsView pic_homepage_adv;

    private String[] imgStr = new String[]{
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490060209&di=cfd280840f3b78e671173bcf1a98d183&imgtype=jpg&er=1&src=http%3A%2F%2Fpic3.16pic.com%2F00%2F03%2F82%2F16pic_382826_b.jpg",
            "http://pic.qiantucdn.com/58pic/12/74/84/44D58PICAwc.jpg!/format/webp",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489465726492&di=cf18806dbb53c41194675e892460988b&imgtype=0&src=http%3A%2F%2Fpic1.cxtuku.com%2F00%2F10%2F10%2Fb3222276957d.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489465776531&di=cf70a0d5eca0901cd0e68370ef87ad89&imgtype=0&src=http%3A%2F%2Fpic30.nipic.com%2F20130607%2F4380757_083106640359_2.jpg"
    };


    RelativeLayout rl_homepage_timeLimit;//限时抢购的布局
    TextView tv_homepage_timePart;//几点场部分
    TextView tv_homepage_timeHour;//小时
    TextView tv_homepage_timeMinute;//分钟
    TextView tv_homepage_timeSecond;//秒钟
    TextView tv_homepage_timeTitle;//场标题
    String screenings = "";//判断是几点场次
    ViewFlipper vf_homepage_viewflipper;//快报内容
    TextView tv_homepage_viewflipper_express, tv_homepage_viewflipper_more;//快报

    public HomepageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);

        //设置下拉刷新，上拉加载
//        setRefreshAndLoad(view);
        initView(view);
        //请求轮播图数据
//        requestDataWithLunBo();
        //设置轮播图
        setPicsView();
        //设置快报
        setExpress(view);

        //设置限时抢购
        setTimeLimit(view);
        //设置画廊
        setGallery(view);
        //设置发现好货
        setFourLayout(view);
        //设置 爱生活 享品质 购特色 逛商场
        setRecycleBaseClassify(view);
        //设置为你推荐
        setRecommend(view);
        return view;
    }

    private void initView(View view) {
        pic_homepage_adv = (PicsView) view.findViewById(R.id.pic_homepage_adv);
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 20) {
                ArrayList<String> lunboList = (ArrayList<String>) msg.obj;
//                setPicsView(lunboList);
            }
            return false;
        }
    });

    /**
     * 请求轮播图数据
     */
    private void requestDataWithLunBo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(URLCollection.HOMEPAGE_LUNBOPIC).build();
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String lunBoStr = response.body().string();
                        Gson gson = new Gson();
                        HomepageLunboBean homepageLunboBeans = gson.fromJson(lunBoStr, HomepageLunboBean.class);
                        ArrayList<HomepageLunboBean.LunboListBean> lunboListBeans = (ArrayList<HomepageLunboBean.LunboListBean>) homepageLunboBeans.getLunboList();
                        Log.d("lunboListBeans", homepageLunboBeans.getLunboList().size() + "");
                        ArrayList<String> lunboList = new ArrayList<String>();
                        for (int i = 0; i < lunboListBeans.size(); i++) {
                            HomepageLunboBean.LunboListBean lunboListBean = lunboListBeans.get(i);
                            lunboList.add(lunboListBean.getImgurl());
                        }
                        Message msg = new Message();
                        msg.what = 20;
                        msg.obj = lunboList;
                        handler.sendMessage(msg);

                    } else {
                        throw new IOException("Unexpected code" + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     * 设置下拉刷新上拉加载
     *
     * @param view
     */
    private void setRefreshAndLoad(View view) {
        final AlphaTitleScrollView ats_homepage_show = (AlphaTitleScrollView) view.findViewById(R.id.ats_homepage_show);
//        final SwipeRefreshLayout swipe_Refresh_homepage = (SwipeRefreshLayout) view.findViewById(R.id.swipe_Refresh_homepage);
//        //设置下拉进度的背景颜色
//        swipe_Refresh_homepage.setColorSchemeResources(R.color.red, R.color.colorPrimaryDark, R.color.colorPrimary, R.color.colorAccent);
//
//        swipe_Refresh_homepage.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                ll_homepage_title.setVisibility(View.GONE);
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getContext(), "刷新数据", Toast.LENGTH_SHORT).show();
//
//                        //加载完数据设置为不刷新状态
//                        swipe_Refresh_homepage.setRefreshing(false);
//                        ll_homepage_title.setVisibility(View.VISIBLE);
//                    }
//                }, 1200);
//            }
//        });


    }

    /**
     * 设置快报
     *
     * @param view
     */
    private void setExpress(View view) {
        tv_homepage_viewflipper_express = (TextView) view.findViewById(R.id.tv_homepage_viewflipper_express);
        tv_homepage_viewflipper_more = (TextView) view.findViewById(R.id.tv_homepage_viewflipper_more);
        vf_homepage_viewflipper = (ViewFlipper) view.findViewById(R.id.vf_homepage_viewflipper);
        tv_homepage_viewflipper_express.setOnClickListener(this);
        tv_homepage_viewflipper_more.setOnClickListener(this);
        vf_homepage_viewflipper.setOnClickListener(this);
        //准备数据
        ArrayList<HomepageViewFlipperBean> vfBeans = new ArrayList<HomepageViewFlipperBean>();
        vfBeans.add(new HomepageViewFlipperBean("荐", "爸妈爱的“白”娃娃，真是孕期吃出来的吗？"));
        vfBeans.add(new HomepageViewFlipperBean("大促", "如果徒步真的需要理由，十四个够不够？"));
        vfBeans.add(new HomepageViewFlipperBean("抢", "享受清爽啤酒的同时，这些常识你真的了解吗？"));
        vfBeans.add(new HomepageViewFlipperBean("荐", "三星Galaxy S8定型图无悬念"));
        vfBeans.add(new HomepageViewFlipperBean("大促", "家猫为何如此高冷？"));
        vfBeans.add(new HomepageViewFlipperBean("抢", "有吧满100减88！！！"));

        for (int i = 0; i < vfBeans.size(); i++) {
            View vfView = View.inflate(getContext(), R.layout.homepage_viewflipper_express, null);
            TextView tv_viewflipper_recommend = (TextView) vfView.findViewById(R.id.tv_viewflipper_recommend);
            TextView tv_viewflipper_content = (TextView) vfView.findViewById(R.id.tv_viewflipper_content);
            tv_viewflipper_recommend.setText(vfBeans.get(i).getRecommendStr().toString());
            tv_viewflipper_content.setText(vfBeans.get(i).getContentStr().toString());
            tv_viewflipper_content.setOnClickListener(this);
            vf_homepage_viewflipper.addView(vfView);
        }
    }

    /**
     * 设置限时抢购
     *
     * @param view
     */
    private void setTimeLimit(View view) {
        rl_homepage_timeLimit = (RelativeLayout) view.findViewById(R.id.rl_homepage_timeLimit);
        tv_homepage_timePart = (TextView) view.findViewById(R.id.tv_homepage_timePart);
        tv_homepage_timeHour = (TextView) view.findViewById(R.id.tv_homepage_timeHour);
        tv_homepage_timeMinute = (TextView) view.findViewById(R.id.tv_homepage_timeMinute);
        tv_homepage_timeSecond = (TextView) view.findViewById(R.id.tv_homepage_timeSecond);
        tv_homepage_timeTitle = (TextView) view.findViewById(R.id.tv_homepage_timeTitle);

        //获取系统时间
        getSystemTime();

    }

    /**
     * 获取系统时间
     */
    private void getSystemTime() {
        //获取系统时间
        SimpleDateFormat hour = new SimpleDateFormat("HH");
        SimpleDateFormat minute = new SimpleDateFormat("mm");
        SimpleDateFormat second = new SimpleDateFormat("ss");
        hour.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        minute.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        second.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String hourStr = hour.format(new Date());
        String minuteStr = minute.format(new Date());
        String secondStr = second.format(new Date());
        Log.d("SystemTime", "hourStr:" + hourStr + "minuteStr:" + minuteStr + "secondStr:" + secondStr);
        //判断是几点场
        final String screening = judgeScreenings(Integer.parseInt(hourStr));
        tv_homepage_timePart.setText(screening + "点场");
        long scr = Integer.parseInt(screening);
        Log.d("screening", scr + "");
        long millisInFuture = 7200000 - ((Integer.parseInt(hourStr) - scr) * 3600 + Integer.parseInt(minuteStr) * 60 + Integer.parseInt(secondStr)) * 1000;
        //参数为倒计时的总时间millisInFuture和间隔时间countDownInterval
        CountDownTimer timer = new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long hour = millisUntilFinished / 1000 / 3600;
                long minute = millisUntilFinished / 1000 % 3600 / 60;
                long second = millisUntilFinished / 1000 % 3600 % 60;
                if (hour < 10 && hour >= 0) {
                    tv_homepage_timeHour.setText("0" + hour);
                } else {
                    tv_homepage_timeHour.setText("" + hour);
                }
                if (minute < 10 && minute >= 0) {
                    tv_homepage_timeMinute.setText("0" + minute);
                } else {
                    tv_homepage_timeMinute.setText("" + minute);
                }
                if (second < 10 && second >= 0) {
                    tv_homepage_timeSecond.setText("0" + second);
                } else {
                    tv_homepage_timeSecond.setText("" + second);
                }

            }

            @Override
            public void onFinish() {
                tv_homepage_timePart.setText(screening + "点场");
            }
        }.start();

    }

    /**
     * 判断是几点场次
     *
     * @param hour
     */
    private String judgeScreenings(long hour) {

        if (hour >= 0 && hour < 2) {
            screenings = "0";
        }
        if (hour >= 2 && hour < 4) {
            screenings = "2";
        }
        if (hour >= 4 && hour < 6) {
            screenings = "4";
        }
        if (hour >= 6 && hour < 8) {
            screenings = "6";
        }
        if (hour >= 8 && hour < 10) {
            screenings = "8";
        }
        if (hour >= 10 && hour < 12) {
            screenings = "10";
        }
        if (hour >= 12 && hour < 14) {
            screenings = "12";
        }
        if (hour >= 14 && hour < 16) {
            screenings = "14";
        }
        if (hour >= 16 && hour < 18) {
            screenings = "16";
        }
        if (hour >= 18 && hour < 20) {
            screenings = "18";
        }
        if (hour >= 20 && hour < 22) {
            screenings = "20";
        }
        if (hour >= 22 && hour < 24) {
            screenings = "22";
        }
        return screenings;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //toolBar透明度渐变
        AlphaTitleScrollView scrollView = (AlphaTitleScrollView) view.findViewById(R.id.ats_homepage_show);
        ll_homepage_title = (LinearLayout) view.findViewById(R.id.ll_homepage_title);
        ImageView iv_homepage_adv = (ImageView) view.findViewById(R.id.iv_homepage_adv);
        Glide.with(getContext()).load("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3015042362,1805604445&fm=23&gp=0.jpg")
                .into(iv_homepage_adv);
        scrollView.setTitleAndHead(ll_homepage_title, iv_homepage_adv);
        ll_homepage_title.getBackground().setAlpha(0);

        search_homepage_show = (SearchView) view.findViewById(R.id.search_homepage_show);
        search_homepage_show.clearFocus();
        iv_homepage_communate = (ImageView) view.findViewById(R.id.iv_homepage_communate);
        search_homepage_show.setOnClickListener(this);
        iv_homepage_communate.setOnClickListener(this);
        iv_homepage_adv.setOnClickListener(this);
    }

    /**
     * 设置轮播图
     */
    private void setPicsView() {

        List<ImageView> imgList = new ArrayList<>();
        String titles[] = {"1", "2", "3"};
        for (int i = 0; i < titles.length; i++) {
            ImageView iv = new ImageView(getContext());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(getContext()).load(imgStr[i]).into(iv);
            imgList.add(iv);
        }
        //初始化数据
        pic_homepage_adv.setTilesAndImages(titles, imgList);
        pic_homepage_adv.setTvTitleVisibility(View.GONE);
        // 设置点击事件
        pic_homepage_adv.setOnLunBoClickListener(new PicsView.OnLunBoClickListener() {

            @Override
            public void clickLunbo(int position) {
                System.out.println("点击有效");
                Toast.makeText(getContext(), "点击有效，位置为：" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 设置画廊
     *
     * @param view
     */
    private void setGallery(View view) {
        String[] gallery = {"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490068459&di=9d8b43fdcf4e2a7259bf8071d3d704d9&imgtype=jpg&er=1&src=http%3A%2F%2Fm.qqzhi.com%2Fupload%2Fimg_5_1893005491D3867961214_23.jpg",
                "https://f11.baidu.com/it/u=1773370537,2404250031&fm=72",
                "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=873149991,360816307&fm=23&gp=0.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490068459&di=9d8b43fdcf4e2a7259bf8071d3d704d9&imgtype=jpg&er=1&src=http%3A%2F%2Fm.qqzhi.com%2Fupload%2Fimg_5_1893005491D3867961214_23.jpg",
                "https://f11.baidu.com/it/u=1773370537,2404250031&fm=72",
                "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=873149991,360816307&fm=23&gp=0.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490068459&di=9d8b43fdcf4e2a7259bf8071d3d704d9&imgtype=jpg&er=1&src=http%3A%2F%2Fm.qqzhi.com%2Fupload%2Fimg_5_1893005491D3867961214_23.jpg",
                "https://f11.baidu.com/it/u=1773370537,2404250031&fm=72",
                "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=873149991,360816307&fm=23&gp=0.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490068459&di=9d8b43fdcf4e2a7259bf8071d3d704d9&imgtype=jpg&er=1&src=http%3A%2F%2Fm.qqzhi.com%2Fupload%2Fimg_5_1893005491D3867961214_23.jpg",
                "https://f11.baidu.com/it/u=1773370537,2404250031&fm=72",
                "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=873149991,360816307&fm=23&gp=0.jpg",
        };
        RecyclerView recycle_homepage_gallery = (RecyclerView) view.findViewById(R.id.recycle_homepage_gallery);
        //设置布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycle_homepage_gallery.setLayoutManager(manager);
        ArrayList<HomePageSecKillBean> secKillBeans = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            secKillBeans.add(new HomePageSecKillBean(gallery[i], "￥1000" + i, "￥2000" + i));
        }
        HomepageGalleryAdapter galleryAdapter = new HomepageGalleryAdapter(getContext(), secKillBeans);
        recycle_homepage_gallery.setAdapter(galleryAdapter);
    }


    @Override
    public void onPause() {
        //停止图片轮播
        pic_homepage_adv.stopAutoScroll();
        super.onPause();
    }

    @Override
    public void onResume() {
        //开启图片轮播
        pic_homepage_adv.startAutoScroll();
        super.onResume();
    }

    /**
     * 获取屏宽
     *
     * @return
     */
    private int getScreenWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    /**
     * 设置四个布局 发现好货 优品专辑  排行榜 品牌头条 闪购
     *
     * @param view
     */
    private void setFourLayout(View view) {
        RelativeLayout rl_homepage_find = (RelativeLayout) view.findViewById(R.id.rl_homepage_find);
        RelativeLayout rl_homepage_album = (RelativeLayout) view.findViewById(R.id.rl_homepage_album);
        RelativeLayout rl_homepage_rank = (RelativeLayout) view.findViewById(R.id.rl_homepage_rank);
        LinearLayout ll_homepage_brand = (LinearLayout) view.findViewById(R.id.ll_homepage_brand);
        LinearLayout ll_homepage_purchase = (LinearLayout) view.findViewById(R.id.ll_homepage_purchase);
        rl_homepage_find.setOnClickListener(this);
        rl_homepage_album.setOnClickListener(this);
        rl_homepage_rank.setOnClickListener(this);
        ll_homepage_brand.setOnClickListener(this);
        ll_homepage_purchase.setOnClickListener(this);
        TextView tv_homepage_find_left = (TextView) view.findViewById(R.id.tv_homepage_find_left);
        TextView tv_homepage_find_right = (TextView) view.findViewById(R.id.tv_homepage_find_right);
        TextView tv_homepage_album_left = (TextView) view.findViewById(R.id.tv_homepage_album_left);
        TextView tv_homepage_album_right = (TextView) view.findViewById(R.id.tv_homepage_album_right);
        TextView tv_homepage_rank = (TextView) view.findViewById(R.id.tv_homepage_rank);
        TextView tv_homepage_brand = (TextView) view.findViewById(R.id.tv_homepage_brand);
        TextView tv_homepage_purchase = (TextView) view.findViewById(R.id.tv_homepage_purchase);
        ImageView iv_homepage_find = (ImageView) view.findViewById(R.id.iv_homepage_find);
        ImageView iv_homepage_album = (ImageView) view.findViewById(R.id.iv_homepage_album);
        ImageView iv_homepage_rank = (ImageView) view.findViewById(R.id.iv_homepage_rank);
        ImageView iv_homepage_brand = (ImageView) view.findViewById(R.id.iv_homepage_brand);
        ImageView iv_homepage_purchase = (ImageView) view.findViewById(R.id.iv_homepage_purchase);
    }

    int carouseId1[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    int carouseId2[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    int carouseId3[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    int carouseId4[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    String carouseId_1[] = {"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2835212338,3969829694&fm=23&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3216694337,554764305&fm=23&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2835212338,3969829694&fm=23&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3216694337,554764305&fm=23&gp=0.jpg"};
    String carouseId_2[] = {"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2835212338,3969829694&fm=23&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3216694337,554764305&fm=23&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2835212338,3969829694&fm=23&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3216694337,554764305&fm=23&gp=0.jpg"};
    String carouseId_3[] = {"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2835212338,3969829694&fm=23&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3216694337,554764305&fm=23&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2835212338,3969829694&fm=23&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3216694337,554764305&fm=23&gp=0.jpg"};
    String carouseId_4[] = {"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2835212338,3969829694&fm=23&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3216694337,554764305&fm=23&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2835212338,3969829694&fm=23&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3216694337,554764305&fm=23&gp=0.jpg"};
    int titlesId[] = {R.mipmap.ay0, R.mipmap.ay0, R.mipmap.ay0, R.mipmap.axy};
    String titleStr[] = {"爱生活", "享品质", "购特色", "逛商场"};
    String topBaseClassifyTitle1[] = {"玩3C", "有吧家电", "有吧超市", "爱家"};
    String topBaseClassifyTitle2[] = {"全球尖货", "有吧精选", "有吧体育", "奢品大牌"};
    String topBaseClassifyTitle3[] = {"智能生活", "有吧旅行", "爱车生活", "电影娱乐"};
    String topBaseClassifyTitle4[] = {"美妆馆", "大家电馆", "时尚馆", "手机数码"};

    String topBaseClassifyContent1[] = {"荣耀畅玩6X", "70时电视7999", "满199件减100", "宝宝的专业家纺"};
    String topBaseClassifyContent2[] = {"Beats头戴耳机", "进口纸尿裤直降", "大牌春上新", "百元直降"};
    String topBaseClassifyContent3[] = {"智能电动摩托", "9.9秒杀", "满499-120", "电影娱乐"};
    String topBaseClassifyContent4[] = {"跨品牌199-100", "满1500减150", "换新低至4折", "荣耀畅玩6X"};

    String topBaseClassifyDiscontent1[] = {"放量抢购", "", "", "折扣享不停"};
    String topBaseClassifyDiscontent2[] = {"", "妈妈心仪之选", "满600减50", ""};
    String topBaseClassifyDiscontent3[] = {"", "", "", ""};
    String topBaseClassifyDiscontent4[] = {"", "", "", "放量抢购"};


    int topBaseClassifyImgs1[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    int topBaseClassifyImgs2[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    int topBaseClassifyImgs3[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    int topBaseClassifyImgs4[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};


    String bottomBaseClassifyTitle1[] = {"爱宝宝", "爱美丽", "爱吃", "爱逛"};
    String bottomBaseClassifyTitle2[] = {"环球时尚", "珠宝首饰", "设计师", "达人精选"};
    String bottomBaseClassifyTitle3[] = {"清仓二手", "大药房", "陪伴宝宝", "有吧拍卖"};
    String bottomBaseClassifyTitle4[] = {"美食城", "电脑办公", "鞋靴箱包", "小家电馆"};

    String bottomBaseClassifyContent1[] = {"满199减50", "每满199-100", "低至99减40", "自营199-50"};
    String bottomBaseClassifyContent2[] = {"低至2折", "放饰美", "潮流设计精选", "早春时髦穿搭"};
    String bottomBaseClassifyContent3[] = {"9.9数码好货", "治疗脚气", "一元狂抢", "名家陶瓷抢拍"};
    String bottomBaseClassifyContent4[] = {"9.9超值抢", "399抢显示器", "女人节", "2件8.8折"};


    int bottomBaseClassifyImgs1[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    int bottomBaseClassifyImgs2[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    int bottomBaseClassifyImgs3[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    int bottomBaseClassifyImgs4[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    /**
     * 设置 爱生活 享品质 购特色 逛商场
     *
     * @param view
     */
    private void setRecycleBaseClassify(View view) {
        RecyclerView recycle_homepage_show = (RecyclerView) view.findViewById(R.id.recycle_homepage_show);
        //设置布局管理器
        recycle_homepage_show.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<HomepageClassifyBean> classifyBeans = new ArrayList<HomepageClassifyBean>();

        ArrayList<String> imgList_1 = new ArrayList<>();
        imgList_1.add(carouseId_1[0]);
        imgList_1.add(carouseId_1[1]);
        imgList_1.add(carouseId_1[2]);
        imgList_1.add(carouseId_1[3]);
        ArrayList<String> imgList_2 = new ArrayList<>();
        imgList_2.add(carouseId_2[0]);
        imgList_2.add(carouseId_2[1]);
        imgList_2.add(carouseId_2[2]);
        imgList_2.add(carouseId_2[3]);
        ArrayList<String> imgList_3 = new ArrayList<>();
        imgList_3.add(carouseId_3[0]);
        imgList_3.add(carouseId_3[1]);
        imgList_3.add(carouseId_3[2]);
        imgList_3.add(carouseId_3[3]);
        ArrayList<String> imgList_4 = new ArrayList<>();
        imgList_4.add(carouseId_4[0]);
        imgList_4.add(carouseId_4[1]);
        imgList_4.add(carouseId_4[2]);
        imgList_4.add(carouseId_4[3]);

        HomepageTitleBean titleBeens_1 = new HomepageTitleBean(titleStr[0], titlesId[0]);

        HomepageTitleBean titleBeens_2 = new HomepageTitleBean(titleStr[1], titlesId[1]);

        HomepageTitleBean titleBeens_3 = new HomepageTitleBean(titleStr[2], titlesId[2]);

        HomepageTitleBean titleBeens_4 = new HomepageTitleBean(titleStr[3], titlesId[3]);

        ArrayList<HomepageBaseClassifyBean> topBaseClassifyBeans_1 = new ArrayList<HomepageBaseClassifyBean>();

        topBaseClassifyBeans_1.add(new HomepageBaseClassifyBean.Builder().titleStr(topBaseClassifyTitle1[0]).contentStr(topBaseClassifyContent1[0]).discountStr(topBaseClassifyDiscontent1[0]).imgStr(topBaseClassifyImgs1[0]).build());
        topBaseClassifyBeans_1.add(new HomepageBaseClassifyBean.Builder().titleStr(topBaseClassifyTitle1[1]).contentStr(topBaseClassifyContent1[1]).discountStr(topBaseClassifyDiscontent1[1]).imgStr(topBaseClassifyImgs1[1]).build());
        topBaseClassifyBeans_1.add(new HomepageBaseClassifyBean.Builder().titleStr(topBaseClassifyTitle1[2]).contentStr(topBaseClassifyContent1[2]).discountStr(topBaseClassifyDiscontent1[2]).imgStr(topBaseClassifyImgs1[2]).build());
        topBaseClassifyBeans_1.add(new HomepageBaseClassifyBean.Builder().titleStr(topBaseClassifyTitle1[3]).contentStr(topBaseClassifyContent1[3]).discountStr(topBaseClassifyDiscontent1[3]).imgStr(topBaseClassifyImgs1[3]).build());

        ArrayList<HomepageBaseClassifyBean> topBaseClassifyBeans_2 = new ArrayList<HomepageBaseClassifyBean>();

        topBaseClassifyBeans_2.add(new HomepageBaseClassifyBean.Builder().titleStr(topBaseClassifyTitle2[0]).contentStr(topBaseClassifyContent2[0]).discountStr(topBaseClassifyDiscontent2[0]).imgStr(topBaseClassifyImgs2[0]).build());
        topBaseClassifyBeans_2.add(new HomepageBaseClassifyBean.Builder().titleStr(topBaseClassifyTitle2[1]).contentStr(topBaseClassifyContent2[1]).discountStr(topBaseClassifyDiscontent2[1]).imgStr(topBaseClassifyImgs2[1]).build());
        topBaseClassifyBeans_2.add(new HomepageBaseClassifyBean.Builder().titleStr(topBaseClassifyTitle2[2]).contentStr(topBaseClassifyContent2[2]).discountStr(topBaseClassifyDiscontent2[2]).imgStr(topBaseClassifyImgs2[2]).build());
        topBaseClassifyBeans_2.add(new HomepageBaseClassifyBean.Builder().titleStr(topBaseClassifyTitle2[3]).contentStr(topBaseClassifyContent2[3]).discountStr(topBaseClassifyDiscontent2[3]).imgStr(topBaseClassifyImgs2[3]).build());

        ArrayList<HomepageBaseClassifyBean> topBaseClassifyBeans_3 = new ArrayList<HomepageBaseClassifyBean>();

        topBaseClassifyBeans_3.add(new HomepageBaseClassifyBean.Builder().titleStr(topBaseClassifyTitle3[0]).contentStr(topBaseClassifyContent3[0]).discountStr(topBaseClassifyDiscontent3[0]).imgStr(topBaseClassifyImgs3[0]).build());
        topBaseClassifyBeans_3.add(new HomepageBaseClassifyBean.Builder().titleStr(topBaseClassifyTitle3[1]).contentStr(topBaseClassifyContent3[1]).discountStr(topBaseClassifyDiscontent3[1]).imgStr(topBaseClassifyImgs3[1]).build());
        topBaseClassifyBeans_3.add(new HomepageBaseClassifyBean.Builder().titleStr(topBaseClassifyTitle3[2]).contentStr(topBaseClassifyContent3[2]).discountStr(topBaseClassifyDiscontent3[2]).imgStr(topBaseClassifyImgs3[2]).build());
        topBaseClassifyBeans_3.add(new HomepageBaseClassifyBean.Builder().titleStr(topBaseClassifyTitle3[3]).contentStr(topBaseClassifyContent3[3]).discountStr(topBaseClassifyDiscontent3[3]).imgStr(topBaseClassifyImgs3[3]).build());

        ArrayList<HomepageBaseClassifyBean> topBaseClassifyBeans_4 = new ArrayList<HomepageBaseClassifyBean>();

        topBaseClassifyBeans_4.add(new HomepageBaseClassifyBean.Builder().titleStr(topBaseClassifyTitle4[0]).contentStr(topBaseClassifyContent4[0]).discountStr(topBaseClassifyDiscontent4[0]).imgStr(topBaseClassifyImgs4[0]).build());
        topBaseClassifyBeans_4.add(new HomepageBaseClassifyBean.Builder().titleStr(topBaseClassifyTitle4[1]).contentStr(topBaseClassifyContent4[1]).discountStr(topBaseClassifyDiscontent4[1]).imgStr(topBaseClassifyImgs4[1]).build());
        topBaseClassifyBeans_4.add(new HomepageBaseClassifyBean.Builder().titleStr(topBaseClassifyTitle4[2]).contentStr(topBaseClassifyContent4[2]).discountStr(topBaseClassifyDiscontent4[2]).imgStr(topBaseClassifyImgs4[2]).build());
        topBaseClassifyBeans_4.add(new HomepageBaseClassifyBean.Builder().titleStr(topBaseClassifyTitle4[3]).contentStr(topBaseClassifyContent4[3]).discountStr(topBaseClassifyDiscontent4[3]).imgStr(topBaseClassifyImgs4[3]).build());

        ArrayList<HomepageBaseClassifyBean> bootomBaseClassifyBeans_1 = new ArrayList<HomepageBaseClassifyBean>();

        bootomBaseClassifyBeans_1.add(new HomepageBaseClassifyBean.Builder().titleStr(bottomBaseClassifyTitle1[0]).contentStr(bottomBaseClassifyContent1[0]).imgStr(bottomBaseClassifyImgs1[0]).build());
        bootomBaseClassifyBeans_1.add(new HomepageBaseClassifyBean.Builder().titleStr(bottomBaseClassifyTitle1[1]).contentStr(bottomBaseClassifyContent1[1]).imgStr(bottomBaseClassifyImgs1[1]).build());
        bootomBaseClassifyBeans_1.add(new HomepageBaseClassifyBean.Builder().titleStr(bottomBaseClassifyTitle1[2]).contentStr(bottomBaseClassifyContent1[2]).imgStr(bottomBaseClassifyImgs1[2]).build());
        bootomBaseClassifyBeans_1.add(new HomepageBaseClassifyBean.Builder().titleStr(bottomBaseClassifyTitle1[3]).contentStr(bottomBaseClassifyContent1[3]).imgStr(bottomBaseClassifyImgs1[3]).build());

        ArrayList<HomepageBaseClassifyBean> bootomBaseClassifyBeans_2 = new ArrayList<HomepageBaseClassifyBean>();

        bootomBaseClassifyBeans_2.add(new HomepageBaseClassifyBean.Builder().titleStr(bottomBaseClassifyTitle2[0]).contentStr(bottomBaseClassifyContent2[0]).imgStr(bottomBaseClassifyImgs2[0]).build());
        bootomBaseClassifyBeans_2.add(new HomepageBaseClassifyBean.Builder().titleStr(bottomBaseClassifyTitle2[1]).contentStr(bottomBaseClassifyContent2[1]).imgStr(bottomBaseClassifyImgs2[1]).build());
        bootomBaseClassifyBeans_2.add(new HomepageBaseClassifyBean.Builder().titleStr(bottomBaseClassifyTitle2[2]).contentStr(bottomBaseClassifyContent2[2]).imgStr(bottomBaseClassifyImgs2[2]).build());
        bootomBaseClassifyBeans_2.add(new HomepageBaseClassifyBean.Builder().titleStr(bottomBaseClassifyTitle2[3]).contentStr(bottomBaseClassifyContent2[3]).imgStr(bottomBaseClassifyImgs2[3]).build());

        ArrayList<HomepageBaseClassifyBean> bootomBaseClassifyBeans_3 = new ArrayList<HomepageBaseClassifyBean>();

        bootomBaseClassifyBeans_3.add(new HomepageBaseClassifyBean.Builder().titleStr(bottomBaseClassifyTitle3[0]).contentStr(bottomBaseClassifyContent3[0]).imgStr(bottomBaseClassifyImgs3[0]).build());
        bootomBaseClassifyBeans_3.add(new HomepageBaseClassifyBean.Builder().titleStr(bottomBaseClassifyTitle3[1]).contentStr(bottomBaseClassifyContent3[1]).imgStr(bottomBaseClassifyImgs3[1]).build());
        bootomBaseClassifyBeans_3.add(new HomepageBaseClassifyBean.Builder().titleStr(bottomBaseClassifyTitle3[2]).contentStr(bottomBaseClassifyContent3[2]).imgStr(bottomBaseClassifyImgs3[2]).build());
        bootomBaseClassifyBeans_3.add(new HomepageBaseClassifyBean.Builder().titleStr(bottomBaseClassifyTitle3[3]).contentStr(bottomBaseClassifyContent3[3]).imgStr(bottomBaseClassifyImgs3[3]).build());

        ArrayList<HomepageBaseClassifyBean> bootomBaseClassifyBeans_4 = new ArrayList<HomepageBaseClassifyBean>();

        bootomBaseClassifyBeans_4.add(new HomepageBaseClassifyBean.Builder().titleStr(bottomBaseClassifyTitle4[0]).contentStr(bottomBaseClassifyContent4[0]).imgStr(bottomBaseClassifyImgs4[0]).build());
        bootomBaseClassifyBeans_4.add(new HomepageBaseClassifyBean.Builder().titleStr(bottomBaseClassifyTitle4[1]).contentStr(bottomBaseClassifyContent4[1]).imgStr(bottomBaseClassifyImgs4[1]).build());
        bootomBaseClassifyBeans_4.add(new HomepageBaseClassifyBean.Builder().titleStr(bottomBaseClassifyTitle4[2]).contentStr(bottomBaseClassifyContent4[2]).imgStr(bottomBaseClassifyImgs4[2]).build());
        bootomBaseClassifyBeans_4.add(new HomepageBaseClassifyBean.Builder().titleStr(bottomBaseClassifyTitle4[3]).contentStr(bottomBaseClassifyContent4[3]).imgStr(bottomBaseClassifyImgs4[3]).build());


        classifyBeans.add(new HomepageClassifyBean(imgList_1, topBaseClassifyBeans_1, bootomBaseClassifyBeans_1, titleBeens_1));
        classifyBeans.add(new HomepageClassifyBean(imgList_2, topBaseClassifyBeans_2, bootomBaseClassifyBeans_2, titleBeens_2));
        classifyBeans.add(new HomepageClassifyBean(imgList_3, topBaseClassifyBeans_3, bootomBaseClassifyBeans_3, titleBeens_3));
        classifyBeans.add(new HomepageClassifyBean(imgList_4, topBaseClassifyBeans_4, bootomBaseClassifyBeans_4, titleBeens_4));


        //准备适配器
        HomepageClassifyAdapter classifyAdapter = new HomepageClassifyAdapter(classifyBeans, getContext());
        recycle_homepage_show.setAdapter(classifyAdapter);

    }

    /**
     * 设置为你推荐
     *
     * @param view
     */
    private void setRecommend(View view) {
        RecyclerView recycle_homepage_recommend = (RecyclerView) view.findViewById(R.id.recycle_homepage_recommend);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            list.add("sss" + i);
        }

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recycle_homepage_recommend.setLayoutManager(manager);
        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);

            }
        };
        recycle_homepage_recommend.addItemDecoration(itemDecoration);

        RecommendAdapter adapter = new RecommendAdapter(getActivity(), list);
        recycle_homepage_recommend.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_homepage_show:

                break;
            case R.id.iv_homepage_communate:
                startActivity(new Intent(getContext(), CommunicateActivity.class));
                getActivity().overridePendingTransition(R.anim.translation_animation_enter_in, R.anim.translation_animation_enter_out);
                break;
            case R.id.rl_homepage_timeLimit:
                Toast.makeText(getContext(), "有吧限时抢购", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_homepage_viewflipper_express:
                Toast.makeText(getContext(), "跳转到有吧快报", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_homepage_viewflipper_more:
                Toast.makeText(getContext(), "跳转到有吧快报", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_viewflipper_content:
                Toast.makeText(getContext(), "跳转到对应的快报内容", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_homepage_find:
                Toast.makeText(getContext(), "跳转到发现好货", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_homepage_album:
                Toast.makeText(getContext(), "跳转到优品专辑", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_homepage_rank:
                Toast.makeText(getContext(), "跳转到排行榜", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_homepage_brand:
                Toast.makeText(getContext(), "跳转到品牌头条", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_homepage_purchase:
                Toast.makeText(getContext(), "跳转到闪购", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_homepage_adv:
                startActivity(new Intent(getContext(), ShopDetailActivity.class));
                getActivity().overridePendingTransition(R.anim.translation_animation_enter_in, R.anim.translation_animation_enter_out);
                Toast.makeText(getContext(), "跳转详情", Toast.LENGTH_SHORT).show();
                break;


        }
    }
}
