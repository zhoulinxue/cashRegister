package com.shigoo.cashregister.print.content;

import android.os.Parcel;
import android.os.Parcelable;

import com.shigoo.cashregister.print.attr.PrintFormat;

public class Content implements Parcelable {
    private String content;
    private PrintFormat format;
    private int enterNum = 1;
    private byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getEnterNum() {
        return enterNum;
    }

    public void setEnterNum(int enterNum) {
        this.enterNum = enterNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PrintFormat getFormat() {
        return format;
    }

    public void setFormat(PrintFormat format) {
        this.format = format;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeParcelable(this.format, flags);
        dest.writeInt(this.enterNum);
        dest.writeByteArray(this.bytes);
    }

    public Content() {
    }

    protected Content(Parcel in) {
        this.content = in.readString();
        this.format = in.readParcelable(PrintFormat.class.getClassLoader());
        this.enterNum = in.readInt();
        this.bytes = in.createByteArray();
    }

    public static final Parcelable.Creator<Content> CREATOR = new Parcelable.Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel source) {
            return new Content(source);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };
}
