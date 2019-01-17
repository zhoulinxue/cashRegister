package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.FavorableTypeHoldView;
import com.shigoo.cashregister.adapters.viewHolder.PayFavorableHoldView;
import com.xgsb.datafactory.bean.AddFavorablebean;
import com.xgsb.datafactory.bean.AddPayment;
import com.xgsb.datafactory.bean.Favorablebean;

import java.util.List;

public class PayFavorableAdapter extends BaseQuickAdapter<AddFavorablebean, PayFavorableHoldView> {
    public PayFavorableAdapter(int layoutResId, @Nullable List<AddFavorablebean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(PayFavorableHoldView helper, AddFavorablebean item) {
        helper.setItem(item);
    }

    public void update(AddFavorablebean favorablebean) {
        for(int i=0;i<getData().size();i++){
            if(getData().get(i).equals(favorablebean)){
                getData().remove(i);
                addData(i,favorablebean);
                return;
            }
        }
    }

    public void removeData(String favorableName) {
        for(int i=0;i<getData().size();i++){
            if(favorableName.equals(getData().get(i).getName())){
                getData().remove(i);
                return;
            }
        }
    }
}
