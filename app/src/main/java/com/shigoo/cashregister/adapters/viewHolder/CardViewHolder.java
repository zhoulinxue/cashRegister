package com.shigoo.cashregister.adapters.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Auther:zhouxue
 * Time :2018/8/7.
 */

public class CardViewHolder extends BaseViewHolder {
    @BindView(R.id.position_text)
    public TextView mPosition;
    @BindView(R.id.card_num)
    public TextView mCardNum;
    @BindView(R.id.card_status)
    public TextView mStatus;
    @BindView(R.id.delete_card)
    public TextView mDelete;


    public CardViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
        addOnClickListener(R.id.delete_card);
    }
}
