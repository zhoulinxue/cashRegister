package com.shigoo.cashregister.adapters.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;
import com.xgsb.datafactory.bean.Paybean;
import com.zx.network.Param;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderpayHoldView extends BaseViewHolder {
    @BindView(R.id.pay_list_item_time_tv)
    TextView mTimeTv;
    @BindView(R.id.pay_list_item_name_tv)
    TextView mNameTv;
    @BindView(R.id.pay_list_item_money_tv)
    TextView mMoneyTv;

    public OrderpayHoldView(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setItem(Paybean item) {
        mTimeTv.setText(item.getCreate_date());
        mMoneyTv.setText(Param.Keys.RMB + item.getPay_amount());
        switch (item.getPay_tag()) {
            case 0:
                mNameTv.setText("第" + item.getPay_num() + "次未支付");
                break;
            case 1:
                mNameTv.setText("第" + item.getPay_num() + "次已支付");
                break;
            case 2:
                mNameTv.setText("第" + item.getPay_num() + "次已撤单");
                break;
        }
    }
}
