package com.shigoo.cashregister.activitys;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.TableBillListAdapter;
import com.xgsb.datafactory.bean.Billbean;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.bean.Table;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.mvplibrary.BaseActivity;
import com.zx.network.Param;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AssembleTablesActivity extends BaseActivity {
    private Table mTable;
    @BindView(R.id.ordersheet_bill_listview)
    RecyclerView mRecyclerView;
    private TableBillListAdapter mAdapter;
    private String mAction;

    @Override
    protected int initLayout() {
        return R.layout.ordersheet_assemble_activity;
    }

    @Override
    protected void onCreateView() {
        ButterKnife.bind(this);
        mTable = getIntent().getParcelableExtra(Param.Keys.TABLE);
        mAction = getIntent().getAction();
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mAdapter = new TableBillListAdapter(R.layout.ordersheet_table_item_layout, mTable.getBill());
        int width = mTable.getBill().size() < 3 ? mTable.getBill().size() : 3;
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, width));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setTable(mTable);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Billbean billbean = mAdapter.getData().get(position);
                switch (mAction) {
                    case Param.ACTION.COPY:
                        EventBus.getDefault().post(new EventRouter(EventBusAction.COPY_CHILD, billbean));
                        break;
                    case Param.ACTION.DETAIL:
                        EventBus.getDefault().post(new EventRouter(EventBusAction.TABLE, getTableFromBill(billbean)));
                        break;
                }
                finish();
            }
        });
    }

    private Table getTableFromBill(Billbean billbean) {
        billbean.setRegion_name(mTable.getRegion_name());
        Table table = new Table();
        table.setCurrentBillbean(billbean);
        table.setLocal_status(billbean.getLocal_status());
        return table;
    }

    @Override
    public void onSuccess(Object object) {

    }

    @OnClick({R.id.ordersheet_bill_close_img})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.ordersheet_bill_close_img:
                finish();
                break;
        }
    }
}
