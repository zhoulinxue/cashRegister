package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.GiveDetailListContact;
import com.xgsb.datafactory.bean.GiveDetailListbean;
import com.xgsb.datafactory.bean.ListData;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

public class GiveDetailListPresenter extends BasePresenterImpl<GiveDetailListContact.view> implements GiveDetailListContact.presenter {

    public GiveDetailListPresenter(GiveDetailListContact.view view) {
        super(view);
    }

    @Override
    public void getGiveDetailList(String... params) {
        NetRequest request = ApiManager.getInstance().getGiveDetailList(params, mListCallback);
        addRequest(request);
    }

    NetRequestCallBack<ListData<GiveDetailListbean>> mListCallback = new NetRequestCallBack<ListData<GiveDetailListbean>>() {
        @Override
        public void onSuccess(ListData<GiveDetailListbean> tables) {
            mView.onGiveDetailListResult(tables.getData());
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(responseCode,msg);
        }
    };
}
