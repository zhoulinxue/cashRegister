package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Name: MemberLevel
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-04 10:47
 */
public class MemberLevel implements Parcelable {
    private int id;
    private String xgsb_system_number;
    private String grade_name;
    private String grade_discount;
    private String grade_discount_than;
    private String participate_in_food_id;
    private String no_participate_in_food_id;
    private String condition;
    private String give_integral;
    private String is_new_user;
    private String create_date;
    private String update_date;
    private String status;
    private String growth_threshold;
    private String grade_discount_than_name;

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

    public String getGrade_name() {
        return grade_name;
    }

    public void setGrade_name(String grade_name) {
        this.grade_name = grade_name;
    }

    public String getGrade_discount() {
        return grade_discount;
    }

    public void setGrade_discount(String grade_discount) {
        this.grade_discount = grade_discount;
    }

    public String getGrade_discount_than() {
        return grade_discount_than;
    }

    public void setGrade_discount_than(String grade_discount_than) {
        this.grade_discount_than = grade_discount_than;
    }

    public String getParticipate_in_food_id() {
        return participate_in_food_id;
    }

    public void setParticipate_in_food_id(String participate_in_food_id) {
        this.participate_in_food_id = participate_in_food_id;
    }

    public String getNo_participate_in_food_id() {
        return no_participate_in_food_id;
    }

    public void setNo_participate_in_food_id(String no_participate_in_food_id) {
        this.no_participate_in_food_id = no_participate_in_food_id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getGive_integral() {
        return give_integral;
    }

    public void setGive_integral(String give_integral) {
        this.give_integral = give_integral;
    }

    public String getIs_new_user() {
        return is_new_user;
    }

    public void setIs_new_user(String is_new_user) {
        this.is_new_user = is_new_user;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGrowth_threshold() {
        return growth_threshold;
    }

    public void setGrowth_threshold(String growth_threshold) {
        this.growth_threshold = growth_threshold;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.xgsb_system_number);
        dest.writeString(this.grade_name);
        dest.writeString(this.grade_discount);
        dest.writeString(this.grade_discount_than);
        dest.writeString(this.participate_in_food_id);
        dest.writeString(this.no_participate_in_food_id);
        dest.writeString(this.condition);
        dest.writeString(this.give_integral);
        dest.writeString(this.is_new_user);
        dest.writeString(this.create_date);
        dest.writeString(this.update_date);
        dest.writeString(this.status);
        dest.writeString(this.growth_threshold);
    }

    public MemberLevel() {
    }

    protected MemberLevel(Parcel in) {
        this.id = in.readInt();
        this.xgsb_system_number = in.readString();
        this.grade_name = in.readString();
        this.grade_discount = in.readString();
        this.grade_discount_than = in.readString();
        this.participate_in_food_id = in.readString();
        this.no_participate_in_food_id = in.readString();
        this.condition = in.readString();
        this.give_integral = in.readString();
        this.is_new_user = in.readString();
        this.create_date = in.readString();
        this.update_date = in.readString();
        this.status = in.readString();
        this.growth_threshold = in.readString();
    }

    public static final Parcelable.Creator<MemberLevel> CREATOR = new Parcelable.Creator<MemberLevel>() {
        @Override
        public MemberLevel createFromParcel(Parcel source) {
            return new MemberLevel(source);
        }

        @Override
        public MemberLevel[] newArray(int size) {
            return new MemberLevel[size];
        }
    };

    public void setGrade_discount_than_name(String grade_discount_than_name) {
        this.grade_discount_than_name = grade_discount_than_name;
    }

    public String getGrade_discount_than_name() {
        if ("2".equals(getGrade_discount())) {
            setGrade_discount_than_name("整单折扣      " + getGrade_discount_than() + "  折");
        } else if ("3".equals(getGrade_discount())) {
            setGrade_discount_than_name("菜品会员价      " + getGrade_discount_than() + "  折");
        } else {
            setGrade_discount_than_name("无优惠");
        }
        return grade_discount_than_name;
    }
}
