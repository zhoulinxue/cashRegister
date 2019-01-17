package com.shigoo.cashregister.adapters.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;
import com.xgsb.datafactory.bean.Billbean;
import com.xgsb.datafactory.bean.Table;
import com.zx.api.api.utils.AppUtil;
import com.zx.network.Param;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BillHoldView extends BaseViewHolder {
    @BindView(R.id.ordersheet_table_title_tv)
    TextView mTableName;
    @BindView(R.id.ordersheet_table_color_bg)
    LinearLayout mColorLayout;
    @BindView(R.id.ordersheet_table_table_status_tv)
    TextView mStatusTv;
    @BindView(R.id.ordersheet_table_num_status)
    TextView mTableNumStatus;
    @BindView(R.id.ordersheet_table_order_name_tv)
    TextView mGustName;
    @BindView(R.id.ordersheet_table_pay_num_tv)
    TextView mPayNum;
    @BindView(R.id.ordersheet_table_pay_tv)
    TextView mCost;
    @BindColor(R.color.ordersheet_kx_color)
    int mKxColor;
    @BindColor(R.color.ordersheet_yd_color)
    int mYdColor;
    @BindColor(R.color.ordersheet_kt_color)
    int mKtColor;
    @BindColor(R.color.ordersheet_xd_color)
    int mXdColor;
    @BindColor(R.color.ordersheet_jz_color)
    int mJzColor;
    @BindView(R.id.ordersheet_table_item_link_layout)
    LinearLayout mLinkLayout;
    @BindView(R.id.ordersheet_link_tv)
    TextView mLinkName;
    @BindView(R.id.ordersheet_vip_img)
    ImageView mVipImg;
    @BindView(R.id.ordersheet_pingtai_num_layout)
    RelativeLayout mPingTTv;
    @BindView(R.id.ordersheet_pingtai_num_tv)
    TextView mPTNumTv;
    private Table mTable;

    public BillHoldView(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setItem(Billbean item) {
        mTableName.setText(mTable.getRegion_name() + " " + item.getTable_number());
        float cost = AppUtil.getFloatFromString(item.getBill_cost()).floatValue();
        if (cost != 0) {
            mCost.setVisibility(View.VISIBLE);
            mCost.setText("消费  " + Param.Keys.RMB + cost);
        } else {
            mCost.setVisibility(View.GONE);
        }
        // 当 vip tag==1 时  为 vip 显示vip 标记
        if ("1".equals(item.getVip_tag())) {
            mVipImg.setVisibility(View.VISIBLE);
        } else {
            mVipImg.setVisibility(View.GONE);
        }

        // 获取 当前台的 状态显示
        String status = item.getStatus();
        mStatusTv.setText(status);
        mGustName.setText(item.getGuest_name());
        switch (status) {
            case "已预订":
                mColorLayout.setBackgroundColor(mYdColor);
                break;
            case "已开台":
                mColorLayout.setBackgroundColor(mKtColor);
                break;
            case "已下单":
                mColorLayout.setBackgroundColor(mXdColor);
                break;
            case "已结账":
                mColorLayout.setBackgroundColor(mJzColor);
                break;
            case "空闲":
                mColorLayout.setBackgroundColor(mKxColor);
                break;
        }
        mPayNum.setText("低消  " + Param.Keys.RMB + mTable.getLowest_expense());
        mTableNumStatus.setText(item.getGuest_qty() + "/" + mTable.getSite_num());
    }

    public void setLower(Table table) {
        this.mTable = table;
    }
}
