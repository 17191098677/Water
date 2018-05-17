package com.smartcityin.waterknow.Presenter.PresenterCountry;

import android.support.annotation.Nullable;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.Home.WisdomEntity;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelCountry.WisdomModel;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

/**
 * Author : Mr.老王
 * Created on 2018/4/8
 * E-mail : wkz123011@gmail.com
 */
public class WisdomPresenter extends BasePresenter<Commpart.WisdomLifeViewInf> implements Commpart.WisdomLifePreInf,WaterCallback.WisdomLifeCallback<WisdomEntity>{

    @Override
    public void successWisdomLife(WisdomEntity wisdomEntity) {
        if(isViewAttarchView())
            getView().getData(wisdomEntity);
    }

    @Override
    public void errorWisdomLife(String error) {

    }

    @Override
    public void setWisdomLife(String token, @Nullable int page) {
        WisdomModel model=new WisdomModel();
        model.getWisdomLifeCallback(this,token,page);
    }
}
