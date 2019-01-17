package com.shigoo.cashregister.adapters.viewHolder;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;
import com.xgsb.datafactory.bean.Specifications;
import com.zx.api.api.utils.AppUtil;
import com.zx.network.Param;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DishesFormatHoldView extends BaseViewHolder {
    @BindView(R.id.ordersheet_dishes_type_item_tv)
    TextView mNameTv;
    @BindView(R.id.ordersheet_dishes_time_price_tv)
    TextView mTimePriceTv;
    @BindColor(R.color.ordersheet_colorAccent)
    int mSelectedColor;
    @BindColor(R.color.ordersheet_area_color)
    int mNomalColor;

    public DishesFormatHoldView(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    private void setDishesFormatName(String region_name) {
        mNameTv.setText(region_name);
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

    public void setItem(Specifications item) {
        float spcialPrice = 0f;
        float finalyPrice = 0f;
        if ("1".equals(item.getTag()) && !TextUtils.isEmpty(item.getTime_price())) {
            spcialPrice = AppUtil.getFloatFromString(item.getSale_price()).floatValue() * (AppUtil.getFloatFromString(item.getDiscount()).floatValue() / 100);
            //既是特价 又是时段价
            float timePrice = AppUtil.getFloatFromString(item.getTime_price()).floatValue();
            if (spcialPrice < timePrice) {
                finalyPrice = spcialPrice;
                mTimePriceTv.setText("特价菜");
                mTimePriceTv.setBackgroundResource(R.drawable.ordersheet_dishes_tj_bg);
            } else {
                finalyPrice = timePrice;
                mTimePriceTv.setText("时段价");
                mTimePriceTv.setBackgroundResource(R.drawable.ordersheet_dishes_time_bg);
            }
            ;
        } else if ("1".equals(item.getTag()) && TextUtils.isEmpty(item.getTime_price())) {
            spcialPrice = AppUtil.getFloatFromString(item.getSale_price()).floatValue() * (AppUtil.getFloatFromString(item.getDiscount()).floatValue() / 100);
            finalyPrice = spcialPrice;
            //特价
            mTimePriceTv.setVisibility(View.VISIBLE);
            mTimePriceTv.setText("特价菜");
            mTimePriceTv.setBackgroundResource(R.drawable.ordersheet_dishes_tj_bg);
        } else if (!"1".equals(item.getTag()) && !TextUtils.isEmpty(item.getTime_price())) {
            float timePrice = AppUtil.getFloatFromString(item.getTime_price()).floatValue();
            finalyPrice = timePrice;
            //时段
            mTimePriceTv.setVisibility(View.VISIBLE);
            mTimePriceTv.setText("时段价");
            mTimePriceTv.setBackgroundResource(R.drawable.ordersheet_dishes_time_bg);
        } else {
            finalyPrice = AppUtil.getFloatFromString(item.getSale_price()).floatValue();
            //既不是特价 又不是时段价
            mTimePriceTv.setVisibility(View.GONE);
        }
        setDishesFormatName(Param.Keys.RMB + finalyPrice + " / " + item.getSpecification());
    }
}
