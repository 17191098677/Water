package com.smartcityin.waterknow.Presenter.PresenterCountry;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.Home.HomeSwitchNumberCommitEntity;
import com.smartcityin.waterknow.Entity.SwitchNumberEntity;
import com.smartcityin.waterknow.Model.ModelCountry.SwitchNumberModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

/**
 * Author : Mr.老王
 * Created on 2018/4/23
 * E-mail : wkz123011@gmail.com
 */
public class SwitchNumberPresenter extends BasePresenter<Commpart.SwitchNumberViewInf> implements Commpart.SwitchNumberPreInf,WaterCallback.HomeSwitchNumberCallback{

    @Override
    public void successHomeSwitchNumber(SwitchNumberEntity switchNumberEntity) {
        if (isViewAttarchView())
            getView().getHomeSwitchNumber(switchNumberEntity);
    }

    @Override
    public void successHomeRechargePayment(HomeSwitchNumberCommitEntity homeRechargePaymentEntity) {
        if (isViewAttarchView())
            getView().getRechargePayment(homeRechargePaymentEntity);
    }

    @Override
    public void deleteSwitchNumber(HomeSwitchNumberCommitEntity homeSwitchNumberCommitEntity) {
        if (isViewAttarchView())
            getView().unBindNumber(homeSwitchNumberCommitEntity);
    }

    @Override
    public void setSwitchNumber(String token) {
        SwitchNumberModel model=new SwitchNumberModel();
        model.getHomeSwitchNumber(this,token);
    }

    @Override
    public void setRechargement(String meterId, String token) {
        SwitchNumberModel model=new SwitchNumberModel();
        model.getHomeRecharge(this,meterId,token);
    }

    @Override
    public void unBindNumber(String token, String meterID) {
        SwitchNumberModel model=new SwitchNumberModel();
        model.deleteSwitchNumber(this,meterID,token);
    }
}
