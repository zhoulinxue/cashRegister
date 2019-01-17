package com.xgsb.datafactory.bean;

/**
 * Name: ReChargeListData
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-28 19:12
 */
public class ReChargeListData extends ListData<ReChargebean> {
    private float money;
    private float money_give;
    private float money_count;

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public float getMoney_give() {
        return money_give;
    }

    public void setMoney_give(float money_give) {
        this.money_give = money_give;
    }

    public float getMoney_count() {
        return money_count;
    }

    public void setMoney_count(float money_count) {
        this.money_count = money_count;
    }
}
