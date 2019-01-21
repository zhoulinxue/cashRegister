package com.shigoo.cashregister.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.activitys.AssembleTablesActivity;
import com.shigoo.cashregister.adapters.TableAreaListAdapter;
import com.shigoo.cashregister.adapters.TableBottomListAdapter;
import com.shigoo.cashregister.adapters.TableListAdapter;
import com.shigoo.cashregister.customViews.TableDialogView;
import com.shigoo.cashregister.mvp.contacts.TableContact;
import com.shigoo.cashregister.mvp.presenter.TablePresenter;
import com.xgsb.datafactory.bean.Billbean;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.bean.SettalOrderResultbean;
import com.xgsb.datafactory.bean.Table;
import com.xgsb.datafactory.bean.TableArea;
import com.xgsb.datafactory.bean.TableStatus;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.mvplibrary.MvpFragment;
import com.zx.network.Param;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Name: TableFragment
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-01 14:27
 */
public class TableFragment extends MvpFragment<TablePresenter> implements TableContact.view, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.ordersheet_table_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.ordersheet_table_area_recyclerview)
    RecyclerView mAreaRecyclerView;
    @BindView(R.id.ordersheet_table_search_edite)
    EditText mEdite;
    @BindView(R.id.ordersheet_table_kt_tv)
    TextView mKtText;
    @BindView(R.id.ordersheet_table_kx_tv)
    TextView mKxText;
    @BindView(R.id.ordersheet_table_yd_tv)
    TextView mYdText;
    @BindView(R.id.ordersheet_table_xd_tv)
    TextView mXdText;
    @BindView(R.id.ordersheet_table_jz_tv)
    TextView mJzText;
    @BindView(R.id.ordersheet_table_status_recyclerview)
    RecyclerView mStatusRecyclerView;
    TableBottomListAdapter mBottomAdapter;
    @BindView(R.id.ordersheet_swiprefresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.dialog_root_layout)
    FrameLayout mDialogRootLayout;

    TableListAdapter mAdapter;
    TableAreaListAdapter mAreaAdapter;
    TableDialogView mDialogView;
    private List<TableArea> mAreaList = new ArrayList<>();
    private List<Table> mList = new ArrayList<>();
    private List<Table> mKxList = new ArrayList<>();
    private List<Table> mKtList = new ArrayList<>();
    private List<Table> mYdList = new ArrayList<>();
    private List<Table> mXdList = new ArrayList<>();
    private List<Table> mJzList = new ArrayList<>();

    private List<TableStatus> mStatusList = new ArrayList<>();
    private int[] mColors = {
//            R.color.ordersheet_kx_color,
//            R.color.ordersheet_yd_color,
            R.color.ordersheet_kt_color,
            R.color.ordersheet_xd_color,
            R.color.ordersheet_jz_color};

    public static TableFragment newInstance() {
        TableFragment fragment = new TableFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected TablePresenter onCtreatPresenter() {
        return new TablePresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.ordersheet_table_layout;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        refreshLayout.setOnRefreshListener(this);
        mDialogView = new TableDialogView(getContext(), mDialogRootLayout);
        mAreaAdapter = new TableAreaListAdapter(R.layout.ordersheet_table_area_item_layout, mAreaList);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mAreaRecyclerView.setLayoutManager(manager);
        mAreaRecyclerView.setAdapter(mAreaAdapter);
        mAdapter = new TableListAdapter(R.layout.ordersheet_table_item_layout, mList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Table table = mAdapter.getData().get(position);
                if (table.isAssembleTable()) {
                    Intent intent = new Intent(getActivity(), AssembleTablesActivity.class);
                    intent.setAction(Param.ACTION.DETAIL);
                    intent.putExtra(Param.Keys.TABLE, mAdapter.getData().get(position));
                    startActivity(intent);
                    return;
                }
                table.getBillbean().setRegion_name(table.getRegion_name());
                EventBus.getDefault().post(new EventRouter(EventBusAction.TABLE, table));
            }
        });
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
        mRecyclerView.setAdapter(mAdapter);
        mAreaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mAdapter.getFilter().filter(mAreaAdapter.getData().get(position).getRegion_name());
                mAreaAdapter.setSelected(position);
                mBottomAdapter.setSelected(-1);
            }
        });
        mBottomAdapter = new TableBottomListAdapter(R.layout.ordersheet_table_bottom_item_layout, mStatusList);
        LinearLayoutManager bottomManager = new LinearLayoutManager(getContext());
        bottomManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mStatusRecyclerView.setLayoutManager(bottomManager);
        mStatusRecyclerView.setAdapter(mBottomAdapter);
        mBottomAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!mBottomAdapter.isSelected(position)) {
                    mBottomAdapter.setSelected(position);
                    mAdapter.getFilter().filter(mBottomAdapter.getData().get(position).getStatusName());
                } else {
                    mAdapter.getFilter().filter("");
                    mBottomAdapter.setSelected(-1);
                }
                mAreaAdapter.setSelected(-1);
            }
        });

        mEdite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mAdapter.getFilter().filter(charSequence);
                if(TextUtils.isEmpty(charSequence)){
                    hideInputMethod();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mPresenter.getAreaList(Param.Keys.TOKEN, getToken());
        mPresenter.getTableList(Param.Keys.TOKEN, getToken());
    }

    @Override
    public void onError(String msg) {
        super.onError(msg);
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onTableResult(List<Table> tables) {
        refreshLayout.setRefreshing(false);
        try {
            fillList(tables);
        } catch (Exception e) {
            e.printStackTrace();
            showToast("桌台数据异常");
        }
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

    }

    private void fillList(List<Table> tables) throws Exception {
        mList.clear();
        mJzList.clear();
        mKxList.clear();
        mKtList.clear();
        mYdList.clear();
        mXdList.clear();
        mStatusList.clear();
        for (Table table : tables) {
            //获取当前 应该展示的台
            //--------------------------------------------------------
            Billbean billbean = null;
            if (table.isAssembleTable()) {
                if ("1".equals(table.getUse_tag())) {
                    billbean = table.getMainBill();
                } else {
                    billbean = table.getOrherBill();
                }
            } else if (!table.isKx() && !table.isLocked()) {
                billbean = getMainBill(table);
            }
            if (billbean == null) {
                if (!"1".equals(table.getLock_tag())) {
                    mKxList.add(table);
                    table.setLocal_status("空闲");
                }
            } else {
                table.setLocal_status(billbean.getStatus());
                table.setCurrentBillbean(billbean);
                mList.add(table);
                switch (billbean.getStatus()) {
                    case "已预订":
                        mYdList.add(table);
                        break;
                    case "已开台":
                        mKtList.add(table);
                        break;
                    case "已下单":
                        mXdList.add(table);
                        break;
                    case "已结账":
                        mJzList.add(table);
                        break;
                    case "空闲":
                        mKxList.add(table);
                        break;
                }
            }
        }
        String[] statuName = getResources().getStringArray(R.array.ordersheet_table_status);
        for (int i = 0; i < statuName.length; i++) {
            TableStatus status = new TableStatus();
            status.setColor(mColors[i]);
            status.setStatusName(statuName[i]);
            switch (statuName[i]) {
                case "已预订":
                    status.setNum(mYdList);
                    break;
                case "已开台":
                    status.setNum(mKtList);
                    break;
                case "已下单":
                    status.setNum(mXdList);
                    break;
                case "已结账":
                    status.setNum(mJzList);
                    break;
                case "空闲":
                    status.setNum(mKxList);
                    break;
            }
            mStatusList.add(status);
        }
        mBottomAdapter.setNewData(mStatusList);
        if (mJzList != null && mJzList.size() != 0) {
            mJzText.setText("已结账(" + mJzList.size() + ")");
        }
        if (mXdList != null && mXdList.size() != 0) {
            mXdText.setText("已下单(" + mXdList.size() + ")");
        }
        if (mKtList != null && mKtList.size() != 0) {
            mKtText.setText("已开台(" + mKtList.size() + ")");
        }
        if (mYdList != null && mYdList.size() != 0) {
            mYdText.setText("已预订(" + mYdList.size() + ")");
        }
        if (mKxList != null && mKxList.size() != 0) {
            mKxText.setText("空闲(" + mList.size() + ")");
        }

    }

    private Billbean getMainBill(Table table) {
        for (Billbean billbean : table.getBill()) {
            if ("1".equals(billbean.getBill_tag())) {
                return billbean;
            }
        }
        return null;
    }

    /**
     * 按优先级 获取当前应该展示的 单号
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

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.getTableList(Param.Keys.TOKEN, getToken());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDialogView.onDestory();
    }
}
