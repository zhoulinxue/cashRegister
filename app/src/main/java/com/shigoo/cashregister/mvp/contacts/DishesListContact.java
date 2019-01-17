package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.DishesTypebean;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.Remarkbean;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

public class DishesListContact {
    public interface presenter {
        void getDishesList(String... params);

        void getDishesTypeList(String... params);

        void getRemarkList(String... params);

        void getSetMealDishes(String... params);
    }

    public interface view extends BaseView {
        void onDishesListResult(List<Dishesbean> dishesbeans);

        void onDishesTypeListResult(List<DishesTypebean> dishesTypebeans);

        void onRemarkListResult(List<Remarkbean> remarkbeans);

        void onSetMealDishesResult(List<Dishesbean> dishesbeans);

    }
}
