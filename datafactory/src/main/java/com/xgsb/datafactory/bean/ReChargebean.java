package com.xgsb.datafactory.bean;

/**
 * Name: ReChargebean
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-27 19:07
 */
public class ReChargebean {
    private int id;
    private String xgsb_system_number;
    private String member_id;
    private String card_number;
    private String mobile;
    private String order_money_id;
    private String order_status;
    private String order_status_name;
    private String create_time;
    private int money;
    private String money_give;
    private String integral_give;
    private String payment_method;
    private String selling_id;
    private String status;
    private String payment_method_name;
    private String stored_value_money;

    public String getStored_value_money() {
        return stored_value_money;
    }

    public void setStored_value_money(String stored_value_money) {
        this.stored_value_money = stored_value_money;
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

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
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


    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getSelling_id() {
        return selling_id;
    }

    public void setSelling_id(String selling_id) {
        this.selling_id = selling_id;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrder_money_id() {
        return order_money_id;
    }

    public void setOrder_money_id(String order_money_id) {
        this.order_money_id = order_money_id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    private String getName(String order_status) {
        String name = "";
        switch (order_status) {
            case "1":
                name = "储值";
                break;
            case "2":
                name = "注销";
                break;
            case "3":
                name = "退款";
                break;
            case "4":
                name = "撤销";
                break;
        }
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getMoney_give() {
        return money_give;
    }

    public void setMoney_give(String money_give) {
        this.money_give = money_give;
    }

    public String getIntegral_give() {
        return integral_give;
    }

    public void setIntegral_give(String integral_give) {
        this.integral_give = integral_give;
    }

    public String getOrder_status_name() {
        setOrder_status_name(getName(getOrder_status()));
        return order_status_name;
    }

    public void setOrder_status_name(String order_status_name) {
        this.order_status_name = order_status_name;
    }

    public String getPayment_method_name() {
        setPayment_method_name(getPayName(getPayment_method()));
        return payment_method_name;
    }

    public void setPayment_method_name(String payment_method_name) {
        this.payment_method_name = payment_method_name;
    }

    private String getPayName(String payment_method) {
        String name = "";
        switch (payment_method) {
            case "1":
                name = "微信支付";
                break;
            case "2":
                name = "支付宝支付";
                break;
            case "3":
                name = "银行卡支付";
                break;
            case "4":
                name = "现金支付";
                break;
            case "5":
                name="已撤销";
                break;
        }
        return name;
    }
}
