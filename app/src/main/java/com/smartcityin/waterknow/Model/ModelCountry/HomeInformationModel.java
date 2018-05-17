package com.smartcityin.waterknow.Model.ModelCountry;

import com.smartcityin.waterknow.Entity.Home.HomeInformationEntity;
import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : Mr.老王
 * Created on 2018/4/23
 * E-mail : wkz123011@gmail.com
 */
public class HomeInformationModel implements WaterKnowModelInf.HomeInformationInf{
    @Override
    public void getHomeInformation(final WaterCallback.HomeInformationCallback callback, String token) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        Map<String,String> map=new HashMap<>();
        map.put("token",token);
        HttpFactory.creteObserverable(retrofitService.getHomeInformation(map), new OnNextListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(Object data) {
                callback.successHomeInformation((HomeInformationEntity) data);
            }
        });
    }
}
