package com.smartcityin.waterknow.Model.ModelCountry;

import com.smartcityin.waterknow.Entity.Service.ServiceNetWorkEntity;
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
public class ServiceNetWorkModel implements WaterKnowModelInf.ServiceNetWorkInf,OnNextListener<ServiceNetWorkEntity>{
    private WaterCallback.ServiceNetWorkCallback callback;
    @Override
    public void getNetWork(WaterCallback.ServiceNetWorkCallback callback,String token) {
        this.callback=callback;
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.getServiceNetWork(token),this);
    }

    @Override
    public void error(String error) {
        callback.errorServiceNetWork(error);
    }

    @Override
    public void success(ServiceNetWorkEntity data) {
        callback.successServiceNetWork(data);
    }
}
