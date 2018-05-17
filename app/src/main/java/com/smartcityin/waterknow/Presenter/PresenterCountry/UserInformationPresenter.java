package com.smartcityin.waterknow.Presenter.PresenterCountry;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.My.MyUpdatePhoneEntity;
import com.smartcityin.waterknow.Entity.My.MyUpdatePhotoEntity;
import com.smartcityin.waterknow.Entity.My.MyUserInformationEntity;
import com.smartcityin.waterknow.Model.ModelCountry.MyUpdatePhoneModel;
import com.smartcityin.waterknow.Model.ModelCountry.MyUserInformationModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

import java.io.InputStream;

/**
 * Author : Mr.老王
 * Created on 2018/4/26
 * E-mail : wkz123011@gmail.com
 */
public class UserInformationPresenter extends BasePresenter<Commpart.MyInformationViewInf> implements Commpart.MyInformationPreInf,WaterCallback.MyUserInformationCallback,WaterCallback.MyUpdatePhoneCallback{

    @Override
    public void successMyUserInformation(MyUserInformationEntity myUserInformationEntity) {
        if (isViewAttarchView())
            getView().getMyInformation(myUserInformationEntity);
    }

    @Override
    public void updateImageHead(MyUpdatePhotoEntity myUpdatePhotoEntity) {
        if (isViewAttarchView())
            getView().getUpdateHeadImage(myUpdatePhotoEntity);
    }

    @Override
    public void setMyinformation(String token) {
        MyUserInformationModel model=new MyUserInformationModel();
        model.getMyUserInformation(this,token);
    }

    @Override
    public void updateHeadImage(String token, String head,String foot) {
        MyUserInformationModel model=new MyUserInformationModel();
        model.getUpdateHeadImage(this,token,head,foot);
    }

    @Override
    public void setUpdatePhone(String token) {
        MyUpdatePhoneModel model=new MyUpdatePhoneModel();
        model.getMyupdatePhone(this,token);
    }

    @Override
    public void successUpdatePhone(MyUpdatePhoneEntity myUpdatePhoneEntity) {
        if (isViewAttarchView())
            getView().getMyUpdatePhone(myUpdatePhoneEntity);
    }
}
