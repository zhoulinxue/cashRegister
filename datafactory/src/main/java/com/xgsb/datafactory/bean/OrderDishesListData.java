package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class OrderDishesListData implements Parcelable {
    private int dishe_num;
    private int count_money;
    private int paid_moeny;
    private List<Dishesbean> data;

    public int getDishe_num() {
        return dishe_num;
    }

    public void setDishe_num(int dishe_num) {
        this.dishe_num = dishe_num;
    }

    public int getCount_money() {
        return count_money;
    }

    public void setCount_money(int count_money) {
        this.count_money = count_money;
    }

    public int getPaid_moeny() {
        return paid_moeny;
    }

    public void setPaid_moeny(int paid_moeny) {
        this.paid_moeny = paid_moeny;
    }

    public List<Dishesbean> getData() {
        return data;
    }

    public void setData(List<Dishesbean> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.dishe_num);
        dest.writeInt(this.count_money);
        dest.writeInt(this.paid_moeny);
        dest.writeTypedList(this.data);
    }

    public OrderDishesListData() {
    }

    protected OrderDishesListData(Parcel in) {
        this.dishe_num = in.readInt();
        this.count_money = in.readInt();
        this.paid_moeny = in.readInt();
        this.data = in.createTypedArrayList(Dishesbean.CREATOR);
    }

    public static final Parcelable.Creator<OrderDishesListData> CREATOR = new Parcelable.Creator<OrderDishesListData>() {
        @Override
        public OrderDishesListData createFromParcel(Parcel source) {
            return new OrderDishesListData(source);
        }

        @Override
        public OrderDishesListData[] newArray(int size) {
            return new OrderDishesListData[size];
        }
    };
}
