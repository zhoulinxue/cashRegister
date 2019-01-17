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
public class TableHoldView extends BaseViewHolder {
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


    public TableHoldView(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setItem(Table item) {
        // 迭代 器 寻找当前应该显示的 账单
        Billbean billbean = item.getBillbean();
        AppLog.print(billbean == null ? "无账单" : billbean.getTable_number());
        mTableName.setText(item.getRegion_name() + " " + item.getTable_number());
        mPayNum.setText("低消  " + Param.Keys.RMB + item.getLowest_expense());
        // 显示合计消费的 数目
        float cost = getBillCost(item);
        if (cost != 0) {
            mCost.setVisibility(View.VISIBLE);
            mCost.setText("消费  " + Param.Keys.RMB + cost);
        } else {
            mCost.setVisibility(View.GONE);
        }
        //--------------------------------------------------------------------------
        if (billbean == null) {
            // 空闲或者 锁定台 账单为空
            if ("1".equals(item.getLock_tag())) {
                mStatusTv.setText("已锁定");
            } else {
                mStatusTv.setText("空闲");
                mTableNumStatus.setText("0/" + item.getSite_num());
                mColorLayout.setBackgroundColor(mKxColor);
            }
        } else {
            item.setCurrentBillbean(billbean);
            // 单 账单 的 relation_table_name 字段不为空时 说明有联台
            if (!TextUtils.isEmpty(billbean.getRelation_table_name())) {
                mLinkLayout.setVisibility(View.VISIBLE);
                mLinkName.setText(billbean.getRelation_table_name());
            } else {
                mLinkLayout.setVisibility(View.GONE);
            }
            // 当 vip tag==1 时  为 vip 显示vip 标记
            if ("1".equals(billbean.getVip_tag())) {
                mVipImg.setVisibility(View.VISIBLE);
            } else {
                mVipImg.setVisibility(View.GONE);
            }
            // 获取 当前台的 状态显示
            String status = billbean.getStatus();
            mStatusTv.setText(status);
            mGustName.setText(billbean.getGuest_name());
            mTableNumStatus.setText(billbean.getGuest_qty() + "/" + item.getSite_num());
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
        }
        //拼台
        //--------------------------------------------------------------------------
        if (item.isAssembleTable()) {
            mPingTTv.setVisibility(View.VISIBLE);
            mPTNumTv.setText(item.getBill().size() + "");
        } else {
            mPingTTv.setVisibility(View.GONE);
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
     * @param item
     * @param level
     * @return
     */
    private List<Billbean> hasBillTag(Table item, String level) {
        List<Billbean> list = new ArrayList<>();
        if (item.getBill() == null || item.getBill().size() == 0) {
            return null;
        } else {
            for (int i = 0; i < item.getBill().size(); i++) {
                Billbean billbean = item.getBill().get(i);
                if (level.equals(billbean.getBill_tag())) {
                    list.add(billbean);
                }
            }
        }
        return list;
    }

    /**
     * 获取台状态
     *
     * @param billbean
     * @return
     */
    private String getStatus(Billbean billbean) {
        if (!TextUtils.isEmpty(billbean.getBill_tag()) && Integer.valueOf(billbean.getBill_tag()) > 2) {
            return "已预订";
        }
        if ("0".equals(billbean.getFinsh_tag())) {
            if ("0".equals(billbean.getWaiter_id())) {
                return "已开台";
            } else {
                return "已下单";
            }
        } else {
            return "已结账";
        }
    }
}
