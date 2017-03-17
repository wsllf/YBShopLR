package com.android.leleyouba.ybshop.common.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by llf on 16/10/20.
 */

public class ScreenUtil {

    /**
     * 获取屏幕相关参数
     *
     * @param context context
     * @return DisplayMetrics 屏幕分辨率
     */
    public static DisplayMetrics getScreenSize(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(
                Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(metrics);
        return metrics;
    }

    /**
     * 获取屏幕 density
     *
     * @param context
     * @return density 屏幕 density（密度）
     */

    public static float getDeviceDEnsity(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(
                Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.density;
    }
}
