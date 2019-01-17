package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.viewHolder.MemberRechargeGiveHolder;
import com.xgsb.datafactory.bean.MemberReChargeGive;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Name: MemberLevelsAdapter
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-04 11:01
 */
public class MemberReChargeGivesAdapter extends BaseQuickAdapter<MemberReChargeGive, MemberRechargeGiveHolder> {
    private Map<String, Boolean> isSelected = new HashMap<>();
    private MemberReChargeGive memberReChargeGive;

    public MemberReChargeGivesAdapter(int layoutResId, @Nullable List<MemberReChargeGive> data) {
        super(layoutResId, data);
        initSelect(data);
    }

    private void initSelect(List<MemberReChargeGive> data) {
        if (data != null && data.size() != 0) {
            for (int i = 0; i < data.size(); i++) {
                isSelected.put(i + "", false);
            }
        }
    }

    @Override
    protected void convert(MemberRechargeGiveHolder helper, MemberReChargeGive item) {
        Boolean isSele = isSelected.get(helper.getLayoutPosition() + "");
        if (isSele == null || !isSele) {
            helper.mRechargeLayout.setBackgroundResource(R.drawable.card_level_bg);
            helper.mRechargeName.setTextColor(mContext.getResources().getColor(R.color.menber_num_decript_color));
            helper.mRechargeGive.setTextColor(mContext.getResources().getColor(R.color.menber_num_decript_color));
        } else {
            helper.mRechargeLayout.setBackgroundResource(R.drawable.card_level_bg_selected);
            helper.mRechargeName.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            helper.mRechargeGive.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        }
        helper.mRechargeName.setText(item.getAmount_of_money() + "元");
        helper.mRechargeGive.setText(item.getGive() + "元");
    }

    @Override
    public void setNewData(@Nullable List<MemberReChargeGive> data) {
        super.setNewData(data);
        initSelect(data);
    }


    public MemberReChargeGive onSelected(int position) {
        memberReChargeGive = getItem(position);
        for (int i = 0; i < getData().size(); i++) {
            if (i == position) {
                isSelected.put(i + "", true);
            } else {
                isSelected.put(i + "", false);
            }
        }
        notifyDataSetChanged();
        return memberReChargeGive;
    }

    public MemberReChargeGive getMemberReChargeGive() {
        return memberReChargeGive;
    }
}
