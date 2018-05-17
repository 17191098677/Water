package com.smartcityin.waterknow.Model.ModelCountry;

import android.util.Log;

import com.smartcityin.waterknow.Entity.UserWater.UserWaterEntity;
import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;

import java.util.ArrayList;

/**
 * Author : Mr.老王
 * Created on 2018/4/9
 * E-mail : wkz123011@gmail.com
 */
public class UserWaterModel implements WaterKnowModelInf.UserWaterInf,OnNextListener<UserWaterEntity> {
    private WaterCallback.UserWaterCallback callback;
    @Override
    public void getUserWaterData(WaterCallback.UserWaterCallback callback,String token,String water_id) {
        this.callback=callback;
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.getUserWater(token,water_id),this);
    }

    @Override
    public void error(String error) {
        Log.e("QQQ",error);
        callback.errorUserWater(error);
    }

    @Override
    public void success(UserWaterEntity data) {
        callback.successUserWater(data);
    }

}
