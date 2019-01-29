package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderPerformanceDetailbean implements Parcelable {
    private String order_date;
    private String region_name;
    private String bill_code;
    private String dish_name;
    private String specification_name;
    private String sum_dish_qty;
    private String sum_finally_price;

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getBill_code() {
        return bill_code;
    }

    public void setBill_code(String bill_code) {
        this.bill_code = bill_code;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public String getSpecification_name() {
        return specification_name;
    }

    public void setSpecification_name(String specification_name) {
        this.specification_name = specification_name;
    }

    public String getSum_dish_qty() {
        return sum_dish_qty;
    }

    public void setSum_dish_qty(String sum_dish_qty) {
        this.sum_dish_qty = sum_dish_qty;
    }

    public String getSum_finally_price() {
        return sum_finally_price;
    }

    public void setSum_finally_price(String sum_finally_price) {
        this.sum_finally_price = sum_finally_price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.order_date);
        dest.writeString(this.region_name);
        dest.writeString(this.bill_code);
        dest.writeString(this.dish_name);
        dest.writeString(this.specification_name);
        dest.writeString(this.sum_dish_qty);
        dest.writeString(this.sum_finally_price);
    }

    public OrderPerformanceDetailbean() {
    }

    protected OrderPerformanceDetailbean(Parcel in) {
        this.order_date = in.readString();
        this.region_name = in.readString();
        this.bill_code = in.readString();
        this.dish_name = in.readString();
        this.specification_name = in.readString();
        this.sum_dish_qty = in.readString();
        this.sum_finally_price = in.readString();
    }

    public static final Parcelable.Creator<OrderPerformanceDetailbean> CREATOR = new Parcelable.Creator<OrderPerformanceDetailbean>() {
        @Override
        public OrderPerformanceDetailbean createFromParcel(Parcel source) {
            return new OrderPerformanceDetailbean(source);
        }

        @Override
        public OrderPerformanceDetailbean[] newArray(int size) {
            return new OrderPerformanceDetailbean[size];
        }
    };
}
