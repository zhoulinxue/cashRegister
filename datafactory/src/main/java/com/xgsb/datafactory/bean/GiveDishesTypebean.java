package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class GiveDishesTypebean implements Parcelable {
    private String giving_id;
    private String staff_id;
    private String giving_name;
    private String cycle;
    private String giving_type;
    private String giving_money;
    private int rest_money;

    public String getGiving_id() {
        return giving_id;
    }

    public void setGiving_id(String giving_id) {
        this.giving_id = giving_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getGiving_name() {
        return giving_name;
    }

    public void setGiving_name(String giving_name) {
        this.giving_name = giving_name;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getGiving_type() {
        return giving_type;
    }

    public void setGiving_type(String giving_type) {
        this.giving_type = giving_type;
    }

    public String getGiving_money() {
        return giving_money;
    }

    public void setGiving_money(String giving_money) {
        this.giving_money = giving_money;
    }

    public int getRest_money() {
        return rest_money;
    }

    public void setRest_money(int rest_money) {
        this.rest_money = rest_money;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.giving_id);
        dest.writeString(this.staff_id);
        dest.writeString(this.giving_name);
        dest.writeString(this.cycle);
        dest.writeString(this.giving_type);
        dest.writeString(this.giving_money);
        dest.writeInt(this.rest_money);
    }

    public GiveDishesTypebean() {
    }

    protected GiveDishesTypebean(Parcel in) {
        this.giving_id = in.readString();
        this.staff_id = in.readString();
        this.giving_name = in.readString();
        this.cycle = in.readString();
        this.giving_type = in.readString();
        this.giving_money = in.readString();
        this.rest_money = in.readInt();
    }

    public static final Parcelable.Creator<GiveDishesTypebean> CREATOR = new Parcelable.Creator<GiveDishesTypebean>() {
        @Override
        public GiveDishesTypebean createFromParcel(Parcel source) {
            return new GiveDishesTypebean(source);
        }

        @Override
        public GiveDishesTypebean[] newArray(int size) {
            return new GiveDishesTypebean[size];
        }
    };
}
