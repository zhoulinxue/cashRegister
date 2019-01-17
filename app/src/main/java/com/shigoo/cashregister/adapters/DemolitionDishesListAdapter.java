package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.DemolitionDishesListHoldView;
import com.shigoo.cashregister.adapters.viewHolder.DishesFormatHoldView;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.Specifications;
import com.zx.network.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemolitionDishesListAdapter extends BaseQuickAdapter<Dishesbean, DemolitionDishesListHoldView> {
    private int mCurrentPosition;
    private Map<Integer, Boolean> isChecked = new HashMap<>();

    public DemolitionDishesListAdapter(int layoutResId, @Nullable List<Dishesbean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(DemolitionDishesListHoldView helper, Dishesbean item) {
        helper.setItem(item);
        helper.setSelected(mCurrentPosition);
        helper.setChecked(isChecked);
    }

    public void setSelected(int position) {
        mCurrentPosition = position;
        notifyDataSetChanged();
    }

    @Override
    public void setNewData(@Nullable List<Dishesbean> data) {
        super.setNewData(data);
        for (int i = 0; i < data.size(); i++) {
            isChecked.put(i, false);
        }
    }
}
