package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.GiveTypeListHoldView;
import com.xgsb.datafactory.bean.GiveDishesTypebean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GiveDishesTypeListAdapter extends BaseQuickAdapter<GiveDishesTypebean, GiveTypeListHoldView> {
    private Map<Integer, Boolean> isSelected = new HashMap<>();
    private int mCurrentPosition;

    public GiveDishesTypeListAdapter(int layoutResId, @Nullable List<GiveDishesTypebean> data) {
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

    public GiveDishesTypebean getSelectedItem() {
        return getItem(mCurrentPosition);
    }

    @Override
    protected void convert(GiveTypeListHoldView helper, GiveDishesTypebean item) {
        Boolean selectedP = isSelected.get(helper.getLayoutPosition());
        if (selectedP != null && selectedP) {
            helper.setSelected(true);
        } else {
            helper.setSelected(false);
        }
        helper.setAreaName(item.getGiving_name());
    }
}
