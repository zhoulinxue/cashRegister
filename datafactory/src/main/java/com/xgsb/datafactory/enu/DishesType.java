package com.xgsb.datafactory.enu;

public enum  DishesType {
    DISHES(1),DISHES_CHILD(2);
    private final int value;

    DishesType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
