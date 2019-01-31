package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.PayHoldView;
import com.shigoo.cashregister.adapters.viewHolder.StringHoldView;
import com.xgsb.datafactory.bean.Paybean;
import com.xgsb.datafactory.bean.Remarkbean;
import com.xgsb.datafactory.bean.TimeData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeDataPopAdapter extends BaseQuickAdapter<TimeData, StringHoldView> {
    public TimeDataPopAdapter(int layoutResId, @Nullable List<TimeData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(StringHoldView helper, TimeData item) {
        helper.setItem(item.getName());
    }
}
