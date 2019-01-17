package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Chedanbean implements Parcelable {
    private String token;
    private String bill_code;
    private List<String> revoke_reason;

    public Chedanbean(String token, String bill_code, List<String> revoke_reason) {
        this.token = token;
        this.bill_code = bill_code;
        this.revoke_reason = revoke_reason;
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

    public List<String> getRevoke_reason() {
        return revoke_reason;
    }

    public void setRevoke_reason(List<String> revoke_reason) {
        this.revoke_reason = revoke_reason;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeString(this.bill_code);
        dest.writeStringList(this.revoke_reason);
    }

    public Chedanbean() {
    }

    protected Chedanbean(Parcel in) {
        this.token = in.readString();
        this.bill_code = in.readString();
        this.revoke_reason = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Chedanbean> CREATOR = new Parcelable.Creator<Chedanbean>() {
        @Override
        public Chedanbean createFromParcel(Parcel source) {
            return new Chedanbean(source);
        }

        @Override
        public Chedanbean[] newArray(int size) {
            return new Chedanbean[size];
        }
    };
}
