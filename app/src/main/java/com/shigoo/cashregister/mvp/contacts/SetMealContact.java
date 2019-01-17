package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.SetMealGroupbean;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

public class SetMealContact {
    public interface presenter {
        void getSetMealGroup(String... params);

        void getSetMealDishes(String... params);

    }

    public interface view extends BaseView {

        void onSetMealDishesResult(List<Dishesbean> dishesbeans);

        void onGroupDishesList(List<SetMealGroupbean> dishesbeans);
    }
}
