package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.DishesTypeHoldView;
import com.shigoo.cashregister.adapters.viewHolder.FavorableTypeHoldView;
import com.xgsb.datafactory.bean.Favorablebean;
import com.xgsb.datafactory.bean.SettalOrderbean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavorableTypeListAdapter extends BaseQuickAdapter<Favorablebean, FavorableTypeHoldView> {
    private Map<Integer, Boolean> isSelected = new HashMap<>();
    private int mCurrentPosition;
    private SettalOrderbean mOrderbean;

    public FavorableTypeListAdapter(int layoutResId, @Nullable List<Favorablebean> data) {
        super(layoutResId, data);
    }

    public boolean onClickPosition(int position) {
        if (isSelected.get(position) == null || !isSelected.get(position)) {
            setSelected(position);
        } else {
            setSelected(-1);
        }
        return isSelected.get(position);
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
    protected void convert(FavorableTypeHoldView helper, Favorablebean item) {
        Boolean selectedP = isSelected.get(helper.getLayoutPosition());
        if (selectedP != null && selectedP) {
            helper.setSelected(true);
        } else {
            helper.setSelected(false);
        }
        helper.setDishesTypeName(item.getFavorable_name(), mOrderbean);
    }

    public void setOrderbean(SettalOrderbean orderbean) {
        this.mOrderbean = orderbean;
        notifyDataSetChanged();
    }
}
