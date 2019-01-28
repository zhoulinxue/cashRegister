package com.shigoo.cashregister.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shigoo.cashregister.R;
import com.shigoo.cashregister.activitys.FanJZActivity;
import com.shigoo.cashregister.mvp.contacts.OrderPayDetailContact;
import com.shigoo.cashregister.mvp.presenter.OrderPayDetailPresenter;
import com.xgsb.datafactory.JSONManager;
import com.xgsb.datafactory.bean.Billbean;
import com.xgsb.datafactory.bean.OrderPayDetailbean;
import com.xgsb.datafactory.bean.Paybean;
import com.xgsb.datafactory.bean.Table;
import com.zx.api.api.utils.AppLog;
import com.zx.mvplibrary.MvpFragment;
import com.zx.mvplibrary.web.InitWebView;
import com.zx.mvplibrary.web.onOperateLisenter;
import com.zx.mvplibrary.wedgit.WebChartView;
import com.zx.network.Param;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.starteos.dappsdk.Request;

public class OrderPayDetailFragment extends MvpFragment<OrderPayDetailPresenter> implements OrderPayDetailContact.view, onOperateLisenter {
    @BindView(R.id.web_chart_layout)
    FrameLayout mWebContainer;
    @BindView(R.id.fan_jie_zhang_tv)
    TextView mFjzTv;
    WebChartView webChartView;
    private Table mTable;
    Paybean mPaybean;
    private OrderPayDetailbean mOrderPayDetailbean;

    public static OrderPayDetailFragment newInstance() {
        OrderPayDetailFragment fragment = new OrderPayDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.order_pay_fragment_layout;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        showLoadingDialog();
        webChartView = new WebChartView(getContext(), mWebContainer, this, mHandler);
        webChartView.loadLocalDefaultUrl(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    dismissLoadingDiaog();
                }
            }
        });
        singleClickOnMinutes(mFjzTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FanJZActivity.class);
                intent.putExtra(Param.Keys.TABLE, mTable);
                intent.putExtra(Param.Keys.PAY_INFO, mPaybean);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {

    }

    @Override
    protected OrderPayDetailPresenter onCtreatPresenter() {
        return new OrderPayDetailPresenter(this);
    }

    @Override
    public void initWebview(Request request) {
//        webChartView.getDataFormWeb("tablePayNumberOrderDetails", "rePage");
        InitWebView initWebView = new InitWebView(getToken(), "tablePayNumberOrderDetails");
        webChartView.callback(request, JSONManager.getInstance().toJson(initWebView));
    }

    @Override
    public void getTableInfo(Request request) {

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

    @Override
    public void handoverPrint(Request request) {

    }

    @Override
    public void handDutyHistroyListPrint(Request request) {

    }

    @Override
    public void getPayNumOrderDetailsData(Request request) {
        if (mOrderPayDetailbean != null) {
            String json = JSONManager.getInstance().toJson(mOrderPayDetailbean);
            webChartView.callback(request, json);
        }
    }

    @Override
    public void onPayDetailResualt(OrderPayDetailbean orderPayDetailbean) {
        this.mOrderPayDetailbean = orderPayDetailbean;
        webChartView.getDataFormWeb("tablePayNumberOrderDetails", "rePage");
    }

    public void setPaybean(Table table, Paybean paybean) {
        this.mTable = table;
        this.mPaybean = paybean;
        mPresenter.getOrderPayDetal(Param.Keys.TOKEN, getToken(), Param.Keys.BILL_CODE, mPaybean.getBillCode(), Param.Keys.PAY_NUM, mPaybean.getPay_num() + "", Param.Keys.PAY_TAG, mPaybean.getPay_tag() + "");
    }
}
