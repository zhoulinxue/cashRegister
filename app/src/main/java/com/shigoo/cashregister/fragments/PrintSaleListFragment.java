package com.shigoo.cashregister.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rmondjone.locktableview.DisplayUtil;
import com.rmondjone.locktableview.LockTableView;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.PopDepartmentDataAdapter;
import com.shigoo.cashregister.adapters.PopDisehesTagAdapter;
import com.shigoo.cashregister.adapters.PopKindDataAdapter;
import com.shigoo.cashregister.adapters.TimeDataPopAdapter;
import com.shigoo.cashregister.mvp.contacts.PrintSaleListContact;
import com.shigoo.cashregister.mvp.presenter.PrintSaleListPresenter;
import com.shigoo.cashregister.utils.ChartUtil;
import com.xgsb.datafactory.bean.Departmentbean;
import com.xgsb.datafactory.bean.DishesKind;
import com.xgsb.datafactory.bean.Paybean;
import com.xgsb.datafactory.bean.PrintSaleListbean;
import com.xgsb.datafactory.bean.Printbean;
import com.xgsb.datafactory.bean.Salejlbean;
import com.xgsb.datafactory.bean.TimeData;
import com.zx.api.api.utils.AppLog;
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
    @BindView(R.id.web_chart_layout)
    FrameLayout mChartContainer;
    private CommonPopupWindow.LayoutGravity layoutGravity;
    private TimeData mTimeData;
    private DishesKind mKind;
    private Departmentbean mDepartment;
    private int mDishesTag = 2;
    LockTableView mLockTableView;
    String[] title;
    @BindView(R.id.sale_list_refresh)
    TextView mStartTv;
    @BindView(R.id.print_time_tv)
    TextView mTimeTv;
    @BindView(R.id.total_number)
    TextView mTotalMoney;
    @BindView(R.id.total_money)
    TextView mTotalTv;
    private TimePickerView mTimePicker;

    public static PrintSaleListFragment newInstance() {
        PrintSaleListFragment fragment = new PrintSaleListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaleResult(List<PrintSaleListbean> list) {
        ArrayList<ArrayList<String>> mList = getTableData(list);
        if (mLockTableView == null) {
            mLockTableView = new LockTableView(getContext(), mChartContainer, mList);
            ChartUtil.setLockTableView(mLockTableView, 180, title);
        } else {
            mLockTableView.setTableDatas(mList);
        }
    }

    private ArrayList<ArrayList<String>> getTableData(List<PrintSaleListbean> list) {
        ArrayList<ArrayList<String>> mTableDatas = new ArrayList<ArrayList<String>>();
        ArrayList<String> mfristData = new ArrayList<String>();
        for (int i = 0; i < title.length; i++) {
            mfristData.add(title[i]);
        }
        mTableDatas.add(mfristData);
        float money = 0;
        for (int i = 0; i < list.size(); i++) {
            PrintSaleListbean debean = list.get(i);
            money += AppUtil.getFloatFromString(debean.getMoney()).floatValue();
            ArrayList<String> mRowDatas = new ArrayList<String>();
            for (int j = 0; j < title.length; j++) {
                switch (j) {
                    case 0:
                        mRowDatas.add(debean.getDish_number());
                        break;
                    case 1:
                        mRowDatas.add(debean.getDish_name());
                        break;
                    case 2:
                        mRowDatas.add(debean.getSpecification());
                        break;
                    case 3:
                        mRowDatas.add(debean.getNumber());
                        break;
                    case 4:
                        mRowDatas.add(debean.getMoney());
                        break;
                }
            }
            mTableDatas.add(mRowDatas);
        }
        mTotalMoney.setText("合计:收入金额：" + money+" 元");
        mTotalTv.setText("数量：" + list.size()+"项");
        return mTableDatas;
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

    String DEFAULT_START_TIME = DateUtil.format(DateUtil.getStartTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
    private String mDayTime = DEFAULT_START_TIME;

    private void initTime() {
        mTimePicker = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                mDayTime = DateUtil.format(date, DateUtil.YEAR_MONTH_DAY_PATTERN);
                mTimeTv.setText(mDayTime);
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
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        title = getResources().getStringArray(R.array.print_sale_list_title);
        initTimePop();
        initKindPop();
        initDishesTagPop();
        initDepartmentPop();
        layoutGravity = new CommonPopupWindow.LayoutGravity(CommonPopupWindow.LayoutGravity.CENTER_HORI | CommonPopupWindow.LayoutGravity.TO_BOTTOM);
        singleClickOnMinutes(mStartTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kindId = "";
                String departId = "";
                if (mKind != null && !TextUtils.isEmpty(mKind.getDrawer_id())) {
                    kindId = mKind.getDrawer_id() + "";
                }
                if (mDepartment != null && mDishesTag == 2) {
                    departId = mDepartment.getId() + "";
                }
                getSaleList(mDishesTag, kindId, departId);
            }
        });
        singleClickOnMinutes(mTimeTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar date = Calendar.getInstance();
                date.setTime(DateUtil.parse(mDayTime, DateUtil.YEAR_MONTH_DAY_PATTERN));
                mTimePicker.setTitleText("请选择时间");
                mTimePicker.setDate(date);
                mTimePicker.show();
            }
        });
        initTime();
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
        mPresenter.getTimeList(Param.Keys.TOKEN, getToken());
        mPresenter.getDepartment(Param.Keys.TOKEN, getToken());
        mPresenter.getDishKind(Param.Keys.TOKEN, getToken());
        getSaleList(mDishesTag, "", "");
    }

    private void getSaleList(int mDishesTag, String cId, String mId) {
        mPresenter.getSaleList(Param.Keys.TOKEN, getToken(), Param.Keys.DISH_TAG, mDishesTag + "", Param.Keys.CATEGRAY_ID, cId, Param.Keys.DRAWER, mId);
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
