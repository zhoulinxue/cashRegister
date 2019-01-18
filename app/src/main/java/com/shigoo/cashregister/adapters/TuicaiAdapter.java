package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.RemarkHoldView;
import com.xgsb.datafactory.bean.Remarkbean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TuicaiAdapter extends BaseQuickAdapter<Remarkbean, RemarkHoldView> {
    private Map<Integer, Boolean> isSelected = new HashMap<>();
    private int mCurrentPosition;

    public TuicaiAdapter(int layoutResId, @Nullable List<Remarkbean> data) {
        super(layoutResId, data);
    }

    public void setSelected(int position) {
        mCurrentPosition = position;
        Boolean isSe = isSelected.get(position);
        if (isSe != null && isSe) {
            isSelected.put(position, false);
        } else {
            isSelected.put(position, true);
        }
        notifyDataSetChanged();
    }

    @Override
    protected void convert(RemarkHoldView helper, Remarkbean item) {
        Boolean selectedP = isSelected.get(helper.getLayoutPosition());
        if (selectedP != null && selectedP) {
            helper.setSelected(true);
        } else {
            helper.setSelected(false);
        }
        helper.setDishesFormatName(item.getRemarks_name());
    }

    @Override
    public void setNewData(@Nullable List<Remarkbean> data) {
        super.setNewData(data);
        isSelected.clear();
    }


    public void setSelected(String remarkbeans) {
        for (int i = 0; i < getData().size(); i++) {
            Remarkbean remarkbean = getData().get(i);
            if (!TextUtils.isEmpty(remarkbeans)) {
                if (remarkbean.getRemarks_name().equals(remarkbeans)) {
                    isSelected.put(i, true);
                } else {
                    isSelected.put(i, false);
                }
            } else {
                isSelected.put(i, false);
            }
        }
        notifyDataSetChanged();
    }
    public String getReason() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < getData().size(); i++) {
            Boolean selectedP = isSelected.get(i);
            if (selectedP != null && selectedP) {
                buffer.append(getData().get(i).getRemarks_name());
            }
        }
        return buffer.toString();
    }
}
