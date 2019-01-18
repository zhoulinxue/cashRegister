package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.Chedanbean;
import com.xgsb.datafactory.bean.Remarkbean;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

public class TableDialogContact {
    public interface presenter {
        void cancelOrder(Chedanbean chedanbean);

        void getMultReasonList(String... params);

        void chargePrice(String... params);

        void discountDishes(String... params);

        void returnDishes(String... params);
    }

    public interface view extends BaseView {

        void onCancelOrderResult(String listData);

        void onReasonResult(List<Remarkbean> reasonList);

        void onChargePriceResult(String msg);

        void onDiscountResult(String msg);

        void onReturnDishesResult(String msg);
    }
}
