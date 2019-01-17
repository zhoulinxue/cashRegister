package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

public class GiveDishesbean extends BaseGivebean implements Parcelable {
    @JSONField(serialize = false)
    private String dishes_name;
    @JSONField(serialize = false)
    private String specification;
    @JSONField(serialize = false)
    private String sale_price;
    @JSONField(serialize = false)
    private String guqing;
    @JSONField(serialize = false)
    private String num_ceiling;
    @JSONField(serialize = false)
    private String giving_income;
    @JSONField(serialize = false)
    private String giving_income_type;
    @JSONField(serialize = false)
    private String giving_dishes;
    @JSONField(serialize = false)
    private String giving_type;
    @JSONField(serialize = false)
    private int giving_income_number;
    @JSONField(serialize = false)
    private int num;

    public int getGiving_income_number() {
        return giving_income_number;
    }

    public void setGiving_income_number(int giving_income_number) {
        this.giving_income_number = giving_income_number;
    }

    public String getDishes_name() {
        return dishes_name;
    }

    public void setDishes_name(String dishes_name) {
        this.dishes_name = dishes_name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getGuqing() {
        return guqing;
    }

    public void setGuqing(String guqing) {
        this.guqing = guqing;
    }

    public String getNum_ceiling() {
        return num_ceiling;
    }

    public void setNum_ceiling(String num_ceiling) {
        this.num_ceiling = num_ceiling;
    }

    public String getGiving_income() {
        return giving_income;
    }

    public void setGiving_income(String giving_income) {
        this.giving_income = giving_income;
    }

    public String getGiving_income_type() {
        return giving_income_type;
    }

    public void setGiving_income_type(String giving_income_type) {
        this.giving_income_type = giving_income_type;
    }

    public String getGiving_dishes() {
        return giving_dishes;
    }

    public void setGiving_dishes(String giving_dishes) {
        this.giving_dishes = giving_dishes;
    }

    public String getGiving_type() {
        return giving_type;
    }

    public void setGiving_type(String giving_type) {
        this.giving_type = giving_type;
    }
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public GiveDishesbean() {
    }

    public BaseGivebean toBase() {
        BaseGivebean givebean = new BaseGivebean();
        givebean.setBill_code(getBill_code());
        givebean.setBill_date(getBill_date());
        givebean.setDish_qty(getDish_qty());
        givebean.setDishes_id(getDishes_id());
        givebean.setGift_reason(getGift_reason());
        givebean.setGiving_id(getGiving_id());
        givebean.setSpecification_id(getSpecification_id());
        givebean.setStaff_id(getStaff_id());
        givebean.setTable_number(getTable_number());
        return givebean;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.dishes_name);
        dest.writeString(this.specification);
        dest.writeString(this.sale_price);
        dest.writeString(this.guqing);
        dest.writeString(this.num_ceiling);
        dest.writeString(this.giving_income);
        dest.writeString(this.giving_income_type);
        dest.writeString(this.giving_dishes);
        dest.writeString(this.giving_type);
        dest.writeInt(this.giving_income_number);
        dest.writeInt(this.num);
    }

    protected GiveDishesbean(Parcel in) {
        super(in);
        this.dishes_name = in.readString();
        this.specification = in.readString();
        this.sale_price = in.readString();
        this.guqing = in.readString();
        this.num_ceiling = in.readString();
        this.giving_income = in.readString();
        this.giving_income_type = in.readString();
        this.giving_dishes = in.readString();
        this.giving_type = in.readString();
        this.giving_income_number = in.readInt();
        this.num = in.readInt();
    }

    public static final Creator<GiveDishesbean> CREATOR = new Creator<GiveDishesbean>() {
        @Override
        public GiveDishesbean createFromParcel(Parcel source) {
            return new GiveDishesbean(source);
        }

        @Override
        public GiveDishesbean[] newArray(int size) {
            return new GiveDishesbean[size];
        }
    };
}
