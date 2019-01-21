package com.shigoo.cashregister.adapters.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;
import com.xgsb.datafactory.bean.Paybean;
import com.zx.network.Param;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PayHoldView extends BaseViewHolder {
    @BindView(R.id.pay_list_item_name_tv)
    TextView mNameTv;
    @BindColor(R.color.ordersheet_colorAccent)
    int mSelectedColor;
    @BindColor(R.color.ordersheet_area_color)
    int mNomalColor;

    public PayHoldView(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setItem(Paybean item) {
        mNameTv.setText("第" + item.getPay_num() + "次已支付");
    }

    public void setSelected(boolean isSelected) {
        if (isSelected) {
            mNameTv.setTextColor(mSelectedColor);
            mNameTv.setBackgroundResource(R.drawable.ordersheet_table_area_item_selected_bg);
        } else {
            mNameTv.setTextColor(mNomalColor);
            mNameTv.setBackgroundResource(R.drawable.ordersheet_table_area_item_bg);
        }
    }
}
