package com.shigoo.cashregister.adapters.viewHolder;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.GiveDishesListAdapter;
import com.shigoo.cashregister.adapters.SetMealListAdapter;
import com.xgsb.datafactory.bean.GiveDishesbean;
import com.xgsb.datafactory.bean.SetMealGroupbean;
import com.zx.api.api.utils.AppUtil;
import com.zx.network.Param;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GiveDishesViewHolder extends BaseViewHolder {
    @BindView(R.id.ordersheet_dishes_name_tv)
    TextView mDishesName;
    @BindView(R.id.ordersheet_dishes_num_tv)
    TextView mDishesNum;
    @BindView(R.id.ordersheet_setmeal_item_bottom_layout)
    LinearLayout mBottomlayout;
    @BindView(R.id.ordersheet_dishes_time_price_tv)
    TextView mRestTv;
    @BindView(R.id.ordersheet_dishes_money)
    TextView mMoneyTv;
    @BindView(R.id.ordersheet_dishes_last_num)
    TextView mMaxNum;
    private GiveDishesbean mItem;
    private int currentNum;
    private GiveDishesListAdapter mealChildListAdapter;
    private SetMealGroupbean setMealGroupbean;
    private SetMealListAdapter.onNumChanage mLisenter;

    public GiveDishesViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setItem(GiveDishesbean item) {
        mItem = item;
        mDishesName.setText(item.getDishes_name());
        if (item.getNum() != 0) {
            mDishesNum.setVisibility(View.VISIBLE);
            mDishesNum.setText(item.getNum() + "");
        } else {
            mDishesNum.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(item.getGuqing())) {
            int num = AppUtil.getFloatFromString(item.getGuqing()).intValue();
            mRestTv.setVisibility(View.VISIBLE);
            if (num != 0) {
                mRestTv.setText("剩 " + AppUtil.getFloatFromString(item.getGuqing()).intValue() + item.getSpecification());
            } else {
                mRestTv.setText("已送完");
            }
        } else {
            mRestTv.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(item.getNum_ceiling()) && !"0".equals(item.getNum_ceiling())) {
            mMaxNum.setVisibility(View.VISIBLE);
            mMaxNum.setText("最多可送" + item.getGiving_income_number() + item.getSpecification());
        } else {
            mMaxNum.setVisibility(View.GONE);
        }
        mMoneyTv.setText(Param.Keys.RMB + item.getSale_price());
        addOnClickListener(R.id.ordersheet_decrice_tv);
        addOnClickListener(R.id.ordersheet_add_tv);

    }

    public void setSetMealGroupbean(SetMealGroupbean setMealGroupbean) {
        this.setMealGroupbean = setMealGroupbean;
    }

    public void setGiveDishesListAdapter(GiveDishesListAdapter mealChildListAdapter) {
        this.mealChildListAdapter = mealChildListAdapter;
    }

    public void setonNumChanage(SetMealListAdapter.onNumChanage lisenter) {
        this.mLisenter = lisenter;
    }
}
