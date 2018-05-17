package com.smartcityin.waterknow.Model.ModelCountry;

import android.util.Log;
import android.widget.RelativeLayout;

import com.smartcityin.waterknow.Entity.My.MyAnnualQueryEntity;
import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;

import io.reactivex.Observable;

/**
 * Author : Mr.老王
 * Created on 2018/5/4
 * E-mail : wkz123011@gmail.com
 */
public class AnnualQueryModel implements WaterKnowModelInf.MyAnnualQueryInf{
    @Override
    public void getMyAnnualQuery(final WaterCallback.AnnualQueryCallback callback, String token, String year, String water_id) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        Observable<MyAnnualQueryEntity> myAnnualEntity = retrofitService.getMyAnnualEntity(token, year, water_id);
        HttpFactory.creteObserverable(myAnnualEntity, new OnNextListener() {
            @Override
            public void error(String error) {
                Log.e("QQQQQ",error);
            }

            @Override
            public void success(Object data) {
                callback.getAnnualEntity((MyAnnualQueryEntity) data);
            }
        });
    }
}
