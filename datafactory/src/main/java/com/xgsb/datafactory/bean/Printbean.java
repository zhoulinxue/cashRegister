package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Printbean implements Parcelable {

    private int id;
    private String printing_name;
    private String printing_brand;
    private String printing_interface_type;
    private String printing_ip;
    private String printing_width;
    private String printing_number;
    private String printing_region;
    private String printing_merge;
    private String printing_exhibition;
    private String printing_buzzing;
    private String printing_print_bill;
    private String status;
    private String create_date;
    private String xgsb_system_number;
    private String printing_purpose;
    private String print_dishes;
    private List<String> relation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPrinting_width() {
        return printing_width;
    }

    public void setPrinting_width(String printing_width) {
        this.printing_width = printing_width;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getXgsb_system_number() {
        return xgsb_system_number;
    }

    public void setXgsb_system_number(String xgsb_system_number) {
        this.xgsb_system_number = xgsb_system_number;
    }

    public String getPrinting_purpose() {
        return printing_purpose;
    }

    public void setPrinting_purpose(String printing_purpose) {
        this.printing_purpose = printing_purpose;
    }

    public String getPrint_dishes() {
        return print_dishes;
    }

    public void setPrint_dishes(String print_dishes) {
        this.print_dishes = print_dishes;
    }

    public List<String> getRelation() {
        return relation;
    }

    public void setRelation(List<String> relation) {
        this.relation = relation;
    }

    public Printbean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.printing_name);
        dest.writeString(this.printing_brand);
        dest.writeString(this.printing_interface_type);
        dest.writeString(this.printing_ip);
        dest.writeString(this.printing_width);
        dest.writeString(this.printing_number);
        dest.writeString(this.printing_region);
        dest.writeString(this.printing_merge);
        dest.writeString(this.printing_exhibition);
        dest.writeString(this.printing_buzzing);
        dest.writeString(this.printing_print_bill);
        dest.writeString(this.status);
        dest.writeString(this.create_date);
        dest.writeString(this.xgsb_system_number);
        dest.writeString(this.printing_purpose);
        dest.writeString(this.print_dishes);
        dest.writeStringList(this.relation);
    }

    protected Printbean(Parcel in) {
        this.id = in.readInt();
        this.printing_name = in.readString();
        this.printing_brand = in.readString();
        this.printing_interface_type = in.readString();
        this.printing_ip = in.readString();
        this.printing_width = in.readString();
        this.printing_number = in.readString();
        this.printing_region = in.readString();
        this.printing_merge = in.readString();
        this.printing_exhibition = in.readString();
        this.printing_buzzing = in.readString();
        this.printing_print_bill = in.readString();
        this.status = in.readString();
        this.create_date = in.readString();
        this.xgsb_system_number = in.readString();
        this.printing_purpose = in.readString();
        this.print_dishes = in.readString();
        this.relation = in.createStringArrayList();
    }

    public static final Creator<Printbean> CREATOR = new Creator<Printbean>() {
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
