package com.xgsb.datafactory.bean;

/**
 * Name: Column
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-26 20:24
 */
public class Column {
    private String name;
    private String prop;

    public Column(String name) {
        this.name = name;
    }

    public Column() {
    }

    public Column(String name, String prop) {
        this.name = name;
        this.prop = prop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }
}
