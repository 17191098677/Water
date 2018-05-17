package com.smartcityin.waterknow.Presenter.PresenterCountry;

import android.util.Log;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.Home.HomeSwitchNumberCommitEntity;
import com.smartcityin.waterknow.Entity.Login.LoginEntity;
import com.smartcityin.waterknow.Model.ModelCountry.LoginModel;
import com.smartcityin.waterknow.Model.ModelCountry.QQModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

/**
 * Author : Mr.老王
 * Created on 2018/4/18
 * E-mail : wkz123011@gmail.com
 */
public class LoginPresenter extends BasePresenter<Commpart.LoginViewInf> implements Commpart.LoginPreInf,WaterCallback.LoginCallback,WaterCallback.QQCallback{

    @Override
    public void successLoginData(Object loginEntity) {
        if (isViewAttarchView())
            getView().getLoginData((LoginEntity) loginEntity);
    }

    @Override
    public void getQQEntity(HomeSwitchNumberCommitEntity homeSwitchNumberCommitEntity) {
        if (isViewAttarchView())
            getView().getQQLogin(homeSwitchNumberCommitEntity);
    }

    @Override
    public void setLogin(String username, String password) {
        LoginModel model=new LoginModel();
        model.getLogin(this,username,password);
    }

    @Override
    public void setQQ(String access_token, String openid) {
        QQModel model=new QQModel();
        model.getQQEntity(this,access_token,openid);
    }

    @Override
    public void QQLogin(String open_id,String nickname,String head) {
        LoginModel loginModel=new LoginModel();
        loginModel.QQLogin(this,open_id,nickname,head);
    }

    @Override
    public void getQQEntity(Object qqEntity) {
        if (isViewAttarchView())
            getView().getQQData((String) qqEntity);
    }
}
