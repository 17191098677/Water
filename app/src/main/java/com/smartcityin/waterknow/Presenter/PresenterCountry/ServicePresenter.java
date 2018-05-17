package com.smartcityin.waterknow.Presenter.PresenterCountry;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.ServiceGridEntity;
import com.smartcityin.waterknow.Model.ModelCountry.ServiceModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

import java.util.ArrayList;

/**
 * Author : Mr.老王
 * Created on 2018/4/9
 * E-mail : wkz123011@gmail.com
 */
public class ServicePresenter extends BasePresenter<Commpart.ServiceViewInf> implements Commpart.ServicePreInf,WaterCallback.ServiceCallback<ArrayList<ServiceGridEntity>>{

    @Override
    public void successService(ArrayList<ServiceGridEntity> service) {
        if (isViewAttarchView()){
            getView().getServiceData(service);
        }
    }

    @Override
    public void errorService(String error) {

    }

    @Override
    public void setServiceData() {
        ServiceModel model=new ServiceModel();
        model.getServiceData(this);
    }
}
