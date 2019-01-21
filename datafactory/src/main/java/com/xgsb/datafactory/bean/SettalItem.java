package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class SettalItem implements Parcelable {
    private int id;
    private String bill_code;
    private String bill_date;
    private String dish_qty;
    private String specification_id;
    private String specification_name;
    private String dish_number;
    private String dish_name;
    private String drawer;
    private String sale_price;
    private String vip_price;
    private String integral_exchange;
    private String time_price;
    private String finally_price;
    private int whole_flag;
    private String whole_discount;
    private String whole_discount_id;
    private String discount;
    private String discount_type;
    private String tag;
    private String tag_id;
    private int all_flag;
    private String all_discount;
    private String all_discount_id;
    private String combo_id;
    private String combo_number;
    private String combo_price;
    private String combo_group_id;
    private String same_dish_id;
    private String buy_give_id;
    private String dish_tag;
    private String pay_tag;
    private String pay_num;
    private String waiter_id;
    private String cashier_id;
    private String table_number;
    private String source_table_number;
    private String quick_count;
    private String first_quick_time;
    private String last_quick_time;
    private String repricer_id;
    private String reprice_reason;
    private String back_id;
    private String back_reason;
    private String gifter_id;
    private String gift_reason;
    private String re_discount_id;
    private String re_discount;
    private String re_discount_reason;
    private String wait_tag;
    private String out_tag;
    private String table_gift_tag;
    private String remark;
    private String print_num;
    private String status;
    private String order_date;
    private String remark_data;
    private String serving_status;
    private String dishes_id;
    private List<ComboData> combo_data;
    private String sale_id;
    private String finally_tag;

    public String getFinally_tag() {
        return finally_tag;
    }

    public void setFinally_tag(String finally_tag) {
        this.finally_tag = finally_tag;
    }

    public String getSale_id() {
        return sale_id;
    }

    public void setSale_id(String sale_id) {
        this.sale_id = sale_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBill_code() {
        return bill_code;
    }

    public void setBill_code(String bill_code) {
        this.bill_code = bill_code;
    }

    public String getBill_date() {
        return bill_date;
    }

    public void setBill_date(String bill_date) {
        this.bill_date = bill_date;
    }

    public String getSpecification_name() {
        return specification_name;
    }

    public void setSpecification_name(String specification_name) {
        this.specification_name = specification_name;
    }

    public String getDish_number() {
        return dish_number;
    }

    public void setDish_number(String dish_number) {
        this.dish_number = dish_number;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public String getDrawer() {
        return drawer;
    }

    public void setDrawer(String drawer) {
        this.drawer = drawer;
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

    public String getTime_price() {
        return time_price;
    }

    public void setTime_price(String time_price) {
        this.time_price = time_price;
    }

    public int getWhole_flag() {
        return whole_flag;
    }

    public void setWhole_flag(int whole_flag) {
        this.whole_flag = whole_flag;
    }

    public String getWhole_discount() {
        return whole_discount;
    }

    public void setWhole_discount(String whole_discount) {
        this.whole_discount = whole_discount;
    }

    public String getWhole_discount_id() {
        return whole_discount_id;
    }

    public void setWhole_discount_id(String whole_discount_id) {
        this.whole_discount_id = whole_discount_id;
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

    public String getCombo_number() {
        return combo_number;
    }

    public void setCombo_number(String combo_number) {
        this.combo_number = combo_number;
    }

    public String getCombo_price() {
        return combo_price;
    }

    public void setCombo_price(String combo_price) {
        this.combo_price = combo_price;
    }

    public String getCombo_group_id() {
        return combo_group_id;
    }

    public void setCombo_group_id(String combo_group_id) {
        this.combo_group_id = combo_group_id;
    }

    public String getSame_dish_id() {
        return same_dish_id;
    }

    public void setSame_dish_id(String same_dish_id) {
        this.same_dish_id = same_dish_id;
    }

    public String getBuy_give_id() {
        return buy_give_id;
    }

    public void setBuy_give_id(String buy_give_id) {
        this.buy_give_id = buy_give_id;
    }

    public String getPay_tag() {
        return pay_tag;
    }

    public void setPay_tag(String pay_tag) {
        this.pay_tag = pay_tag;
    }

    public String getPay_num() {
        return pay_num;
    }

    public void setPay_num(String pay_num) {
        this.pay_num = pay_num;
    }

    public String getWaiter_id() {
        return waiter_id;
    }

    public void setWaiter_id(String waiter_id) {
        this.waiter_id = waiter_id;
    }

    public String getCashier_id() {
        return cashier_id;
    }

    public void setCashier_id(String cashier_id) {
        this.cashier_id = cashier_id;
    }

    public String getTable_number() {
        return table_number;
    }

    public void setTable_number(String table_number) {
        this.table_number = table_number;
    }

    public String getSource_table_number() {
        return source_table_number;
    }

    public void setSource_table_number(String source_table_number) {
        this.source_table_number = source_table_number;
    }

    public String getQuick_count() {
        return quick_count;
    }

    public void setQuick_count(String quick_count) {
        this.quick_count = quick_count;
    }

    public String getFirst_quick_time() {
        return first_quick_time;
    }

    public void setFirst_quick_time(String first_quick_time) {
        this.first_quick_time = first_quick_time;
    }

    public String getLast_quick_time() {
        return last_quick_time;
    }

    public void setLast_quick_time(String last_quick_time) {
        this.last_quick_time = last_quick_time;
    }

    public String getRepricer_id() {
        return repricer_id;
    }

    public void setRepricer_id(String repricer_id) {
        this.repricer_id = repricer_id;
    }

    public String getReprice_reason() {
        return reprice_reason;
    }

    public void setReprice_reason(String reprice_reason) {
        this.reprice_reason = reprice_reason;
    }

    public String getBack_id() {
        return back_id;
    }

    public void setBack_id(String back_id) {
        this.back_id = back_id;
    }

    public String getBack_reason() {
        return back_reason;
    }

    public void setBack_reason(String back_reason) {
        this.back_reason = back_reason;
    }

    public String getGifter_id() {
        return gifter_id;
    }

    public void setGifter_id(String gifter_id) {
        this.gifter_id = gifter_id;
    }

    public String getGift_reason() {
        return gift_reason;
    }

    public void setGift_reason(String gift_reason) {
        this.gift_reason = gift_reason;
    }

    public String getRe_discount_id() {
        return re_discount_id;
    }

    public void setRe_discount_id(String re_discount_id) {
        this.re_discount_id = re_discount_id;
    }

    public String getRe_discount() {
        return re_discount;
    }

    public void setRe_discount(String re_discount) {
        this.re_discount = re_discount;
    }

    public String getRe_discount_reason() {
        return re_discount_reason;
    }

    public void setRe_discount_reason(String re_discount_reason) {
        this.re_discount_reason = re_discount_reason;
    }

    public String getWait_tag() {
        return wait_tag;
    }

    public void setWait_tag(String wait_tag) {
        this.wait_tag = wait_tag;
    }

    public String getOut_tag() {
        return out_tag;
    }

    public void setOut_tag(String out_tag) {
        this.out_tag = out_tag;
    }

    public String getTable_gift_tag() {
        return table_gift_tag;
    }

    public void setTable_gift_tag(String table_gift_tag) {
        this.table_gift_tag = table_gift_tag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPrint_num() {
        return print_num;
    }

    public void setPrint_num(String print_num) {
        this.print_num = print_num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getRemark_data() {
        return remark_data;
    }

    public void setRemark_data(String remark_data) {
        this.remark_data = remark_data;
    }

    public String getServing_status() {
        return serving_status;
    }

    public void setServing_status(String serving_status) {
        this.serving_status = serving_status;
    }

    public String getDishes_id() {
        return dishes_id;
    }

    public void setDishes_id(String dishes_id) {
        this.dishes_id = dishes_id;
    }

    public String getSpecification_id() {
        return specification_id;
    }

    public void setSpecification_id(String specification_id) {
        this.specification_id = specification_id;
    }

    public String getCombo_id() {
        return combo_id;
    }

    public void setCombo_id(String combo_id) {
        this.combo_id = combo_id;
    }

    public String getDish_qty() {
        return dish_qty;
    }

    public void setDish_qty(String dish_qty) {
        this.dish_qty = dish_qty;
    }

    public String getFinally_price() {
        return finally_price;
    }

    public void setFinally_price(String finally_price) {
        this.finally_price = finally_price;
    }

    public String getDish_tag() {
        return dish_tag;
    }

    public void setDish_tag(String dish_tag) {
        this.dish_tag = dish_tag;
    }


    public List<ComboData> getCombo_data() {
        return combo_data;
    }

    public void setCombo_data(List<ComboData> combo_data) {
        this.combo_data = combo_data;
    }

    public SettalItem() {
    }

    public Dishesbean toDishesbean() {
        Dishesbean dishesbean = new Dishesbean();
        dishesbean.setId(getId());
        dishesbean.setBack_id(getBack_id());
        dishesbean.setLocal_num(TextUtils.isEmpty(getDish_qty()) ? 1 : Integer.valueOf(getDish_qty()));
        dishesbean.setDish_tag(getDish_tag());
        dishesbean.setWait_tag(TextUtils.isEmpty(getWait_tag()) ? 0 : Integer.valueOf(getWait_tag()));
        dishesbean.setOut_tag(TextUtils.isEmpty(getOut_tag()) ? 0 : Integer.valueOf(getOut_tag()));
        dishesbean.setDrawer(getDrawer());
        dishesbean.setCombo_data(getCombo_data());
        dishesbean.setWhole_flag(getWhole_flag());
        dishesbean.setWhole_discount(getWhole_discount());
        dishesbean.setWhole_discount_id(getWhole_discount_id());
        dishesbean.setVip_price(getVip_price());
        dishesbean.setBack_reason(getBack_reason());
        dishesbean.setSpecification_name(getSpecification_name());
        dishesbean.setSale_price(getSale_price());
        dishesbean.setIntegral_exchange(getIntegral_exchange());
        dishesbean.setFinally_price(getFinally_price());
        dishesbean.setDishes_name(getDish_name());
        dishesbean.setCombo_price(getCombo_price());
        dishesbean.setCombo_number(getCombo_number());
        dishesbean.setDish_number(getDish_number());
        dishesbean.setRemark(getRemark_data());
        dishesbean.setPay_tag(getPay_tag());
        dishesbean.setFinally_tag(getFinally_tag());
        if (dishesbean.isSelMeal()) {
            dishesbean.setCombo_time_price(getTime_price());
            dishesbean.setCombo_id(getCombo_id());
            SetMealGroupbean groupbean = new SetMealGroupbean();
            groupbean.setGroup_dishes(getDishesList(getCombo_data()));
            groupbean.setDishesbean(dishesbean);
            dishesbean.setMealGroupbean(groupbean);
        } else {
            dishesbean.setDishes_id(getDishes_id());
            Specifications specifications = getCunrrentSp();
            dishesbean.setCurrentSp(specifications);
        }
        return dishesbean;
    }

    private List<Dishesbean> getDishesList(List<ComboData> combo_data) {
        List<Dishesbean> list = new ArrayList<>();
        for (ComboData data : combo_data) {
            Dishesbean dishesbean = new Dishesbean();
            dishesbean.setNum(Integer.valueOf(TextUtils.isEmpty(data.getNum()) ? "0" : data.getNum()));
            dishesbean.setDish_name(data.getDishes_name());
            dishesbean.setDishes_specification(data.getDishes_specification());
            dishesbean.setSpecification_id(data.getId() + "");
            list.add(dishesbean);
        }
        return list;
    }

    private Specifications getCunrrentSp() {
        Specifications specifications = new Specifications();
        specifications.setAll_flag(getAll_flag());
        specifications.setSpecification(getSpecification_name());
        specifications.setId(getSpecification_id());
        specifications.setSale_price(getSale_price());
        specifications.setVip_price(getVip_price());
        specifications.setAll_discount(getAll_discount());
        specifications.setAll_discount_id(getAll_discount_id());
        specifications.setAll_flag(getAll_flag());
        specifications.setDiscount_type(getDiscount_type());
        specifications.setDish_id(getDishes_id());
        specifications.setTime_price(getTime_price());
        specifications.setTag(getTag());
        specifications.setDiscount(getDiscount());
        specifications.setDiscount_type(getDiscount_type());
        specifications.setIntegral_exchange(getIntegral_exchange());
        return specifications;
    }

    public int getAll_flag() {
        return all_flag;
    }

    public void setAll_flag(int all_flag) {
        this.all_flag = all_flag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.bill_code);
        dest.writeString(this.bill_date);
        dest.writeString(this.dish_qty);
        dest.writeString(this.specification_id);
        dest.writeString(this.specification_name);
        dest.writeString(this.dish_number);
        dest.writeString(this.dish_name);
        dest.writeString(this.drawer);
        dest.writeString(this.sale_price);
        dest.writeString(this.vip_price);
        dest.writeString(this.integral_exchange);
        dest.writeString(this.time_price);
        dest.writeString(this.finally_price);
        dest.writeInt(this.whole_flag);
        dest.writeString(this.whole_discount);
        dest.writeString(this.whole_discount_id);
        dest.writeString(this.discount);
        dest.writeString(this.discount_type);
        dest.writeString(this.tag);
        dest.writeString(this.tag_id);
        dest.writeInt(this.all_flag);
        dest.writeString(this.all_discount);
        dest.writeString(this.all_discount_id);
        dest.writeString(this.combo_id);
        dest.writeString(this.combo_number);
        dest.writeString(this.combo_price);
        dest.writeString(this.combo_group_id);
        dest.writeString(this.same_dish_id);
        dest.writeString(this.buy_give_id);
        dest.writeString(this.dish_tag);
        dest.writeString(this.pay_tag);
        dest.writeString(this.pay_num);
        dest.writeString(this.waiter_id);
        dest.writeString(this.cashier_id);
        dest.writeString(this.table_number);
        dest.writeString(this.source_table_number);
        dest.writeString(this.quick_count);
        dest.writeString(this.first_quick_time);
        dest.writeString(this.last_quick_time);
        dest.writeString(this.repricer_id);
        dest.writeString(this.reprice_reason);
        dest.writeString(this.back_id);
        dest.writeString(this.back_reason);
        dest.writeString(this.gifter_id);
        dest.writeString(this.gift_reason);
        dest.writeString(this.re_discount_id);
        dest.writeString(this.re_discount);
        dest.writeString(this.re_discount_reason);
        dest.writeString(this.wait_tag);
        dest.writeString(this.out_tag);
        dest.writeString(this.table_gift_tag);
        dest.writeString(this.remark);
        dest.writeString(this.print_num);
        dest.writeString(this.status);
        dest.writeString(this.order_date);
        dest.writeString(this.remark_data);
        dest.writeString(this.serving_status);
        dest.writeString(this.dishes_id);
        dest.writeTypedList(this.combo_data);
        dest.writeString(this.sale_id);
        dest.writeString(this.finally_tag);
    }

    protected SettalItem(Parcel in) {
        this.id = in.readInt();
        this.bill_code = in.readString();
        this.bill_date = in.readString();
        this.dish_qty = in.readString();
        this.specification_id = in.readString();
        this.specification_name = in.readString();
        this.dish_number = in.readString();
        this.dish_name = in.readString();
        this.drawer = in.readString();
        this.sale_price = in.readString();
        this.vip_price = in.readString();
        this.integral_exchange = in.readString();
        this.time_price = in.readString();
        this.finally_price = in.readString();
        this.whole_flag = in.readInt();
        this.whole_discount = in.readString();
        this.whole_discount_id = in.readString();
        this.discount = in.readString();
        this.discount_type = in.readString();
        this.tag = in.readString();
        this.tag_id = in.readString();
        this.all_flag = in.readInt();
        this.all_discount = in.readString();
        this.all_discount_id = in.readString();
        this.combo_id = in.readString();
        this.combo_number = in.readString();
        this.combo_price = in.readString();
        this.combo_group_id = in.readString();
        this.same_dish_id = in.readString();
        this.buy_give_id = in.readString();
        this.dish_tag = in.readString();
        this.pay_tag = in.readString();
        this.pay_num = in.readString();
        this.waiter_id = in.readString();
        this.cashier_id = in.readString();
        this.table_number = in.readString();
        this.source_table_number = in.readString();
        this.quick_count = in.readString();
        this.first_quick_time = in.readString();
        this.last_quick_time = in.readString();
        this.repricer_id = in.readString();
        this.reprice_reason = in.readString();
        this.back_id = in.readString();
        this.back_reason = in.readString();
        this.gifter_id = in.readString();
        this.gift_reason = in.readString();
        this.re_discount_id = in.readString();
        this.re_discount = in.readString();
        this.re_discount_reason = in.readString();
        this.wait_tag = in.readString();
        this.out_tag = in.readString();
        this.table_gift_tag = in.readString();
        this.remark = in.readString();
        this.print_num = in.readString();
        this.status = in.readString();
        this.order_date = in.readString();
        this.remark_data = in.readString();
        this.serving_status = in.readString();
        this.dishes_id = in.readString();
        this.combo_data = in.createTypedArrayList(ComboData.CREATOR);
        this.sale_id = in.readString();
        this.finally_tag = in.readString();
    }

    public static final Creator<SettalItem> CREATOR = new Creator<SettalItem>() {
        @Override
        public SettalItem createFromParcel(Parcel source) {
            return new SettalItem(source);
        }

        @Override
        public SettalItem[] newArray(int size) {
            return new SettalItem[size];
        }
    };
}

