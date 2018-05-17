package com.smartcityin.waterknow.Presenter.PresenterCountry;

import android.util.Log;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.My.MyAnnualQueryEntity;
import com.smartcityin.waterknow.Model.ModelCountry.AnnualQueryModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

/**
 * Author : Mr.老王
 * Created on 2018/5/4
 * E-mail : wkz123011@gmail.com
 */
public class MyAnnualQueryPresenter extends BasePresenter<Commpart.MyAnnualQueryViewInf> implements Commpart.MyAnnualQueryPreInf,WaterCallback.AnnualQueryCallback{
    @Override
    public void getAnnualEntity(MyAnnualQueryEntity myAnnualQueryEntity) {
        if (isViewAttarchView())
            getView().getMyAnnualQueryInf(myAnnualQueryEntity);
    }

    @Override
    public void setMyAnnualQuery(String token, String year, String water_id) {
        AnnualQueryModel model=new AnnualQueryModel();
        model.getMyAnnualQuery(this,token,year,water_id);
    }
}
