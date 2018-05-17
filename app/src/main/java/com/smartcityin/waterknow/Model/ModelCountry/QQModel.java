package com.smartcityin.waterknow.Model.ModelCountry;

import android.util.Log;

import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.Utils.OkHttpUtils;

import java.io.IOException;
import java.util.logging.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Author : Mr.老王
 * Created on 2018/4/19
 * E-mail : wkz123011@gmail.com
 */
public class QQModel implements WaterKnowModelInf.QQInf{
    @Override
    public void getQQEntity(final WaterCallback.QQCallback callback, String access_token, String openid) {
        String url="https://graph.qq.com/user/get_simple_userinfo?access_token="+access_token+"&oauth_consumer_key=1106804472&openid="+openid+"&format=json ";
        OkHttpUtils.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                callback.getQQEntity(string);
            }
        });

    }
}
