package com.shigoo.cashregister.adapters.viewHolder;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;
import com.xgsb.datafactory.bean.Dishesbean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeftSetmealChildDishesHoldView extends BaseViewHolder {

    @BindView(R.id.ordersheet_left_dishes_child_name_tv)
    TextView mNametv;
    @BindView(R.id.ordersheet_dishes_left_child_num_tv)
    TextView mNumTv;

    public LeftSetmealChildDishesHoldView(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }


    public void setItem(Dishesbean item) {
        mNametv.setText(TextUtils.isEmpty(item.getDishes_name()) ? item.getDish_name() : item.getDishes_name());
        if (item.getNum() != 0) {
            mNumTv.setVisibility(View.VISIBLE);
            mNumTv.setText("X" + item.getNum());
        } else {
            mNumTv.setVisibility(View.GONE);
        }
    }
}
