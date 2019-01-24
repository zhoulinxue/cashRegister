package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.PrintContacts;
import com.zx.mvplibrary.presenter.BasePresenterImpl;

public class PrintPresenter extends BasePresenterImpl<PrintContacts.view> implements PrintContacts.printPresenter {
    public PrintPresenter(PrintContacts.view view) {
        super(view);
    }

    @Override
    public void getPrintList(String... params) {

    }
}
