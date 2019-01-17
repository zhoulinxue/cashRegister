package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.TableBottomHoldView;
import com.xgsb.datafactory.bean.TableStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableBottomListAdapter extends BaseQuickAdapter<TableStatus, TableBottomHoldView> {
    private Map<Integer, Boolean> isSelected = new HashMap<>();
    private int mCurrentPosition;

    public TableBottomListAdapter(int layoutResId, @Nullable List<TableStatus> data) {
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
    protected void convert(TableBottomHoldView helper, TableStatus item) {
        Boolean selectedP = isSelected.get(helper.getLayoutPosition());
        if (selectedP != null && selectedP) {
            helper.setSelected(true);
        } else {
            helper.setSelected(false);
        }
        helper.setItem(item);
    }

    public boolean isSelected(int position) {
        return isSelected.get(position) == null ? false : isSelected.get(position);
    }
}
