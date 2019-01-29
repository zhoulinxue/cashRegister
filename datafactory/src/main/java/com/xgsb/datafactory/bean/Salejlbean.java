package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Salejlbean implements Parcelable {
    private String region_name;
    private String table_number;
    private String dish_number;
    private String dish_name;
    private String sale_price;
    private String last_price;
    private String dish_qty;
    private String finally_price;
    private String order_date;

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getTable_number() {
        return table_number;
    }

    public void setTable_number(String table_number) {
        this.table_number = table_number;
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

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getLast_price() {
        return last_price;
    }

    public void setLast_price(String last_price) {
        this.last_price = last_price;
    }

    public String getDish_qty() {
        return dish_qty;
    }

    public void setDish_qty(String dish_qty) {
        this.dish_qty = dish_qty;
    }

    public String getFinally_price() {
        return finally_price;
    }

    public void setFinally_price(String finally_price) {
        this.finally_price = finally_price;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.region_name);
        dest.writeString(this.table_number);
        dest.writeString(this.dish_number);
        dest.writeString(this.dish_name);
        dest.writeString(this.sale_price);
        dest.writeString(this.last_price);
        dest.writeString(this.dish_qty);
        dest.writeString(this.finally_price);
        dest.writeString(this.order_date);
    }

    public Salejlbean() {
    }

    protected Salejlbean(Parcel in) {
        this.region_name = in.readString();
        this.table_number = in.readString();
        this.dish_number = in.readString();
        this.dish_name = in.readString();
        this.sale_price = in.readString();
        this.last_price = in.readString();
        this.dish_qty = in.readString();
        this.finally_price = in.readString();
        this.order_date = in.readString();
    }

    public static final Parcelable.Creator<Salejlbean> CREATOR = new Parcelable.Creator<Salejlbean>() {
        @Override
        public Salejlbean createFromParcel(Parcel source) {
            return new Salejlbean(source);
        }

        @Override
        public Salejlbean[] newArray(int size) {
            return new Salejlbean[size];
        }
    };
}
