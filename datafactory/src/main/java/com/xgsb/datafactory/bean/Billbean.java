package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class Billbean implements Parcelable {
    private String bill_code;
    private int id;
    private String table_number;
    private String guest_qty;
    private String bill_date;
    private String order_date;
    private String finsh_tag;
    private String guest_tel;
    private String bill_cost;
    private String vip_tag;
    private String waiter_id;
    private String clean_id;
    private String relation_table_name;
    private String spell_id;
    private String guest_name;
    private String guest_type_name;
    private String sale_name;
    private String bill_tag;
    private String local_status;
    private String region_name;
    private String sale_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSale_id() {
        return sale_id;
    }

    public void setSale_id(String sale_id) {
        this.sale_id = sale_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getBill_code() {
        return bill_code;
    }

    public void setBill_code(String bill_code) {
        this.bill_code = bill_code;
    }

    public String getTable_number() {
        return table_number;
    }

    public void setTable_number(String table_number) {
        this.table_number = table_number;
    }

    public String getGuest_qty() {
        return guest_qty;
    }

    public void setGuest_qty(String guest_qty) {
        this.guest_qty = guest_qty;
    }

    public String getBill_date() {
        return bill_date;
    }

    public void setBill_date(String bill_date) {
        this.bill_date = bill_date;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getFinsh_tag() {
        return finsh_tag;
    }

    public void setFinsh_tag(String finsh_tag) {
        this.finsh_tag = finsh_tag;
    }

    public String getGuest_tel() {
        return guest_tel;
    }

    public void setGuest_tel(String guest_tel) {
        this.guest_tel = guest_tel;
    }

    public String getBill_cost() {
        return bill_cost;
    }

    public void setBill_cost(String bill_cost) {
        this.bill_cost = bill_cost;
    }

    public String getVip_tag() {
        return vip_tag;
    }

    public void setVip_tag(String vip_tag) {
        this.vip_tag = vip_tag;
    }

    public String getWaiter_id() {
        return waiter_id;
    }

    public void setWaiter_id(String waiter_id) {
        this.waiter_id = waiter_id;
    }

    public String getClean_id() {
        return clean_id;
    }

    public void setClean_id(String clean_id) {
        this.clean_id = clean_id;
    }

    public String getRelation_table_name() {
        return relation_table_name;
    }

    public void setRelation_table_name(String relation_table_name) {
        this.relation_table_name = relation_table_name;
    }

    public String getSpell_id() {
        return spell_id;
    }

    public void setSpell_id(String spell_id) {
        this.spell_id = spell_id;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public String getGuest_type_name() {
        return guest_type_name;
    }

    public void setGuest_type_name(String guest_type_name) {
        this.guest_type_name = guest_type_name;
    }

    public String getSale_name() {
        return sale_name;
    }

    public void setSale_name(String sale_name) {
        this.sale_name = sale_name;
    }

    public String getBill_tag() {
        return bill_tag;
    }

    public void setBill_tag(String bill_tag) {
        this.bill_tag = bill_tag;
    }

    public Billbean() {
    }

    public String getLocal_status() {
        local_status = getStatus();
        return local_status;
    }

    public void setLocal_status(String local_status) {
        this.local_status = local_status;
    }

    /**
     * 获取台状态
     *
     * @return
     */
    public String getStatus() {
        if (!TextUtils.isEmpty(getBill_tag()) && Integer.valueOf(getBill_tag()) == 3) {
            return "已预订";
        }
        if ("0".equals(getFinsh_tag())) {
            if ("1".equals(getWaiter_id())) {
                return "已下单";
            } else {
                return "已开台";
            }
        } else if ("1".equals(getFinsh_tag())) {
            return "已结账";
        }
        return "";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bill_code);
        dest.writeInt(this.id);
        dest.writeString(this.table_number);
        dest.writeString(this.guest_qty);
        dest.writeString(this.bill_date);
        dest.writeString(this.order_date);
        dest.writeString(this.finsh_tag);
        dest.writeString(this.guest_tel);
        dest.writeString(this.bill_cost);
        dest.writeString(this.vip_tag);
        dest.writeString(this.waiter_id);
        dest.writeString(this.clean_id);
        dest.writeString(this.relation_table_name);
        dest.writeString(this.spell_id);
        dest.writeString(this.guest_name);
        dest.writeString(this.guest_type_name);
        dest.writeString(this.sale_name);
        dest.writeString(this.bill_tag);
        dest.writeString(this.local_status);
        dest.writeString(this.region_name);
        dest.writeString(this.sale_id);
    }

    protected Billbean(Parcel in) {
        this.bill_code = in.readString();
        this.id = in.readInt();
        this.table_number = in.readString();
        this.guest_qty = in.readString();
        this.bill_date = in.readString();
        this.order_date = in.readString();
        this.finsh_tag = in.readString();
        this.guest_tel = in.readString();
        this.bill_cost = in.readString();
        this.vip_tag = in.readString();
        this.waiter_id = in.readString();
        this.clean_id = in.readString();
        this.relation_table_name = in.readString();
        this.spell_id = in.readString();
        this.guest_name = in.readString();
        this.guest_type_name = in.readString();
        this.sale_name = in.readString();
        this.bill_tag = in.readString();
        this.local_status = in.readString();
        this.region_name = in.readString();
        this.sale_id = in.readString();
    }

    public static final Creator<Billbean> CREATOR = new Creator<Billbean>() {
        @Override
        public Billbean createFromParcel(Parcel source) {
            return new Billbean(source);
        }

        @Override
        public Billbean[] newArray(int size) {
            return new Billbean[size];
        }
    };
}
