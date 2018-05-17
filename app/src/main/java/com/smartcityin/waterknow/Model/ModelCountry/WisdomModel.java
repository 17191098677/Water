package com.smartcityin.waterknow.Model.ModelCountry;

import android.support.annotation.Nullable;
import android.util.Log;

import com.smartcityin.waterknow.Entity.Home.WisdomEntity;
import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;

import io.reactivex.Observable;

/**
 * Author : Mr.老王
 * Created on 2018/4/8
 * E-mail : wkz123011@gmail.com
 */
public class WisdomModel implements WaterKnowModelInf.WisdomLifeInf,OnNextListener<WisdomEntity> {
    private WaterCallback.WisdomLifeCallback wisdomLifeCallback;
    @Override
    public void getWisdomLifeCallback(WaterCallback.WisdomLifeCallback wisdomLifeCallback, String token, @Nullable int page) {
        this.wisdomLifeCallback=wisdomLifeCallback;
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        Observable<WisdomEntity> homeWisdomLife = retrofitService.getHomeWisdomLife(token, page);
        HttpFactory.creteObserverable(homeWisdomLife,this);
    }

    @Override
    public void error(String error) {
        wisdomLifeCallback.errorWisdomLife(error);
    }

    @Override
    public void success(WisdomEntity data) {
        wisdomLifeCallback.successWisdomLife(data);
    }
}
