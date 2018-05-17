package com.smartcityin.waterknow.Model.ModelCountry;

import android.util.Log;
import android.widget.RelativeLayout;

import com.smartcityin.waterknow.Entity.Home.HomeSearchEntity;
import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;

/**
 * Author : Mr.老王
 * Created on 2018/4/26
 * E-mail : wkz123011@gmail.com
 */
public class HomeSearchModel implements WaterKnowModelInf.HomeSearchInf{
    @Override
    public void getHomeSearch(final WaterCallback.HomeSearchCallback callback, String token, String like) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.getHomeSearchEntity(token, like), new OnNextListener() {
            @Override
            public void error(String error) {
                Log.e("QQQ",error);
            }

            @Override
            public void success(Object data) {
                callback.successHomeSearch((HomeSearchEntity) data);
            }
        });
    }
}
