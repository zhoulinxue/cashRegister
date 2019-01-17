package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SettalOrderResultbean implements Parcelable {
    private String remarks_info;
    private List<SettalItem> data;
    private String bill_code;
    private int dishe_num;
    private int count_money;
    private int paid_moeny;

    public String getRemarks_info() {
        return remarks_info;
    }

    public void setRemarks_info(String remarks_info) {
        this.remarks_info = remarks_info;
    }

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

    public String getBill_code() {
        return bill_code;
    }

    public void setBill_code(String bill_code) {
        this.bill_code = bill_code;
    }

    public List<SettalItem> getData() {
        return data;
    }

    public void setData(List<SettalItem> data) {
        this.data = data;
    }

    public SettalOrderResultbean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.remarks_info);
        dest.writeTypedList(this.data);
        dest.writeString(this.bill_code);
        dest.writeInt(this.dishe_num);
        dest.writeInt(this.count_money);
        dest.writeInt(this.paid_moeny);
    }

    protected SettalOrderResultbean(Parcel in) {
        this.remarks_info = in.readString();
        this.data = in.createTypedArrayList(SettalItem.CREATOR);
        this.bill_code = in.readString();
        this.dishe_num = in.readInt();
        this.count_money = in.readInt();
        this.paid_moeny = in.readInt();
    }

    public static final Creator<SettalOrderResultbean> CREATOR = new Creator<SettalOrderResultbean>() {
        @Override
        public SettalOrderResultbean createFromParcel(Parcel source) {
            return new SettalOrderResultbean(source);
        }

        @Override
        public SettalOrderResultbean[] newArray(int size) {
            return new SettalOrderResultbean[size];
        }
    };
}
