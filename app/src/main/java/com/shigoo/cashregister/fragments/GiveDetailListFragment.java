package com.shigoo.cashregister.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.mvp.contacts.GiveDetailListContact;
import com.shigoo.cashregister.mvp.presenter.GiveDetailListPresenter;
import com.xgsb.datafactory.bean.GiveDetailListbean;
import com.xgsb.datafactory.bean.WebData;
import com.zx.api.api.utils.AppUtil;
import com.zx.api.api.utils.DateUtil;
import com.zx.mvplibrary.MvpFragment;
import com.zx.mvplibrary.web.onOperateLisenter;
import com.zx.mvplibrary.wedgit.WebChartView;
import com.zx.network.Param;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.starteos.dappsdk.Request;

public class GiveDetailListFragment extends MvpFragment<GiveDetailListPresenter> implements GiveDetailListContact.view, onOperateLisenter {
    @BindView(R.id.ordersheet_chart_webView)
    FrameLayout mWebChartContainer;
    WebChartView mWebCahrtView;
    @BindView(R.id.ordersheet_time_text)
    TextView mTimeTv;
    @BindView(R.id.ordersheet_get_give_detail)
    TextView mDetailTv;
    @BindView(R.id.ordersheet_item_count_tv)
    TextView mItemCount;
    @BindView(R.id.ordersheet_money_count)
    TextView mMoneyCount;
    private Request mRequest;

    String DEFAULT_END_TIME = DateUtil.format(DateUtil.getnowEndTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
    String DEFAULT_START_TIME = DateUtil.format(DateUtil.getStartTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
    private String mStartTime = DEFAULT_START_TIME;
    private String mEndTime = DEFAULT_END_TIME;
    private TimePickerView mTimePicker;
    private boolean hasStartTime;
    private boolean hasEndTime;

    public static GiveDetailListFragment newInstance() {
        GiveDetailListFragment fragment = new GiveDetailListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected GiveDetailListPresenter onCtreatPresenter() {
        return new GiveDetailListPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.ordersheet_detail_list_fragment;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        mWebCahrtView = new WebChartView(getContext(), mWebChartContainer, this, mHandler);
        mWebCahrtView.loadDefaultUrl();
        initTime();
        singleClickOnMinutes(mTimeTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });

        singleClickOnMinutes(mDetailTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebCahrtView.refresh("refresh");
            }
        });
    }

    private void getList() {
        mPresenter.getGiveDetailList(Param.Keys.TOKEN, getToken(), Param.Keys.STAFF_ID,getUser().getStaff_id(), Param.Keys.PAGE_NUM, "10000", Param.Keys.START_DATE, mStartTime, Param.Keys.END_DATE, mEndTime);
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        getList();
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
            }
        }).setType(new boolean[]{true, true, true, false, false, false}).isDialog(true).build();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(500, 400,
                Gravity.CENTER_VERTICAL);
        params.leftMargin = 0;
        params.rightMargin = 0;
        mTimePicker.getDialogContainerLayout().setLayoutParams(params);
    }


    private void updateTime() {
        if (TextUtils.isEmpty(mStartTime) || TextUtils.isEmpty(mEndTime)) {
            mEndTime = DateUtil.format(DateUtil.getnowEndTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
            mStartTime = DateUtil.format(DateUtil.getStartTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
        }
        mTimeTv.setText(String.format("%s   ~   %s", mStartTime, mEndTime));
    }

    @Override
    public void initWebview(Request request) {

    }

    @Override
    public void getTableInfo(Request request) {
        mRequest = request;
    }

    @Override
    public void operateHandle(Request request) {

    }

    @Override
    public void searchOperate(Request request) {
        mRequest = request;
        getList();
    }

    @Override
    public void currentPage(Request request) {

    }

    @Override
    public void orderDetailsData(Request request) {

    }


    @Override
    public void onGiveDetailListResult(List<GiveDetailListbean> giveDetailListbeans) {
        if(giveDetailListbeans==null||giveDetailListbeans.size()==0){
            showToast("暂无数据");
            return;
        }
        float total = 0;
        for (GiveDetailListbean detailListbean : giveDetailListbeans) {
            if (!TextUtils.isEmpty(detailListbean.getSum_giving_money())) {
                total += AppUtil.getFloatFromString(detailListbean.getSum_giving_money()).floatValue();
            }
        }
        mItemCount.setText("赠送数量:" + giveDetailListbeans.size() + "项");
        mMoneyCount.setText("消费金额:" + total + "元");
        if (giveDetailListbeans != null && giveDetailListbeans.size() != 0) {
            String json = WebData.newInstance().getGiveDetail(giveDetailListbeans, mWebCahrtView.getWidth(), mWebCahrtView.getHight());
            mWebCahrtView.callback(mRequest, json);
        }
    }
}
