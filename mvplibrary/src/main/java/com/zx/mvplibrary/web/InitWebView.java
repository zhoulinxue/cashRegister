package com.zx.mvplibrary.web;

import android.os.Parcel;
import android.os.Parcelable;

public class InitWebView implements Parcelable {
    private String token;
    private String page;

    public InitWebView(String token, String page) {
        this.token = token;
        this.page = page;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeString(this.page);
    }

    public InitWebView() {
    }

    protected InitWebView(Parcel in) {
        this.token = in.readString();
        this.page = in.readString();
    }

    public static final Parcelable.Creator<InitWebView> CREATOR = new Parcelable.Creator<InitWebView>() {
        @Override
        public InitWebView createFromParcel(Parcel source) {
            return new InitWebView(source);
        }

        @Override
        public InitWebView[] newArray(int size) {
            return new InitWebView[size];
        }
    };
}
