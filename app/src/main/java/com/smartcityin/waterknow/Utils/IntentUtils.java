package com.smartcityin.waterknow.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;


import java.util.ArrayList;

public class IntentUtils {
    public static void startIntent(Activity activity, Class<? extends AppCompatActivity> ActivityClz){
        Intent intent = new Intent(activity, ActivityClz);
        activity.startActivity(intent);

    }
    public static void startIntentForIntent(Activity activity,Class<? extends AppCompatActivity> ActivityClz,int requestCode){
        Intent intent = new Intent(activity, ActivityClz);

        activity.startActivityForResult(intent,requestCode);
    }
    public static void putExtra(Activity activity,Class<? extends AppCompatActivity> ActivityClz,String key,String value){
        Intent intent = new Intent(activity, ActivityClz);
        intent.putExtra(key,value);
        activity.startActivity(intent);
    }
    public static void putExtra(Activity activity,Class<? extends AppCompatActivity> ActivityClz,String key,byte value){
        Intent intent = new Intent(activity, ActivityClz);
        intent.putExtra(key,value);
        activity.startActivity(intent);
    }
    public static void putExtra(Activity activity,Class<? extends AppCompatActivity> ActivityClz,String key,short value){
        Intent intent = new Intent(activity, ActivityClz);
        intent.putExtra(key,value);
        activity.startActivity(intent);
    }
    public static void putExtra(Activity activity,Class<? extends AppCompatActivity> ActivityClz,String key,int value){
        Intent intent = new Intent(activity, ActivityClz);
        intent.putExtra(key,value);
        activity.startActivity(intent);
    }
    public static void putExtra(Activity activity,Class<? extends AppCompatActivity> ActivityClz,String key,long value){
        Intent intent = new Intent(activity, ActivityClz);
        intent.putExtra(key,value);
        activity.startActivity(intent);
    }
    public static void putExtra(Activity activity,Class<? extends AppCompatActivity> ActivityClz,String key,float value){
        Intent intent = new Intent(activity, ActivityClz);
        intent.putExtra(key,value);
        activity.startActivity(intent);
    }
    public static void putExtra(Activity activity,Class<? extends AppCompatActivity> ActivityClz,String key,double value){
        Intent intent = new Intent(activity, ActivityClz);
        intent.putExtra(key,value);
        activity.startActivity(intent);
    }
    public static void putExtra(Activity activity,Class<? extends AppCompatActivity> ActivityClz,String key,char value){
        Intent intent = new Intent(activity, ActivityClz);
        intent.putExtra(key,value);
        activity.startActivity(intent);
    }
    public static void putExtra(Activity activity,Class<? extends AppCompatActivity> ActivityClz,String key,boolean value){
        Intent intent = new Intent(activity, ActivityClz);
        intent.putExtra(key,value);
        activity.startActivity(intent);
    }
    public static void putExtra(Activity activity,Class<? extends AppCompatActivity> ActivityClz,String key,Parcelable value){
        Intent intent = new Intent(activity, ActivityClz);
        intent.putExtra(key,value);
        activity.startActivity(intent);
    }
    public static void putExtra(Activity activity,Class<? extends AppCompatActivity> ActivityClz,String key,String[] value){
        Intent intent = new Intent(activity, ActivityClz);
        intent.putExtra(key,value);
        activity.startActivity(intent);
    }
    public static void putExtra(Activity activity,Class<? extends AppCompatActivity> ActivityClz,String key,Parcelable[] value){
        Intent intent = new Intent(activity, ActivityClz);
        intent.putExtra(key,value);
        activity.startActivity(intent);
    }
    public static void putExtra(Activity activity,Class<? extends AppCompatActivity> ActivityClz,String key,int[] value){
        Intent intent = new Intent(activity, ActivityClz);
        intent.putExtra(key,value);
        activity.startActivity(intent);
    }
    public static void putStringArrayExtra(Activity activity,Class<? extends AppCompatActivity> ActivityClz,String key,ArrayList<String> value){
        Intent intent = new Intent(activity, ActivityClz);
        intent.putStringArrayListExtra(key,value);
        activity.startActivity(intent);
    }
    public static void putParcelableArrayExtra(Activity activity,Class<? extends AppCompatActivity> ActivityClz,String key,ArrayList<Parcelable> value){
        Intent intent = new Intent(activity, ActivityClz);
        intent.putParcelableArrayListExtra(key,value);
        activity.startActivity(intent);
    }
    public static void putCharSequenceArrayExtra(Activity activity,Class<? extends AppCompatActivity> ActivityClz,String key,ArrayList<CharSequence> value){
        Intent intent = new Intent(activity, ActivityClz);
        intent.putCharSequenceArrayListExtra(key,value);
        activity.startActivity(intent);
    }
    public static void putIntegerArrayExtra(Activity activity,Class<? extends AppCompatActivity> ActivityClz,String key,ArrayList<Integer> value){
        Intent intent = new Intent(activity, ActivityClz);
        intent.putIntegerArrayListExtra(key,value);
        activity.startActivity(intent);
    }
    public static String getStringExtra(Activity activity,String name){
        Intent intent=activity.getIntent();
        String stringExtra = intent.getStringExtra(name);
        return stringExtra;
    }

}
