package com.smartcityin.waterknow.Model.ModelCountry;

import com.smartcityin.waterknow.Entity.My.MyUpdatePhoneEntity;
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
public class MyUpdatePhoneYZMModel implements WaterKnowModelInf.MyUpdatePhoneYZMInf{
    @Override
    public void getMyUpdatePhoneYZM(final WaterCallback.MyUpdatePhoneYZMCallback callback, String token, String code, String msg_id) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.getMyUpdatePhoneYZMEntity(token,code,msg_id), new OnNextListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(Object data) {
                callback.successPhoneYZM((MyUpdatePhoneYZMEntity) data);
            }
        });
    }

    @Override
    public void updatePhone(final WaterCallback.MyUpdatePhoneYZMCallback callback, String token) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.getMyUpdatePhoneEntity(token), new OnNextListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(Object data) {
                callback.updatePhone((MyUpdatePhoneEntity) data);
            }
        });
    }
}
