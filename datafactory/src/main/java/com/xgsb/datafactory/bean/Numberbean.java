package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Numberbean implements Parcelable {
    private String title;
    private String currentNum;
    private String unit;
    private int requstCode;

    public Numberbean(String title, String currentNum, String unit, int requstCode) {
        this.title = title;
        this.currentNum = currentNum;
        this.unit = unit;
        this.requstCode = requstCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(String currentNum) {
        this.currentNum = currentNum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getRequstCode() {
        return requstCode;
    }

    public void setRequstCode(int requstCode) {
        this.requstCode = requstCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.currentNum);
        dest.writeString(this.unit);
        dest.writeInt(this.requstCode);
    }

    public Numberbean() {
    }

    protected Numberbean(Parcel in) {
        this.title = in.readString();
        this.currentNum = in.readString();
        this.unit = in.readString();
        this.requstCode = in.readInt();
    }

    public static final Parcelable.Creator<Numberbean> CREATOR = new Parcelable.Creator<Numberbean>() {
        @Override
        public Numberbean createFromParcel(Parcel source) {
            return new Numberbean(source);
        }

        @Override
        public Numberbean[] newArray(int size) {
            return new Numberbean[size];
        }
    };
}
