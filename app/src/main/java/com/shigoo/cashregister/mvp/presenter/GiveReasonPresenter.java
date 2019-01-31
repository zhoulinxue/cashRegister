package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.GiveReasonContact;
import com.xgsb.datafactory.bean.AddGivebean;
import com.xgsb.datafactory.bean.GiveDishesbean;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

import java.util.List;

public class GiveReasonPresenter extends BasePresenterImpl<GiveReasonContact.view> implements GiveReasonContact.presenter {
    public GiveReasonPresenter(GiveReasonContact.view view) {
        super(view);
    }

    @Override
    public void getGiveReson(String... params) {
        NetRequest request = ApiManager.getInstance().getReasonList(params, mReasonListCallback);
        addRequest(request);
    }

    @Override
    public void addGive(AddGivebean addGivebean) {
        NetRequest request = ApiManager.getInstance().addGivebean(addGivebean, mReasonCallback);
        addRequest(request);
    }

    NetRequestCallBack<List<String>> mReasonListCallback = new NetRequestCallBack<List<String>>() {
        @Override
        public void onSuccess(List<String> tables) {
            mView.onResonResult(tables);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(responseCode,msg);
        }
    };

    NetRequestCallBack<String> mReasonCallback = new NetRequestCallBack<String>() {
        @Override
        public void onSuccess(String tables) {
            mView.onAddResult(tables);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(responseCode,msg);
        }
    };
}
