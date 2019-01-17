package com.shigoo.cashregister.customViews;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shigoo.cashregister.R;
import com.xgsb.datafactory.bean.ReChargeListData;
import com.zx.mvplibrary.BaseCustomView;
import com.zx.network.Param;

import butterknife.BindView;

/**
 * Name: ReChargeHeaderView
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-27 19:28
 */
public class ReChargeHeaderView extends BaseCustomView {
    @BindView(R.id.records_tv)
    TextView mRecordsTv;
    @BindView(R.id.recharge_count)
    TextView mRechargeCount;
    @BindView(R.id.recharge_money)
    TextView mRechargeMoney;
    @BindView(R.id.recharge_give)
    TextView mRechargeGive;
    @BindView(R.id.money_header)
    FrameLayout mMoneyHeaderLayout;
    private MemberMoneyView mMoneyheader;

    public ReChargeHeaderView(Context context, ViewGroup rootGroup) {
        super(context, rootGroup);
    }

    public ReChargeHeaderView(Context context) {
        super(context);
    }

    @Override
    protected void initView(Context context, View rootView) {
        mMoneyheader = new MemberMoneyView(context, mMoneyHeaderLayout);
    }

    @Override
    public int initLayout() {
        return R.layout.recharge_header_layout;
    }

    public String getStartTime() {
        return mMoneyheader.getStartTime();
    }

    public String getEndTime() {
        return mMoneyheader.getEndTime();
    }


    public String getKey() {
        return mMoneyheader.getKey();
    }

    public void setNum(ReChargeListData data) {
        mRechargeCount.setText(Param.Keys.RMB + data.getMoney_count());
        mRechargeMoney.setText(Param.Keys.RMB + data.getMoney());
        mRechargeGive.setText(Param.Keys.RMB + data.getMoney_give());
    }

    public void setQueryNum(int size) {
        String total = String.format("查询到 %s 笔记录", size + "");
        mRecordsTv.setText(total);
    }

    public TextView getSearchBtn() {
        return mMoneyheader.getSearchTv();
    }

    public TextView getCleanBtn() {
        return mMoneyheader.getCleanTv();
    }

    public void clean() {
        mMoneyheader.clean();
    }
}
