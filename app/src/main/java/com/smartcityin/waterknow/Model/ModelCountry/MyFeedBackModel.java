package com.smartcityin.waterknow.Model.ModelCountry;

import android.support.annotation.Nullable;

import com.smartcityin.waterknow.Entity.My.MyFeedBackEntity;
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
public class MyFeedBackModel implements WaterKnowModelInf.MyFeedBackInf{
    @Override
    public void getMyFeedBack(final WaterCallback.MyFeedBakcCallback callback, String token, String content, @Nullable String Email) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.getMyFeedBackEntity(token, content, Email), new OnNextListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(Object data) {
                callback.successMyFeedBack((MyFeedBackEntity) data);
            }
        });
    }
}
