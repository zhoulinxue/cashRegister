package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

public class BaseGivebean implements Parcelable {
    private String giving_id;
    private String dishes_id;
    private String staff_id;
    private String specification_id;
    private String gift_reason;
    private String table_number;
    private String bill_date;
    private int dish_qty;
    private String bill_code;

    public String getGiving_id() {
        return giving_id;
    }

    public void setGiving_id(String giving_id) {
        this.giving_id = giving_id;
    }

    public String getDishes_id() {
        return dishes_id;
    }

    public void setDishes_id(String dishes_id) {
        this.dishes_id = dishes_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getSpecification_id() {
        return specification_id;
    }

    public void setSpecification_id(String specification_id) {
        this.specification_id = specification_id;
    }

    public String getGift_reason() {
        return gift_reason;
    }

    public void setGift_reason(String gift_reason) {
        this.gift_reason = gift_reason;
    }

    public String getTable_number() {
        return table_number;
    }

    public void setTable_number(String table_number) {
        this.table_number = table_number;
    }

    public String getBill_date() {
        return bill_date;
    }

    public void setBill_date(String bill_date) {
        this.bill_date = bill_date;
    }

    public int getDish_qty() {
        return dish_qty;
    }

    public void setDish_qty(int dish_qty) {
        this.dish_qty = dish_qty;
    }

    public String getBill_code() {
        return bill_code;
    }

    public void setBill_code(String bill_code) {
        this.bill_code = bill_code;
    }

    public BaseGivebean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.giving_id);
        dest.writeString(this.dishes_id);
        dest.writeString(this.staff_id);
        dest.writeString(this.specification_id);
        dest.writeString(this.gift_reason);
        dest.writeString(this.table_number);
        dest.writeString(this.bill_date);
        dest.writeInt(this.dish_qty);
        dest.writeString(this.bill_code);
    }

    protected BaseGivebean(Parcel in) {
        this.giving_id = in.readString();
        this.dishes_id = in.readString();
        this.staff_id = in.readString();
        this.specification_id = in.readString();
        this.gift_reason = in.readString();
        this.table_number = in.readString();
        this.bill_date = in.readString();
        this.dish_qty = in.readInt();
        this.bill_code = in.readString();
    }

    public static final Creator<BaseGivebean> CREATOR = new Creator<BaseGivebean>() {
        @Override
        public BaseGivebean createFromParcel(Parcel source) {
            return new BaseGivebean(source);
        }

        @Override
        public BaseGivebean[] newArray(int size) {
            return new BaseGivebean[size];
        }
    };
}
