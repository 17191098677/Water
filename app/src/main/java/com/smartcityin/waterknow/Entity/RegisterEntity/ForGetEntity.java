package com.smartcityin.waterknow.Entity.RegisterEntity;

/**
 * Author : Mr.老王
 * Created on 2018/4/18
 * E-mail : wkz123011@gmail.com
 */
public class ForGetEntity {

    /**
     * token : null
     * data : null
     * mark :
     * code : 200
     * value : 成功，请从新登录
     */

    private Object token;
    private Object data;
    private String mark;
    private int code;
    private String value;

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
