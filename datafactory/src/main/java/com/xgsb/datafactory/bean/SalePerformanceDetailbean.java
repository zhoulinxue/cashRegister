package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SalePerformanceDetailbean implements Parcelable {
    private String bill_code;
    private String guest_tel;
    private String guest_name;
    private String guest_qty;
    private String order_date;
    private String end_date;
    private List<Salejlbean> list;
    private List<Paymentbean> payment;

    public String getBill_code() {
        return bill_code;
    }

    public void setBill_code(String bill_code) {
        this.bill_code = bill_code;
    }

    public String getGuest_tel() {
        return guest_tel;
    }

    public void setGuest_tel(String guest_tel) {
        this.guest_tel = guest_tel;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public String getGuest_qty() {
        return guest_qty;
    }

    public void setGuest_qty(String guest_qty) {
        this.guest_qty = guest_qty;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public List<Salejlbean> getList() {
        return list;
    }

    public void setList(List<Salejlbean> list) {
        this.list = list;
    }

    public List<Paymentbean> getPayment() {
        return payment;
    }

    public void setPayment(List<Paymentbean> payment) {
        this.payment = payment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bill_code);
        dest.writeString(this.guest_tel);
        dest.writeString(this.guest_name);
        dest.writeString(this.guest_qty);
        dest.writeString(this.order_date);
        dest.writeString(this.end_date);
        dest.writeTypedList(this.list);
        dest.writeTypedList(this.payment);
    }

    public SalePerformanceDetailbean() {
    }

    protected SalePerformanceDetailbean(Parcel in) {
        this.bill_code = in.readString();
        this.guest_tel = in.readString();
        this.guest_name = in.readString();
        this.guest_qty = in.readString();
        this.order_date = in.readString();
        this.end_date = in.readString();
        this.list = in.createTypedArrayList(Salejlbean.CREATOR);
        this.payment = in.createTypedArrayList(Paymentbean.CREATOR);
    }

    public static final Parcelable.Creator<SalePerformanceDetailbean> CREATOR = new Parcelable.Creator<SalePerformanceDetailbean>() {
        @Override
        public SalePerformanceDetailbean createFromParcel(Parcel source) {
            return new SalePerformanceDetailbean(source);
        }

        @Override
        public SalePerformanceDetailbean[] newArray(int size) {
            return new SalePerformanceDetailbean[size];
        }
    };
}
