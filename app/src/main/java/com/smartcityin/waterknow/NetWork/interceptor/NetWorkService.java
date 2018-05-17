package com.smartcityin.waterknow.NetWork.interceptor;



import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.NetWork.NetWorkState;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者: ---> 臻 <----
 * 联系方式：17801094126
 */

public class NetWorkService {
    //设置缓存时间
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 7;
    //设置拦截器供RetrofitUtils使用
    public static Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String cacheControl = request.cacheControl().toString();
            if (!NetWorkState.isNetworkAvailable(WaterKnowApp.WATER_CONTEXT)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetWorkState.isNetworkAvailable(WaterKnowApp.WATER_CONTEXT)) {
                return originalResponse.newBuilder()
                        .header("userid", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" +CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };
}
