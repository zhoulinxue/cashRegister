package com.xgsb.datafactory.bean;

/**
 * Name: Cardbean
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-30 13:38
 */
public class Cardbean {
    private int id;
    private String entity_card;
    private String create_date;
    private String end_date;
    private String status;
    private String coding_card;
    private String xgsb_system_number;

    public Cardbean(int id, String entity_card, String create_date, String end_date, String status) {
        this.id = id;
        this.entity_card = entity_card;
        this.create_date = create_date;
        this.end_date = end_date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntity_card() {
        return entity_card;
    }

    public void setEntity_card(String entity_card) {
        this.entity_card = entity_card;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getXgsb_system_number() {
        return xgsb_system_number;
    }

    public void setXgsb_system_number(String xgsb_system_number) {
        this.xgsb_system_number = xgsb_system_number;
    }

    public String getCoding_card() {
        return coding_card;
    }

    public void setCoding_card(String coding_card) {
        this.coding_card = coding_card;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Cardbean) {
            return ((Cardbean) obj).getCoding_card().equals(getCoding_card());
        }
        return super.equals(obj);
    }
}
