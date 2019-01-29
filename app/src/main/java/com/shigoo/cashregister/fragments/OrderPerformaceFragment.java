package com.shigoo.cashregister.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.activitys.RouterActivity;
import com.shigoo.cashregister.mvp.contacts.OrderPerformaceContact;
import com.shigoo.cashregister.mvp.presenter.OrderPerformancePresenter;
import com.xgsb.datafactory.JSONManager;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.bean.Member;
import com.xgsb.datafactory.bean.OrderPerformanceDetailbean;
import com.xgsb.datafactory.bean.OrderPerformancebean;
import com.xgsb.datafactory.bean.SalePerformancebean;
import com.xgsb.datafactory.bean.WebData;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.api.api.utils.AppLog;
import com.zx.api.api.utils.DateUtil;
import com.zx.mvplibrary.MvpFragment;
import com.zx.mvplibrary.web.onOperateLisenter;
import com.zx.mvplibrary.wedgit.WebChartView;
import com.zx.network.Param;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.starteos.dappsdk.Request;

public class OrderPerformaceFragment extends MvpFragment<OrderPerformancePresenter> implements OrderPerformaceContact.view, onOperateLisenter {
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
    private String mAction;
    private Request mRequest;

    private List<OrderPerformancebean> mOrderList = new ArrayList<>();
    private List<OrderPerformanceDetailbean> mDetailbeans = new ArrayList<>();
    String DEFAULT_END_TIME = DateUtil.format(DateUtil.getnowEndTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
    String DEFAULT_START_TIME = DateUtil.format(DateUtil.getStartTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
    private String mStartTime = DEFAULT_START_TIME;
    private String mEndTime = DEFAULT_END_TIME;
    private TimePickerView mTimePicker;
    private boolean hasStartTime;
    private boolean hasEndTime;
    private int page = 1;
    private String mTimeType = "1";
    private String mDishesTag = "2";
    OrderPerformancebean mBeanInfo;

    public static OrderPerformaceFragment newInstance() {
        OrderPerformaceFragment fragment = new OrderPerformaceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onOrderPerformanceListResult(List<OrderPerformancebean> dishesbeans) {
        mOrderList = dishesbeans;
        mWebCahrtView.refresh("refresh");
    }

    @Override
    public void onOrderCount(String number, String money) {
        mTotalNumber.setText("合计：点单数量：" + number + "项");
        mTotalMoney.setText("点单金额：" + money + "元");
    }

    @Override
    public void onOrderPerformanceDetail(List<OrderPerformanceDetailbean> detailbeanList) {
        mDetailbeans = detailbeanList;
        mWebCahrtView.refresh("refresh");
    }

    @Override
    protected OrderPerformancePresenter onCtreatPresenter() {
        return new OrderPerformancePresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.order_performance_fragment;
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

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mPresenter.getOrderPerformanceList(Param.Keys.TOKEN, getToken(),
                Param.Keys.WAITER_ID, getUser().getId() + "",
                Param.Keys.PAGE_NUM, 30 + "",
                Param.Keys.PAGE, page + "",
                Param.Keys.DISH_TAG, mDishesTag,
                Param.Keys.START_DATE, mStartTime,
                Param.Keys.TIME_TYPE, mTimeType,
                Param.Keys.END_DATE, mEndTime);
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

    private void updateTime() {
        if (TextUtils.isEmpty(mStartTime) || TextUtils.isEmpty(mEndTime)) {
            mEndTime = DateUtil.format(DateUtil.getnowEndTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
            mStartTime = DateUtil.format(DateUtil.getStartTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
            mTodayTv.setTextColor(mTimePressColor);
            mTimeType = "1";
        }
        mTimeTv.setText(String.format("%s   ~   %s", mStartTime, mEndTime));
    }

    @Override
    public void initWebview(Request request) {

    }

    @Override
    public void getTableInfo(Request request) {
        mRequest = request;
//        OrderPerformancebean orderPerformancebean = new OrderPerformancebean("威士忌", "000004", "翰格蓝爵", "25", "瓶", "1180", "2", "3600", "54", null, "2");
//        mOrderList.add(orderPerformancebean);
        if (mOrderList != null) {
            AppLog.print(mOrderList.size() + "!#");
            String json = WebData.newInstance().getOrderList(mOrderList, mWebCahrtView.getWidth(), mWebCahrtView.getHight());
            mWebCahrtView.callback(mRequest, json);
        }
    }

    @Override
    public void operateHandle(Request request) {
        String operate = request.getParams().optString("method");
        switch (operate) {
            case "查看详情":
                mBackTv.setVisibility(View.VISIBLE);
                mBeanInfo = (OrderPerformancebean) JSONManager.getInstance().parseObject(request.getParams().opt("row_data") + "", OrderPerformancebean.class);
                if (mBeanInfo != null) {
                    if ("2".equals(mBeanInfo.getDish_tag())) {
                        mPresenter.getOrderPerformanceDetail(Param.Keys.TOKEN, getToken(),
                                Param.Keys.WAITER_ID, getUser().getId() + "",
                                Param.Keys.DISH_TAG, mDishesTag, Param.Keys.DISHE_ID, mBeanInfo.getDishes_id(), Param.Keys.specification_id, mBeanInfo.getSpecification_id());
                    } else {
                        mPresenter.getOrderPerformanceDetail(Param.Keys.TOKEN, getToken(),
                                Param.Keys.WAITER_ID, getUser().getId() + "",
                                Param.Keys.DISH_TAG, mDishesTag, Param.Keys.DISHE_ID, mBeanInfo.getCombo_id());
                    }
                }
                break;
        }
    }

    @Override
    public void searchOperate(Request request) {
        mRequest = request;
        if (mBackTv.getVisibility() != View.VISIBLE) {
            if (mOrderList != null) {
                String json = WebData.newInstance().getOrderList(mOrderList, mWebCahrtView.getWidth(), mWebCahrtView.getHight());
                mWebCahrtView.callback(mRequest, json);
            }
        } else {
            if (mDetailbeans != null) {
                String json = WebData.newInstance().getOrderDetailList(mDetailbeans, mWebCahrtView.getWidth(), mWebCahrtView.getHight());
                mWebCahrtView.callback(mRequest, json);
            }
        }
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

    @OnClick({R.id.ordersheet_time_text, R.id.ordersheet_yesterday_tv, R.id.ordersheet_today_tv, R.id.ordersheet_last_seven_days})
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

    private void getNewData() {
        updateTime();
        mPresenter.getOrderPerformanceList(Param.Keys.TOKEN, getToken(),
                Param.Keys.WAITER_ID, getUser().getId() + "",
                Param.Keys.PAGE_NUM, 30 + "",
                Param.Keys.PAGE, page + "",
                Param.Keys.DISH_TAG, mDishesTag,
                Param.Keys.START_DATE, mStartTime,
                Param.Keys.TIME_TYPE, mTimeType,
                Param.Keys.END_DATE, mEndTime);
    }
}
