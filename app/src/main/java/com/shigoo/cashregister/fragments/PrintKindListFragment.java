package com.shigoo.cashregister.fragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.rmondjone.locktableview.LockTableView;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.mvp.contacts.PrintkindContact;
import com.shigoo.cashregister.mvp.presenter.PrintKindPrensenter;
import com.shigoo.cashregister.utils.ChartUtil;
import com.xgsb.datafactory.bean.KindRecivebean;
import com.zx.api.api.utils.DateUtil;
import com.zx.mvplibrary.MvpFragment;
import com.zx.network.Param;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrintKindListFragment extends MvpFragment<PrintKindPrensenter> implements PrintkindContact.view {
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
    private TimePickerView mTimePicker;
    String DEFAULT_START_TIME = DateUtil.format(DateUtil.getStartTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
    private String mDayTime = DEFAULT_START_TIME;

    public static PrintKindListFragment newInstance() {
        PrintKindListFragment fragment = new PrintKindListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private ArrayList<ArrayList<String>> getTableData(List<KindRecivebean> list) {
        ArrayList<ArrayList<String>> mTableDatas = new ArrayList<ArrayList<String>>();
        ArrayList<String> mfristData = new ArrayList<String>();
        for (int i = 0; i < title.length; i++) {
            mfristData.add(title[i]);
        }
        mTableDatas.add(mfristData);
        for (int i = 0; i < list.size(); i++) {
            KindRecivebean debean = list.get(i);
            ArrayList<String> mRowDatas = new ArrayList<String>();
            for (int j = 0; j < title.length; j++) {
                switch (j) {
                    case 0:
                        mRowDatas.add(debean.getDishes_category_name());
                        break;
                    case 1:
                        mRowDatas.add(debean.getNumber());
                        break;
                    case 2:
                        mRowDatas.add(debean.getMoney());
                        break;
                    case 3:
                        mRowDatas.add(debean.getProportion());
                        break;
                }
            }
            mTableDatas.add(mRowDatas);
        }
        return mTableDatas;
    }

    @Override
    protected int initLayout() {
        return R.layout.print_kind_list_fragment;
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
        mPresenter.getKindReceveList(Param.Keys.TOKEN, getToken(), Param.Keys.BILL_DATE, mTimeTv.getText().toString());
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        title = getResources().getStringArray(R.array.print_kind_list_title);
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
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mPresenter.getKindReceveList(Param.Keys.TOKEN, getToken(), Param.Keys.BILL_DATE, mTimeTv.getText().toString());
    }

    @OnClick({R.id.print_time_tv, R.id.print_list_tv})
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
        }
    }


    @Override
    protected PrintKindPrensenter onCtreatPresenter() {
        return new PrintKindPrensenter(this);
    }

    @Override
    public void onKindList(List<KindRecivebean> list) {
        ArrayList<ArrayList<String>> mList = getTableData(list);
        if (mLockTableView == null) {
            mLockTableView = new LockTableView(getContext(), mChartContainer, mList);
            ChartUtil.setLockTableView(mLockTableView, 240, title);
        } else {
            mLockTableView.setTableDatas(mList);
        }
    }

    @Override
    public void onKindCount(String percent, String money) {
        mTotalMoney.setText("合计:收入金额：" + money + " 元");
        mTotalTv.setText("百分比：" + percent + "%");
    }
}
