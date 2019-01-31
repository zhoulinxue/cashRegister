package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.StringHoldView;
import com.xgsb.datafactory.bean.DishesKind;

import java.util.List;

public class PopDisehesTagAdapter extends BaseQuickAdapter<String, StringHoldView> {
    public PopDisehesTagAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(StringHoldView helper, String item) {
        helper.setItem(item);
    }
}
