package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.StringHoldView;
import com.xgsb.datafactory.bean.TableArea;
import com.xgsb.datafactory.bean.TimeData;

import java.util.List;

public class PopTableAreaAdapter extends BaseQuickAdapter<TableArea, StringHoldView> {
    public PopTableAreaAdapter(int layoutResId, @Nullable List<TableArea> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(StringHoldView helper, TableArea item) {
        helper.setItem(item.getRegion_name());
    }
}
