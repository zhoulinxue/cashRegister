package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TableArea implements Parcelable {

    private int id;
    private String region_name;

    public TableArea() {
    }

    public TableArea(int id, String region_name) {
        this.id = id;
        this.region_name = region_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.region_name);
    }

    protected TableArea(Parcel in) {
        this.id = in.readInt();
        this.region_name = in.readString();
    }

    public static final Parcelable.Creator<TableArea> CREATOR = new Parcelable.Creator<TableArea>() {
        @Override
        public TableArea createFromParcel(Parcel source) {
            return new TableArea(source);
        }

        @Override
        public TableArea[] newArray(int size) {
            return new TableArea[size];
        }
    };
}
