package com.shigoo.cashregister.adapters.viewHolder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Auther:zhouxue
 * Time :2018/8/7.
 */

public class CouponViewHolder extends BaseViewHolder {
    @BindView(R.id.coupon_name)
    public TextView mCouponTypeName;
    @BindView(R.id.coupon_money)
    public TextView mCouponMoney;
    @BindView(R.id.coupon_time_text)
    public TextView mTimeText;
    @BindView(R.id.coupon_limit)
    public TextView mLimit;
    @BindView(R.id.check_box)
    public CheckBox mCheckBox;

    public CouponViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }
}
