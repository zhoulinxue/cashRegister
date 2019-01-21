package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.RemarkHoldView;
import com.xgsb.datafactory.bean.Remarkbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChedanAdapter extends BaseQuickAdapter<Remarkbean, RemarkHoldView> {
    private Map<Integer, Boolean> isSelected = new HashMap<>();

    public ChedanAdapter(int layoutResId, @Nullable List<Remarkbean> data) {
        super(layoutResId, data);
    }

    public void setSelected(int position) {
        Boolean isSe = isSelected.get(position);
        if (isSe != null ) {
            isSelected.put(position, !isSe);
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

    public List<String> getReason() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < getData().size(); i++) {
            Boolean selectedP = isSelected.get(i);
            if (selectedP != null && selectedP) {
                stringList.add(getData().get(i).getRemarks_name());
            }
        }
        return stringList;
    }
}
