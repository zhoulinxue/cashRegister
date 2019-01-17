package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Favorablebean implements Parcelable {
    private String id;
    private String favorable_name;
    private String category;
    private  float money;

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFavorable_name() {
        return favorable_name;
    }

    public void setFavorable_name(String favorable_name) {
        this.favorable_name = favorable_name;
    }

    public Favorablebean() {
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Favorablebean){
            return ((Favorablebean) obj).getFavorable_name().equals(getFavorable_name());
        }
        return super.equals(obj);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.favorable_name);
        dest.writeString(this.category);
        dest.writeFloat(this.money);
    }

    protected Favorablebean(Parcel in) {
        this.id = in.readString();
        this.favorable_name = in.readString();
        this.category = in.readString();
        this.money = in.readFloat();
    }

    public static final Creator<Favorablebean> CREATOR = new Creator<Favorablebean>() {
        @Override
        public Favorablebean createFromParcel(Parcel source) {
            return new Favorablebean(source);
        }

        @Override
        public Favorablebean[] newArray(int size) {
            return new Favorablebean[size];
        }
    };
}
