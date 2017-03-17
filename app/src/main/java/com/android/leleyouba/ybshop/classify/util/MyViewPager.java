package com.android.leleyouba.ybshop.classify.util;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by xalo on 2017/2/28.
 */

public class MyViewPager extends ViewPager {

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//	@Override
//	public boolean onInterceptTouchEvent(MotionEvent arg0) {
//		return true;
//	}

    /**
     * 取消viewpager的切换动画效果
     * @param item
     * @param smoothScroll
     */
    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (listener != null) {
                    listener.onTouchDown();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (listener != null) {
                    listener.onTouchUp();
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private OnViewPagerTouchEvent listener;

    public void setOnViewPagerTouchEventListener(OnViewPagerTouchEvent l) {
        listener = l;
    }

    public interface OnViewPagerTouchEvent {
        void onTouchDown();

        void onTouchUp();
    }

}
