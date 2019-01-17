package com.shigoo.cashregister.adapters.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;
import com.xgsb.datafactory.bean.SaleOutbean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaleOutViewHolder extends BaseViewHolder {
    @BindView(R.id.ordersheet_sale_out_name_tv)
    TextView mDishesNameTv;
    @BindView(R.id.ordersheet_sale_out_last_tv)
    TextView mlastTv;
    @BindView(R.id.ordersheet_update_sale_out)
    TextView mUpdateButton;
    @BindView(R.id.ordersheet_cancel_sale_out)
    TextView mCancelButton;


    public SaleOutViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setItem(SaleOutbean item) {
        mDishesNameTv.setText(item.getDishes_name());
        mlastTv.setText("剩余" + item.getNumber() + "份");
        addOnClickListener(R.id.ordersheet_cancel_sale_out);
        addOnClickListener(R.id.ordersheet_update_sale_out);
    }

    public void setSelected(boolean isSelected) {
        mUpdateButton.setVisibility(isSelected ? View.VISIBLE : View.GONE);
        mCancelButton.setVisibility(isSelected ? View.VISIBLE : View.GONE);
    }
}
