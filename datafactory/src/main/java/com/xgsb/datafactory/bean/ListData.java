package com.xgsb.datafactory.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Name: ListData
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-26 16:55
 */
public class ListData<T> implements Serializable {
    private int current_page;
    private String first_page_url;
    private int from;
    private int last_page;
    private String last_page_url;
    private String next_page_url;
    private String path;
    private int per_page;
    private String prev_page_url;
    private int to;
    private int total;
    private int dishe_num;
    private int count_money;
    private int paid_moeny;
    protected List<T> data;
    private int count_dish_qty;
    private int count_finally_price;
    private int list_count;
    private  int sum_money;

    public int getList_count() {
        return list_count;
    }

    public void setList_count(int list_count) {
        this.list_count = list_count;
    }

    public int getSum_money() {
        return sum_money;
    }

    public void setSum_money(int sum_money) {
        this.sum_money = sum_money;
    }

    public int getCount_dish_qty() {
        return count_dish_qty;
    }

    public void setCount_dish_qty(int count_dish_qty) {
        this.count_dish_qty = count_dish_qty;
    }

    public int getCount_finally_price() {
        return count_finally_price;
    }

    public void setCount_finally_price(int count_finally_price) {
        this.count_finally_price = count_finally_price;
    }

    public int getDishe_num() {
        return dishe_num;
    }

    public void setDishe_num(int dishe_num) {
        this.dishe_num = dishe_num;
    }

    public int getCount_money() {
        return count_money;
    }

    public void setCount_money(int count_money) {
        this.count_money = count_money;
    }

    public int getPaid_moeny() {
        return paid_moeny;
    }

    public void setPaid_moeny(int paid_moeny) {
        this.paid_moeny = paid_moeny;
    }

    public ListData() {
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public String getFirst_page_url() {
        return first_page_url;
    }

    public void setFirst_page_url(String first_page_url) {
        this.first_page_url = first_page_url;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public String getLast_page_url() {
        return last_page_url;
    }

    public void setLast_page_url(String last_page_url) {
        this.last_page_url = last_page_url;
    }

    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public String getPrev_page_url() {
        return prev_page_url;
    }

    public void setPrev_page_url(String prev_page_url) {
        this.prev_page_url = prev_page_url;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
