package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.OrderpayHoldView;
import com.shigoo.cashregister.adapters.viewHolder.PayHoldView;
import com.xgsb.datafactory.bean.Paybean;

import java.util.List;

public class PayListAdapter extends BaseQuickAdapter<Paybean, PayHoldView> {
    public PayListAdapter(int layoutResId, @Nullable List<Paybean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(PayHoldView helper, Paybean item) {
        helper.setItem(item);
    }
}
