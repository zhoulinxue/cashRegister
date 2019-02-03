package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class PrintTableConsumebean implements Parcelable {
    private int id;
    private String region_name;
    private String table_number;
    private String finsh_tag;
    private String bill_code;
    private String number;
    private String money;
    private String not_number;
    private String not_money;
    private String count_number;
    private String count_money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getTable_number() {
        return table_number;
    }

    public void setTable_number(String table_number) {
        this.table_number = table_number;
    }

    public String getFinsh_tag() {
        return finsh_tag;
    }

    public void setFinsh_tag(String finsh_tag) {
        this.finsh_tag = finsh_tag;
    }

    public String getBill_code() {
        return bill_code;
    }

    public void setBill_code(String bill_code) {
        this.bill_code = bill_code;
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

    public String getNot_number() {
        return not_number;
    }

    public void setNot_number(String not_number) {
        this.not_number = not_number;
    }

    public String getNot_money() {
        return not_money;
    }

    public void setNot_money(String not_money) {
        this.not_money = not_money;
    }

    public String getCount_number() {
        return count_number;
    }

    public void setCount_number(String count_number) {
        this.count_number = count_number;
    }

    public String getCount_money() {
        return count_money;
    }

    public void setCount_money(String count_money) {
        this.count_money = count_money;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.region_name);
        dest.writeString(this.table_number);
        dest.writeString(this.finsh_tag);
        dest.writeString(this.bill_code);
        dest.writeString(this.number);
        dest.writeString(this.money);
        dest.writeString(this.not_number);
        dest.writeString(this.not_money);
        dest.writeString(this.count_number);
        dest.writeString(this.count_money);
    }

    public PrintTableConsumebean() {
    }

    protected PrintTableConsumebean(Parcel in) {
        this.id = in.readInt();
        this.region_name = in.readString();
        this.table_number = in.readString();
        this.finsh_tag = in.readString();
        this.bill_code = in.readString();
        this.number = in.readString();
        this.money = in.readString();
        this.not_number = in.readString();
        this.not_money = in.readString();
        this.count_number = in.readString();
        this.count_money = in.readString();
    }

    public static final Parcelable.Creator<PrintTableConsumebean> CREATOR = new Parcelable.Creator<PrintTableConsumebean>() {
        @Override
        public PrintTableConsumebean createFromParcel(Parcel source) {
            return new PrintTableConsumebean(source);
        }

        @Override
        public PrintTableConsumebean[] newArray(int size) {
            return new PrintTableConsumebean[size];
        }
    };
}
