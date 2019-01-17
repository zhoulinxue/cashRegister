package com.xgsb.datafactory.enu;

import android.text.TextUtils;

public enum DiscountType {
    WHOLE_TAG("5"),
    ALL_TAG("1"),
    TIME_TAG("7"),
    MEMBER("6"),
    NULL("-1");

    DiscountType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public static DiscountType getEnum(String actionStr) {
        if (!TextUtils.isEmpty(actionStr)) {
            for (DiscountType action : values()) {
                if (actionStr.equals(action.getType())) {
                    return action;
                }
            }
        } else {
            return null;
        }
        return null;
    }
}
