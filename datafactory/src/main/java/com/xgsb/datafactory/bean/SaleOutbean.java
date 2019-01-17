package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class SaleOutbean implements Parcelable {
    private int id;
    private String dishes_id;
    private String number;
    private String status;
    private String dishes_name;
    private String combo_id;
    private String xgsb_system_number;
    private String type;

    public String getType() {
        type=TextUtils.isEmpty(getCombo_id()) ? "1" : "2";
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCombo_id() {
        return combo_id;
    }

    public void setCombo_id(String combo_id) {
        this.combo_id = combo_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDishes_id() {
        return dishes_id;
    }

    public void setDishes_id(String dishes_id) {
        this.dishes_id = dishes_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDishes_name() {
        return dishes_name;
    }

    public void setDishes_name(String dishes_name) {
        this.dishes_name = dishes_name;
    }

    public String getXgsb_system_number() {
        return xgsb_system_number;
    }

    public void setXgsb_system_number(String xgsb_system_number) {
        this.xgsb_system_number = xgsb_system_number;
    }

    public SaleOutbean() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SaleOutbean) {
            SaleOutbean oSa = (SaleOutbean) obj;
            return getType().equals(oSa.getType()) && getDishes_id().equals(oSa.getDishes_id());
        }
        return super.equals(obj);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.dishes_id);
        dest.writeString(this.number);
        dest.writeString(this.status);
        dest.writeString(this.dishes_name);
        dest.writeString(this.combo_id);
        dest.writeString(this.xgsb_system_number);
    }

    protected SaleOutbean(Parcel in) {
        this.id = in.readInt();
        this.dishes_id = in.readString();
        this.number = in.readString();
        this.status = in.readString();
        this.dishes_name = in.readString();
        this.combo_id = in.readString();
        this.xgsb_system_number = in.readString();
    }

    public static final Creator<SaleOutbean> CREATOR = new Creator<SaleOutbean>() {
        @Override
        public SaleOutbean createFromParcel(Parcel source) {
            return new SaleOutbean(source);
        }

        @Override
        public SaleOutbean[] newArray(int size) {
            return new SaleOutbean[size];
        }
    };
}
