package com.smartcityin.waterknow.Model.ModelListener;

/**
 * Author : Mr.老王
 * Created on 2018/3/21
 * E-mail : wkz123011@gmail.com
 */

public interface OnNextListener<T> {
    void error(String error);
    void success(T data);
}
