package com.android.leleyouba.ybshop.homepage.customview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by xalo on 2017/2/27.
 */

/**
 * 该类是实现图片轮播的核心类
 */
public class ImagebannerViewGroup extends ViewGroup {


    private int children;//我们ViewGroup的子视图的总个数
    private int childWidth;//子视图的宽度
    private int childHeight;//子视图的高度
    private int x;//此时的X的值 代表的是第一次按下位置的横坐标，每一次移动过程中移动之前位置的横坐标
    private int index = 0;//代表的是我们每张图片的索引

    private int screenWidth = 0;

    public ImagebannerViewGroup(Context context) {
        super(context);

    }

    public ImagebannerViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public ImagebannerViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    /**
     * 在自定义ViewGroup中，我们必须要实现的方法有：测量》》布局》》绘制
     * 对于我们来说就是：onMeasure();
     * 我们对于绘制来说，因为我们是自定义ViewGroup，针对于容器的绘制，
     * 其实就是容器内子视图的绘制过程，那么我们只需要调用系统自带的绘制即可，
     * 也就是说，对于ViewGroup绘制过程我们不需要再重写该方法
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 由于我们要实现的是一个ViewGroup的容器，那么我们就应该知道该容器中的所有走子视图，
         * 我们要想测量我们的ViewGroup的宽度和高度，那么我们就必须要去测量子视图的宽度和高度的和，才能知道ViewGroup的宽高
         *
         */
        //1.求出子视图的个数
        children = getChildCount();

        if (0 == children) {
            setMeasuredDimension(0, 0);
        } else {
            //2.测量子视图的宽度和高度
            measureChildren(widthMeasureSpec, heightMeasureSpec);
            //此时，我们以第一个子视图为基准，也就是说我们的ViewGroup的高度就是我们的第一个子视图

            View view = getChildAt(0);//此时第一个子视图是绝对存在的
            childWidth = view.getMeasuredWidth();
            childHeight = view.getMeasuredHeight();
            int height = view.getMeasuredHeight();
            int width = view.getMeasuredWidth() * children;
            setMeasuredDimension(width, childHeight);
        }

    }


    /**
     * 事件的传递过程调用的方法：我们需要调用容器的拦截方法 onInterceptTouchEvent
     * 针对该方法我们可以理解为：如果说该方法的返回值为TRUE的时候，那么我们自定义的ViewGroup就会拦截事件，
     * 返回值为FALSE的时候，将不会接受该事件的处理过程，会向下传递
     * <p>
     * 针对于我们自定义的ViewGroup，我们希望容器处理事件，所以返回TRUE
     * 如果返回TRUE，真正处理该事件的方法是onTouchEvent
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }


    /**
     * 利用2种方式来实现该轮播图的手动轮播
     * 1.利用scrollTo，scrollBy完成轮播图的手动轮播
     * 2.利用scroller对象 完成轮播图的手动轮播
     * <p>
     * <p>
     * <p>
     * 第一：我们在滑动图片的过程中，起始就是我们自定义ViewGroup的子视图的移动过程，那么我们只需要知道滑动之前的横坐标和滑动之后的横坐标
     * ，此时我们可以求出此次滑动中移动的距离，我们利用scrollBy方法实现图片的滑动，所以此时我们需要两个值 是需要我们求出的
     * 移动之前 移动之后的横坐标
     * 第二：在我们自一次按下的一瞬间，此时的移动之前和和移动之后的值是相等的，也就是 我们按下的一瞬间的哪一个点的横坐标的值
     * <p>
     * 第三：我们在不断的移动过程中回不断的调用action_move方法，那么我们回叙要将移动之前的值和移动之后的值进行保存
     * <p>
     * 第四：在我们抬起的一瞬间，我们需要计算出我们将要滑动到那张图片的位置上
     * 我们需要求出滑动到那张图片的索引值
     * (我们当前ViewGroup的位置 + 我们每一张图片的宽度/2) / 我们每一张图片宽度值
     * 此时我们就可以利用scrollTo方法滑动到该图片的位置上
     *
     * @param event
     * @return
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://按下

                x = (int) event.getX();

                break;
            case MotionEvent.ACTION_MOVE://移动
                int moveX = (int) event.getX();
                int distance = moveX - x;
                scrollBy(-distance, 0);
                x = moveX;
                break;
            case MotionEvent.ACTION_UP://抬起
                int scrollX = getScrollX();
                index = (scrollX + childWidth / 2) / childWidth;
                if (index < 0) {
                    //说明此时已经滑动到了最左边的一张图片
                    index = 0;
                } else if (index > children - 1) {
                    //说明滑动到了最右边的一张图片
                    index = children - 1;
                }

                scrollTo(index * childWidth, 0);
                break;
            default:
                break;
        }

        return true;//返回TRUE的目的是告诉我们，该ViewGroup容器的父View，我们已经处理好了该事件
    }

    /**
     * 继承ViewGroup必须实现的方法
     *
     * @param changed 代表的是我们当我们的ViewGroup布局位置放生改变为TRUE，否则为FALSE
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        if (changed) {

            int leftMargin = 0;
            for (int i = 0; i < children; i++) {
                View view = getChildAt(i);
                view.layout(leftMargin, 0, leftMargin + childWidth, childHeight);
                leftMargin += childWidth;
            }
        }
    }
}
