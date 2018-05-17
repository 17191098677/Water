package com.smartcityin.waterknow.Model.ModelCountry;

import com.smartcityin.waterknow.Entity.Home.HomeWaterKnowEntity;
import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;

/**
 * Author : Mr.老王
 * Created on 2018/4/8
 * E-mail : wkz123011@gmail.com
 */
public class WaterKnowLedgeModel implements WaterKnowModelInf.HomeWaterKnowLedgeInf,OnNextListener<HomeWaterKnowEntity>{
    private WaterCallback.WaterKnowCallback callback;
    @Override
    public void getWaterKnowLedge(WaterCallback.WaterKnowCallback callback,String token,int page) {
        this.callback=callback;
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.getHomeUserWater(token,page),this);
    }

    @Override
    public void error(String error) {

    }

    @Override
    public void success(HomeWaterKnowEntity data) {
        callback.successWaterKnow(data);
    }
}
