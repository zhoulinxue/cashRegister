package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class SaleDetailbean implements Parcelable {
    private String dishes_id;
    private String dish_number;
    private String dish_name;
    private String number;
    private String money;

    public String getDishes_id() {
        return dishes_id;
    }

    public void setDishes_id(String dishes_id) {
        this.dishes_id = dishes_id;
    }

    public String getDish_number() {
        return dish_number;
    }

    public void setDish_number(String dish_number) {
        this.dish_number = dish_number;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dishes_id);
        dest.writeString(this.dish_number);
        dest.writeString(this.dish_name);
        dest.writeString(this.number);
        dest.writeString(this.money);
    }

    public SaleDetailbean() {
    }

    protected SaleDetailbean(Parcel in) {
        this.dishes_id = in.readString();
        this.dish_number = in.readString();
        this.dish_name = in.readString();
        this.number = in.readString();
        this.money = in.readString();
    }

    public static final Parcelable.Creator<SaleDetailbean> CREATOR = new Parcelable.Creator<SaleDetailbean>() {
        @Override
        public SaleDetailbean createFromParcel(Parcel source) {
            return new SaleDetailbean(source);
        }

        @Override
        public SaleDetailbean[] newArray(int size) {
            return new SaleDetailbean[size];
        }
    };
}
