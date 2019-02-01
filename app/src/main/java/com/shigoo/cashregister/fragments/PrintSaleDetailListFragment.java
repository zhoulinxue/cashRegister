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
import com.shigoo.cashregister.mvp.contacts.PrintSaleDetailContact;
import com.shigoo.cashregister.mvp.contacts.PrintSaleListContact;
import com.shigoo.cashregister.mvp.contacts.PrintkindContact;
import com.shigoo.cashregister.mvp.presenter.PrintKindPrensenter;
import com.shigoo.cashregister.mvp.presenter.PrintSaleDetailPresenter;
import com.shigoo.cashregister.utils.ChartUtil;
import com.xgsb.datafactory.bean.Departmentbean;
import com.xgsb.datafactory.bean.DishesKind;
import com.xgsb.datafactory.bean.KindRecivebean;
import com.xgsb.datafactory.bean.PrintSaleListbean;
import com.xgsb.datafactory.bean.SaleDetailbean;
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

public class PrintSaleDetailListFragment extends MvpFragment<PrintSaleDetailPresenter> implements PrintSaleDetailContact.view {
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
    @BindView(R.id.ordersheet_dishes_tag)
    TextView mDishesTagTv;
    private TimePickerView mTimePicker;
    String DEFAULT_START_TIME = DateUtil.format(DateUtil.getStartTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
    private String mDayTime = DEFAULT_START_TIME;
    private CommonPopupWindow mDishesTagPop;
    private CommonPopupWindow.LayoutGravity layoutGravity;
    private PopDisehesTagAdapter mPopDishesTagAdapter;
    private int mDishesTag = 2;
    private RecyclerView mDishesTagRecyclerView;

    public static PrintSaleDetailListFragment newInstance() {
        PrintSaleDetailListFragment fragment = new PrintSaleDetailListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private ArrayList<ArrayList<String>> getTableData(List<SaleDetailbean> list) {
        ArrayList<ArrayList<String>> mTableDatas = new ArrayList<ArrayList<String>>();
        ArrayList<String> mfristData = new ArrayList<String>();
        for (int i = 0; i < title.length; i++) {
            mfristData.add(title[i]);
        }
        mTableDatas.add(mfristData);
        float money = 0;
        for (int i = 0; i < list.size(); i++) {
            SaleDetailbean debean = list.get(i);
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
                        mRowDatas.add(debean.getMoney());
                        break;
                    case 3:
                        mRowDatas.add(debean.getNumber());
                        break;
                }
            }
            mTableDatas.add(mRowDatas);
        }
        mTotalMoney.setText("合计:收入金额：" + money + " 元");
        mTotalTv.setText("数量：" + list.size() + "项");
        return mTableDatas;
    }

    @Override
    protected int initLayout() {
        return R.layout.print_sale_detail_list_fragment;
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
        mPresenter.getSaleDetailList(Param.Keys.TOKEN, getToken(), Param.Keys.DISH_TAG, mDishesTag + "");
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        title = getResources().getStringArray(R.array.print_sale_detail_list_title);
        initDishesTagPop();
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
        initTime();
        layoutGravity = new CommonPopupWindow.LayoutGravity(CommonPopupWindow.LayoutGravity.CENTER_HORI | CommonPopupWindow.LayoutGravity.TO_BOTTOM);
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mPresenter.getSaleDetailList(Param.Keys.TOKEN, getToken(), Param.Keys.DISH_TAG, mDishesTag + "");
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

    @OnClick({R.id.print_time_tv,
            R.id.print_list_tv,
            R.id.sale_list_refresh,
            R.id.ordersheet_dishes_tag,})
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
            case R.id.ordersheet_dishes_tag:
                mDishesTagPop.showBashOfAnchor(mDishesTagTv, layoutGravity, 0, 0);
                break;

        }
    }

    @Override
    protected PrintSaleDetailPresenter onCtreatPresenter() {
        return new PrintSaleDetailPresenter(this);
    }

    @Override
    public void onSaleDetailList(List<SaleDetailbean> list) {
        ArrayList<ArrayList<String>> mList = getTableData(list);
        if (mLockTableView == null) {
            mLockTableView = new LockTableView(getContext(), mChartContainer, mList);
            ChartUtil.setLockTableView(mLockTableView, 240, title);
        } else {
            mLockTableView.setTableDatas(mList);
        }
    }
}
