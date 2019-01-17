package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.LeftSetmealChildDishesHoldView;
import com.xgsb.datafactory.bean.Dishesbean;

import java.util.List;

public class LeftSetMealChildAdapter extends BaseQuickAdapter<Dishesbean, LeftSetmealChildDishesHoldView> {
    public LeftSetMealChildAdapter(int layoutResId, @Nullable List<Dishesbean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(LeftSetmealChildDishesHoldView helper, Dishesbean item) {
        helper.setItem(item);
    }
}
