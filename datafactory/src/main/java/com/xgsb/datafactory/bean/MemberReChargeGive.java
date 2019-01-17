package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Name: MemberReChargeGive
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-04 15:19
 */
public class MemberReChargeGive implements Parcelable {
    private int id;
    private String xgsb_system_number;
    private String serial_number;
    private String amount_of_money;
    private String give;
    private String order_of_consumption;
    private String status;
    private String create_date;

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

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getAmount_of_money() {
        return amount_of_money;
    }

    public void setAmount_of_money(String amount_of_money) {
        this.amount_of_money = amount_of_money;
    }

    public String getGive() {
        return give;
    }

    public void setGive(String give) {
        this.give = give;
    }

    public String getOrder_of_consumption() {
        return order_of_consumption;
    }

    public void setOrder_of_consumption(String order_of_consumption) {
        this.order_of_consumption = order_of_consumption;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.xgsb_system_number);
        dest.writeString(this.serial_number);
        dest.writeString(this.amount_of_money);
        dest.writeString(this.give);
        dest.writeString(this.order_of_consumption);
        dest.writeString(this.status);
        dest.writeString(this.create_date);
    }

    public MemberReChargeGive() {
    }

    protected MemberReChargeGive(Parcel in) {
        this.id = in.readInt();
        this.xgsb_system_number = in.readString();
        this.serial_number = in.readString();
        this.amount_of_money = in.readString();
        this.give = in.readString();
        this.order_of_consumption = in.readString();
        this.status = in.readString();
        this.create_date = in.readString();
    }

    public static final Parcelable.Creator<MemberReChargeGive> CREATOR = new Parcelable.Creator<MemberReChargeGive>() {
        @Override
        public MemberReChargeGive createFromParcel(Parcel source) {
            return new MemberReChargeGive(source);
        }

        @Override
        public MemberReChargeGive[] newArray(int size) {
            return new MemberReChargeGive[size];
        }
    };
}
