package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Departmentbean implements Parcelable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    public Departmentbean() {
    }

    protected Departmentbean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Departmentbean> CREATOR = new Parcelable.Creator<Departmentbean>() {
        @Override
        public Departmentbean createFromParcel(Parcel source) {
            return new Departmentbean(source);
        }

        @Override
        public Departmentbean[] newArray(int size) {
            return new Departmentbean[size];
        }
    };
}
