package com.smartcityin.waterknow.NetWork;

import android.util.Log;

import com.google.gson.Gson;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.NetWork.interceptor.NetWorkService;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aa on 2018/3/16.
 */

public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;
    private static Retrofit retrofit;
    private RetrofitUtils(){}
    public static Retrofit getInstance(String BASE_URL){
        if(retrofit==null)
            synchronized (RetrofitUtils.class){
                if (retrofit==null){
                    HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            Log.i("HTTPTAG",message);
                        }
                    });
                    File cachefile=new File(WaterKnowApp.WATER_CONTEXT.getCacheDir().getAbsolutePath(),"HttpCache");
                    Cache cache=new Cache(cachefile,1024*1024*50);
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    OkHttpClient client=new OkHttpClient.Builder()
                            .connectTimeout(200, TimeUnit.SECONDS)
                            .readTimeout(200,TimeUnit.SECONDS)
                            .writeTimeout(200, TimeUnit.SECONDS)
                            .addInterceptor(NetWorkService.mRewriteCacheControlInterceptor)
                            .addInterceptor(new CacheInterceptor())
                            .addInterceptor(interceptor)
                            .addNetworkInterceptor(loggingInterceptor)
                            .cache(cache)
                            .build();
                    retrofit=new Retrofit.Builder()
                            .client(client)
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(new Gson()))
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }

            }
        return retrofit;
    }
    static class CacheInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            boolean netAvailable =NetWorkState.isNetworkAvailable(WaterKnowApp.WATER_CONTEXT);
            if (netAvailable) { request = request.newBuilder()
                //网络可用 强制从网络获取数据
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();
            } else {
                request = request.newBuilder()
                //网络不可用 从缓存获取
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build(); }
                        Response response = chain.proceed(request);
            if (netAvailable) {
                response = response.newBuilder() .removeHeader("Pragma")
                // 有网络时 设置缓存超时时间1个小时
                        .header("Cache-Control", "public, max-age=" + 60 * 60)
                        .build();
            } else {
                response = response.newBuilder() .removeHeader("Pragma")
                // 无网络时，设置超时为1周
                          .header("Cache-Control", "public, only-if-cached, max-stale=" + 7 * 24 * 60 * 60)
                        .build();
            }
            return response;
        }
    }
    public  static   Interceptor interceptor=new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            int maxAge = 60*60; // 有网络时 设置缓存超时时间1个小时
            int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
            Request request = chain.request();
            if (NetWorkState.isNetworkReachable(WaterKnowApp.WATER_CONTEXT)) {
                request= request.newBuilder()
                        .addHeader("apikey", "2ffc3e48c7086e0e1faa003d781c0e69")
                        .cacheControl(CacheControl.FORCE_NETWORK)//有网络时只从网络获取
                        .build();
            }else {
                request= request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)//无网络时只从缓存中读取
                        .build();
            }
            Response response = chain.proceed(request);
            if (NetWorkState.isNetworkReachable(WaterKnowApp.WATER_CONTEXT)) {
                response= response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                response= response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }

    };
}
