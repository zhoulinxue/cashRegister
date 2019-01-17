package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.OrderSheetMainViewHolder;
import com.xgsb.datafactory.bean.OrderSheetMainMenu;

import java.util.List;

/**
 * Auther:zhouxue
 * Time :2018/8/24.
 */

public class OrderSheetMainMenuListAdapter extends BaseQuickAdapter<OrderSheetMainMenu, OrderSheetMainViewHolder> {


    public OrderSheetMainMenuListAdapter(int layoutResId, @Nullable List<OrderSheetMainMenu> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(OrderSheetMainViewHolder helper, OrderSheetMainMenu item) {
        helper.mLogo.setImageResource(item.getLogo());
        helper.mNameTv.setText(item.getName());
    }
}
