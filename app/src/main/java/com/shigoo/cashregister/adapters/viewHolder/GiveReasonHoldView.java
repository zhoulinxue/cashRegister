package com.shigoo.cashregister.adapters.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GiveReasonHoldView extends BaseViewHolder {
    @BindView(R.id.ordersheet_dishes_type_item_tv)
    TextView mNameTv;
    @BindColor(R.color.ordersheet_colorAccent)
    int mSelectedColor;
    @BindColor(R.color.ordersheet_area_color)
    int mNomalColor;

    public GiveReasonHoldView(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setDishesFormatName(String region_name) {
        mNameTv.setText(region_name);
    }

    public void setItem(String item) {
        mNameTv.setText(item);
    }

    public void setCurrentPosition(Boolean currentPosition) {
        if (currentPosition != null && currentPosition) {
            mNameTv.setTextColor(mSelectedColor);
            mNameTv.setBackgroundResource(R.drawable.ordersheet_table_area_item_selected_bg);
        } else {
            mNameTv.setTextColor(mNomalColor);
            mNameTv.setBackgroundResource(R.drawable.ordersheet_table_area_item_bg);
        }
    }
}
