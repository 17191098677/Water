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
 * Created on 2018/4/18
 * E-mail : wkz123011@gmail.com
 */
public class RegisterModel implements WaterKnowModelInf.RegisterInf{
    @Override
    public void getRegister(final WaterCallback.RegisterCallback callback, final String username, String code, String nickname , final String pass, String password, String msg_id) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        Map<String,String> map=new HashMap<>();
        map.put("username",username);
        map.put("code",code);
        map.put("nickname",nickname);
        map.put("pass",pass);
        map.put("password",password);
        map.put("msg_id",msg_id);
        HttpFactory.creteObserverable(retrofitService.getRegister(map), new OnNextListener() {
            @Override
            public void error(String error) {
                Log.e("ERROR",error);
            }

            @Override
            public void success(Object data) {
                callback.successRegisterData(data);
            }
        });
    }

    @Override
    public void resetGetYZM(final WaterCallback.RegisterCallback callback, String edPhone) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        Map<String,String> map=new HashMap<>();
        map.put("username",edPhone);
        HttpFactory.creteObserverable(retrofitService.getRegisterEntity(map), new OnNextListener<RegisterEntity>() {
            @Override
            public void error(String error) {
            }

            @Override
            public void success(RegisterEntity data) {
                callback.getYZM(data);
            }
        });
    }

    @Override
    public void resetForgetYZM(final WaterCallback.RegisterCallback callback, String edPhone) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        Map<String,String> map=new HashMap<>();
        map.put("username",edPhone);
        HttpFactory.creteObserverable(retrofitService.getForGetPSWEntity(map), new OnNextListener<RegisterEntity>() {
            @Override
            public void error(String error) {
            }

            @Override
            public void success(RegisterEntity data) {
                callback.getForgetYZM(data);
            }
        });
    }
}
