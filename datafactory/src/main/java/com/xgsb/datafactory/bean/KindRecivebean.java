package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class KindRecivebean implements Parcelable {
    private int id;
    private String dishes_category_name;
    private String number;
    private String money;
    private String proportion;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.dishes_category_name);
        dest.writeString(this.number);
        dest.writeString(this.money);
        dest.writeString(this.proportion);
    }

    public KindRecivebean() {
    }

    protected KindRecivebean(Parcel in) {
        this.id = in.readInt();
        this.dishes_category_name = in.readString();
        this.number = in.readString();
        this.money = in.readString();
        this.proportion = in.readString();
    }

    public static final Parcelable.Creator<KindRecivebean> CREATOR = new Parcelable.Creator<KindRecivebean>() {
        @Override
        public KindRecivebean createFromParcel(Parcel source) {
            return new KindRecivebean(source);
        }

        @Override
        public KindRecivebean[] newArray(int size) {
            return new KindRecivebean[size];
        }
    };
}
