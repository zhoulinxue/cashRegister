package com.shigoo.cashregister.adapters.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;
import com.xgsb.datafactory.bean.Paybean;
import com.zx.network.Param;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayHoldView extends BaseViewHolder {
    @BindView(R.id.pay_list_item_name_tv)
    TextView mNameTv;


    public PayHoldView(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setItem(Paybean item) {
        mNameTv.setText("第" + item.getPay_num() + "次已支付");
    }
}
