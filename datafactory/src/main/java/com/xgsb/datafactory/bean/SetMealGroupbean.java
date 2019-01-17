package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SetMealGroupbean implements Parcelable {
    private String id;
    private String group_name;
    private int must_num;
    private String is_reply;
    private String dishes_name;
    private String rule;
    private List<Dishesbean> group_dishes;
    private Dishesbean dishesbean;


    public int isSelected() {
        if ("-1".equals(getId())) {
            return 0;
        } else {
            int num = 0;
            for (Dishesbean dishesbean : getGroup_dishes()) {
                if (dishesbean.getNum() != 0) {
                    num += dishesbean.getNum();
                }
            }
            return getMust_num() - num;
        }
    }

    public Dishesbean getDishesbean() {
        return dishesbean;
    }

    public void setDishesbean(Dishesbean dishesbean) {
        this.dishesbean = dishesbean;
    }

    public List<Dishesbean> getGroup_dishes() {
        return group_dishes;
    }


    public void setGroup_dishes(List<Dishesbean> group_dishes) {
        this.group_dishes = group_dishes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public int getMust_num() {
        return must_num;
    }

    public void setMust_num(int must_num) {
        this.must_num = must_num;
    }

    public String getIs_reply() {
        return is_reply;
    }

    public void setIs_reply(String is_reply) {
        this.is_reply = is_reply;
    }

    public String getDishes_name() {
        return dishes_name;
    }

    public void setDishes_name(String dishes_name) {
        this.dishes_name = dishes_name;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public SetMealGroupbean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.group_name);
        dest.writeInt(this.must_num);
        dest.writeString(this.is_reply);
        dest.writeString(this.dishes_name);
        dest.writeString(this.rule);
        dest.writeTypedList(this.group_dishes);
    }

    protected SetMealGroupbean(Parcel in) {
        this.id = in.readString();
        this.group_name = in.readString();
        this.must_num = in.readInt();
        this.is_reply = in.readString();
        this.dishes_name = in.readString();
        this.rule = in.readString();
        this.group_dishes = in.createTypedArrayList(Dishesbean.CREATOR);
    }

    public static final Creator<SetMealGroupbean> CREATOR = new Creator<SetMealGroupbean>() {
        @Override
        public SetMealGroupbean createFromParcel(Parcel source) {
            return new SetMealGroupbean(source);
        }

        @Override
        public SetMealGroupbean[] newArray(int size) {
            return new SetMealGroupbean[size];
        }
    };

    public boolean isMore() {
        int current = 0;
        for (Dishesbean dishesbean : getGroup_dishes()) {
            current += dishesbean.getNum();
        }
        if (getMust_num() > current) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SetMealGroupbean) {
            return getId().equals(((SetMealGroupbean) obj).getId());
        }
        return super.equals(obj);
    }
}
