package com.shigoo.cashregister.fragments;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shigoo.cashregister.R;
import com.shigoo.cashregister.mvp.presenter.OrderDishesPresenter;
import com.xgsb.datafactory.JSONManager;
import com.zx.api.api.utils.AppLog;
import com.zx.mvplibrary.BaseFragment;
import com.zx.mvplibrary.MvpFragment;
import com.zx.mvplibrary.web.InitWebView;
import com.zx.mvplibrary.web.onOperateLisenter;
import com.zx.mvplibrary.wedgit.WebChartView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.starteos.dappsdk.Request;

import static com.shigoo.cashregister.activitys.CashRigisterMainActivity.TEST;

/**
 * Name: TableFragment
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-01 14:27
 */
public class BooksOffFragment extends MvpFragment<OrderDishesPresenter> implements onOperateLisenter {
    @BindView(R.id.web_chart_layout)
    FrameLayout mWebContainer;
    WebChartView webChartView;


    public static BooksOffFragment newInstance() {
        BooksOffFragment fragment = new BooksOffFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void preOnCreatView() {

    }

    @Override
    protected OrderDishesPresenter onCtreatPresenter() {
        return null;
    }

    @Override
    protected int initLayout() {
        return R.layout.bookoff_fragment_layout;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        showLoadingDialog();
        webChartView = new WebChartView(getContext(), mWebContainer, this, mHandler);
        webChartView.loadUrl("file:///android_asset/index.html", new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    dismissLoadingDiaog();
                }
            }
        });
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {

    }

    @Override
    public void initWebview(Request request) {
        InitWebView initWebView = new InitWebView(getToken(), "handOverDuty");
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
        AppLog.print(request.getParams()+"交班");
    }

    @Override
    public void handDutyHistroyListPrint(Request request) {
        AppLog.print(request.getParams()+"交班历史");
    }
}
