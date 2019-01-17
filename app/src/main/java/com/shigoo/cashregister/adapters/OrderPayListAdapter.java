package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.OrderpayHoldView;
import com.xgsb.datafactory.bean.Paybean;

import java.util.List;

public class OrderPayListAdapter extends BaseQuickAdapter<Paybean, OrderpayHoldView> {
    public OrderPayListAdapter(int layoutResId, @Nullable List<Paybean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(OrderpayHoldView helper, Paybean item) {
        helper.setItem(item);
    }
}
