package com.smartcityin.waterknow.Model.ModelCountry;

import android.util.Log;

import com.smartcityin.waterknow.Entity.My.MyInToolPAymentEntity;
import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;

import java.util.Map;

/**
 * Author : Mr.老王
 * Created on 2018/5/3
 * E-mail : wkz123011@gmail.com
 */
public class HomeWaterRepotModel implements WaterKnowModelInf.HomeWaterRepotInf{
    @Override
    public void getHomeWaterRepot(final WaterCallback.HomeWaterRepotCallback callback, Map<String, String> map, String pics, String foots) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.getWaterRepot(map, pics, foots), new OnNextListener() {
            @Override
            public void error(String error) {
            }

            @Override
            public void success(Object data) {
                callback.successWaterRepot((MyInToolPAymentEntity) data);
            }
        });
    }
}
