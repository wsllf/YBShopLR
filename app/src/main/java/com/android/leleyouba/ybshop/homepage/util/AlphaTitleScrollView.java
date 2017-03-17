package com.android.leleyouba.ybshop.homepage.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * 第三方自定义的ScrollView 实现toolBar透明渐变效果
 */

public class AlphaTitleScrollView extends ScrollView {
    public static final String TAG = "AlphaTitleScrollView";
    private int mSlop;
    private LinearLayout toolbar;
    private ImageView headView;

    public AlphaTitleScrollView(Context context, AttributeSet attrs,
                                int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public AlphaTitleScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }

    public AlphaTitleScrollView(Context context) {
        this(context, null);
    }

    private void init(Context context) {
        // mSlop = ViewConfiguration.get(context).getScaledDoubleTapSlop();
        mSlop = 10;
        Log.i(TAG, mSlop + "");
    }

    /**
     * 头部布局
     * 标题
     *
     * @param toolbar
     * @param headView
     */
    public void setTitleAndHead(LinearLayout toolbar, ImageView headView) {
        this.toolbar = toolbar;
        this.headView = headView;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        float headHeight = headView.getMeasuredHeight()
                - toolbar.getMeasuredHeight();


        int alpha = (int) (((float) t / headHeight) * 255);
        if (alpha >= 255)
            alpha = 255;
        if (alpha <= mSlop)
            alpha = 0;
        toolbar.getBackground().setAlpha(alpha);

        super.onScrollChanged(l, t, oldl, oldt);
    }
}

