package com.smartcityin.waterknow.Presenter.PresenterCountry;

import android.util.Log;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.My.MyInToolPAymentEntity;
import com.smartcityin.waterknow.Entity.My.MyUpdatePhoneYZMEntity;
import com.smartcityin.waterknow.Model.ModelCountry.InToolPaymentModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

import java.util.Map;

/**
 * Author : Mr.老王
 * Created on 2018/4/29
 * E-mail : wkz123011@gmail.com
 */
public class MyToolPaymentPresenter extends BasePresenter<Commpart.MyToolPaymentViewInf> implements Commpart.MyToolPaymentPreInf,WaterCallback.MyInToolPaymentCallback{

    @Override
    public void setElectronicsToolPayment(Map<String, String> map, String[] orders) {
        InToolPaymentModel model=new InToolPaymentModel();
        model.getMyToolPayment(this,map,orders);
    }

    @Override
    public void successElectronicsToolPayment(MyInToolPAymentEntity my) {
        if (isViewAttarchView())
            getView().getMyToolPayment(my);
    }
}
