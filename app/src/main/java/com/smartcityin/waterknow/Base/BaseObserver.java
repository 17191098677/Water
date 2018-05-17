package com.smartcityin.waterknow.Base;



import com.smartcityin.waterknow.Model.ModelListener.OnNextListener;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by aa on 2018/3/16.
 */

public class BaseObserver<T> implements Observer<T> {
    private OnNextListener listener;

    public BaseObserver(OnNextListener listener) {
        this.listener = listener;
    }
    @Override
    public void onSubscribe(Disposable d) {

    }
    @Override
    public void onNext(T t) {
        listener.success(t);
    }

    @Override
    public void onError(Throwable e) {
        listener.error(e.toString());
    }

    @Override
    public void onComplete() {

    }
}
