package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Paybean implements Parcelable {
    private int pay_num;
    private int pay_tag;
    private String create_date;
    private float pay_amount;
    private String bill_amount;
    private String should_receive_amount;
    private String had_receive_amount;
    private String vip_sale;
    private String dish_sale;
    private String order_sale;
    private String billCode;

    public String getBill_amount() {
        return bill_amount;
    }

    public void setBill_amount(String bill_amount) {
        this.bill_amount = bill_amount;
    }

    public String getShould_receive_amount() {
        return should_receive_amount;
    }

    public void setShould_receive_amount(String should_receive_amount) {
        this.should_receive_amount = should_receive_amount;
    }

    public String getHad_receive_amount() {
        return had_receive_amount;
    }

    public void setHad_receive_amount(String had_receive_amount) {
        this.had_receive_amount = had_receive_amount;
    }

    public String getVip_sale() {
        return vip_sale;
    }

    public void setVip_sale(String vip_sale) {
        this.vip_sale = vip_sale;
    }

    public String getDish_sale() {
        return dish_sale;
    }

    public void setDish_sale(String dish_sale) {
        this.dish_sale = dish_sale;
    }

    public String getOrder_sale() {
        return order_sale;
    }

    public void setOrder_sale(String order_sale) {
        this.order_sale = order_sale;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getPay_num() {
        return pay_num;
    }

    public void setPay_num(int pay_num) {
        this.pay_num = pay_num;
    }

    public int getPay_tag() {
        return pay_tag;
    }

    public void setPay_tag(int pay_tag) {
        this.pay_tag = pay_tag;
    }

    public float getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(float pay_amount) {
        this.pay_amount = pay_amount;
    }

    public Paybean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.pay_num);
        dest.writeInt(this.pay_tag);
        dest.writeString(this.create_date);
        dest.writeFloat(this.pay_amount);
        dest.writeString(this.bill_amount);
        dest.writeString(this.should_receive_amount);
        dest.writeString(this.had_receive_amount);
        dest.writeString(this.vip_sale);
        dest.writeString(this.dish_sale);
        dest.writeString(this.order_sale);
        dest.writeString(this.billCode);
    }

    protected Paybean(Parcel in) {
        this.pay_num = in.readInt();
        this.pay_tag = in.readInt();
        this.create_date = in.readString();
        this.pay_amount = in.readFloat();
        this.bill_amount = in.readString();
        this.should_receive_amount = in.readString();
        this.had_receive_amount = in.readString();
        this.vip_sale = in.readString();
        this.dish_sale = in.readString();
        this.order_sale = in.readString();
        this.billCode = in.readString();
    }

    public static final Creator<Paybean> CREATOR = new Creator<Paybean>() {
        @Override
        public Paybean createFromParcel(Parcel source) {
            return new Paybean(source);
        }

        @Override
        public Paybean[] newArray(int size) {
            return new Paybean[size];
        }
    };
}
