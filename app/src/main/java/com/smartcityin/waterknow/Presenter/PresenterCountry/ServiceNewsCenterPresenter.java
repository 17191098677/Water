package com.smartcityin.waterknow.Presenter.PresenterCountry;

import android.util.Log;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.Service.ServiceNewsCenterEntity;
import com.smartcityin.waterknow.Model.ModelCountry.ServiceNewsCenterModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

import java.util.ArrayList;

/**
 * Author : Mr.老王
 * Created on 2018/4/9
 * E-mail : wkz123011@gmail.com
 */
public class ServiceNewsCenterPresenter extends BasePresenter<Commpart.ServiceNewsCenterViewInf>implements Commpart.ServiceNewsCenterPreInf,WaterCallback.ServiceNewsCenterCallback<ServiceNewsCenterEntity>{
    @Override
    public void successNewsCenter(ServiceNewsCenterEntity serviceNewsCenter) {
        if (isViewAttarchView())
            getView().getServiceNewsCenterData(serviceNewsCenter);
    }

    @Override
    public void errorNewsCenter(String error) {

    }

    @Override
    public void setServiceNewsCenter(String token, int page) {
        ServiceNewsCenterModel model=new ServiceNewsCenterModel();
        model.getServiceNewsCenter(this,token,page);
    }
}
