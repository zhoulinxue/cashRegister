package com.shigoo.cashregister.adapters;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.SaleOutViewHolder;
import com.xgsb.datafactory.bean.SaleOutbean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaleOutListAdapter extends BaseQuickAdapter<SaleOutbean, SaleOutViewHolder> {
    private Map<Integer, Boolean> isSelected = new HashMap<>();
    private int mPosition;

    public SaleOutListAdapter(int layoutResId, @Nullable List<SaleOutbean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(SaleOutViewHolder helper, SaleOutbean item) {
        if (helper.getLayoutPosition() == mPosition) {
            helper.itemView.setBackgroundColor(Color.parseColor("#d2eaff"));
            helper.setSelected(true);
        } else {
            helper.setSelected(false);
            helper.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        helper.setItem(item);
    }

    public void setSelected(int position) {
        mPosition = position;
        notifyDataSetChanged();
    }

    public void setSelected(SaleOutbean current) {
        for (int i = 0; i < getData().size(); i++) {
            if (current.getId() == getData().get(i).getId()) {
                mPosition = i;
                isSelected.put(i, true);
                remove(i);
                addData(i, current);
            } else {
                isSelected.put(i, false);
            }
        }
        notifyDataSetChanged();
    }
}
