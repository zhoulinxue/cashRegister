package com.shigoo.cashregister.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.activitys.AssembleTablesActivity;
import com.shigoo.cashregister.adapters.TableAreaListAdapter;
import com.shigoo.cashregister.adapters.TableListAdapter;
import com.shigoo.cashregister.mvp.contacts.TableContact;
import com.shigoo.cashregister.mvp.presenter.TablePresenter;
import com.xgsb.datafactory.bean.Billbean;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.bean.SettalOrderResultbean;
import com.xgsb.datafactory.bean.Table;
import com.xgsb.datafactory.bean.TableArea;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.mvplibrary.MvpFragment;
import com.zx.network.Param;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CopyDishesFragment extends MvpFragment<TablePresenter> implements TableContact.view {
    @BindView(R.id.ordersheet_table_area_recyclerview)
    RecyclerView mAreaListView;
    @BindView(R.id.ordersheet_table_recyclerview)
    RecyclerView mListView;
    TableListAdapter mAdapter;
    TableAreaListAdapter mAreaAdapter;
    private List<TableArea> mAreaList = new ArrayList<>();
    private List<Table> mList = new ArrayList<>();
    private String mBillCode;

    public static CopyDishesFragment newInstance() {
        CopyDishesFragment fragment = new CopyDishesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.ordersheet_copy_dishes_fragment;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        mAreaAdapter = new TableAreaListAdapter(R.layout.ordersheet_table_area_item_layout, mAreaList);
        mAreaAdapter = new TableAreaListAdapter(R.layout.ordersheet_table_area_item_layout, mAreaList);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mAreaListView.setLayoutManager(manager);
        mAreaListView.setAdapter(mAreaAdapter);
        mAdapter = new TableListAdapter(R.layout.ordersheet_table_item_layout, mList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Table table = mAdapter.getData().get(position);
                if (table.isAssembleTable()) {
                    Intent intent = new Intent(getActivity(), AssembleTablesActivity.class);
                    intent.setAction(Param.ACTION.COPY);
                    intent.putExtra(Param.Keys.TABLE, mAdapter.getData().get(position));
                    startActivity(intent);
                    return;
                } else {
                    copyDishes(table.getBillbean().getBill_code());
                }
            }
        });
        mListView.setLayoutManager(new GridLayoutManager(getContext(), 5));
        mListView.setAdapter(mAdapter);
        mAreaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mAdapter.getFilter().filter(mAreaAdapter.getData().get(position).getRegion_name());
                mAreaAdapter.setSelected(position);
            }
        });
    }

    private void copyDishes(String bill_code) {
        mPresenter.copyDishes(Param.Keys.TOKEN, getToken(), Param.Keys.BILL_CODE, bill_code);
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mPresenter.getAreaList(Param.Keys.TOKEN, getToken());
        mPresenter.getTableList(Param.Keys.TOKEN, getToken());
    }

    @Override
    protected TablePresenter onCtreatPresenter() {
        return new TablePresenter(this);
    }

    @Override
    public void onTableResult(List<Table> tables) {
        fillList(tables);
        mAdapter.setNewData(mList);
    }

    @Override
    public void onAreaListResult(List<TableArea> areas) {
        mAreaList.clear();
        mAreaList.add(new TableArea(0, "全部区域"));
        mAreaList.addAll(areas);
        mAreaAdapter.setNewData(mAreaList);
        mAreaAdapter.setSelected(0);
    }

    @Override
    public void onCopySuc(SettalOrderResultbean listData) {
        EventBus.getDefault().post(new EventRouter(EventBusAction.COPY_SUC, listData));
    }

    private void fillList(List<Table> tables) {
        mList.clear();
        for (Table table : tables) {
            //获取当前 应该展示的台
            //--------------------------------------------------------
            Billbean billbean = getCurrentBill(table, "1");
            if (billbean == null) {
                if (!"1".equals(table.getLock_tag())) {
                    table.setLocal_status("空闲");
                }
            } else {
                // 不显示已锁定、空闲、已预订的台号
                if (!table.isLocked() && !table.isKx() && !"3".equals(billbean.getBill_tag())) {
                    mList.add(table);
                }
                table.setLocal_status(billbean.getStatus());
            }

        }
    }

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

    public void copyChild(Billbean copyChild) {
        copyDishes(copyChild.getBill_code());
    }

    public void setBillCode(String s) {
        this.mBillCode = s;
    }
}
