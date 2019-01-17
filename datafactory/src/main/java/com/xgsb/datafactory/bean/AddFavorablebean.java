package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class AddFavorablebean implements Parcelable, Comparable<AddFavorablebean> {
    private String parent_name;
    private String name;
    private String discount;
    private String money;

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.parent_name);
        dest.writeString(this.name);
        dest.writeString(this.discount);
        dest.writeString(this.money);
    }

    public AddFavorablebean() {
    }

    protected AddFavorablebean(Parcel in) {
        this.parent_name = in.readString();
        this.name = in.readString();
        this.discount = in.readString();
        this.money = in.readString();
    }

    public static final Parcelable.Creator<AddFavorablebean> CREATOR = new Parcelable.Creator<AddFavorablebean>() {
        @Override
        public AddFavorablebean createFromParcel(Parcel source) {
            return new AddFavorablebean(source);
        }

        @Override
        public AddFavorablebean[] newArray(int size) {
            return new AddFavorablebean[size];
        }
    };

    @Override
    public int compareTo(@NonNull AddFavorablebean o) {
        //注意：一定是 o.age>this.age,若 this.age在前，则排序功能无效（亲测）。
        if (Float.valueOf(o.money) > Float.valueOf(this.money)) {
            //首次执行，o.age代表List里第一个元素，this.age是List里第二个元素
            return 1;
        } else if (Float.valueOf(o.money) == Float.valueOf(this.money)) {
            return 0;
        } else {
            return -1;
        }
    }
}
