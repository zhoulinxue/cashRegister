package com.xgsb.datafactory.bean;

/**
 * Name: OrderSheetMainMenu
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-10 16:54
 */
public class OrderSheetMainMenu {
    private int logo;
    private String name;

    public OrderSheetMainMenu(int logo, String name) {
        this.logo = logo;
        this.name = name;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
