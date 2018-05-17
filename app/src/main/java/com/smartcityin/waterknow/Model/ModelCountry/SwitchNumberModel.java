package com.smartcityin.waterknow.Model.ModelCountry;

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
 * Created on 2018/4/23
 * E-mail : wkz123011@gmail.com
 */
public class SwitchNumberModel implements WaterKnowModelInf.HomeSwitchNumberInf,OnNextListener<SwitchNumberEntity> {
    private WaterCallback.HomeSwitchNumberCallback callback;
    @Override
    public void getHomeSwitchNumber(WaterCallback.HomeSwitchNumberCallback callback, String token) {
        this.callback=callback;
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        Map<String,String> map=new HashMap<>();
        map.put("token",token);
        HttpFactory.creteObserverable(retrofitService.getSwitchNumber(map),this);
    }

    @Override
    public void getHomeRecharge(final WaterCallback.HomeSwitchNumberCallback callback, String meterID, String token) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.getHomeRechargePayment(meterID, token), new OnNextListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(Object data) {
                callback.successHomeRechargePayment((HomeSwitchNumberCommitEntity) data);
            }
        });
    }

    @Override
    public void deleteSwitchNumber(final WaterCallback.HomeSwitchNumberCallback callback, String meterID, String token) {
        RetrofitService retrofitService=HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.unBindingWaterID(token, meterID), new OnNextListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(Object data) {
                callback.deleteSwitchNumber((HomeSwitchNumberCommitEntity) data);
            }
        });
    }

    @Override
    public void error(String error) {

    }

    @Override
    public void success(SwitchNumberEntity data) {
        callback.successHomeSwitchNumber(data);
    }
}
