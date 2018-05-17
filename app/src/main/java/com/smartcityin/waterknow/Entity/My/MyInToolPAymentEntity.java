package com.smartcityin.waterknow.Entity.My;

/**
 * Author : Mr.老王
 * Created on 2018/5/3
 * E-mail : wkz123011@gmail.com
 */
public class MyInToolPAymentEntity {

    /**
     * token : N9o3CgsQ+2RUOCjccyPFSw==
     * data : null
     * mark :
     * code : 200
     * value : 已提交财务进行发票开具
     */

    private String token;
    private Object data;
    private String mark;
    private int code;
    private String value;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
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
