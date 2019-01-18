package com.shigoo.cashregister.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.shigoo.cashregister.R;
import com.shigoo.cashregister.mvp.contacts.ConsumeContact;
import com.shigoo.cashregister.mvp.presenter.ConsumePresenter;
import com.xgsb.datafactory.bean.ConsumeListData;
import com.xgsb.datafactory.bean.Member;
import com.xgsb.datafactory.bean.MemberMoney;
import com.xgsb.datafactory.bean.WebData;
import com.zx.mvplibrary.MvpFragment;
import com.zx.mvplibrary.web.onOperateLisenter;
import com.zx.mvplibrary.wedgit.WebChartView;
import com.zx.network.Param;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.starteos.dappsdk.Request;

public class MemberMoneyDetailListFragment extends MvpFragment<ConsumePresenter> implements ConsumeContact.view, onOperateLisenter {
    private String mId;
    @BindView(R.id.web_chart_layout)
    FrameLayout mWebContainer;
    WebChartView mWebChartView;
    private Request request;

    public static MemberMoneyDetailListFragment newInstance() {
        MemberMoneyDetailListFragment fragment = new MemberMoneyDetailListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onGetConsumeListCallback(ConsumeListData list) {

    }

    @Override
    public void onMemberMoneyList(List<MemberMoney> list) {
        if (request != null) {
            String json = WebData.newInstance().getMemberMoney(list, mWebChartView.getWidth(), mWebChartView.getHight());
            mWebChartView.callback(request, json);
        }
    }

    @Override
    protected ConsumePresenter onCtreatPresenter() {
        return new ConsumePresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.member_money_detail_activity;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        mWebChartView = new WebChartView(view.getContext(), mWebContainer, this, mHandler);
        mWebChartView.loadDefaultUrl();
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {

    }

    public void setMemberId(String id) {
        mId = id;
        mPresenter.getMemberMoneyDiatailList(Param.Keys.TOKEN, getToken(), Param.Keys.id, mId);
    }

    @Override
    public void initWebview(Request request) {

    }

    @Override
    public void getTableInfo(Request request) {
        this.request = request;
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
}
