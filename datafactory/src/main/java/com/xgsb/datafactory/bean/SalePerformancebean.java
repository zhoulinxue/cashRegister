package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class SalePerformancebean implements Parcelable {
    private String bill_date;
    private String bill_code;
    private String guest_name;
    private String table_number;
    private String sum_pay_amount;

    public String getBill_date() {
        return bill_date;
    }

    public void setBill_date(String bill_date) {
        this.bill_date = bill_date;
    }

    public String getBill_code() {
        return bill_code;
    }

    public void setBill_code(String bill_code) {
        this.bill_code = bill_code;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public String getTable_number() {
        return table_number;
    }

    public void setTable_number(String table_number) {
        this.table_number = table_number;
    }

    public String getSum_pay_amount() {
        return sum_pay_amount;
    }

    public void setSum_pay_amount(String sum_pay_amount) {
        this.sum_pay_amount = sum_pay_amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bill_date);
        dest.writeString(this.bill_code);
        dest.writeString(this.guest_name);
        dest.writeString(this.table_number);
        dest.writeString(this.sum_pay_amount);
    }

    public SalePerformancebean() {
    }

    protected SalePerformancebean(Parcel in) {
        this.bill_date = in.readString();
        this.bill_code = in.readString();
        this.guest_name = in.readString();
        this.table_number = in.readString();
        this.sum_pay_amount = in.readString();
    }

    public static final Creator<SalePerformancebean> CREATOR = new Creator<SalePerformancebean>() {
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
