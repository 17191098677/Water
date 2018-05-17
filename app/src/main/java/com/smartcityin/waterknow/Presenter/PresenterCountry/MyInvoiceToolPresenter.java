package com.smartcityin.waterknow.Presenter.PresenterCountry;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.My.MyInvoiceToolEntity;
import com.smartcityin.waterknow.Model.ModelCountry.MyInvoiceToolModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

/**
 * Author : Mr.老王
 * Created on 2018/4/28
 * E-mail : wkz123011@gmail.com
 */
public class MyInvoiceToolPresenter extends BasePresenter<Commpart.MyInvoiceViewInf> implements Commpart.MyInvoiceToolPreInf,WaterCallback.MyInvoiceToolCallback{
    @Override
    public void successMyInvoiceTool(MyInvoiceToolEntity myInvoiceToolEntity) {
        if (isViewAttarchView())
            getView().getMyInvoice(myInvoiceToolEntity);
    }

    @Override
    public void setMyInvoiceTool(String token) {
        MyInvoiceToolModel model=new MyInvoiceToolModel();
        model.getMyInvoiceTool(this,token);
    }
}
