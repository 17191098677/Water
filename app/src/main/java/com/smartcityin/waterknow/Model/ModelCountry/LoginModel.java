package com.smartcityin.waterknow.Model.ModelCountry;

import android.util.Log;

import com.smartcityin.waterknow.Entity.Home.HomeSwitchNumberCommitEntity;
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
public class LoginModel implements WaterKnowModelInf.LoginInf {
    @Override
    public void getLogin(final WaterCallback.LoginCallback callback, final String username, final String password) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        Map<String,String> map=new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        HttpFactory.creteObserverable(retrofitService.getLogin(map), new OnNextListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(Object data) {
                callback.successLoginData(data);
            }
        });
    }

    @Override
    public void QQLogin(final WaterCallback.LoginCallback callback, String openid,String nickname,String head) {
        RetrofitService retrofitService=HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.QQLogin(openid,nickname,head), new OnNextListener() {
            @Override
            public void error(String error) {
            }

            @Override
            public void success(Object data) {
                callback.getQQEntity((HomeSwitchNumberCommitEntity) data);
            }
        });
    }

}
