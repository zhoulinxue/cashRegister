package com.shigoo.cashregister.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.R;
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
    private CommonPopupWindow mTimePop,mKindPop;
    private RecyclerView mTimeRecyclerView,mKindRecyclerView;
    private TimeDataPopAdapter mTimeAdapter;
    @BindView(R.id.ordersheet_time_data_text)
    TextView mTimeDataTv;
    private CommonPopupWindow.LayoutGravity layoutGravity;
    private TimeData mTimeData;

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

    }

    @Override
    public void onDishKind(List<DishesKind> list) {

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
        layoutGravity = new CommonPopupWindow.LayoutGravity(CommonPopupWindow.LayoutGravity.CENTER_HORI | CommonPopupWindow.LayoutGravity.TO_BOTTOM);
    }

    private void initKindPop() {

        mKindPop=new CommonPopupWindow(getContext(), R.layout.common_pop_layout, 100, ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            protected void initView() {
                mKindRecyclerView = getContentView().findViewById(R.id.pop_recycler_view);

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
        mTimePop = new CommonPopupWindow(getContext(), R.layout.common_pop_layout, 100, ViewGroup.LayoutParams.WRAP_CONTENT) {
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

    @OnClick({R.id.ordersheet_time_data_text})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ordersheet_time_data_text:
                mTimePop.showBashOfAnchor(mTimeDataTv, layoutGravity, 0, 0);
                break;
        }
    }
}
