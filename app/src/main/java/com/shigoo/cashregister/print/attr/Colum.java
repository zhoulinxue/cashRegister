package com.shigoo.cashregister.print.attr;

import android.os.Parcel;
import android.os.Parcelable;

import com.shigoo.cashregister.print.content.Content;

public class Colum implements Parcelable {
    private String name;
    private Content content;
    private int width;

    public Colum(String name) {
        this.name = name;
    }

    public Colum(Content content, int width) {
        this.content = content;
        this.width = width;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Colum() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeParcelable(this.content, flags);
        dest.writeInt(this.width);
    }

    protected Colum(Parcel in) {
        this.name = in.readString();
        this.content = in.readParcelable(Content.class.getClassLoader());
        this.width = in.readInt();
    }

    public static final Creator<Colum> CREATOR = new Creator<Colum>() {
        @Override
        public Colum createFromParcel(Parcel source) {
            return new Colum(source);
        }

        @Override
        public Colum[] newArray(int size) {
            return new Colum[size];
        }
    };
}
