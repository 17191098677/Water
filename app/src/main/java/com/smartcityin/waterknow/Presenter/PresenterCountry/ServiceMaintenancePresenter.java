package com.smartcityin.waterknow.Presenter.PresenterCountry;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.Service.ServiceMaintenanceEntity;
import com.smartcityin.waterknow.Model.ModelCountry.ServiceMaintenanceModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

/**
 * Author : Mr.老王
 * Created on 2018/5/3
 * E-mail : wkz123011@gmail.com
 */
public class ServiceMaintenancePresenter extends BasePresenter<Commpart.ServiceMaintenanceViewInf> implements Commpart.ServiceMaintenancePreInf,WaterCallback.ServiceMaintenceCallback{
    @Override
    public void setServiceMaintenance(String token) {
        ServiceMaintenanceModel model=new ServiceMaintenanceModel();
        model.getServiceMaintenance(this,token);
    }

    @Override
    public void serviceMaintence(ServiceMaintenanceEntity serviceMaintenanceEntity) {
        if (isViewAttarchView())
            getView().getServiceMaintenance(serviceMaintenanceEntity);
    }
}
