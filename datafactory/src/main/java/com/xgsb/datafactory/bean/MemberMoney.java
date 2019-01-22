package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class MemberMoney implements Parcelable {
    private String id;
    private String order_id;
    private String money;
    private String money_give;
    private String create_time;
    private String money_count;
    private String money_give_count;
    private String action;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoney_give() {
        return money_give;
    }

    public void setMoney_give(String money_give) {
        this.money_give = money_give;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getMoney_count() {
        return money_count;
    }

    public void setMoney_count(String money_count) {
        this.money_count = money_count;
    }

    public String getMoney_give_count() {
        return money_give_count;
    }

    public void setMoney_give_count(String money_give_count) {
        this.money_give_count = money_give_count;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MemberMoney() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.order_id);
        dest.writeString(this.money);
        dest.writeString(this.money_give);
        dest.writeString(this.create_time);
        dest.writeString(this.money_count);
        dest.writeString(this.money_give_count);
        dest.writeString(this.action);
    }

    protected MemberMoney(Parcel in) {
        this.id = in.readString();
        this.order_id = in.readString();
        this.money = in.readString();
        this.money_give = in.readString();
        this.create_time = in.readString();
        this.money_count = in.readString();
        this.money_give_count = in.readString();
        this.action = in.readString();
    }

    public static final Creator<MemberMoney> CREATOR = new Creator<MemberMoney>() {
        @Override
        public MemberMoney createFromParcel(Parcel source) {
            return new MemberMoney(source);
        }

        @Override
        public MemberMoney[] newArray(int size) {
            return new MemberMoney[size];
        }
    };
}
