package com.shigoo.cashregister.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.shigoo.cashregister.R;
import com.xgsb.datafactory.JSONManager;
import com.zx.api.api.utils.AppLog;
import com.zx.api.api.utils.SPUtil;
import com.zx.mvplibrary.BaseFragment;
import com.zx.mvplibrary.web.InitWebView;
import com.zx.mvplibrary.web.onOperateLisenter;
import com.zx.mvplibrary.wedgit.WebChartView;
import com.zx.network.Param;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.starteos.dappsdk.Request;

/**
 * Name: TableFragment
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-01 14:27
 */
public class OrdersFragment extends BaseFragment implements onOperateLisenter {
    @BindView(R.id.web_chart_layout)
    FrameLayout mWebContainer;
    WebChartView webChartView;


    public static OrdersFragment newInstance() {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void preOnCreatView() {

    }

    @Override
    protected int initLayout() {
        return R.layout.order_fragment_layout;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        webChartView = new WebChartView(getContext(), mWebContainer, this, mHandler);
        webChartView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {

    }

    @Override
    public void initWebview(Request request) {
        InitWebView initWebView = new InitWebView(getToken(), "order");
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
        AppLog.print(request.getParams().optString("page"));
    }

    @Override
    public void orderDetailsData(Request request) {

    }

    @OnClick({R.id.print_cosume_order, R.id.fan_jie_zhang_tv, R.id.back_to_list})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.print_cosume_order:
                break;
            case R.id.fan_jie_zhang_tv:
                webChartView.getDataFormWeb("", "orderDetailsData");
                break;
            case R.id.back_to_list:
                webChartView.backTolast();
                break;
        }
    }
}
