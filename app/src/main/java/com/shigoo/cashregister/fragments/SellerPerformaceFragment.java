package com.shigoo.cashregister.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.mvp.contacts.SellerPerformaceContact;
import com.shigoo.cashregister.mvp.presenter.SellerPerformancePresenter;
import com.xgsb.datafactory.JSONManager;
import com.xgsb.datafactory.bean.OrderPerformancebean;
import com.xgsb.datafactory.bean.SalePerformanceDetailbean;
import com.xgsb.datafactory.bean.SalePerformancebean;
import com.xgsb.datafactory.bean.WebData;
import com.zx.api.api.utils.DateUtil;
import com.zx.mvplibrary.MvpFragment;
import com.zx.mvplibrary.web.onOperateLisenter;
import com.zx.mvplibrary.wedgit.WebChartView;
import com.zx.network.Param;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.starteos.dappsdk.Request;

public class SellerPerformaceFragment extends MvpFragment<SellerPerformancePresenter> implements SellerPerformaceContact.view, onOperateLisenter {
    private String mTimeType = "1";
    String DEFAULT_END_TIME = DateUtil.format(DateUtil.getnowEndTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
    String DEFAULT_START_TIME = DateUtil.format(DateUtil.getStartTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
    private String mStartTime = DEFAULT_START_TIME;
    private String mEndTime = DEFAULT_END_TIME;
    private TimePickerView mTimePicker;
    private boolean hasStartTime;
    private boolean hasEndTime;
    @BindView(R.id.ordersheet_chart_webView)
    FrameLayout mWebChartContainer;
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
    @BindView(R.id.back_to_list)
    TextView mBackTv;
    @BindView(R.id.total_number)
    TextView mTotalNumber;
    @BindView(R.id.total_money)
    TextView mTotalMoney;
    private Request mRequest;
    private List<SalePerformancebean> mSaleList = new ArrayList<>();

    public static SellerPerformaceFragment newInstance() {
        SellerPerformaceFragment fragment = new SellerPerformaceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int initLayout() {
        return R.layout.sale_performance_fragment;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        showLoadingDialog();
        mWebCahrtView = new WebChartView(getContext(), mWebChartContainer, this, mHandler);
        mWebCahrtView.loadDefaultUrl(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    dismissLoadingDiaog();
                }
            }
        });
        initTime();
        singleClickOnMinutes(mBackTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBackTv.setVisibility(View.GONE);
                mWebCahrtView.refresh("refresh");
            }
        });
    }

    private void initTime() {
        mTimePicker = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
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
                    getNewData();
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

    private void getNewData() {
        updateTime();
        mPresenter.getSellerPerformanceList(Param.Keys.TOKEN, getToken(), Param.Keys.TIME_TYPE, mTimeType, Param.Keys.START_DATE, mStartTime, Param.Keys.END_DATE, mEndTime);
    }

    private void updateTime() {
        if (TextUtils.isEmpty(mStartTime) || TextUtils.isEmpty(mEndTime)) {
            mEndTime = DateUtil.format(DateUtil.getnowEndTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
            mStartTime = DateUtil.format(DateUtil.getStartTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
            mTodayTv.setTextColor(mTimePressColor);
            mTimeType = "1";
        }
        mTimeTv.setText(String.format("%s   ~   %s", mStartTime, mEndTime));
    }

    @OnClick({R.id.ordersheet_time_text,
            R.id.ordersheet_yesterday_tv,
            R.id.ordersheet_today_tv,
            R.id.ordersheet_last_seven_days})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
                mTimeType = "2";
                getNewData();
                break;
            case R.id.ordersheet_today_tv:
                mStartTime = DEFAULT_START_TIME;
                mEndTime = DEFAULT_END_TIME;
                mTodayTv.setTextColor(mTimePressColor);
                mYesTerdayTv.setTextColor(mTimeNomalc);
                mLast7DaysTv.setTextColor(mTimeNomalc);
                mTimeType = "1";
                getNewData();
                break;
            case R.id.ordersheet_last_seven_days:
                mStartTime = DateUtil.format(DateUtil.getTime(7), DateUtil.YEAR_MONTH_DAY_PATTERN);
                mEndTime = DEFAULT_END_TIME;
                mTodayTv.setTextColor(mTimeNomalc);
                mYesTerdayTv.setTextColor(mTimeNomalc);
                mLast7DaysTv.setTextColor(mTimePressColor);
                mTimeType = "3";
                getNewData();
                break;
        }
    }


    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mPresenter.getSellerPerformanceList(Param.Keys.TOKEN, getToken(), Param.Keys.TIME_TYPE, mTimeType, Param.Keys.START_DATE, mStartTime, Param.Keys.END_DATE, mEndTime);
    }

    @Override
    protected SellerPerformancePresenter onCtreatPresenter() {
        return new SellerPerformancePresenter(this);
    }


    @Override
    public void onSellerPerformanceListResult(List<SalePerformancebean> dishesbeans) {
        mSaleList = dishesbeans;
        mWebCahrtView.refresh("refresh");
    }

    @Override
    public void onSellerCount(String number, String money) {

    }

    @Override
    public void onSellerPerformanceDetail(SalePerformanceDetailbean detailbeanList) {

    }

    @Override
    public void initWebview(Request request) {

    }

    @Override
    public void getTableInfo(Request request) {
        fillList(request);
    }

    private void fillList(Request request) {
        mRequest = request;
        if (mSaleList != null) {
            String json = WebData.newInstance().getSellerDetailList(mSaleList, mWebCahrtView.getWidth(), mWebCahrtView.getHight());
            mWebCahrtView.callback(mRequest, json);
        }
    }

    @Override
    public void operateHandle(Request request) {
        String operate = request.getParams().optString("method");
        switch (operate) {
            case "查看详情":
                SalePerformancebean salePerformancebean = (SalePerformancebean) JSONManager.getInstance().parseObject(request.getParams().opt("row_data") + "", SalePerformancebean.class);
                mPresenter.getSellerPerformanceDetail(Param.Keys.TOKEN, getToken(), Param.Keys.SALE_ID, getUser().getSeller_id(), Param.Keys.BILL_CODE, salePerformancebean.getBill_code());
                break;
        }
    }

    @Override
    public void searchOperate(Request request) {
        fillList(request);
    }

    @Override
    public void currentPage(Request request) {

    }

    @Override
    public void orderDetailsData(Request request) {

    }

    @Override
    public void handoverPrint(Request request) {

    }

    @Override
    public void handDutyHistroyListPrint(Request request) {

    }

    @Override
    public void getPayNumOrderDetailsData(Request request) {

    }
}
