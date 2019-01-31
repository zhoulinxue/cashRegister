package com.shigoo.cashregister.adapters.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;
import com.xgsb.datafactory.bean.Paybean;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StringHoldView extends BaseViewHolder {
    @BindView(R.id.name_tv)
    TextView mNameTv;
    @BindColor(R.color.ordersheet_colorAccent)
    int mSelectedColor;
    @BindColor(R.color.ordersheet_area_color)
    int mNomalColor;

    public StringHoldView(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setItem(String item) {
        mNameTv.setText(item);
    }
}
