package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class AddGivebean implements Parcelable {
    private String token;
    private List<BaseGivebean> data;

    public List<BaseGivebean> getData() {
        return data;
    }

    public void setData(List<BaseGivebean> data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AddGivebean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeTypedList(this.data);
    }

    protected AddGivebean(Parcel in) {
        this.token = in.readString();
        this.data = in.createTypedArrayList(BaseGivebean.CREATOR);
    }

    public static final Creator<AddGivebean> CREATOR = new Creator<AddGivebean>() {
        @Override
        public AddGivebean createFromParcel(Parcel source) {
            return new AddGivebean(source);
        }

        @Override
        public AddGivebean[] newArray(int size) {
            return new AddGivebean[size];
        }
    };
}
