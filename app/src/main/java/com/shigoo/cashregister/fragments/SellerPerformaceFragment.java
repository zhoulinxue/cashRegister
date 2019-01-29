package com.shigoo.cashregister.fragments;

import android.os.Bundle;
import android.view.View;

import com.shigoo.cashregister.mvp.contacts.SellerPerformaceContact;
import com.shigoo.cashregister.mvp.presenter.SellerPerformancePresenter;
import com.xgsb.datafactory.bean.SalePerformanceDetailbean;
import com.xgsb.datafactory.bean.SalePerformancebean;
import com.zx.mvplibrary.MvpFragment;

import java.util.List;

public class SellerPerformaceFragment extends MvpFragment<SellerPerformancePresenter> implements SellerPerformaceContact.view {

    public static SellerPerformaceFragment newInstance() {
        SellerPerformaceFragment fragment = new SellerPerformaceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int initLayout() {
        return 0;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {

    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {

    }

    @Override
    protected SellerPerformancePresenter onCtreatPresenter() {
        return new SellerPerformancePresenter(this);
    }

    @Override
    public void onOrderPerformanceListResult(List<SalePerformancebean> dishesbeans) {

    }

    @Override
    public void onOrderCount(String number, String money) {

    }

    @Override
    public void onOrderPerformanceDetail(List<SalePerformanceDetailbean> detailbeanList) {

    }
}
