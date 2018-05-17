package com.smartcityin.waterknow.Utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 核心工具类
 * Author : Mr.老王
 * Created on 2018/5/15
 * E-mail : wkz123011@gmail.com
 */
public class CoreUtils {
        //Activity列表
        public static ArrayList<Activity> activityList = new ArrayList<Activity>();

        /**
         * 添加Activity到列表中
         * @param activity
         */
        public static void addAppActivity(Activity activity){
            if(!activityList.contains(activity)){
                activityList.add(activity);
            }
        }

        /**
         * 从列表移除Activity
         * @param activity
         */
        public static void removeAppActivity(Activity activity){
            if(activityList.contains(activity)){
                activityList.remove(activity);
            }
        }

        /**
         * 退出应用程序
         */
        public static void exitApp(Context context){

            for (Activity ac : activityList) {
                if(!ac.isFinishing()){
                    ac.finish();
                }
            }
            activityList.clear();
            //杀掉进程
            android.os.Process.killProcess(android.os.Process.myPid());
        }

        /**
         * 清空List集合
         * @param list
         */
        public static void clearList(List<?> list){
            if(list!=null){
                list.clear();
            }
        }
}
