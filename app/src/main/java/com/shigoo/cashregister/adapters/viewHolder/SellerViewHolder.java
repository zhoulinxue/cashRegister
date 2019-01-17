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

public class SellerViewHolder extends BaseViewHolder {
    @BindView(R.id.seller_list_item_tv)
    public TextView mSellerTv;

    public SellerViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }
}
