package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class OrderPayDetailbean implements Parcelable {
    private  Billbean bill_info;
    private  Paybean pay_info;
    private List<PaymentDetailbean> payment;
    private List<PayDishesbean> dishes;

    public List<PayDishesbean> getDishes() {
        return dishes;
    }

    public void setDishes(List<PayDishesbean> dishes) {
        this.dishes = dishes;
    }

    public List<PaymentDetailbean> getPayment() {
        return payment;
    }

    public void setPayment(List<PaymentDetailbean> payment) {
        this.payment = payment;
    }

    public Billbean getBill_info() {
        return bill_info;
    }

    public void setBill_info(Billbean bill_info) {
        this.bill_info = bill_info;
    }

    public Paybean getPay_info() {
        return pay_info;
    }

    public void setPay_info(Paybean pay_info) {
        this.pay_info = pay_info;
    }

    public OrderPayDetailbean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.bill_info, flags);
        dest.writeParcelable(this.pay_info, flags);
        dest.writeTypedList(this.payment);
        dest.writeTypedList(this.dishes);
    }

    protected OrderPayDetailbean(Parcel in) {
        this.bill_info = in.readParcelable(Billbean.class.getClassLoader());
        this.pay_info = in.readParcelable(Paybean.class.getClassLoader());
        this.payment = in.createTypedArrayList(PaymentDetailbean.CREATOR);
        this.dishes = in.createTypedArrayList(PayDishesbean.CREATOR);
    }

    public static final Creator<OrderPayDetailbean> CREATOR = new Creator<OrderPayDetailbean>() {
        @Override
        public OrderPayDetailbean createFromParcel(Parcel source) {
            return new OrderPayDetailbean(source);
        }

        @Override
        public OrderPayDetailbean[] newArray(int size) {
            return new OrderPayDetailbean[size];
        }
    };
}
