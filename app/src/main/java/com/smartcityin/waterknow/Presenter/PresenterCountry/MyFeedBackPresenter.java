package com.smartcityin.waterknow.Presenter.PresenterCountry;

import android.support.annotation.Nullable;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.My.MyFeedBackEntity;
import com.smartcityin.waterknow.Model.ModelCountry.MyFeedBackModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

/**
 * Author : Mr.老王
 * Created on 2018/4/27
 * E-mail : wkz123011@gmail.com
 */
public class MyFeedBackPresenter extends BasePresenter<Commpart.MyFeedBackViewInf> implements Commpart.MyFeedBackPreInf,WaterCallback.MyFeedBakcCallback {

    @Override
    public void successMyFeedBack(MyFeedBackEntity myFeedBackEntity) {
        if (isViewAttarchView())
            getView().getMyFeedBack(myFeedBackEntity);
    }

    @Override
    public void setFeedBack(String token, String content, @Nullable String Email) {
        MyFeedBackModel model=new MyFeedBackModel();
        model.getMyFeedBack(this,token,content,Email);
    }
}
