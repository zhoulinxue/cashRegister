package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xgsb.datafactory.enu.DiscountType;
import com.xgsb.datafactory.enu.DishesType;
import com.zx.api.api.utils.AppLog;
import com.zx.api.api.utils.AppUtil;
import com.zx.api.api.utils.SPUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dishesbean implements Parcelable, MultiItemEntity, Cloneable, Comparable<Dishesbean> {
    private int id;
    private String combo_id;
    private String dishes_id;
    private String dishes_combo_id;
    private String sale_price;
    private String dish_category_id;
    private String dish_number;
    private String combo_number;
    private String dish_name;
    private String dishes_name;
    private String dishes_price;
    private String dishes_specification;
    private String specification_id;
    private String mnemonic_code;
    private String drawer;
    private int whole_flag;
    private String whole_discount;
    private String whole_discount_id;
    private String combo_price;
    private String vip_price;
    private String integral_exchange;
    private String optional;
    private String optional_group;
    private String combo_time_price;
    private String dish_tag;
    private String remark;
    private String showPrice;
    private String pay_tag = "0";
    private List<AddFavorablebean> addFavorablebeans;

    public List<AddFavorablebean> getAddFavorablebeans() {
        return addFavorablebeans;
    }

    public void setAddFavorablebeans(List<AddFavorablebean> addFavorablebeans) {
        this.addFavorablebeans = addFavorablebeans;
    }

    public String getDishes_id() {
        return dishes_id;
    }

    public void setDishes_id(String dishes_id) {
        this.dishes_id = dishes_id;
    }

    public String getPay_tag() {
        return pay_tag;
    }

    public void setPay_tag(String pay_tag) {
        this.pay_tag = pay_tag;
    }

    public String getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(String showPrice) {
        this.showPrice = showPrice;
    }

    public void setWhole_flag(int whole_flag) {
        this.whole_flag = whole_flag;
    }

    private String finally_price;
    private List<Remarkbean> remarkbeans;
    private List<ComboData> combo_data;
    private List<Specifications> dishes;
    private Specifications currentSp;
    private int local_num = 1;
    private int total_local_num;
    private int num;
    private int wait_tag;
    private int out_tag;
    private DishesType childTag;
    private String group_id;
    private int is_default;
    private SetMealGroupbean mealGroupbean;
    private String specification_name;
    private String sale_id;
    private String combo_guqing;

    public String getCombo_guqing() {
        return combo_guqing;
    }

    public void setCombo_guqing(String combo_guqing) {
        this.combo_guqing = combo_guqing;
    }

    public String getSale_id() {
        return sale_id;
    }

    public void setSale_id(String sale_id) {
        this.sale_id = sale_id;
    }

    public String getSpecification_name() {
        return specification_name;
    }

    public void setSpecification_name(String specification_name) {
        this.specification_name = specification_name;
    }

    /**
     * 获取菜品折扣价
     *
     * @param discountType
     * @return
     */
    public String getDiscountPrice(DiscountType discountType) {
        String discoutPrice = "";
        if ("1".equals(getDish_tag())) {
            discoutPrice = getCombo_price();
        } else {
            discoutPrice = getCurrentSp().getSale_price();
        }
        if (discountType == null || DiscountType.NULL == discountType) {
            return discoutPrice;
        }
        switch (discountType) {
            case MEMBER:
                discoutPrice = getVip_price();
                break;
            case ALL_TAG:
                if ("2".equals(getDish_tag())) {
                    //全单折扣
                    discoutPrice = "" + getCurrentSp().getDiscountPrice();
                }
                break;
            case WHOLE_TAG:
                if (1 == getWhole_flag()) {
                    if ("1".equals(getDish_tag())) {
                        discoutPrice = getComboDicountPrice();
                    } else {
                        //全单折扣
                        discoutPrice = "" + (AppUtil.getFloatFromString(getCurrentSp().getSale_price()).floatValue() * AppUtil.getFloatFromString(getWhole_discount()).floatValue() / 100);
                    }
                }
                break;
        }
        return discoutPrice;
    }

    /**
     * 获取 套餐 折扣价
     *
     * @return
     */
    public String getComboDicountPrice() {
        return (AppUtil.getFloatFromString(getCombo_price()).floatValue() * AppUtil.getFloatFromString(getWhole_discount()).floatValue() / 100) + "";
    }

    public String getFinally_price() {
        return finally_price;
    }

    public int getWhole_flag() {
        return whole_flag;
    }

    public void setFinally_price(String finally_price) {
        this.finally_price = finally_price;
    }

    public SetMealGroupbean getMealGroupbean() {
        return mealGroupbean;
    }

    public void setMealGroupbean(SetMealGroupbean mealGroupbean) {
        this.mealGroupbean = mealGroupbean;
    }

    public String getDishes_combo_id() {
        return dishes_combo_id;
    }

    public void setDishes_combo_id(String dishes_combo_id) {
        this.dishes_combo_id = dishes_combo_id;
    }

    public int getIs_default() {
        return is_default;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getDishes_name() {
        return dishes_name;
    }

    public void setDishes_name(String dishes_name) {
        this.dishes_name = dishes_name;
    }

    public String getDishes_price() {
        return dishes_price;
    }

    public void setDishes_price(String dishes_price) {
        this.dishes_price = dishes_price;
    }

    public String getDishes_specification() {
        return dishes_specification;
    }

    public void setDishes_specification(String dishes_specification) {
        this.dishes_specification = dishes_specification;
    }

    public String getSpecification_id() {
        return specification_id;
    }

    public void setSpecification_id(String specification_id) {
        this.specification_id = specification_id;
    }

    public List<Remarkbean> getRemarkbeans() {
        return remarkbeans;
    }

    public void setRemarkbeans(List<Remarkbean> remarkbeans) {
        this.remarkbeans = remarkbeans;
    }

    public int getWait_tag() {
        return wait_tag;
    }

    public void setWait_tag(int wait_tag) {
        this.wait_tag = wait_tag;
    }

    public int getOut_tag() {
        return out_tag;
    }

    public void setOut_tag(int out_tag) {
        this.out_tag = out_tag;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Specifications getCurrentSp() {
        if (currentSp != null) {
            return currentSp;
        }
        return (getDishes() == null || getDishes().size() == 0) ? null : getDishes().get(0);
    }

    public void setCurrentSp(Specifications currentSp) {
        this.currentSp = currentSp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCombo_id() {
        return combo_id;
    }

    public void setCombo_id(String combo_id) {
        this.combo_id = combo_id;
    }

    public String getDish_category_id() {
        return dish_category_id;
    }

    public void setDish_category_id(String dish_category_id) {
        this.dish_category_id = dish_category_id;
    }

    public String getDish_number() {
        return dish_number;
    }

    public void setDish_number(String dish_number) {
        this.dish_number = dish_number;
    }

    public String getCombo_number() {
        return combo_number;
    }

    public void setCombo_number(String combo_number) {
        this.combo_number = combo_number;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public String getMnemonic_code() {
        return mnemonic_code;
    }

    public void setMnemonic_code(String mnemonic_code) {
        this.mnemonic_code = mnemonic_code;
    }

    public String getDrawer() {
        return drawer;
    }

    public void setDrawer(String drawer) {
        this.drawer = drawer;
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

    public String getCombo_price() {
        return combo_price;
    }

    public void setCombo_price(String combo_price) {
        this.combo_price = combo_price;
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

    public String getOptional() {
        return optional;
    }

    public void setOptional(String optional) {
        this.optional = optional;
    }

    public String getOptional_group() {
        return optional_group;
    }

    public void setOptional_group(String optional_group) {
        this.optional_group = optional_group;
    }

    public String getCombo_time_price() {
        return combo_time_price;
    }

    public void setCombo_time_price(String combo_time_price) {
        this.combo_time_price = combo_time_price;
    }

    public String getDish_tag() {
        return dish_tag;
    }

    public void setDish_tag(String dish_tag) {
        this.dish_tag = dish_tag;
    }

    public List<Specifications> getDishes() {
        return dishes;
    }

    public void setDishes(List<Specifications> dishes) {
        this.dishes = dishes;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public Dishesbean() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Dishesbean) {
            return getId() == ((Dishesbean) obj).getId() || isSame((Dishesbean) obj);
        }
        return super.equals(obj);
    }

    private boolean isSame(Dishesbean bean) {
        boolean isSame = false;
        isSame = !TextUtils.isEmpty(getGroup_id()) &&
                !TextUtils.isEmpty(getSpecification_id()) &&
                getSpecification_id().equals(bean.getSpecification_id()) &&
                getGroup_id().equals(bean.getGroup_id());
        return isSame;
    }

    public boolean isMultDishes() {
        if (getDishes() != null && getDishes().size() > 1) {
            return true;
        }
        return false;
    }

    public DishesType getChildTag() {
        return childTag;
    }

    public void setChildTag(DishesType childTag) {
        this.childTag = childTag;
    }

    public int getLocal_num() {
        return local_num;
    }

    public void setLocal_num(int local_num) {
        this.local_num = local_num;
    }

    @Override
    public int getItemType() {
        return childTag == null ? 0 : childTag.getValue();
    }

    public int getTotal_local_num() {
        return total_local_num;
    }

    public void setTotal_local_num(int total_local_num) {
        this.total_local_num = total_local_num;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public List<ComboData> getCombo_data() {
        return combo_data;
    }

    public void setCombo_data(List<ComboData> combo_data) {
        this.combo_data = combo_data;
    }

    public SaleOutbean toSaleOutbean() {
        SaleOutbean saleOutbean = new SaleOutbean();
        if ("2".equals(getDish_tag())) {
            saleOutbean.setDishes_id(getCurrentSp().getId() + "");
            saleOutbean.setNumber(getCurrentSp().getGuqing());
        } else {
            saleOutbean.setDishes_id(getCombo_id());
            saleOutbean.setCombo_id(getCombo_id());
            saleOutbean.setNumber(getCombo_guqing());
        }
        saleOutbean.setDishes_name(TextUtils.isEmpty(getDishes_name()) ? getDish_name() : getDishes_name());
        return saleOutbean;
    }

    public boolean isSelMeal() {
        return "1".equals(getDish_tag());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.combo_id);
        dest.writeString(this.dishes_id);
        dest.writeString(this.dishes_combo_id);
        dest.writeString(this.sale_price);
        dest.writeString(this.dish_category_id);
        dest.writeString(this.dish_number);
        dest.writeString(this.combo_number);
        dest.writeString(this.dish_name);
        dest.writeString(this.dishes_name);
        dest.writeString(this.dishes_price);
        dest.writeString(this.dishes_specification);
        dest.writeString(this.specification_id);
        dest.writeString(this.mnemonic_code);
        dest.writeString(this.drawer);
        dest.writeInt(this.whole_flag);
        dest.writeString(this.whole_discount);
        dest.writeString(this.whole_discount_id);
        dest.writeString(this.combo_price);
        dest.writeString(this.vip_price);
        dest.writeString(this.integral_exchange);
        dest.writeString(this.optional);
        dest.writeString(this.optional_group);
        dest.writeString(this.combo_time_price);
        dest.writeString(this.dish_tag);
        dest.writeString(this.remark);
        dest.writeString(this.showPrice);
        dest.writeString(this.pay_tag);
        dest.writeTypedList(this.addFavorablebeans);
        dest.writeString(this.finally_price);
        dest.writeTypedList(this.remarkbeans);
        dest.writeTypedList(this.combo_data);
        dest.writeTypedList(this.dishes);
        dest.writeParcelable(this.currentSp, flags);
        dest.writeInt(this.local_num);
        dest.writeInt(this.total_local_num);
        dest.writeInt(this.num);
        dest.writeInt(this.wait_tag);
        dest.writeInt(this.out_tag);
        dest.writeInt(this.childTag == null ? -1 : this.childTag.ordinal());
        dest.writeString(this.group_id);
        dest.writeInt(this.is_default);
        dest.writeParcelable(this.mealGroupbean, flags);
        dest.writeString(this.specification_name);
        dest.writeString(this.sale_id);
        dest.writeString(this.combo_guqing);
    }

    protected Dishesbean(Parcel in) {
        this.id = in.readInt();
        this.combo_id = in.readString();
        this.dishes_id = in.readString();
        this.dishes_combo_id = in.readString();
        this.sale_price = in.readString();
        this.dish_category_id = in.readString();
        this.dish_number = in.readString();
        this.combo_number = in.readString();
        this.dish_name = in.readString();
        this.dishes_name = in.readString();
        this.dishes_price = in.readString();
        this.dishes_specification = in.readString();
        this.specification_id = in.readString();
        this.mnemonic_code = in.readString();
        this.drawer = in.readString();
        this.whole_flag = in.readInt();
        this.whole_discount = in.readString();
        this.whole_discount_id = in.readString();
        this.combo_price = in.readString();
        this.vip_price = in.readString();
        this.integral_exchange = in.readString();
        this.optional = in.readString();
        this.optional_group = in.readString();
        this.combo_time_price = in.readString();
        this.dish_tag = in.readString();
        this.remark = in.readString();
        this.showPrice = in.readString();
        this.pay_tag = in.readString();
        this.addFavorablebeans = in.createTypedArrayList(AddFavorablebean.CREATOR);
        this.finally_price = in.readString();
        this.remarkbeans = in.createTypedArrayList(Remarkbean.CREATOR);
        this.combo_data = in.createTypedArrayList(ComboData.CREATOR);
        this.dishes = in.createTypedArrayList(Specifications.CREATOR);
        this.currentSp = in.readParcelable(Specifications.class.getClassLoader());
        this.local_num = in.readInt();
        this.total_local_num = in.readInt();
        this.num = in.readInt();
        this.wait_tag = in.readInt();
        this.out_tag = in.readInt();
        int tmpChildTag = in.readInt();
        this.childTag = tmpChildTag == -1 ? null : DishesType.values()[tmpChildTag];
        this.group_id = in.readString();
        this.is_default = in.readInt();
        this.mealGroupbean = in.readParcelable(SetMealGroupbean.class.getClassLoader());
        this.specification_name = in.readString();
        this.sale_id = in.readString();
        this.combo_guqing = in.readString();
    }

    public static final Creator<Dishesbean> CREATOR = new Creator<Dishesbean>() {
        @Override
        public Dishesbean createFromParcel(Parcel source) {
            return new Dishesbean(source);
        }

        @Override
        public Dishesbean[] newArray(int size) {
            return new Dishesbean[size];
        }
    };

    public AddFavorablebean getMinFavorable() {
        return getAddFavorablebeans() == null ? null : getAddFavorablebeans().get(getAddFavorablebeans().size() - 1);
    }

    public AddFavorablebean getMaxFavorable() {
        return getAddFavorablebeans() == null ? null : getAddFavorablebeans().get(0);
    }

    @Override
    public int compareTo(@NonNull Dishesbean o) {
        //注意：一定是 o.age>this.age,若 this.age在前，则排序功能无效（亲测）。
        if (Float.valueOf(o.getPay_tag()) > Float.valueOf(this.getPay_tag())) {
            //首次执行，o.age代表List里第一个元素，this.age是List里第二个元素
            return 1;
        } else if (Float.valueOf(o.getPay_tag()) == Float.valueOf(this.getPay_tag())) {
            return 0;
        } else {
            return -1;
        }
    }
}
