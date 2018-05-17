package com.smartcityin.waterknow.Presenter.PresenterCountry;

import android.util.Log;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.Service.ServiceUserHelpEntity;
import com.smartcityin.waterknow.Model.ModelCountry.ServiceUserHelpModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

/**
 * Author : Mr.老王
 * Created on 2018/4/25
 * E-mail : wkz123011@gmail.com
 */
public class ServiceUserHelpPresenter extends BasePresenter<Commpart.ServiceUserHelpViewInf> implements Commpart.ServiceUserHelpPreInf,WaterCallback.ServiceUserHelpCallback<ServiceUserHelpEntity>{

    @Override
    public void successUserHelp(ServiceUserHelpEntity serviceUserHelp) {
        if (isViewAttarchView())
            getView().getServiceUserHelp(serviceUserHelp);
    }

    @Override
    public void errorUserHelp(String error) {
        Log.e("QQQ",error);
    }

    @Override
    public void setUserHelp(String token, int page) {
        ServiceUserHelpModel model=new ServiceUserHelpModel();
        model.getServiceUserHelp(this,token,page);
    }
}
