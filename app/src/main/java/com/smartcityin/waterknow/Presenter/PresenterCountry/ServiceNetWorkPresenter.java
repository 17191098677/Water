package com.smartcityin.waterknow.Presenter.PresenterCountry;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.Service.ServiceNetWorkEntity;
import com.smartcityin.waterknow.Model.ModelCountry.ServiceNetWorkModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

import java.util.ArrayList;

/**
 * Author : Mr.老王
 * Created on 2018/4/9
 * E-mail : wkz123011@gmail.com
 */
public class ServiceNetWorkPresenter extends BasePresenter<Commpart.ServiceNetWorkViewInf> implements Commpart.ServiceNetWorkPreInf,WaterCallback.ServiceNetWorkCallback<ServiceNetWorkEntity>{

    @Override
    public void successServiceNetWork(ServiceNetWorkEntity service) {
        if (isViewAttarchView())
            getView().getServiceNetWorkData(service);
    }

    @Override
    public void errorServiceNetWork(String error) {

    }

    @Override
    public void setServiceNetWork(String token) {
        ServiceNetWorkModel model=new ServiceNetWorkModel();
        model.getNetWork(this,token);
    }
}
