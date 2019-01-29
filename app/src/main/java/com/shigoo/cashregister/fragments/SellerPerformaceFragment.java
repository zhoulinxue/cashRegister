package com.shigoo.cashregister.fragments;

import android.os.Bundle;
import android.view.View;

import com.shigoo.cashregister.mvp.contacts.OrderPerformaceContact;
import com.shigoo.cashregister.mvp.presenter.OrderPerformancePresenter;
import com.xgsb.datafactory.bean.OrderPerformancebean;
import com.zx.mvplibrary.MvpFragment;

import java.util.List;

public class SellerPerformaceFragment extends MvpFragment<OrderPerformancePresenter> implements OrderPerformaceContact.view {

    public static SellerPerformaceFragment newInstance() {
        SellerPerformaceFragment fragment = new SellerPerformaceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onOrderPerformanceListResult(List<OrderPerformancebean> dishesbeans) {

    }

    @Override
    protected OrderPerformancePresenter onCtreatPresenter() {
        return null;
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
}
