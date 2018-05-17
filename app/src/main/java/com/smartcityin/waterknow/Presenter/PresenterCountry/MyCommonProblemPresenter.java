package com.smartcityin.waterknow.Presenter.PresenterCountry;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.Service.ServiceUserHelpEntity;
import com.smartcityin.waterknow.Model.ModelCountry.MyCommonModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

/**
 * Author : Mr.老王
 * Created on 2018/4/11
 * E-mail : wkz123011@gmail.com
 */
public class MyCommonProblemPresenter extends BasePresenter<Commpart.MyCommonViewInf> implements Commpart.MyCommonProblemPreInf,WaterCallback.MyCommonProblemCallback<ServiceUserHelpEntity>{

    @Override
    public void successMyCommonData(ServiceUserHelpEntity myCommonProblem) {
        if (isViewAttarchView())
            getView().getMyCommonData(myCommonProblem);
    }

    @Override
    public void errorMyCommon(String error) {

    }

    @Override
    public void setCommonProblem(String token) {
        MyCommonModel model=new MyCommonModel();
        model.getMyCommonProblemData(this,token);
    }
}
