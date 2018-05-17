package com.smartcityin.waterknow.Model.ModelCountry;


import android.util.Log;

import com.smartcityin.waterknow.Entity.Service.ServiceNewsCenterEntity;
import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;


/**
 * Author : Mr.老王
 * Created on 2018/4/9
 * E-mail : wkz123011@gmail.com
 */
public class ServiceNewsCenterModel implements WaterKnowModelInf.ServiceNewsCenterInf,OnNextListener<ServiceNewsCenterEntity>{
    private WaterCallback.ServiceNewsCenterCallback callback;
    @Override
    public void getServiceNewsCenter(WaterCallback.ServiceNewsCenterCallback callback, String token,int page) {
        this.callback=callback;
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.getServiceNews(token,page),this);
    }

    @Override
    public void error(String error) {
        callback.errorNewsCenter(error);
    }

    @Override
    public void success(ServiceNewsCenterEntity data) {
        callback.successNewsCenter(data);
    }
}
