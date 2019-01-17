package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.viewHolder.CouponViewHolder;
import com.xgsb.datafactory.bean.Couponbean;
import com.zx.network.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Auther:zhouxue
 * Time :2018/8/24.
 */

public class CouponAdapter extends BaseQuickAdapter<Couponbean, CouponViewHolder> {
    private boolean mSelectedMode = false;
    private Map<Integer, Boolean> isSelected = new HashMap<>();
    private int mCurrentPosition;
    private MenuDishesListAdapter.onItemSelected mLisenter;

    public CouponAdapter(int layoutResId, @Nullable List<Couponbean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(CouponViewHolder helper, Couponbean item) {
        helper.mCouponMoney.setText(Param.Keys.RMB + item.getVoucher_money());
        helper.mCouponTypeName.setText("1".equals(item.getVoucher_type()) ? "代金券" : "商品卷");
        helper.mLimit.setText(item.getLimit());
        helper.mTimeText.setText(item.getVoucher_date() + "  -  " + item.getEnd_date());
        helper.mCheckBox.setVisibility(mSelectedMode ? View.VISIBLE : View.GONE);
        Boolean selectedP = isSelected.get(helper.getLayoutPosition());
        helper.mCheckBox.setChecked(selectedP != null && selectedP);
        helper.addOnClickListener(R.id.check_box);
        if (mLisenter != null) {
            mLisenter.onNotifyItem();
        }
    }

    public void setLisenter(MenuDishesListAdapter.onItemSelected lisenter) {
        this.mLisenter = lisenter;
    }

    public void setSelectModle(boolean b) {
        mSelectedMode = b;
    }

    public void setSelected(int position) {
        mCurrentPosition = position;
        for (int i = 0; i < getData().size(); i++) {
            if (position == i) {
                isSelected.put(i, isSelected.get(i)==null?true:!isSelected.get(i));
            } else {
                isSelected.put(i, false);
            }
        }
        notifyDataSetChanged();
    }

    public String getVoucher_money() {
        for (Map.Entry<Integer, Boolean> entry : isSelected.entrySet()) {
            if (entry.getValue()) {
                return getItem(entry.getKey()).getVoucher_money();
            }
        }
        return "0";
    }
}
