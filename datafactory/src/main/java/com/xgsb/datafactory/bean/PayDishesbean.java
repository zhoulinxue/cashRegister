package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class PayDishesbean implements Parcelable {
    private String id;
    private String dishesTag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDishesTag() {
        return dishesTag;
    }

    public void setDishesTag(String dishesTag) {
        this.dishesTag = dishesTag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.dishesTag);
    }

    public PayDishesbean() {
    }

    protected PayDishesbean(Parcel in) {
        this.id = in.readString();
        this.dishesTag = in.readString();
    }

    public static final Parcelable.Creator<PayDishesbean> CREATOR = new Parcelable.Creator<PayDishesbean>() {
        @Override
        public PayDishesbean createFromParcel(Parcel source) {
            return new PayDishesbean(source);
        }

        @Override
        public PayDishesbean[] newArray(int size) {
            return new PayDishesbean[size];
        }
    };
}
