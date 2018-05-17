package com.smartcityin.waterknow.Model.ModelCountry;

import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : Mr.老王
 * Created on 2018/4/19
 * E-mail : wkz123011@gmail.com
 */
public class BindingWaterModel implements WaterKnowModelInf.BindingWaterInf{
    @Override
    public void getBindingWater(final WaterCallback.BindingWaterCallback callback, String meter_id, String token) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        Map<String,String> map=new HashMap<>();
        map.put("meter_id",meter_id);
        map.put("token",token);
        HttpFactory.creteObserverable(retrofitService.getBindingWater(map), new OnNextListener() {
            @Override
            public void error(String error) {
            }

            @Override
            public void success(Object data) {
                callback.successBindingWater(data);
            }
        });
    }
}
