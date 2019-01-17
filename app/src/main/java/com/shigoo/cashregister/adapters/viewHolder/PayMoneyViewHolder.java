package com.shigoo.cashregister.adapters.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;
import com.xgsb.datafactory.bean.AddPayment;
import com.zx.network.Param;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayMoneyViewHolder extends BaseViewHolder {
    @BindView(R.id.payment_name_tv)
    TextView mNameTv;
    @BindView(R.id.payment_money_tv)
    TextView mMoneyTv;
    @BindView(R.id.payment_return_tv)
    TextView mReturnTv;


    public PayMoneyViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setItem(AddPayment item) {
        mNameTv.setText(item.getPay_name());
        mMoneyTv.setText(Param.Keys.RMB + item.getPay_amount());
        addOnClickListener(R.id.payment_return_tv);
    }
}
