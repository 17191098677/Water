package com.smartcityin.waterknow.Model.ModelCountry;


import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;

/**
 * Author : Mr.老王
 * Created on 2018/4/25
 * E-mail : wkz123011@gmail.com
 */
public class ServiceUserHelpModel implements WaterKnowModelInf.ServiceUserHelpInf{
    @Override
    public void getServiceUserHelp(final WaterCallback.ServiceUserHelpCallback callback, String token, int page) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.getServiceUserHelp(token, page), new OnNextListener() {
            @Override
            public void error(String error) {
                callback.errorUserHelp(error);
            }

            @Override
            public void success(Object data) {
                callback.successUserHelp(data);
            }
        });
    }

}
