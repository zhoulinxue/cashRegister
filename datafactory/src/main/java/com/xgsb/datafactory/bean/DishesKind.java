package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class DishesKind implements Parcelable {
    private int id;
    private String dishes_category_name;
    private String drawer_id;
    private String drawer;
    private String giving;
    private String distribution;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDishes_category_name() {
        return dishes_category_name;
    }

    public void setDishes_category_name(String dishes_category_name) {
        this.dishes_category_name = dishes_category_name;
    }

    public String getDrawer_id() {
        return drawer_id;
    }

    public void setDrawer_id(String drawer_id) {
        this.drawer_id = drawer_id;
    }

    public String getDrawer() {
        return drawer;
    }

    public void setDrawer(String drawer) {
        this.drawer = drawer;
    }

    public String getGiving() {
        return giving;
    }

    public void setGiving(String giving) {
        this.giving = giving;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.dishes_category_name);
        dest.writeString(this.drawer_id);
        dest.writeString(this.drawer);
        dest.writeString(this.giving);
        dest.writeString(this.distribution);
    }

    public DishesKind() {
    }

    protected DishesKind(Parcel in) {
        this.id = in.readInt();
        this.dishes_category_name = in.readString();
        this.drawer_id = in.readString();
        this.drawer = in.readString();
        this.giving = in.readString();
        this.distribution = in.readString();
    }

    public static final Parcelable.Creator<DishesKind> CREATOR = new Parcelable.Creator<DishesKind>() {
        @Override
        public DishesKind createFromParcel(Parcel source) {
            return new DishesKind(source);
        }

        @Override
        public DishesKind[] newArray(int size) {
            return new DishesKind[size];
        }
    };
}
