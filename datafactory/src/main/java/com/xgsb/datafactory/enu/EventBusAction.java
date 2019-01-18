package com.xgsb.datafactory.enu;

import android.text.TextUtils;

/**
 * Name: EventBusAction
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-06 18:40
 */
public enum EventBusAction {
    MAIN("android.intent.action.MAIN"),
    MEMBER_MSG("member_msg"),
    SELLER_INFO("seller_info"),
    LOGIN("login"),
    LOGIN_SUC("login_suc"),
    NULL("nothing"),
    TABLE("table"),
    SALE("sale_performance"),
    ORDER("order_performance"),
    ADD_DISHES("dishes"),
    DISHES_UPDATE_TOTAL_NUM("dishes_update"),
    DISHES_DETAIL("dishes_detail"),
    DISHES_LIST("dishes_list"),
    DISHES_REMARK("dishes_remark"),
    ORDER_REMARK("order_remark"),
    DISHES_DELETE("dishes_delete"),
    CHILD("dishes_child"),
    ADD_CHILD("add_child"),
    DELETE_CHILD("delete_child"),
    CHILD_DETAIL("child_detail"),
    DISHES_UPDATE_FORMAT("dishes_update_format"),
    DISHES_UPDATE_REMAR("dishes_update_remark"),
    CHILD_UPDATE("CHILD_UPDATE"),
    SET_NUMBER("set_number"),
    BACK_TO_MAIN("back_to_main"),
    ADD_SUC("add_suc"),
    UPDATE_SALE_OUT("update_sale_out"),
    COPY("copy_dishes"),
    COPY_SUC("copy_dishes_suc"),
    COPY_CHILD("copy_child_dishes"),
    GIVE_SUC("give_suc"),
    CONSUM_DETAIL("consume_detail"),
    SETTAL_ORDER("settal_order"),
    DEMOLITION("demolition"),
    DEMOLITION_ADD_DISHES("demolition_add"),
    CANCEL_DEMOLITION("cancel_demolition"),
    CLEAN_DEMOLITION("clean_demolition"),
    DISCOUNT("discount_type"),
    PAY_SUC("pay_suc"),
    TUI_CAI("tui_cai"),
    GAI_JIA("gai_jia"),
    DA_ZHE("da_zhe"),
    CHE_DAN("che_dan"),
    MEMBER_DETAIL("member_detail"),
    CARD_FRAGMENT("card_fragmnet"),
    MEMBER_DETAIL_BACK("member_detail_back"),
    TABLE_BACK("table_back"),
    FAN_JIE_ZHANG("fan_jie_zhang_suc"),
    BILL_REFRESH("bill_refresh");


    EventBusAction(String member_msg) {
        this.action = member_msg;
    }

    public String getAction() {
        return action;
    }

    private String action;

    public static EventBusAction getEnum(String actionStr) {
        if (!TextUtils.isEmpty(actionStr)) {
            for (EventBusAction action : values()) {
                if (actionStr.equals(action.getAction())) {
                    return action;
                }
            }
        } else {
            return NULL;
        }
        return NULL;
    }

}
