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
 * 作者: ---> 臻 <----
 * 联系方式：17801094126
 */

public abstract class BaseFragment<V,P extends BasePresenter<V>> extends Fragment {
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
        mPresenter=createPresenter();
        if(mPresenter!=null)
            mPresenter.attarchView((V) this);
        initView();
        initData();
        initAdapert();
        initListener();

        return inflate;
    }

    protected abstract P createPresenter();

    //关联XML文件
    public abstract int getLayoutId();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            //该Fragment可见，调用onVisble方法
            isVisible = true;
            onVisble();
        } else {
            //该Fragment不可见，调用onInVisble方法
            isVisible = false;
            onInVisble();
        }
    }


    //不可见或者为初始化之前进行的操作
    private void onInVisble() {}

    //需重写该方法，进行判断之后，再进行加载数据
    protected void onVisble() {

    }

    protected abstract void initAdapert();

    protected abstract void initListener();

    protected abstract void initView();

    //数据加载
    public abstract void initData();

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
