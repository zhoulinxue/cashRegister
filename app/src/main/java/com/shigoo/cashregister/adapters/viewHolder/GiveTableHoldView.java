package com.shigoo.cashregister.adapters.viewHolder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;
import com.xgsb.datafactory.bean.Billbean;
import com.xgsb.datafactory.bean.Table;
import com.zx.api.api.utils.AppLog;
import com.zx.api.api.utils.AppUtil;
import com.zx.network.Param;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Name: TableHoldView
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-12 16:05
 */
public class GiveTableHoldView extends BaseViewHolder {
    @BindView(R.id.ordersheet_table_title_tv)
    TextView mTableName;
    @BindView(R.id.ordersheet_table_table_status_tv)
    TextView mStatusTv;
    @BindView(R.id.ordersheet_table_pay_num_tv)
    TextView mTableNumStatus;
    @BindColor(R.color.white)
    int SelectedColor;
    @BindColor(R.color.ordersheet_table_name_color)
    int mTabNameColor;
    @BindColor(R.color.ordersheet_table_des_color)
    int mDesColor;
    @BindColor(R.color.ordersheet_colorAccent)
    int mMainColor;
    @BindView(R.id.ordersheet_link_tv)
    TextView mLinkName;
    @BindView(R.id.ordersheet_pingtai_num_tv)
    TextView mPTNumTv;
    @BindView(R.id.ordersheet_table_item_layout)
    RelativeLayout mBackBg;


    public GiveTableHoldView(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setItem(Table item) {
        // 迭代 器 寻找当前应该显示的 账单
        Billbean billbean = item.getBillbean();
        mTableName.setText(item.getRegion_name() + " " + item.getTable_number());
        //--------------------------------------------------------------------------
        if (billbean != null) {
            // 获取 当前台的 状态显示
            String status = billbean.getStatus();
            mStatusTv.setText(status);
            mTableNumStatus.setVisibility(View.VISIBLE);
            mTableNumStatus.setText(billbean.getGuest_qty() + "位");
        }
    }

    private float getBillCost(Table item) {
        float totalCost = 0;
        try {
            if (item.getBill() != null) {
                BigDecimal decimal = null;
                for (Billbean billbean : item.getBill()) {
                    if (!TextUtils.isEmpty(billbean.getBill_cost())) {
                        decimal = AppUtil.getFloatFromString(billbean.getBill_cost());
                        if (decimal != null) {
                            totalCost += decimal.floatValue();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        return totalCost;
    }

    /**
     * 按优先级 获取当前应该暂时的 单号
     *
     * @param item
     * @param level
     * @return
     */
    /**
     * 按优先级 获取当前应该暂时的 单号
     *
     * @param item
     * @param level
     * @return
     */
    private Billbean getCurrentBill(Table item, String level) {
        int lev = Integer.valueOf(level);
        if (item.getBill() == null || item.getBill().size() == 0) {
            return null;
        }
        for (int i = 0; i < item.getBill().size(); i++) {
            Billbean billbean = item.getBill().get(i);
            if (level.equals(billbean.getBill_tag())) {
                if ("1".equals(billbean.getBill_tag()) && "0".equals(item.getUse_tag())) {
                    //当前台 为主台 且 未被使用  迭代 找下一个台
                    return getCurrentBill(item, (lev + 1) + "");
                }
                return billbean;
            }
        }
        return null;
    }

    public void setSelect(int currentPosition) {
        if (currentPosition == getLayoutPosition()) {
            mBackBg.setBackgroundResource(R.drawable.ordersheet_table_item_selectd_bg);
            mTableName.setTextColor(SelectedColor);
            mStatusTv.setTextColor(SelectedColor);
            mTableNumStatus.setTextColor(SelectedColor);
        } else {
            mBackBg.setBackgroundResource(R.drawable.ordersheet_table_item_bg);
            mTableName.setTextColor(mTabNameColor);
            mStatusTv.setTextColor(mDesColor);
            mTableNumStatus.setTextColor(mDesColor);
        }
    }
}
