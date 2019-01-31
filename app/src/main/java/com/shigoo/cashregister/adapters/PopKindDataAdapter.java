package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.StringHoldView;
import com.xgsb.datafactory.bean.DishesKind;
import com.xgsb.datafactory.bean.TimeData;

import java.util.List;

public class PopKindDataAdapter extends BaseQuickAdapter<DishesKind, StringHoldView> {
    public PopKindDataAdapter(int layoutResId, @Nullable List<DishesKind> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(StringHoldView helper, DishesKind item) {
        helper.setItem(item.getDrawer());
    }
}
