package com.smartcityin.waterknow.Model.ModelCountry;

import android.util.Log;

import com.smartcityin.waterknow.Entity.My.MyInToolPAymentEntity;
import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;

import java.util.Map;

import retrofit2.Retrofit;

/**
 * Author : Mr.老王
 * Created on 2018/4/29
 * E-mail : wkz123011@gmail.com
 */
public class InToolPaymentModel implements WaterKnowModelInf.MyToolPaymentInf{
    @Override
    public void getMyToolPayment(final WaterCallback.MyInToolPaymentCallback callback, Map<String, String> map, String[] orders) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.getElectronicsToolPayment(map,orders), new OnNextListener() {
            @Override
            public void error(String error) {
            }

            @Override
            public void success(Object data) {
                Log.e("QQQ",data.toString());
                callback.successElectronicsToolPayment((MyInToolPAymentEntity) data);
            }
        });
    }


}
