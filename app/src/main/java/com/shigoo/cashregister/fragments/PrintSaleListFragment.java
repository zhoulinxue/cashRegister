package com.shigoo.cashregister.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.PopDepartmentDataAdapter;
import com.shigoo.cashregister.adapters.PopDisehesTagAdapter;
import com.shigoo.cashregister.adapters.PopKindDataAdapter;
import com.shigoo.cashregister.adapters.TimeDataPopAdapter;
import com.shigoo.cashregister.mvp.contacts.PrintSaleListContact;
import com.shigoo.cashregister.mvp.presenter.PrintSaleListPresenter;
import com.xgsb.datafactory.bean.Departmentbean;
import com.xgsb.datafactory.bean.DishesKind;
import com.xgsb.datafactory.bean.Paybean;
import com.xgsb.datafactory.bean.Printbean;
import com.xgsb.datafactory.bean.TimeData;
import com.zx.mvplibrary.MvpFragment;
import com.zx.mvplibrary.wedgit.CommonPopupWindow;
import com.zx.network.Param;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrintSaleListFragment extends MvpFragment<PrintSaleListPresenter> implements PrintSaleListContact.view {
    private CommonPopupWindow mTimePop, mKindPop, mDishesTagPop, mDepartmentPop;
    private RecyclerView mTimeRecyclerView, mKindRecyclerView, mDishesTagRecyclerView, mDepartmentRecyclerView;
    private TimeDataPopAdapter mTimeAdapter;
    private PopKindDataAdapter mPopKindAdapter;
    private PopDisehesTagAdapter mPopDishesTagAdapter;
    private PopDepartmentDataAdapter mDepartAdapter;

    @BindView(R.id.ordersheet_time_data_text)
    TextView mTimeDataTv;
    @BindView(R.id.print_dishes_kind)
    TextView mKindTv;
    @BindView(R.id.ordersheet_dishes_tag)
    TextView mDishesTagTv;
    @BindView(R.id.ordersheet_department_tv)
    TextView mDepartmentTv;
    private CommonPopupWindow.LayoutGravity layoutGravity;
    private TimeData mTimeData;
    private DishesKind mKind;
    private Departmentbean mDepartment;
    private int mDishesTag = 2;

    public static PrintSaleListFragment newInstance() {
        PrintSaleListFragment fragment = new PrintSaleListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaleResult(List<Printbean> list) {

    }

    @Override
    public void onTimeListResult(List<TimeData> list) {
        if (list != null && list.size() != 0) {
            mTimeData = list.get(0);
            mTimeDataTv.setText(mTimeData.getName());
            mTimeAdapter.setNewData(list);
        }
    }

    @Override
    public void onDepartmentList(List<Departmentbean> list) {
        if (list != null && list.size() != 0) {
            Departmentbean departmentbean = new Departmentbean();
            departmentbean.setName("全部");
            list.add(0, departmentbean);
            mDepartment = list.get(0);
            mDepartAdapter.setNewData(list);
        }
    }

    @Override
    public void onDishKind(List<DishesKind> list) {
        mKind = new DishesKind();
        mKind.setDrawer("全部");
        list.add(0, mKind);
        mPopKindAdapter.setNewData(list);
    }

    @Override
    protected PrintSaleListPresenter onCtreatPresenter() {
        return new PrintSaleListPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.print_sale_list_fragment;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        initTimePop();
        initKindPop();
        initDishesTagPop();
        initDepartmentPop();
        layoutGravity = new CommonPopupWindow.LayoutGravity(CommonPopupWindow.LayoutGravity.CENTER_HORI | CommonPopupWindow.LayoutGravity.TO_BOTTOM);
    }

    private void initDepartmentPop() {
        mDepartAdapter = new PopDepartmentDataAdapter(R.layout.pop_item_layout, new ArrayList<Departmentbean>());
        mDepartAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mDepartment = mDepartAdapter.getItem(position);
                mDepartmentTv.setText(mDepartment.getName());
                mDepartmentPop.getPopupWindow().dismiss();
            }
        });
        mDepartmentPop = new CommonPopupWindow(getContext(), R.layout.common_pop_layout, 120, ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            protected void initView() {
                mDepartmentRecyclerView = getContentView().findViewById(R.id.pop_recycler_view);
                mDepartmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mDepartmentRecyclerView.setAdapter(mDepartAdapter);
            }

            @Override
            protected void initEvent() {

            }
        };
    }

    private void initDishesTagPop() {
        List<String> taglist = new ArrayList<String>();
        taglist.add("套餐");
        taglist.add("单品");
        mDishesTagTv.setText("单品");
        mPopDishesTagAdapter = new PopDisehesTagAdapter(R.layout.pop_item_layout, taglist);
        mPopDishesTagAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mDishesTag = position + 1;
                mDishesTagTv.setText(mDishesTag == 1 ? "套餐" : "单品");
                mDishesTagPop.getPopupWindow().dismiss();
            }
        });
        mDishesTagPop = new CommonPopupWindow(getContext(), R.layout.common_pop_layout, 80, ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            protected void initView() {
                mDishesTagRecyclerView = getContentView().findViewById(R.id.pop_recycler_view);
                mDishesTagRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mDishesTagRecyclerView.setAdapter(mPopDishesTagAdapter);
            }

            @Override
            protected void initEvent() {

            }
        };
    }

    private void initKindPop() {
        mPopKindAdapter = new PopKindDataAdapter(R.layout.pop_item_layout, new ArrayList<DishesKind>());
        mPopKindAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mKind = mPopKindAdapter.getItem(position);
                mKindTv.setText(mKind.getDrawer());
                mKindPop.getPopupWindow().dismiss();
            }
        });
        mKindPop = new CommonPopupWindow(getContext(), R.layout.common_pop_layout, 100, ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            protected void initView() {
                mKindRecyclerView = getContentView().findViewById(R.id.pop_recycler_view);
                mKindRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mKindRecyclerView.setAdapter(mPopKindAdapter);
            }

            @Override
            protected void initEvent() {

            }
        };
    }

    private void initTimePop() {
        mTimeAdapter = new TimeDataPopAdapter(R.layout.pop_item_layout, new ArrayList<TimeData>());
        mTimeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mTimeData = mTimeAdapter.getItem(position);
                mTimeDataTv.setText(mTimeData.getName());
                mTimePop.getPopupWindow().dismiss();
            }
        });
        mTimePop = new CommonPopupWindow(getContext(), R.layout.common_pop_layout, 80, ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            protected void initView() {
                mTimeRecyclerView = getContentView().findViewById(R.id.pop_recycler_view);
                mTimeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mTimeRecyclerView.setAdapter(mTimeAdapter);
            }

            @Override
            protected void initEvent() {

            }
        };
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mPresenter.getSaleList(Param.Keys.TOKEN, getToken());
        mPresenter.getTimeList(Param.Keys.TOKEN, getToken());
        mPresenter.getDepartment(Param.Keys.TOKEN, getToken());
        mPresenter.getDishKind(Param.Keys.TOKEN, getToken());
    }

    @OnClick({R.id.ordersheet_time_data_text,
            R.id.print_dishes_kind,
            R.id.ordersheet_dishes_tag,
            R.id.ordersheet_department_tv})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ordersheet_time_data_text:
                mTimePop.showBashOfAnchor(mTimeDataTv, layoutGravity, 0, 0);
                break;
            case R.id.print_dishes_kind:
                mKindPop.showBashOfAnchor(mKindTv, layoutGravity, 0, 0);
                break;
            case R.id.ordersheet_dishes_tag:
                mDishesTagPop.showBashOfAnchor(mDishesTagTv, layoutGravity, 0, 0);
                break;
            case R.id.ordersheet_department_tv:
                mDepartmentPop.showBashOfAnchor(mDepartmentTv, layoutGravity, 0, 0);
                break;
        }
    }
}
