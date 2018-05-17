package com.smartcityin.waterknow.Presenter.PresenterCountry;


import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.Home.HomeWaterKnowEntity;
import com.smartcityin.waterknow.Model.ModelCountry.WaterKnowLedgeModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

/**
 * Author : Mr.老王
 * Created on 2018/4/8
 * E-mail : wkz123011@gmail.com
 */
public class WaterKnowLedgePresenter extends BasePresenter<Commpart.WaterKnowLedgeViewInf> implements Commpart.WaterKnowLedgePerInf,WaterCallback.WaterKnowCallback<HomeWaterKnowEntity> {

    @Override
    public void successWaterKnow(HomeWaterKnowEntity waterKnow) {
        if(isViewAttarchView())
            getView().getWaterKnowLedgeData(waterKnow);
    }

    @Override
    public void errorWaterKnow(String error) {

    }

    @Override
    public void setHomeUsetWaterKnow(String token, int page) {
        WaterKnowLedgeModel model=new WaterKnowLedgeModel();
        model.getWaterKnowLedge(this,token,page);
    }
}
