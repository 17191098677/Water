package com.smartcityin.waterknow.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author : Mr.老王
 * Created on 2018/4/8
 * E-mail : wkz123011@gmail.com
 */
public abstract class BaseWaterFragment<V,P extends BasePresenter<V>> extends Fragment {
    //设置fragment是否可见
    public boolean isVisible;
    //P层对象
    protected P mPresenter;
    protected View inflate;
    //绑定ButterKnife
    Unbinder unbinder;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        if(mPresenter!=null)
            mPresenter.attarchView((V) this);
        initData();
        initAdapert();
        initListener();
        return inflate;
    }
    //关联XML文件
    public abstract int getLayoutId();

    //不可见或者为初始化之前进行的操作
    private void onInVisble() {}

    //需重写该方法，进行判断之后，再进行加载数据
    protected void onVisble() {

    }
    protected abstract void initView();
    //数据加载
    public abstract void initData();
    protected abstract void initAdapert();
    protected abstract void initListener();
    //在销毁Fragment的时候，注销Eventbus
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mPresenter!=null){
            mPresenter.detachView();
        }
    }
}
