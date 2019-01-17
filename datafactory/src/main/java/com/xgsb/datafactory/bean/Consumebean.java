package com.xgsb.datafactory.bean;

/**
 * Name: ReChargebean
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-27 19:07
 */
public class Consumebean {
    private int id;
    private String xgsb_system_number;
    private String member_id;
    private String card_number;
    private String mobile;
    private String order_consumption_id;
    private String order_consumption_status;
    private String create_time;
    private int consumption_money;
    private String receivable_money;
    private String no_storage_money;
    private String storage_money;
    private String storage_money_give;
    private String vip_discount;
    private String other_discount;
    private String integral_deductible;
    private String payment_method;
    private String payment_method_name;
    private String selling_id;
    private String person_in_charge_id;
    private String status;

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

    public String getOrder_consumption_id() {
        return order_consumption_id;
    }

    public void setOrder_consumption_id(String order_consumption_id) {
        this.order_consumption_id = order_consumption_id;
    }

    public String getOrder_consumption_status() {
        return order_consumption_status;
    }

    public void setOrder_consumption_status(String order_consumption_status) {
        this.order_consumption_status = order_consumption_status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getConsumption_money() {
        return consumption_money;
    }

    public void setConsumption_money(int consumption_money) {
        this.consumption_money = consumption_money;
    }

    public String getReceivable_money() {
        return receivable_money;
    }

    public void setReceivable_money(String receivable_money) {
        this.receivable_money = receivable_money;
    }

    public String getNo_storage_money() {
        return no_storage_money;
    }

    public void setNo_storage_money(String no_storage_money) {
        this.no_storage_money = no_storage_money;
    }

    public String getStorage_money() {
        return storage_money;
    }

    public void setStorage_money(String storage_money) {
        this.storage_money = storage_money;
    }

    public String getStorage_money_give() {
        return storage_money_give;
    }

    public void setStorage_money_give(String storage_money_give) {
        this.storage_money_give = storage_money_give;
    }

    public String getVip_discount() {
        return vip_discount;
    }

    public void setVip_discount(String vip_discount) {
        this.vip_discount = vip_discount;
    }

    public String getOther_discount() {
        return other_discount;
    }

    public void setOther_discount(String other_discount) {
        this.other_discount = other_discount;
    }

    public String getIntegral_deductible() {
        return integral_deductible;
    }

    public void setIntegral_deductible(String integral_deductible) {
        this.integral_deductible = integral_deductible;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    private String getName(String payment_method) {
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
                name="退款";
                break;
        }
        return name;
    }

    public String getSelling_id() {
        return selling_id;
    }

    public void setSelling_id(String selling_id) {
        this.selling_id = selling_id;
    }

    public String getPerson_in_charge_id() {
        return person_in_charge_id;
    }

    public void setPerson_in_charge_id(String person_in_charge_id) {
        this.person_in_charge_id = person_in_charge_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment_method_name() {
        setPayment_method_name(getName(getPayment_method()));
        return payment_method_name;
    }

    public void setPayment_method_name(String payment_method_name) {
        this.payment_method_name = payment_method_name;
    }
}
