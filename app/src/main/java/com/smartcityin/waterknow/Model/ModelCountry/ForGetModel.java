package com.smartcityin.waterknow.Model.ModelCountry;

import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : Mr.老王
 * Created on 2018/4/19
 * E-mail : wkz123011@gmail.com
 */
public class ForGetModel implements WaterKnowModelInf.ForGetInf{
    @Override
    public void getForget(final WaterCallback.ForgetCallback callback, String username, String code, String msg_id, String pass, String password) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        Map<String,String> map=new HashMap<>();
        map.put("username",username);
        map.put("code",code);
        map.put("msg_id",msg_id);
        map.put("pass",pass);
        map.put("password",password);
        HttpFactory.creteObserverable(retrofitService.getForGetPSW(map), new OnNextListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(Object data) {
                callback.successForgetCallback(data);
            }
        });
    }
}
