package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Name: User
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-12 14:02
 */
public class User implements Parcelable {
    private String userName;
    private String phoneNum;
    private String psw;
    private String token;
    private String staff_id;
    private String cashier_id="11";
    private String timestamp;
    private String number;
    private String signature;
    private String person_in_charge_id;

    public String getPerson_in_charge_id() {
        return person_in_charge_id;
    }

    public void setPerson_in_charge_id(String person_in_charge_id) {
        this.person_in_charge_id = person_in_charge_id;
    }

    public String getCashier_id() {
        return cashier_id;
    }

    public void setCashier_id(String cashier_id) {
        this.cashier_id = cashier_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public User(String userName, String psw) {
        this.userName = userName;
        this.psw = psw;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public User() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.phoneNum);
        dest.writeString(this.psw);
        dest.writeString(this.token);
        dest.writeString(this.staff_id);
        dest.writeString(this.cashier_id);
        dest.writeString(this.timestamp);
        dest.writeString(this.number);
        dest.writeString(this.signature);
        dest.writeString(this.cashier_id);
    }

    protected User(Parcel in) {
        this.userName = in.readString();
        this.phoneNum = in.readString();
        this.psw = in.readString();
        this.token = in.readString();
        this.staff_id = in.readString();
        this.cashier_id = in.readString();
        this.timestamp = in.readString();
        this.number = in.readString();
        this.signature = in.readString();
        this.cashier_id = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
