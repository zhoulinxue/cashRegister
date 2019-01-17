package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.SetMealHoldView;
import com.xgsb.datafactory.bean.SetMealGroupbean;

import java.util.List;

public class SetMealListAdapter extends BaseQuickAdapter<SetMealGroupbean, SetMealHoldView> {
    private onNumChanage mLisenter;

    public SetMealListAdapter(int layoutResId, @Nullable List<SetMealGroupbean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(SetMealHoldView helper, SetMealGroupbean item) {
        helper.setLisenter(mLisenter);
        helper.setItem(item);
    }

    public void setLisenter(onNumChanage lisenter) {
        this.mLisenter = lisenter;
    }


    public interface onNumChanage {
        public void onNumChanage();
    }
}
