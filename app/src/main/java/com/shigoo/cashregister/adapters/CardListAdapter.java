package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.CardViewHolder;
import com.xgsb.datafactory.bean.Cardbean;

import java.util.List;

/**
 * Auther:zhouxue
 * Time :2018/8/24.
 */

public class CardListAdapter extends BaseQuickAdapter<Cardbean, CardViewHolder> {


    public CardListAdapter(int layoutResId, @Nullable List<Cardbean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(CardViewHolder helper, Cardbean item) {
        helper.mPosition.setText(helper.getLayoutPosition() + "");
        helper.mCardNum.setText(item.getCoding_card());
        helper.mStatus.setText("1".equals(item.getStatus())?"未制卡":"已制卡");
    }
}
