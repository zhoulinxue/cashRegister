package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Name: Member
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-26 16:23
 */
public class Member implements Parcelable {
    private int id;
    private String xgsb_system_number;
    private String name;
    private String card_number;
    private String mobile;
    private String growth_value;
    private String birthday;
    private String open_card_time;
    private String update_time;
    private String status;
    private String membership;
    private String mail;
    private String age;
    private String sex;
    private String id_card;
    private String occupation;
    private String address;
    private String entity_card;
    private String status_type;
    private String create_date;
    private String reserve_balance_give;
    private String reserve_balance_principal;
    private String money;
    private String consumption_count;
    private String last_consumption_time;
    private String consumption_money_count;
    private String money_principal;
    private String money_give;
    private String integral;
    private String grade_name;
    private String grade_discount;
    private String grade_discount_than;
    private String grade_discount_than_name;
    private List<Couponbean> voucher;
    private Sellerbean sales;
    private String coding_card;
    private String integral_offset;
    private String integral_offset_than;
    private List<Dishesbean> no_relation;

    public List<Dishesbean> getNo_relation() {
        return no_relation;
    }

    public void setNo_relation(List<Dishesbean> no_relation) {
        this.no_relation = no_relation;
    }

    public String getIntegral_offset() {
        return integral_offset;
    }

    public void setIntegral_offset(String integral_offset) {
        this.integral_offset = integral_offset;
    }

    public String getIntegral_offset_than() {
        return integral_offset_than;
    }

    public void setIntegral_offset_than(String integral_offset_than) {
        this.integral_offset_than = integral_offset_than;
    }

    public Sellerbean getSales() {
        return sales;
    }

    public void setSales(Sellerbean sales) {
        this.sales = sales;
    }

    public String getMoney_principal() {
        return money_principal;
    }

    public void setMoney_principal(String money_principal) {
        this.money_principal = money_principal;
    }

    public String getMoney_give() {
        return money_give;
    }

    public void setMoney_give(String money_give) {
        this.money_give = money_give;
    }

    public String getGrade_discount() {
        return grade_discount;
    }

    public void setGrade_discount(String grade_discount) {
        this.grade_discount = grade_discount;
    }

    private String getGradeName(String grade_discount) {
        String name = "无优惠";
        if ("2".equals(getGrade_discount())) {
            name = "整单折扣      " + getGrade_discount_than() + "  折";
        } else if (" 3".equals(getGrade_discount())) {
            name = "菜品会员价      " + getGrade_discount_than() + "  折";
        }
        return name;
    }

    public List<Couponbean> getVoucher() {
        return voucher;
    }

    public void setVoucher(List<Couponbean> voucher) {
        this.voucher = voucher;
    }

    public String getGrade_discount_than() {
        return grade_discount_than;
    }

    public void setGrade_discount_than(String grade_discount_than) {
        this.grade_discount_than = grade_discount_than;
    }


    public String getGrade_name() {
        return grade_name;
    }

    public void setGrade_name(String grade_name) {
        this.grade_name = grade_name;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getConsumption_money_count() {
        return consumption_money_count;
    }

    public void setConsumption_money_count(String consumption_money_count) {
        this.consumption_money_count = consumption_money_count;
    }

    public String getLast_consumption_time() {
        return last_consumption_time;
    }

    public void setLast_consumption_time(String last_consumption_time) {
        this.last_consumption_time = last_consumption_time;
    }

    public String getConsumption_count() {
        return consumption_count;
    }

    public void setConsumption_count(String consumption_count) {
        this.consumption_count = consumption_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getXgsb_system_number() {
        return xgsb_system_number;
    }

    public void setXgsb_system_number(String xgsb_system_number) {
        this.xgsb_system_number = xgsb_system_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getOpen_card_time() {
        return open_card_time;
    }

    public void setOpen_card_time(String open_card_time) {
        this.open_card_time = open_card_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEntity_card() {
        return entity_card;
    }

    public void setEntity_card(String entity_card) {
        this.entity_card = entity_card;
    }

    public String getStatus_type() {
        return status_type;
    }

    public void setStatus_type(String status_type) {
        this.status_type = status_type;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getReserve_balance_give() {
        return reserve_balance_give;
    }

    public void setReserve_balance_give(String reserve_balance_give) {
        this.reserve_balance_give = reserve_balance_give;
    }

    public String getReserve_balance_principal() {
        return reserve_balance_principal;
    }

    public void setReserve_balance_principal(String reserve_balance_principal) {
        this.reserve_balance_principal = reserve_balance_principal;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getGrowth_value() {
        return growth_value;
    }

    public void setGrowth_value(String growth_value) {
        this.growth_value = growth_value;
    }

    public String getGrade_discount_than_name() {
        setGrade_discount_than_name(getGradeName(getGrade_discount()));
        return grade_discount_than_name;
    }

    public void setGrade_discount_than_name(String grade_discount_than_name) {
        this.grade_discount_than_name = grade_discount_than_name;
    }

    public String getCoding_card() {
        return coding_card;
    }

    public void setCoding_card(String coding_card) {
        this.coding_card = coding_card;
    }

    public Member() {
    }

    public String getSeller_Id() {
        return "";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.xgsb_system_number);
        dest.writeString(this.name);
        dest.writeString(this.card_number);
        dest.writeString(this.mobile);
        dest.writeString(this.growth_value);
        dest.writeString(this.birthday);
        dest.writeString(this.open_card_time);
        dest.writeString(this.update_time);
        dest.writeString(this.status);
        dest.writeString(this.membership);
        dest.writeString(this.mail);
        dest.writeString(this.age);
        dest.writeString(this.sex);
        dest.writeString(this.id_card);
        dest.writeString(this.occupation);
        dest.writeString(this.address);
        dest.writeString(this.entity_card);
        dest.writeString(this.status_type);
        dest.writeString(this.create_date);
        dest.writeString(this.reserve_balance_give);
        dest.writeString(this.reserve_balance_principal);
        dest.writeString(this.money);
        dest.writeString(this.consumption_count);
        dest.writeString(this.last_consumption_time);
        dest.writeString(this.consumption_money_count);
        dest.writeString(this.money_principal);
        dest.writeString(this.money_give);
        dest.writeString(this.integral);
        dest.writeString(this.grade_name);
        dest.writeString(this.grade_discount);
        dest.writeString(this.grade_discount_than);
        dest.writeString(this.grade_discount_than_name);
        dest.writeTypedList(this.voucher);
        dest.writeParcelable(this.sales, flags);
        dest.writeString(this.coding_card);
        dest.writeString(this.integral_offset);
        dest.writeString(this.integral_offset_than);
        dest.writeTypedList(this.no_relation);
    }

    protected Member(Parcel in) {
        this.id = in.readInt();
        this.xgsb_system_number = in.readString();
        this.name = in.readString();
        this.card_number = in.readString();
        this.mobile = in.readString();
        this.growth_value = in.readString();
        this.birthday = in.readString();
        this.open_card_time = in.readString();
        this.update_time = in.readString();
        this.status = in.readString();
        this.membership = in.readString();
        this.mail = in.readString();
        this.age = in.readString();
        this.sex = in.readString();
        this.id_card = in.readString();
        this.occupation = in.readString();
        this.address = in.readString();
        this.entity_card = in.readString();
        this.status_type = in.readString();
        this.create_date = in.readString();
        this.reserve_balance_give = in.readString();
        this.reserve_balance_principal = in.readString();
        this.money = in.readString();
        this.consumption_count = in.readString();
        this.last_consumption_time = in.readString();
        this.consumption_money_count = in.readString();
        this.money_principal = in.readString();
        this.money_give = in.readString();
        this.integral = in.readString();
        this.grade_name = in.readString();
        this.grade_discount = in.readString();
        this.grade_discount_than = in.readString();
        this.grade_discount_than_name = in.readString();
        this.voucher = in.createTypedArrayList(Couponbean.CREATOR);
        this.sales = in.readParcelable(Sellerbean.class.getClassLoader());
        this.coding_card = in.readString();
        this.integral_offset = in.readString();
        this.integral_offset_than = in.readString();
        this.no_relation = in.createTypedArrayList(Dishesbean.CREATOR);
    }

    public static final Creator<Member> CREATOR = new Creator<Member>() {
        @Override
        public Member createFromParcel(Parcel source) {
            return new Member(source);
        }

        @Override
        public Member[] newArray(int size) {
            return new Member[size];
        }
    };
}
