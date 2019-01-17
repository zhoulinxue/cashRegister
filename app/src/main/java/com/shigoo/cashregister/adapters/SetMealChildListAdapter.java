package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.SetMealChildViewHolder;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.SetMealGroupbean;

import java.util.List;

public class SetMealChildListAdapter extends BaseQuickAdapter<Dishesbean, SetMealChildViewHolder> {
    private SetMealGroupbean setMealGroupbean;
    private SetMealListAdapter.onNumChanage mLisenter;

    public SetMealChildListAdapter(int layoutResId, @Nullable List<Dishesbean> data) {
        super(layoutResId, data);

    }


    @Override
    protected void convert(SetMealChildViewHolder helper, Dishesbean item) {
        helper.setSetMealGroupbean(setMealGroupbean);
        helper.setMealChildListAdapter(this);
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
}
