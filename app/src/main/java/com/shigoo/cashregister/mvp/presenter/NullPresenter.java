package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.NUllContact;
import com.zx.mvplibrary.presenter.BasePresenterImpl;

public class NullPresenter extends BasePresenterImpl<NUllContact.view> implements NUllContact.presenter {

    public NullPresenter(NUllContact.view view) {
        super(view);
    }
}
