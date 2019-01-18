package com.shigoo.cashregister.utils;

import android.text.TextUtils;

import com.xgsb.datafactory.JSONManager;
import com.xgsb.datafactory.bean.AddFavorablebean;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.Member;
import com.xgsb.datafactory.enu.DiscountType;
import com.zx.api.api.utils.AppLog;
import com.zx.api.api.utils.AppUtil;
import com.zx.api.api.utils.SPUtil;
import com.zx.network.Param;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DishesUtils {
    /**
     * 根据折扣率 兑算 折扣金额
     *
     * @param member
     * @return
     */
    public static int getDiscountMoney(Member member, List<Dishesbean> dishesbeans) {
        float favorableMoney = 0f;
        for (Dishesbean dishesbean : dishesbeans) {
            favorableMoney += getItemDiscountMoney(member, dishesbean);
        }
        return (int) favorableMoney;
    }

    public static float getItemDiscountMoney(Member member, Dishesbean dishesbean) {
        float favorableMoney = 0f;
        //折扣率
        float discountThan = AppUtil.getFloatFromString(member.getGrade_discount_than()).floatValue() / 10;
        for (Dishesbean nodi : member.getNo_relation()) {
            if ("2".equals(dishesbean.getDish_tag())) {
                if (!nodi.getDishes_id().equals(dishesbean.getCurrentSp().getDish_id())) {
                    favorableMoney += AppUtil.getFloatFromString(dishesbean.getCurrentSp().getSale_price()).floatValue() * (1 - discountThan);
                }
            }
        }
        return (int) favorableMoney;
    }

    public static float getVipPrice(float salePrice, List<Dishesbean> dishesbeans) {
        float favorableMoney = 0f;
        for (Dishesbean dishesbean : dishesbeans) {
            favorableMoney += getPriceItemFavorable(dishesbean);
        }
        return salePrice - favorableMoney;
    }

    public static float getPriceItemFavorable(Dishesbean dishesbean) {
        float favorableMoney = 0f;
        if ("1".equals(dishesbean.getDish_tag())) {
            favorableMoney += TextUtils.isEmpty(dishesbean.getVip_price()) ? AppUtil.getFloatFromString(dishesbean.getCombo_price()).floatValue() : AppUtil.getFloatFromString(dishesbean.getVip_price()).floatValue();
        } else {
            favorableMoney += TextUtils.isEmpty(dishesbean.getCurrentSp().getVip_price()) ? AppUtil.getFloatFromString(dishesbean.getCurrentSp().getSale_price()).floatValue() : AppUtil.getFloatFromString(dishesbean.getCurrentSp().getVip_price()).floatValue();
        }
        return favorableMoney;
    }

    /**
     * 填充 菜品 享受的优惠数组
     */
    public static void fullFavorableList(Dishesbean dishesbean, DiscountType discountType, String billCode) {
        List<AddFavorablebean> addFavorablebeans = new ArrayList<>();
        String timePrice = "";
        String salePrice = "";
        String vipPrice = "";
        String specialPrice = "";
        String discountPrice = "";
        String dicountName = "";
        float discount = 0f;
        if ("1".equals(dishesbean.getDish_tag())) {
            //套餐
            timePrice = dishesbean.getCombo_time_price();
            salePrice = dishesbean.getCombo_price();
            vipPrice = dishesbean.getVip_price();
        } else {
            if (dishesbean.getCurrentSp() != null) {
                timePrice = dishesbean.getCurrentSp().getTime_price();
                salePrice = dishesbean.getCurrentSp().getSale_price();
                vipPrice = dishesbean.getCurrentSp().getVip_price();
                if ("1".equals(dishesbean.getCurrentSp().getTag())) {
                    specialPrice = "" + AppUtil.getFloatFromString(salePrice).floatValue() * AppUtil.getFloatFromString(dishesbean.getCurrentSp().getDiscount()).floatValue() / 100;
                }
            }
        }
        switch (discountType) {
            case MEMBER:
                String memberJson = SPUtil.getInstance().getString(billCode);
                Member member = (Member) JSONManager.getInstance().parseObject(memberJson, Member.class);
                if (member != null) {
                    if ("2".equals(member.getGrade_discount())) {
                        vipPrice = "";
                        dicountName = "优惠-会员权益";
                        discount = AppUtil.getFloatFromString(member.getGrade_discount_than()).floatValue() / 10;
                        discountPrice = (AppUtil.getFloatFromString(salePrice).floatValue() * discount) + "";
                    } else if ("1".equals(member.getGrade_discount())) {
                        vipPrice = "";
                        dicountName = "会员价";
                    }
                }
                break;
            case ALL_TAG:
                dicountName = "全单";
                if ("2".equals(dishesbean.getDish_tag())) {
                    discount = AppUtil.getFloatFromString(dishesbean.getCurrentSp().getAll_discount()).floatValue() / 10;
                    //整单折扣
                    discountPrice = "" + dishesbean.getCurrentSp().getDiscountPrice();
                }
                break;
            case WHOLE_TAG:
                dicountName = "整单";
                AppLog.print(discount + "!!!!!!!!!!!" + dishesbean.getWhole_flag());
                if (1 == dishesbean.getWhole_flag()) {
                    discount = AppUtil.getFloatFromString(dishesbean.getWhole_discount()).floatValue() / 10;
                    if ("1".equals(dishesbean.getDish_tag())) {
                        discountPrice = dishesbean.getComboDicountPrice();
                    } else {
                        //全单折扣
                        discountPrice = "" + (AppUtil.getFloatFromString(dishesbean.getCurrentSp().getSale_price()).floatValue() * AppUtil.getFloatFromString(dishesbean.getWhole_discount()).floatValue() / 100);
                    }
                }
                break;
        }
        int num = dishesbean.getLocal_num() == 0 ? 1 : dishesbean.getLocal_num();
        float totoalSalePrice = AppUtil.getFloatFromString(salePrice).floatValue() * num;
        AddFavorablebean noFavorablebean = new AddFavorablebean();
        noFavorablebean.setName("无优惠");
        noFavorablebean.setDiscount(discount + "");
        noFavorablebean.setMoney(totoalSalePrice + "");
        addFavorablebeans.add(noFavorablebean);
        if (!TextUtils.isEmpty(timePrice)) {
            float toatalTimePrice = AppUtil.getFloatFromString(timePrice).floatValue() * num;
            AddFavorablebean addFavorablebean = new AddFavorablebean();
            addFavorablebean.setName("优惠-时段价");
            addFavorablebean.setDiscount(discount + "");
            addFavorablebean.setMoney(toatalTimePrice + "");
            addFavorablebeans.add(addFavorablebean);
        }
        if (!TextUtils.isEmpty(vipPrice) && discountType == DiscountType.MEMBER) {
            float totalVipPrice = AppUtil.getFloatFromString(vipPrice).floatValue() * num;
            AddFavorablebean addFavorablebean = new AddFavorablebean();
            addFavorablebean.setName("会员价");
            addFavorablebean.setDiscount(discount + "");
            addFavorablebean.setMoney(totalVipPrice + "");
            addFavorablebeans.add(addFavorablebean);
        }

        if (!TextUtils.isEmpty(discountPrice)) {
            float totalDiscountPrivce = AppUtil.getFloatFromString(discountPrice).floatValue() * num;
            AddFavorablebean addFavorablebean = new AddFavorablebean();
            addFavorablebean.setName(dicountName);
            addFavorablebean.setDiscount(discount + "");
            addFavorablebean.setMoney(totalDiscountPrivce + "");
            addFavorablebeans.add(addFavorablebean);
        }

        if (!TextUtils.isEmpty(specialPrice)) {
            float totalSpcial = AppUtil.getFloatFromString(specialPrice).floatValue() * num;
            AddFavorablebean addFavorablebean = new AddFavorablebean();
            addFavorablebean.setName("优惠-特价菜");
            addFavorablebean.setDiscount(discount + "");
            addFavorablebean.setMoney(totalSpcial + "");
            addFavorablebeans.add(addFavorablebean);
        }
        dishesbean.setAddFavorablebeans(addFavorablebeans);
        Collections.sort(addFavorablebeans);
        for (AddFavorablebean dad : addFavorablebeans) {
            AppLog.print(JSONManager.getInstance().toJson(dad));
        }
    }

    public static List<Float> calculaPrice(List<Dishesbean> dishesbeans) {
        float salePrice = 0f;
        float finalyPrice = 0f;
        float restPrice=0f;
        List<Float> priceList=new ArrayList<>();
        for (Dishesbean dishesbean : dishesbeans) {
            salePrice += AppUtil.getFloatFromString(dishesbean.getMaxFavorable().getMoney()).floatValue();
            if ("0".equals(dishesbean.getPay_tag()) || TextUtils.isEmpty(dishesbean.getPay_tag())) {
                AppLog.print(dishesbean.getDishes_name() + "   未支付");
                restPrice+= AppUtil.getFloatFromString(dishesbean.getShowPrice()).floatValue();
            } else {
                AppLog.print(dishesbean.getDishes_name() + "   已支付");
            }
            finalyPrice += AppUtil.getFloatFromString(dishesbean.getMinFavorable().getMoney()).floatValue();
        }
        priceList.add(salePrice);
        priceList.add(finalyPrice);
        priceList.add(restPrice);
        return priceList;
    }
}
