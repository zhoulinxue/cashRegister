package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TimeData implements Parcelable {
   private  int id;
    private String name;
    private String dishes;
    private String status;
    private String dishes_num;
    private String time;

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

    public String getDishes() {
        return dishes;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDishes_num() {
        return dishes_num;
    }

    public void setDishes_num(String dishes_num) {
        this.dishes_num = dishes_num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.dishes);
        dest.writeString(this.status);
        dest.writeString(this.dishes_num);
        dest.writeString(this.time);
    }

    public TimeData() {
    }

    protected TimeData(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.dishes = in.readString();
        this.status = in.readString();
        this.dishes_num = in.readString();
        this.time = in.readString();
    }

    public static final Parcelable.Creator<TimeData> CREATOR = new Parcelable.Creator<TimeData>() {
        @Override
        public TimeData createFromParcel(Parcel source) {
            return new TimeData(source);
        }

        @Override
        public TimeData[] newArray(int size) {
            return new TimeData[size];
        }
    };
}
