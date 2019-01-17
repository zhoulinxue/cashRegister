package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class PayTypebean implements Parcelable {
    private String pay_name;
    private int id;
    private String actual_tag;
    private String rebate_tag;
    private String able_tag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActual_tag() {
        return actual_tag;
    }

    public void setActual_tag(String actual_tag) {
        this.actual_tag = actual_tag;
    }

    public String getRebate_tag() {
        return rebate_tag;
    }

    public void setRebate_tag(String rebate_tag) {
        this.rebate_tag = rebate_tag;
    }

    public String getAble_tag() {
        return able_tag;
    }

    public void setAble_tag(String able_tag) {
        this.able_tag = able_tag;
    }

    public String getPay_name() {
        return pay_name;
    }

    public void setPay_name(String pay_name) {
        this.pay_name = pay_name;
    }

    public PayTypebean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pay_name);
        dest.writeInt(this.id);
        dest.writeString(this.actual_tag);
        dest.writeString(this.rebate_tag);
        dest.writeString(this.able_tag);
    }

    protected PayTypebean(Parcel in) {
        this.pay_name = in.readString();
        this.id = in.readInt();
        this.actual_tag = in.readString();
        this.rebate_tag = in.readString();
        this.able_tag = in.readString();
    }

    public static final Creator<PayTypebean> CREATOR = new Creator<PayTypebean>() {
        @Override
        public PayTypebean createFromParcel(Parcel source) {
            return new PayTypebean(source);
        }

        @Override
        public PayTypebean[] newArray(int size) {
            return new PayTypebean[size];
        }
    };
}
