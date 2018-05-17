package com.smartcityin.waterknow.Model.ModelCountry;

import com.smartcityin.waterknow.Entity.My.MyPersonalEntity;
import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;

/**
 * Author : Mr.老王
 * Created on 2018/4/10
 * E-mail : wkz123011@gmail.com
 */
public class PersonalFunctionModel implements WaterKnowModelInf.MyFunctionInf{


    @Override
    public void getMyPersonal(final WaterCallback.MyFunctionCallback callback, String token) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.getMyPersonalEntity(token), new OnNextListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(Object data) {
                callback.successMyPersonal((MyPersonalEntity) data);
            }
        });
    }
}
