package com.smartcityin.waterknow.Presenter.PresenterCountry;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.My.MyOrderEntity;
import com.smartcityin.waterknow.Model.ModelCountry.MyOrderModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

import java.util.ArrayList;

/**
 * Author : Mr.老王
 * Created on 2018/4/10
 * E-mail : wkz123011@gmail.com
 */
public class MyOrderPresenter extends BasePresenter<Commpart.MyOrderViewInf> implements Commpart.MyOrderPreInf,WaterCallback.MyOrderCallback<MyOrderEntity>{
    @Override
    public void successMyOrder(MyOrderEntity myOrder) {
        if (isViewAttarchView())
            getView().getMyOrderData(myOrder);
    }

    @Override
    public void errorMyOrder(String error) {

    }

    @Override
    public void setMyOrder(String token, int page) {
        MyOrderModel myOrderModel=new MyOrderModel();
        myOrderModel.getMyOrderData(this,token,page);
    }
}
