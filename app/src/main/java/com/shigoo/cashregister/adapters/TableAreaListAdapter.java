package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.TableAreaHoldView;
import com.xgsb.datafactory.bean.TableArea;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableAreaListAdapter extends BaseQuickAdapter<TableArea, TableAreaHoldView> {
    private Map<Integer, Boolean> isSelected = new HashMap<>();
    private int mCurrentPosition;

    public TableAreaListAdapter(int layoutResId, @Nullable List<TableArea> data) {
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
    protected void convert(TableAreaHoldView helper, TableArea item) {
        Boolean selectedP = isSelected.get(helper.getLayoutPosition());
        if (selectedP != null && selectedP) {
            helper.setSelected(true);
        } else {
            helper.setSelected(false);
        }
        helper.setAreaName(item.getRegion_name());
    }
}
