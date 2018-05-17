package com.smartcityin.waterknow.Model.ModelCountry;

import android.util.Log;

import com.smartcityin.waterknow.Entity.RegisterEntity.RegisterEntity;
import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : Mr.老王
 * Created on 2018/4/17
 * E-mail : wkz123011@gmail.com
 */
public class RegisterSMSMsgIDModel implements WaterKnowModelInf.RegisterSMSMsgInf{

    @Override
    public void getRegisterMsgID(final WaterCallback.RegisterSMSCallback callback, final String edPhone) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        Map<String,String> map=new HashMap<>();
        map.put("username",edPhone);
        HttpFactory.creteObserverable(retrofitService.getRegisterEntity(map), new OnNextListener<RegisterEntity>() {
            @Override
            public void error(String error) {
                callback.errorRegisterSMS(error);
            }

            @Override
            public void success(RegisterEntity data) {
                callback.successRegisterSMSData(data);
            }
        });
    }
}
