package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.SaleOutMainHolderView;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.SaleOutbean;

import java.util.List;

public class SaleOutMainAdapter extends BaseQuickAdapter<Dishesbean, SaleOutMainHolderView> {

    public SaleOutMainAdapter(int layoutResId, @Nullable List<Dishesbean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(SaleOutMainHolderView helper, Dishesbean item) {
        helper.setItem(item);
    }

    public void update(SaleOutbean saleOutbean) {
        for (Dishesbean dishesbean : getData()) {
            if ("1".equals(dishesbean.getDish_tag())) {
                if (saleOutbean.getDishes_id().equals(dishesbean.getCombo_id())) {
                    dishesbean.setCombo_guqing(saleOutbean.getNumber());
                    notifyDataSetChanged();
                    return;
                }
            } else {
                if (saleOutbean.getDishes_id().equals(dishesbean.getCurrentSp().getId())) {
                    dishesbean.getCurrentSp().setGuqing(saleOutbean.getNumber());
                    notifyDataSetChanged();
                    return;
                }
            }
        }
    }
}
