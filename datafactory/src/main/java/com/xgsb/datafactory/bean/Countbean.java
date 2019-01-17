package com.xgsb.datafactory.bean;

/**
 * Name: Countbean
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-27 15:50
 */
public class Countbean {
    private int this_count_member; //今日新增会员
    private int count_member; //会员总数

    public int getThis_count_member() {
        return this_count_member;
    }

    public void setThis_count_member(int this_count_member) {
        this.this_count_member = this_count_member;
    }

    public int getCount_member() {
        return count_member;
    }

    public void setCount_member(int count_member) {
        this.count_member = count_member;
    }
}
