package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.xgsb.datafactory.enu.DiscountType;
import com.zx.api.api.utils.AppLog;
import com.zx.api.api.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class Specifications implements Parcelable {
    private String id;
    private String dish_id;
    private String specification;
    private String sale_price;
    private String vip_price;
    private String integral_exchange;
    private String guqing;
    private String time_price;
    private String discount;
    private String discount_type;
    private String buy_num;
    private String send_num;
    private List<Dishesbean> gift_dishes;
    private String tag;
    private String tag_id;
    private int all_flag;
    private String all_discount;
    private String all_discount_id;
    private String finally_price;

    public String getFinally_price() {
        return finally_price;
    }

    public void setFinally_price(String finally_price) {
        this.finally_price = finally_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDish_id() {
        return dish_id;
    }

    public void setDish_id(String dish_id) {
        this.dish_id = dish_id;
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

    public String getVip_price() {
        return vip_price;
    }

    public void setVip_price(String vip_price) {
        this.vip_price = vip_price;
    }

    public String getIntegral_exchange() {
        return integral_exchange;
    }

    public void setIntegral_exchange(String integral_exchange) {
        this.integral_exchange = integral_exchange;
    }

    public String getGuqing() {
        return guqing;
    }

    public void setGuqing(String guqing) {
        this.guqing = guqing;
    }

    public String getTime_price() {
        return time_price;
    }

    public void setTime_price(String time_price) {
        this.time_price = time_price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(String discount_type) {
        this.discount_type = discount_type;
    }

    public String getBuy_num() {
        return buy_num;
    }

    public void setBuy_num(String buy_num) {
        this.buy_num = buy_num;
    }

    public String getSend_num() {
        return send_num;
    }

    public void setSend_num(String send_num) {
        this.send_num = send_num;
    }

    public List<Dishesbean> getGift_dishes() {
        return gift_dishes;
    }

    public void setGift_dishes(List<Dishesbean> gift_dishes) {
        this.gift_dishes = gift_dishes;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id;
    }

    public int getAll_flag() {
        return all_flag;
    }

    public void setAll_flag(int all_flag) {
        this.all_flag = all_flag;
    }

    public String getAll_discount() {
        return all_discount;
    }

    public void setAll_discount(String all_discount) {
        this.all_discount = all_discount;
    }

    public String getAll_discount_id() {
        return all_discount_id;
    }

    public void setAll_discount_id(String all_discount_id) {
        this.all_discount_id = all_discount_id;
    }

    public Specifications() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.dish_id);
        dest.writeString(this.specification);
        dest.writeString(this.sale_price);
        dest.writeString(this.vip_price);
        dest.writeString(this.integral_exchange);
        dest.writeString(this.guqing);
        dest.writeString(this.time_price);
        dest.writeString(this.discount);
        dest.writeString(this.discount_type);
        dest.writeString(this.buy_num);
        dest.writeString(this.send_num);
        dest.writeTypedList(this.gift_dishes);
        dest.writeString(this.tag);
        dest.writeString(this.tag_id);
        dest.writeInt(this.all_flag);
        dest.writeString(this.all_discount);
        dest.writeString(this.all_discount_id);
        dest.writeString(this.finally_price);
    }

    protected Specifications(Parcel in) {
        this.id = in.readString();
        this.dish_id = in.readString();
        this.specification = in.readString();
        this.sale_price = in.readString();
        this.vip_price = in.readString();
        this.integral_exchange = in.readString();
        this.guqing = in.readString();
        this.time_price = in.readString();
        this.discount = in.readString();
        this.discount_type = in.readString();
        this.buy_num = in.readString();
        this.send_num = in.readString();
        this.gift_dishes = in.createTypedArrayList(Dishesbean.CREATOR);
        this.tag = in.readString();
        this.tag_id = in.readString();
        this.all_flag = in.readInt();
        this.all_discount = in.readString();
        this.all_discount_id = in.readString();
        this.finally_price = in.readString();
    }

    public static final Creator<Specifications> CREATOR = new Creator<Specifications>() {
        @Override
        public Specifications createFromParcel(Parcel source) {
            return new Specifications(source);
        }

        @Override
        public Specifications[] newArray(int size) {
            return new Specifications[size];
        }
    };

    public String getDiscountPrice() {
        if(1==getAll_flag()) {
            float pri=(AppUtil.getFloatFromString(getSale_price()).floatValue() * AppUtil.getFloatFromString(getAll_discount()).floatValue() / 100);
            AppLog.print(getSale_price()+"整单优惠价格"+pri);
            return pri + "";
        }else {
            return  getSale_price();
        }
    }
}
