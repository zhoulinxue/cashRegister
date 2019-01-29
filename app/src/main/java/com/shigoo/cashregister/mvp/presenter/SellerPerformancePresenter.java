package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.SellerPerformaceContact;
import com.zx.mvplibrary.presenter.BasePresenterImpl;

public class SellerPerformancePresenter extends BasePresenterImpl<SellerPerformaceContact.view> implements SellerPerformaceContact.presenter {
    public SellerPerformancePresenter(SellerPerformaceContact.view view) {
        super(view);
    }

    @Override
    public void getOrderPerformanceList(String... params) {

    }

    @Override
    public void getOrderPerformanceDetail(String... params) {

    }
}
