package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.PayMoneyViewHolder;
import com.shigoo.cashregister.adapters.viewHolder.PaymentTypeHoldView;
import com.xgsb.datafactory.bean.AddPayment;
import com.xgsb.datafactory.bean.PayTypebean;
import com.xgsb.datafactory.bean.SettalOrderbean;
import com.zx.api.api.utils.AppUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayMoneyTypeListAdapter extends BaseQuickAdapter<AddPayment, PayMoneyViewHolder> {
    private SettalOrderbean mOrderbean;
    private Map<Integer, Boolean> isNoStorageValus = new HashMap<>();
    private MenuDishesListAdapter.onItemSelected mLisenter;

    public PayMoneyTypeListAdapter(int layoutResId, @Nullable List<AddPayment> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(PayMoneyViewHolder helper, AddPayment item) {
        helper.setItem(item);
        if(mLisenter!=null){
            mLisenter.onNotifyItem();
        }
    }

    public void setLisenter(MenuDishesListAdapter.onItemSelected lisenter) {
        this.mLisenter = lisenter;
    }

    public int replace(AddPayment addPayment) {
        for (int i = 0; i < getData().size(); i++) {
            AddPayment payment = getData().get(i);
            if (payment.getPay_way().equals(addPayment.getPay_way())) {
                remove(i);
                addData(i, addPayment);
                return i;
            }
        }
        return 0;
    }

    public void setStorageValue(int  position, boolean isNoStorage) {
        isNoStorageValus.put(position, isNoStorage);
        notifyDataSetChanged();
    }

    public float getRestPrice() {
        float payFloat = 0f;
        for (int i = 0; i < getData().size(); i++) {
            payFloat += getData().get(i).getPay_amount();
        }
        return payFloat;
    }

    public String getNoStorageMoney() {
        float nostorage = 0f;
        for (Map.Entry<Integer, Boolean> entry : isNoStorageValus.entrySet()) {
            if (isNoStorageValus.get(entry.getKey())) {
                nostorage += getData().get(entry.getKey()).getPay_amount();
            }
        }
        return nostorage + "";
    }
}
