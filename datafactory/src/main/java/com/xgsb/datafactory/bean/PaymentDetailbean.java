package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentDetailbean implements Parcelable {
    private String pay_name;
    private String pay_amount;
    private String create_date;
    private String cashier_name;

    public String getPay_name() {
        return pay_name;
    }

    public void setPay_name(String pay_name) {
        this.pay_name = pay_name;
    }

    public String getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(String pay_amount) {
        this.pay_amount = pay_amount;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getCashier_name() {
        return cashier_name;
    }

    public void setCashier_name(String cashier_name) {
        this.cashier_name = cashier_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pay_name);
        dest.writeString(this.pay_amount);
        dest.writeString(this.create_date);
        dest.writeString(this.cashier_name);
    }

    public PaymentDetailbean() {
    }

    protected PaymentDetailbean(Parcel in) {
        this.pay_name = in.readString();
        this.pay_amount = in.readString();
        this.create_date = in.readString();
        this.cashier_name = in.readString();
    }

    public static final Parcelable.Creator<PaymentDetailbean> CREATOR = new Parcelable.Creator<PaymentDetailbean>() {
        @Override
        public PaymentDetailbean createFromParcel(Parcel source) {
            return new PaymentDetailbean(source);
        }

        @Override
        public PaymentDetailbean[] newArray(int size) {
            return new PaymentDetailbean[size];
        }
    };
}
