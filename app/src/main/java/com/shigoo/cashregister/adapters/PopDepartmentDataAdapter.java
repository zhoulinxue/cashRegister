package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.StringHoldView;
import com.xgsb.datafactory.bean.Departmentbean;
import com.xgsb.datafactory.bean.DishesKind;

import java.util.List;

public class PopDepartmentDataAdapter extends BaseQuickAdapter<Departmentbean, StringHoldView> {
    public PopDepartmentDataAdapter(int layoutResId, @Nullable List<Departmentbean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(StringHoldView helper, Departmentbean item) {
        helper.setItem(item.getName());
    }
}
