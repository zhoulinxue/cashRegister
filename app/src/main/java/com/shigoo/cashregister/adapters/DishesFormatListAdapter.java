package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.DishesFormatHoldView;
import com.xgsb.datafactory.bean.Specifications;
import com.zx.network.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DishesFormatListAdapter extends BaseQuickAdapter<Specifications, DishesFormatHoldView> {
    private Map<Integer, Boolean> isSelected = new HashMap<>();
    private int mCurrentPosition;

    public DishesFormatListAdapter(int layoutResId, @Nullable List<Specifications> data) {
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
    protected void convert(DishesFormatHoldView helper, Specifications item) {
        Boolean selectedP = isSelected.get(helper.getLayoutPosition());
        if (selectedP != null && selectedP) {
            helper.setSelected(true);
        } else {
            helper.setSelected(false);
        }
      helper.setItem(item);
    }
}
