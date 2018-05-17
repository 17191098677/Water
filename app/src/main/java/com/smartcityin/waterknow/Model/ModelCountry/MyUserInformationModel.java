package com.smartcityin.waterknow.Model.ModelCountry;

import android.util.Log;

import com.smartcityin.waterknow.Entity.My.MyUpdatePhotoEntity;
import com.smartcityin.waterknow.Entity.My.MyUserInformationEntity;
import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;

import java.io.InputStream;

/**
 * Author : Mr.老王
 * Created on 2018/4/26
 * E-mail : wkz123011@gmail.com
 */
public class MyUserInformationModel implements WaterKnowModelInf.MyUserInformationInf{
    @Override
    public void getMyUserInformation(final WaterCallback.MyUserInformationCallback callback, String token) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.getUserInformationEntity(token), new OnNextListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(Object data) {
                callback.successMyUserInformation((MyUserInformationEntity) data);
            }
        });
    }

    @Override
    public void getUpdateHeadImage(final WaterCallback.MyUserInformationCallback callback, String token,String head,String foot) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.getUpdateImage(token, head,foot), new OnNextListener() {
            @Override
            public void error(String error) {
                Log.e("QQQ",error);
            }

            @Override
            public void success(Object data) {
                callback.updateImageHead((MyUpdatePhotoEntity) data);
            }
        });
    }
}
