package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderPerformancebean implements Parcelable {
    private String dishes_category_name;
    private String dish_number;
    private String dish_name;
    private String specification_id;
    private String specification_name;
    private String sale_price;
    private String sum_dish_qty;
    private String sum_finally_price;
    private String dishes_id;
    private String combo_id;
    private String dish_tag;

    public String getDishes_category_name() {
        return dishes_category_name;
    }

    public void setDishes_category_name(String dishes_category_name) {
        this.dishes_category_name = dishes_category_name;
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

    public String getSpecification_id() {
        return specification_id;
    }

    public void setSpecification_id(String specification_id) {
        this.specification_id = specification_id;
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

    public String getDishes_id() {
        return dishes_id;
    }

    public void setDishes_id(String dishes_id) {
        this.dishes_id = dishes_id;
    }

    public String getCombo_id() {
        return combo_id;
    }

    public void setCombo_id(String combo_id) {
        this.combo_id = combo_id;
    }

    public String getDish_tag() {
        return dish_tag;
    }

    public void setDish_tag(String dish_tag) {
        this.dish_tag = dish_tag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dishes_category_name);
        dest.writeString(this.dish_number);
        dest.writeString(this.dish_name);
        dest.writeString(this.specification_id);
        dest.writeString(this.specification_name);
        dest.writeString(this.sale_price);
        dest.writeString(this.sum_dish_qty);
        dest.writeString(this.sum_finally_price);
        dest.writeString(this.dishes_id);
        dest.writeString(this.combo_id);
        dest.writeString(this.dish_tag);
    }

    public OrderPerformancebean() {
    }

    protected OrderPerformancebean(Parcel in) {
        this.dishes_category_name = in.readString();
        this.dish_number = in.readString();
        this.dish_name = in.readString();
        this.specification_id = in.readString();
        this.specification_name = in.readString();
        this.sale_price = in.readString();
        this.sum_dish_qty = in.readString();
        this.sum_finally_price = in.readString();
        this.dishes_id = in.readString();
        this.combo_id = in.readString();
        this.dish_tag = in.readString();
    }

    public static final Creator<OrderPerformancebean> CREATOR = new Creator<OrderPerformancebean>() {
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
