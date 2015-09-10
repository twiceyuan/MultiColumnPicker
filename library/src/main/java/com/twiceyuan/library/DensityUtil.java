package com.twiceyuan.library;

import android.content.Context;

public class DensityUtil {

    /**
     * dp to px
     *
     * @param context context
     * @param dp dip
     * @return px
     */
    public static int dip2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dp * scale + 0.5f);
    }

    /**
     * px to dp
     *
     * @param context context
     * @param px pixel
     * @return dip
     */
    public static int px2dip(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (px / scale + 0.5f);
    }

    /**
     * Get width of screen
     *
     * @param context context
     * @return width of screen
     */
    public static int screenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * Get height of screen
     *
     * @param context context
     * @return height of screen
     */
    public static int screenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}  