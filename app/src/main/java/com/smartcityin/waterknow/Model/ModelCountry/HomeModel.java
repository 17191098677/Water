package com.smartcityin.waterknow.Model.ModelCountry;

import android.util.Log;

import com.smartcityin.waterknow.Entity.Home.HomeBannerEntity;
import com.smartcityin.waterknow.Entity.Home.HomeMeterStateEntity;
import com.smartcityin.waterknow.Entity.Home.HomeNewsEntity;
import com.smartcityin.waterknow.Entity.Home.HomeSwitchNumberCommitEntity;
import com.smartcityin.waterknow.Entity.SwitchNumberEntity;
import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : Mr.老王
 * Created on 2018/4/10
 * E-mail : wkz123011@gmail.com
 */
public class HomeModel implements WaterKnowModelInf.HomeInf{
    RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
    @Override
    public void getHomeWaterMeterState(final WaterCallback.HomeCallback callback, String token) {
        Map<String,String> map=new HashMap<>();
        map.put("token",token);
        HttpFactory.creteObserverable(retrofitService.getHomeWaterMeterState(map), new OnNextListener() {
            @Override
            public void error(String error) {
                Log.e("QQQ",error);
            }

            @Override
            public void success(Object data) {
                callback.successHomeWaterMeterState((HomeMeterStateEntity) data);
            }
        });
    }

    @Override
    public void getHomeBanner(final WaterCallback.HomeCallback callback, String token) {
        Map<String,String> map=new HashMap<>();
        map.put("token",token);
        HttpFactory.creteObserverable(retrofitService.getHomeBannerEntity(map), new OnNextListener() {
            @Override
            public void error(String error) {

            }

            @Override public void success(Object data) {
                callback.successHomeBanner((HomeBannerEntity) data);
            }
        });
    }

    @Override
    public void getHomeNews(final WaterCallback.HomeCallback callback, String token) {
        Map<String,String> map=new HashMap<>();
        map.put("token",token);
        HttpFactory.creteObserverable(retrofitService.getHomeNews(map), new OnNextListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(Object data) {
                callback.successHomeNewsEntity((HomeNewsEntity) data);
            }
        });
    }

    @Override
    public void getHomeSwitchNumber(final WaterCallback.HomeCallback callback, String token) {
        Map<String,String> map=new HashMap<>();
        map.put("token",token);
        HttpFactory.creteObserverable(retrofitService.getSwitchNumber(map), new OnNextListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(Object data) {
                callback.getHomeSwitchNumberEntity((SwitchNumberEntity) data);
            }
        });
    }


    @Override
    public void confirmNumber(final WaterCallback.HomeCallback callback, String meterId, String token) {
        HttpFactory.creteObserverable(retrofitService.getHomeRechargePayment(meterId, token), new OnNextListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(Object data) {
                callback.confirmNumberEntity((HomeSwitchNumberCommitEntity) data);
            }
        });
    }

}
