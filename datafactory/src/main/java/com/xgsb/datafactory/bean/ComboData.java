package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ComboData implements Parcelable {
    private int id;
    private String number;
    private String dishes_specification;
    private String dishes_name;
    private String group_id;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return number;
    }

    public void setNum(String num) {
        this.number = num;
    }

    public String getDishes_specification() {
        return dishes_specification;
    }

    public void setDishes_specification(String dishes_specification) {
        this.dishes_specification = dishes_specification;
    }

    public String getDishes_name() {
        return dishes_name;
    }

    public void setDishes_name(String dishes_name) {
        this.dishes_name = dishes_name;
    }

    public ComboData() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.number);
        dest.writeString(this.dishes_specification);
        dest.writeString(this.dishes_name);
        dest.writeString(this.group_id);
    }

    protected ComboData(Parcel in) {
        this.id = in.readInt();
        this.number = in.readString();
        this.dishes_specification = in.readString();
        this.dishes_name = in.readString();
        this.group_id = in.readString();
    }

    public static final Creator<ComboData> CREATOR = new Creator<ComboData>() {
        @Override
        public ComboData createFromParcel(Parcel source) {
            return new ComboData(source);
        }

        @Override
        public ComboData[] newArray(int size) {
            return new ComboData[size];
        }
    };
}
