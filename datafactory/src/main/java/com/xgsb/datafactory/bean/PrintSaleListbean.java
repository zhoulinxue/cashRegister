package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class PrintSaleListbean implements Parcelable {
    private String dishes_id;
    private String dish_number;
    private String dish_name;
    private String number;
    private String money;
    private String specification;
    private String dishes_category_name;
    private int id;
    private String drawer;

    public String getDishes_category_name() {
        return dishes_category_name;
    }

    public void setDishes_category_name(String dishes_category_name) {
        this.dishes_category_name = dishes_category_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrawer() {
        return drawer;
    }

    public void setDrawer(String drawer) {
        this.drawer = drawer;
    }

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

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public PrintSaleListbean() {
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
        dest.writeString(this.specification);
        dest.writeString(this.dishes_category_name);
        dest.writeInt(this.id);
        dest.writeString(this.drawer);
    }

    protected PrintSaleListbean(Parcel in) {
        this.dishes_id = in.readString();
        this.dish_number = in.readString();
        this.dish_name = in.readString();
        this.number = in.readString();
        this.money = in.readString();
        this.specification = in.readString();
        this.dishes_category_name = in.readString();
        this.id = in.readInt();
        this.drawer = in.readString();
    }

    public static final Creator<PrintSaleListbean> CREATOR = new Creator<PrintSaleListbean>() {
        @Override
        public PrintSaleListbean createFromParcel(Parcel source) {
            return new PrintSaleListbean(source);
        }

        @Override
        public PrintSaleListbean[] newArray(int size) {
            return new PrintSaleListbean[size];
        }
    };
}
