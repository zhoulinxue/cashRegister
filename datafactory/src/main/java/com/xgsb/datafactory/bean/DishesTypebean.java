package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Name: DishesTypebean
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-14 23:44
 */
public class DishesTypebean implements Parcelable {
    private String category_name;
    private int category_id;

    public DishesTypebean(String category_name, int category_id) {
        this.category_name = category_name;
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.category_name);
        dest.writeInt(this.category_id);
    }

    public DishesTypebean() {
    }

    protected DishesTypebean(Parcel in) {
        this.category_name = in.readString();
        this.category_id = in.readInt();
    }

    public static final Parcelable.Creator<DishesTypebean> CREATOR = new Parcelable.Creator<DishesTypebean>() {
        @Override
        public DishesTypebean createFromParcel(Parcel source) {
            return new DishesTypebean(source);
        }

        @Override
        public DishesTypebean[] newArray(int size) {
            return new DishesTypebean[size];
        }
    };
}
