package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.DishesTypeHoldView;
import com.xgsb.datafactory.bean.DishesTypebean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DishesTypeListAdapter extends BaseQuickAdapter<DishesTypebean, DishesTypeHoldView> {
    private Map<Integer, Boolean> isSelected = new HashMap<>();
    private int mCurrentPosition;

    public DishesTypeListAdapter(int layoutResId, @Nullable List<DishesTypebean> data) {
        super(layoutResId, data);
    }

    public void setSelected(int position) {
        mCurrentPosition = position;
        for (int i = 0; i < getData().size(); i++) {
            if (position == i) {
                isSelected.put(i, true);
            } else {
                isSelected.put(i, false);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    protected void convert(DishesTypeHoldView helper, DishesTypebean item) {
        Boolean selectedP = isSelected.get(helper.getLayoutPosition());
        if (selectedP != null && selectedP) {
            helper.setSelected(true);
        } else {
            helper.setSelected(false);
        }
        helper.setDishesTypeName(item.getCategory_name());
    }
}
