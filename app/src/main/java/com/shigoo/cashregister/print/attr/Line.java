package com.shigoo.cashregister.print.attr;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Line implements Parcelable {
    private int topNum;
    private int bottomNum;
    private List<Colum> colum;

    public int getTopNum() {
        return topNum;
    }

    public void setTopNum(int topNum) {
        this.topNum = topNum;
    }

    public int getBottomNum() {
        return bottomNum;
    }

    public void setBottomNum(int bottomNum) {
        this.bottomNum = bottomNum;
    }

    public List<Colum> getColum() {
        return colum;
    }

    public void setColum(List<Colum> colum) {
        this.colum = colum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.topNum);
        dest.writeInt(this.bottomNum);
        dest.writeTypedList(this.colum);
    }

    public Line() {
    }

    protected Line(Parcel in) {
        this.topNum = in.readInt();
        this.bottomNum = in.readInt();
        this.colum = in.createTypedArrayList(Colum.CREATOR);
    }

    public static final Parcelable.Creator<Line> CREATOR = new Parcelable.Creator<Line>() {
        @Override
        public Line createFromParcel(Parcel source) {
            return new Line(source);
        }

        @Override
        public Line[] newArray(int size) {
            return new Line[size];
        }
    };
}
