package com.shigoo.cashregister.adapters.viewHolder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Auther:zhouxue
 * Time :2018/8/7.
 */

public class MemberLevelHolder extends BaseViewHolder {
    @BindView(R.id.member_level_name)
    public TextView mLevelName;
    @BindView(R.id.member_youhu_name)
    public TextView mLevelZhe;
    @BindView(R.id.level_item_layout)
    public LinearLayout mLevelLayout;

    public MemberLevelHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }
}
