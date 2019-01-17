package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class SettalSetMealChildbean implements Parcelable {
    private String id;
    private String number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        dest.writeString(this.id);
        dest.writeString(this.number);
    }

    public SettalSetMealChildbean() {
    }

    protected SettalSetMealChildbean(Parcel in) {
        this.id = in.readString();
        this.number = in.readString();
    }

    public static final Parcelable.Creator<SettalSetMealChildbean> CREATOR = new Parcelable.Creator<SettalSetMealChildbean>() {
        @Override
        public SettalSetMealChildbean createFromParcel(Parcel source) {
            return new SettalSetMealChildbean(source);
        }

        @Override
        public SettalSetMealChildbean[] newArray(int size) {
            return new SettalSetMealChildbean[size];
        }
    };
}
