package com.smartcityin.waterknow.Model.ModelCountry;

import com.smartcityin.waterknow.Entity.Service.ServiceMaintenanceEntity;
import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;

/**
 * Author : Mr.老王
 * Created on 2018/5/3
 * E-mail : wkz123011@gmail.com
 */
public class ServiceMaintenanceModel implements WaterKnowModelInf.ServiceMaintenanceInf{
    @Override
    public void getServiceMaintenance(final WaterCallback.ServiceMaintenceCallback callback, String token) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.getServiceMaintenance(token), new OnNextListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(Object data) {
                callback.serviceMaintence((ServiceMaintenanceEntity) data);
            }
        });
    }
}
