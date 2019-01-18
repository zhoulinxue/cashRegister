package com.shigoo.cashregister.activitys;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.mvp.contacts.SalesPerformanceContact;
import com.shigoo.cashregister.mvp.presenter.SalesPerformancePresenter;
import com.xgsb.datafactory.JSONManager;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.OrderPerformancebean;
import com.xgsb.datafactory.bean.SalePerformancebean;
import com.xgsb.datafactory.bean.WebData;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.api.api.utils.DateUtil;
import com.zx.mvplibrary.MvpActivity;
import com.zx.mvplibrary.web.InitWebView;
import com.zx.mvplibrary.web.onOperateLisenter;
import com.zx.mvplibrary.wedgit.WebChartView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.starteos.dappsdk.Request;

public class PerformanceActivity extends MvpActivity<SalesPerformancePresenter> implements SalesPerformanceContact.view, onOperateLisenter {
    @BindView(R.id.ordersheet_chart_webView)
    FrameLayout mWebChartContainer;
    @BindView(R.id.ordersheet_logo_title_tv)
    TextView mTitleTv;
    @BindView(R.id.ordersheet_logo_name_tv)
    TextView mNameTv;
    @BindView(R.id.ordersheet_back_img)
    ImageView mBackImg;
    @BindView(R.id.ordersheet_today_tv)
    TextView mTodayTv;
    @BindView(R.id.ordersheet_yesterday_tv)
    TextView mYesTerdayTv;
    @BindView(R.id.ordersheet_last_seven_days)
    TextView mLast7DaysTv;
    @BindView(R.id.ordersheet_time_text)
    TextView mTimeTv;
    WebChartView mWebCahrtView;
    @BindColor(R.color.ordersheet_table_des_color)
    int mTimeNomalc;
    @BindColor(R.color.ordersheet_colorAccent)
    int mTimePressColor;
    private String mAction;
    private Request mRequest;

    private List<SalePerformancebean> mSaleList = new ArrayList<>();
    private List<OrderPerformancebean> mOrderList = new ArrayList<>();
    String DEFAULT_END_TIME = DateUtil.format(DateUtil.getnowEndTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
    String DEFAULT_START_TIME = DateUtil.format(DateUtil.getStartTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
    private String mStartTime = DEFAULT_START_TIME;
    private String mEndTime = DEFAULT_END_TIME;
    private TimePickerView mTimePicker;
    private boolean hasStartTime;
    private boolean hasEndTime;

    @Override
    protected SalesPerformancePresenter onCtreatPresenter() {
        return new SalesPerformancePresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.ordersheet_salesperformance_activity;
    }

    @Override
    protected void onCreateView() {
        ButterKnife.bind(this);
        mWebCahrtView = new WebChartView(this, mWebChartContainer, this, mHandler);
        mWebCahrtView.loadDefaultUrl();
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mSaleList.add(new SalePerformancebean("2018-10-12", "0513212544", "王思聪", "vip103", "1015.5", "15", "1000", "0.5"));
        mSaleList.add(new SalePerformancebean("2018-10-13", "0513212544", "思聪", "vip104", "1015.5", "15", "1000", "0.5"));
        mSaleList.add(new SalePerformancebean("2018-10-14", "0513212544", "王聪", "vip105", "1015.5", "15", "1000", "0.5"));
        mSaleList.add(new SalePerformancebean("2018-10-15", "0513212544", "王思", "vip106", "1015.5", "15", "1000", "0.5"));
        mSaleList.add(new SalePerformancebean("2018-10-16", "0513212544", "聪", "vip107", "1015.5", "15", "1000", "0.5"));
        mSaleList.add(new SalePerformancebean("2018-10-17", "0513212544", "王聪", "vip108", "1015.5", "15", "1000", "0.5"));

        mOrderList.add(new OrderPerformancebean("小吃", "0513212544", "红糖糍粑", "份", "10", "100"));
        mOrderList.add(new OrderPerformancebean("酒", "0513212544", "红糖", "份", "5", "150"));
        mOrderList.add(new OrderPerformancebean("套餐", "0513212544", "糖糍粑", "份", "1", "1500"));
        mOrderList.add(new OrderPerformancebean("套餐", "0513212544", "粑", "份", "2", "1500"));
        mOrderList.add(new OrderPerformancebean("酒", "0513212544", "红粑", "份", "3", "1500"));
        mOrderList.add(new OrderPerformancebean("小吃", "0513212544", "糖糍", "份", "4", "1500"));

//        mAction = getIntent().getAction();
//        mAction = EventBusAction.SALE.getAction();
        mAction = EventBusAction.ORDER.getAction();
        if (EventBusAction.SALE.getAction().equals(mAction)) {
            mPresenter.getSalePerformanceList();
        } else if (EventBusAction.ORDER.getAction().equals(mAction)) {
            mPresenter.getOrderPerformanceList();
        }
        initTime();
    }

    private void initTime() {
        mTimePicker = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (!hasStartTime) {
                    hasStartTime = true;
                    mStartTime = DateUtil.format(date, DateUtil.YEAR_MONTH_DAY_PATTERN);
                } else if (hasStartTime && !hasEndTime) {
                    hasEndTime = true;
                    mEndTime = DateUtil.format(date, DateUtil.YEAR_MONTH_DAY_PATTERN);
                }
                updateTime();
                if (hasEndTime && hasStartTime) {
                    mTodayTv.setTextColor(mTimeNomalc);
                    mYesTerdayTv.setTextColor(mTimeNomalc);
                    mLast7DaysTv.setTextColor(mTimeNomalc);
                }
            }
        }).setType(new boolean[]{true, true, true, false, false, false}).isDialog(true).build();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(500, 400,
                Gravity.CENTER_VERTICAL);
        params.leftMargin = 0;
        params.rightMargin = 0;
        mTimePicker.getDialogContainerLayout().setLayoutParams(params);
        mTodayTv.setTextColor(mTimePressColor);
    }


    @Override
    public void initWebview(Request request) {
        InitWebView initWebView = new InitWebView(getToken(), "order");
        mWebCahrtView.callback(request, JSONManager.getInstance().toJson(initWebView));
    }

    @Override
    public void getTableInfo(Request request) {
        mRequest = request;
        showToast("onGettableInfo");
        if (EventBusAction.SALE.getAction().equals(mAction)) {
            if (mSaleList != null) {
                String json = WebData.newInstance().getSaleList(mSaleList, mWebCahrtView.getWidth(), mWebCahrtView.getHight());
                mWebCahrtView.callback(mRequest, json);
            }
        } else if (EventBusAction.ORDER.getAction().equals(mAction)) {
            if (mOrderList != null) {
                String json = WebData.newInstance().getOrderList(mOrderList, mWebCahrtView.getWidth(), mWebCahrtView.getHight());
                mWebCahrtView.callback(mRequest, json);
            }
        }
    }

    @Override
    public void operateHandle(Request request) {

    }

    @Override
    public void searchOperate(Request request) {

    }

    @Override
    public void currentPage(Request request) {

    }

    @Override
    public void orderDetailsData(Request request) {

    }


    @OnClick({R.id.ordersheet_back_img, R.id.ordersheet_time_text, R.id.ordersheet_yesterday_tv, R.id.ordersheet_today_tv, R.id.ordersheet_last_seven_days})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ordersheet_back_img:
                finish();
                break;
            case R.id.ordersheet_time_text:
                if (!hasStartTime || (hasEndTime && hasEndTime)) {
                    if ((hasEndTime && hasEndTime)) {
                        hasStartTime = false;

                        hasEndTime = false;
                    }
                    Calendar date = Calendar.getInstance();
                    date.setTime(DateUtil.parse(mStartTime, DateUtil.YEAR_MONTH_DAY_PATTERN));
                    mTimePicker.setTitleText("设置开始时间");
                } else if (!hasEndTime) {
                    mTimePicker.setTitleText("设置结束时间");
                    Calendar date = null;
                    if (mEndTime.equals(DEFAULT_END_TIME)) {
                        date = Calendar.getInstance();
                        date.setTime(DateUtil.parse(mStartTime, DateUtil.YEAR_MONTH_DAY_PATTERN));
                    } else {
                        date = Calendar.getInstance();
                        date.setTime(DateUtil.parse(mEndTime, DateUtil.YEAR_MONTH_DAY_PATTERN));
                    }
                    mTimePicker.setDate(date);
                }
                mTimePicker.show();
                break;
            case R.id.ordersheet_yesterday_tv:
                mStartTime = DateUtil.format(DateUtil.getTime(1), DateUtil.YEAR_MONTH_DAY_PATTERN);
                mEndTime = DEFAULT_START_TIME;
                mTodayTv.setTextColor(mTimeNomalc);
                mYesTerdayTv.setTextColor(mTimePressColor);
                mLast7DaysTv.setTextColor(mTimeNomalc);
                updateTime();
                break;
            case R.id.ordersheet_today_tv:
                mStartTime = DEFAULT_START_TIME;
                mEndTime = DEFAULT_END_TIME;
                mTodayTv.setTextColor(mTimePressColor);
                mYesTerdayTv.setTextColor(mTimeNomalc);
                mLast7DaysTv.setTextColor(mTimeNomalc);
                updateTime();
                break;
            case R.id.ordersheet_last_seven_days:
                mStartTime = DateUtil.format(DateUtil.getTime(7), DateUtil.YEAR_MONTH_DAY_PATTERN);
                mEndTime = DEFAULT_END_TIME;
                mTodayTv.setTextColor(mTimeNomalc);
                mYesTerdayTv.setTextColor(mTimeNomalc);
                mLast7DaysTv.setTextColor(mTimePressColor);
                updateTime();
                break;
        }
    }

    private void updateTime() {
        if (TextUtils.isEmpty(mStartTime) || TextUtils.isEmpty(mEndTime)) {
            mEndTime = DateUtil.format(DateUtil.getnowEndTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
            mStartTime = DateUtil.format(DateUtil.getStartTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
            mTodayTv.setTextColor(mTimePressColor);
        }
        mTimeTv.setText(String.format("%s   ~   %s", mStartTime, mEndTime));
    }

    @Override
    public void onSalePerformanceListResult(List<SalePerformancebean> dishesbeans) {

    }

    @Override
    public void onOrderPerformanceListResult(List<Dishesbean> dishesbeans) {

    }
}
