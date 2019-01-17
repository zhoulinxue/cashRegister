package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.GiveReasonHoldView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GiveReasonAdapter extends BaseQuickAdapter<String, GiveReasonHoldView> {
    private Map<Integer, Boolean> mCurrentPosition = new HashMap<>();

    public GiveReasonAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(GiveReasonHoldView helper, String item) {
        helper.setItem(item);
        helper.setCurrentPosition(mCurrentPosition.get(helper.getLayoutPosition()));
    }

    public void setSelected(int position) {
        if (mCurrentPosition.get(position) == null) {
            mCurrentPosition.put(position, true);
        } else if (mCurrentPosition.get(position) != null) {
            mCurrentPosition.put(position, !mCurrentPosition.get(position));
        }
        notifyDataSetChanged();
    }

    public StringBuffer getSelected() {
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<Integer, Boolean> entry : mCurrentPosition.entrySet()) {
            Boolean isSelected = mCurrentPosition.get(entry.getKey());
            if (isSelected != null && isSelected) {
                buffer.append(getData().get(entry.getKey()));
                buffer.append(";");
            }
        }
        return buffer;
    }
}
