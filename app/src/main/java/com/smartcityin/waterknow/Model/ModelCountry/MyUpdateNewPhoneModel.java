package com.smartcityin.waterknow.Model.ModelCountry;

import com.smartcityin.waterknow.Entity.My.MyUpdatePhoneYZMEntity;
import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;

/**
 * Author : Mr.老王
 * Created on 2018/4/27
 * E-mail : wkz123011@gmail.com
 */
public class MyUpdateNewPhoneModel implements WaterKnowModelInf.MyUpdateNewPhoneInf{
    @Override
    public void getMyUpdateNewPhone(final WaterCallback.MyUpdateNewPhoneCallback callback, String token, String code, String msg_id, String newPhone) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.getMyUpdateNewPhoneEntity(token, code, msg_id, newPhone), new OnNextListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(Object data) {
                callback.successnewPhone((MyUpdatePhoneYZMEntity) data);
            }
        });
    }
}
