package com.shigoo.cashregister.adapters;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.App;
import com.shigoo.cashregister.adapters.viewHolder.MenuListHoldView;
import com.xgsb.datafactory.JSONManager;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.Member;
import com.xgsb.datafactory.enu.DiscountType;
import com.zx.api.api.utils.AppLog;
import com.zx.api.api.utils.AppUtil;
import com.zx.api.api.utils.SPUtil;

import java.util.List;

public class MenuDishesListAdapter extends BaseQuickAdapter<Dishesbean, MenuListHoldView> {
    private int mCurrentPositon = -1;
    private onItemSelected onItemSelected;
    private DiscountType mDiscountType;
    private String mBillCode;
    private Member member;

    public MenuDishesListAdapter(int layoutResId, @Nullable List<Dishesbean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(MenuListHoldView helper, Dishesbean item) {
        helper.setBillCode(mBillCode);
        helper.setMember(member);
        helper.setItem(item, mDiscountType,mCurrentPositon);
        if (onItemSelected != null) {
            onItemSelected.onNotifyItem();
        }
    }

    public void setOnItemSelected(MenuDishesListAdapter.onItemSelected onItemSelected) {
        this.onItemSelected = onItemSelected;
    }

    public void setCurrentPositon(int positon) {
        mCurrentPositon = positon;
        if (onItemSelected != null && positon != -1) {
            onItemSelected.onItemSelected(positon);
        }
        notifyDataSetChanged();
    }

    public Dishesbean getCurrentItem() {
        if (mCurrentPositon != -1) {
            return getData().get(mCurrentPositon);
        } else {
            return null;
        }
    }

    public Dishesbean getTotalNum(int currentPosition) {
        Dishesbean number = getItem(currentPosition);
        return getTotalNum(number);
    }

    public Dishesbean getTotalNum(Dishesbean number) {
        int total = 0;
        for (Dishesbean dishesbean : getData()) {
            if (dishesbean.getDish_tag().equals(number.getDish_tag())) {
                if ("2".equals(number.getDish_tag())) {
                    if (dishesbean.getId() == number.getId()) {
                        total += dishesbean.getLocal_num();
                    }
                } else {
                    if (dishesbean.getCombo_id().equals(number.getCombo_id())) {
                        total += dishesbean.getLocal_num();
                    }
                }
            }
        }
        number.setTotal_local_num(total);
        return number;
    }

    public float getRestPrice() {
        float restPrice = 0f;
        for (Dishesbean dishesbean : getData()) {
            if ("0".equals(dishesbean.getPay_tag())) {
                restPrice += AppUtil.getFloatFromString(dishesbean.getShowPrice()).floatValue();
            }
        }
        return restPrice;
    }

    public interface onItemSelected {
        public void onItemSelected(int position);

        public void onNotifyItem();
    }

    public void setBillCode(String mBillCode) {
        this.mBillCode = mBillCode;
    }
}
