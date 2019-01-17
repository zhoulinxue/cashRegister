package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.GiveDishesViewHolder;
import com.xgsb.datafactory.bean.GiveDishesbean;
import com.xgsb.datafactory.bean.SetMealGroupbean;

import java.util.ArrayList;
import java.util.List;

public class GiveDishesListAdapter extends BaseQuickAdapter<GiveDishesbean, GiveDishesViewHolder> {
    private SetMealGroupbean setMealGroupbean;
    private SetMealListAdapter.onNumChanage mLisenter;

    public GiveDishesListAdapter(int layoutResId, @Nullable List<GiveDishesbean> data) {
        super(layoutResId, data);

    }


    @Override
    protected void convert(GiveDishesViewHolder helper, GiveDishesbean item) {
        helper.setSetMealGroupbean(setMealGroupbean);
        helper.setGiveDishesListAdapter(this);
        helper.setItem(item);
        if (mLisenter != null) {
            mLisenter.onNumChanage();
        }
    }

    public void setSetMealGroupbean(SetMealGroupbean setMealGroupbean) {
        this.setMealGroupbean = setMealGroupbean;
    }

    public void setLisenter(SetMealListAdapter.onNumChanage lisenter) {
        this.mLisenter = lisenter;
    }

    public ArrayList<GiveDishesbean> getSelected() {
        ArrayList<GiveDishesbean> selected = new ArrayList<>();
        for (GiveDishesbean dishesbean : getData()) {
            if (dishesbean.getNum() != 0) {
                selected.add(dishesbean);
            }
        }
        return selected;
    }
}
