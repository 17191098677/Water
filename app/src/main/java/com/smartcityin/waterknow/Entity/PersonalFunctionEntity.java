package com.smartcityin.waterknow.Entity;

/**
 * Author : Mr.老王
 * Created on 2018/4/10
 * E-mail : wkz123011@gmail.com
 */
public class PersonalFunctionEntity {
    private int img;
    private String name;

    public PersonalFunctionEntity(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
