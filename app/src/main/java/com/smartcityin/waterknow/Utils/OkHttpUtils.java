package com.smartcityin.waterknow.Utils;

import android.util.Log;


import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.NetWork.interceptor.NetWorkService;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 作者: ---> 臻 <----
 * 联系方式：17801094126
 */

public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils;
    private OkHttpClient client;
    private OkHttpUtils(){
            HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e("HTTPTAG",message);
                }
            });
            File cachefile=new File(WaterKnowApp.WATER_CONTEXT.getCacheDir(),"cache");
            Cache cache=new Cache(cachefile,1024*1024*100);
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client=new OkHttpClient.Builder()
                    .certificatePinner(new CertificatePinner.Builder()
                            .add("sbbic.com", "sha1/C8xoaOSEzPC6BgGmxAt/EAcsajw=")
                            .add("closedevice.com", "sha1/T5x9IXmcrQ7YuQxXnxoCmeeQ84c=")
                            .build())
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15,TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .addInterceptor(NetWorkService.mRewriteCacheControlInterceptor)
                    .addNetworkInterceptor(NetWorkService.mRewriteCacheControlInterceptor)
                    .cache(cache)
                    .build();
    }
    public static OkHttpUtils getInstance(){
        if(okHttpUtils==null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpUtils == null)
                    okHttpUtils = new OkHttpUtils();
            }
        }
            return okHttpUtils;
    }
    public void get(String url,Callback callback){
        Request request=new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
