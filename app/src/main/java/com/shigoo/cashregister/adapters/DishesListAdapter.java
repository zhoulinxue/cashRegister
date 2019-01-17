package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.DishesListHoldView;
import com.xgsb.datafactory.bean.Dishesbean;

import java.util.List;

/**
 * Name: DishesListAdapter
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-14 21:58
 */
public class DishesListAdapter extends BaseQuickAdapter<Dishesbean, DishesListHoldView> {
    public DishesListAdapter(int layoutResId, @Nullable List<Dishesbean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(DishesListHoldView helper, Dishesbean item) {
        helper.setItem(item);
    }

}
