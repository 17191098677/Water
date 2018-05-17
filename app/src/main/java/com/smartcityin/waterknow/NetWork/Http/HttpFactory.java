package com.smartcityin.waterknow.NetWork.Http;



import com.smartcityin.waterknow.Base.BaseObserver;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.NetWork.RetrofitUtils;

import java.lang.reflect.Field;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by aa on 2018/3/16.
 */

public class HttpFactory {
    public static Retrofit retrofit;
    public static  <S>S createRetrofitService(Class<S> serviceClz) {
        String baseUrl="";
        try {
            Field field1 = serviceClz.getField("BASE_URL");
            baseUrl = (String)field1.get(serviceClz);
        } catch (NoSuchFieldException var4) {
            var4.printStackTrace();
        } catch (IllegalAccessException var5) {
            var5.getMessage();
            var5.printStackTrace();
        }
        retrofit= RetrofitUtils.getInstance(baseUrl);
        return retrofit.create(serviceClz);
    }
    public static void createObserver(Observable observable, Observer observer){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static void creteObserverable(Observable observable,OnNextListener listener){
        HttpFactory.createObserver(observable,new BaseObserver(listener));
    }
}
