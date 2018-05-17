package com.smartcityin.waterknow.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.smartcityin.waterknow.R;

/**
 * 修改状态栏字体的颜色
 */
public class StyleUtils {

    /**

     * 布局中必须有一个id为status_bar的view来设置状态栏背景

     * 必须要在 setContentView之后调用

     *

     * @param activity 当前页面

     */

    public static void setTranslateStatusBar(Activity activity,int titleID) {

// 4.4以上处理

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // android

            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);// 状态栏透明

            View status_bar = activity.findViewById(titleID);// 标题栏id

            if (status_bar != null) {

                ViewGroup.LayoutParams params = status_bar.getLayoutParams();

                params.height = getStatusBarHeight(activity);

                status_bar.setLayoutParams(params);

            }

        }

//5.0 以上处理

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = activity.getWindow();

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.setStatusBarColor(Color.TRANSPARENT);

        }

    }
        private static int getStatusBarHeight(Context context) {

            int result = 0;

            int resourceId = context.getResources().getIdentifier("status_bar_height",

                    "dimen", "android");

            if (resourceId > 0) {

                result = context.getResources().getDimensionPixelSize(resourceId);

            }

            return result;

        }



        /**

         * Flag只有在使用了FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS

         * 并且没有使用 FLAG_TRANSLUCENT_STATUS的时候才有效，也就是只有在状态栏全透明的时候才有效。

         * @param activity

         * @param bDark

         */

        public static void setStatusBarMode(Activity activity, boolean bDark) {
//6.0以上
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                View decorView = activity.getWindow().getDecorView();
                if (decorView != null) {
                    int vis = decorView.getSystemUiVisibility();
                    if (bDark) {
                        vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                    } else {
                        vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                    }
                    decorView.setSystemUiVisibility(vis);
                }
            }else if (Build.VERSION.SDK_INT <Build.VERSION_CODES.M){
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //set child View not fill the system window
                ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
                View mChildView = mContentView.getChildAt(0);
                if (mChildView != null) {
                    ViewCompat.setFitsSystemWindows(mChildView, true);
                }
            }
        }

}
