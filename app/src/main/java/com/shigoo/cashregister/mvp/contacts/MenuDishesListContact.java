package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.OrderPayStatusbean;
import com.xgsb.datafactory.bean.SettalOrderResultbean;
import com.xgsb.datafactory.bean.SettalOrderbean;
import com.zx.api.api.mvp.BaseView;

public class MenuDishesListContact {
    public interface presenter {
        void getOrderDishes(String... params);

        void OrderDishesAdd(String... params);

        void deleteAllDishes(String... params);

        void settleOrder(SettalOrderbean settleOrder);

        void updateBillbeanNum(String... params);

        void getPayList(String... params);
    }

    public interface view extends BaseView {
        void onOrderDishesListResult(SettalOrderResultbean resultbean);

        void onAddResult(String msg);

        void onDeleteResult(String msg);

        void onSettleResult(String msg);

        void onUpdateNumResult(String msg);

        void onPayResult(OrderPayStatusbean paybeans);
    }
}
