package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderPerformancebean implements Parcelable {
    private int id;
    private String typeName;
    private String dishes_number;
    private String name;
    private String unit;
    private String number;
    private String disher_money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDishes_number() {
        return dishes_number;
    }

    public void setDishes_number(String dishes_number) {
        this.dishes_number = dishes_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDisher_money() {
        return disher_money;
    }

    public void setDisher_money(String disher_money) {
        this.disher_money = disher_money;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.typeName);
        dest.writeString(this.dishes_number);
        dest.writeString(this.name);
        dest.writeString(this.unit);
        dest.writeString(this.number);
        dest.writeString(this.disher_money);
    }

    public OrderPerformancebean(String typeName, String dishes_number, String name, String unit, String number, String disher_money) {
        this.typeName = typeName;
        this.dishes_number = dishes_number;
        this.name = name;
        this.unit = unit;
        this.number = number;
        this.disher_money = disher_money;
    }

    public OrderPerformancebean() {
    }

    protected OrderPerformancebean(Parcel in) {
        this.id = in.readInt();
        this.typeName = in.readString();
        this.dishes_number = in.readString();
        this.name = in.readString();
        this.unit = in.readString();
        this.number = in.readString();
        this.disher_money = in.readString();
    }

    public static final Parcelable.Creator<OrderPerformancebean> CREATOR = new Parcelable.Creator<OrderPerformancebean>() {
        @Override
        public OrderPerformancebean createFromParcel(Parcel source) {
            return new OrderPerformancebean(source);
        }

        @Override
        public OrderPerformancebean[] newArray(int size) {
            return new OrderPerformancebean[size];
        }
    };
}
