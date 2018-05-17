package com.smartcityin.waterknow.Presenter.PresenterCountry;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.BindingWaterMeter.BindingWaterEntity;
import com.smartcityin.waterknow.Model.ModelCountry.BindingWaterModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

/**
 * Author : Mr.老王
 * Created on 2018/4/19
 * E-mail : wkz123011@gmail.com
 */
public class BindingWaterPresenter extends BasePresenter<Commpart.BindingWaterViewInf> implements Commpart.BindingWaterPreInf,WaterCallback.BindingWaterCallback{

    @Override
    public void successBindingWater(Object bindingWaterEntity) {
        if (isViewAttarchView())
            getView().getBindingWaterData((BindingWaterEntity) bindingWaterEntity);
    }

    @Override
    public void setBindingWater(String meter_id, String token) {
        BindingWaterModel model=new BindingWaterModel();
        model.getBindingWater(this,meter_id,token);
    }
}
