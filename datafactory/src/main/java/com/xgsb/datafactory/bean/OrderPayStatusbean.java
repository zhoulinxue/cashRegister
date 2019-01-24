package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class OrderPayStatusbean implements Parcelable {
    private float amount;
    private int hour;
    private int min;
    private List<Paybean> list;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public List<Paybean> getList() {
        return list;
    }

    public void setList(List<Paybean> list) {
        this.list = list;
    }

    public OrderPayStatusbean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.amount);
        dest.writeInt(this.hour);
        dest.writeInt(this.min);
        dest.writeTypedList(this.list);
    }

    protected OrderPayStatusbean(Parcel in) {
        this.amount = in.readFloat();
        this.hour = in.readInt();
        this.min = in.readInt();
        this.list = in.createTypedArrayList(Paybean.CREATOR);
    }

    public static final Creator<OrderPayStatusbean> CREATOR = new Creator<OrderPayStatusbean>() {
        @Override
        public OrderPayStatusbean createFromParcel(Parcel source) {
            return new OrderPayStatusbean(source);
        }

        @Override
        public OrderPayStatusbean[] newArray(int size) {
            return new OrderPayStatusbean[size];
        }
    };
}
