package com.shigoo.cashregister.activitys;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.PayListAdapter;
import com.shigoo.cashregister.adapters.RemarkListAdapter;
import com.shigoo.cashregister.mvp.contacts.FanJZContact;
import com.shigoo.cashregister.mvp.presenter.FanJZPresenter;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.bean.FanJZbean;
import com.xgsb.datafactory.bean.OrderPayStatusbean;
import com.xgsb.datafactory.bean.Paybean;
import com.xgsb.datafactory.bean.Remarkbean;
import com.xgsb.datafactory.bean.SettalOrderResultbean;
import com.xgsb.datafactory.bean.Table;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.mvplibrary.MvpActivity;
import com.zx.network.Param;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FanJZActivity extends MvpActivity<FanJZPresenter> implements FanJZContact.view {
    @BindView(R.id.ordersheet_dishes_give_reason_list)
    RecyclerView mFanZJRecyclerView;
    @BindView(R.id.order_pay_orderlist)
    RecyclerView mPayRecyclerView;
    @BindView(R.id.ordersheet_dishes_reason_edite)
    EditText mResonEdite;
    @BindView(R.id.pay_item_list_layout)
    LinearLayout mPayListlayout;
    @BindView(R.id.reason_layout)
    LinearLayout mReasonLayout;
    RemarkListAdapter mRemarkAdapter;
    List<Remarkbean> mList = new ArrayList<>();
    List<Paybean> mPayList = new ArrayList<>();
    PayListAdapter mPayListAdapter;
    private String mNum;
    private Table mTable;
    private Paybean mPaybean;

    @Override
    public void onOrderDishesListResult(SettalOrderResultbean resultbean) {

    }

    @Override
    public void onFJZOrderResult(String listData) {
        mTable.setLocal_status("已下单");
        EventBus.getDefault().post(new EventRouter(EventBusAction.FAN_JIE_ZHANG, mTable));
        finish();
    }

    @Override
    public void onPayResult(OrderPayStatusbean paybeans) {
        if (paybeans != null && paybeans.getList() != null && paybeans.getList().size() != 0) {
            mPayList.clear();
            for (Paybean paybean : paybeans.getList()) {
                if ("1".equals(paybean.getPay_tag())) {
                    mPayList.add(paybean);
                }
            }
            if (mPayList.size() == 1) {
                mNum = mPayListAdapter.getData().get(0).getPay_num() + "";
                mReasonLayout.setVisibility(View.VISIBLE);
                mPayListlayout.setVisibility(View.GONE);
                return;
            }
            mPayListAdapter.setNewData(mPayList);
        }
    }

    @Override
    public void onReasonlResult(List<Remarkbean> resonList) {

    }

    @Override
    protected FanJZPresenter onCtreatPresenter() {
        return new FanJZPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.fan_jie_zhang_layout;
    }

    @Override
    protected void onCreateView() {
        mRemarkAdapter = new RemarkListAdapter(R.layout.ordersheet_remark_item_layout, mList);
        mFanZJRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mFanZJRecyclerView.setAdapter(mRemarkAdapter);

        mPayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPayListAdapter = new PayListAdapter(R.layout.paylist_item_layout, mPayList);
        mPayRecyclerView.setAdapter(mPayListAdapter);
        mPayListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mNum = mPayListAdapter.getData().get(position).getPay_num() + "";
            }
        });
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mTable = getIntent().getParcelableExtra(Param.Keys.TABLE);
        //备注类型1表示单品备注，2表示整单备注，3退菜备注，4改价备注，5打折备注，6撤单备注，8反结账原因
        mPresenter.getFJZReasonList(Param.Keys.TOKEN, getToken(), Param.Keys.TYPE, "8");
        mPresenter.getPayStatus(Param.Keys.TOKEN, getToken(), Param.Keys.BILL_CODE, mTable.getBillbean().getBill_code());
    }

    @OnClick({R.id.ordersheet_dishes_bootom_cancel, R.id.ordersheet_dishes_pay_sure, R.id.ordersheet_dishes_bottom_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ordersheet_dishes_bottom_sure:
                List<String> reasons = mRemarkAdapter.getReason();
                if (!TextUtils.isEmpty(mResonEdite.getText())) {
                    reasons.add(mResonEdite.getText().toString());
                }
                FanJZbean fanJZbean = new FanJZbean(getToken(), mTable.getBillbean().getBill_code(), mNum, mRemarkAdapter.getReason());
                mPresenter.FJZOrder(fanJZbean);
                break;
            case R.id.ordersheet_dishes_bootom_cancel:
                finish();
                break;
            case R.id.ordersheet_dishes_pay_sure:
                if (!TextUtils.isEmpty(mNum)) {
                    mReasonLayout.setVisibility(View.VISIBLE);
                    mPayListlayout.setVisibility(View.GONE);
                } else {
                    showToast("请选择要反结账的条目");
                }
                break;
        }
    }
}