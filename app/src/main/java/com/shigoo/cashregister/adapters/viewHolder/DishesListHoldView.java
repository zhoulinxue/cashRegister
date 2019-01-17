package com.shigoo.cashregister.adapters.viewHolder;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;
import com.xgsb.datafactory.JSONManager;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.Specifications;
import com.zx.api.api.utils.AppLog;
import com.zx.api.api.utils.AppUtil;
import com.zx.network.Param;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DishesListHoldView extends BaseViewHolder {

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

    public DishesListHoldView(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }


    public void setItem(Dishesbean item) {
        mNametv.setText(item.getDish_name());
        if ("2".equals(item.getDish_tag())) {
            //单品 ui显示
            showDishesItem(item);
        } else {
            //套餐ui显示
            mDishesType.setVisibility(View.VISIBLE);
            mPriceTv.setText(Param.Keys.RMB + (TextUtils.isEmpty(item.getCombo_time_price()) ? item.getCombo_price() : item.getCombo_time_price()));
            if (!TextUtils.isEmpty(item.getCombo_time_price())) {
                mTimePriceTv.setVisibility(View.VISIBLE);
            } else {
                mTimePriceTv.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(item.getCombo_guqing())) {
                mRestNum.setVisibility(View.VISIBLE);
                if (!"0".equals(item.getCombo_guqing())) {
                    mRestNum.setText("剩 " + item.getCombo_guqing() + "份");
                } else {
                    mRestNum.setText("已售完");
                }
            } else {
                mRestNum.setVisibility(View.GONE);
            }
        }

        if (item.getTotal_local_num() != 0) {
            mNumTv.setVisibility(View.VISIBLE);
            mNumTv.setText(item.getTotal_local_num() + "");
        } else {
            mNumTv.setVisibility(View.GONE);
        }
    }

    /**
     * 单品 ui显示
     *
     * @param item
     */
    private void showDishesItem(Dishesbean item) {
        List<Specifications> list = item.getDishes();
        if (list != null) {
            boolean hasTimePrice = false;
            boolean hasSpicePrice=false;
            List<Float> floats = new ArrayList<>();
            float finalyPrice=0f;
            for (Specifications childItem : list) {
                if (!TextUtils.isEmpty(childItem.getSale_price())) {
                    floats.add(Float.valueOf(childItem.getSale_price()));
                }
                if (!TextUtils.isEmpty(childItem.getTime_price())) {
                    // 是否有时段价
                    hasTimePrice = true;
                }
                if("1".equals(childItem.getTag())){
                    //是否有特价菜
                    hasSpicePrice=true;
                }
            }
            float min = Collections.min(floats);
            float max = Collections.max(floats);
            if (min != max) {
                mPriceTv.setText(Param.Keys.RMB + min + "--" + Param.Keys.RMB + max);
                mTimePriceTv.setVisibility(View.GONE);
            } else {
                if(hasSpicePrice&&hasTimePrice){
                    mTimePriceTv.setVisibility(View.VISIBLE);
                    float  spcialPrice = AppUtil.getFloatFromString(item.getSale_price()).floatValue() * (AppUtil.getFloatFromString(item.getDishes().get(0).getDiscount()).floatValue() / 100);
                    float timePrice=AppUtil.getFloatFromString(item.getDishes().get(0).getTime_price()).floatValue();
                    if(spcialPrice<timePrice){
                        finalyPrice=spcialPrice;
                        mTimePriceTv.setText("特价菜");
                        mTimePriceTv.setBackgroundResource(R.drawable.ordersheet_dishes_tj_bg);
                    }else {
                        finalyPrice=timePrice;
                        mTimePriceTv.setText("时段价");
                        mTimePriceTv.setBackgroundResource(R.drawable.ordersheet_dishes_time_bg);
                    };
                }else if(hasSpicePrice&&!hasTimePrice){
                    float spcialPrice = AppUtil.getFloatFromString(item.getSale_price()).floatValue() * (AppUtil.getFloatFromString(item.getDishes().get(0).getDiscount()).floatValue() / 100);
                    //特价
                    finalyPrice=spcialPrice;
                    mTimePriceTv.setVisibility(View.VISIBLE);
                    mTimePriceTv.setText("特价菜");
                    mTimePriceTv.setBackgroundResource(R.drawable.ordersheet_dishes_tj_bg);
                    mPriceTv.setText(Param.Keys.RMB + spcialPrice);
                }else if(!hasSpicePrice&&hasTimePrice){
                    mTimePriceTv.setVisibility(View.VISIBLE);
                    float timePrice=AppUtil.getFloatFromString(item.getDishes().get(0).getTime_price()).floatValue();
                    finalyPrice=timePrice;
                    mTimePriceTv.setText("时段价");
                    mTimePriceTv.setBackgroundResource(R.drawable.ordersheet_dishes_time_bg);
                }else{
                    finalyPrice=AppUtil.getFloatFromString(item.getDishes().get(0).getSale_price()).floatValue();
                    //既不是特价 又不是时段价
                    mTimePriceTv.setVisibility(View.GONE);
                }
                mPriceTv.setText(Param.Keys.RMB+finalyPrice);
            }
            Specifications specifications = list.get(0);
            mRestNum.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(specifications.getGuqing()) && !"0".equals(specifications.getGuqing())) {
                mRestNum.setText("剩 " + specifications.getGuqing() + specifications.getSpecification());
            } else if ("0".equals(specifications.getGuqing())) {
                mRestNum.setVisibility(View.VISIBLE);
                mRestNum.setText("已售完");
            } else {
                mRestNum.setVisibility(View.GONE);
            }
        } else {
            AppLog.print("菜品无规格" + JSONManager.getInstance().toJson(item.getId()));
        }
        mDishesType.setVisibility(View.GONE);
    }
}
