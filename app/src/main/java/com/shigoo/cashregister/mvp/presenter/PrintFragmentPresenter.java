package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.PrintFragmentContact;
import com.zx.mvplibrary.presenter.BasePresenterImpl;

public class PrintFragmentPresenter extends BasePresenterImpl<PrintFragmentContact.view> implements PrintFragmentContact.printPresenter {
    public PrintFragmentPresenter(PrintFragmentContact.view view) {
        super(view);
    }

    @Override
    public void getSaleList(String... params) {

    }

    @Override
    public void getKindRecive(String... params) {

    }

    @Override
    public void getSaleCountList(String... params) {

    }

    @Override
    public void getTableCosumList(String... params) {

    }
}
