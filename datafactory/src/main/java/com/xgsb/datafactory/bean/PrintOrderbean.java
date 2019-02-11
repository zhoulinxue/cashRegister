package com.xgsb.datafactory.bean;

import java.util.List;

public class PrintOrderbean extends BasePrintbean {
    private String billCode;
    private String tableNumber;
    private String date;
    private String payTime;
    private String orderName;
    private String sellerName;
    private List<Dishesbean> dishes;
    private String salePrice;
    private String finalyPrice;
    private String alreadyMoney;

    public String getAlreadyMoney() {
        return alreadyMoney;
    }

    public void setAlreadyMoney(String alreadyMoney) {
        this.alreadyMoney = alreadyMoney;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getFinalyPrice() {
        return finalyPrice;
    }

    public void setFinalyPrice(String finalyPrice) {
        this.finalyPrice = finalyPrice;
    }

    public List<Dishesbean> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dishesbean> dishes) {
        this.dishes = dishes;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }
}
