package com.shigoo.cashregister.adapters.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;
import com.xgsb.datafactory.bean.TableStatus;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TableBottomHoldView extends BaseViewHolder {
    @BindView(R.id.ordersheet_table_bottom_item_tv)
    TextView mNameTv;
    @BindColor(R.color.ordersheet_colorAccent)
    int mSelectedColor;
    @BindColor(R.color.ordersheet_area_color)
    int mNomalColor;
    @BindView(R.id.ordersheet_table_bottom_item_layout)
    LinearLayout mItemBgLayout;
    @BindView(R.id.ordersheet_table_bottom_item_img)
    ImageView mColorImg;

    public TableBottomHoldView(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setSelected(boolean isSelected) {
        if (isSelected) {
            mNameTv.setTextColor(mSelectedColor);
            mItemBgLayout.setBackgroundResource(R.drawable.ordersheet_table_bottom_item_selected_bg);
        } else {
            mNameTv.setTextColor(mNomalColor);
            mItemBgLayout.setBackgroundResource(R.drawable.ordersheet_table_bottom_item_bg);
        }
    }

    public void setItem(TableStatus item) {
        mColorImg.setBackgroundColor(itemView.getResources().getColor(item.getColor()));
        mNameTv.setText(item.getStatusName());
    }
}
