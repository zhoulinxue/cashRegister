package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.SalesPerformanceContact;
import com.zx.mvplibrary.presenter.BasePresenterImpl;

public class SalesPerformancePresenter extends BasePresenterImpl<SalesPerformanceContact.view> implements SalesPerformanceContact.presenter {

    public SalesPerformancePresenter(SalesPerformanceContact.view view) {
        super(view);
    }

    @Override
    public void getSalePerformanceList(String... params) {

    }

    @Override
    public void getOrderPerformanceList(String... params) {

    }
}
