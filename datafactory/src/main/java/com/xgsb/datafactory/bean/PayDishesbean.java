package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class PayDishesbean implements Parcelable {
  private String dish_name;
    private String dish_qty;
    private String specification_name;
    private String sale_price;
    private String order_date;
    private String order_man;
    private String order_source;
    private String sale_category;

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public String getDish_qty() {
        return dish_qty;
    }

    public void setDish_qty(String dish_qty) {
        this.dish_qty = dish_qty;
    }

    public String getSpecification_name() {
        return specification_name;
    }

    public void setSpecification_name(String specification_name) {
        this.specification_name = specification_name;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_man() {
        return order_man;
    }

    public void setOrder_man(String order_man) {
        this.order_man = order_man;
    }

    public String getOrder_source() {
        return order_source;
    }

    public void setOrder_source(String order_source) {
        this.order_source = order_source;
    }

    public String getSale_category() {
        return sale_category;
    }

    public void setSale_category(String sale_category) {
        this.sale_category = sale_category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dish_name);
        dest.writeString(this.dish_qty);
        dest.writeString(this.specification_name);
        dest.writeString(this.sale_price);
        dest.writeString(this.order_date);
        dest.writeString(this.order_man);
        dest.writeString(this.order_source);
        dest.writeString(this.sale_category);
    }

    protected PayDishesbean(Parcel in) {
        this.dish_name = in.readString();
        this.dish_qty = in.readString();
        this.specification_name = in.readString();
        this.sale_price = in.readString();
        this.order_date = in.readString();
        this.order_man = in.readString();
        this.order_source = in.readString();
        this.sale_category = in.readString();
    }

    public static final Creator<PayDishesbean> CREATOR = new Creator<PayDishesbean>() {
        @Override
        public PayDishesbean createFromParcel(Parcel source) {
            return new PayDishesbean(source);
        }

        @Override
        public PayDishesbean[] newArray(int size) {
            return new PayDishesbean[size];
        }
    };
}
