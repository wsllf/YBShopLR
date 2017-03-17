package com.android.leleyouba.ybshop.homepage.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.leleyouba.ybshop.R;

/**
 * 图片轮播
 * <p>
 * 指示器
 * <p>
 * 图片指示器联动
 * <p>
 * 定时轮播
 */
public class PicsView extends RelativeLayout {


    private Context context;//上下文
    //准备数据集合
    List<ImageView> mListDatas = new ArrayList<ImageView>();
    String[] titles;
    //容器对象
    ViewPager mViewPager;
    //适配器对象
    MyAdapter mMyAdapter;
    //小白点的容器
    LinearLayout mPointContainer;
    //小白点容器中的textView
    TextView mTvTitle;
    LinearLayout mLl;
    //设置标识
    boolean isScrolling = false;

    Handler handler;
    //自动滑动的对象
    AutoScrollTask mAutoScrollTask;
    //轮播的监听事件
    private OnLunBoClickListener onLunBoClickListener;

    public PicsView(Context context) {
        super(context, null);
    }

    /**
     * 多参的构造方法
     *
     * @param context
     * @param attrs
     */
    public PicsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        //创建从子线程到主线程传递消息的工具对象
        handler = new Handler();

        //初始化视图
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        //获取单个View的布局视图
        View.inflate(context, R.layout.pics_view, this);
        //初始化创建容器对象
        mViewPager = (ViewPager) findViewById(R.id.pager);
        //获取设置小白点的布局
        mPointContainer = (LinearLayout) findViewById(R.id.point_container);
        //获取textView对象
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        //获取布局、
        mLl = (LinearLayout) findViewById(R.id.ll);
    }

    private void initData() {
        //初始化数据
        if (mListDatas != null) {
            for (int i = 0; i < mListDatas.size(); i++) {
                //添加白点
                //实例化的方式创建自定义视图对象
                View point = new View(context);
                //为自定义视图设置内容
                point.setBackgroundResource(R.drawable.point_normal);
                //为自定义视图设置宽高
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
                if (i != 0) {
                    params.leftMargin = 10;
                } else {
                    //当选中第一个视图的时候，设置小白点为选中状态
                    point.setBackgroundResource(R.drawable.point_selected);
                    //当选中第一个视图的时候，设置textView的内容
//                    mTvTitle.setText(titles[i]);
                }
                //将自定义的视图添加到对应的布局上
                mPointContainer.addView(point, params);
            }
        }
        //准备适配器
        mMyAdapter = new MyAdapter();
        //为容器设置适配器
        mViewPager.setAdapter(mMyAdapter);

        int firstPosition = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % mListDatas.size());
        //viewPager.setOffscreenPageLimit(imageViewList.size());

        //设置默认选中中间的item
        //Integer.MAX_VALUE:整型的无限大的一个数
        int middle = Integer.MAX_VALUE / 2;
        //取余
        int extra = middle % mListDatas.size();
        int item = middle - extra;
        // 设置从中间的某个位置开始滑动，从而能够实现向左向右的循环滑动
        mViewPager.setCurrentItem(item);
    }


    /**
     * 图片轮播ViewPager适配器
     */
    class MyAdapter extends PagerAdapter {

        // 页面的数量
        @Override
        public int getCount() {
            if (mListDatas.size() <= 1) {
                return mListDatas.size();
            }
            return Integer.MAX_VALUE;
        }

        // 标记方法，用来判断缓存标记
        @Override
        public boolean isViewFromObject(View view, Object object) {
            // view:显示的view，object: 标记
            return view == object;
        }

        // 初始化item

        /**
         * 还要说一下 instantiateItem() 方法，为什么要判断 parent 的状态？原因是如果不这样处理，在向左滑动 ViewPager 的时候会报错： The specified child already has a parent. You must call removeView 。
         * <p>
         * 注意：instantiateItem() 进行了判断，destroyItem() 就不用再 removeView() 了，否则会出现空白页。
         *
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            int newPosition = position % mListDatas.size();
            //因为当用户向左滑时position可能出现负值，所以必须进行处理
            if (newPosition < 0) {
                newPosition = mListDatas.size() + newPosition;
            }
//             position： 要加载的位置
            ImageView imageView = mListDatas.get(newPosition);
            //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
            ViewGroup parent = (ViewGroup) imageView.getParent();
            if (parent != null) {
                parent.removeView(imageView);
            }

//            ImageView imageView = new ImageView(container.getContext());
//            imageView.setBackgroundResource(mListDatas[position % mListDatas.size()]);
//            ((ViewPager)container).addView(imageView, 0);


//            // 用来添加要显示的View的
            container.addView(imageView);

            // 记录缓存标记--return 标记
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // 销毁移除item， object:标记
//            container.removeView((View) object);
        }
    }

    /**
     * 获取自动轮播任务
     *
     * @return
     */
    public AutoScrollTask getAutoScrollTask() {
        if (mAutoScrollTask == null) {
            synchronized (this) {  //synchronized:同步的
                if (mAutoScrollTask == null) {
                    mAutoScrollTask = new AutoScrollTask();
                }
            }
        }
        return mAutoScrollTask;
    }

    /**
     *
     */
    class AutoScrollTask implements Runnable {
        public void start() {
            //延迟执行
            handler.postDelayed(this, 1800);
        }

        public void stop() {
            //删除当前接口
            handler.removeCallbacks(this);
        }

        @Override
        public void run() {

            //获取当前的item
            int currentItem = mViewPager.getCurrentItem();
            currentItem++;
            mViewPager.setCurrentItem(currentItem);
            //递归
            start();
        }
    }

    /**
     * 开启自动轮播
     */
    public void startAutoScroll() {
        if (!isScrolling) {
            getAutoScrollTask().start();
            isScrolling = true;
        }
    }

    /**
     * 停止自动轮播
     */
    public void stopAutoScroll() {
        if (isScrolling) {
            getAutoScrollTask().stop();
            isScrolling = false;
        }
    }

    /**
     * 设置线性布局的背景颜色
     *
     * @param color
     */
    public void setLlBackgroundAlph(int color) {
        mLl.setBackgroundColor(color);
    }

    /**
     * 设置文字描述是否可见
     *
     * @param visibility
     */
    public void setTvTitleVisibility(int visibility) {
        mTvTitle.setVisibility(visibility);
    }

    /**
     * 调用此方法设置布局中的内容
     *
     * @param titles
     * @param imgs
     */
    public void setTilesAndImages(String[] titles, List<ImageView> imgs) {
        this.titles = titles;
        this.mListDatas = imgs;
        initData();
        initEvent();
    }

    /**
     * 调用此方法设置布局中的内容
     *
     * @param imgs
     */
    public void setImages(List<ImageView> imgs) {
        this.mListDatas = imgs;
        initData();
        initEvent();
    }

    private void initEvent() {
        //设置viewPager监听器
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * 回调方法，当ViewPager滚动时的回调
             * @param position
             * @param positionOffset
             * @param positionOffsetPixels
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /**
             * 回到方法，当viewPager的某个页面选中时的回调
             * @param position
             */
            @Override
            public void onPageSelected(int position) {
                int newPosition = position % mListDatas.size();

                //设置选中的点的样式
                int count = mPointContainer.getChildCount();
                for (int i = 0; i < count; i++) {
                    View view = mPointContainer.getChildAt(i);

                    view.setBackgroundResource(newPosition == i ? R.drawable.point_selected : R.drawable.point_normal);
                }
                //设置标题
//                mTvTitle.setText(titles[newPosition]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setOnTouchListener(new OnTouchListener() {
            private float mDownX;
            private float mDownY;
            private long mDownTime;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("按下");
                        mDownX = event.getX();
                        mDownY = event.getY();
                        mDownTime = System.currentTimeMillis();
                        //调用停止滚动的
                        stopAutoScroll();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("移动...");
                        break;
                    case MotionEvent.ACTION_UP:
                        float upX = event.getX();
                        float upY = event.getY();
                        long upTime = System.currentTimeMillis();
                        //设置点击事件
                        if (mDownX == upX && mDownY == upY) {
                            if (upTime - mDownTime < 500) {
                                //点击
                                System.out.println("点击");
                                onLunBoClickListener.clickLunbo(mViewPager.getCurrentItem() % mListDatas.size());
                            }
                        }
                        System.out.println("松开");
                        //抬起开启自动轮播
                        startAutoScroll();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

    }

    /**
     * 设置监听
     *
     * @param onLunBoClickListener
     */
    public void setOnLunBoClickListener(OnLunBoClickListener onLunBoClickListener) {
        this.onLunBoClickListener = onLunBoClickListener;
    }

    /**
     * 自定义轮播图的监听接口
     */
    public interface OnLunBoClickListener {
        void clickLunbo(int position);
    }
}
