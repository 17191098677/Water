package com.smartcityin.waterknow.Model.ModelCountry;

import com.smartcityin.waterknow.Entity.My.MyInToolPAymentEntity;
import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;

import java.util.Map;

/**
 * Author : Mr.老王
 * Created on 2018/5/16
 * E-mail : wkz123011@gmail.com
 */
public class BindingPhoneModel implements WaterKnowModelInf.BindingPhoneInf{

    @Override
    public void bindingPhone(final WaterCallback.BindingPhoneCallback callback, Map<String, String> map) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.bindingPhone(map), new OnNextListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(Object data) {
                callback.bindingPhone((MyInToolPAymentEntity) data);
            }
        });
    }
}
