package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class GiveDetailListbean implements Parcelable {
    private String stocktake_date;
    private String station_number;
    private String dishes_name;
    private String giving_number;
    private String giving_type;
    private String giving_price;
    private String sum_giving_money;

    public String getStocktake_date() {
        return stocktake_date;
    }

    public void setStocktake_date(String stocktake_date) {
        this.stocktake_date = stocktake_date;
    }

    public String getStation_number() {
        return station_number;
    }

    public void setStation_number(String station_number) {
        this.station_number = station_number;
    }

    public String getDishes_name() {
        return dishes_name;
    }

    public void setDishes_name(String dishes_name) {
        this.dishes_name = dishes_name;
    }

    public String getGiving_number() {
        return giving_number;
    }

    public void setGiving_number(String giving_number) {
        this.giving_number = giving_number;
    }

    public String getGiving_type() {
        return giving_type;
    }

    public void setGiving_type(String giving_type) {
        this.giving_type = giving_type;
    }

    public String getGiving_price() {
        return giving_price;
    }

    public void setGiving_price(String giving_price) {
        this.giving_price = giving_price;
    }

    public String getSum_giving_money() {
        return sum_giving_money;
    }

    public void setSum_giving_money(String sum_giving_money) {
        this.sum_giving_money = sum_giving_money;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.stocktake_date);
        dest.writeString(this.station_number);
        dest.writeString(this.dishes_name);
        dest.writeString(this.giving_number);
        dest.writeString(this.giving_type);
        dest.writeString(this.giving_price);
        dest.writeString(this.sum_giving_money);
    }

    public GiveDetailListbean() {
    }

    protected GiveDetailListbean(Parcel in) {
        this.stocktake_date = in.readString();
        this.station_number = in.readString();
        this.dishes_name = in.readString();
        this.giving_number = in.readString();
        this.giving_type = in.readString();
        this.giving_price = in.readString();
        this.sum_giving_money = in.readString();
    }

    public static final Parcelable.Creator<GiveDetailListbean> CREATOR = new Parcelable.Creator<GiveDetailListbean>() {
        @Override
        public GiveDetailListbean createFromParcel(Parcel source) {
            return new GiveDetailListbean(source);
        }

        @Override
        public GiveDetailListbean[] newArray(int size) {
            return new GiveDetailListbean[size];
        }
    };
}
