package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.OrderpayHoldView;
import com.shigoo.cashregister.adapters.viewHolder.PayHoldView;
import com.xgsb.datafactory.bean.Paybean;
import com.xgsb.datafactory.bean.Remarkbean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayListAdapter extends BaseQuickAdapter<Paybean, PayHoldView> {
    private Map<Integer, Boolean> isSelected = new HashMap<>();

    public PayListAdapter(int layoutResId, @Nullable List<Paybean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(PayHoldView helper, Paybean item) {
        Boolean selectedP = isSelected.get(helper.getLayoutPosition());
        if (selectedP != null && selectedP) {
            helper.setSelected(true);
        } else {
            helper.setSelected(false);
        }
        helper.setItem(item);
    }

    public void setSelected(List<Remarkbean> remarkbeans) {
        for (int i = 0; i < getData().size(); i++) {
            Paybean remarkbean = getData().get(i);
            if (remarkbeans != null) {
                if (remarkbeans.contains(remarkbean)) {
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

    public void setSelected(int position) {
        Boolean selectedP = isSelected.get(position);
        if (selectedP != null) {
            isSelected.put(position, !selectedP);
        } else {
            isSelected.put(position, true);
        }
        notifyDataSetChanged();
    }
}
