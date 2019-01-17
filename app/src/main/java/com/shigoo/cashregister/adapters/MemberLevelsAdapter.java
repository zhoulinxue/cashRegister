package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.viewHolder.MemberLevelHolder;
import com.xgsb.datafactory.bean.MemberLevel;

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
public class MemberLevelsAdapter extends BaseQuickAdapter<MemberLevel, MemberLevelHolder> implements BaseQuickAdapter.OnItemClickListener {
    private Map<String, Boolean> isSelected = new HashMap<>();
    private MemberLevel memberLevel;

    public MemberLevelsAdapter(int layoutResId, @Nullable List<MemberLevel> data) {
        super(layoutResId, data);
        initSelect(data);
        setOnItemClickListener(this);
    }

    private void initSelect(List<MemberLevel> data) {
        if (data != null && data.size() != 0) {
            for (int i = 0; i < data.size(); i++) {
                isSelected.put(i + "", false);
            }
        }
    }

    @Override
    protected void convert(MemberLevelHolder helper, MemberLevel item) {
        Boolean isSele = isSelected.get(helper.getLayoutPosition() + "");
        if (isSele == null || !isSele) {
            helper.mLevelLayout.setBackgroundResource(R.drawable.card_level_bg);
            helper.mLevelName.setTextColor(mContext.getResources().getColor(R.color.menber_num_decript_color));
            helper.mLevelZhe.setTextColor(mContext.getResources().getColor(R.color.menber_num_decript_color));
        } else {
            helper.mLevelLayout.setBackgroundResource(R.drawable.card_level_bg_selected);
            helper.mLevelName.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            helper.mLevelZhe.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        }
        helper.mLevelName.setText(item.getGrade_name());
        helper.mLevelZhe.setText(item.getGrade_discount_than_name());
    }

    @Override
    public void setNewData(@Nullable List<MemberLevel> data) {
        super.setNewData(data);
        initSelect(data);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        memberLevel = getItem(position);
        for (int i = 0; i < getData().size(); i++) {
            if (i == position) {
                isSelected.put(i + "", true);
            } else {
                isSelected.put(i + "", false);
            }
        }
        notifyDataSetChanged();
    }

    public String getCurrentLevel() {
        if (getData() != null && getData().size() != 0) {
            return memberLevel == null ? getData().get(0).getId() + "" : memberLevel.getId() + "";
        } else {
            return "";
        }
    }

    public int setSelected(String grade_discount) {
        int position = 0;
        for (int i = 0; i < getData().size(); i++) {
            if (getData().get(i).getGrade_discount().equals(grade_discount)) {
                position = i;
                memberLevel = getData().get(i);
                isSelected.put(i + "", true);
            } else {
                isSelected.put(i + "", false);
            }
        }
        return position;
    }
}
