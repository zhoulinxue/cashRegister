package com.shigoo.cashregister.print.attr;

public class DishesPrint {
    private String name;
    private String num;
    private String price;
    private String others;

    public DishesPrint(String name, String num, String price, String others) {
        this.name = name;
        this.num = num;
        this.price = price;
        this.others = others;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
