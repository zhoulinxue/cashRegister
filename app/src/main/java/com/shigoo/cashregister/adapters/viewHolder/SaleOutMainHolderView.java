package com.shigoo.cashregister.adapters.viewHolder;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.Specifications;
import com.zx.network.Param;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaleOutMainHolderView extends BaseViewHolder {
    @BindView(R.id.ordersheet_dishes_name_tv)
    TextView mNametv;
    @BindView(R.id.ordersheet_dishes_price_tv)
    TextView mPriceTv;
    @BindView(R.id.ordersheet_dishes_type_name)
    TextView mDishesType;
    @BindView(R.id.ordersheet_dishes_last_num)
    TextView mRestNum;
    @BindView(R.id.ordersheet_dishes_time_price_tv)
    TextView mTimePriceTv;
    @BindView(R.id.ordersheet_dishes_num_tv)
    TextView mNumTv;

    public SaleOutMainHolderView(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }


    public void setItem(Dishesbean item) {
        mNametv.setText(item.getDish_name());
        List<Specifications> list = new ArrayList<>();
        if (item.getCurrentSp() != null) {
            list.add(item.getCurrentSp());
        }
        if (list != null && list.size() == 1) {
            Specifications specifications = list.get(0);
            mRestNum.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(specifications.getGuqing()) && !"0".equals(specifications.getGuqing())) {
                mRestNum.setText("剩 " + specifications.getGuqing() + specifications.getSpecification());
            } else if ("0".equals(specifications.getGuqing())) {
                mRestNum.setText("已售完");
            } else {
                mRestNum.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(specifications.getTime_price())) {
                mTimePriceTv.setVisibility(View.VISIBLE);
            } else {
                mTimePriceTv.setVisibility(View.GONE);
            }
        } else if (!TextUtils.isEmpty(item.getCombo_guqing())) {
            mRestNum.setVisibility(View.VISIBLE);
            if (!"0".equals(item.getCombo_guqing())) {
                mRestNum.setText("剩 " + item.getCombo_guqing() + "份");
            } else if ("0".equals(item.getCombo_guqing())) {
                mRestNum.setText("已售完");
            } else {
                mRestNum.setVisibility(View.GONE);
            }
        } else {
            mRestNum.setVisibility(View.GONE);
        }
        List<Float> floats = new ArrayList<>();
        if ("2".equals(item.getDish_tag())) {
            for (Specifications specifications : list) {
                if (!TextUtils.isEmpty(specifications.getSale_price())) {
                    floats.add(Float.valueOf(specifications.getSale_price()));
                }
            }
            float min = Collections.min(floats);
            float max = Collections.max(floats);
            if (min != max) {
                mPriceTv.setText(Param.Keys.RMB + min + "--" + Param.Keys.RMB + max);
            } else {
                mPriceTv.setText(Param.Keys.RMB + max);
            }
            mDishesType.setVisibility(View.GONE);
        } else {
            mDishesType.setVisibility(View.VISIBLE);
            mPriceTv.setText(Param.Keys.RMB + item.getCombo_price());
        }
        if (item.getTotal_local_num() != 0) {
            mNumTv.setVisibility(View.VISIBLE);
            mNumTv.setText(item.getTotal_local_num() + "");
        } else {
            mNumTv.setVisibility(View.GONE);
        }
    }
}
