package com.shigoo.cashregister.adapters.viewHolder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;
import com.xgsb.datafactory.bean.Dishesbean;
import com.zx.network.Param;

import java.util.Map;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DemolitionDishesListHoldView extends BaseViewHolder {
    @BindView(R.id.demolition_dishes_name_layout)
    TextView mNametv;
    @BindView(R.id.demolition_number_tv)
    TextView mNumbertv;
    @BindView(R.id.demolition_price_tv)
    TextView mPriceTv;
    @BindView(R.id.demolition_checkbox)
    CheckBox mCheckbox;
    @BindView(R.id.demolition_decrice_tv)
    TextView mDecriceTv;
    @BindView(R.id.demolition_add_tv)
    TextView mAddTv;


    public DemolitionDishesListHoldView(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }


    public void setItem(Dishesbean item) {
        mNametv.setText(item.getDishes_name());
        mNumbertv.setText(item.getLocal_num() + "");
        mPriceTv.setText(Param.Keys.RMB + item.getShowPrice());
        addOnClickListener(R.id.demolition_checkbox);
        addOnClickListener(R.id.demolition_decrice_tv);
        addOnClickListener(R.id.demolition_add_tv);
    }

    public void setSelected(int currentPosition) {
        if (currentPosition == getLayoutPosition()) {
            mAddTv.setVisibility(View.VISIBLE);
            mDecriceTv.setVisibility(View.VISIBLE);
        } else {
            mAddTv.setVisibility(View.INVISIBLE);
            mDecriceTv.setVisibility(View.INVISIBLE);
        }
    }

    public void setChecked(Map<Integer, Boolean> isChecked) {
        mCheckbox.setChecked(isChecked.get(getLayoutPosition()));
    }
}
