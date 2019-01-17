package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.DishesTypebean;
import com.xgsb.datafactory.bean.Dishesbean;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

public class SaleOutMainListContact {
    public interface presenter {
        void getDishesList(String... params);

        void getDishesTypeList(String... params);

        void saleOut(String... params);
    }

    public interface view extends BaseView {
        void onDishesListResult(List<Dishesbean> dishesbeans);

        void onDishesTypeListResult(List<DishesTypebean> dishesTypebeans);

        void onSaleOutResult(Integer msg);

    }
}
