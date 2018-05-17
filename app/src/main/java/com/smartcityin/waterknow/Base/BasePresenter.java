package com.smartcityin.waterknow.Base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Author : Mr.老王
 * Created on 2018/3/21
 * E-mail : wkz123011@gmail.com
 */

public abstract class BasePresenter<V> {
    //Viewceng层接口的引用
    protected Reference<V> mViewRef;
    //View层接口弱引用
    public void attarchView(V view){
        mViewRef=new WeakReference<V>(view);
    }
    //获取View层接口
    protected V getView(){
        return mViewRef.get();
    }
    //判断View是否为空
    public boolean isViewAttarchView(){
        return mViewRef!=null&&mViewRef.get()!=null;
    }

    //用来关联View层的生命周期，看View层是否已经销毁
    public void detachView(){
        if(mViewRef!=null){
            mViewRef.clear();
            mViewRef=null;
        }
    }
}
