package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.BillHoldView;
import com.xgsb.datafactory.JSONManager;
import com.xgsb.datafactory.bean.Billbean;
import com.xgsb.datafactory.bean.Table;
import com.zx.api.api.utils.AppLog;

import java.util.List;

public class TableBillListAdapter extends BaseQuickAdapter<Billbean, BillHoldView> {
    private Table mTable;

    public TableBillListAdapter(int layoutResId, @Nullable List<Billbean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BillHoldView helper, Billbean item) {
        if (mTable != null) {
            helper.setLower(mTable);
        }
        helper.setItem(item);
    }

    public void setTable(Table table) {
        this.mTable = table;
        notifyDataSetChanged();
    }
}
