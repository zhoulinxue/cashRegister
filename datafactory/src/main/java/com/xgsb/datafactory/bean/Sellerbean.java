package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Name: Sellerbean
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-05 19:30
 */
public class Sellerbean implements Parcelable {
    private int id;
    private String staff_name;
    private String number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.staff_name);
        dest.writeString(this.number);
    }

    public Sellerbean() {
    }

    protected Sellerbean(Parcel in) {
        this.id = in.readInt();
        this.staff_name = in.readString();
        this.number = in.readString();
    }

    public static final Creator<Sellerbean> CREATOR = new Creator<Sellerbean>() {
        @Override
        public Sellerbean createFromParcel(Parcel source) {
            return new Sellerbean(source);
        }

        @Override
        public Sellerbean[] newArray(int size) {
            return new Sellerbean[size];
        }
    };
}
