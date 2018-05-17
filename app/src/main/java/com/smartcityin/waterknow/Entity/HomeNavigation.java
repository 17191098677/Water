package com.smartcityin.waterknow.Entity;

/**
 * 首页的四个按钮
 */
public class HomeNavigation{
    private String name;
    private int img;

    public HomeNavigation(String name, int img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
