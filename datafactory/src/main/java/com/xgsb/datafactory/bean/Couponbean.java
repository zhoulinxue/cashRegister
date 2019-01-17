package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Name: Couponbean
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-30 10:39
 */
public class Couponbean implements Parcelable {
    private int id;
    private String xgsb_system_number;
    private String member_id;
    private String voucher_money;
    private String voucher_commodity;
    private String voucher_type;
    private String voucher_date;
    private String end_date;
    private String status;
    private String markenting_rule_id;
    private String limit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getXgsb_system_number() {
        return xgsb_system_number;
    }

    public void setXgsb_system_number(String xgsb_system_number) {
        this.xgsb_system_number = xgsb_system_number;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getVoucher_money() {
        return voucher_money;
    }

    public void setVoucher_money(String voucher_money) {
        this.voucher_money = voucher_money;
    }

    public String getVoucher_commodity() {
        return voucher_commodity;
    }

    public void setVoucher_commodity(String voucher_commodity) {
        this.voucher_commodity = voucher_commodity;
    }

    public String getVoucher_type() {
        return voucher_type;
    }

    public void setVoucher_type(String voucher_type) {
        this.voucher_type = voucher_type;
    }

    public String getVoucher_date() {
        return voucher_date;
    }

    public void setVoucher_date(String voucher_date) {
        this.voucher_date = voucher_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMarkenting_rule_id() {
        return markenting_rule_id;
    }

    public void setMarkenting_rule_id(String markenting_rule_id) {
        this.markenting_rule_id = markenting_rule_id;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.xgsb_system_number);
        dest.writeString(this.member_id);
        dest.writeString(this.voucher_money);
        dest.writeString(this.voucher_commodity);
        dest.writeString(this.voucher_type);
        dest.writeString(this.voucher_date);
        dest.writeString(this.end_date);
        dest.writeString(this.status);
        dest.writeString(this.markenting_rule_id);
        dest.writeString(this.limit);
    }

    public Couponbean() {
    }

    protected Couponbean(Parcel in) {
        this.id = in.readInt();
        this.xgsb_system_number = in.readString();
        this.member_id = in.readString();
        this.voucher_money = in.readString();
        this.voucher_commodity = in.readString();
        this.voucher_type = in.readString();
        this.voucher_date = in.readString();
        this.end_date = in.readString();
        this.status = in.readString();
        this.markenting_rule_id = in.readString();
        this.limit = in.readString();
    }

    public static final Creator<Couponbean> CREATOR = new Creator<Couponbean>() {
        @Override
        public Couponbean createFromParcel(Parcel source) {
            return new Couponbean(source);
        }

        @Override
        public Couponbean[] newArray(int size) {
            return new Couponbean[size];
        }
    };
}
