package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class SalePerformancebean implements Parcelable {
    private int id;
    private String time;
    private String orderNum;
    private String name;
    private String tableNum;
    private String money;
    private String money_xj;
    private String money_card;
    private String money_others;

    public SalePerformancebean(String time, String orderNum, String name, String tableNum, String money, String money_xj, String money_card, String money_others) {
        this.time = time;
        this.orderNum = orderNum;
        this.name = name;
        this.tableNum = tableNum;
        this.money = money;
        this.money_xj = money_xj;
        this.money_card = money_card;
        this.money_others = money_others;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTableNum() {
        return tableNum;
    }

    public void setTableNum(String tableNum) {
        this.tableNum = tableNum;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoney_xj() {
        return money_xj;
    }

    public void setMoney_xj(String money_xj) {
        this.money_xj = money_xj;
    }

    public String getMoney_card() {
        return money_card;
    }

    public void setMoney_card(String money_card) {
        this.money_card = money_card;
    }

    public String getMoney_others() {
        return money_others;
    }

    public void setMoney_others(String money_others) {
        this.money_others = money_others;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.time);
        dest.writeString(this.orderNum);
        dest.writeString(this.name);
        dest.writeString(this.tableNum);
        dest.writeString(this.money);
        dest.writeString(this.money_xj);
        dest.writeString(this.money_card);
        dest.writeString(this.money_others);
    }

    public SalePerformancebean() {
    }

    protected SalePerformancebean(Parcel in) {
        this.id = in.readInt();
        this.time = in.readString();
        this.orderNum = in.readString();
        this.name = in.readString();
        this.tableNum = in.readString();
        this.money = in.readString();
        this.money_xj = in.readString();
        this.money_card = in.readString();
        this.money_others = in.readString();
    }

    public static final Parcelable.Creator<SalePerformancebean> CREATOR = new Parcelable.Creator<SalePerformancebean>() {
        @Override
        public SalePerformancebean createFromParcel(Parcel source) {
            return new SalePerformancebean(source);
        }

        @Override
        public SalePerformancebean[] newArray(int size) {
            return new SalePerformancebean[size];
        }
    };
}
