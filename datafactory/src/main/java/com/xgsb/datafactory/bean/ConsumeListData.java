package com.xgsb.datafactory.bean;

/**
 * Name: ConsumeListData
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-28 19:13
 */
public class ConsumeListData extends ListData<Consumebean> {
    private float receivable_money_count = 0;//应收金额
    private float no_storage_money_count = 0;//非储值实收
    private float storage_money_count = 0;//储值实收
    private float storage_money_give_count = 0;//储值赠送

    public float getReceivable_money_count() {
        return receivable_money_count;
    }

    public void setReceivable_money_count(float receivable_money_count) {
        this.receivable_money_count = receivable_money_count;
    }

    public float getNo_storage_money_count() {
        return no_storage_money_count;
    }

    public void setNo_storage_money_count(float no_storage_money_count) {
        this.no_storage_money_count = no_storage_money_count;
    }

    public float getStorage_money_count() {
        return storage_money_count;
    }

    public void setStorage_money_count(float storage_money_count) {
        this.storage_money_count = storage_money_count;
    }

    public float getStorage_money_give_count() {
        return storage_money_give_count;
    }

    public void setStorage_money_give_count(float storage_money_give_count) {
        this.storage_money_give_count = storage_money_give_count;
    }
}
