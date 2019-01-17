package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.xgsb.datafactory.enu.DiscountType;

import java.util.List;

public class SettalOrderbean implements Parcelable {
    private String remarks;
    private List<SettalItem> data;
    private List<Dishesbean> dishes;
    private String bill_code;
    private String bill_type_info;
    private String order_source;
    private transient String sale_id;
    private DiscountType discountType;
    transient float finalyPrice;
    transient float salePrice;
    transient float restPrice;

    public String getSale_id() {
        return sale_id;
    }

    public void setSale_id(String sale_id) {
        this.sale_id = sale_id;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<Dishesbean> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dishesbean> dishes) {
        this.dishes = dishes;
    }

    public String getOrder_source() {
        return order_source;
    }

    public void setOrder_source(String order_source) {
        this.order_source = order_source;
    }

    public String getBill_type_info() {
        return bill_type_info;
    }

    public void setBill_type_info(String bill_type_info) {
        this.bill_type_info = bill_type_info;
    }

    public String getBill_code() {
        return bill_code;
    }

    public void setBill_code(String bill_code) {
        this.bill_code = bill_code;
    }

    public String getRemark() {
        return remarks;
    }

    public void setRemark(String remark) {
        this.remarks = remark;
    }

    public List<SettalItem> getData() {
        return data;
    }

    public void setData(List<SettalItem> data) {
        this.data = data;
    }

    public float getFinalyPrice() {
        return finalyPrice;
    }

    public void setFinalyPrice(float finalyPrice) {
        this.finalyPrice = finalyPrice;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public SettalOrderbean() {
    }

    public float getRestPrice() {
        return restPrice;
    }

    public void setRestPrice(float restPrice) {
        this.restPrice = restPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.remarks);
        dest.writeTypedList(this.data);
        dest.writeTypedList(this.dishes);
        dest.writeString(this.bill_code);
        dest.writeString(this.bill_type_info);
        dest.writeString(this.order_source);
        dest.writeString(this.sale_id);
        dest.writeInt(this.discountType == null ? -1 : this.discountType.ordinal());
        dest.writeFloat(this.finalyPrice);
        dest.writeFloat(this.salePrice);
        dest.writeFloat(this.restPrice);
    }

    protected SettalOrderbean(Parcel in) {
        this.remarks = in.readString();
        this.data = in.createTypedArrayList(SettalItem.CREATOR);
        this.dishes = in.createTypedArrayList(Dishesbean.CREATOR);
        this.bill_code = in.readString();
        this.bill_type_info = in.readString();
        this.order_source = in.readString();
        this.sale_id = in.readString();
        int tmpDiscountType = in.readInt();
        this.discountType = tmpDiscountType == -1 ? null : DiscountType.values()[tmpDiscountType];
        this.finalyPrice = in.readFloat();
        this.salePrice = in.readFloat();
        this.restPrice = in.readFloat();
    }

    public static final Creator<SettalOrderbean> CREATOR = new Creator<SettalOrderbean>() {
        @Override
        public SettalOrderbean createFromParcel(Parcel source) {
            return new SettalOrderbean(source);
        }

        @Override
        public SettalOrderbean[] newArray(int size) {
            return new SettalOrderbean[size];
        }
    };
}
