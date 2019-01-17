package com.shigoo.cashregister.adapters.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;
import com.xgsb.datafactory.bean.AddFavorablebean;
import com.xgsb.datafactory.bean.Favorablebean;
import com.zx.network.Param;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayFavorableHoldView extends BaseViewHolder {
    @BindView(R.id.pay_favorable_name_tv)
    TextView mNametv;
    @BindView(R.id.pay_favorable_money_tv)
    TextView mMoneyTv;

    public PayFavorableHoldView(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setItem(AddFavorablebean item) {
        mMoneyTv.setText("-  "+Param.Keys.RMB + item.getMoney());
        mNametv.setText(item.getName());
    }
}
