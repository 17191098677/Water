package com.smartcityin.waterknow.Entity.RegisterEntity;

/**
 * Author : Mr.老王
 * Created on 2018/4/17
 * E-mail : wkz123011@gmail.com
 */
public class RegisterEntity {

    /**
     * token : false
     * data : {"msg_id":"480909543227"}
     * mark : 84a4f7c3d8e78b7ed1b98842eed52d54
     * code : 200
     * value : OK
     */

    private boolean token;
    private DataBean data;
    private String mark;
    private int code;
    private String value;

    public boolean isToken() {
        return token;
    }

    public void setToken(boolean token) {
        this.token = token;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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

    public static class DataBean {
        /**
         * msg_id : 480909543227
         */

        private String msg_id;

        public String getMsg_id() {
            return msg_id;
        }

        public void setMsg_id(String msg_id) {
            this.msg_id = msg_id;
        }
    }
}
