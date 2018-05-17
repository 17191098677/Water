package com.smartcityin.waterknow.Entity.My;

/**
 * Author : Mr.老王
 * Created on 2018/4/27
 * E-mail : wkz123011@gmail.com
 */
public class MyFeedBackEntity {

    /**
     * token : LSO+ry82r/sEs8lh7tv8Cw==
     * data : null
     * mark :
     * code : 200
     * value : 已提交反馈
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
