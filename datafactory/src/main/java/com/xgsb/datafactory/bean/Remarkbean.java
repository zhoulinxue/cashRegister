package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Remarkbean implements Parcelable {
    private int id;
    private String remarks_name;
    private String status;
    private String xgsb_system_number;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemarks_name() {
        return remarks_name;
    }

    public void setRemarks_name(String remarks_name) {
        this.remarks_name = remarks_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getXgsb_system_number() {
        return xgsb_system_number;
    }

    public void setXgsb_system_number(String xgsb_system_number) {
        this.xgsb_system_number = xgsb_system_number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Remarkbean) {
            return getId() == ((Remarkbean) obj).getId();
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
        dest.writeString(this.remarks_name);
        dest.writeString(this.status);
        dest.writeString(this.xgsb_system_number);
        dest.writeString(this.type);
    }

    public Remarkbean() {
    }

    protected Remarkbean(Parcel in) {
        this.id = in.readInt();
        this.remarks_name = in.readString();
        this.status = in.readString();
        this.xgsb_system_number = in.readString();
        this.type = in.readString();
    }

    public static final Parcelable.Creator<Remarkbean> CREATOR = new Parcelable.Creator<Remarkbean>() {
        @Override
        public Remarkbean createFromParcel(Parcel source) {
            return new Remarkbean(source);
        }

        @Override
        public Remarkbean[] newArray(int size) {
            return new Remarkbean[size];
        }
    };
}
