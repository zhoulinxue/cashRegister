package com.shigoo.cashregister.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rmondjone.locktableview.LockTableView;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.PopDisehesTagAdapter;
import com.shigoo.cashregister.adapters.PopTableAreaAdapter;
import com.shigoo.cashregister.adapters.TimeDataPopAdapter;
import com.shigoo.cashregister.mvp.contacts.PrintSaleDetailContact;
import com.shigoo.cashregister.mvp.contacts.PrintTableConsumContact;
import com.shigoo.cashregister.mvp.presenter.PrintSaleDetailPresenter;
import com.shigoo.cashregister.mvp.presenter.PrintTableConsumePresenter;
import com.shigoo.cashregister.utils.ChartUtil;
import com.xgsb.datafactory.bean.PrintTableConsumebean;
import com.xgsb.datafactory.bean.SaleDetailbean;
import com.xgsb.datafactory.bean.TableArea;
import com.xgsb.datafactory.bean.TimeData;
import com.zx.api.api.utils.AppUtil;
import com.zx.api.api.utils.DateUtil;
import com.zx.mvplibrary.MvpFragment;
import com.zx.mvplibrary.wedgit.CommonPopupWindow;
import com.zx.network.Param;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrintTableConsumeListFragment extends MvpFragment<PrintTableConsumePresenter> implements PrintTableConsumContact.view {
    @BindView(R.id.web_chart_layout)
    FrameLayout mChartContainer;
    LockTableView mLockTableView;
    String[] title;
    @BindView(R.id.print_time_tv)
    TextView mTimeTv;
    @BindView(R.id.total_number)
    TextView mTotalMoney;
    @BindView(R.id.total_money)
    TextView mTotalTv;
    @BindView(R.id.ordersheet_table_area)
    TextView mTableAreaTv;
    private TimePickerView mTimePicker;
    String DEFAULT_START_TIME = DateUtil.format(DateUtil.getStartTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
    private String mDayTime = DEFAULT_START_TIME;
    private CommonPopupWindow.LayoutGravity layoutGravity;
    private CommonPopupWindow mAreaPop;
    private PopTableAreaAdapter mAreaAdapter;
    private RecyclerView mAreaRecyclerView;
    private TableArea mTableArea;

    public static PrintTableConsumeListFragment newInstance() {
        PrintTableConsumeListFragment fragment = new PrintTableConsumeListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private ArrayList<ArrayList<String>> getTableData(List<PrintTableConsumebean> list) {
        ArrayList<ArrayList<String>> mTableDatas = new ArrayList<ArrayList<String>>();
        ArrayList<String> mfristData = new ArrayList<String>();
        for (int i = 0; i < title.length; i++) {
            mfristData.add(title[i]);
        }
        mTableDatas.add(mfristData);
        float money = 0;
        float not_money=0;
        int number = 0;
        int notNumber = 0;
        for (int i = 0; i < list.size(); i++) {
            PrintTableConsumebean debean = list.get(i);
            number += Integer.valueOf(debean.getNumber());
            notNumber += Integer.valueOf(debean.getNot_number());
            money += AppUtil.getFloatFromString(debean.getMoney()).floatValue();
            not_money+=AppUtil.getFloatFromString(debean.getMoney()).floatValue();
            ArrayList<String> mRowDatas = new ArrayList<String>();
            for (int j = 0; j < title.length; j++) {
                switch (j) {
                    case 0:
                        mRowDatas.add(debean.getRegion_name());
                        break;
                    case 1:
                        mRowDatas.add(debean.getTable_number());
                        break;
                    case 2:
                        mRowDatas.add(debean.getNumber());
                        break;
                    case 3:
                        mRowDatas.add(debean.getMoney());
                        break;
                    case 4:
                        mRowDatas.add(debean.getNot_number());
                        break;
                    case 5:
                        mRowDatas.add(debean.getNot_money());
                        break;
                    case 6:
                        mRowDatas.add(debean.getCount_number());
                        break;
                    case 7:
                        mRowDatas.add(debean.getCount_money());
                        break;
                }
            }
            mTableDatas.add(mRowDatas);
        }
        mTotalMoney.setText("合计:已结账人数：" + number + " 人  金额：" + money + " 元 未结账人数：" + notNumber + " 人   未结账金额：" +not_money+" 元");
        mTotalTv.setText("印单已结账人数：" + number + "人 金额："+money);
        return mTableDatas;
    }

    @Override
    protected int initLayout() {
        return R.layout.print_table_consume_list_fragment;
    }

    private void initAreaPop() {
        mAreaAdapter = new PopTableAreaAdapter(R.layout.pop_item_layout, new ArrayList<TableArea>());
        mAreaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mTableArea = mAreaAdapter.getItem(position);
                mTableAreaTv.setText(mTableArea.getRegion_name());
                mAreaPop.getPopupWindow().dismiss();
            }
        });
        mAreaPop = new CommonPopupWindow(getContext(), R.layout.common_pop_layout, 100, ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            protected void initView() {
                mAreaRecyclerView = getContentView().findViewById(R.id.pop_recycler_view);
                mAreaRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                mAreaRecyclerView.setAdapter(mAreaAdapter);
            }

            @Override
            protected void initEvent() {

            }
        };
    }

    private void initTime() {
        mTimePicker = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                mDayTime = DateUtil.format(date, DateUtil.YEAR_MONTH_DAY_PATTERN);
                mTimeTv.setText(mDayTime);
                onRefresh();
            }
        }).setType(new boolean[]{true, true, true, false, false, false}).isDialog(true).build();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(500, 400,
                Gravity.CENTER_VERTICAL);
        params.leftMargin = 0;
        params.rightMargin = 0;
        mTimePicker.getDialogContainerLayout().setLayoutParams(params);
        mTimeTv.setText(mDayTime);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.getTableConsumeList(Param.Keys.TOKEN, getToken(), Param.Keys.REGIN_ID, mTableArea.getId() + "");
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        title = getResources().getStringArray(R.array.print_table_consum_title);
        singleClickOnMinutes(mTimeTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar date = Calendar.getInstance();
                date.setTime(DateUtil.parse(mDayTime, DateUtil.YEAR_MONTH_DAY_PATTERN));
                mTimePicker.setTitleText("设置开始时间");
                mTimePicker.setDate(date);
                mTimePicker.show();
            }
        });
        initAreaPop();
        initTime();
        layoutGravity = new CommonPopupWindow.LayoutGravity(CommonPopupWindow.LayoutGravity.CENTER_HORI | CommonPopupWindow.LayoutGravity.TO_BOTTOM);
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mPresenter.getTableAreaList(Param.Keys.TOKEN, getToken());
        mPresenter.getTableConsumeList(Param.Keys.TOKEN, getToken());
    }


    @OnClick({R.id.print_time_tv,
            R.id.print_list_tv,
            R.id.sale_list_refresh,
            R.id.ordersheet_table_area,})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.print_time_tv:
                Calendar date = Calendar.getInstance();
                date.setTime(DateUtil.parse(mDayTime, DateUtil.YEAR_MONTH_DAY_PATTERN));
                mTimePicker.setTitleText("请选择时间");
                mTimePicker.setDate(date);
                mTimePicker.show();
                break;
            case R.id.print_list_tv:
                showToast("打印报表");
                break;
            case R.id.sale_list_refresh:
                onRefresh();
                break;
            case R.id.ordersheet_table_area:
                mAreaPop.showBashOfAnchor(mTableAreaTv, layoutGravity, 0, 0);
                break;

        }
    }


    @Override
    public void onTableConsumeResult(List<PrintTableConsumebean> list) {
        ArrayList<ArrayList<String>> mList = getTableData(list);
        if (mLockTableView == null) {
            mLockTableView = new LockTableView(getContext(), mChartContainer, mList);
            ChartUtil.setLockTableView(mLockTableView, 88, title);
        } else {
            mLockTableView.setTableDatas(mList);
        }
    }

    @Override
    public void onAreaListResult(List<TableArea> list) {
        list.add(0, new TableArea(0, "全部区域"));
        mTableAreaTv.setText("全部区域");
        mAreaAdapter.setNewData(list);
    }

    @Override
    protected PrintTableConsumePresenter onCtreatPresenter() {
        return new PrintTableConsumePresenter(this);
    }
}
