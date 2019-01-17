package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.FavorableTypeHoldView;
import com.shigoo.cashregister.adapters.viewHolder.PaymentTypeHoldView;
import com.xgsb.datafactory.bean.Favorablebean;
import com.xgsb.datafactory.bean.PayTypebean;
import com.xgsb.datafactory.bean.SettalOrderbean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentTypeListAdapter extends BaseQuickAdapter<PayTypebean, PaymentTypeHoldView> {
    private Map<Integer, Boolean> isSelected = new HashMap<>();
    private int mCurrentPosition;
    private SettalOrderbean mOrderbean;

    public PaymentTypeListAdapter(int layoutResId, @Nullable List<PayTypebean> data) {
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
    protected void convert(PaymentTypeHoldView helper, PayTypebean item) {
        Boolean selectedP = isSelected.get(helper.getLayoutPosition());
        if (selectedP != null && selectedP) {
            helper.setSelected(true);
        } else {
            helper.setSelected(false);
        }
        helper.setDishesTypeName(item.getPay_name(),mOrderbean);
    }
    public void setOrderbean(SettalOrderbean orderbean) {
        this.mOrderbean = orderbean;
        notifyDataSetChanged();
    }
}
