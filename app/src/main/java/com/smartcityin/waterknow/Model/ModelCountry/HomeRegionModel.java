package com.smartcityin.waterknow.Model.ModelCountry;

import com.smartcityin.waterknow.Entity.Home.RegionEntity;
import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Author : Mr.老王
 * Created on 2018/4/8
 * E-mail : wkz123011@gmail.com
 */
public class HomeRegionModel implements WaterKnowModelInf.HomeRegionInf,OnNextListener<RegionEntity>{
    private WaterCallback.HomeRegionCallback callback;
    @Override
    public void getHomeRegion(WaterCallback.HomeRegionCallback callback, String token) {
        this.callback=callback;
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        Observable<RegionEntity> homeRegion = retrofitService.getHomeRegion(token);
        HttpFactory.creteObserverable(homeRegion,this);
    }

    @Override
    public void error(String error) {

    }

    @Override
    public void success(RegionEntity data) {
        callback.successHomeRegion(data);
    }
}
