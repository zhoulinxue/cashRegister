package com.shigoo.cashregister.adapters.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Auther:zhouxue
 * Time :2018/8/7.
 */

public class OrderSheetMainViewHolder extends BaseViewHolder {
    @BindView(R.id.ordersheet_main_itme_name)
   public TextView mNameTv;
    @BindView(R.id.ordersheet_main_itme_logo)
   public ImageView mLogo;

    public OrderSheetMainViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }
}
