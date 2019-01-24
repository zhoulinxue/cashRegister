package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Printbean implements Parcelable {
    private int id;
    private String token;
    private String printing_name;
    private String printing_brand;
    private String printing_food;
    private String printing_interface_type;
    private String printing_ip;
    private String printing_number;
    private String printing_region;
    private String printing_merge;
    private String printing_exhibition;
    private String printing_buzzing;
    private String printing_print_bill;
    private String printing_purpose;
    private String disher_data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPrinting_name() {
        return printing_name;
    }

    public void setPrinting_name(String printing_name) {
        this.printing_name = printing_name;
    }

    public String getPrinting_brand() {
        return printing_brand;
    }

    public void setPrinting_brand(String printing_brand) {
        this.printing_brand = printing_brand;
    }

    public String getPrinting_food() {
        return printing_food;
    }

    public void setPrinting_food(String printing_food) {
        this.printing_food = printing_food;
    }

    public String getPrinting_interface_type() {
        return printing_interface_type;
    }

    public void setPrinting_interface_type(String printing_interface_type) {
        this.printing_interface_type = printing_interface_type;
    }

    public String getPrinting_ip() {
        return printing_ip;
    }

    public void setPrinting_ip(String printing_ip) {
        this.printing_ip = printing_ip;
    }

    public String getPrinting_number() {
        return printing_number;
    }

    public void setPrinting_number(String printing_number) {
        this.printing_number = printing_number;
    }

    public String getPrinting_region() {
        return printing_region;
    }

    public void setPrinting_region(String printing_region) {
        this.printing_region = printing_region;
    }

    public String getPrinting_merge() {
        return printing_merge;
    }

    public void setPrinting_merge(String printing_merge) {
        this.printing_merge = printing_merge;
    }

    public String getPrinting_exhibition() {
        return printing_exhibition;
    }

    public void setPrinting_exhibition(String printing_exhibition) {
        this.printing_exhibition = printing_exhibition;
    }

    public String getPrinting_buzzing() {
        return printing_buzzing;
    }

    public void setPrinting_buzzing(String printing_buzzing) {
        this.printing_buzzing = printing_buzzing;
    }

    public String getPrinting_print_bill() {
        return printing_print_bill;
    }

    public void setPrinting_print_bill(String printing_print_bill) {
        this.printing_print_bill = printing_print_bill;
    }

    public String getPrinting_purpose() {
        return printing_purpose;
    }

    public void setPrinting_purpose(String printing_purpose) {
        this.printing_purpose = printing_purpose;
    }

    public String getDisher_data() {
        return disher_data;
    }

    public void setDisher_data(String disher_data) {
        this.disher_data = disher_data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.token);
        dest.writeString(this.printing_name);
        dest.writeString(this.printing_brand);
        dest.writeString(this.printing_food);
        dest.writeString(this.printing_interface_type);
        dest.writeString(this.printing_ip);
        dest.writeString(this.printing_number);
        dest.writeString(this.printing_region);
        dest.writeString(this.printing_merge);
        dest.writeString(this.printing_exhibition);
        dest.writeString(this.printing_buzzing);
        dest.writeString(this.printing_print_bill);
        dest.writeString(this.printing_purpose);
        dest.writeString(this.disher_data);
    }

    public Printbean() {
    }

    protected Printbean(Parcel in) {
        this.id = in.readInt();
        this.token = in.readString();
        this.printing_name = in.readString();
        this.printing_brand = in.readString();
        this.printing_food = in.readString();
        this.printing_interface_type = in.readString();
        this.printing_ip = in.readString();
        this.printing_number = in.readString();
        this.printing_region = in.readString();
        this.printing_merge = in.readString();
        this.printing_exhibition = in.readString();
        this.printing_buzzing = in.readString();
        this.printing_print_bill = in.readString();
        this.printing_purpose = in.readString();
        this.disher_data = in.readString();
    }

    public static final Parcelable.Creator<Printbean> CREATOR = new Parcelable.Creator<Printbean>() {
        @Override
        public Printbean createFromParcel(Parcel source) {
            return new Printbean(source);
        }

        @Override
        public Printbean[] newArray(int size) {
            return new Printbean[size];
        }
    };
}
