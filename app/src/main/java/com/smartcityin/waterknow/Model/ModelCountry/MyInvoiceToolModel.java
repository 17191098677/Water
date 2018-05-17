package com.smartcityin.waterknow.Model.ModelCountry;

import com.smartcityin.waterknow.Entity.My.MyInvoiceToolEntity;
import com.smartcityin.waterknow.Global.RetrofitService;
import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Model.ModelListener.WaterKnowModelInf;
import com.smartcityin.waterknow.NetWork.Http.HttpFactory;

/**
 * Author : Mr.老王
 * Created on 2018/4/28
 * E-mail : wkz123011@gmail.com
 */
public class MyInvoiceToolModel implements WaterKnowModelInf.MyInvoiceToolInf{
    @Override
    public void getMyInvoiceTool(final WaterCallback.MyInvoiceToolCallback callback, String token) {
        RetrofitService retrofitService = HttpFactory.createRetrofitService(RetrofitService.class);
        HttpFactory.creteObserverable(retrofitService.getMyInvoiceTool(token), new OnNextListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(Object data) {
                callback.successMyInvoiceTool((MyInvoiceToolEntity) data);
            }
        });
    }
}
