package com.smartcityin.waterknow.Global;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.StrictMode;

import com.smartcityin.waterknow.Base.BaseDialog;
import com.smartcityin.waterknow.Base.LoginDialog;
import com.smartcityin.waterknow.Presenter.PresenterCountry.UserInformationPresenter;
import com.smartcityin.waterknow.Utils.IntentUtils;
import com.smartcityin.waterknow.View.Activity.LoginActivity;
import com.smartcityin.waterknow.View.Activity.UserInformationActivity;

/**
 * Author : Mr.老王
 * Created on 2018/3/21
 * E-mail : wkz123011@gmail.com
 */

public class WaterKnowApp extends Application {
    public static  Context WATER_CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        WATER_CONTEXT=this;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }

    /**
     * 获取用户登录信息的sp文件
     * @param context
     * @return
     */
    public static SharedPreferences getSharedPreferences(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("User",context.MODE_PRIVATE);
        return sharedPreferences;
    }

    /**
     * 获取用户登录信息
     * @param context
     * @return
     */
    public static SharedPreferences.Editor getSharedPreferencesEditor(Context context){
        SharedPreferences.Editor edit = getSharedPreferences(context).edit();
        return edit;
    }

    /**
     * 清空登录信息
     * @param context
     */
    public static void clearSharedPreferences(Context context) {
        SharedPreferences.Editor editor = getSharedPreferencesEditor(context);
        editor.clear();
        editor.commit();
    }

    /**
     * 获取用户登录Token
     * @param context
     * @param key
     * @return
     */
    public static String getToken(Context context,String key){
        String string = getSharedPreferences(context).getString(key, "");
        return string;
    }

    /**
     * 获取用户信息的sp文件
     * @param context
     * @return
     */
    public static  SharedPreferences getUserInformationSharePreferences(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("", context.MODE_PRIVATE);
        return sharedPreferences;
    }

    /**
     * 获取用户信息的sp文件
     * @param context
     * @return
     */
    public static SharedPreferences.Editor getUserInformationSharedPreferencesEditor(Context context){
        SharedPreferences.Editor edit = getUserInformationSharePreferences(context).edit();
        return edit;
    }

    /**
     * 清空用户信息存储记录
     * @param context
     */
    public static void clearUserInformationSharedPreferences(Context context) {
        SharedPreferences.Editor editor = getUserInformationSharedPreferencesEditor(context);
        editor.clear();
        editor.commit();
    }

    /**
     * Token值不正确时重新登录
     * @param activity
     */
    public static void reNewLogin(final Activity activity) {
        final LoginDialog dialog=new LoginDialog(activity);
        dialog.show();
        dialog.setTvContent("登录信息已过期,是否重新登录");
        dialog.setLeftButton("确定", new BaseDialog.OnClickerListenerLeft() {
            @Override
            public void setOnClicker() {
                WaterKnowApp.clearSharedPreferences(activity);
                IntentUtils.startIntent(activity, LoginActivity.class);
                dialog.dismiss();
                activity.finish();
            }
        });
        dialog.setRightButton("取消", new BaseDialog.OnClickerListenerRight() {
            @Override
            public void setOnClicker() {
                dialog.dismiss();
                activity.finish();
            }
        });
        dialog.setCancelable(false);
    }

}
