package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class AddPayment implements Parcelable {
    private String pay_way;
    private String pay_name;
    private float pay_amount;

    public String getPay_way() {
        return pay_way;
    }

    public void setPay_way(String pay_way) {
        this.pay_way = pay_way;
    }

    public String getPay_name() {
        return pay_name;
    }

    public void setPay_name(String pay_name) {
        this.pay_name = pay_name;
    }

    public float getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(float pay_amount) {
        this.pay_amount = pay_amount;
    }

    public AddPayment() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddPayment && !TextUtils.isEmpty(getPay_way())) {
            return getPay_way().equals(((AddPayment) obj).getPay_way());
        }
        return super.equals(obj);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pay_way);
        dest.writeString(this.pay_name);
        dest.writeFloat(this.pay_amount);
    }

    protected AddPayment(Parcel in) {
        this.pay_way = in.readString();
        this.pay_name = in.readString();
        this.pay_amount = in.readFloat();
    }

    public static final Creator<AddPayment> CREATOR = new Creator<AddPayment>() {
        @Override
        public AddPayment createFromParcel(Parcel source) {
            return new AddPayment(source);
        }

        @Override
        public AddPayment[] newArray(int size) {
            return new AddPayment[size];
        }
    };
}
