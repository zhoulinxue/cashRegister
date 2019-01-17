package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class TableStatus implements Parcelable {
    private int color;
    private String statusName;
    private List<Table> num;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<Table> getNum() {
        return num;
    }

    public void setNum(List<Table> num) {
        this.num = num;
    }

    public TableStatus() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.color);
        dest.writeString(this.statusName);
        dest.writeTypedList(this.num);
    }

    protected TableStatus(Parcel in) {
        this.color = in.readInt();
        this.statusName = in.readString();
        this.num = in.createTypedArrayList(Table.CREATOR);
    }

    public static final Creator<TableStatus> CREATOR = new Creator<TableStatus>() {
        @Override
        public TableStatus createFromParcel(Parcel source) {
            return new TableStatus(source);
        }

        @Override
        public TableStatus[] newArray(int size) {
            return new TableStatus[size];
        }
    };
}
