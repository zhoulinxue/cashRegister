package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FanJZbean implements Parcelable {
    private String token;
    private String bill_code;
    private String pay_num;
    private List<String> reverse_reason;

    public FanJZbean(String token, String bill_code, String pay_num, List<String> reverse_reason) {
        this.token = token;
        this.bill_code = bill_code;
        this.pay_num = pay_num;
        this.reverse_reason = reverse_reason;
    }

    public void setReverse_reason(List<String> reverse_reason) {
        this.reverse_reason = reverse_reason;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBill_code() {
        return bill_code;
    }

    public void setBill_code(String bill_code) {
        this.bill_code = bill_code;
    }

    public String getPay_num() {
        return pay_num;
    }

    public void setPay_num(String pay_num) {
        this.pay_num = pay_num;
    }

    public FanJZbean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeString(this.bill_code);
        dest.writeString(this.pay_num);
        dest.writeStringList(this.reverse_reason);
    }

    protected FanJZbean(Parcel in) {
        this.token = in.readString();
        this.bill_code = in.readString();
        this.pay_num = in.readString();
        this.reverse_reason = in.createStringArrayList();
    }

    public static final Creator<FanJZbean> CREATOR = new Creator<FanJZbean>() {
        @Override
        public FanJZbean createFromParcel(Parcel source) {
            return new FanJZbean(source);
        }

        @Override
        public FanJZbean[] newArray(int size) {
            return new FanJZbean[size];
        }
    };
}
