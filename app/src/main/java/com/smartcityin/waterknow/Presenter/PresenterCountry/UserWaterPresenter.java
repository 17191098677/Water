package com.smartcityin.waterknow.Presenter.PresenterCountry;

import android.util.Log;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.UserWater.UserWaterEntity;
import com.smartcityin.waterknow.Model.ModelCountry.UserWaterModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

/**
 * Author : Mr.老王
 * Created on 2018/4/9
 * E-mail : wkz123011@gmail.com
 */
public class UserWaterPresenter extends BasePresenter<Commpart.UserWaterViewInf> implements Commpart.UserWaterPreInf,WaterCallback.UserWaterCallback<UserWaterEntity> {
    @Override
    public void successUserWater(UserWaterEntity userWater) {
        if (isViewAttarchView())
            getView().getUserWaterData(userWater);
    }

    @Override
    public void errorUserWater(String error) {

    }

    @Override
    public void setUserWater(String token, String water_id) {
        UserWaterModel userWaterModel=new UserWaterModel();
        userWaterModel.getUserWaterData(this,token,water_id);
    }
}
